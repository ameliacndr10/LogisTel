package com.logistel.model;

/**
 * PILAR ENKAPSULASI: Kelas Peminjaman berfungsi sebagai kelas asosiasi utama untuk mencatat 
 * transaksi peminjaman barang maupun ruangan di LogisTel. Dilengkapi enkapsulasi dengan validasi setter.
 */
public class Peminjaman {
    private int idPeminjaman;
    private User userPeminjam;
    private String tanggalMulai;
    private String tanggalSelesai;
    private String status; // "PENDING", "APPROVED", "REJECTED", "RETURNED"
    private String barcode;

    /**
     * PILAR CONSTRUCTOR OVERLOADING: Constructor 1 - Default Constructor
     */
    public Peminjaman() {}

    /**
     * PILAR CONSTRUCTOR OVERLOADING: Constructor 2 - Constructor Lengkap
     *
     * @param idPeminjaman   ID unik peminjaman
     * @param userPeminjam   User yang melakukan peminjaman
     * @param tanggalMulai   Tanggal mulai peminjaman (Format: YYYY-MM-DD)
     * @param tanggalSelesai Tanggal selesai peminjaman (Format: YYYY-MM-DD)
     * @param status         Status transaksi (PENDING, APPROVED, REJECTED, RETURNED)
     * @param barcode        Barcode unik transaksi
     */
    public Peminjaman(int idPeminjaman, User userPeminjam, String tanggalMulai, String tanggalSelesai, String status, String barcode) {
        setIdPeminjaman(idPeminjaman);
        setUserPeminjam(userPeminjam);
        setTanggalMulai(tanggalMulai);
        setTanggalSelesai(tanggalSelesai);
        setStatus(status);
        setBarcode(barcode);
    }

    // Getter dan Setter dengan Validasi (Enkapsulasi)

    public int getIdPeminjaman() {
        return idPeminjaman;
    }

    public void setIdPeminjaman(int idPeminjaman) {
        if (idPeminjaman <= 0) {
            throw new IllegalArgumentException("ID Peminjaman harus lebih besar dari 0.");
        }
        this.idPeminjaman = idPeminjaman;
    }

    public User getUserPeminjam() {
        return userPeminjam;
    }

    public void setUserPeminjam(User userPeminjam) {
        if (userPeminjam == null) {
            throw new IllegalArgumentException("User peminjam tidak boleh kosong/null.");
        }
        this.userPeminjam = userPeminjam;
    }

    public String getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(String tanggalMulai) {
        if (tanggalMulai == null || tanggalMulai.trim().isEmpty()) {
            throw new IllegalArgumentException("Tanggal mulai tidak boleh kosong.");
        }
        this.tanggalMulai = tanggalMulai;
    }

    public String getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(String tanggalSelesai) {
        if (tanggalSelesai == null || tanggalSelesai.trim().isEmpty()) {
            throw new IllegalArgumentException("Tanggal selesai tidak boleh kosong.");
        }
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status tidak boleh kosong.");
        }
        this.status = status;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        if (barcode == null || barcode.trim().isEmpty()) {
            throw new IllegalArgumentException("Barcode tidak boleh kosong.");
        }
        this.barcode = barcode;
    }
}
