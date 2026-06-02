package com.logistel.model;

/**
 * Kelas Ruangan merepresentasikan fasilitas ruangan logistik yang dapat dipinjam oleh user.
 * Mengimplementasikan interface IManajemenStok.
 */
public class Ruangan implements IManajemenStok {
    private int idRuangan;
    private String namaRuangan;
    private int stok; // 1 untuk tersedia (default), 0 untuk terisi/sedang dipinjam

    /**
     * Constructor untuk membuat objek Ruangan baru.
     *
     * @param idRuangan   ID unik ruangan
     * @param namaRuangan Nama ruangan/fasilitas (misal: AULA RACHMAT EFFENDY)
     * @param stok       Stok ketersediaan ruangan (default = 1)
     */
    public Ruangan(int idRuangan, String namaRuangan, int stok) {
        this.idRuangan = idRuangan;
        this.namaRuangan = namaRuangan;
        this.stok = stok;
    }

    public int getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(int idRuangan) {
        this.idRuangan = idRuangan;
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public void setNamaRuangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
    }

    @Override
    public int getStok() {
        return this.stok;
    }

    @Override
    public void tambahStok(int jumlah) {
        if (jumlah > 0) {
            this.stok = 1; // Ruangan kembali tersedia (maksimum ketersediaan adalah 1)
        }
    }

    @Override
    public boolean kurangiStok(int jumlah) {
        if (jumlah > 0 && this.stok >= jumlah) {
            this.stok -= jumlah; // Ruangan terisi/tidak tersedia lagi (stok menjadi 0)
            return true;
        }
        return false;
    }

    /**
     * Memeriksa ketersediaan ruangan secara eksplisit.
     *
     * @return true jika ruangan siap dipinjam, false jika sedang terisi
     */
    public boolean isTersedia() {
        return this.stok > 0;
    }
}
