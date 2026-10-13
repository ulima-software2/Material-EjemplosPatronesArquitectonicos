package pe.edu.ulima.is2.mvc.controlador;

import pe.edu.ulima.is2.mvc.modelo.ProductoModelo;

/**
 * CONTROLADOR (MVC).
 * Recibe las acciones del usuario (que le reenvía la Vista) y las traduce en
 * operaciones sobre el Modelo. No actualiza la Vista directamente: la Vista se
 * entera de los cambios porque observa al Modelo.
 */
public class ProductoControlador {

    private final ProductoModelo modelo;

    public ProductoControlador(ProductoModelo modelo) {
        this.modelo = modelo;
    }

    public void buscar(String texto) {
        modelo.filtrar(texto);
    }

    public void limpiar() {
        modelo.filtrar("");
    }
}
