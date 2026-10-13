package pe.edu.ulima.is2.mvvm.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import pe.edu.ulima.is2.mvvm.binding.Enlazador;
import pe.edu.ulima.is2.mvvm.viewmodel.ProductoViewModel;

/**
 * VISTA (MVVM).
 * Solo crea los componentes y los ENLAZA a las propiedades y comandos del
 * ViewModel. No contiene listeners con lógica ni métodos "mostrarXxx()":
 * todo fluye a través del data binding.
 */
public class ProductoVista extends JFrame {

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

    public ProductoVista(ProductoViewModel viewModel) {
        super("Listado de productos - MVVM");
        construirInterfaz();
        enlazar(viewModel);
    }

    private void enlazar(ProductoViewModel vm) {
        Enlazador.texto(txtBusqueda, vm.textoBusqueda());
        Enlazador.comando(btnBuscar, vm.comandoBuscar());
        Enlazador.comando(txtBusqueda, vm.comandoBuscar());
        Enlazador.comando(btnLimpiar, vm.comandoLimpiar());
        Enlazador.etiqueta(lblEstado, vm.estado());
        Enlazador.tabla(modeloTabla, vm.productos(),
                p -> new Object[]{p.id(), p.nombre(), p.categoria(), p.precio(), p.stock()});
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
