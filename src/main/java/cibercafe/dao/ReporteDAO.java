package cibercafe.dao;

import cibercafe.modelo.ReporteCliente;
import cibercafe.modelo.InformePC;
import cibercafe.util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteDAO {

    // 1. Reporte para un cliente específico (Historial de uso)
    public List<ReporteCliente> historialDeCliente(int idCliente) {
        List<ReporteCliente> lista = new ArrayList<>();
        
        String sql = "SELECT c.nombre, p.nombre as numero, s.hora_inicio, s.hora_fin, s.costo_total " +
             "FROM sesion s " +
             "INNER JOIN cliente c ON s.id_cliente = c.id " +
             "INNER JOIN computadora p ON s.id_computadora = p.id " +
             "WHERE c.id = ? ORDER BY s.hora_inicio DESC";

        // Usamos getConexion() tal cual lo tienes en tu clase Conexion
        try (Connection con = Conexion.getConexion(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                  java.sql.Timestamp inicio = rs.getTimestamp("hora_inicio");
                  java.sql.Timestamp fin = rs.getTimestamp("hora_fin");
                  int minutos = 0;
                  if (inicio != null && fin != null) {
                  minutos = (int) ((fin.getTime() - inicio.getTime()) / 60000);
                  }
                  lista.add(new ReporteCliente(
                  rs.getString("nombre"),
                  rs.getString("numero"),
                  inicio,
                  minutos,
                  rs.getDouble("costo_total")
                  ));
               }
            }
        } catch (SQLException e) {
            System.err.println("Error en reporte de cliente: " + e.getMessage());
        }
        return lista;
    }

    // 2. Informe General de Uso de PCs
    public List<InformePC> estadisticasUsoPCs() {
        List<InformePC> lista = new ArrayList<>();
        
        String sql = "SELECT p.nombre, COUNT(s.id) as usos, " +
             "SUM(TIMESTAMPDIFF(MINUTE, s.hora_inicio, s.hora_fin)) as min_totales, " +
             "SUM(s.costo_total) as ingresos " +
             "FROM computadora p " +
             "LEFT JOIN sesion s ON p.id = s.id_computadora " +
             "GROUP BY p.nombre " +
             "ORDER BY p.nombre ASC";

        // Usamos getConexion() tal cual lo tienes en tu clase Conexion
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                lista.add(new InformePC(
                    rs.getString("nombre"),
                    rs.getInt("usos"),
                    rs.getInt("min_totales"),
                    rs.getDouble("ingresos")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error en estadísticas de PCs: " + e.getMessage());
        }
        return lista;
    }
}