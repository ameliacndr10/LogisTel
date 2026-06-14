package com.logistel.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.logistel.config.DatabaseConfig;

@WebServlet("/pengajuan-peminjaman")
public class PengajuanPeminjamanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Ambil session yang aktif
        HttpSession session = request.getSession(false);
        
        // Proteksi: Jaga-jaga jika session kosong / expired, tendang kembali ke login
        if (session == null || session.getAttribute("id_pengguna") == null) {
            response.sendRedirect("login.jsp?status=timeout");
            return;
        }

        // 1. OTOMATIS: Mengambil id_pengguna dari session untuk mengisi id_user di database peminjaman
        int idUserFix = (Integer) session.getAttribute("id_pengguna");

        // Ambil data dari form jsp
        String nim = request.getParameter("nim");
        String tipe = request.getParameter("tipe_inventaris"); // "Barang" atau "Ruangan"
        String idItem = request.getParameter("id_item");       // ID Barang atau ID Ruangan
        String jumlahStr = request.getParameter("jumlah");
        String kegiatan = request.getParameter("nama_kegiatan");
        String tglMulai = request.getParameter("tanggal_mulai");
        String tglSelesai = request.getParameter("tanggal_selesai");
        String deskripsi = request.getParameter("deskripsi");

        // Validasi input wajib awal
        if (idItem == null || idItem.trim().isEmpty() || tglMulai == null || tglSelesai == null) {
            response.setContentType("text/html");
            response.getWriter().println("<h3>Gagal Validasi: Parameter id_item, tanggal_mulai, atau tanggal_selesai kosong!</h3>");
            return;
        }

        Connection conn = null;
        PreparedStatement psPeminjaman = null;
        PreparedStatement psDetail = null;
        ResultSet rsKeys = null;

        try {
            conn = DatabaseConfig.getConnection();
            conn.setAutoCommit(false); // Mode transaksi database aktif

            // =================================================================
            // LANGKAH 1: INSERT KE TABEL INDUK (peminjaman)
            // =================================================================
            String sqlPeminjaman = "INSERT INTO peminjaman (id_user, tanggal_mulai, tanggal_selesai, nama_kegiatan, deskripsi, status, barcode) VALUES (?, ?, ?, ?, ?, 'PENDING', ?)";
            psPeminjaman = conn.prepareStatement(sqlPeminjaman, Statement.RETURN_GENERATED_KEYS);
            
            psPeminjaman.setInt(1, idUserFix); // Otomatis dari akun yang sedang login!
            psPeminjaman.setString(2, tglMulai);
            psPeminjaman.setString(3, tglSelesai);
            psPeminjaman.setString(4, kegiatan);
            psPeminjaman.setString(5, deskripsi);
            
            // Generate kode barcode unik menggunakan UUID agar tidak duplicate entry
            String barcodeData = java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            psPeminjaman.setString(6, barcodeData);
            
            psPeminjaman.executeUpdate();

            // Ambil ID Peminjaman yang barusan digenerate otomatis
            int idPeminjamanGenerated = 0;
            rsKeys = psPeminjaman.getGeneratedKeys();
            if (rsKeys.next()) {
                idPeminjamanGenerated = rsKeys.getInt(1);
            }

            // =================================================================
            // LANGKAH 2: INSERT KE TABEL DETAIL (Barang / Ruangan)
            // =================================================================
            if (idPeminjamanGenerated != 0) {
                if ("Barang".equalsIgnoreCase(tipe)) {
                    String sqlDetailBarang = "INSERT INTO detail_peminjaman_barang (id_peminjaman, id_barang, jumlah, nama_kegiatan, deskripsi) VALUES (?, ?, ?, ?, ?)";
                    psDetail = conn.prepareStatement(sqlDetailBarang);
                    psDetail.setInt(1, idPeminjamanGenerated);
                    psDetail.setInt(2, Integer.parseInt(idItem.trim()));
                    
                    int jumlahFix = 1;
                    if (jumlahStr != null && !jumlahStr.trim().isEmpty()) {
                        jumlahFix = Integer.parseInt(jumlahStr.trim());
                    }
                    psDetail.setInt(3, jumlahFix);
                    psDetail.setString(4, kegiatan);
                    psDetail.setString(5, deskripsi);
                    psDetail.executeUpdate();

                } else if ("Ruangan".equalsIgnoreCase(tipe)) {
                    String sqlDetailRuangan = "INSERT INTO detail_peminjaman_ruangan (id_peminjaman, id_ruangan, nama_kegiatan, deskripsi) VALUES (?, ?, ?, ?)";
                    psDetail = conn.prepareStatement(sqlDetailRuangan);
                    psDetail.setInt(1, idPeminjamanGenerated);
                    psDetail.setInt(2, Integer.parseInt(idItem.trim()));
                    psDetail.setString(3, kegiatan);
                    psDetail.setString(4, deskripsi);
                    psDetail.executeUpdate();
                }
            }

            conn.commit(); // Sukses total, simpan permanen ke MySQL
            response.sendRedirect("dashboard-user.jsp?status=berhasil");

        } catch (Exception e) {
            // Batalkan transaksi jika ada eror
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            // Cetak error aslinya ke browser jika sewaktu-waktu ada kendala tipe data lain
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h2>Terjadi Kesalahan System (Database Error):</h2>");
            out.println("<pre style='color:red; background:#f4f4f4; padding:15px; border:1px solid #ccc;'>");
            e.printStackTrace(out);
            out.println("</pre>");
            out.println("<br><a href='dashboard-user.jsp'>Kembali ke Dashboard</a>");
            
        } finally {
            try { if (rsKeys != null) rsKeys.close(); } catch (Exception e) {}
            try { if (psPeminjaman != null) psPeminjaman.close(); } catch (Exception e) {}
            try { if (psDetail != null) psDetail.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}