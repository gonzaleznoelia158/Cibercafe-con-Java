package cibercafe.modelo;


public class Categoria {
    
    private int id;
    private String nombre;
    private double precioHora;
    
    public Categoria(){}
    
    public Categoria(int id, String nombre, double precioHora) {
        this.id = id;
        this.nombre = nombre;
        this.precioHora = precioHora;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public double getPrecioHora() {
        return precioHora;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setPrecioHora(double precioHora) {
        this.precioHora = precioHora;
    }
}
