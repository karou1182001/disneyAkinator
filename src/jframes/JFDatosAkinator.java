
package jframes;

import AppPackage.AnimationClass;
import akinatordisney.MetodosArbolAdivinador;
import akinatordisney.NodoAdivinador;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author María Zapata y Mateo Peralta
 * Este Frame es muy útil para el programador al momento de probar el programa.
 * Esto debido a que nos permite visualizar el árbol y el archivo en un Jtable
 * cuando el proyecto está corriendo. Mientras el proyecto corre, basta con poner 
 * en el nombre de usuario la palabra: "datos2" para poder acceder a este frame, 
 * donde podremos visualizar.
 */
public class JFDatosAkinator extends javax.swing.JFrame {

    MetodosArbolAdivinador a= new MetodosArbolAdivinador();
    public JFDatosAkinator() 
    {
        initComponents();
        //Le pone el icono de la aplicación
        this.setIconImage(new ImageIcon(getClass().getResource
        ("/pictures/mickeyIcon.png")).getImage());
        //Pone la pantalla en la mitad
        this.setLocationRelativeTo(null);
        //PONER EL ARCHIVO EN EL JTABLE
        ponerArchivoenJTable();
        a.recuperarArbol(a.raiz,1,true );
        imagenes();
      
    }
     public void imagenes()
    {
       ImageIcon imagen = new ImageIcon(getClass().
       getResource("/pictures/fondo2.png"));
       Icon icono= new ImageIcon(imagen.getImage().getScaledInstance
       (lbFondo.getWidth(), lbFondo.getHeight(),Image.SCALE_DEFAULT));
       lbFondo.setIcon(icono);
       this.repaint();
    }
  
    
    int large=700;
    NodoAdivinador nodo= new NodoAdivinador();
    //Mètodo recursivo para dibujar
    public void dibujar(NodoAdivinador nodo, long nivel,int x, int y)
    {
        
            int rad=10, diam=20, dist=40;
            //Con el comando this.algo, llamas a la variable global para utilizarla.
            int large=this.large;
            Graphics g = jPanelDibujo.getGraphics(); 
            if(nodo == null)
            {
                //Si no hay nada dentro del nodo, entonces no se dibuja nada
                //O este es el caso base, en caso de que no hayan hijos. Por 
                //consiguiente, dejará de dibujar
            }
            else 
            {
                //Asigna el color de las bolitas
                g.setColor(Color.ORANGE);
                //Dibuja los rectángulos
                g.fillRect(x, y, diam+20, diam);
                //Asigna color de la información
                g.setColor(Color.BLACK);
                //Dibuja el dato
                g.drawString(nodo.dato+"", x+3, y+13 );
                int xa=x, ya=y;
                nivel++;
                  if (nodo.izqn!=null)
                  {
                     int var=(int) Math.pow(2, nivel);
                     x=large/(2*var);
                     //Lo dibuja a la izquierda del nodo padre
                     x=xa-x;
                     //El siguiente nodo va a ser dibujado a 40 más abajo
                     y=ya+dist;
                     //Dibuja las líneas negras antes de dibujar al siguiente nodo
                     g.setColor(Color.black);

                     g.drawLine(xa, y-dist+rad, x+rad, y);
                     dibujar(nodo.izqn, nivel, x , y);

                  }
                  if(nodo.ders!=null)
                  {
                     int var=(int) Math.pow(2, nivel);
                     x=large/(2*var);
                     x=xa+x;
                     y=ya+dist;
                     g.setColor(Color.black);
                     g.drawLine(xa+diam, y-dist+rad, x+rad, y);
                     dibujar(nodo.ders, nivel, x, y);
                  }


            }
        
    }
    private void ponerArchivoenJTable()
    {
        //Crear un objeto de la clase archivo para manipular archivo
        try 
        {
            //Para instanciar, leer, y ver lo que almacen al archivo
            File file= new File("datosAkinator.txt");
            FileReader leer= new FileReader(file);
            BufferedReader alm=new BufferedReader(leer);;
            String cadena, dato, pregunta, num, nivel;
            DefaultTableModel model = new DefaultTableModel();
            
            //Vamos a colocarle nombre a las columnas
            model.addColumn("NUM");
            model.addColumn("DATO");
            model.addColumn("PREGUNTA");
            model.addColumn("NIVEL");

            try 
            {
                while ((cadena = alm.readLine())!= null)
                {
                        //Como | es un caracter especial, hay que colocar \\
                        num = cadena.split("\\|")[0];
                        dato = cadena.split("\\|")[1];
                        pregunta =cadena.split("\\|")[2];
                        nivel = cadena.split("\\|")[3];
                        model.addRow(new Object[]{num,dato,pregunta,nivel});
                } 
                tbDatosAkinator.setModel(model);
                 
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, e);
        }
    }
    

