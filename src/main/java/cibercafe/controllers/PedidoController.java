package cibercafe.controllers;

import cibercafe.modelo.Venta;
import cibercafe.modelo.DetalleVenta;
import cibercafe.dao.VentaDAO;
import cibercafe.dao.DetalleVentaDAO;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class PedidoController {
    
    private VentaDAO ventaDAO;
    private DetalleVentaDAO detalleVentaDAO;
    
    public PedidoController() {
        this.ventaDAO = new VentaDAO();
        this.detalleVentaDAO = new DetalleVentaDAO();
    }
    
    public void registrarPedido(int idCliente, int idSesion, int idProducto, int cantidad, double precioUnitario) {
        double total = cantidad * precioUnitario;
        
        Venta v = new Venta();
        v.setIdCliente(idCliente);
        v.setIdSesion(idSesion);
        v.setFecha(LocalDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires")));
        v.setTotal(total);
        
        int idVenta = ventaDAO.insertar(v);
        
        DetalleVenta det = new DetalleVenta();
        det.setIdVenta(idVenta);
        det.setIdProducto(idProducto);
        det.setCantidad(cantidad);
        det.setPrecioUnitario(precioUnitario);
        detalleVentaDAO.insertar(det);
    }
    
}
