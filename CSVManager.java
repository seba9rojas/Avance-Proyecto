import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// Clase CSVManager
public class CSVManager {
    private static final String CODIGO_SEGURIDAD = "1234";
    private CollectionManager collectionManager;

    public CSVManager(CollectionManager cm) {
        collectionManager = cm;
    }

    public HashMap<String, Evento> lecturaEventos(String direccionArchivo) throws FileNotFoundException {
        HashMap<String, Evento> mapaEventos = new HashMap<>();
        File archivoEventos = new File(direccionArchivo);
        Scanner lector = new Scanner(archivoEventos);
        lector.nextLine();

        while (lector.hasNextLine()) {
            String dataLine = lector.nextLine();
            String[] dataArray = dataLine.split(",");

            if (dataArray.length < 4) {
                // Esta línea no tiene suficientes campos, así que la saltamos
                continue;
            }

            String id = dataArray[0];
            String nombre = dataArray[1];
            String ubicacion = dataArray[2];
            int entradasRestantes = Integer.parseInt(dataArray[3]);
            int precioDeEntrada = Integer.parseInt(dataArray[4]);

        
            Evento evento = new Evento();
            evento.setId(id);
            evento.setNombre(nombre);
            evento.setUbicacion(ubicacion);
        
            evento.setEntradasRestantes(entradasRestantes);
            evento.setPrecioDeEntrada(precioDeEntrada);
        
            for (int i = 0; i < entradasRestantes; i++) {
                Entrada entrada = new Entrada();
                entrada.setId(String.valueOf(i + 1));
                entrada.setDisponible(true);
                evento.getEntradas().add(entrada);
            }

            mapaEventos.put(id, evento);
        }
        return mapaEventos;
    }
    
    
    public void guardarEventos(String direccionArchivo, HashMap<String, Evento> eventos) {
        try (PrintWriter writer = new PrintWriter(new File(direccionArchivo))) {
            writer.println("id,nombre,ubicacion,entradasRestantes,precioDeEntrada");

            for (Evento evento : eventos.values()) {
                writer.println(evento.getId() + "," + evento.getNombre() + "," + evento.getUbicacion() + "," + evento.getEntradasRestantes() + "," + evento.getPrecioDeEntrada());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void registrarUsuario(String direccionArchivo, Usuario usuario) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File(direccionArchivo), true))) {
            writer.println(usuario.getRut() + "," + usuario.getContrasena());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void registrarUsuario(String direccionArchivo, Usuario usuario, String codigoSeguridad) {
        if (!CODIGO_SEGURIDAD.equals(codigoSeguridad)) {
            System.out.println("Código de seguridad incorrecto.");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File(direccionArchivo), true))) {
            writer.println(usuario.getRut() + "(admin)," + usuario.getContrasena());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public Usuario validarUsuario(String direccionArchivo, String rut, String contrasena) {
    try (Scanner lector = new Scanner(new File(direccionArchivo))) {
        lector.nextLine();

        while (lector.hasNextLine()) {
            String[] datosUsuario = lector.nextLine().split(",");
            if (datosUsuario[0].equals(rut) && datosUsuario[1].equals(contrasena)) {
                Usuario usuario = new Usuario();
                usuario.setRut(rut);
                usuario.setContrasena(contrasena);
                return usuario;
            }
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    
    return null;
    }
    
    public void eliminarUsuario(String direccionArchivo, String rut) {
        try {
            File archivoUsuarios = new File(direccionArchivo);
            File archivoTemporal = new File("temp.csv");

            BufferedReader reader = new BufferedReader(new FileReader(archivoUsuarios));
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTemporal));

            String lineaActual;

            while ((lineaActual = reader.readLine()) != null) {
                if (lineaActual.split(",")[0].equals(rut)) continue;
                writer.write(lineaActual + System.getProperty("line.separator"));
            }
            writer.close(); 
            reader.close(); 

            archivoUsuarios.delete();
            archivoTemporal.renameTo(archivoUsuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void agregarEntradas(Scanner scanner, String direccionArchivo, HashMap<String, Evento> eventos) throws EventoNoEncontradoException, EntradaInvalidaException {
        System.out.println("Por favor, introduce tu RUT:");
        String rut = scanner.nextLine();
        System.out.println("Por favor, introduce tu contraseña:");
        String contrasena = scanner.nextLine();

        Usuario usuario = validarUsuario("C:\\Users\\sebas\\OneDrive\\Documentos\\NetBeansProjects\\MenuVentaEntradasV2\\src\\main\\java\\usuarios.csv", rut, contrasena);
        if (usuario == null || !rut.endsWith("(admin)")) {
            System.out.println("No tienes permisos para realizar esta acción.");
            return;
        }

        System.out.println("Por favor, introduce la ID del evento al que deseas agregar entradas:");
        String idEvento = scanner.nextLine();
        Evento evento = eventos.get(idEvento);
        if (evento == null) {
            throw new EventoNoEncontradoException("No se encontró ningún evento con esa ID.");
        }

        boolean continuar = true;
        while (continuar) {
            System.out.println("Por favor, introduce la ID de la entrada que deseas agregar:");
            String idEntrada = scanner.nextLine();

            // Verificar si la entrada ya existe
            for (Entrada entrada : evento.getEntradas()) {
                if (entrada.getId().equals(idEntrada)) {
                    throw new EntradaInvalidaException("Ya existe una entrada con esa ID.");
                }
            }

            Entrada nuevaEntrada = new Entrada();
            nuevaEntrada.setId(idEntrada);
            nuevaEntrada.setDisponible(true);

            evento.getEntradas().add(nuevaEntrada);
            evento.setEntradasRestantes(evento.getEntradasRestantes() + 1);

            System.out.println("¿Deseas agregar otra entrada? (s/n)");
            String respuesta = scanner.nextLine();
            continuar = respuesta.equalsIgnoreCase("s");
        }

        guardarEventos("C:\\Users\\sebas\\OneDrive\\Documentos\\NetBeansProjects\\MenuVentaEntradasV2\\src\\main\\java\\eventos.csv", eventos);
        System.out.println("Entradas agregadas con éxito.");
    }
    
}
