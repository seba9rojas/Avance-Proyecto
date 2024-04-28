public class UsuarioPremium extends Usuario {
    private boolean accesoPremium;

    public UsuarioPremium() {
        super();
        this.accesoPremium = false;
    }

    public boolean isAccesoPremium() {
        return accesoPremium;
    }

    public void setAccesoPremium(boolean accesoPremium) {
        this.accesoPremium = accesoPremium;
    }

    @Override
    public void setContrasena(String contrasena) {
        if (contrasena.length() >= 8) {
            super.setContrasena(contrasena);
            this.accesoPremium = true;
        } else {
            System.out.println("La contraseña debe tener al menos 8 caracteres.");
        }
    }

    public void mostrarInfoPremium() {
        System.out.println("Usuario Premium:");
        System.out.println("RUT: " + getRut());
        System.out.println("Acceso Premium: " + isAccesoPremium());
    }
}
