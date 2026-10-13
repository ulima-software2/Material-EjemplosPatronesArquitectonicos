package pe.edu.ulima.is2.mvvm.binding;

import java.util.List;
import java.util.function.Function;
import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 * Utilidades de enlace (binding) entre componentes Swing y el ViewModel.
 * Esta clase hace el trabajo que en otras plataformas hace el framework
 * (XAML en WPF, Data Binding en Android, etc.).
 */
public final class Enlazador {

    private Enlazador() {
    }

    /** Enlace bidireccional: JTextField <-> Propiedad<String>. */
    public static void texto(JTextField campo, Propiedad<String> propiedad) {
        // ViewModel -> Vista
        propiedad.observar(valor -> {
            if (!campo.getText().equals(valor)) {
                campo.setText(valor);
            }
        });
        // Vista -> ViewModel
        campo.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                propiedad.set(campo.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                propiedad.set(campo.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                propiedad.set(campo.getText());
            }
        });
    }

    /** Enlace unidireccional: Propiedad<String> -> JLabel. */
    public static void etiqueta(JLabel etiqueta, Propiedad<String> propiedad) {
        propiedad.observar(etiqueta::setText);
    }

    /** Enlaza un botón a un comando del ViewModel. */
    public static void comando(AbstractButton boton, Comando comando) {
        boton.addActionListener(e -> comando.ejecutar());
    }

    /** Enlaza la tecla Enter de un campo de texto a un comando del ViewModel. */
    public static void comando(JTextField campo, Comando comando) {
        campo.addActionListener(e -> comando.ejecutar());
    }

    /** Enlace unidireccional: Propiedad<List<T>> -> filas de una tabla. */
    public static <T> void tabla(DefaultTableModel modeloTabla, Propiedad<List<T>> propiedad,
                                 Function<T, Object[]> aFila) {
        propiedad.observar(elementos -> {
            modeloTabla.setRowCount(0);
            elementos.forEach(e -> modeloTabla.addRow(aFila.apply(e)));
        });
    }
}
