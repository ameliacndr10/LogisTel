package com.logistel.model;

/**
 * PILAR INTERFACE & ENKAPSULASI: Kelas Barang merepresentasikan item inventaris logistik
 * yang mengimplementasikan dua interface: IManajemenStok dan LogistikAset.
 */
public class Barang implements IManajemenStok, LogistikAset {
    // PILAR ENKAPSULASI: Atribut private untuk melindungi state objek
    private int idBarang;
    private String namaBarang;
    private int stok;

    /**
     * PILAR CONSTRUCTOR OVERLOADING: Constructor 1 - Default Constructor
     */
    public Barang() {}

    /**
     * PILAR CONSTRUCTOR OVERLOADING: Constructor 2 - Constructor Lengkap dengan Parameter
     *
     * @param idBarang   ID unik barang
     * @param namaBarang Nama jenis barang (misal: KAMERA, SOFA)
     * @param stok       Stok awal barang
     */
    public Barang(int idBarang, String namaBarang, int stok) {
        setIdBarang(idBarang);
        setNamaBarang(namaBarang);
        setStok(stok);
    }

    // Getter dan Setter dengan Validasi Ketat (Enkapsulasi)

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        if (idBarang <= 0) {
            throw new IllegalArgumentException("ID Barang harus lebih besar dari 0.");
        }
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        if (namaBarang == null || namaBarang.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama barang tidak boleh kosong.");
        }
        this.namaBarang = namaBarang;
    }

    @Override
    public int getStok() {
        return this.stok;
    }

    public void setStok(int stok) {
        if (stok < 0) {
            throw new IllegalArgumentException("Stok barang tidak boleh minus.");
        }
        this.stok = stok;
    }

    // PILAR EXCEPTION HANDLING: Validasi pada operasi penambahan stok
    @Override
    public void tambahStok(int jumlah) {
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah penambahan stok harus lebih besar dari 0.");
        }
        this.stok += jumlah;
    }

    // PILAR EXCEPTION HANDLING: Validasi dan throwing exception jika stok tidak mencukupi
    @Override
    public boolean kurangiStok(int jumlah) {
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah pengurangan stok harus lebih besar dari 0.");
        }
        if (this.stok < jumlah) {
            throw new IllegalArgumentException("Stok barang '" + namaBarang + "' tidak mencukupi. Stok saat ini: " + this.stok + ", diminta: " + jumlah);
        }
        this.stok -= jumlah;
        return true;
    }

    /**
     * IMPLEMENTASI INTERFACE: Merealisasikan kontrak dari LogistikAset
     */
    @Override
    public void perbaruiStatus() {
        System.out.println("[LOGISTIK INFO] Status Barang '" + namaBarang + "' diperbarui. Stok aktif saat ini: " + stok);
    }
}
