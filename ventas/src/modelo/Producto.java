package modelo;

public class Producto {

    private int idProducto;
    private String nombre;
    private String codigoBarras;
    private double valorCompra;
    private double valorVenta;
    private int stock;
    private String rutProveedor;

    // Constructor
    public Producto(int idProducto, String nombre, String codigoBarras, double valorCompra, double valorVenta, int stock, String rutProveedor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.codigoBarras = codigoBarras;
        this.valorCompra = valorCompra;
        this.valorVenta = valorVenta;
        this.stock = stock;
        this.rutProveedor = rutProveedor;
    }

    // Getters
    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public double getValorVenta() {
        return valorVenta;
    }

    public int getStock() {
        return stock;
    }

    public String getRutProveedor() {
        return rutProveedor;
    }
}
