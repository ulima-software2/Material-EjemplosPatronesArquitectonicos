package pe.edu.ulima.is2.mvp.vista;

import java.util.List;

/**
 * Contrato de la VISTA (MVP).
 * El Presentador solo conoce esta interfaz, nunca a Swing. Gracias a ello se
 * podría reemplazar la ventana por una vista de consola, web o un "mock" en
 * pruebas unitarias sin tocar el Presentador.
 *
 * Es una "vista pasiva": no tiene lógica ni conoce al Modelo; solo muestra lo
 * que el Presentador le indica y le avisa de lo que hace el usuario.
 */
public interface ProductoVista {

    String getTextoBusqueda();

    void setTextoBusqueda(String texto);

    /** Cada fila llega ya formateada por el Presentador. */
    void mostrarProductos(List<String[]> filas);

    void mostrarEstado(String mensaje);
}
