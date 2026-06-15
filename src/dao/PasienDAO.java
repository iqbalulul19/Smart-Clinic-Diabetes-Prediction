package dao;
import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Pasien;
import java.sql.*;
public class PasienDAO {
    Connection conn = DBConnection.connect();
    // LOAD DATA
    public ObservableList<Pasien> getAllPasien(){
        ObservableList<Pasien> list =FXCollections.observableArrayList();
        try{
            Statement st = conn.createStatement();
            ResultSet rs =st.executeQuery("SELECT * FROM pasien");
            while(rs.next()){
                list.add(new Pasien(
                        rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getInt("umur"),
                        rs.getString("gender"),
                        rs.getString("alamat"),
                        rs.getString("noHP"),
                        rs.getDouble("tekananDarah"),
                        rs.getDouble("gulaDarah")
                        )
                );
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    // INSERT
    public void insertPasien(Pasien p){
        try{
    String sql ="INSERT INTO pasien(nama,umur,gender,alamat,noHP,tekananDarah,gulaDarah) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps =conn.prepareStatement(sql);
            ps.setString(1, p.getNama());
            ps.setInt(2, p.getUmur());
            ps.setString(3, p.getGender());
            ps.setString(4, p.getAlamat());
            ps.setString(5, p.getNoHP());
            ps.setDouble(6, p.getTekananDarah());
            ps.setDouble(7, p.getGulaDarah());
            ps.executeUpdate();
        }catch(Exception e){

            e.printStackTrace();
        }
    }

    // UPDATE
    public void updatePasien(Pasien p){

        try{

            String sql =
                    "UPDATE pasien SET nama=?,umur=?,gender=?,alamat=?,noHP=?,tekananDarah=?,gulaDarah=? WHERE id=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, p.getNama());
            ps.setInt(2, p.getUmur());
            ps.setString(3, p.getGender());
            ps.setString(4, p.getAlamat());
            ps.setString(5, p.getNoHP());
            ps.setDouble(6, p.getTekananDarah());
            ps.setDouble(7, p.getGulaDarah());
            ps.setInt(8, p.getIdPasien());

            ps.executeUpdate();

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    // DELETE
    public void deletePasien(int id){

        try{

            String sql =
                    "DELETE FROM pasien WHERE id=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    // SEARCH
    public ObservableList<Pasien> searchPasien(String keyword){

        ObservableList<Pasien> list =
                FXCollections.observableArrayList();

        try{

            String sql =
                    "SELECT * FROM pasien WHERE nama LIKE ?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                list.add(
                        new Pasien(
                                rs.getInt("id"),
                                rs.getString("nama"),
                                rs.getInt("umur"),
                                rs.getString("gender"),
                                rs.getString("alamat"),
                                rs.getString("noHP"),
                                rs.getDouble("tekananDarah"),
                                rs.getDouble("gulaDarah")
                        )
                );
            }

        }catch(Exception e){

            e.printStackTrace();
        }

        return list;
    }

    public int getTotalPasien() {
    int total = 0;
    String sql = "SELECT COUNT(*) AS total FROM pasien";
    
    try (Connection conn = database.DBConnection.connect();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
         
        if (rs.next()) {
            total = rs.getInt("total");
        }
        
    } catch (Exception e) {
        System.out.println("Gagal menghitung total pasien: " + e.getMessage());
        e.printStackTrace();
    }
    
    return total;
}
}