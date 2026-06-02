package com.logistel.model;

/**
 * Kelas Barang merepresentasikan item inventaris logistik yang dapat dipinjam oleh user.
 * Mengimplementasikan interface IManajemenStok.
 */
public class Barang implements IManajemenStok {
    private int idBarang;
    private String namaBarang;
    private int stok;

    /**
     * Constructor untuk membuat objek Barang baru.
     *
     * @param idBarang   ID unik barang
     * @param namaBarang Nama jenis barang (misal: KAMERA, SOFA)
     * @param stok       Stok awal barang
     */
    public Barang(int idBarang, String namaBarang, int stok) {
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.stok = stok;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    @Override
    public int getStok() {
        return this.stok;
    }

    @Override
    public void tambahStok(int jumlah) {
        if (jumlah > 0) {
            this.stok += jumlah;
        }
    }

    @Override
    public boolean kurangiStok(int jumlah) {
        if (jumlah > 0 && this.stok >= jumlah) {
            this.stok -= jumlah;
            return true;
        }
        return false;
    }
}
