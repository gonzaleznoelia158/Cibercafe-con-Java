package cibercafe.dao;

import cibercafe.modelo.Categoria;
import cibercafe.util.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CategoriaDAO {
    
    public void insertar(Categoria categoria) {
       try  {
        String sql = "INSERT INTO categoria (nombre, precio_hora) VALUES (?, ?)";
        
        Connection conec = Conexion.getConexion(); //aplicar la conexion
        PreparedStatement prep = conec.prepareStatement(sql);
        prep.setString(1, categoria.getNombre());
        prep.setDouble(2, categoria.getPrecioHora());
        prep.executeUpdate(); 
        } catch (SQLException e) {
           System.out.println("Hubo un error al insertar la sesion en esta categoria: " + e.getMessage());
       }
    }
    
    public List<Categoria> obtenerTodos(){
    
        List<Categoria> cat = new ArrayList<>();
        try {
        String sql = "SELECT * FROM categoria";
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        ResultSet res = prep.executeQuery();
        while (res.next()){
            //crear categorias
            Categoria cate = new Categoria();
            cate.setId(res.getInt("id"));
            cate.setNombre(res.getString("nombre"));
            cate.setPrecioHora(res.getDouble("precio_hora"));
            
            cat.add(cate);
         }
        } catch (SQLException e) {
            System.out.println("Hubo un error al obtener las categorias: " + e.getMessage());
        }
        return cat;  
    }
    
    public Categoria buscarPorId(int id){
        Categoria categoria = null;
        try {
            String sql = "SELECT * FROM categoria WHERE id = ?";
            Connection conec = Conexion.getConexion();
            PreparedStatement prep = conec.prepareStatement(sql);
            prep.setInt(1, id);
            ResultSet res = prep.executeQuery();
            if (res.next()){
            categoria = new Categoria();
            categoria.setId(res.getInt("id"));
            categoria.setNombre(res.getString("nombre"));
            categoria.setPrecioHora(res.getDouble("precio_hora"));
           }  
            
          } catch (SQLException e){
                    System.out.println("No se encontró ninguna categoria con el id indicado: " + e.getMessage());
            }
        return categoria;
    }
    
    public void actualizar(Categoria categoria){
        try{
            String sql = "UPDATE categoria SET nombre = ?, precio_hora = ? WHERE id = ?";
            
            Connection conec = Conexion.getConexion();
            PreparedStatement prep = conec.prepareStatement(sql);
            prep.setString(1, categoria.getNombre());
            prep.setDouble(2, categoria.getPrecioHora());
            prep.setInt(3, categoria.getId());
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se pudo actualizar la categoria: " + e.getMessage());
        }
    }
    
    public void eliminar(int id){  
        try {
        String sql = "DELETE FROM categoria WHERE id = ?";
        
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        prep.setInt(1, id);
        prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se pudo eliminar la categoria: " + e.getMessage());
        }
    }
}
