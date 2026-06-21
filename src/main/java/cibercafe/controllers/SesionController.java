package cibercafe.controllers;

import cibercafe.dao.ComputadoraDAO;
import cibercafe.dao.SesionDAO;
import cibercafe.modelo.Sesion;
import java.time.LocalDateTime;
import java.util.List;

public class SesionController {
    
    private SesionDAO sesionDAO;
    private ComputadoraDAO compuDAO;
    
    public SesionController(){
        this.sesionDAO = new SesionDAO();
        this.compuDAO = new ComputadoraDAO();
    }
    
    public int iniciarSesion(int idCliente, int idComputadora, int horasAlquilar, double precio) {
        LocalDateTime horaInicio = LocalDateTime.now(java.time.ZoneId.of("America/Argentina/Buenos_Aires"));
        LocalDateTime horaFin = horaInicio.plusHours(horasAlquilar);
        
        Sesion se = new Sesion();
        se.setIdCliente(idCliente);
        se.setIdComputadora(idComputadora);
        se.setHoraInicio(horaInicio);
        se.setHoraFin(horaFin);
        se.setEstado("activa");
        se.setCostoTotal(precio);
        int idSesion = sesionDAO.insertar(se);
        
        compuDAO.actualizarEstado(idComputadora, "activa");
        return idSesion;
    }
    public void cerrarSesion(int idSesion, int idComputadora) {
    sesionDAO.cerrarSesion(idSesion);
    compuDAO.actualizarEstado(idComputadora, "libre");
}
    public void liberarSesionesVencidas() {
    List<Sesion> vencidas = sesionDAO.obtenerVencidas();
    for (Sesion s : vencidas) {
        sesionDAO.cerrarSesion(s.getId());
        compuDAO.actualizarEstado(s.getIdComputadora(), "libre");
    }
}
}
