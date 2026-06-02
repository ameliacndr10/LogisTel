package com.logistel.model;

/**
 * Kelas Peminjaman berfungsi sebagai kelas asosiasi utama untuk mencatat 
 * transaksi peminjaman barang maupun ruangan di LogisTel.
 */
public class Peminjaman {
    private int idPeminjaman;
    private User userPeminjam;
    private String tanggalMulai;
    private String tanggalSelesai;
    private String status; // "PENDING", "APPROVED", "REJECTED", "RETURNED"
    private String barcode;

    /**
     * Constructor kosong/default.
     */
    public Peminjaman() {}

    /**
     * Constructor lengkap untuk inisialisasi Peminjaman.
     *
     * @param idPeminjaman   ID unik peminjaman
     * @param userPeminjam   User yang melakukan peminjaman
     * @param tanggalMulai   Tanggal mulai peminjaman (Format: YYYY-MM-DD)
     * @param tanggalSelesai Tanggal selesai peminjaman (Format: YYYY-MM-DD)
     * @param status         Status transaksi (PENDING, APPROVED, REJECTED, RETURNED)
     * @param barcode        Barcode unik transaksi
     */
    public Peminjaman(int idPeminjaman, User userPeminjam, String tanggalMulai, String tanggalSelesai, String status, String barcode) {
        this.idPeminjaman = idPeminjaman;
        this.userPeminjam = userPeminjam;
        this.tanggalMulai = tanggalMulai;
        this.tanggalSelesai = tanggalSelesai;
        this.status = status;
        this.barcode = barcode;
    }

    // Getter dan Setter

    public int getIdPeminjaman() {
        return idPeminjaman;
    }

    public void setIdPeminjaman(int idPeminjaman) {
        this.idPeminjaman = idPeminjaman;
    }

    public User getUserPeminjam() {
        return userPeminjam;
    }

    public void setUserPeminjam(User userPeminjam) {
        this.userPeminjam = userPeminjam;
    }

    public String getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(String tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public String getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(String tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
