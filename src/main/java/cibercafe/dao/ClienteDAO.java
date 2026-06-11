package cibercafe.dao;

import cibercafe.modelo.Cliente;
import cibercafe.util.Conexion;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteDAO {
 
    public void insertar(Cliente cliente) {
       try  {
        //consulta sql
        String sql = "INSERT INTO cliente (nombre, apellido, telefono) VALUES (?, ?, ?)";
        
        Connection conec = Conexion.getConexion(); //aplicar la conexion
        PreparedStatement prep = conec.prepareStatement(sql);
        prep.setString(1, cliente.getNombre());
        prep.setString(2, cliente.getApellido());
        prep.setString(3, cliente.getTelefono());
        prep.executeUpdate(); 
        } catch (SQLException e) {
           System.out.println("Hubo un error al insertar el cliente: " + e.getMessage());
       }
    }
    
    public List<Cliente> obtenerTodos(){
    
        List<Cliente> cli = new ArrayList<>();
        try {
        String sql = "SELECT * FROM cliente";
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        ResultSet res = prep.executeQuery();
        while (res.next()){
            //crear clientes
            Cliente cliente = new Cliente();
            cliente.setId(res.getInt("id"));
            cliente.setNombre(res.getString("nombre"));
            cliente.setApellido(res.getString("apellido"));
            cliente.setTelefono(res.getString("telefono"));
            
            cli.add(cliente);
         }
        } catch (SQLException e) {
            System.out.println("Hubo un error al obtener los clientes: " + e.getMessage());
        }
        return cli; 
    }
    
    public Cliente buscarPorId(int id){
        Cliente cliente = null;
        try {
            String sql = "SELECT * FROM cliente WHERE id = ?";
            Connection conec = Conexion.getConexion();
            PreparedStatement prep = conec.prepareStatement(sql);
            prep.setInt(1, id);
            ResultSet res = prep.executeQuery();
            if (res.next()){
            cliente = new Cliente();
            cliente.setId(res.getInt("id"));
            cliente.setNombre(res.getString("nombre"));
            cliente.setApellido(res.getString("apellido"));
            cliente.setTelefono(res.getString("telefono"));
           }  
            
          } catch (SQLException e){
                    System.out.println("No se encontró a ningun cliente con el id indicado: " + e.getMessage());
            }
        return cliente;
    }
    
    public void actualizar(Cliente cliente){
        
        try{
            String sql = "UPDATE cliente SET nombre = ?, apellido = ?, telefono = ? WHERE id = ?";
            
            Connection conec = Conexion.getConexion();
            PreparedStatement prep = conec.prepareStatement(sql);
            prep.setString(1, cliente.getNombre());
            prep.setString(2, cliente.getApellido());
            prep.setString(3, cliente.getTelefono());
            prep.setInt(4, cliente.getId());
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se pudo actualizar el cliente: " + e.getMessage());
        }
    }
    
    public void eliminar(int id){  
        try {
        //consulta sql
        String sql = "DELETE FROM cliente WHERE id = ?";
        
        Connection conec = Conexion.getConexion();
        PreparedStatement prep = conec.prepareStatement(sql);
        prep.setInt(1, id);
        prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se pudo eliminar el cliente: " + e.getMessage());
        }
    }
    
}
//Parte hecha por Fabri
