package database;
import java.sql.*;
public class ConnexionDB {
    private static final String url = "jdbc:postgresql://localhost:5432/gestionhospitalisation"; // Modifiez le nom de la base ici
    private static final String user = "postgres";
    private static final String password = "12AZQSWX";

    public Connection connection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Échec de la connexion à la base de données : " + e.getMessage());
        }
        return conn;
    }
}