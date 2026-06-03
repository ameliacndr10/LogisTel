package com.logistel.model;

/**
 * PILAR PEWARISAN (INHERITANCE): Subclass Admin mewarisi (extends) superclass Pengguna
 * untuk menampung hak akses administratif dalam sistem LogisTel.
 */
public class Admin extends Pengguna {
    // PILAR ENKAPSULASI: Data hiding dengan private
    private String noPegawai;

    /**
     * PILAR CONSTRUCTOR OVERLOADING: Constructor 1 - Default Constructor
     */
    public Admin() {
        super();
    }

    /**
     * PILAR CONSTRUCTOR OVERLOADING: Constructor 2 - Menjaga kompatibilitas inisialisasi dasar.
     * Menggunakan super() untuk memicu constructor dari superclass.
     *
     * @param idPengguna ID unik pengguna
     * @param nama       Nama lengkap admin
     * @param username   Username untuk login
     * @param password   Password untuk login
     * @param noPegawai  Nomor pegawai unik admin
     */
    public Admin(String idPengguna, String nama, String username, String password, String noPegawai) {
        super(idPengguna, nama, username, password);
        setNoPegawai(noPegawai);
    }

    /**
     * PILAR CONSTRUCTOR OVERLOADING: Constructor 3 - Constructor lengkap beserta atribut superclass.
     *
     * @param idPengguna  ID unik pengguna
     * @param nama        Nama lengkap admin
     * @param username    Username untuk login
     * @param password    Password untuk login
     * @param nomorHp     Nomor HP admin
     * @param statusAktif Status aktif admin
     * @param noPegawai   Nomor pegawai unik admin
     */
    public Admin(String idPengguna, String nama, String username, String password, String nomorHp, boolean statusAktif, String noPegawai) {
        super(idPengguna, nama, username, password, nomorHp, statusAktif);
        setNoPegawai(noPegawai);
    }

    // Getter dan Setter dengan validasi (Enkapsulasi)

    public String getNoPegawai() {
        return noPegawai;
    }

    public void setNoPegawai(String noPegawai) {
        if (noPegawai == null || noPegawai.trim().isEmpty()) {
            throw new IllegalArgumentException("Nomor pegawai tidak boleh kosong.");
        }
        this.noPegawai = noPegawai;
    }

    /**
     * Mengembalikan peran pengguna ini.
     *
     * @return String "Admin"
     */
    @Override
    public String getRole() {
        return "Admin";
    }

    /**
     * PILAR POLIMORFISME: Melakukan Overriding method toString() dari Object class
     * untuk menampilkan representasi data objek Admin secara spesifik.
     */
    @Override
    public String toString() {
        return "Admin [ID: " + getIdPengguna() + 
               ", Nama: " + getNama() + 
               ", Username: " + getUsername() + 
               ", No Pegawai: " + noPegawai + 
               ", Status: " + (isStatusAktif() ? "Aktif" : "Non-Aktif") + "]";
    }
}
