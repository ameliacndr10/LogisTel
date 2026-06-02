package com.logistel.model;

/**
 * Subclass Admin merepresentasikan pengguna dengan hak akses administratif dalam sistem LogisTel.
 */
public class Admin extends Pengguna {
    private String noPegawai;

    /**
     * Constructor untuk membuat objek Admin baru.
     *
     * @param idPengguna ID unik pengguna
     * @param nama       Nama lengkap admin
     * @param username   Username untuk login
     * @param password   Password untuk login
     * @param noPegawai  Nomor pegawai unik admin
     */
    public Admin(String idPengguna, String nama, String username, String password, String noPegawai) {
        super(idPengguna, nama, username, password);
        this.noPegawai = noPegawai;
    }

    // Getter dan Setter untuk atribut spesifik Admin

    public String getNoPegawai() {
        return noPegawai;
    }

    public void setNoPegawai(String noPegawai) {
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
}
