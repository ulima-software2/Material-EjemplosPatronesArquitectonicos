# Patrones arquitectónicos de presentación: MVC, MVP y MVVM

Tres proyectos Java/Maven que hacen **exactamente lo mismo**: muestran en una
tabla un listado de productos que viene de una lista en memoria, con búsqueda por
nombre/categoría y un botón para limpiar el filtro.

Los proyectos Java solo usan el JDK (Swing + `java.util`), sin dependencias
externas. `productos-mvvm-react` implementa el mismo caso MVVM como app web.

| Proyecto          | Patrón | Clases clave |
|-------------------|--------|--------------|
| `productos-mvc`   | Model-View-Controller | `ProductoModelo`, `ProductoVista`, `ProductoControlador` |
| `productos-mvp`   | Model-View-Presenter  | `ProductoVista` (interfaz), `ProductoVentana`, `ProductoPresentador` |
| `productos-mvvm`  | Model-View-ViewModel  | `ProductoViewModel`, `ProductoVista`, paquete `binding` |
| `productos-mvvm-react` | Model-View-ViewModel (React + TypeScript) | `useProductoViewModel`, `ProductoVista` |

El dominio (`Producto` y `ProductoRepositorio`) es idéntico en los tres.

## Cómo ejecutar

Requiere Java 17+ y Maven.

```bash
cd productos-mvc        # o productos-mvp / productos-mvvm
mvn compile exec:java
# o bien
mvn package && java -jar target/productos-mvc-1.0.0.jar
```

### Versión web (React + TypeScript)

Requiere Node.js 20+.

```bash
cd productos-mvvm-react
npm install
npm run dev        # abre http://localhost:5173
```

## Diferencias clave

### MVC
```
Usuario --> Vista --(evento)--> Controlador --(modifica)--> Modelo
              ^                                               |
              +------------(notifica / la Vista lee)----------+
```
- El **Modelo** guarda el estado y notifica a sus observadores (patrón Observer).
- La **Vista** observa al Modelo y **lee sus datos directamente** para pintarse.
- El **Controlador** solo traduce acciones del usuario en cambios del Modelo;
  nunca toca la Vista.

### MVP
```
Usuario --> Vista --(evento)--> Presentador --(consulta)--> Modelo
              ^                     |
              +--(ordena qué mostrar)
```
- La **Vista** es pasiva y se define como **interfaz** (`ProductoVista`). No conoce
  al Modelo: recibe datos ya formateados (`List<String[]>`).
- El **Presentador** conoce la interfaz de la Vista y le dice explícitamente qué
  mostrar (`vista.mostrarProductos(...)`, `vista.mostrarEstado(...)`).
- Como depende de una interfaz, el Presentador puede probarse con una vista falsa.

### MVVM
```
Vista <==(data binding)==> ViewModel ---(consulta)---> Modelo
```
- El **ViewModel** expone **estado observable** (`Propiedad<T>`) y **comandos**
  (`Comando`). **No tiene ninguna referencia a la Vista** y no importa Swing.
- La **Vista** solo enlaza componentes a propiedades/comandos; se actualiza sola.
- El campo de búsqueda tiene enlace **bidireccional**: al pulsar *Limpiar*, el
  ViewModel pone `textoBusqueda` en `""` y el `JTextField` se vacía solo.
- Swing no trae data binding, por eso el paquete `binding` implementa uno mínimo
  (`Propiedad`, `Comando`, `Enlazador`). En JavaFX, WPF o Android esto lo provee
  la plataforma.
- En `productos-mvvm-react` el ViewModel es un *custom hook*
  (`useProductoViewModel`): `useState` cumple el papel de `Propiedad<T>` y el
  re-render de React cumple el papel del `Enlazador`. El hook no contiene JSX
  ni toca el DOM; la Vista (`ProductoVista.tsx`) solo recibe el ViewModel por
  props y enlaza el `<input>` (bidireccional), la tabla, la etiqueta de estado
  y los botones.

## Resumen

| Pregunta                                  | MVC | MVP | MVVM |
|-------------------------------------------|-----|-----|------|
| ¿La Vista conoce al Modelo?               | Sí  | No  | No   |
| ¿El intermediario conoce a la Vista?      | No (la Vista observa al Modelo) | Sí, vía interfaz | No |
| ¿Quién formatea los datos para mostrar?   | Vista | Presentador | ViewModel |
| ¿Cómo se actualiza la Vista?              | Observer sobre el Modelo | El Presentador llama a sus métodos | Data binding |
