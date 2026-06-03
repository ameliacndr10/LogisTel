package com.logistel.model;

/**
 * PILAR INTERFACE & ENKAPSULASI: Kelas Ruangan merepresentasikan fasilitas ruangan logistik
 * yang mengimplementasikan dua interface: IManajemenStok dan LogistikAset.
 */
public class Ruangan implements IManajemenStok, LogistikAset {
    // PILAR ENKAPSULASI: Atribut private untuk melindungi state objek
    private int idRuangan;
    private String namaRuangan;
    private int stok; // 1 untuk tersedia (default), 0 untuk terisi/sedang dipinjam

    /**
     * PILAR CONSTRUCTOR OVERLOADING: Constructor 1 - Default Constructor
     */
    public Ruangan() {}

    /**
     * PILAR CONSTRUCTOR OVERLOADING: Constructor 2 - Constructor Lengkap dengan Parameter
     *
     * @param idRuangan   ID unik ruangan
     * @param namaRuangan Nama ruangan/fasilitas (misal: AULA RACHMAT EFFENDY)
     * @param stok        Stok ketersediaan ruangan (default = 1)
     */
    public Ruangan(int idRuangan, String namaRuangan, int stok) {
        setIdRuangan(idRuangan);
        setNamaRuangan(namaRuangan);
        setStok(stok);
    }

    // Getter dan Setter dengan Validasi Ketat (Enkapsulasi)

    public int getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(int idRuangan) {
        if (idRuangan <= 0) {
            throw new IllegalArgumentException("ID Ruangan harus lebih besar dari 0.");
        }
        this.idRuangan = idRuangan;
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public void setNamaRuangan(String namaRuangan) {
        if (namaRuangan == null || namaRuangan.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama ruangan tidak boleh kosong.");
        }
        this.namaRuangan = namaRuangan;
    }

    @Override
    public int getStok() {
        return this.stok;
    }

    public void setStok(int stok) {
        if (stok < 0 || stok > 1) {
            throw new IllegalArgumentException("Stok ketersediaan ruangan harus bernilai 0 (dipinjam) atau 1 (tersedia).");
        }
        this.stok = stok;
    }

    // PILAR EXCEPTION HANDLING: Validasi pada operasi pengembalian/penambahan ketersediaan ruangan
    @Override
    public void tambahStok(int jumlah) {
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah penambahan stok harus lebih besar dari 0.");
        }
        this.stok = 1; // Ruangan kembali tersedia
    }

    // PILAR EXCEPTION HANDLING: Validasi dan throwing exception jika ruangan sudah dipinjam
    @Override
    public boolean kurangiStok(int jumlah) {
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah pengurangan stok harus lebih besar dari 0.");
        }
        if (this.stok < jumlah) {
            throw new IllegalArgumentException("Ruangan '" + namaRuangan + "' sedang tidak tersedia (sedang dipinjam).");
        }
        this.stok -= jumlah; // Ruangan terisi/tidak tersedia lagi (stok menjadi 0)
        return true;
    }

    /**
     * Memeriksa ketersediaan ruangan secara eksplisit.
     *
     * @return true jika ruangan siap dipinjam, false jika sedang terisi
     */
    public boolean isTersedia() {
        return this.stok > 0;
    }

    /**
     * IMPLEMENTASI INTERFACE: Merealisasikan kontrak dari LogistikAset
     */
    @Override
    public void perbaruiStatus() {
        System.out.println("[LOGISTIK INFO] Status Ruangan '" + namaRuangan + "' diperbarui. Ketersediaan: " + (isTersedia() ? "Tersedia" : "Dipinjam"));
    }
}
