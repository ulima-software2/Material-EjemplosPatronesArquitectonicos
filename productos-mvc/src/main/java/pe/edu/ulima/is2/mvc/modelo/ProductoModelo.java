package pe.edu.ulima.is2.mvc.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * MODELO (MVC).
 * Guarda el estado de la aplicación (filtro actual y productos visibles) y
 * avisa a sus observadores cuando cambia (patrón Observer).
 * No conoce ni a la Vista ni al Controlador concretos.
 */
public class ProductoModelo {

    /** Cualquiera que quiera enterarse de los cambios del modelo (normalmente la Vista). */
    public interface Observador {
        void modeloActualizado();
    }

    private final ProductoRepositorio repositorio;
    private final List<Observador> observadores = new ArrayList<>();

    private String filtro = "";
    private List<Producto> productos;

    public ProductoModelo(ProductoRepositorio repositorio) {
        this.repositorio = repositorio;
        this.productos = repositorio.listarTodos();
    }

    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    /** Cambia el estado del modelo y notifica a los observadores. */
    public void filtrar(String texto) {
        this.filtro = texto == null ? "" : texto.trim();
        this.productos = repositorio.buscar(filtro);
        notificar();
    }

    public String getFiltro() {
        return filtro;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    private void notificar() {
        observadores.forEach(Observador::modeloActualizado);
    }
}
