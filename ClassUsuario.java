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
