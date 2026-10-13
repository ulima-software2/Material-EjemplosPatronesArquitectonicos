import type { Producto } from './Producto';

/**
 * Fuente de datos en memoria. En una aplicación real podría ser una base de
 * datos o un servicio REST; el resto de la aplicación no se enteraría del cambio.
 */
export class ProductoRepositorio {
  private readonly productos: readonly Producto[] = [
    { id: 1, nombre: 'Laptop Lenovo ThinkPad', categoria: 'Computadoras', precio: 4599.9, stock: 12 },
    { id: 2, nombre: 'Mouse Logitech M185', categoria: 'Accesorios', precio: 59.9, stock: 80 },
    { id: 3, nombre: 'Teclado mecánico Redragon', categoria: 'Accesorios', precio: 189.0, stock: 35 },
    { id: 4, nombre: 'Monitor LG 27"', categoria: 'Monitores', precio: 1099.0, stock: 20 },
    { id: 5, nombre: 'Monitor Samsung 24"', categoria: 'Monitores', precio: 749.5, stock: 18 },
    { id: 6, nombre: 'Impresora Epson L3250', categoria: 'Impresoras', precio: 899.0, stock: 9 },
    { id: 7, nombre: 'Disco SSD Kingston 1TB', categoria: 'Almacenamiento', precio: 299.9, stock: 40 },
    { id: 8, nombre: 'Memoria USB SanDisk 64GB', categoria: 'Almacenamiento', precio: 39.9, stock: 150 },
    { id: 9, nombre: 'Audífonos Sony WH-1000XM5', categoria: 'Audio', precio: 1499.0, stock: 7 },
    { id: 10, nombre: 'Parlante JBL Flip 6', categoria: 'Audio', precio: 549.0, stock: 25 },
    { id: 11, nombre: 'Webcam Logitech C920', categoria: 'Accesorios', precio: 329.0, stock: 30 },
    { id: 12, nombre: 'Router TP-Link Archer AX55', categoria: 'Redes', precio: 389.9, stock: 15 },
    { id: 13, nombre: 'Laptop HP Pavilion 15', categoria: 'Computadoras', precio: 3299.0, stock: 10 },
    { id: 14, nombre: 'Tablet Samsung Galaxy Tab S9', categoria: 'Tablets', precio: 2899.0, stock: 6 },
    { id: 15, nombre: 'Switch TP-Link 8 puertos', categoria: 'Redes', precio: 119.9, stock: 45 },
  ];

  listarTodos(): readonly Producto[] {
    return this.productos;
  }

  /** Devuelve los productos cuyo nombre o categoría contienen el texto (sin distinguir mayúsculas). */
  buscar(texto: string): readonly Producto[] {
    if (texto.trim() === '') {
      return this.listarTodos();
    }
    const criterio = texto.trim().toLowerCase();
    return this.productos.filter(
      (p) =>
        p.nombre.toLowerCase().includes(criterio) ||
        p.categoria.toLowerCase().includes(criterio),
    );
  }
}
