
package jframes;

import AppPackage.AnimationClass;
import akinatordisney.MetodosArbolAdivinador;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author María Zapata y Mateo Peralta
 * Este Frame es para poder visualizar los records del juego en un JTable.
 Lo que hace la clase es que los pasa del archivo adjunto: usurios.txt al Jtable
 */
public class JFLeaderboards extends javax.swing.JFrame {

    MetodosArbolAdivinador a = new MetodosArbolAdivinador();
    long may = 0;
    String nom = "", t = "", f = "";

    public JFLeaderboards() throws IOException {
        initComponents();
        //Le pone el icono de la aplicación
        this.setIconImage(new ImageIcon(getClass().getResource("/pictures/mickeyIcon.png")).getImage());
        this.setLocationRelativeTo(null);
        long l = lineas();
        imagenes();
        for (long i = 0; i < l; i++) {
            buscmay();
            eliminar();
            may = 0;
        }
        File file = new File("usuarios.txt");
        File temp = new File("temp.txt");
        if (!file.delete()) {
        }
        if (!temp.renameTo(file)) {
        }
        ponerArchivoenJTable();
        tbLeaderboards.setBackground(new Color(0, 0, 0, 0));
        ((DefaultTableCellRenderer) tbLeaderboards.getDefaultRenderer(Object.class)).setBackground(new Color(0, 0, 0, 0));
        tbLeaderboards.setGridColor(Color.WHITE);
        tbLeaderboards.setForeground(Color.BLACK);
        jScrollPane2.setBackground(new Color(0, 0, 0, 0));
        jScrollPane2.setOpaque(false);
        tbLeaderboards.setOpaque(false);
        ((DefaultTableCellRenderer) tbLeaderboards.getDefaultRenderer(Object.class)).setOpaque(false);
        jScrollPane2.getViewport().setOpaque(false);
        jScrollPane2.setBorder(null);
        tbLeaderboards.setShowGrid(false);
        tbLeaderboards.getTableHeader().setUI(null);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int x = 0; x < tbLeaderboards.getColumnCount(); x++) {
            tbLeaderboards.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
        imagenes2();
    }

