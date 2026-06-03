package com.logistel;

import com.logistel.model.Barang;
import com.logistel.model.Pengguna;
import java.util.ArrayList;

/**
 * PILAR ARSITEKTUR MVC (CONTROLLER):
 * Kelas Application bertindak sebagai Controller utama dalam arsitektur MVC (Model-View-Controller).
 * Kelas ini bertugas mengolah logika bisnis dan menjembatani data Model ke View.
 */
public class Application {
    
    /**
     * PILAR COLLECTION (Heterogeneous Collection):
     * Menggunakan ArrayList untuk menampung subclass dari tipe superclass Pengguna (Admin dan User),
     * serta mengelola koleksi Barang secara in-memory.
     */
    private ArrayList<Pengguna> daftarPengguna;
    private ArrayList<Barang> daftarBarang;

    /**
     * Constructor untuk inisialisasi collection kosong.
     */
    public Application() {
        this.daftarPengguna = new ArrayList<>();
        this.daftarBarang = new ArrayList<>();
    }

    /**
     * Menambahkan pengguna baru ke collection.
     * 
     * @param pengguna Objek Pengguna (bisa berupa Admin atau User)
     */
    public void tambahPengguna(Pengguna pengguna) {
        if (pengguna == null) {
            throw new IllegalArgumentException("Pengguna tidak boleh null.");
        }
        this.daftarPengguna.add(pengguna);
        System.out.println("[APPLICATION INFO] Berhasil menambahkan pengguna ke koleksi: " + pengguna.getNama() + " (" + pengguna.getRole() + ")");
    }

    /**
     * Menambahkan barang baru ke collection.
     * 
     * @param barang Objek Barang
     */
    public void tambahBarang(Barang barang) {
        if (barang == null) {
            throw new IllegalArgumentException("Barang tidak boleh null.");
        }
        this.daftarBarang.add(barang);
        System.out.println("[APPLICATION INFO] Berhasil menambahkan barang ke koleksi: " + barang.getNamaBarang());
    }

    public ArrayList<Pengguna> getDaftarPengguna() {
        return daftarPengguna;
    }

    public ArrayList<Barang> getDaftarBarang() {
        return daftarBarang;
    }

    /**
     * PILAR COLLECTION & STREAM FILTERING (Lambda Expression):
     * Melakukan pencarian data user berdasarkan username secara case-insensitive menggunakan stream.
     *
     * @param username Username yang dicari
     * @return Objek Pengguna yang cocok
     * @throws IllegalArgumentException jika input kosong atau akun tidak ditemukan (Pilar Exception Handling)
     */
    public Pengguna cariPenggunaBerdasarkanUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username pencarian tidak boleh kosong.");
        }

        // Pencarian dengan stream, filter, dan lambda expression
        Pengguna hasil = daftarPengguna.stream()
                .filter(p -> p.getUsername().equalsIgnoreCase(username.trim()))
                .findFirst()
                .orElse(null);

        if (hasil == null) {
            throw new IllegalArgumentException("Pencarian Gagal: Akun dengan username '" + username + "' tidak ditemukan.");
        }

        return hasil;
    }

    /**
     * PILAR COLLECTION & STREAM FILTERING (Lambda Expression):
     * Melakukan pencarian data barang berdasarkan nama barang secara case-insensitive menggunakan stream.
     *
     * @param namaBarang Nama barang yang dicari
     * @return Objek Barang yang cocok
     * @throws IllegalArgumentException jika input kosong atau barang tidak ditemukan (Pilar Exception Handling)
     */
    public Barang cariBarangBerdasarkanNama(String namaBarang) {
        if (namaBarang == null || namaBarang.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama barang pencarian tidak boleh kosong.");
        }

        // Pencarian dengan stream, filter, dan lambda expression
        Barang hasil = daftarBarang.stream()
                .filter(b -> b.getNamaBarang().equalsIgnoreCase(namaBarang.trim()))
                .findFirst()
                .orElse(null);

        if (hasil == null) {
            throw new IllegalArgumentException("Pencarian Gagal: Barang dengan nama '" + namaBarang + "' tidak ditemukan.");
        }

        return hasil;
    }
}
