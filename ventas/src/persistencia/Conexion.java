package persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:sqlite:C:/Users/di3go/OneDrive/Escritorio/vale 2.0/ventas.db";
    private static Connection conn = null;

    public static Connection getConnection() {
        try {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(URL);
            System.out.println("conexion exitosa");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("conexion cerrada");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
