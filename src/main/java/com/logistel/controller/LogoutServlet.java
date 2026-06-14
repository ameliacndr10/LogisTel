package com.logistel.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout-process")
public class LogoutServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processLogout(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processLogout(request, response);
    }

    private void processLogout(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        // 1. Ambil session yang ada saat ini (jangan buat baru jika tidak ada)
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // 2. Hapus seluruh data atribut di dalam session dan hancurkan objek session-nya
            session.invalidate();
        }
        
        // 3. Alihkan pengguna kembali ke halaman login dengan tambahan parameter status
        response.sendRedirect(request.getContextPath() + "/login.jsp?status=logout");
    }
}