package pe.edu.ulima.is2.mvp;

import javax.swing.SwingUtilities;

import pe.edu.ulima.is2.mvp.modelo.ProductoRepositorio;
import pe.edu.ulima.is2.mvp.presentador.ProductoPresentador;
import pe.edu.ulima.is2.mvp.vista.ProductoVentana;

/**
 * Punto de entrada: arma las piezas del patrón MVP.
 *
 *   Usuario --> Vista --(evento)--> Presentador --(consulta)--> Modelo
 *                 ^                     |
 *                 +--(ordena qué mostrar)
 *
 * Vista y Modelo no se conocen entre sí.
 */
public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductoVentana vista = new ProductoVentana();
            ProductoPresentador presentador = new ProductoPresentador(vista, new ProductoRepositorio());

            vista.setPresentador(presentador);
            presentador.iniciar();
            vista.setVisible(true);
        });
    }
}
