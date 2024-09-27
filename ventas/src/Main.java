import persistencia.Conexion;
import java.sql.Connection;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Connection conn = Conexion.getConnection();
        if (conn != null) {
            System.out.println("La conexión fue establecida correctamente.");
        } else {
            System.out.println("Error al establecer la conexión.");
        }
        Conexion.closeConnection();
    }
}