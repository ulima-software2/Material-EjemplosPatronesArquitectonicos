package pe.edu.ulima.is2.mvp.presentador;

import java.util.List;

import pe.edu.ulima.is2.mvp.modelo.Producto;
import pe.edu.ulima.is2.mvp.modelo.ProductoRepositorio;
import pe.edu.ulima.is2.mvp.vista.ProductoVista;

/**
 * PRESENTADOR (MVP).
 * Es el intermediario entre Vista y Modelo:
 *  1. La Vista le avisa de un evento del usuario.
 *  2. El Presentador consulta al Modelo.
 *  3. El Presentador prepara (formatea) los datos y le dice a la Vista qué mostrar.
 * La Vista y el Modelo nunca se comunican directamente.
 */
public class ProductoPresentador {

    private final ProductoVista vista;
    private final ProductoRepositorio repositorio;

    public ProductoPresentador(ProductoVista vista, ProductoRepositorio repositorio) {
        this.vista = vista;
        this.repositorio = repositorio;
    }

    public void iniciar() {
        mostrar(repositorio.listarTodos());
    }

    public void alBuscar() {
        mostrar(repositorio.buscar(vista.getTextoBusqueda()));
    }

    public void alLimpiar() {
        vista.setTextoBusqueda("");
        mostrar(repositorio.listarTodos());
    }

    private void mostrar(List<Producto> productos) {
        List<String[]> filas = productos.stream()
                .map(p -> new String[]{
                        String.valueOf(p.id()), p.nombre(), p.categoria(),
                        String.format("S/ %,.2f", p.precio()), String.valueOf(p.stock())
                })
                .toList();
        vista.mostrarProductos(filas);
        vista.mostrarEstado(productos.size() + " producto(s) encontrado(s)");
    }
}
