package com.logistel.model;

/**
 * PILAR ENKAPSULASI: Kelas abstrak Pengguna sebagai superclass untuk semua jenis pengguna
 * dalam sistem informasi LogisTel. Menggunakan access modifier private untuk data hiding.
 */
public abstract class Pengguna {
    // Data Hiding (Enkapsulasi)
    private String idPengguna;
    private String nama;
    private String username;
    private String password;
    private String nomorHp;
    private boolean statusAktif;

    /**
     * PILAR CONSTRUCTOR OVERLOADING: Constructor 1 - Default Constructor
     */
    public Pengguna() {
        this.statusAktif = true; // Default aktif
    }

    /**
     * PILAR CONSTRUCTOR OVERLOADING: Constructor 2 - Menjaga kompatibilitas inisialisasi dasar.
     *
     * @param idPengguna ID unik pengguna (bisa NIM/NIP/dll.)
     * @param nama       Nama lengkap pengguna
     * @param username   Username untuk login
     * @param password   Password untuk login
     */
    public Pengguna(String idPengguna, String nama, String username, String password) {
        this();
        setIdPengguna(idPengguna);
        setNama(nama);
        setUsername(username);
        setPassword(password);
    }

    /**
     * PILAR CONSTRUCTOR OVERLOADING: Constructor 3 - Constructor dengan parameter lengkap.
     *
     * @param idPengguna  ID unik pengguna
     * @param nama        Nama lengkap pengguna
     * @param username    Username untuk login
     * @param password    Password untuk login
     * @param nomorHp     Nomor HP aktif
     * @param statusAktif Status aktif akun
     */
    public Pengguna(String idPengguna, String nama, String username, String password, String nomorHp, boolean statusAktif) {
        setIdPengguna(idPengguna);
        setNama(nama);
        setUsername(username);
        setPassword(password);
        setNomorHp(nomorHp);
        this.statusAktif = statusAktif;
    }

    // Getter dan Setter dengan validasi ketat (Enkapsulasi)

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        if (idPengguna == null || idPengguna.trim().isEmpty()) {
            throw new IllegalArgumentException("ID Pengguna tidak boleh kosong.");
        }
        this.idPengguna = idPengguna;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong.");
        }
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username tidak boleh kosong.");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password tidak boleh kosong.");
        }
        this.password = password;
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        if (nomorHp == null || nomorHp.trim().isEmpty()) {
            throw new IllegalArgumentException("Nomor HP tidak boleh kosong.");
        }
        this.nomorHp = nomorHp;
    }

    public boolean isStatusAktif() {
        return statusAktif;
    }

    public void setStatusAktif(boolean statusAktif) {
        this.statusAktif = statusAktif;
    }

    /**
     * Method abstrak untuk mendapatkan peran (role) dari pengguna.
     * Harus diimplementasikan oleh setiap subclass (Admin dan User).
     *
     * @return String yang merepresentasikan role pengguna (misal: "Admin", "User")
     */
    public abstract String getRole();
}
