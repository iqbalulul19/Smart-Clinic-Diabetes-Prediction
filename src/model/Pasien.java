package model;

public class Pasien {

    private int idPasien;
    private String nama;
    private int umur;
    private String gender;
    private String alamat;
    private String noHP;
    private double tekananDarah;
    private double gulaDarah;

    // CONSTRUCTOR KOSONG
    public Pasien() {
    }

    // CONSTRUCTOR UTAMA
    public Pasien(int idPasien, String nama, int umur, String gender, String alamat, String noHP, double tekananDarah, double gulaDarah) {
        this.idPasien = idPasien;
        this.nama = nama;
        this.umur = umur;
        this.gender = gender;
        this.alamat = alamat;
        this.noHP = noHP;
        this.tekananDarah = tekananDarah;
        this.gulaDarah = gulaDarah;
    }

    // GETTER & SETTER
    public int getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(int idPasien) {
        this.idPasien = idPasien;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    public double getTekananDarah() {
        return tekananDarah;
    }

    public void setTekananDarah(double tekananDarah) {
        this.tekananDarah = tekananDarah;
    }

    public double getGulaDarah() {
        return gulaDarah;
    }

    public void setGulaDarah(double gulaDarah) {
        this.gulaDarah = gulaDarah;
    }

    // Ini wajib ada agar di ComboBox halaman Prediksi yang muncul adalah namanya (bukan kode memori)
    @Override
    public String toString() {
        return nama;
    }
}