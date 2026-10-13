import { ProductoRepositorio } from './modelo/ProductoRepositorio';
import { useProductoViewModel } from './viewmodel/useProductoViewModel';
import { ProductoVista } from './vista/ProductoVista';

const repositorio = new ProductoRepositorio();

/**
 * Arma las piezas del patrón MVVM.
 *
 *   Vista <==(data binding)==> ViewModel ---(consulta)---> Modelo
 *
 * La Vista conoce al ViewModel; el ViewModel NO conoce a la Vista.
 */
export function App() {
  const viewModel = useProductoViewModel(repositorio);
  return <ProductoVista vm={viewModel} />;
}
