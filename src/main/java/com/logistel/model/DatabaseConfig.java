package com.logistel.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Kelas DatabaseConfig berfungsi untuk mengelola dan menyediakan koneksi
 * ke database MySQL menggunakan JDBC dengan pola desain Singleton.
 */
public class DatabaseConfig {
    // 1. Konstanta Konfigurasi Database
    private static final String URL = "jdbc:mysql://localhost:3306/logistel_db"; // Menggunakan port default 3306 (sesuaikan ke 3606 jika diperlukan)
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // 2. Instance tunggal Connection untuk pola Singleton
    private static Connection connection = null;

    // 3. Static initializer block untuk memuat (load) Driver MySQL JDBC secara aman
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("[DATABASE ERROR] Driver MySQL JDBC tidak ditemukan!");
            System.err.println("Pastikan mysql-connector-j telah ditambahkan ke dependensi proyek.");
            e.printStackTrace();
        }
    }

    // Private constructor untuk mencegah instansiasi dari luar kelas (Singleton Pattern)
    private DatabaseConfig() {}

    /**
     * Mendapatkan koneksi ke database. Jika koneksi belum dibuat atau sudah ditutup,
     * fungsi ini akan mencoba menghubungkan kembali.
     *
     * @return Objek Connection yang aktif
     * @throws SQLException jika gagal melakukan koneksi ke database
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Memeriksa apakah koneksi belum ada, atau sudah ditutup sebelumnya
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("[DATABASE INFO] Berhasil terhubung ke database: " + URL);
            }
        } catch (SQLException e) {
            System.err.println("[DATABASE ERROR] Gagal menyambungkan ke MySQL/XAMPP!");
            System.err.println("Detail Masalah:");
            System.err.println("- URL Database : " + URL);
            System.err.println("- Pesan Error  : " + e.getMessage());
            System.err.println("- Error Code   : " + e.getErrorCode());
            System.err.println("- SQL State    : " + e.getSQLState());
            throw e; // Lemparkan kembali agar kelas pemanggil tahu koneksi gagal
        }
        return connection;
    }
}
