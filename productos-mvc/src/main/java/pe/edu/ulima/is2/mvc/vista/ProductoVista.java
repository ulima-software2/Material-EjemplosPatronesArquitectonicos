package pe.edu.ulima.is2.mvc.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import pe.edu.ulima.is2.mvc.controlador.ProductoControlador;
import pe.edu.ulima.is2.mvc.modelo.Producto;
import pe.edu.ulima.is2.mvc.modelo.ProductoModelo;

/**
 * VISTA (MVC).
 * - Observa al Modelo y, cuando este cambia, LEE directamente sus datos para
 *   pintarse (la Vista conoce al Modelo).
 * - Reenvía los eventos del usuario al Controlador.
 */
public class ProductoVista extends JFrame implements ProductoModelo.Observador {

    private static final String[] COLUMNAS = {"ID", "Nombre", "Categoría", "Precio", "Stock"};

    private final ProductoModelo modelo;

    private final JTextField txtBusqueda = new JTextField(20);
    private final JButton btnBuscar = new JButton("Buscar");
    private final JButton btnLimpiar = new JButton("Limpiar");
    private final JLabel lblEstado = new JLabel();
    private final DefaultTableModel modeloTabla = new DefaultTableModel(COLUMNAS, 0) {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };

    public ProductoVista(ProductoModelo modelo) {
        super("Listado de productos - MVC");
        this.modelo = modelo;
        construirInterfaz();
    }

    /** La Vista delega en el Controlador todo lo que hace el usuario. */
    public void setControlador(ProductoControlador controlador) {
        btnBuscar.addActionListener(e -> controlador.buscar(txtBusqueda.getText()));
        txtBusqueda.addActionListener(e -> controlador.buscar(txtBusqueda.getText()));
        btnLimpiar.addActionListener(e -> controlador.limpiar());
    }

    /** Llamado por el Modelo cuando su estado cambia. */
    @Override
    public void modeloActualizado() {
        List<Producto> productos = modelo.getProductos();

        modeloTabla.setRowCount(0);
        for (Producto p : productos) {
            modeloTabla.addRow(new Object[]{
                    p.id(), p.nombre(), p.categoria(),
                    String.format("S/ %,.2f", p.precio()), p.stock()
            });
        }
        if (!txtBusqueda.getText().equals(modelo.getFiltro())) {
            txtBusqueda.setText(modelo.getFiltro());
        }
        lblEstado.setText(productos.size() + " producto(s) encontrado(s)");
    }

    private void construirInterfaz() {
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(new JLabel("Buscar por nombre o categoría:"));
        panelBusqueda.add(txtBusqueda);
        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(btnLimpiar);

        JTable tabla = new JTable(modeloTabla);
        tabla.setFillsViewportHeight(true);

        lblEstado.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

        setLayout(new BorderLayout());
        add(panelBusqueda, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(lblEstado, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720, 420);
        setLocationRelativeTo(null);
    }
}
