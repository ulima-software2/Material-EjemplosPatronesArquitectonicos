import { useCallback, useMemo, useState } from 'react';

import type { Producto } from '../modelo/Producto';
import type { ProductoRepositorio } from '../modelo/ProductoRepositorio';
import type { ProductoItem } from './ProductoItem';

/**
 * Contrato del ViewModel: ESTADO observable + COMANDOS.
 * Es lo único que la Vista conoce.
 */
export interface ProductoViewModel {
  // ----- Estado observable -----
  readonly textoBusqueda: string;
  readonly productos: readonly ProductoItem[];
  readonly estado: string;
  // ----- Enlace Vista -> ViewModel (mitad "de vuelta" del binding bidireccional) -----
  setTextoBusqueda(texto: string): void;
  // ----- Comandos -----
  comandoBuscar(): void;
  comandoLimpiar(): void;
}

const formatoPrecio = new Intl.NumberFormat('es-PE', {
  minimumFractionDigits: 2,
  maximumFractionDigits: 2,
});

function aItem(p: Producto): ProductoItem {
  return {
    id: String(p.id),
    nombre: p.nombre,
    categoria: p.categoria,
    precio: `S/ ${formatoPrecio.format(p.precio)}`,
    stock: String(p.stock),
  };
}

/**
 * VIEWMODEL (MVVM).
 * Expone el ESTADO de la pantalla y las ACCIONES como comandos. A diferencia
 * del Presentador de MVP:
 *  - NO tiene referencia a la Vista (ni a una interfaz de ella).
 *  - No "ordena" a la Vista qué mostrar: solo cambia su estado, y React vuelve
 *    a renderizar la Vista automáticamente (ese es el data binding).
 * No contiene JSX ni toca el DOM, por lo que podría reutilizarse con otra UI
 * (por ejemplo, React Native).
 *
 * En React, la forma idiomática de un ViewModel es un custom hook: useState
 * cumple el papel de Propiedad<T> del proyecto Java y el re-render cumple el
 * papel del Enlazador.
 */
export function useProductoViewModel(repositorio: ProductoRepositorio): ProductoViewModel {
  const [textoBusqueda, setTextoBusqueda] = useState('');
  const [lista, setLista] = useState<readonly Producto[]>(() => repositorio.listarTodos());

  // Datos derivados: el ViewModel formatea, la Vista solo pinta.
  const productos = useMemo(() => lista.map(aItem), [lista]);
  const estado = `${lista.length} producto(s) encontrado(s)`;

  const comandoBuscar = useCallback(() => {
    // El texto ya está en el ViewModel gracias al enlace bidireccional.
    setLista(repositorio.buscar(textoBusqueda));
  }, [repositorio, textoBusqueda]);

  const comandoLimpiar = useCallback(() => {
    setTextoBusqueda(''); // el campo de texto se vacía solo (binding)
    setLista(repositorio.listarTodos());
  }, [repositorio]);

  return { textoBusqueda, productos, estado, setTextoBusqueda, comandoBuscar, comandoLimpiar };
}
