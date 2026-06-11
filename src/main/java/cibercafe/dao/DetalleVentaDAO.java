package cibercafe.dao;

import cibercafe.modelo.DetalleVenta;
import cibercafe.util.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentaDAO {
    
       public void insertar(DetalleVenta det) {
       try  {
        String sql = "INSERT INTO detalleventa (id_venta, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
        
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        prep.setInt(1, det.getIdVenta());
        prep.setInt(2, det.getIdProducto());
        prep.setInt(3, det.getCantidad());
        prep.setDouble(4, det.getPrecioUnitario());
        prep.executeUpdate(); 
        } catch (SQLException e) {
           System.out.println("Hubo un error al ingresar los detalles de las ventas: " + e.getMessage());
       }
    }
    
    public List<DetalleVenta> obtenerTodos(){
    
        List<DetalleVenta> dv = new ArrayList<>();
        try {
        String sql = "SELECT * FROM detalleventa";
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        ResultSet res = prep.executeQuery();
        while (res.next()){
            
            DetalleVenta dtv = new DetalleVenta();
            dtv.setId(res.getInt("id"));
            dtv.setIdVenta(res.getInt("id_venta"));
            dtv.setIdProducto(res.getInt("id_producto"));
            dtv.setCantidad(res.getInt("cantidad"));
            dtv.setPrecioUnitario(res.getDouble("precio_unitario"));
            
            dv.add(dtv);
         }
        } catch (SQLException e) {
            System.out.println("Hubo un error al obtener los detalles de las ventas: " + e.getMessage());
        }
        return dv;  
    }
    
    public DetalleVenta buscarPorId(int id){
        DetalleVenta d = null;
        try {
            String sql = "SELECT * FROM detalleventa WHERE id = ?";
            Connection conec = Conexion.getConexion();
            PreparedStatement prep = conec.prepareStatement(sql);
            prep.setInt(1, id);
            ResultSet res = prep.executeQuery();
            if (res.next()){
            d = new DetalleVenta();
            d.setId(res.getInt("id"));
            d.setIdVenta(res.getInt("id_venta"));
            d.setIdProducto(res.getInt("id_producto"));
            d.setCantidad(res.getInt("cantidad"));
            d.setPrecioUnitario(res.getDouble("precio_unitario"));
           }  
            
          } catch (SQLException e){
                    System.out.println("No se encontraron ninguna detalles de ventas con el id indicado: " + e.getMessage());
            }
        return d;
    }
    
    public void actualizar(DetalleVenta det){
        try{
            String sql = "UPDATE detalleventa SET id_venta = ?, id_producto = ?, cantidad = ?, precio_unitario = ? WHERE id = ?";
            
            Connection conec = Conexion.getConexion();
            PreparedStatement prep = conec.prepareStatement(sql);
            prep.setInt(1, det.getIdVenta());
            prep.setInt(2, det.getIdProducto());
            prep.setInt(3, det.getCantidad());
            prep.setDouble(4, det.getPrecioUnitario());
            prep.setInt(5, det.getId());
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se pudo actualizar el detalle de la venta: " + e.getMessage());
        }
    }
    
    public void eliminar(int id){  
        try {
        String sql = "DELETE FROM detalleventa WHERE id = ?";
        
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        prep.setInt(1, id);
        prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se pudo eliminar el detalle de la venta: " + e.getMessage());
        }
    }
    
}
//Parte hecha por Fabri 