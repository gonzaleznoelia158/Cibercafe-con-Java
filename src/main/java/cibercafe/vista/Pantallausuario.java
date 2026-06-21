package cibercafe.vista;


import cibercafe.controllers.SesionController;
import cibercafe.dao.ComputadoraDAO;
import cibercafe.dao.SesionDAO;
import cibercafe.modelo.Computadora;
import cibercafe.modelo.Sesion;
import cibercafe.dao.VentaDAO;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Uzulu
 */
public class Pantallausuario extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Pantallausuario.class.getName());
    private DefaultTableModel modelo;
    private javax.swing.Timer timer;
    private SesionDAO sesionDAO;
    private ComputadoraDAO computadoraDAO;
    private SesionController sesionController;

    /**
     * Creates new form Pantallausuario
     */
   public Pantallausuario(String nombrePC, String tiempoRestante, double extraCafeteria, int horasAlquilar) {
    initComponents();
    sesionDAO = new SesionDAO();
    computadoraDAO = new ComputadoraDAO();
    sesionController = new SesionController();
    sesionController.liberarSesionesVencidas();
    setVisible(true);
    cargarTabla();
    iniciarTimer();
}

    private void initComponents() {
         modelo = new DefaultTableModel(new Object[]{"PC", "Tiempo Restante", "Costo Sesión", "Extra Cafetería", "Acción"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tabla = new JTable(modelo);
        tabla.getColumn("Acción").setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JButton btn = new JButton("Cerrar sesión");
                return btn;
            }
        });

        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int fila = tabla.rowAtPoint(e.getPoint());
                int columna = tabla.columnAtPoint(e.getPoint());
                if (columna == 4 && fila >= 0) {
                    cerrarSesionFila(fila);
                }
            }
        });

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(10, 10, 460, 300);

        JButton btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.setBounds(150, 320, 200, 30);
        btnCerrarSesion.addActionListener(e -> cerrarSesion());

        JButton btnReportes = new JButton("Ver Reportes");
        btnReportes.setBounds(150, 360, 200, 30);
        btnReportes.addActionListener(e -> {
        Reportes ventana = new Reportes();
        ventana.setVisible(true);});

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sesiones Activas");
        setLayout(null);
        setSize(500, 380);
        add(scroll);
        add(btnCerrarSesion);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sesiones Activas");
        setLayout(null);
        setSize(500, 380);
        add(scroll);
        add(btnReportes);
        setSize(500, 420);
        
    }

   private void cargarTabla() {
    modelo.setRowCount(0);
    VentaDAO ventaDAO = new VentaDAO();
    List<Sesion> activas = sesionDAO.obtenerActivas();
    for (Sesion s : activas) {
        Computadora c = computadoraDAO.buscarPorId(s.getIdComputadora());
        String nombrePC = c != null ? c.getNombre() : "PC-??";
        String tiempo = calcularTiempo(s.getHoraFin());
        double extraCafeteria = ventaDAO.obtenerTotalCafeteriaPorSesion(s.getId());
        modelo.addRow(new Object[]{nombrePC, tiempo, "$" + s.getCostoTotal(), "$" + extraCafeteria, "Cerrar sesión"});
    }
}

    private String calcularTiempo(LocalDateTime horaFin) {
    if (horaFin == null) return "00:00:00";
    LocalDateTime ahora = LocalDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));
    long segundos = ChronoUnit.SECONDS.between(ahora, horaFin);
    if (segundos <= 0) return "00:00:00";
    long horas = segundos / 3600;
    long minutos = (segundos % 3600) / 60;
    long segs = segundos % 60;
    return String.format("%02d:%02d:%02d", horas, minutos, segs);
}

    private void cerrarSesionFila(int fila) {
        String nombrePC = (String) modelo.getValueAt(fila, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Cerrar sesión de " + nombrePC + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            List<Sesion> activas = sesionDAO.obtenerActivas();
            Sesion s = activas.get(fila);
            sesionController.cerrarSesion(s.getId(), s.getIdComputadora());
            cargarTabla();
        }
    }

    private void iniciarTimer() {
        timer = new javax.swing.Timer(1000, e -> cargarTabla());
        timer.start();
    }
    private void cerrarSesion() {
    java.io.File archivoSesion = new java.io.File("sesion.txt");
    if (archivoSesion.exists()) {
        archivoSesion.delete();
    }
    timer.stop();
    Inicio inicio = new Inicio();
    inicio.setVisible(true);
    this.dispose();
}
    /* 
    * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Pantallausuario("PC 01", "01:00:00", 0, 1).setVisible(true));
    }
}
