-- Script DDL untuk database "logistel_db"
-- Mengimplementasikan relational inheritance (1-to-1 mapping) untuk class Pengguna, Admin, dan User.

CREATE DATABASE IF NOT EXISTS logistel_db;
USE logistel_db;

-- 1. Tabel "pengguna" (Base Table)
CREATE TABLE IF NOT EXISTS pengguna (
    id_pengguna INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(50) NOT NULL,
    username VARCHAR(25) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, -- Menggunakan 255 karakter untuk mendukung penyimpanan hash password
    role ENUM('Admin', 'User') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 2. Tabel "admin" (Sub-table / Child Table)
CREATE TABLE IF NOT EXISTS admin (
    id_admin INT PRIMARY KEY,
    no_pegawai VARCHAR(20) NOT NULL UNIQUE,
    CONSTRAINT fk_admin_pengguna 
        FOREIGN KEY (id_admin) 
        REFERENCES pengguna(id_pengguna) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. Tabel "user" (Sub-table / Child Table)
CREATE TABLE IF NOT EXISTS user (
    id_user INT PRIMARY KEY,
    nim VARCHAR(15) NOT NULL UNIQUE,
    nama_ormawa VARCHAR(50) NOT NULL,
    no_handphone VARCHAR(15) NOT NULL,
    email_sso VARCHAR(50) NOT NULL UNIQUE,
    CONSTRAINT fk_user_pengguna 
        FOREIGN KEY (id_user) 
        REFERENCES pengguna(id_pengguna) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 4. Tabel "barang"
CREATE TABLE IF NOT EXISTS barang (
    id_barang INT AUTO_INCREMENT PRIMARY KEY,
    nama_barang VARCHAR(50) NOT NULL UNIQUE,
    stok INT NOT NULL DEFAULT 10
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 5. Tabel "ruangan"
CREATE TABLE IF NOT EXISTS ruangan (
    id_ruangan INT AUTO_INCREMENT PRIMARY KEY,
    nama_ruangan VARCHAR(50) NOT NULL UNIQUE,
    stok INT NOT NULL DEFAULT 1 -- 1 untuk tersedia, 0 untuk terisi/kapasitas penuh
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 6. Tabel "peminjaman" (Header Transaksi)
CREATE TABLE IF NOT EXISTS peminjaman (
    id_peminjaman INT AUTO_INCREMENT PRIMARY KEY,
    id_user INT NOT NULL,
    tanggal_mulai DATE NOT NULL,
    tanggal_selesai DATE NOT NULL,
    status ENUM('PENDING', 'APPROVED', 'REJECTED', 'RETURNED') NOT NULL DEFAULT 'PENDING',
    barcode VARCHAR(100) UNIQUE,
    CONSTRAINT fk_peminjaman_user 
        FOREIGN KEY (id_user) 
        REFERENCES user(id_user) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 7. Tabel "detail_peminjaman_barang"
CREATE TABLE IF NOT EXISTS detail_peminjaman_barang (
    id_detail_peminjaman_barang INT AUTO_INCREMENT PRIMARY KEY,
    id_peminjaman INT NOT NULL,
    id_barang INT NOT NULL,
    jumlah INT NOT NULL,
    nama_kegiatan VARCHAR(100) NOT NULL,
    deskripsi TEXT,
    CONSTRAINT fk_detail_barang_peminjaman 
        FOREIGN KEY (id_peminjaman) 
        REFERENCES peminjaman(id_peminjaman) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE,
    CONSTRAINT fk_detail_barang_barang 
        FOREIGN KEY (id_barang) 
        REFERENCES barang(id_barang) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 8. Tabel "detail_peminjaman_ruangan"
CREATE TABLE IF NOT EXISTS detail_peminjaman_ruangan (
    id_detail_peminjaman_ruangan INT AUTO_INCREMENT PRIMARY KEY,
    id_peminjaman INT NOT NULL,
    id_ruangan INT NOT NULL,
    nama_kegiatan VARCHAR(100) NOT NULL,
    deskripsi TEXT,
    CONSTRAINT fk_detail_ruangan_peminjaman 
        FOREIGN KEY (id_peminjaman) 
        REFERENCES peminjaman(id_peminjaman) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE,
    CONSTRAINT fk_detail_ruangan_ruangan 
        FOREIGN KEY (id_ruangan) 
        REFERENCES ruangan(id_ruangan) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Seed data awal untuk barang
INSERT INTO barang (nama_barang, stok) VALUES
('KAMERA', 10),
('STAND MIC', 10),
('MIC WIRELESS', 10),
('BENDERA', 10),
('NAMPAN', 10),
('MEJA', 10),
('SOFA', 10)
ON DUPLICATE KEY UPDATE stok = VALUES(stok);

-- Seed data awal untuk ruangan
INSERT INTO ruangan (nama_ruangan, stok) VALUES
('DSP 301', 1), ('DSP 302', 1), ('DSP 303', 1), ('DSP 304', 1),
('DSP 305', 1), ('DSP 306', 1), ('DSP 307', 1), ('DSP 308', 1),
('REK 201', 1), ('REK 202', 1), ('REK 203', 1), ('REK 204', 1),
('REK 205', 1), ('REK 206', 1), ('REK 207', 1),
('REK 301', 1), ('REK 302', 1), ('REK 303', 1), ('REK 304', 1),
('REK 305', 1), ('REK 306', 1), ('REK 307', 1),
('AULA RACHMAT EFFENDY', 1),
('IOT 101', 1), ('IOT 102', 1), ('IOT 103', 1), ('IOT 104', 1), ('IOT 105', 1),
('TT 101', 1), ('TT 102', 1), ('TT 103', 1), ('TT 104', 1), ('TT 105', 1),
('DC 301', 1), ('DC 302', 1), ('DC 303', 1), ('DC 304', 1), ('DC 305', 1),
('Parkiran DSP', 1),
('GOR DI PANDJAITAN', 1)
ON DUPLICATE KEY UPDATE stok = VALUES(stok);

