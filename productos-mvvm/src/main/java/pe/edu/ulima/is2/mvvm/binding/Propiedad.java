package pe.edu.ulima.is2.mvvm.binding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Valor observable: cuando cambia, notifica a todos sus observadores.
 * Es la pieza básica que permite el "data binding" entre Vista y ViewModel
 * (equivalente a ObservableValue de JavaFX o LiveData/StateFlow de Android).
 */
public class Propiedad<T> {

    private T valor;
    private final List<Consumer<T>> observadores = new ArrayList<>();

    public Propiedad(T valorInicial) {
        this.valor = valorInicial;
    }

    public T get() {
        return valor;
    }

    public void set(T nuevoValor) {
        if (Objects.equals(valor, nuevoValor)) {
            return; // evita notificaciones (y bucles) innecesarios
        }
        valor = nuevoValor;
        observadores.forEach(o -> o.accept(nuevoValor));
    }

    /** Registra un observador y le entrega inmediatamente el valor actual. */
    public void observar(Consumer<T> observador) {
        observadores.add(observador);
        observador.accept(valor);
    }
}
