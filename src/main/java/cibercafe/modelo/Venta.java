package cibercafe.modelo;

import java.time.LocalDateTime;

public class Venta {

    private int id;
    private int idCliente;
    private int idSesion;
    private LocalDateTime fecha;
    private double total;

    public Venta() {}

    public Venta(int id, int idCliente, LocalDateTime fecha, double total) {
        this.id = id;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }
    
    public int getIdSesion() {
    return idSesion;
    }
    
    public LocalDateTime getFecha() {
        return fecha;
    }
    
    public double getTotal() {
        return total;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    public void setIdSesion(int idSesion) {
    this.idSesion = idSesion;
    }
    
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

//Parte hecha por Fabri 