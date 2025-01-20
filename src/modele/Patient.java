package modele;
import database.ConnexionDB;
import java.sql.*;
public class Patient {
    private Integer IDPatient;
    private String NCIN;
    private String NomPrenomPatient;
    private String AdressePatient;
    private String TelPatient;
    private boolean MutuellePatient;
    private String TypeMutuellePatient;
    final Connection conn= new ConnexionDB().connection();
    // Constructeur par défaut
    public Patient(int i, String text, String txtAdresseText, String txtTelText, String string, String s) {}
    // Constructeur paramétrique
    public Patient() {
        this.IDPatient = IDPatient;
        this.NCIN = NCIN;
        this.NomPrenomPatient = NomPrenomPatient;
        this.AdressePatient = AdressePatient;
        this.TelPatient = TelPatient;
        this.MutuellePatient = MutuellePatient;
        this.TypeMutuellePatient = TypeMutuellePatient;
    }

    // Getters et Setters
    public Integer getIDPatient() {
        return IDPatient;
    }

    public void setIDPatient(Integer IDPatient) {
        this.IDPatient = IDPatient;
    }

    public String getNCIN() {
        return NCIN;
    }

    public void setNCIN(String NCIN) {
        this.NCIN = NCIN;
    }

    public String getNomPrenomPatient() {
        return NomPrenomPatient;
    }

    public void setNomPrenomPatient(String NomPrenomPatient) {
        this.NomPrenomPatient = NomPrenomPatient;
    }

    public String getAdressePatient() {
        return AdressePatient;
    }

    public void setAdressePatient(String AdressePatient) {
        this.AdressePatient = AdressePatient;
    }

    public String getTelPatient() {
        return TelPatient;
    }

    public void setTelPatient(String TelPatient) {
        this.TelPatient = TelPatient;
    }

    public boolean isMutuellePatient() {
        return MutuellePatient;
    }

    public void setMutuellePatient(boolean MutuellePatient) {
        this.MutuellePatient = MutuellePatient;
    }

    public String getTypeMutuellePatient() {
        return TypeMutuellePatient;
    }

    public void setTypeMutuellePatient(String TypeMutuellePatient) {
        this.TypeMutuellePatient = TypeMutuellePatient;
    }

    // Méthodes de mise à jour
    public void chercherPatient(Integer id) {
        String query = "SELECT * FROM Patient WHERE IDPatient = ?";
        try (conn){
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.IDPatient = rs.getInt("IDPatient");
                this.NCIN = rs.getString("NCIN");
                this.NomPrenomPatient = rs.getString("NomPrenomPatient");
                this.AdressePatient = rs.getString("AdressePatient");
                this.TelPatient = rs.getString("TelPatient");
                this.MutuellePatient = rs.getBoolean("MutuellePatient");
                this.TypeMutuellePatient = rs.getString("TypeMutuellePatient");
                System.out.println("Patient trouvé: " + this.NomPrenomPatient);
            } else {
                System.out.println("Aucun patient trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ajouterPatient() {
        String query = "INSERT INTO Patient (NCIN, NomPrenomPatient, AdressePatient, TelPatient, MutuellePatient, TypeMutuellePatient) VALUES (?, ?, ?, ?, ?, ?)";
        try (conn){
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, this.NCIN);
            pstmt.setString(2, this.NomPrenomPatient);
            pstmt.setString(3, this.AdressePatient);
            pstmt.setString(4, this.TelPatient);
            pstmt.setBoolean(5, this.MutuellePatient);
            pstmt.setString(6, this.TypeMutuellePatient);
            pstmt.executeUpdate();
            System.out.println("Patient ajouté avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void modifierPatient() {
        String query = "UPDATE Patient SET NCIN = ?, NomPrenomPatient = ?, AdressePatient = ?, TelPatient = ?, MutuellePatient = ?, TypeMutuellePatient = ? WHERE IDPatient = ?";
        try (conn){
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, this.NCIN);
            pstmt.setString(2, this.NomPrenomPatient);
            pstmt.setString(3, this.AdressePatient);
            pstmt.setString(4, this.TelPatient);
            pstmt.setBoolean(5, this.MutuellePatient);
            pstmt.setString(6, this.TypeMutuellePatient);
            pstmt.setInt(7, this.IDPatient);
            pstmt.executeUpdate();
            System.out.println("Patient modifié avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerPatient(Integer id) {
        String query = "DELETE FROM Patient WHERE IDPatient = ?";
        try (conn){
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Patient supprimé avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
