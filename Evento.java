import java.util.ArrayList;
import java.util.List;

public class Evento {
    private String id;
    private String nombre;
    private List<Entrada> entradas; // Colección anidada de entradas
    private String ubicacion;
    private int entradasRestantes;
    private int precioDeEntrada;

    public int getEntradasRestantes() {
        return entradasRestantes;
    }

    public void setEntradasRestantes(int entradasRestantes) {
        this.entradasRestantes = entradasRestantes;
    }

    public int getPrecioDeEntrada() {
        return precioDeEntrada;
    }

    public void setPrecioDeEntrada(int precioDeEntrada) {
        this.precioDeEntrada = precioDeEntrada;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Evento() {
        entradas = new ArrayList<>();
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<Entrada> entradas) {
        this.entradas = entradas;
    }
    
    public List<Entrada> getEntradasDisponibles() {
        List<Entrada> entradasDisponibles = new ArrayList<>();
        for (Entrada entrada : entradas) {
            if (entrada.isDisponible()) {
                entradasDisponibles.add(entrada);
            }
        }
        return entradasDisponibles;
    }
    
    // Método para agregar una entrada existente
    public void agregarEntrada(Entrada entrada) {
        this.entradas.add(entrada);
    }

    // Método para agregar una entrada creando una nueva instancia de Entrada
    public void agregarEntrada(String id, boolean disponible) {
        Entrada nuevaEntrada = new Entrada();
        nuevaEntrada.setId(id);
        nuevaEntrada.setDisponible(disponible);
        this.entradas.add(nuevaEntrada);
    }
}
