package pe.edu.ulima.is2.mvvm.viewmodel;

import java.util.List;

import pe.edu.ulima.is2.mvvm.binding.Comando;
import pe.edu.ulima.is2.mvvm.binding.Propiedad;
import pe.edu.ulima.is2.mvvm.modelo.Producto;
import pe.edu.ulima.is2.mvvm.modelo.ProductoRepositorio;

/**
 * VIEWMODEL (MVVM).
 * Expone el ESTADO de la pantalla como propiedades observables y las ACCIONES
 * como comandos. A diferencia del Presentador de MVP:
 *  - NO tiene referencia a la Vista (ni a una interfaz de ella).
 *  - No "ordena" a la Vista qué mostrar: solo cambia sus propiedades, y la
 *    Vista se actualiza sola gracias al data binding.
 * No importa nada de Swing, por lo que podría reutilizarse con otra UI.
 */
public class ProductoViewModel {

    private final ProductoRepositorio repositorio;

    // ----- Estado observable -----
    private final Propiedad<String> textoBusqueda = new Propiedad<>("");
    private final Propiedad<List<ProductoItem>> productos = new Propiedad<>(List.of());
    private final Propiedad<String> estado = new Propiedad<>("");

    // ----- Comandos -----
    private final Comando buscar = this::buscar;
    private final Comando limpiar = this::limpiar;

    public ProductoViewModel(ProductoRepositorio repositorio) {
        this.repositorio = repositorio;
        cargar(repositorio.listarTodos());
    }

    public Propiedad<String> textoBusqueda() {
        return textoBusqueda;
    }

    public Propiedad<List<ProductoItem>> productos() {
        return productos;
    }

    public Propiedad<String> estado() {
        return estado;
    }

    public Comando comandoBuscar() {
        return buscar;
    }

    public Comando comandoLimpiar() {
        return limpiar;
    }

    private void buscar() {
        // El texto ya está en el ViewModel gracias al enlace bidireccional.
        cargar(repositorio.buscar(textoBusqueda.get()));
    }

    private void limpiar() {
        textoBusqueda.set(""); // el campo de texto se vacía solo (binding)
        cargar(repositorio.listarTodos());
    }

    private void cargar(List<Producto> lista) {
        productos.set(lista.stream()
                .map(p -> new ProductoItem(
                        String.valueOf(p.id()), p.nombre(), p.categoria(),
                        String.format("S/ %,.2f", p.precio()), String.valueOf(p.stock())))
                .toList());
        estado.set(lista.size() + " producto(s) encontrado(s)");
    }
}
