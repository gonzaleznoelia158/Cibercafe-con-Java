package cibercafe.dao;

import cibercafe.modelo.Usuario;
import cibercafe.util.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    
    public void insertar(Usuario usu) {
       try  {
        String sql = "INSERT INTO usuario (nombre_usuario, contrasenia, rol) VALUES (?, ?, ?)";
        
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        prep.setString(1, usu.getNombreUsuario());
        prep.setString(2, usu.getContrasenia());
        prep.setString(3, usu.getRol());
        prep.executeUpdate(); 
        } catch (SQLException e) {
           System.out.println("Ocurrió un error al insertar al usuario: " + e.getMessage());
       }
    }
    
    public List<Usuario> obtenerTodos(){
    
        List<Usuario> user = new ArrayList<>();
        try {
        String sql = "SELECT * FROM usuario";
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        ResultSet res = prep.executeQuery();
        while (res.next()){
            
            Usuario u = new Usuario();
            u.setId(res.getInt("id"));
            u.setNombreUsuario(res.getString("nombre_usuario"));
            u.setContrasenia(res.getString("contrasenia"));
            u.setRol(res.getString("rol"));
            
            user.add(u);
         }
        } catch (SQLException e) {
            System.out.println("Hubo un error al obtener los usuarios: " + e.getMessage());
        }
        return user;  
    }
    
    public Usuario buscarPorId(int id){
        Usuario us = null;
        try {
            String sql = "SELECT * FROM usuario WHERE id = ?";
            Connection conec = Conexion.getConexion();
            PreparedStatement prep = conec.prepareStatement(sql);
            prep.setInt(1, id);
            ResultSet res = prep.executeQuery();
            if (res.next()){
            us = new Usuario();
            us.setId(res.getInt("id"));
            us.setNombreUsuario(res.getString("nombre_usuario"));
            us.setContrasenia(res.getString("contrasenia"));
            us.setRol(res.getString("rol"));
                
           }  
          } catch (SQLException e){
                    System.out.println("No se encontró ningun usuario con el id ingresado: " + e.getMessage());
            }
        return us;
    }
    
    public Usuario buscarPorCredenciales(String nombreUsuario, String contrasenia) {
        Usuario usuar = null;
        try {
            String sql = "SELECT * FROM usuario WHERE nombre_usuario = ? AND contrasenia = ?";
            Connection conec= Conexion.getConexion();
            PreparedStatement prep = conec.prepareStatement(sql);
            prep.setString(1, nombreUsuario);
            prep.setString(2, contrasenia);
            ResultSet res = prep.executeQuery();
            if (res.next()) {
            usuar = new Usuario();
            usuar.setNombreUsuario(res.getString("nombre_usuario"));
            usuar.setContrasenia(res.getString("contrasenia"));
             }
            } catch (SQLException e) {
                    System.out.println("Usuario o contraseña no encontrados/incorrectos" + e.getMessage());
                    }
        return usuar;
    }
    
    public void actualizar(Usuario usu){
        try{
            String sql = "UPDATE usuario SET nombre_usuario = ?, contrasenia = ?, rol = ? WHERE id = ?";
            
            Connection conec = Conexion.getConexion();
            PreparedStatement prep = conec.prepareStatement(sql);
            prep.setString(1, usu.getNombreUsuario());
            prep.setString(2, usu.getContrasenia());
            prep.setString(3, usu.getRol());
            prep.setInt(4, usu.getId());
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se pudo actualizar al usuario: " + e.getMessage());
        }
    }
    
    public void eliminar(int id){  
        try {
        String sql = "DELETE FROM usuario WHERE id = ?";
        
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        prep.setInt(1, id);
        prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se pudo eliminar al usuario: " + e.getMessage());
        }
    }
    
}
