Proyecto Venta de entradas

Compilación
Para compilar el código, asegúrate de tener Java Development Kit (JDK) instalado en tu sistema. Luego, ejecuta el siguiente comando en la terminal:

javac MenuVentaEntradasV2.java

Ejecución
Una vez compilado el código, puedes ejecutar el programa con el siguiente comando:

java MenuVentaEntradasV2

Esto iniciará el programa y mostrará un menú interactivo en la consola donde podrás interactuar con el sistema de venta de entradas y si desea compilar y ejecutar el programa en una app o pagina web solo debe descargar el archivo e importarlo en la app o pagina web para poder ejecutar.


Acciones de los Usuarios

Al ejecutar el programa, los usuarios podrán realizar las siguientes acciones:

1. Mostrar lista de eventos.
2. Mostrar eventos por palabra.
3. Ver entradas disponibles.
4. Registrar usuario.
5. Comprar entrada.
6. Registrar administrador.
7. Agregar un evento (admin).
8. Eliminar un evento (admin).
9. Eliminar usuario (admin).
10. Añadir entradas (admin).
11. Mostrar reporte (admin).
0. Salir.




Para hacer que el código sea utilizable, hay que hacer modificaciones en los directorios en los que se encuentran los archivos CSV utilizados. Para ello hay que seguir un paso a paso dentro de netbeans:

- Hacer doble clic en los archivos eventos.csv y usuarios.csv
- Darle click derecho en los nombres de estos archivos y clickear Copy File Path. Esto hay que hacerlo para las lineas que trabajan con eventos.csv y usuarios.csv.
- Pegar las direcciones en las lineas correspondientes.

Aquí hay un listado de las lineas que hay que cambiar para que el código funcione. Para localizar fácilmente las lineas se puede usar Ctrl+F y poner \\java\\eventos o \\java\\usuarios.

En CSVManager:
- Linea 149 (usuarios)
- Linea 186 (eventos)

En main:
- Linea 24 (eventos)
- Linea 27 (eventos)
- Linea 101 (usuarios)
- Linea 110 (usuarios)
- Linea 164 (eventos)
- Linea 180 (usuarios)
- Linea 189 (usuarios)
- Linea 213 (eventos)
- Linea 224 (usuarios)
- Linea 232 (eventos)
- Linea 248 (usuarios)
- Linea 257 (usuarios)
- Linea 263 (eventos)
- Linea 276 (usuarios)
- Linea 300 (usuarios)

Para las ventanas, hay que darle doble click a cada una de las ventanas y luego ir a source, luego cambiar las siguientes lineas:
- Ventana 1 Linea 158 (usuarios)
- Ventana 2 Linea 145 (usuarios), Linea 162 (usuarios)
- VentanaAgregarEntradas Linea 137 (eventos)
- VentanaAgregarEvento Linea 156 (eventos)
- VentanaComprarEntradas Linea 149 (eventos)
- VentanaDisponibilidad Linea 86 (eventos)
- VentanaEliminarEvento Linea 152 (eventos)
- VentanaEventos Linea 66 (eventos)
- VentanaReporte Linea 72 (eventos), Linea 101 (usuarios)


Hecho esto, se pueden ejecutar las ventanas dándole click derecho por ejemplo a Ventana1 en la sección files en la parte izquierda de netbeans, y luego Run File.
Lo mismo aplica para el resto de ventanas.
