package pe.edu.ulima.is2.mvc.modelo;

import java.util.List;
import java.util.Locale;

/**
 * Fuente de datos en memoria. En una aplicación real podría ser una base de
 * datos o un servicio REST; el resto de la aplicación no se enteraría del cambio.
 */
public class ProductoRepositorio {

    private final List<Producto> productos = List.of(
            new Producto(1, "Laptop Lenovo ThinkPad", "Computadoras", 4599.90, 12),
            new Producto(2, "Mouse Logitech M185", "Accesorios", 59.90, 80),
            new Producto(3, "Teclado mecánico Redragon", "Accesorios", 189.00, 35),
            new Producto(4, "Monitor LG 27\"", "Monitores", 1099.00, 20),
            new Producto(5, "Monitor Samsung 24\"", "Monitores", 749.50, 18),
            new Producto(6, "Impresora Epson L3250", "Impresoras", 899.00, 9),
            new Producto(7, "Disco SSD Kingston 1TB", "Almacenamiento", 299.90, 40),
            new Producto(8, "Memoria USB SanDisk 64GB", "Almacenamiento", 39.90, 150),
            new Producto(9, "Audífonos Sony WH-1000XM5", "Audio", 1499.00, 7),
            new Producto(10, "Parlante JBL Flip 6", "Audio", 549.00, 25),
            new Producto(11, "Webcam Logitech C920", "Accesorios", 329.00, 30),
            new Producto(12, "Router TP-Link Archer AX55", "Redes", 389.90, 15),
            new Producto(13, "Laptop HP Pavilion 15", "Computadoras", 3299.00, 10),
            new Producto(14, "Tablet Samsung Galaxy Tab S9", "Tablets", 2899.00, 6),
            new Producto(15, "Switch TP-Link 8 puertos", "Redes", 119.90, 45)
    );

    public List<Producto> listarTodos() {
        return productos;
    }

    /** Devuelve los productos cuyo nombre o categoría contienen el texto (sin distinguir mayúsculas). */
    public List<Producto> buscar(String texto) {
        if (texto == null || texto.isBlank()) {
            return listarTodos();
        }
        String criterio = texto.trim().toLowerCase(Locale.ROOT);
        return productos.stream()
                .filter(p -> p.nombre().toLowerCase(Locale.ROOT).contains(criterio)
                        || p.categoria().toLowerCase(Locale.ROOT).contains(criterio))
                .toList();
    }
}
