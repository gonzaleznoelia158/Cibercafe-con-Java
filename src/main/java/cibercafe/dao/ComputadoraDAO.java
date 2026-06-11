package cibercafe.dao;

import cibercafe.modelo.Computadora;
import cibercafe.util.Conexion;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ComputadoraDAO {
    
     public void insertar(Computadora comp) {
       try  {
        String sql = "INSERT INTO computadora (nombre, id_categoria) VALUES (?, ?)";
        
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        prep.setString(1, comp.getNombre());
        prep.setInt(2, comp.getIdCategoria());
        prep.executeUpdate(); 
        } catch (SQLException e) {
           System.out.println("Hubo un error al insertar la computadora con el id indicado: " + e.getMessage());
       } 
    }
     
      public List<Computadora> obtenerTodos(){
    
        List<Computadora> compu = new ArrayList<>();
        try {
        String sql = "SELECT * FROM computadora";
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        ResultSet res = prep.executeQuery();
        while (res.next()){
            //crear computadora
            Computadora com = new Computadora();
            com.setId(res.getInt("id"));
            com.setNombre(res.getString("nombre"));
            com.setIdCategoria(res.getInt("id_categoria"));
            
            compu.add(com);
         }
        } catch (SQLException e) {
            System.out.println("Hubo un error al listar las computadoras: " + e.getMessage());
        }
        return compu; 
    }
      
      public Computadora buscarPorId(int id){
        Computadora comput = null;
        try {
            String sql = "SELECT * FROM computadora WHERE id = ?";
            Connection conec = Conexion.getConexion();
            PreparedStatement prep = conec.prepareStatement(sql);
            prep.setInt(1, id);
            ResultSet res = prep.executeQuery();
            if (res.next()){
            comput = new Computadora();
            comput.setId(res.getInt("id"));
            comput.setNombre(res.getString("nombre"));
            comput.setIdCategoria(res.getInt("id_categoria"));
           }  
            
          } catch (SQLException e){
                    System.out.println("No se encontró ninguna computadora con el id ingresado: " + e.getMessage());
            }
        return comput;
    }
      
      public void actualizar(Computadora comp){
        
        try{
            String sql = "UPDATE computadora SET nombre = ?, id_categoria = ? WHERE id = ?";
            
            Connection conec = Conexion.getConexion();
            PreparedStatement prep = conec.prepareStatement(sql);
            prep.setString(1, comp.getNombre());
            prep.setInt(2, comp.getIdCategoria());
            prep.setInt(3, comp.getId());
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se pudo actualizar la computadora con el id ingresado: " + e.getMessage());
        }
    }
      
       public void eliminar(int id){  
        try {
        String sql = "DELETE FROM computadora WHERE id = ?";
        
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        prep.setInt(1, id);
        prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se pudo eliminar la computadora con el id ingresado: " + e.getMessage());
        }
    }  
}
