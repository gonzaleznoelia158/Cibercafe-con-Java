package cibercafe.modelo;

public class Usuario {
    private int id;
    private String nombreUsuario;
    private String contrasenia;
    private String rol;

    public Usuario() {}

    public Usuario(int id, String nombreUsuario, String contrasenia, String rol) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public int getId() {
        return id; 
    }
    
    public String getNombreUsuario() {
        return nombreUsuario; 
    }
    
    public String getContrasenia() {
        return contrasenia; 
    }
    
    public String getRol() {
        return rol; 
    }
    
    public void setId(int id) {
        this.id = id; 
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario; 
    }
    
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia; 
    }
    
    public void setRol(String rol) {
        this.rol = rol; 
    }
}
