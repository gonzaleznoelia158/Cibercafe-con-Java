package SistemaCibercafe;

import cibercafe.util.Conexion;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection con = Conexion.getConexion();
        if (con != null) {
            System.out.println("¡DataBase conectada con exito!");
        } else {
            System.out.println("Conexion fallida");
        }
    }
}