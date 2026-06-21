package cibercafe.modelo;

public class InformePC {
    private String numeroPc;
    private int cantidadAlquileres;
    private int totalMinutos;
    private double ingresosGenerados;

    public InformePC(String numeroPc, int cantidadAlquileres, int totalMinutos, double ingresosGenerados) {
        this.numeroPc = numeroPc;
        this.cantidadAlquileres = cantidadAlquileres;
        this.totalMinutos = totalMinutos;
        this.ingresosGenerados = ingresosGenerados;
    }

    public String getNumeroPc() { return numeroPc; }
    public int getCantidadAlquileres() { return cantidadAlquileres; }
    public int getTotalMinutos() { return totalMinutos; }
    public double getIngresosGenerados() { return ingresosGenerados; }
}