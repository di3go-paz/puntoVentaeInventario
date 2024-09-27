package logica;

import modelo.Producto;
import persistencia.ProductoDAO;
import java.util.List;

public class GestionProducto {
    private ProductoDAO productoDAO;

    public GestionProducto() {
        this.productoDAO = new ProductoDAO();
    }

    public void agregarProducto(Producto producto) {
        productoDAO.insertarProducto(producto);
    }

    public List<Producto> listarProductos() {
        return productoDAO.obtenerProductos();
    }

    public void actualizarProducto(Producto producto) {
        productoDAO.actualizarProducto(producto);
    }

    public void eliminarProducto(int idProducto) {
        productoDAO.eliminarProducto(idProducto);
    }

    // Agrega más métodos para actualizar y eliminar productos
}

