import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Clase para representar un evento
class Evento {
    private String nombre;
    private String fecha;
    private int capacidad;
    private String descripcion;
    private String lugar;
    private String hora;
    private String artistasInvitados;
    private int entradasDisponibles;

    // Constructor
    public Evento(String nombre, String fecha, int capacidad, String descripcion, String lugar, String hora, String artistasInvitados, int entradasDisponibles) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.hora = hora;
        this.artistasInvitados = artistasInvitados;
        this.entradasDisponibles = entradasDisponibles;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    // Sobrecarga del método setCapacidad para permitir especificar también la disponibilidad de entradas
    public void setCapacidad(int capacidad, int entradasDisponibles) {
        this.capacidad = capacidad;
        this.entradasDisponibles = entradasDisponibles;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getArtistasInvitados() {
        return artistasInvitados;
    }

    public void setArtistasInvitados(String artistasInvitados) {
        this.artistasInvitados = artistasInvitados;
    }

    public int getEntradasDisponibles() {
        return entradasDisponibles;
    }

    public void setEntradasDisponibles(int entradasDisponibles) {
        this.entradasDisponibles = entradasDisponibles;
    }
}

// Clase para representar un usuario
class Usuario {
    private String nombreUsuario;
    private String contrasena;
    private String email;

    public Usuario(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.email = email;
    }

    // Getters y setters
      public String getNombreUsuario() {
          return nombreUsuario;
      }

      public void setNombreUsuario(String nombreUsuario) {
          this.nombreUsuario = nombreUsuario;
      }

      public String getContrasena() {
          return contrasena;
      }

      // Sobrecarga del método setContrasena para aceptar contraseña y correo electrónico, esto hace posible agregar la opción de cambiar contraseña y/o email de algún usuario.
      public void setContrasena(String contrasena, String email) {
          this.contrasena = contrasena;
          this.email = email;
      }

      public String getEmail() {
          return email;
      }

      public void setEmail(String email) {
          this.email = email;
      }
    
}


// Clase principal para el sistema de venta de entradas
public class MenuVentaEntradas {
    private ArrayList<Evento> eventos;
    private Map<String, Usuario> usuariosRegistrados;

    // Constructor
    public MenuVentaEntradas() {
        eventos = new ArrayList<>();
        usuariosRegistrados = new HashMap<>();
    }

    // Método para agregar un evento al sistema
    public void agregarEvento(Evento evento) {
        eventos.add(evento);
    }

    // Método para agregar un usuario al sistema
    public void agregarUsuario(Usuario usuario) {
        usuariosRegistrados.put(usuario.getNombreUsuario(), usuario);
    }

    // Método para autenticar a un usuario
    public boolean autenticarUsuario(String nombreUsuario, String contrasena) {
        if (usuariosRegistrados.containsKey(nombreUsuario)) {
            Usuario usuario = usuariosRegistrados.get(nombreUsuario);
            return usuario.getContrasena().equals(contrasena);
        }
        return false;
    }

    // Método para mostrar el listado de eventos
    public void mostrarEventos() {
        System.out.println("Listado de eventos:");
        for (int i = 0; i < eventos.size(); i++) {
            System.out.println((i + 1) + ". " + eventos.get(i).getNombre());
        }
    }

    // Método para ver la disponibilidad de entradas para un evento específico
    public void verDisponibilidadEntradas() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Mostrar la lista de eventos
        mostrarEventos();
        System.out.print("Ingrese el número de evento para ver la disponibilidad de entradas: ");
        int opcionEvento = Integer.parseInt(reader.readLine());

        if (opcionEvento > 0 && opcionEvento <= eventos.size()) {
            Evento eventoSeleccionado = eventos.get(opcionEvento - 1);
            System.out.println("Disponibilidad de entradas para el evento " + eventoSeleccionado.getNombre() + ":");
            System.out.println("Entradas disponibles: " + eventoSeleccionado.getEntradasDisponibles());
        } else {
            System.out.println("Número de evento no válido.");
        }
    }

    // Método para ver los detalles de un evento
    public void verDetallesEvento() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Mostrar la lista de eventos
        mostrarEventos();
        System.out.print("Ingrese el número de evento para ver los detalles: ");
        int opcionEvento = Integer.parseInt(reader.readLine());

