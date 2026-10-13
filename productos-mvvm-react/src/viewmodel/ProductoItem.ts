/**
 * Representación de un producto lista para mostrarse (datos ya formateados).
 * Es parte del ViewModel, no del dominio.
 */
export interface ProductoItem {
  readonly id: string;
  readonly nombre: string;
  readonly categoria: string;
  readonly precio: string;
  readonly stock: string;
}
