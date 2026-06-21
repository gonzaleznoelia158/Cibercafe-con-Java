package cibercafe.dao;

import cibercafe.modelo.Producto;
import cibercafe.util.Conexion;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductoDAO {
    
    public void insertar(Producto prod) {
       try  {
        String sql = "INSERT INTO producto (nombre, stock, precio) VALUES (?, ?, ?)";
        
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        prep.setString(1, prod.getNombre());
        prep.setInt(2, prod.getStock());
        prep.setDouble(3, prod.getPrecio());
        prep.executeUpdate(); 
        } catch (SQLException e) {
           System.out.println("Hubo un error al insertar productos: " + e.getMessage());
       } 
    }
     
      public List<Producto> obtenerTodos(){
    
        List<Producto> produ = new ArrayList<>();
        try {
        String sql = "SELECT * FROM producto WHERE activo = true";
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        ResultSet res = prep.executeQuery();
        while (res.next()){
            //crear producto
            Producto pro = new Producto();
            pro.setId(res.getInt("id"));
            pro.setNombre(res.getString("nombre"));
            pro.setStock(res.getInt("stock"));
            pro.setPrecio(res.getDouble("precio"));
            
            produ.add(pro);
         }
        } catch (SQLException e) {
            System.out.println("Hubo un error al listar las computadoras: " + e.getMessage());
        }
        return produ; 
    }
      
      public void desactivar(int id) {
    try {
        String sql = "UPDATE producto SET activo = false WHERE id = ?";
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        prep.setInt(1, id);
        prep.executeUpdate();
    } catch (SQLException e) {
        System.out.println("No se pudo desactivar el producto: " + e.getMessage());
    }
}
      
      public Producto buscarPorId(int id){
        Producto prod = null;
        try {
            String sql = "SELECT * FROM producto WHERE id = ?";
            Connection conec = Conexion.getConexion();
            PreparedStatement prep = conec.prepareStatement(sql);
            prep.setInt(1, id);
            ResultSet res = prep.executeQuery();
            if (res.next()){
            prod = new Producto();
            prod.setId(res.getInt("id"));
            prod.setNombre(res.getString("nombre"));
            prod.setStock(res.getInt("stock"));
            prod.setPrecio(res.getDouble("precio"));
           }  
            
          } catch (SQLException e){
                    System.out.println("No se encontró ningun producto con el id ingresado: " + e.getMessage());
            }
        return prod;
    }
      
      public void actualizar(Producto prod){
        
        try{
            String sql = "UPDATE producto SET nombre = ?, stock = ?, precio = ? WHERE id = ?";
            
            Connection conec = Conexion.getConexion();
            PreparedStatement prep = conec.prepareStatement(sql);
            prep.setString(1, prod.getNombre());
            prep.setInt(2, prod.getStock());
            prep.setDouble(3, prod.getPrecio());
            prep.setInt(4, prod.getId());
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se pudo actualizar el producto con el id ingresado: " + e.getMessage());
        }
    }
      
       public void eliminar(int id){  
        try {
        String sql = "DELETE FROM producto WHERE id = ?";
        
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        prep.setInt(1, id);
        prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se pudo eliminar el producto con el id ingresado: " + e.getMessage());
        }
    }
}
