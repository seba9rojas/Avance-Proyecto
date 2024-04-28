class Usuario {
    private String rut;
    private String contrasena;

    public Usuario(String rut, String contrasena) {
        this.rut = rut;
        this.contrasena = contrasena;
    }

    // Getters y setters
      public String getRut() {
          return nombreUsuario;
      }

      public void setRut(String rut) {
          this.rut = rut;
      }

      public String getContrasena() {
          return contrasena;
      }

      public void setContrasena(String contrasena) {
          this.contrasena = contrasena;
      }
}
