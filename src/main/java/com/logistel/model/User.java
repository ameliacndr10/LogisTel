package com.logistel.model;

/**
 * PILAR PEWARISAN (INHERITANCE): Subclass User mewarisi (extends) superclass Pengguna
 * untuk merepresentasikan pengguna biasa (mahasiswa/ormawa).
 */
public class User extends Pengguna {
    // PILAR ENKAPSULASI: Data hiding dengan private
    private String nim;
    private String namaOrmawa;
    private String emailSSO;

    /**
     * PILAR CONSTRUCTOR OVERLOADING: Constructor 1 - Default Constructor
     */
    public User() {
        super();
    }

    /**
     * PILAR CONSTRUCTOR OVERLOADING: Constructor 2 - Menjaga kompatibilitas inisialisasi dasar.
     * Menggunakan super() untuk memicu constructor dari superclass.
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
        setNomorHp(noHandphone);
        setNim(nim);
        setNamaOrmawa(namaOrmawa);
        setEmailSSO(emailSSO);
    }

    /**
     * PILAR CONSTRUCTOR OVERLOADING: Constructor 3 - Constructor lengkap beserta atribut superclass.
     *
     * @param idPengguna  ID unik pengguna
     * @param nama        Nama lengkap pengguna
     * @param username    Username untuk login
     * @param password    Password untuk login
     * @param nomorHp     Nomor HP aktif
     * @param statusAktif Status aktif akun
     * @param nim         Nomor Induk Mahasiswa
     * @param namaOrmawa  Nama Organisasi Mahasiswa yang diwakili
     * @param emailSSO    Email Single Sign-On (SSO) institusi
     */
    public User(String idPengguna, String nama, String username, String password, String nomorHp, boolean statusAktif,
                String nim, String namaOrmawa, String emailSSO) {
        super(idPengguna, nama, username, password, nomorHp, statusAktif);
        setNim(nim);
        setNamaOrmawa(namaOrmawa);
        setEmailSSO(emailSSO);
    }

    // Getter dan Setter dengan validasi (Enkapsulasi)

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        if (nim == null || nim.trim().isEmpty()) {
            throw new IllegalArgumentException("NIM tidak boleh kosong.");
        }
        this.nim = nim;
    }

    public String getNamaOrmawa() {
        return namaOrmawa;
    }

    public void setNamaOrmawa(String namaOrmawa) {
        this.namaOrmawa = namaOrmawa;
    }

    // Delegasi noHandphone ke nomorHp superclass
    public String getNoHandphone() {
        return getNomorHp();
    }

    public void setNoHandphone(String noHandphone) {
        setNomorHp(noHandphone);
    }

    public String getEmailSSO() {
        return emailSSO;
    }

    public void setEmailSSO(String emailSSO) {
        if (emailSSO == null || emailSSO.trim().isEmpty()) {
            throw new IllegalArgumentException("Email SSO tidak boleh kosong.");
        }
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
     * PILAR POLIMORFISME: Melakukan Overriding method toString() dari Object class
     * untuk menampilkan representasi data objek User secara spesifik.
     */
    @Override
    public String toString() {
        return "User [ID: " + getIdPengguna() + 
               ", Nama: " + getNama() + 
               ", Username: " + getUsername() + 
               ", NIM: " + nim + 
               ", Ormawa: " + namaOrmawa + 
               ", No HP: " + getNoHandphone() + 
               ", Email: " + emailSSO + 
               ", Status: " + (isStatusAktif() ? "Aktif" : "Non-Aktif") + "]";
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
               "ID Pengguna  : " + getIdPengguna() + "\n" +
               "Nama Lengkap : " + getNama() + "\n" +
               "Username     : " + getUsername() + "\n" +
               "Role         : " + getRole() + "\n" +
               "NIM          : " + nim + "\n" +
               "Ormawa       : " + (namaOrmawa != null && !namaOrmawa.trim().isEmpty() ? namaOrmawa : "-") + "\n" +
               "No. HP       : " + getNoHandphone() + "\n" +
               "Email SSO    : " + emailSSO + "\n" +
               "====================================";
    }
}
