package pe.edu.ulima.is2.mvp.vista;

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

import pe.edu.ulima.is2.mvp.presentador.ProductoPresentador;

/**
 * Implementación Swing de la VISTA (MVP).
 * No importa ninguna clase del Modelo: solo recibe Strings del Presentador.
 */
public class ProductoVentana extends JFrame implements ProductoVista {

    private static final String[] COLUMNAS = {"ID", "Nombre", "Categoría", "Precio", "Stock"};

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

    public ProductoVentana() {
        super("Listado de productos - MVP");
        construirInterfaz();
    }

    /** Los eventos del usuario se reenvían al Presentador sin procesarlos. */
    public void setPresentador(ProductoPresentador presentador) {
        btnBuscar.addActionListener(e -> presentador.alBuscar());
        txtBusqueda.addActionListener(e -> presentador.alBuscar());
        btnLimpiar.addActionListener(e -> presentador.alLimpiar());
    }

    @Override
    public String getTextoBusqueda() {
        return txtBusqueda.getText();
    }

    @Override
    public void setTextoBusqueda(String texto) {
        txtBusqueda.setText(texto);
    }

    @Override
    public void mostrarProductos(List<String[]> filas) {
        modeloTabla.setRowCount(0);
        filas.forEach(modeloTabla::addRow);
    }

    @Override
    public void mostrarEstado(String mensaje) {
        lblEstado.setText(mensaje);
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
