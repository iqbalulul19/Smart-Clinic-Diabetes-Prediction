package service;

import dao.PasienDAO;
import javafx.collections.ObservableList;
import model.Pasien;

public class PasienService {

    // Memanggil DAO (Data Access Object) yang berhubungan langsung ke Database
    private PasienDAO pasienDAO = new PasienDAO();

    // Fungsi untuk mengambil semua data pasien
    public ObservableList<Pasien> getAll() {
        return pasienDAO.getAllPasien();
    }

    // Fungsi untuk simpan (Tambah Baru atau Update)
    public void simpan(Pasien p, boolean modeEdit) {
        if (modeEdit) {
            pasienDAO.updatePasien(p);
        } else {
            pasienDAO.insertPasien(p);
        }
    }

    // Fungsi untuk hapus data
    public void delete(int id) {
        pasienDAO.deletePasien(id);
    }

    // Fungsi untuk mencari pasien
    public ObservableList<Pasien> search(String keyword) {
        return pasienDAO.searchPasien(keyword);
    }
}