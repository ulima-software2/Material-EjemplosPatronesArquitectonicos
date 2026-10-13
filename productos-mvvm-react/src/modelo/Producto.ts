/**
 * Entidad de dominio. Es inmutable y no sabe nada de la interfaz gráfica.
 */
export interface Producto {
  readonly id: number;
  readonly nombre: string;
  readonly categoria: string;
  readonly precio: number;
  readonly stock: number;
}
