public class EventoVIP extends Evento {
    private int precioVIP;

    public EventoVIP() {
        super();
    }

    public int getPrecioVIP() {
        return precioVIP;
    }

    public void setPrecioVIP(int precioVIP) {
        this.precioVIP = precioVIP;
    }

    @Override
    public void setPrecioDeEntrada(int precioDeEntrada) {
        super.setPrecioDeEntrada(precioDeEntrada);
        this.precioVIP = precioDeEntrada * 2;
    }

    public void mostrarInfoVIP() {
        System.out.println("Evento VIP:");
        System.out.println("ID: " + getId());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Ubicación: " + getUbicacion());
        System.out.println("Entradas restantes: " + getEntradasRestantes());
        System.out.println("Precio de entrada regular: " + getPrecioDeEntrada());
        System.out.println("Precio de entrada VIP: " + getPrecioVIP());
    }
}