    public void imagenes() {
        ImageIcon imagen = new ImageIcon(getClass().getResource("/pictures/fondo2.png"));
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lbFondo.getWidth(), lbFondo.getHeight(), Image.SCALE_DEFAULT));
        lbFondo.setIcon(icono);
        this.repaint();
    }

    public void imagenes2() {
        ImageIcon imagen = new ImageIcon(getClass().getResource("/pictures/pergamino2.png"));
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(pergamino.getWidth(), pergamino.getHeight(), Image.SCALE_DEFAULT));
        pergamino.setIcon(icono);
        this.repaint();
    }

    private long lineas() throws FileNotFoundException, IOException {
        File f = new File("usuarios.txt");
        long linecount = 0;
        try (FileReader fr = new FileReader(f)) {
            BufferedReader br = new BufferedReader(fr);
            String s;
            while ((s = br.readLine()) != null) {
                linecount++;
            }
        }
        return linecount;
    }

    private void buscmay() throws FileNotFoundException, IOException {
        File file = new File("usuarios.txt");
        File temp = new File("temp.txt");
        a.crearArchivo(temp);
        FileReader leer = new FileReader(file);
        String cadena, puntaje, tiempo, nombre, fecha;
        FileWriter escribir = new FileWriter(file, true);
        PrintWriter linea = new PrintWriter(escribir);
        BufferedReader alm = new BufferedReader(leer);
        FileReader leert = new FileReader(temp);
        FileWriter escribirt = new FileWriter(temp, true);
        PrintWriter lineat = new PrintWriter(escribirt);
        while ((cadena = alm.readLine()) != null) {
            nombre = cadena.split("\\|")[0];
            puntaje = cadena.split("\\|")[1];
            tiempo = cadena.split("\\|")[2];
            fecha = cadena.split("\\|")[3];
            if (Integer.parseInt(puntaje) > may) {
                may = Integer.parseInt(puntaje);
                nom = nombre;
                t = tiempo;
                f = fecha;
            }
        }
        alm.close();
        lineat.println(nom + "|" + may + "|" + t + "|" + f);
        lineat.close();
        escribirt.close();
        leer.close();
        escribir.close();
        linea.close();
        leert.close();
    }

    private void eliminar() throws FileNotFoundException, IOException {
        File file = new File("usuarios.txt");
        File temp = new File("usuarios2.txt");
        a.crearArchivo(temp);
        FileReader leer = new FileReader(file);
        FileWriter escribir = new FileWriter(file, true);
        PrintWriter linea = new PrintWriter(escribir);
        BufferedReader alm = new BufferedReader(leer);
        FileWriter escribirt = new FileWriter(temp, true);
        PrintWriter pw = new PrintWriter(escribirt);
        String cadena;
        while ((cadena = alm.readLine()) != null) {
            if (!cadena.trim().equals(nom + "|" + may + "|" + t + "|" + f)) {
                pw.println(cadena);
                pw.flush();

            }
        }
        alm.close();
        pw.close();
        leer.close();
        escribir.close();
        linea.close();
        escribirt.close();
        if (!file.delete()) {
            System.out.println("no");
        }
        if (!temp.renameTo(file)) {
            System.out.println("No cambió");
        }
    }

    private void ponerArchivoenJTable() {
        //Crear un objeto de la clase archivo para manipular archivo
        try {
            File file = new File("usuarios.txt");
            FileReader leer = new FileReader(file);
            BufferedReader alm = new BufferedReader(leer);;
            String cadena, puntaje, tiempo, nombre, fecha;
            long t, min, sec;
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Nombre");
            model.addColumn("Puntaje");
            model.addColumn("Tiempo");
            model.addColumn("Fecha");

            try {
                while ((cadena = alm.readLine()) != null) {
                    nombre = cadena.split("\\|")[0];
                    puntaje = cadena.split("\\|")[1];
                    tiempo = cadena.split("\\|")[2];
                    t = Integer.parseInt(tiempo);
                    min = t / 60;
                    sec = t % 60;
                    if (sec < 10) {
                        if (min < 10) {
                            tiempo = "0" + min + ":" + "0" + sec;
                        } else {
                            tiempo = min + ":" + "0" + sec;
                        }
                    } else {
                        if (min < 10) {
                            tiempo = "0" + min + ":" + sec;
                        } else {
                            tiempo = min + ":" + sec;
                        }
                    }
                    fecha = cadena.split("\\|")[3];
                    model.addRow(new Object[]{nombre, puntaje, tiempo, fecha});
                }
                tbLeaderboards.setModel(model);
                alm.close();
                leer.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tbLeaderboards = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        lbMickey = new javax.swing.JLabel();
        lbMulti = new javax.swing.JLabel();
        lbCopa = new javax.swing.JLabel();
        lbReglas = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pergamino = new javax.swing.JLabel();
        lbFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mateo P. & María Z.");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbLeaderboards.setFont(new java.awt.Font("Forte", 0, 18)); // NOI18N
        tbLeaderboards.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbLeaderboards.setRowHeight(35);
        jScrollPane2.setViewportView(tbLeaderboards);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 460, 430));

        jLabel3.setFont(new java.awt.Font("Forte", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("LeaderBoards");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, -1));

        lbMickey.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/mickey32.png"))); // NOI18N
        lbMickey.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbMickey.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbMickeyMouseClicked(evt);
            }
        });
        getContentPane().add(lbMickey, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 70, 30, 30));

        lbMulti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/multiMenu.png"))); // NOI18N
        lbMulti.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbMulti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbMultiMouseClicked(evt);
            }
        });
        getContentPane().add(lbMulti, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 30, 30));

        lbCopa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/reiniciar32.png"))); // NOI18N
        lbCopa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbCopa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCopaMouseClicked(evt);
            }
        });
        getContentPane().add(lbCopa, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 150, -1, 40));

        lbReglas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/reglas32.png"))); // NOI18N
        lbReglas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbReglas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbReglasMouseClicked(evt);
            }
        });
        getContentPane().add(lbReglas, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 110, 40, 30));

        jLabel1.setFont(new java.awt.Font("Forte", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PUNTAJE");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 120, -1));

        jLabel6.setFont(new java.awt.Font("Forte", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("TIEMPO");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, 140, -1));

        jLabel7.setFont(new java.awt.Font("Forte", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("FECHA");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, 130, -1));

        jLabel8.setFont(new java.awt.Font("Forte", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("NOMBRE");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 120, -1));
        getContentPane().add(pergamino, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 610, 640));

        lbFondo.setBackground(new java.awt.Color(0, 255, 255));
        lbFondo.setForeground(new java.awt.Color(255, 255, 255));
        lbFondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbFondoMouseClicked(evt);
            }
        });
        getContentPane().add(lbFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 760));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbMickeyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMickeyMouseClicked
        try {
            JFInicio inicio = new JFInicio();
            this.dispose();
            inicio.setSize(1010, 580);
            inicio.setLocationRelativeTo(null);
            inicio.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(JFRules.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lbMickeyMouseClicked

    private void lbMultiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMultiMouseClicked
        //Derecha
        AnimationClass mickey = new AnimationClass();
        mickey.jLabelXRight(-30, 10, 10, 5, lbMickey);

        AnimationClass music = new AnimationClass();
        music.jLabelXRight(-30, 10, 10, 5, lbReglas);

        AnimationClass copa = new AnimationClass();
        copa.jLabelXRight(-30, 10, 10, 5, lbCopa);

        //Izquierda
        AnimationClass mickeyy = new AnimationClass();
        mickeyy.jLabelXLeft(10, -30, 10, 5, lbMickey);

        AnimationClass musicc = new AnimationClass();
        musicc.jLabelXLeft(10, -30, 10, 5, lbReglas);

        AnimationClass copaa = new AnimationClass();
        copaa.jLabelXLeft(10, -30, 10, 5, lbCopa);
    }//GEN-LAST:event_lbMultiMouseClicked

    private void lbCopaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCopaMouseClicked

        try {
            JFLeaderboards scores = new JFLeaderboards();
            this.dispose();
            scores.setLocationRelativeTo(null);
            scores.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(JFRules.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lbCopaMouseClicked

    private void lbReglasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbReglasMouseClicked
        JFRules r = new JFRules();
        this.dispose();
        r.setLocationRelativeTo(null);
        r.setVisible(true);
    }//GEN-LAST:event_lbReglasMouseClicked

    private void lbFondoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFondoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbFondoMouseClicked

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFLeaderboards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFLeaderboards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFLeaderboards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFLeaderboards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFLeaderboards().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(JFLeaderboards.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbCopa;
    private javax.swing.JLabel lbFondo;
    private javax.swing.JLabel lbMickey;
    private javax.swing.JLabel lbMulti;
    private javax.swing.JLabel lbReglas;
    private javax.swing.JLabel pergamino;
    private javax.swing.JTable tbLeaderboards;
    // End of variables declaration//GEN-END:variables
}
