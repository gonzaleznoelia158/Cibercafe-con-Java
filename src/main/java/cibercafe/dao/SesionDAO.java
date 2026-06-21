package cibercafe.dao;

import cibercafe.modelo.Sesion;
import cibercafe.util.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SesionDAO {
    
        public int insertar(Sesion s) {
            int idGenerado = -1;
       try  {
        String sql = "INSERT INTO sesion (id_cliente, id_computadora, hora_inicio, hora_fin, estado, costo_total) VALUES (?, ?, ?, ?, ?, ?)";
        
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        prep.setInt(1, s.getIdCliente());
        prep.setInt(2, s.getIdComputadora());
        prep.setTimestamp(3, java.sql.Timestamp.valueOf(s.getHoraInicio()));
        prep.setTimestamp(4, java.sql.Timestamp.valueOf(s.getHoraFin()));
        prep.setString(5, s.getEstado());
        prep.setDouble(6, s.getCostoTotal());
        prep.executeUpdate(); 
        ResultSet rs = prep.getGeneratedKeys();
        if (rs.next()) {
        idGenerado = rs.getInt(1);
       }
        } catch (SQLException e) {
           System.out.println("Hubo un error al insertar los datos de sesion: " + e.getMessage());
       }
       return idGenerado;
    }
    
    public List<Sesion> obtenerTodos(){
    
        List<Sesion> ses = new ArrayList<>();
        try {
        String sql = "SELECT * FROM sesion";
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        ResultSet res = prep.executeQuery();
        while (res.next()){
            
            Sesion sess = new Sesion();
            sess.setId(res.getInt("id"));
            sess.setIdCliente(res.getInt("id_cliente"));
            sess.setIdComputadora(res.getInt("id_computadora"));
            sess.setHoraInicio(res.getTimestamp("hora_inicio").toLocalDateTime());
            sess.setHoraFin(res.getTimestamp("hora_fin").toLocalDateTime());
            sess.setEstado(res.getString("estado"));
            sess.setCostoTotal(res.getDouble("costo_total"));
            
            ses.add(sess);
         }
        } catch (SQLException e) {
            System.out.println("Hubo un error al obtener las sesiones: " + e.getMessage());
        }
        return ses;  
    }
    
    public Sesion buscarPorId(int id){
        Sesion se = null;
        try {
            String sql = "SELECT * FROM sesion WHERE id = ?";
            Connection conec = Conexion.getConexion();
            PreparedStatement prep = conec.prepareStatement(sql);
            prep.setInt(1, id);
            ResultSet res = prep.executeQuery();
            if (res.next()){
            se = new Sesion();
            se.setId(res.getInt("id"));
            se.setIdCliente(res.getInt("id_cliente"));
            se.setIdComputadora(res.getInt("id_computadora"));
            se.setHoraInicio(res.getTimestamp("hora_inicio").toLocalDateTime());
            se.setHoraFin(res.getTimestamp("hora_fin").toLocalDateTime());
            se.setEstado(res.getString("estado"));
            se.setCostoTotal(res.getDouble("costo_total"));
           }  
            
          } catch (SQLException e){
                    System.out.println("No se encontró ninguna sesion con el id ingresado: " + e.getMessage());
            }
        return se;
    }
    
    public void actualizar(Sesion s){
        try{
            String sql = "UPDATE sesion SET id_cliente = ?, id_computadora = ?, hora_inicio = ?, hora_fin = ?, estado = ? WHERE id = ?";
            
            Connection conec = Conexion.getConexion();
            PreparedStatement prep = conec.prepareStatement(sql);
            prep.setInt(1, s.getIdCliente());
            prep.setInt(2, s.getIdComputadora());
            prep.setTimestamp(3, java.sql.Timestamp.valueOf(s.getHoraInicio()));
            prep.setTimestamp(4, java.sql.Timestamp.valueOf(s.getHoraFin()));
            prep.setString(5, s.getEstado());
            prep.setDouble(6, s.getCostoTotal());
            prep.setInt(7, s.getId());
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se pudo actualizar la sesion: " + e.getMessage());
        }
    }
    
    public void eliminar(int id){  
        try {
        String sql = "DELETE FROM sesion WHERE id = ?";
        
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        prep.setInt(1, id);
        prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se pudo eliminar la sesion: " + e.getMessage());
        }
    }
    
    public List<Sesion> obtenerActivas() {
    List<Sesion> ses = new ArrayList<>();
    try {
        String sql = "SELECT * FROM sesion WHERE estado = 'activa'";
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        ResultSet res = prep.executeQuery();
        while (res.next()) {
            Sesion sess = new Sesion();
            sess.setId(res.getInt("id"));
            sess.setIdCliente(res.getInt("id_cliente"));
            sess.setIdComputadora(res.getInt("id_computadora"));
            sess.setHoraInicio(res.getTimestamp("hora_inicio").toLocalDateTime());
            if (res.getTimestamp("hora_fin") != null) {
            sess.setHoraFin(res.getTimestamp("hora_fin").toLocalDateTime());}
            sess.setEstado(res.getString("estado"));
            sess.setCostoTotal(res.getDouble("costo_total"));
            ses.add(sess);
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener sesiones activas: " + e.getMessage());
    }
    return ses;
}
    
    public List<Sesion> obtenerVencidas() {
    List<Sesion> ses = new ArrayList<>();
    try {
        String sql = "SELECT * FROM sesion WHERE estado = 'activa' AND hora_fin < NOW()";
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        ResultSet res = prep.executeQuery();
        while (res.next()) {
            Sesion sess = new Sesion();
            sess.setId(res.getInt("id"));
            sess.setIdComputadora(res.getInt("id_computadora"));
            ses.add(sess);
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener sesiones vencidas: " + e.getMessage());
    }
    return ses;
}
   
    public void cerrarSesion(int id) {
    try {
        String sql = "UPDATE sesion SET estado = 'cerrada' WHERE id = ?";
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        prep.setInt(1, id);
        prep.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error al cerrar sesión: " + e.getMessage());
    }
}
    
}
//Parte hecha por Fabri