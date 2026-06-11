package cibercafe.modelo;

import java.time.LocalDateTime;

public class Sesion {

    private int id;
    private int idCliente;
    private int idComputadora;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private String estado;
    private double costoTotal;

    public Sesion() {}

    public Sesion(int id, int idCliente, int idComputadora,
                  LocalDateTime horaInicio,
                  LocalDateTime horaFin, String estado,
                  double costoTotal) {

        this.id = id;
        this.idCliente = idCliente;
        this.idComputadora = idComputadora;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
        this.costoTotal = costoTotal;
    }

    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }
    
    public int getIdComputadora(){
        return idComputadora;
    }
    
    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }
    
    public LocalDateTime getHoraFin() {
        return horaFin;
    }
    
    public String getEstado(){
        return estado;
    }
    
    public double getCostoTotal() {
        return costoTotal;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdComputadora(int idComputadora){
        this.idComputadora = idComputadora;
    }
    
    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(LocalDateTime horaFin) {
        this.horaFin = horaFin;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }
}

//Parte hecha por Fabri 