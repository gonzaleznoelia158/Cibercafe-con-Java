package cibercafe.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private static final String url = "jdbc:mysql://localhost:3307/cibercafe";
    private static final String usuario = "root";
    private static final String contrasenia = "1231";
    
    public static Connection getConexion() {
        Connection conec = null;
        try {
            conec = DriverManager.getConnection(url, usuario, contrasenia);
        } catch (SQLException e) {
            System.out.println("No se pudo conectar a la base de datos" + e.getMessage());
        }
        return conec;
    }
}