import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
