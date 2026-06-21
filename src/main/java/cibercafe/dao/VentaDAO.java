package cibercafe.dao;

import cibercafe.modelo.Venta;
import cibercafe.util.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {
    
    public int insertar(Venta ventas) {
    int idGenerado = -1;
    try {
        String sql = "INSERT INTO venta (id_cliente, id_sesion, fecha, total) VALUES (?, ?, ?, ?)";
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        prep.setInt(1, ventas.getIdCliente());
        prep.setInt(2, ventas.getIdSesion());
        prep.setTimestamp(3, java.sql.Timestamp.valueOf(ventas.getFecha()));
        prep.setDouble(4, ventas.getTotal());
        prep.executeUpdate();
        ResultSet rs = prep.getGeneratedKeys();
        if (rs.next()) {
            idGenerado = rs.getInt(1);
        }
    } catch (SQLException e) {
        System.out.println("Hubo un error con la venta: " + e.getMessage());
    }
    return idGenerado;
}
    
    public List<Venta> obtenerTodos(){
    
        List<Venta> vent = new ArrayList<>();
        try {
        String sql = "SELECT * FROM venta";
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        ResultSet res = prep.executeQuery();
        while (res.next()){
            
            Venta ven = new Venta();
            ven.setId(res.getInt("id"));
            ven.setIdCliente(res.getInt("id_cliente"));
            ven.setFecha(res.getTimestamp("fecha").toLocalDateTime());
            ven.setTotal(res.getDouble("total"));
            
            vent.add(ven);
         }
        } catch (SQLException e) {
            System.out.println("Hubo un error al obtener las ventas: " + e.getMessage());
        }
        return vent;  
    }
    
    public Venta buscarPorId(int id){
        Venta v = null;
        try {
            String sql = "SELECT * FROM venta WHERE id = ?";
            Connection conec = Conexion.getConexion();
            PreparedStatement prep = conec.prepareStatement(sql);
            prep.setInt(1, id);
            ResultSet res = prep.executeQuery();
            if (res.next()){
            v = new Venta();
            v.setId(res.getInt("id"));
            v.setIdCliente(res.getInt("id_cliente"));
            v.setFecha(res.getTimestamp("fecha").toLocalDateTime());
            v.setTotal(res.getDouble("total"));
           }  
            
          } catch (SQLException e){
                    System.out.println("No se encontró ninguna venta con el id indicado: " + e.getMessage());
            }
        return v;
    }
    
    public void actualizar(Venta ventas){
        try{
            String sql = "UPDATE venta SET id_cliente = ?, fecha = ?, total =? WHERE id = ?";
            
            Connection conec = Conexion.getConexion();
            PreparedStatement prep = conec.prepareStatement(sql);
            prep.setInt(1, ventas.getIdCliente());
            prep.setTimestamp(2, java.sql.Timestamp.valueOf(ventas.getFecha()));
            prep.setDouble(3, ventas.getTotal());
            prep.setInt(4, ventas.getId());
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se pudo actualizar la venta: " + e.getMessage());
        }
    }
    
    public void eliminar(int id){  
        try {
        String sql = "DELETE FROM venta WHERE id = ?";
        
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        prep.setInt(1, id);
        prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se pudo eliminar la venta: " + e.getMessage());
        }
    }
    
    public double obtenerTotalCafeteriaPorSesion(int idSesion) {
    double total = 0;
    try {
        String sql = "SELECT SUM(total) as total FROM venta WHERE id_sesion = ?";
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        prep.setInt(1, idSesion);
        ResultSet res = prep.executeQuery();
        if (res.next()) {
            total = res.getDouble("total");
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener total de cafetería: " + e.getMessage());
    }
    return total;
}
    
}
//Parte hecha por Fabri
