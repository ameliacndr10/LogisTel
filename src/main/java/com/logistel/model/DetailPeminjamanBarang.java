package com.logistel.model;

/**
 * Kelas DetailPeminjamanBarang mencatat rincian barang yang dipinjam 
 * dalam suatu transaksi peminjaman beserta nama kegiatan dan deskripsinya.
 */
public class DetailPeminjamanBarang {
    private int idDetailPeminjamanBarang;
    private Peminjaman peminjaman;
    private Barang barang;
    private int jumlah;
    private String namaKegiatan;
    private String deskripsi;

    /**
     * Constructor default.
     */
    public DetailPeminjamanBarang() {}

    /**
     * Constructor lengkap untuk inisialisasi detail peminjaman barang.
     *
     * @param idDetailPeminjamanBarang ID unik detail barang
     * @param peminjaman               Objek transaksi utama Peminjaman
     * @param barang                   Objek Barang yang dipinjam
     * @param jumlah                   Jumlah barang yang dipinjam
     * @param namaKegiatan             Nama kegiatan/acara penggunaan barang
     * @param deskripsi                Deskripsi tambahan kegiatan
     */
    public DetailPeminjamanBarang(int idDetailPeminjamanBarang, Peminjaman peminjaman, Barang barang, int jumlah, String namaKegiatan, String deskripsi) {
        this.idDetailPeminjamanBarang = idDetailPeminjamanBarang;
        this.peminjaman = peminjaman;
        this.barang = barang;
        this.jumlah = jumlah;
        this.namaKegiatan = namaKegiatan;
        this.deskripsi = deskripsi;
    }

    // Getter dan Setter

    public int getIdDetailPeminjamanBarang() {
        return idDetailPeminjamanBarang;
    }

    public void setIdDetailPeminjamanBarang(int idDetailPeminjamanBarang) {
        this.idDetailPeminjamanBarang = idDetailPeminjamanBarang;
    }

    public Peminjaman getPeminjaman() {
        return peminjaman;
    }

    public void setPeminjaman(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
