package cibercafe.modelo;

public class Computadora {
    private int id;
    private String nombre;
    private int idCategoria;
    private String estado;

    public Computadora() {}

    public Computadora(int id, String nombre, int idCategoria, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.idCategoria = idCategoria;
        this.estado = estado;
    }

    public int getId(){
        return id; 
    }
    
    public String getNombre(){
        return nombre; 
    }
    
    public int getIdCategoria(){
        return idCategoria; 
    }
    
    public String getEstado() {
    return estado;
    }
    public void setId(int id){
        this.id = id; 
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre; 
    }
    
    public void setIdCategoria(int idCategoria){
        this.idCategoria = idCategoria; 
    }
    
    public void setEstado(String estado) {
    this.estado = estado;
    }
}