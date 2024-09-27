package persistencia;
import modelo.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ProductoDAO {
    private Conexion conexion;
    public ProductoDAO() {
        this.conexion = new Conexion();
    }
    public int obtenerIdProducto() {
        int idProducto = 1;
            String sql = "SELECT MAX(id_producto) FROM Productos";
            try(Connection conn = conexion.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    idProducto = rs.getInt(1);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        return idProducto;
    }
    //metodo para insertar producto

    public void insertarProducto(Producto producto) {
        String sql = "INSERT INTO Productos (nombre,codigo_barras,valor_compra,valor_venta,stock, rut_proveedor) VALUES (?,?,?,?,?,?)";
        try (Connection conn = conexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, producto.getNombre());
            pstmt.setString(2, producto.getCodigoBarras());
            pstmt.setDouble(3, producto.getValorCompra());
            pstmt.setDouble(4, producto.getValorVenta());
            pstmt.setInt(5, producto.getStock());
            pstmt.setString(6, producto.getRutProveedor());
            pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM Productos";
        try(Connection conn = Conexion.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()){
            System.out.println("Producto: " + rs.getString("nombre"));
            Producto producto = new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre"),
                    rs.getString("codigo_barras"),
                    rs.getDouble("valor_compra"),
                    rs.getDouble("valor_venta"),
                    rs.getInt("stock"),
                    rs.getString("rut_proveedor")
            );
            productos.add(producto);
        }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return productos;
    }
    public void actualizarProducto(Producto producto){
        String sql = "UPDATE producto SET nombre = ?, codigo_barras = ?, valor_venta = ?, stock = ?, rut_proveedor = ? WHERE id_producto = ? ";
        try(Connection conn = Conexion.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,producto.getNombre());
            pstmt.setString(2,producto.getCodigoBarras());
            pstmt.setDouble(3,producto.getValorVenta());
            pstmt.setInt(5, producto.getStock());
            pstmt.setString(6, producto.getRutProveedor());
            pstmt.setInt(7, producto.getIdProducto());
            pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void eliminarProducto(int idProducto){
        String sql = "DELETE FROM producto WHERE id_producto = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idProducto);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
