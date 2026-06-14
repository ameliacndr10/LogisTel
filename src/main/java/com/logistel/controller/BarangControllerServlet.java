package com.logistel.controller;

import java.io.IOException;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.logistel.config.DatabaseConfig;

@WebServlet("/barang-action")
public class BarangControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        String id = request.getParameter("id");

        try (Connection conn = DatabaseConfig.getConnection()) {
            if ("hapus".equals(action)) {
                PreparedStatement ps = conn.prepareStatement("DELETE FROM barang WHERE id_barang = ?");
                ps.setString(1, id);
                ps.executeUpdate();
                
                // LANGKAH 3: Redirect khusus setelah sukses menghapus
                response.sendRedirect("dashboard-admin.jsp?status=hapus_sukses");
                return; // Berhenti di sini agar tidak mengeksekusi redirect di bawah blok if-else

            } else if ("edit".equals(action)) {
                PreparedStatement ps = conn.prepareStatement("UPDATE barang SET nama_barang=?, stok=? WHERE id_barang=?");
                ps.setString(1, request.getParameter("nama_barang").toUpperCase());
                ps.setString(2, request.getParameter("stok"));
                ps.setString(3, id);
                ps.executeUpdate();
                
                // LANGKAH 3: Redirect khusus setelah sukses mengedit
                response.sendRedirect("dashboard-admin.jsp?status=edit_sukses");
                return; // Berhenti di sini
            }
            
            // Fallback jikalau parameter action tidak cocok
            response.sendRedirect("dashboard-admin.jsp");

        } catch (Exception e) { 
            e.printStackTrace(); 
            // LANGKAH 3: Redirect dinamis jika terjadi error database berdasarkan jenis aksinya
            if ("edit".equals(action)) {
                response.sendRedirect("dashboard-admin.jsp?status=edit_gagal");
            } else if ("hapus".equals(action)) {
                response.sendRedirect("dashboard-admin.jsp?status=hapus_gagal");
            } else {
                response.sendRedirect("dashboard-admin.jsp?status=gagal");
            }
        }
    }
}