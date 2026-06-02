package com.logistel.model;

/**
 * Interface IManajemenStok mendefinisikan operasi dasar untuk manajemen inventaris stok barang atau ruangan.
 */
public interface IManajemenStok {
    /**
     * Mendapatkan jumlah stok atau ketersediaan saat ini.
     *
     * @return Jumlah stok/ketersediaan
     */
    int getStok();

    /**
     * Menambahkan jumlah stok atau mengeset ketersediaan.
     *
     * @param jumlah Jumlah stok yang ditambahkan
     */
    void tambahStok(int jumlah);

    /**
     * Mengurangi jumlah stok atau mengubah ketersediaan.
     *
     * @param jumlah Jumlah stok yang dikurangi
     * @return true jika berhasil dikurangi, false jika stok tidak mencukupi
     */
    boolean kurangiStok(int jumlah);
}