    /**
     * This ahod is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this ahod is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lbMulti = new javax.swing.JLabel();
        bDibujar3 = new javax.swing.JButton();
        lbMickey = new javax.swing.JLabel();
        lbCopa = new javax.swing.JLabel();
        lbMusic = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDatosAkinator = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanelDibujo = new javax.swing.JPanel();
        lbFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mateo P. & María Z.");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbMulti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/multiMenu.png"))); // NOI18N
        lbMulti.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbMulti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbMultiMouseClicked(evt);
            }
        });
        jPanel2.add(lbMulti, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        bDibujar3.setBackground(new java.awt.Color(255, 255, 255));
        bDibujar3.setFont(new java.awt.Font("Forte", 0, 18)); // NOI18N
        bDibujar3.setText("Dibujar");
        bDibujar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDibujar3ActionPerformed(evt);
            }
        });
        jPanel2.add(bDibujar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, 200, 30));

        lbMickey.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/mickey32.png"))); // NOI18N
        lbMickey.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbMickey.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbMickeyMouseClicked(evt);
            }
        });
        jPanel2.add(lbMickey, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 70, 30, 30));

        lbCopa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/copa32.png"))); // NOI18N
        lbCopa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbCopa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCopaMouseClicked(evt);
            }
        });
        jPanel2.add(lbCopa, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 150, -1, 40));

        lbMusic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/musicPlay.png"))); // NOI18N
        lbMusic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbMusic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbMusicMouseClicked(evt);
            }
        });
        jPanel2.add(lbMusic, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 110, 40, 30));

        jLabel3.setFont(new java.awt.Font("Forte", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Datos de Disney Akinator");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        tbDatosAkinator.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbDatosAkinator);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 470, 500));

        jPanelDibujo.setPreferredSize(new java.awt.Dimension(2000, 2000));

        javax.swing.GroupLayout jPanelDibujoLayout = new javax.swing.GroupLayout(jPanelDibujo);
        jPanelDibujo.setLayout(jPanelDibujoLayout);
        jPanelDibujoLayout.setHorizontalGroup(
            jPanelDibujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelDibujoLayout.setVerticalGroup(
            jPanelDibujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanelDibujo);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, 480, 500));

        lbFondo.setBackground(new java.awt.Color(0, 255, 255));
        lbFondo.setForeground(new java.awt.Color(255, 255, 255));
        lbFondo.setBorder(null);
        lbFondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbFondoMouseClicked(evt);
            }
        });
        jPanel2.add(lbFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 580));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bDibujar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDibujar3ActionPerformed
         try 
        {
            //Instanciamos la clase graphics, diciéndole que va a dibujar en el panel
            Graphics g = jPanelDibujo.getGraphics();
            //Dibujar
            //Hace las operaciones para dibujar al nodo raíz
            int var=(int) Math.pow(2, 0);
            int x=this.large/(2*var);
            dibujar(a.raiz, 0, x, 40);
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_bDibujar3ActionPerformed

    private void lbMickeyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMickeyMouseClicked
        try {
            JFInicio inicio= new JFInicio();
            this.dispose();
            inicio.setSize(1010, 580);
            inicio.setLocationRelativeTo(null);
            inicio.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(JFRules.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lbMickeyMouseClicked

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

    private void lbMusicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMusicMouseClicked
        if (a.play==1)
        {
            lbMusic.setIcon(new ImageIcon(getClass().getResource("/pictures/musicStop.png")));
            a.sonido.stop();
            a.play=0;
        }
        else
        {
            lbMusic.setIcon(new ImageIcon(getClass().getResource("/pictures/musicPlay.png")));
            //Reproduce el sonido una y otra vez
            a.sonido.loop();
            a.play=1;
        }
    }//GEN-LAST:event_lbMusicMouseClicked

    private void lbMultiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMultiMouseClicked
        //Derecha
        AnimationClass mickey= new AnimationClass();
        mickey.jLabelXRight(-30, 10, 10, 5, lbMickey);

        AnimationClass music= new AnimationClass();
        music.jLabelXRight(-30, 10, 10, 5, lbMusic);

        AnimationClass copa= new AnimationClass();
        copa.jLabelXRight(-30, 10, 10, 5, lbCopa);

        //Izquierda
        AnimationClass mickeyy= new AnimationClass();
        mickeyy.jLabelXLeft(10, -30, 10, 5, lbMickey);

        AnimationClass musicc= new AnimationClass();
        musicc.jLabelXLeft(10, -30, 10, 5, lbMusic);

        AnimationClass copaa= new AnimationClass();
        copaa.jLabelXLeft(10, -30, 10, 5, lbCopa);
    }//GEN-LAST:event_lbMultiMouseClicked

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
            java.util.logging.Logger.getLogger(JFDatosAkinator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFDatosAkinator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFDatosAkinator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFDatosAkinator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFDatosAkinator().setVisible(true);
            }
        });
    }
    //Crear un modelo para añadir columnas a la tabla
    private DefaultTableModel model;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bDibujar3;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelDibujo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbCopa;
    private javax.swing.JLabel lbFondo;
    private javax.swing.JLabel lbMickey;
    private javax.swing.JLabel lbMulti;
    private javax.swing.JLabel lbMusic;
    private javax.swing.JTable tbDatosAkinator;
    // End of variables declaration//GEN-END:variables
}
