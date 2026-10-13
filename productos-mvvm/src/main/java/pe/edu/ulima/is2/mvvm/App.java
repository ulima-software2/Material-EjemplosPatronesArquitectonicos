package pe.edu.ulima.is2.mvvm;

import javax.swing.SwingUtilities;

import pe.edu.ulima.is2.mvvm.modelo.ProductoRepositorio;
import pe.edu.ulima.is2.mvvm.viewmodel.ProductoViewModel;
import pe.edu.ulima.is2.mvvm.vista.ProductoVista;

/**
 * Punto de entrada: arma las piezas del patrón MVVM.
 *
 *   Vista <==(data binding)==> ViewModel ---(consulta)---> Modelo
 *
 * La Vista conoce al ViewModel; el ViewModel NO conoce a la Vista.
 */
public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductoViewModel viewModel = new ProductoViewModel(new ProductoRepositorio());
            ProductoVista vista = new ProductoVista(viewModel);
            vista.setVisible(true);
        });
    }
}
