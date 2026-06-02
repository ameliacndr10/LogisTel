package com.logistel.model;

/**
 * Kelas abstrak Pengguna sebagai superclass untuk semua jenis pengguna
 * dalam sistem informasi LogisTel.
 */
public abstract class Pengguna {
    protected String idPengguna;
    protected String nama;
    protected String username;
    protected String password;

    /**
     * Constructor untuk menginisialisasi atribut dasar Pengguna.
     *
     * @param idPengguna ID unik pengguna (bisa NIM/NIP/dll.)
     * @param nama       Nama lengkap pengguna
     * @param username   Username untuk login
     * @param password   Password untuk login
     */
    public Pengguna(String idPengguna, String nama, String username, String password) {
        this.idPengguna = idPengguna;
        this.nama = nama;
        this.username = username;
        this.password = password;
    }

    // Getter dan Setter dengan enkapsulasi yang baik

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method abstrak untuk mendapatkan peran (role) dari pengguna.
     * Harus diimplementasikan oleh setiap subclass (Admin dan User).
     *
     * @return String yang merepresentasikan role pengguna (misal: "Admin", "User")
     */
    public abstract String getRole();
}
