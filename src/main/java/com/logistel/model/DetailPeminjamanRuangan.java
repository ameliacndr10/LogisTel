package com.logistel.model;

/**
 * Kelas DetailPeminjamanRuangan mencatat rincian ruangan yang dipinjam
 * dalam suatu transaksi peminjaman beserta nama kegiatan dan deskripsinya.
 */
public class DetailPeminjamanRuangan {
    private int idDetailPeminjamanRuangan;
    private Peminjaman peminjaman;
    private Ruangan ruangan;
    private String namaKegiatan;
    private String deskripsi;

    /**
     * Constructor default.
     */
    public DetailPeminjamanRuangan() {}

    /**
     * Constructor lengkap untuk inisialisasi detail peminjaman ruangan.
     *
     * @param idDetailPeminjamanRuangan ID unik detail ruangan
     * @param peminjaman                Objek transaksi utama Peminjaman
     * @param ruangan                   Objek Ruangan yang dipinjam
     * @param namaKegiatan              Nama kegiatan/acara penggunaan ruangan
     * @param deskripsi                 Deskripsi tambahan kegiatan
     */
    public DetailPeminjamanRuangan(int idDetailPeminjamanRuangan, Peminjaman peminjaman, Ruangan ruangan, String namaKegiatan, String deskripsi) {
        this.idDetailPeminjamanRuangan = idDetailPeminjamanRuangan;
        this.peminjaman = peminjaman;
        this.ruangan = ruangan;
        this.namaKegiatan = namaKegiatan;
        this.deskripsi = deskripsi;
    }

    // Getter dan Setter

    public int getIdDetailPeminjamanRuangan() {
        return idDetailPeminjamanRuangan;
    }

    public void setIdDetailPeminjamanRuangan(int idDetailPeminjamanRuangan) {
        this.idDetailPeminjamanRuangan = idDetailPeminjamanRuangan;
    }

    public Peminjaman getPeminjaman() {
        return peminjaman;
    }

    public void setPeminjaman(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }

    public Ruangan getRuangan() {
        return ruangan;
    }

    public void setRuangan(Ruangan ruangan) {
        this.ruangan = ruangan;
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
