package pe.edu.ulima.is2.mvc;

import javax.swing.SwingUtilities;

import pe.edu.ulima.is2.mvc.controlador.ProductoControlador;
import pe.edu.ulima.is2.mvc.modelo.ProductoModelo;
import pe.edu.ulima.is2.mvc.modelo.ProductoRepositorio;
import pe.edu.ulima.is2.mvc.vista.ProductoVista;

/**
 * Punto de entrada: arma las piezas del patrón MVC.
 *
 *   Usuario --> Vista --(evento)--> Controlador --(modifica)--> Modelo
 *                 ^                                               |
 *                 +------------(notifica / la Vista lee)----------+
 */
public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductoModelo modelo = new ProductoModelo(new ProductoRepositorio());
            ProductoVista vista = new ProductoVista(modelo);
            ProductoControlador controlador = new ProductoControlador(modelo);

            vista.setControlador(controlador);
            modelo.agregarObservador(vista);

            vista.modeloActualizado(); // primer pintado con todos los productos
            vista.setVisible(true);
        });
    }
}