        if (opcionEvento > 0 && opcionEvento <= eventos.size()) {
            Evento eventoSeleccionado = eventos.get(opcionEvento - 1);
            System.out.println("Detalles del evento " + eventoSeleccionado.getNombre() + ":");
            System.out.println("Fecha: " + eventoSeleccionado.getFecha());
            System.out.println("Lugar: " + eventoSeleccionado.getLugar());
            System.out.println("Hora: " + eventoSeleccionado.getHora());
            System.out.println("Descripción: " + eventoSeleccionado.getDescripcion());
            System.out.println("Artistas invitados: " + eventoSeleccionado.getArtistasInvitados());
        } else {
            System.out.println("Número de evento no válido.");
        }
    }

    // Método para comprar entradas para un evento
    public void comprarEntradas() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Mostrar la lista de eventos
        mostrarEventos();
        System.out.print("Ingrese el número de evento para comprar entradas: ");
        int opcionEvento = Integer.parseInt(reader.readLine());

        if (opcionEvento > 0 && opcionEvento <= eventos.size()) {
            Evento eventoSeleccionado = eventos.get(opcionEvento - 1);
            System.out.println("Comprando entradas para el evento " + eventoSeleccionado.getNombre() + ":");

            // Verificar disponibilidad de entradas
            if (eventoSeleccionado.getEntradasDisponibles() > 0) {
                System.out.print("Ingrese la cantidad de entradas que desea comprar: ");
                int cantidadEntradas = Integer.parseInt(reader.readLine());

                if (cantidadEntradas > 0 && cantidadEntradas <= eventoSeleccionado.getEntradasDisponibles()) {
                    // Realizar la compra
                    eventoSeleccionado.setEntradasDisponibles(eventoSeleccionado.getEntradasDisponibles() - cantidadEntradas);
                    System.out.println("¡Compra realizada con éxito!");
                    System.out.println("Entradas restantes: " + eventoSeleccionado.getEntradasDisponibles());
                } else {
                    System.out.println("Cantidad de entradas no válida.");
                }
            } else {
                System.out.println("Lo sentimos, no hay entradas disponibles para este evento.");
            }
        } else {
            System.out.println("Número de evento no válido.");
        }
    }

    // Método para agregar un evento al sistema (con autenticación de usuario)
    public void agregarEventoAutenticado() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Autenticar al usuario
        System.out.print("Ingrese su nombre de usuario: ");
        String nombreUsuario = reader.readLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = reader.readLine();

        if (!autenticarUsuario(nombreUsuario, contrasena)) {
            System.out.println("Nombre de usuario o contraseña incorrectos. No tiene permiso para agregar eventos.");
            return;
        }

        // Pedir datos del evento
        System.out.println("\nIngrese los datos del nuevo evento:");
        System.out.print("Nombre del evento: ");
        String nombreEvento = reader.readLine();
        System.out.print("Fecha del evento (YYYY-MM-DD): ");
        String fechaEvento = reader.readLine();
        System.out.print("Capacidad del evento: ");
        int capacidadEvento = Integer.parseInt(reader.readLine());
        System.out.print("Descripción del evento: ");
        String descripcionEvento = reader.readLine();
        System.out.print("Lugar del evento: ");
        String lugarEvento = reader.readLine();
        System.out.print("Hora del evento: ");
        String horaEvento = reader.readLine();
        System.out.print("Artistas invitados: ");
        String artistasInvitados = reader.readLine();
        System.out.print("Entradas disponibles: ");
        int entradasDisponibles = Integer.parseInt(reader.readLine());

        // Crear el nuevo evento y agregarlo a la lista
        Evento nuevoEvento = new Evento(nombreEvento, fechaEvento, capacidadEvento, descripcionEvento, lugarEvento, horaEvento, artistasInvitados, entradasDisponibles);
        agregarEvento(nuevoEvento);

        System.out.println("Evento agregado con éxito.");
    }

    // Método para mostrar el menú de la aplicación
    public void mostrarMenu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int opcion;

        do {
            System.out.println("\nMenú:");
            System.out.println("1. Ver lista de eventos");
            System.out.println("2. Ver disponibilidad de entradas para un evento");
            System.out.println("3. Ver detalles de un evento");
            System.out.println("4. Comprar entradas para un evento");
            System.out.println("5. Registrar usuario");
            System.out.println("6. Iniciar sesión");
            System.out.println("7. Agregar evento");
            System.out.println("8. Salir");
            System.out.print("Ingrese el número de la opción deseada: ");

            opcion = Integer.parseInt(reader.readLine());

            switch (opcion) {
                case 1:
                    mostrarEventos();
                    break;
                case 2:
                    verDisponibilidadEntradas();
                    break;
                case 3:
                    verDetallesEvento();
                    break;
                case 4:
                    comprarEntradas();
                    break;
                case 5:
                    registrarUsuario();
                    break;
                case 6:
                    iniciarSesion();
                    break;
                case 7:
                    agregarEventoAutenticado();
                    break;
                case 8:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese un número válido.");
            }
        } while (opcion != 8);
    }

    // Método para registrar un nuevo usuario
    public void registrarUsuario() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\nRegistro de Usuario:");
        System.out.print("Ingrese el nombre de usuario: ");
        String nombreUsuario = reader.readLine();
        System.out.print("Ingrese la contraseña: ");
        String contrasena = reader.readLine();

        Usuario nuevoUsuario = new Usuario(nombreUsuario, contrasena);
        agregarUsuario(nuevoUsuario);

        System.out.println("Usuario registrado con éxito.");
    }

    // Método para iniciar sesión de usuario
    public void iniciarSesion() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\nIniciar Sesión:");
        System.out.print("Ingrese el nombre de usuario: ");
        String nombreUsuario = reader.readLine();
        System.out.print("Ingrese la contraseña: ");
        String contrasena = reader.readLine();

        if (autenticarUsuario(nombreUsuario, contrasena)) {
            System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + nombreUsuario + "!");
        } else {
            System.out.println("Inicio de sesión fallido. Nombre de usuario o contraseña incorrectos.");
        }
    }

    public static void main(String[] args) {
        MenuVentaEntradas menu = new MenuVentaEntradas();

        // Datos iniciales ejemplo
        Evento concierto = new Evento("Concierto", "2024-04-10", 1000, "Concierto en vivo", "Estadio Nacional", "20:00", "Artista1, Artista2", 500);
        Evento teatro = new Evento("Teatro", "2024-05-15", 500, "Obra de teatro", "Teatro Municipal", "18:30", "Actor1, Actor2", 200);

        // Agregar eventos al sistema
        menu.agregarEvento(concierto);
        menu.agregarEvento(teatro);

        // Mostrar el menú
        try {
            menu.mostrarMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
