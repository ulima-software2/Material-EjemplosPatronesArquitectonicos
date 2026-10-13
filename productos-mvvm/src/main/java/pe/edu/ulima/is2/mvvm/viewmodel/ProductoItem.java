package pe.edu.ulima.is2.mvvm.viewmodel;

/**
 * Representación de un producto lista para mostrarse (datos ya formateados).
 * Es parte del ViewModel, no del dominio.
 */
public record ProductoItem(String id, String nombre, String categoria, String precio, String stock) {
}
