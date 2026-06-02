package com.logistel.model;

/**
 * Subclass User merepresentasikan pengguna biasa (mahasiswa/ormawa)
 * yang memiliki profil lengkap agar tidak perlu mengisi data berulang kali saat melakukan peminjaman.
 */
public class User extends Pengguna {
    private String nim;
    private String namaOrmawa;
    private String noHandphone;
    private String emailSSO;

    /**
     * Constructor untuk membuat objek User baru.
     *
     * @param idPengguna  ID unik pengguna
     * @param nama        Nama lengkap pengguna
     * @param username    Username untuk login
     * @param password    Password untuk login
     * @param nim         Nomor Induk Mahasiswa
     * @param namaOrmawa  Nama Organisasi Mahasiswa yang diwakili
     * @param noHandphone Nomor handphone aktif
     * @param emailSSO    Email Single Sign-On (SSO) institusi
     */
    public User(String idPengguna, String nama, String username, String password, 
                String nim, String namaOrmawa, String noHandphone, String emailSSO) {
        super(idPengguna, nama, username, password);
        this.nim = nim;
        this.namaOrmawa = namaOrmawa;
        this.noHandphone = noHandphone;
        this.emailSSO = emailSSO;
    }

    // Getter dan Setter untuk atribut spesifik User

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNamaOrmawa() {
        return namaOrmawa;
    }

    public void setNamaOrmawa(String namaOrmawa) {
        this.namaOrmawa = namaOrmawa;
    }

    public String getNoHandphone() {
        return noHandphone;
    }

    public void setNoHandphone(String noHandphone) {
        this.noHandphone = noHandphone;
    }

    public String getEmailSSO() {
        return emailSSO;
    }

    public void setEmailSSO(String emailSSO) {
        this.emailSSO = emailSSO;
    }

    /**
     * Mengembalikan peran pengguna ini.
     *
     * @return String "User"
     */
    @Override
    public String getRole() {
        return "User";
    }

    /**
     * Method khusus untuk menampilkan atau mendapatkan profil lengkap User secara terformat.
     *
     * @return String representasi detail dari profil lengkap pengguna
     */
    public String getProfilLengkap() {
        return "====================================\n" +
               "          PROFIL LENGKAP USER       \n" +
               "====================================\n" +
               "ID Pengguna  : " + idPengguna + "\n" +
               "Nama Lengkap : " + nama + "\n" +
               "Username     : " + username + "\n" +
               "Role         : " + getRole() + "\n" +
               "NIM          : " + nim + "\n" +
               "Ormawa       : " + (namaOrmawa != null && !namaOrmawa.trim().isEmpty() ? namaOrmawa : "-") + "\n" +
               "No. HP       : " + noHandphone + "\n" +
               "Email SSO    : " + emailSSO + "\n" +
               "====================================";
    }
}
