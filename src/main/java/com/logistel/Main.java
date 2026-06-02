package com.logistel;

import com.logistel.model.Admin;
import com.logistel.model.AuthService;
import com.logistel.model.Barang;
import com.logistel.model.DatabaseConfig;
import com.logistel.model.DetailPeminjamanBarang;
import com.logistel.model.DetailPeminjamanRuangan;
import com.logistel.model.IManajemenStok;
import com.logistel.model.Peminjaman;
import com.logistel.model.PeminjamanService;
import com.logistel.model.Pengguna;
import com.logistel.model.Ruangan;
import com.logistel.model.User;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Kelas utama untuk mendemonstrasikan instansiasi objek, penggunaan inheritance,
 * polymorphism, enkapsulasi, dan method khusus pada kelas Admin dan User.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== LOGISTEL SYSTEM INITIALIZATION ===");

        // 1. Instansiasi Admin
        Admin admin = new Admin(
            "ADM001",
            "Budi Santoso",
            "budis",
            "passwordAdmin123",
            "NIP19950812"
        );

        // 2. Instansiasi User
        User user = new User(
            "USR001",
            "Andi Wijaya",
            "andiw",
            "passwordUser456",
            "1202220001",
            "Himpunan Mahasiswa Teknologi Informasi (HMTI)",
            "081234567890",
            "andiw@student.telkomuniversity.ac.id"
        );

        // 3. Demonstrasi Polymorphism menggunakan tipe data Parent (Pengguna)
        Pengguna p1 = admin;
        Pengguna p2 = user;

        System.out.println("\n--- Mengakses lewat Superclass (Polymorphism) ---");
        System.out.println("Pengguna 1 - Nama: " + p1.getNama() + " | Role: " + p1.getRole());
        System.out.println("Pengguna 2 - Nama: " + p2.getNama() + " | Role: " + p2.getRole());

        // 4. Demonstrasi enkapsulasi & method khusus Admin
        System.out.println("\n--- Detail Admin ---");
        System.out.println("ID Admin    : " + admin.getIdPengguna());
        System.out.println("No. Pegawai : " + admin.getNoPegawai());
        System.out.println("Username    : " + admin.getUsername());

        // 5. Demonstrasi method khusus User (tampilkanProfilLengkap)
        System.out.println("\n--- Detail Profil User ---");
        System.out.println(user.getProfilLengkap());

        // 6. Uji Coba Koneksi Database
        System.out.println("\n--- Menguji Koneksi Database ---");
        try {
            Connection conn = DatabaseConfig.getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("[KONEKSI SUKSES] LogisTel siap terhubung ke database!");
            }
        } catch (SQLException e) {
            System.out.println("[KONEKSI GAGAL] Tidak dapat terhubung ke database (ini wajar jika MySQL/XAMPP belum aktif).");
        }

        // 7. Demonstrasi Instansiasi AuthService
        System.out.println("\n--- Inisialisasi AuthService ---");
        AuthService authService = new AuthService();
        System.out.println("AuthService berhasil dimuat: " + (authService != null));

        // 8. Demonstrasi Simulasi Inventaris Logistik (Barang & Ruangan)
        System.out.println("\n--- Simulasi Inventaris Logistik ---");
        
        // Menggunakan Polymorphism dengan interface IManajemenStok
        IManajemenStok kamera = new Barang(1, "KAMERA", 10);
        IManajemenStok aula = new Ruangan(1, "AULA RACHMAT EFFENDY", 1);

        System.out.println("Stok Awal:");
        System.out.println("- Barang (" + ((Barang) kamera).getNamaBarang() + "): " + kamera.getStok() + " unit");
        System.out.println("- Ruangan (" + ((Ruangan) aula).getNamaRuangan() + "): " + (aula.getStok() > 0 ? "Tersedia" : "Dipinjam"));

        System.out.println("\n[Skenario Peminjaman]");
        System.out.println("User meminjam 3 unit KAMERA...");
        if (kamera.kurangiStok(3)) {
            System.out.println("-> Peminjaman sukses! Stok KAMERA sekarang: " + kamera.getStok() + " unit");
        } else {
            System.out.println("-> Peminjaman gagal! Stok tidak mencukupi.");
        }

        System.out.println("User meminjam AULA RACHMAT EFFENDY...");
        if (aula.kurangiStok(1)) {
            System.out.println("-> Peminjaman sukses! AULA sekarang: " + (aula.getStok() > 0 ? "Tersedia" : "Dipinjam"));
        } else {
            System.out.println("-> Peminjaman gagal! Ruangan sedang dipakai.");
        }

        System.out.println("\n[Skenario Peminjaman Ganda / Overbook]");
        System.out.println("User lain mencoba meminjam AULA RACHMAT EFFENDY kembali...");
        if (aula.kurangiStok(1)) {
            System.out.println("-> Peminjaman sukses!");
        } else {
            System.out.println("-> Peminjaman gagal! Ruangan sudah terisi.");
        }

        System.out.println("\n[Skenario Pengembalian]");
        System.out.println("User mengembalikan AULA RACHMAT EFFENDY...");
        aula.tambahStok(1);
        System.out.println("-> Pengembalian sukses! Status AULA: " + (aula.getStok() > 0 ? "Tersedia" : "Dipinjam"));

        // 9. Demonstrasi Transaksi Peminjaman (Peminjaman & Detail Peminjaman)
        System.out.println("\n--- Simulasi Pembuatan Objek Transaksi Peminjaman ---");
        Peminjaman transaksiBarang = new Peminjaman(101, user, "2026-05-20", "2026-05-25", "PENDING", "TX-BARANG-001");
        DetailPeminjamanBarang detailBarang = new DetailPeminjamanBarang(201, transaksiBarang, (Barang) kamera, 2, "Pameran Ormawa", "Meminjam kamera untuk dokumentasi");

        System.out.println("Detail Transaksi Peminjaman Barang:");
        System.out.println("- Peminjam       : " + detailBarang.getPeminjaman().getUserPeminjam().getNama());
        System.out.println("- Barang         : " + detailBarang.getBarang().getNamaBarang());
        System.out.println("- Jumlah         : " + detailBarang.getJumlah() + " unit");
        System.out.println("- Kegiatan       : " + detailBarang.getNamaKegiatan());
        System.out.println("- Status Awal    : " + detailBarang.getPeminjaman().getStatus());

        PeminjamanService peminjamanService = new PeminjamanService();
        System.out.println("\nPeminjamanService berhasil dimuat: " + (peminjamanService != null));
        
        System.out.println("\n===============================================");
    }
}
