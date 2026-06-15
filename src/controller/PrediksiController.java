package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import service.PasienService;
import model.Pasien;

public class PrediksiController {

    @FXML private ComboBox<Pasien> cbPasien;
    @FXML private TextField txtPregnancies;
    @FXML private TextField txtGlucose;
    @FXML private TextField txtBloodPressure;
    @FXML private TextField txtSkinThickness;
    @FXML private TextField txtInsulin;
    @FXML private TextField txtBMI;
    @FXML private TextField txtPedigree;
    @FXML private TextField txtAge;
    @FXML private Label lblHasil;

    private PasienService pasienService = new PasienService();

    @FXML
    public void initialize() {
        loadPasien();
        
        // Menampilkan Umur, Gula Darah, dan Tekanan Darah otomatis saat pasien dipilih
        cbPasien.setOnAction(e -> {
            Pasien p = cbPasien.getValue();
            if (p != null) {
                txtAge.setText(String.valueOf(p.getUmur()));
                txtGlucose.setText(String.valueOf(p.getGulaDarah()));
                txtBloodPressure.setText(String.valueOf(p.getTekananDarah()));
            }
        });
    }

    private void loadPasien() {
        cbPasien.getItems().addAll(pasienService.getAll());
    }

    @FXML
    private void handlePrediksi() {
        try {
            double glucose = Double.parseDouble(txtGlucose.getText());
            double bmi = Double.parseDouble(txtBMI.getText());
            int age = Integer.parseInt(txtAge.getText());

            // Logika Prediksi Sederhana (Rule-Based)
            if(glucose > 140 && bmi > 30 && age > 40) {
                lblHasil.setText("RISIKO DIABETES TINGGI");
                lblHasil.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 16px;");
            } else {
                lblHasil.setText("RISIKO DIABETES RENDAH");
                lblHasil.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 16px;");
            }
        } catch (NumberFormatException ex) {
            lblHasil.setText("Harap isi field Glucose, BMI, dan Age dengan angka!");
            lblHasil.setStyle("-fx-text-fill: orange; -fx-font-weight: bold;");
        }
    }
}