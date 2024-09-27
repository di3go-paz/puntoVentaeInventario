package igu;

import logica.GestionProducto;
import modelo.Producto;
import persistencia.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoCrud extends JFrame {
    private GestionProducto gestionProducto;
    private DefaultTableModel modeloTabla;
    private JPanel panel1;
    private JTable table1;
    private JButton buscarButton;
    private JTextField txtBuscar;
    private JTextField IDTextField;
    private JTextField txtnombre;
    private JTextField txtstock;
    private JTextField txtcodigo;
    private JTextField txtvalor;
    private JButton btnguardar;
    private JComboBox comboBox1;
    private JTextField txtventa;
    private JButton btneliminar;
    private JButton btneditar;
    private JButton btnagregar;
    private JButton Btn_Nuevo;
    private ProductoDAO productoDAO;
    public ProductoCrud() {
        gestionProducto = new GestionProducto();
        productoDAO = new ProductoDAO();
        initGUI();
        Btn_Nuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nuevoid = GenerarNuevoId();
                System.out.println("nuevo id "+nuevoid);
                IDTextField.setText(String.valueOf(nuevoid));
                txtnombre.requestFocusInWindow();
                habilitarCampos(true);
            }
        });
        btnagregar.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(IDTextField.getText());
                    String nombre = txtnombre.getText();
                    String codigo = txtcodigo.getText();
                    double valor = Double.parseDouble(txtvalor.getText());
                    double venta = Double.parseDouble(txtventa.getText());
                    int stock = Integer.parseInt(txtstock.getText());
                    System.out.println("agregando producto");
                    Producto producto = new Producto(id,nombre,codigo,valor,venta,stock,null);
                    productoDAO.insertarProducto(producto);
                    cargarTabla();
                    JOptionPane.showMessageDialog(null, "Producto creado exitosamente");
                }catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "valores no validos");
                }catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al insertar producto");
                }
            }
        });
        btneliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int id = Integer.parseInt(IDTextField.getText());
                }
            }
        });
    }

    private void initGUI() {
        setContentPane(panel1);
        setTitle("Gestión de Productos");
        setSize(1440, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cargarTabla();
        habilitarCampos(false);
        setVisible(true);
    }
    private void habilitarCampos(boolean habilitar) {
        txtnombre.setText("");
        txtcodigo.setText("");
        txtvalor.setText("");
        txtventa.setText("");
        txtstock.setText("");
        txtnombre.setEnabled(habilitar);
        txtcodigo.setEnabled(habilitar);
        txtvalor.setEnabled(habilitar);
        txtventa.setEnabled(habilitar);
        txtstock.setEnabled(habilitar);
    }

    private void cargarTabla() {
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Código de Barras", "Valor Compra", "Valor Venta", "Stock", "Proveedor"}, 0);
        table1.setModel(modeloTabla);
        List<Producto> productos = gestionProducto.listarProductos();
        System.out.println("Número de productos encontrados: " + productos.size());
        for (Producto producto : productos) {
            modeloTabla.addRow(new Object[]{
                    producto.getIdProducto(),
                    producto.getNombre(),
                    producto.getCodigoBarras(),
                    producto.getValorCompra(),
                    producto.getValorVenta(),
                    producto.getStock(),
                    producto.getRutProveedor()
            });
        }
    }
    private int GenerarNuevoId() {
        ProductoDAO productoDAO = new ProductoDAO();
        return productoDAO.obtenerIdProducto() +1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductoCrud());
    }

}
