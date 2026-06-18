package cibercafe.modelo;

public class InformePC {
    private int numeroPc;
    private int cantidadAlquileres;
    private int totalMinutos;
    private double ingresosGenerados;

    public InformePC(int numeroPc, int cantidadAlquileres, int totalMinutos, double ingresosGenerados) {
        this.numeroPc = numeroPc;
        this.cantidadAlquileres = cantidadAlquileres;
        this.totalMinutos = totalMinutos;
        this.ingresosGenerados = ingresosGenerados;
    }

    public int getNumeroPc() { return numeroPc; }
    public int getCantidadAlquileres() { return cantidadAlquileres; }
    public int getTotalMinutos() { return totalMinutos; }
    public double getIngresosGenerados() { return ingresosGenerados; }
}