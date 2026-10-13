package pe.edu.ulima.is2.mvp.modelo;

/**
 * Entidad de dominio. Es inmutable y no sabe nada de la interfaz gráfica.
 */
public record Producto(int id, String nombre, String categoria, double precio, int stock) {
}
