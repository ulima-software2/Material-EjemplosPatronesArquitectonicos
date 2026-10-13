package pe.edu.ulima.is2.mvvm.binding;

/**
 * Acción que el ViewModel expone para que la Vista la enlace a un botón,
 * a la tecla Enter, a un menú, etc. (similar a ICommand en WPF).
 */
@FunctionalInterface
public interface Comando {
    void ejecutar();
}
