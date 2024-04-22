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
