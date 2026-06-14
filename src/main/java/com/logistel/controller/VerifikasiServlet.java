package com.logistel.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.logistel.config.DatabaseConfig;

@WebServlet("/verifikasi-servlet")
public class VerifikasiServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String idRaw = request.getParameter("id");
        String action = request.getParameter("action"); // "APPROVED", "REJECTED", atau "RETURNED"

        // Validasi awal parameter
        if (idRaw == null || action == null) {
            response.sendRedirect("dashboard-admin.jsp?status=gagal");
            return;
        }

        // PERUBAHAN 1: Konversi langsung String ID ke bentuk primitif int agar tidak error kompilasi
        int idPeminjaman = Integer.parseInt(idRaw.trim());

        try (Connection conn = DatabaseConfig.getConnection()) {
            
            // =================================================================
            // LOGIKA UTAMA 1: JIKA ACTION ADALAH "APPROVED" (CEK BENTROK DULU)
            // =================================================================
            if ("APPROVED".equalsIgnoreCase(action)) {
                
                // 1. Ambil data tanggal dan ID logistik asli dari pengajuan terkait
                String sqlGetDetail = "SELECT p.tanggal_mulai, p.tanggal_selesai, " +
                                      "COALESCE(dpb.id_barang, 0) as id_barang, " +
                                      "COALESCE(dpr.id_ruangan, 0) as id_ruangan " +
                                      "FROM peminjaman p " +
                                      "LEFT JOIN detail_peminjaman_barang dpb ON p.id_peminjaman = dpb.id_peminjaman " +
                                      "LEFT JOIN detail_peminjaman_ruangan dpr ON p.id_peminjaman = dpr.id_peminjaman " +
                                      "WHERE p.id_peminjaman = ?";
                
                String tglMulai = "";
                String tglSelesai = "";
                int idBarang = 0;
                int idRuangan = 0;
                
                try (PreparedStatement psGet = conn.prepareStatement(sqlGetDetail)) {
                    psGet.setInt(1, idPeminjaman);
                    try (ResultSet rsGet = psGet.executeQuery()) {
                        if (rsGet.next()) {
                            tglMulai = rsGet.getString("tanggal_mulai");
                            tglSelesai = rsGet.getString("tanggal_selesai");
                            idBarang = rsGet.getInt("id_barang");
                            idRuangan = rsGet.getInt("id_ruangan");
                        }
                    }
                }
                
                // 2. Cek apakah sudah ada peminjaman lain yang berstatus APPROVED di rentang tanggal tersebut
                boolean sudahAdaYangApproved = false;
                String sqlCheck = "";
                
                if (idBarang > 0) {
                    sqlCheck = "SELECT COUNT(*) FROM detail_peminjaman_barang db " +
                               "JOIN peminjaman p ON db.id_peminjaman = p.id_peminjaman " +
                               "WHERE db.id_barang = ? AND p.status = 'APPROVED' " +
                               "AND (? <= p.tanggal_selesai AND ? >= p.tanggal_mulai)";
                } else if (idRuangan > 0) {
                    sqlCheck = "SELECT COUNT(*) FROM detail_peminjaman_ruangan dr " +
                               "JOIN peminjaman p ON dr.id_peminjaman = p.id_peminjaman " +
                               "WHERE dr.id_ruangan = ? AND p.status = 'APPROVED' " +
                               "AND (? <= p.tanggal_selesai AND ? >= p.tanggal_mulai)";
                }
                
                if (!sqlCheck.isEmpty()) {
                    try (PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
                        psCheck.setInt(1, idBarang > 0 ? idBarang : idRuangan);
                        psCheck.setString(2, tglMulai);
                        psCheck.setString(3, tglSelesai);
                        try (ResultSet rsCheck = psCheck.executeQuery()) {
                            if (rsCheck.next() && rsCheck.getInt(1) > 0) {
                                sudahAdaYangApproved = true;
                            }
                        }
                    }
                }
                
                // 3. Eksekusi keputusan akhir verifikasi approval
                if (sudahAdaYangApproved) {
                    // Jika terdeteksi jadwal telah dikunci duluan, gagalkan proses
                    response.sendRedirect("dashboard-admin.jsp?status=gagal_bentrok");
                    return;
                } else {
                    // Jika aman, lakukan update status PEMINJAMAN menjadi APPROVED
                    String updateSql = "UPDATE peminjaman SET status = 'APPROVED' WHERE id_peminjaman = ?";
                    try (PreparedStatement ps = conn.prepareStatement(updateSql)) {
                        ps.setInt(1, idPeminjaman);
                        ps.executeUpdate();
                    }
                    
                    // Kurangi jumlah stok logistik di gudang
                    String updateStok = "UPDATE barang b " +
                                        "JOIN detail_peminjaman_barang dp ON b.id_barang = dp.id_barang " +
                                        "SET b.stok = b.stok - dp.jumlah " +
                                        "WHERE dp.id_peminjaman = ?";
                    try (PreparedStatement psStok = conn.prepareStatement(updateStok)) {
                        psStok.setInt(1, idPeminjaman);
                        psStok.executeUpdate();
                    }
                    
                    response.sendRedirect("dashboard-admin.jsp?status=approved_sukses");
                    return;
                }
            }
            
            // =================================================================
            // LOGIKA UTAMA 2: JIKA ACTION ADALAH "RETURNED" (PENGEMBALIAN)
            // =================================================================
            else if ("RETURNED".equalsIgnoreCase(action)) {
                // Update status induk menjadi RETURNED
                String updateSql = "UPDATE peminjaman SET status = 'RETURNED' WHERE id_peminjaman = ?";
                try (PreparedStatement ps = conn.prepareStatement(updateSql)) {
                    ps.setInt(1, idPeminjaman);
                    ps.executeUpdate();
                }

                // Tambahkan kembali kuantitas stok barang semula ke dalam tabel barang
                String kembalikanStokSql = "UPDATE barang b " +
                                           "JOIN detail_peminjaman_barang dp ON b.id_barang = dp.id_barang " +
                                           "SET b.stok = b.stok + dp.jumlah " +
                                           "WHERE dp.id_peminjaman = ?";
                try (PreparedStatement psReturStok = conn.prepareStatement(kembalikanStokSql)) {
                    psReturStok.setInt(1, idPeminjaman);
                    psReturStok.executeUpdate();
                }
                
                response.sendRedirect("dashboard-admin.jsp?status=return_sukses");
                return;
            }
            
            // =================================================================
            // LOGIKA UTAMA 3: JIKA ACTION ADALAH "REJECTED" (PENOLAKAN)
            // =================================================================
            else if ("REJECTED".equalsIgnoreCase(action)) {
                String updateSql = "UPDATE peminjaman SET status = 'REJECTED' WHERE id_peminjaman = ?";
                try (PreparedStatement ps = conn.prepareStatement(updateSql)) {
                    ps.setInt(1, idPeminjaman);
                    ps.executeUpdate();
                }
                response.sendRedirect("dashboard-admin.jsp?status=rejected_sukses");
                return;
            }
            
            // Pengamanan fallback jika ada status antrean lain luar sistem
            response.sendRedirect("dashboard-admin.jsp?status=success");
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("dashboard-admin.jsp?status=gagal");
        }
    }
}