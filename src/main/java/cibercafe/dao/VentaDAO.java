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
    
    public void insertar(Venta ventas) {
       try  {
        String sql = "INSERT INTO venta (id_cliente, fecha, total) VALUES (?, ?, ?)";
        
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        prep.setInt(1, ventas.getIdCliente());
        prep.setTimestamp(2, java.sql.Timestamp.valueOf(ventas.getFecha()));
        prep.setDouble(3, ventas.getTotal());
        prep.executeUpdate(); 
        } catch (SQLException e) {
           System.out.println("Hubo un error con la venta: " + e.getMessage());
       }
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
    
}
//Parte hecha por Fabri
