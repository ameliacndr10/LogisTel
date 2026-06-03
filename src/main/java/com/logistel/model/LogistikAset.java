package com.logistel.model;

/**
 * PILAR INTERFACE (Sesuai Modul 5):
 * Interface LogistikAset mendefinisikan kontrak standar untuk mengelola
 * status ketersediaan atau kondisi dari aset logistik (Barang dan Ruangan).
 */
public interface LogistikAset {
    /**
     * Memperbarui status ketersediaan atau kondisi dari aset logistik.
     * Semua kelas yang mengimplementasikan interface ini harus mendefinisikan method ini.
     */
    void perbaruiStatus();
}
