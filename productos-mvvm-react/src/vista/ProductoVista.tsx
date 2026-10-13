import type { ProductoViewModel } from '../viewmodel/useProductoViewModel';
import './ProductoVista.css';

const COLUMNAS = ['ID', 'Nombre', 'Categoría', 'Precio', 'Stock'];

interface Props {
  vm: ProductoViewModel;
}

/**
 * VISTA (MVVM).
 * Solo declara los componentes y los ENLAZA al estado y a los comandos del
 * ViewModel. No contiene lógica ni da formato a los datos, y no importa nada
 * del paquete modelo: todo fluye a través del data binding.
 */
export function ProductoVista({ vm }: Props) {
  return (
    <main className="ventana">
      <h1>Listado de productos - MVVM</h1>

      <form
        className="busqueda"
        onSubmit={(e) => {
          e.preventDefault(); // Enter en el campo => comandoBuscar
          vm.comandoBuscar();
        }}
      >
        <label htmlFor="txtBusqueda">Buscar por nombre o categoría:</label>
        {/* Enlace bidireccional: input <-> vm.textoBusqueda */}
        <input
          id="txtBusqueda"
          value={vm.textoBusqueda}
          onChange={(e) => vm.setTextoBusqueda(e.target.value)}
        />
        <button type="submit">Buscar</button>
        <button type="button" onClick={vm.comandoLimpiar}>
          Limpiar
        </button>
      </form>

      <div className="contenedor-tabla">
        <table>
          <thead>
            <tr>
              {COLUMNAS.map((c) => (
                <th key={c}>{c}</th>
              ))}
            </tr>
          </thead>
          <tbody>
            {vm.productos.map((p) => (
              <tr key={p.id}>
                <td>{p.id}</td>
                <td>{p.nombre}</td>
                <td>{p.categoria}</td>
                <td>{p.precio}</td>
                <td>{p.stock}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Enlace unidireccional: vm.estado -> etiqueta */}
      <p className="estado">{vm.estado}</p>
    </main>
  );
}
