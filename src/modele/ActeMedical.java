package modele;
import database.ConnexionDB;

import java.sql.*;
import java.util.Date;
public class ActeMedical {
    private Integer IDDossierMedical;
    private Integer IDActeMedical;
    private Integer IDPatient;
    private String TypeActeMedical;
    private Date DateActeMedical;
    private String MedecinActeMedical;
    final Connection conn= new ConnexionDB().connection();
    // Constructeur par défaut
    public ActeMedical() {}
    // Constructeur paramétrique
    public ActeMedical(Integer IDDossierMedical, Integer IDActeMedical, Integer IDPatient, String TypeActeMedical, Date DateActeMedical, String MedecinActeMedical) {
        this.IDDossierMedical = IDDossierMedical;
        this.IDActeMedical = IDActeMedical;
        this.IDPatient = IDPatient;
        this.TypeActeMedical = TypeActeMedical;
        this.DateActeMedical = DateActeMedical;
        this.MedecinActeMedical = MedecinActeMedical;
    }

    // Getters et Setters
    public Integer getIDDossierMedical() {
        return IDDossierMedical;
    }

    public void setIDDossierMedical(Integer IDDossierMedical) {
        this.IDDossierMedical = IDDossierMedical;
    }

    public Integer getIDActeMedical() {
        return IDActeMedical;
    }

    public void setIDActeMedical(Integer IDActeMedical) {
        this.IDActeMedical = IDActeMedical;
    }

    public Integer getIDPatient() {
        return IDPatient;
    }

    public void setIDPatient(Integer IDPatient) {
        this.IDPatient = IDPatient;
    }

    public String getTypeActeMedical() {
        return TypeActeMedical;
    }

    public void setTypeActeMedical(String TypeActeMedical) {
        this.TypeActeMedical = TypeActeMedical;
    }

    public Date getDateActeMedical() {
        return DateActeMedical;
    }

    public void setDateActeMedical(Date DateActeMedical) {
        this.DateActeMedical = DateActeMedical;
    }

    public String getMedecinActeMedical() {
        return MedecinActeMedical;
    }

    public void setMedecinActeMedical(String MedecinActeMedical) {
        this.MedecinActeMedical = MedecinActeMedical;
    }

    // Méthodes de mise à jour
    public void chercherActeMedical(Integer id) {
        String query = "SELECT * FROM ActeMedical WHERE IDActeMedical = ?";
        try (conn){
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.IDDossierMedical = rs.getInt("IDDossierMedical");
                this.IDActeMedical = rs.getInt("IDActeMedical");
                this.IDPatient = rs.getInt("IDPatient");
                this.TypeActeMedical = rs.getString("TypeActeMedical");
                this.DateActeMedical = rs.getDate("DateActeMedical");
                this.MedecinActeMedical = rs.getString("MedecinActeMedical");
                System.out.println("Acte médical trouvé: " + this.IDActeMedical);
            } else {
                System.out.println("Aucun acte médical trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ajouterActeMedical() {
        String query = "INSERT INTO ActeMedical (IDDossierMedical, IDPatient, TypeActeMedical, DateActeMedical, MedecinActeMedical) VALUES (?, ?, ?, ?, ?)";
        try (conn){
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, this.IDDossierMedical);
            pstmt.setInt(2, this.IDPatient);
            pstmt.setString(3, this.TypeActeMedical);
            pstmt.setDate(4, new java.sql.Date(this.DateActeMedical.getTime()));
            pstmt.setString(5, this.MedecinActeMedical);
            pstmt.executeUpdate();
            System.out.println("Acte médical ajouté avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void modifierActeMedical() {
        String query = "UPDATE ActeMedical SET IDDossierMedical = ?, IDPatient = ?, TypeActeMedical = ?, DateActeMedical = ?, MedecinActeMedical = ? WHERE IDActeMedical = ?";
        try (conn){
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, this.IDDossierMedical);
            pstmt.setInt(2, this.IDPatient);
            pstmt.setString(3, this.TypeActeMedical);
            pstmt.setDate(4, new java.sql.Date(this.DateActeMedical.getTime()));
            pstmt.setString(5, this.MedecinActeMedical);
            pstmt.setInt(6, this.IDActeMedical);
            pstmt.executeUpdate();
            System.out.println("Acte médical modifié avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void supprimerActeMedical(Integer id) {
        String query = "DELETE FROM ActeMedical WHERE IDActeMedical = ?";
        try (conn){
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Acte médical supprimé avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
