import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        CSVManager csvManager = new CSVManager(new CollectionManager());
        HashMap<String, Evento> eventos = csvManager.lecturaEventos("C:\\Users\\sebas\\OneDrive\\Documentos\\NetBeansProjects\\MenuVentaEntradasV2\\src\\main\\java\\eventos.csv");      
        
        try {
            eventos = csvManager.lecturaEventos("C:\\Users\\sebas\\OneDrive\\Documentos\\NetBeansProjects\\MenuVentaEntradasV2\\src\\main\\java\\eventos.csv");
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de eventos.");
        }
        
        if (eventos.isEmpty()) {
            System.out.println("No se encontraron eventos.");
            return;
        }
        
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continuar = true;
            
            while (continuar) {
                System.out.println("Por favor, selecciona una opción:");
                System.out.println("1. Mostrar lista de eventos");
                System.out.println("2. Mostrar eventos por palabra");
                System.out.println("3. Ver entradas disponibles");
                System.out.println("4. Registrar usuario");
                System.out.println("5. Comprar entrada");
                System.out.println("6. Registrar administrador");
                System.out.println("7. Agregar un evento (admin)");
                System.out.println("8. Eliminar un evento (admin)");
                System.out.println("9. Eliminar usuario (admin)");
                System.out.println("10. Añadir entradas (admin)");
                System.out.println("11. Mostrar reporte (admin)");
                System.out.println("0. Salir");
                
                String opcion = scanner.nextLine();
                String id;
                
                switch (opcion) {
                    case "1":
                        mostrarEventos(eventos);
                        break;
                    case "2":
                        System.out.println("Por favor, introduce una palabra clave para buscar eventos:");
                        String palabraClave = scanner.nextLine();
                        mostrarEventos(eventos, palabraClave);
                        break;
                    case "3":
                        System.out.println("Por favor, introduce la ID del evento que deseas ver:");
                        String idEvento = scanner.nextLine();
                        Evento evento = eventos.get(idEvento);
                        if (evento != null) {
                            List<Entrada> entradasDisponibles = evento.getEntradasDisponibles();
                            System.out.println("Entradas disponibles:");
                            for (Entrada entrada : entradasDisponibles) {
                                System.out.println(entrada.getId());
                            }
                        } else {
                            System.out.println("No se encontró ningún evento con esa ID.");
                        }
                        break;
                    case "4":
                        System.out.println("Por favor, introduce tu RUT:");
                        String rut = scanner.nextLine();
                        System.out.println("Por favor, introduce tu contraseña:");
                        String contrasena = scanner.nextLine();
                        
                        Usuario nuevoUsuario;
                        
                        System.out.println("¿Quieres ser usuario premium? (s/n)");
                        String esPremium = scanner.nextLine();
                        if (esPremium.equalsIgnoreCase("s")) {
                            nuevoUsuario = new UsuarioPremium();
                            nuevoUsuario.setContrasena(contrasena);
                        } else {
                            nuevoUsuario = new Usuario();
                            nuevoUsuario.setContrasena(contrasena);
                        }
                        
                        nuevoUsuario.setRut(rut);
                        
                        csvManager.registrarUsuario("C:\\Users\\sebas\\OneDrive\\Documentos\\NetBeansProjects\\MenuVentaEntradasV2\\src\\main\\java\\usuarios.csv", nuevoUsuario);
                        System.out.println("Usuario registrado con éxito.");
                        break;
                    case "5":
                        System.out.println("Por favor, introduce tu RUT:");
                        rut = scanner.nextLine();
                        System.out.println("Por favor, introduce tu contraseña:");
                        contrasena = scanner.nextLine();
                        
                        Usuario usuario = csvManager.validarUsuario("C:\\Users\\sebas\\OneDrive\\Documentos\\NetBeansProjects\\MenuVentaEntradasV2\\src\\main\\java\\usuarios.csv", rut, contrasena);
                        if (usuario == null) {
                            System.out.println("RUT o contraseña incorrectos.");
                            break;
                        }
                        
                        System.out.println("Por favor, introduce la ID del evento para el que deseas comprar una entrada:");
                        idEvento = scanner.nextLine();
                        evento = eventos.get(idEvento);
                        if (evento == null) {
                            System.out.println("No se encontró ningún evento con esa ID.");
                            break;
                        }
                        
                        List<Entrada> entradasDisponibles = evento.getEntradasDisponibles();
                        if (entradasDisponibles.isEmpty()) {
                            System.out.println("No hay entradas disponibles para este evento.");
                            break;
                        }
                        
                        System.out.println("Entradas disponibles:");
                        for (Entrada entrada : entradasDisponibles) {
                            System.out.println(entrada.getId());
                        }
                        
                        System.out.println("Por favor, introduce la ID de la entrada que deseas comprar:");
                        String idEntrada = scanner.nextLine();
                        Entrada entradaSeleccionada = null;
                        for (Entrada entrada : entradasDisponibles) {
                            if (entrada.getId().equals(idEntrada)) {
                                entradaSeleccionada = entrada;
                                break;
                            }
                        }
                        
                        if (entradaSeleccionada == null) {
                            System.out.println("No se encontró ninguna entrada con esa ID.");
                            break;
                        }
                        
                        System.out.println("El precio de la entrada es: " + evento.getPrecioDeEntrada());
                        
                        double precioEntrada = evento.getPrecioDeEntrada();
                        if (usuario instanceof UsuarioPremium) {
                            precioEntrada /= 2;
                        }
                        
                        System.out.println("Precio de la entrada con descuento: " + precioEntrada);
                        
                        System.out.println("¿Deseas realizar la compra? (s/n)");
                        String confirmacion = scanner.nextLine();
                        if (confirmacion.equalsIgnoreCase("s")) {
                            entradasDisponibles.remove(entradaSeleccionada);
                            evento.setEntradasRestantes(evento.getEntradasRestantes() - 1);
                            csvManager.guardarEventos("C:\\Users\\sebas\\OneDrive\\Documentos\\NetBeansProjects\\MenuVentaEntradasV2\\src\\main\\java\\eventos.csv", eventos);
                            System.out.println("Compra realizada con éxito.");
                        }
                        break;
                    case "6":
                        System.out.println("Por favor, introduce tu RUT:");
                        rut = scanner.nextLine();
                        System.out.println("Por favor, introduce tu contraseña:");
                        contrasena = scanner.nextLine();
                        System.out.println("Por favor, introduce el código de seguridad:");
                        String codigoSeguridad = scanner.nextLine();
                        
                        Usuario nuevoAdmin = new Usuario();
                        nuevoAdmin.setRut(rut);
                        nuevoAdmin.setContrasena(contrasena);
                        
                        csvManager.registrarUsuario("C:\\Users\\sebas\\OneDrive\\Documentos\\NetBeansProjects\\MenuVentaEntradasV2\\src\\main\\java\\usuarios.csv", nuevoAdmin, codigoSeguridad);
                        break;
                        
                    case "7":
                        
                        System.out.println("Por favor, introduce tu RUT:");
                        rut = scanner.nextLine();
                        System.out.println("Por favor, introduce tu contraseña:");
                        contrasena = scanner.nextLine();
                        usuario = csvManager.validarUsuario("C:\\Users\\sebas\\OneDrive\\Documentos\\NetBeansProjects\\MenuVentaEntradasV2\\src\\main\\java\\usuarios.csv", rut, contrasena);
                        
                        if (usuario != null && rut.endsWith("(admin)")) {
                            System.out.println("Por favor, introduce los detalles del nuevo evento:");
                            System.out.print("ID: ");
                            id = scanner.nextLine();
                            System.out.print("Nombre: ");
                            String nombre = scanner.nextLine();
                            System.out.print("Ubicación: ");
                            String ubicacion = scanner.nextLine();
                            System.out.print("Entradas restantes: ");
                            int entradasRestantes = Integer.parseInt(scanner.nextLine());
                            System.out.print("Precio de entrada: ");
                            int precioDeEntrada = Integer.parseInt(scanner.nextLine());
                            
                            Evento nuevoEvento = new Evento(); // Solo creamos un evento regular
                            
                            nuevoEvento.setId(id);
                            nuevoEvento.setNombre(nombre);
                            nuevoEvento.setUbicacion(ubicacion);
                            nuevoEvento.setEntradasRestantes(entradasRestantes);
                            nuevoEvento.setPrecioDeEntrada(precioDeEntrada); // Establecemos el precio de entrada
                            
                            eventos.put(id, nuevoEvento);
                            csvManager.guardarEventos("C:\\Users\\sebas\\OneDrive\\Documentos\\NetBeansProjects\\MenuVentaEntradasV2\\src\\main\\java\\eventos.csv", eventos);
                        } else {
                            System.out.println("Error: RUT o contraseña incorrectos o no tienes permisos de administrador.");
                        }
                        break;
                        
                    case "8":
                        System.out.println("Por favor, introduce tu RUT:");
                        rut = scanner.nextLine();
                        System.out.println("Por favor, introduce tu contraseña:");
                        contrasena = scanner.nextLine();
                        usuario = csvManager.validarUsuario("C:\\Users\\sebas\\OneDrive\\Documentos\\NetBeansProjects\\MenuVentaEntradasV2\\src\\main\\java\\usuarios.csv", rut, contrasena);
                        
                        if (usuario != null && rut.endsWith("(admin)")) {
                            System.out.println("Por favor, introduce la ID del evento que deseas eliminar:");
                            id = scanner.nextLine();
                            
                            if (eventos.containsKey(id)) {
                                eventos.remove(id);
                                csvManager.guardarEventos("C:\\Users\\sebas\\OneDrive\\Documentos\\NetBeansProjects\\MenuVentaEntradasV2\\src\\main\\java\\eventos.csv", eventos);
                                System.out.println("El evento ha sido eliminado.");
                            } else {
                                System.out.println("No se encontró ningún evento con esa ID.");
                            }
                        } else {
                            System.out.println("Error: RUT o contraseña incorrectos o no tienes permisos de administrador.");
                        }
                        break;
                        
                    case "9":
                        System.out.println("Por favor, introduce tu RUT:");
                        rut = scanner.nextLine();
                        System.out.println("Por favor, introduce tu contraseña:");
                        contrasena = scanner.nextLine();
                        
                        usuario = csvManager.validarUsuario("C:\\Users\\sebas\\OneDrive\\Documentos\\NetBeansProjects\\MenuVentaEntradasV2\\src\\main\\java\\usuarios.csv", rut, contrasena);
                        if (usuario == null || !rut.endsWith("(admin)")) {
                            System.out.println("No tienes permisos para realizar esta acción.");
                            break;
                        }
                        
                        System.out.println("Por favor, introduce el RUT del usuario que deseas eliminar:");
                        String rutUsuario = scanner.nextLine();
                        
                        csvManager.eliminarUsuario("C:\\Users\\sebas\\OneDrive\\Documentos\\NetBeansProjects\\MenuVentaEntradasV2\\src\\main\\java\\usuarios.csv", rutUsuario);
                        System.out.println("Usuario eliminado con éxito.");
                        break;
                        
                    case "10":
                        try {
                            csvManager.agregarEntradas(scanner, "C:\\Users\\sebas\\OneDrive\\Documentos\\NetBeansProjects\\MenuVentaEntradasV2\\src\\main\\java\\usuarios.csv", eventos);
                        } catch (EventoNoEncontradoException e) {
                            System.out.println(e.getMessage());
                        } catch (EntradaInvalidaException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                        
                    case "11":
                        System.out.println("Por favor, introduce tu RUT:");
                        rut = scanner.nextLine();
                        System.out.println("Por favor, introduce tu contraseña:");
                        contrasena = scanner.nextLine();
                        usuario = csvManager.validarUsuario("C:\\Users\\sebas\\OneDrive\\Documentos\\NetBeansProjects\\MenuVentaEntradasV2\\src\\main\\java\\usuarios.csv", rut, contrasena);
                        if (usuario != null && rut.endsWith("(admin)")) {
                            System.out.println("Generando reporte de eventos...");
                            try (PrintWriter writer = new PrintWriter(new FileWriter("reporte_eventos.txt"))) {
                                writer.println("Reporte de Eventos");
                                writer.println("------------------");
                                for (Iterator<Evento> it = eventos.values().iterator(); it.hasNext();) {
                                    evento = it.next();
                                    writer.println("ID: " + evento.getId());
                                    writer.println("Nombre: " + evento.getNombre());
                                    writer.println("Ubicación: " + evento.getUbicacion());
                                    writer.println("Entradas restantes: " + evento.getEntradasRestantes());
                                    writer.println("Precio de entrada: " + evento.getPrecioDeEntrada());
                                    writer.println();
                                }
                                System.out.println("Reporte de eventos generado correctamente.");
                            } catch (IOException e) {
                                System.out.println("Error al generar el reporte de eventos: " + e.getMessage());
                            }
                            
                            System.out.println("Generando reporte de usuarios...");
                            try (PrintWriter writer = new PrintWriter(new FileWriter("reporte_usuarios.txt"))) {
                                writer.println("Reporte de Usuarios");
                                writer.println("-------------------");
                                try (Scanner lector = new Scanner(new File("C:\\Users\\sebas\\OneDrive\\Documentos\\NetBeansProjects\\MenuVentaEntradasV2\\src\\main\\java\\usuarios.csv"))) {
                                    lector.nextLine(); // Saltar la primera línea (encabezados)
                                    while (lector.hasNextLine()) {
                                        String[] datosUsuario = lector.nextLine().split(",");
                                        rutUsuario = datosUsuario[0];
                                        String contrasenaUsuario = datosUsuario[1];
                                        writer.println("RUT: " + rutUsuario);
                                        writer.println("Contraseña: " + contrasenaUsuario);
                                        writer.println();
                                    }
                                }
                                System.out.println("Reporte de usuarios generado correctamente.");
                            } catch (FileNotFoundException e) {
                                System.out.println("Error al generar el reporte de usuarios: " + e.getMessage());
                            } catch (IOException e) {
                                System.out.println("Error al generar el reporte de usuarios: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Error: RUT o contraseña incorrectos o no tienes permisos de administrador.");
                        }
                        break;
                    case "0":
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción no reconocida. Por favor, intenta de nuevo.");
                        break;
                }
            }
        }
    }
    
    public static void mostrarEventos(HashMap<String, Evento> eventos) {
        for (Evento evento : eventos.values()) {
            System.out.println("ID: " + evento.getId());
            System.out.println("Nombre: " + evento.getNombre());
            System.out.println("Ubicación: " + evento.getUbicacion());
            System.out.println("Entradas restantes: " + evento.getEntradasRestantes());
            System.out.println("Precio de entrada: " + evento.getPrecioDeEntrada());
            System.out.println();
        }
    }

    public static void mostrarEventos(HashMap<String, Evento> eventos, String palabraClave) {
        for (Evento evento : eventos.values()) {
            if (evento.getNombre().toLowerCase().contains(palabraClave.toLowerCase())) {
                System.out.println("ID: " + evento.getId());
                System.out.println("Nombre: " + evento.getNombre());
                System.out.println("Ubicación: " + evento.getUbicacion());
                System.out.println("Entradas restantes: " + evento.getEntradasRestantes());
                System.out.println("Precio de entrada: " + evento.getPrecioDeEntrada());
                System.out.println();
            }
        }
    }
}
