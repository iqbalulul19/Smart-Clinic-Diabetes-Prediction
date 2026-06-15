package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Pasien;
import service.PasienService;
import util.SceneUtil;

public class DashboardController {
    @FXML private VBox sidebar;
    @FXML private Label logoTitle;
    @FXML private Button btnDashboard, btnPasien, btnDokter, btnPetugas, btnObat;
    
    private boolean collapsed = false;
    
    @FXML private Label lblMaster, lblTransaksi, lblLaporan;
    @FXML private Button btnPendaftaran, btnPemeriksaan, btnRekam, btnPrediksi;
    
    @FXML private Label lblTotalPasien; 
    
    // Bind objek TableView dari dashboard.fxml
    @FXML private TableView<Pasien> tableDashboard; 
    
    private PasienService pasienService = new PasienService();

    @FXML
    public void initialize() {
        // Panggil refreshDashboard saat form pertama kali dimuat
        refreshDashboard();
    }

    /**
     * Method khusus untuk menyegarkan data di Dashboard
     * Berguna untuk dipanggil ulang setelah menutup form lain
     */
    private void refreshDashboard() {
        // 1. Sinkronisasi Card Total Pasien
        if (lblTotalPasien != null) {
            // Catatan: Jika data ribuan, lebih baik gunakan metode COUNT(*) di DAO
            int total = pasienService.getAll().size();
            lblTotalPasien.setText(String.valueOf(total));
        }

        // 2. Mengisi Data ke Tabel Dashboard Utama
        if (tableDashboard != null) {
            // Memetakan kolom berdasarkan urutan indeks di FXML agar tidak perlu ubah file FXML
            tableDashboard.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("idPasien"));
            tableDashboard.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nama"));
            tableDashboard.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("umur"));
            tableDashboard.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("gender"));
            tableDashboard.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("alamat"));

            // Masukkan data dari database ke tabel dashboard
            tableDashboard.setItems(pasienService.getAll());
        }
    }

    @FXML
    private void toggleSidebar(){
        if(!collapsed){
            sidebar.setPrefWidth(80);
            logoTitle.setVisible(false);
            if(lblMaster != null) lblMaster.setVisible(false);
            if(lblTransaksi != null) lblTransaksi.setVisible(false);
            if(lblLaporan != null) lblLaporan.setVisible(false);
            
            btnDashboard.setText("🏠");
            btnPasien.setText("👨");
            if(btnDokter != null) btnDokter.setText("🩺");
            if(btnPetugas != null) btnPetugas.setText("👩");
            if(btnObat != null) btnObat.setText("💊");
            if(btnPendaftaran != null) btnPendaftaran.setText("📝");
            if(btnPemeriksaan != null) btnPemeriksaan.setText("🩻");
            if(btnRekam != null) btnRekam.setText("📋");
            btnPrediksi.setText("🧠");
            
            collapsed = true;
        }else{
            sidebar.setPrefWidth(240);
            logoTitle.setVisible(true);
            if(lblMaster != null) lblMaster.setVisible(true);
            if(lblTransaksi != null) lblTransaksi.setVisible(true);
            if(lblLaporan != null) lblLaporan.setVisible(true);
            
            logoTitle.setText("PREDIKSI");
            btnDashboard.setText("🏠 Dashboard");
            btnPasien.setText("👨‍⚕ Pasien");
            if(btnDokter != null) btnDokter.setText("🩺 Dokter");
            if(btnPetugas != null) btnPetugas.setText("👩‍💼 Petugas");
            if(btnObat != null) btnObat.setText("💊 Obat");
            if(btnPendaftaran != null) btnPendaftaran.setText("📝 Pendaftaran");
            if(btnPemeriksaan != null) btnPemeriksaan.setText("🩻 Pemeriksaan");
            if(btnRekam != null) btnRekam.setText("📋 Rekam Medis");
            btnPrediksi.setText("🧠 Prediksi Diabetes");

            collapsed = false;
        }
    }

    @FXML
    private void openPasien() {
        SceneUtil.openMaximizedWindow("/view/pasien.fxml","Data Pasien");
        // Segarkan data dashboard setelah window Data Pasien ditutup
        refreshDashboard();
    }
    
    @FXML
    private void openPrediksi() {
        SceneUtil.openMaximizedWindow("/view/prediksi.fxml","Prediksi Diabetes");
        // Segarkan data dashboard setelah window Prediksi ditutup
        refreshDashboard();
    }
}