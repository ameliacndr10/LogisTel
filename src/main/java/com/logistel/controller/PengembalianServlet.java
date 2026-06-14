package com.logistel.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.logistel.config.DatabaseConfig;

@WebServlet("/pengembalian-barang")
public class PengembalianServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // PENTING: Harus "id_peminjaman" karena JavaScript di JSP mengirim dengan nama ini
        String idPeminjaman = request.getParameter("id_peminjaman"); 
        
        // Log debug ke konsol server (Tomcat) untuk memastikan ID masuk atau tidak
        System.out.println("DEBUG LOGISTEL: Mengajukan retur untuk ID Peminjaman = " + idPeminjaman);

        if (idPeminjaman == null || idPeminjaman.trim().isEmpty()) {
            System.out.println("DEBUG LOGISTEL: ID Peminjaman Kosong/Null!");
            response.sendRedirect("dashboard-user.jsp?status=gagal");
            return;
        }

        // Gunakan id_user atau id_peminjaman secara konsisten sesuai tipe kolom DB Anda (INT)
        try (Connection conn = DatabaseConfig.getConnection()) {
            
            // Query update status menjadi 'RETURN_REQUESTED'
            String sql = "UPDATE peminjaman SET status = 'RETURN_REQUESTED' WHERE id_peminjaman = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                // Karena id_peminjaman di DB bertipe INT, ubah String menjadi Integer
                ps.setInt(1, Integer.parseInt(idPeminjaman.trim()));
                ps.executeUpdate();
            }
            
            // Jika berhasil, arahkan ke retur_sukses
            response.sendRedirect("dashboard-user.jsp?status=retur_sukses");
            
        } catch (Exception e) {
            // Cetak error lengkap ke konsol NetBeans/Tomcat untuk pelacakan
            System.out.println("DEBUG LOGISTEL ERROR: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect("dashboard-user.jsp?status=gagal");
        }
    }
}