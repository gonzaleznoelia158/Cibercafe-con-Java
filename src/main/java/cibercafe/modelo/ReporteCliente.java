package cibercafe.modelo;

import java.sql.Timestamp;

public class ReporteCliente {
    private String nombreCliente;
    private int numeroPc;
    private Timestamp fechaSesion;
    private int minutosUsados;
    private double totalPagado;

    public ReporteCliente(String nombreCliente, int numeroPc, Timestamp fechaSesion, int minutosUsados, double totalPagado) {
        this.nombreCliente = nombreCliente;
        this.numeroPc = numeroPc;
        this.fechaSesion = fechaSesion;
        this.minutosUsados = minutosUsados;
        this.totalPagado = totalPagado;
    }

    public String getNombreCliente() { return nombreCliente; }
    public int getNumeroPc() { return numeroPc; }
    public Timestamp getFechaSesion() { return fechaSesion; }
    public int getMinutosUsados() { return minutosUsados; }
    public double getTotalPagado() { return totalPagado; }
}