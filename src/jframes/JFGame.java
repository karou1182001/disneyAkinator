
package jframes;

import AppPackage.AnimationClass;
import akinatordisney.MetodosArbolAdivinador;
import akinatordisney.NodoAdivinador;
import java.applet.AudioClip;
import java.awt.Image;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author María Zapata y Mateo Peralta
 Este Frame es el juego en sí. A partir de este se va acceder a casi todos los 
 métodos de la clase métodosArbolAdivinador.java para poder jugar. El Frame lo
 que hace es recorrer un árbol y al llegar a una hoja mostrarla. Ya que las hojas
 son los personajes y lo demás son características. Al mostrar el personaje va a 
 buscar en el package personajes la imagen del mismo. Que está nombrada con el 
 mismo nombre del personaje.*/
public class JFGame extends javax.swing.JFrame {

    //Declaración de variables 
    MetodosArbolAdivinador met = new MetodosArbolAdivinador();
    File direccion;
    NodoAdivinador nodo = new NodoAdivinador();
    public String res;
    boolean fotoMago = true, reglaVista = false;
    String txtfotosala = "";
    String terminaEn;
    byte[] bytesImag;
    static long puntaje = 1;
    public boolean sw = false;
    public static long ini;

    public JFGame() {
        initComponents();
        //Le pone el icono de la aplicación
        this.setIconImage(new ImageIcon(getClass().getResource("/pictures/mickeyIcon.png")).getImage());
        //Pone la pantalla en la mitad
        this.setLocationRelativeTo(null);
        ini = System.currentTimeMillis();
        imagenes();
        jPanel1.setVisible(false);
        //Ponemos la música
        met.sonido.stop();
        met.sonido.loop();
        //Recuperamos el ábol
        met.recuperarArbol(met.raiz, 1, true);
        //A nuestro nodo que va a recorrer el árbol le asignamos el valor de la raíz
        nodo = met.raiz;
        //Hacemos que Mickey haga la primera pregunta
        lbPregunta.setText("¿Tu personaje " + met.raiz.dato + "?");
    }

    //Métodos
    //Método para poner la imagen de fondo y que está se adapte al JFrame
    public void imagenes() {
        ImageIcon imagen = new ImageIcon(getClass()
        .getResource("/pictures/fondo2.png"));
        Icon icono = new ImageIcon(imagen.
        getImage().getScaledInstance(lbFondo.
         getWidth(), lbFondo.getHeight(), Image.SCALE_DEFAULT));
        lbFondo.setIcon(icono);
        this.repaint();
    }

    //Método para un número aleatorio. (Se usa en el no sé)
    private static int random(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;

    }

    //Método para cambiar la foto de Mickey cada pregunte. Esto para que el juego
    //sea un poco animado
    public void fotoMickey(boolean fotoMago) {
        if (this.puntaje % 2 == 0) {
            if (this.fotoMago == true) {
                this.fotoMago = false;
                lbMago.setIcon(new ImageIcon(new ImageIcon(getClass().
                        getResource("/pictures/magoG2.png")).getImage()));
                AnimationClass Magomickey = new AnimationClass();
                Magomickey.jLabelXRight(0, 50, 10, 5, lbMago);

            } else {
                this.fotoMago = true;
                lbMago.setIcon(new ImageIcon(new ImageIcon(getClass().
                        getResource("/pictures/magoG1.png")).getImage()));
                //Izquierda
                AnimationClass Magomickeyy = new AnimationClass();
                Magomickeyy.jLabelXLeft(50, 0, 10, 5, lbMago);
            }
        }
    }

    //MÉTODOS PARA RECORRER EL ÁRBOL
    //Método cuando la respuesta tienda más a u sí. Se irá por la rama derecha
    public void resSi() {
        if (this.nodo.ders != null) {

            this.res = "¿Tu personaje " + this.nodo.dato + "?";

            puntaje++;

        } else {
            this.res = "¿Tu personaje es " + this.nodo.dato + "?";
            lblFotoPersonaje.setVisible(true);
            mostrarImgPersonaje(this.nodo.dato);
            //Volvemos todos los botones invisibles
            visibilidadBotones(false);

        }
        fotoMickey(this.fotoMago);
    }

    //Método para cuando la respuesta tienda más a un no, se irá por la rama
    //izquierda
    public void resNo() {
        if (this.nodo.izqn != null) {
            this.res = "¿Tu personaje " + this.nodo.dato + "?";

            sw = false;
            puntaje++;
        } else {
            this.res = "¿Tu personaje es " + this.nodo.dato + "?";
            lblFotoPersonaje.setVisible(true);
            mostrarImgPersonaje(this.nodo.dato);
            //Volvemos todos los botones invisibles
            visibilidadBotones(false);
        }
        fotoMickey(this.fotoMago);
    }

    //FN.MÉTODOS RECORRIDO
    //Métodos para hacer los botones de respuesta dudosa visibles o invisibles
    public void visibilidadBotones(boolean b) {
        bTalVez.setVisible(b);
        bIrrelevante.setVisible(b);
        bNoSe.setVisible(b);
        bAveces.setVisible(b);
        bProbablemente.setVisible(b);
        bRaravez.setVisible(b);
        bEnparte.setVisible(b);
        bNormalmente.setVisible(b);
        linea5.setVisible(b);
        linea6.setVisible(b);
        linea7.setVisible(b);
        linea8.setVisible(b);
        
    }

    //Método que hace todos lo botones visibles o invisibles
    public void todosBotones(boolean b) {
        bTalVez.setVisible(b);
        bIrrelevante.setVisible(b);
        bNoSe.setVisible(b);
        bAveces.setVisible(b);
        bProbablemente.setVisible(b);
        bRaravez.setVisible(b);
        bEnparte.setVisible(b);
        bNormalmente.setVisible(b);
        bSi.setVisible(b);
        bNo.setVisible(b);
        lbP.setVisible(b);
        linea.setVisible(b);
        linea1.setVisible(b);
        linea2.setVisible(b);
        linea3.setVisible(b);
        linea4.setVisible(b);
    }

    public void todosBotones2(boolean b) {
        bTalVez.setVisible(b);
        bIrrelevante.setVisible(b);
        bNoSe.setVisible(b);
        bAveces.setVisible(b);
        bProbablemente.setVisible(b);
        bRaravez.setVisible(b);
        bEnparte.setVisible(b);
        bNormalmente.setVisible(b);
        bSi.setVisible(b);
        bNo.setVisible(b);
        lbP.setVisible(b);
        linea.setVisible(b);
        linea1.setVisible(b);
        linea2.setVisible(b);
        linea3.setVisible(b);
        linea4.setVisible(b);
        lblFotoPersonaje.setVisible(b);
    }

    //Este método es para cuando ya se quiera dar el personaje final, se busque 
    //la foto del personaje y se muestre, adaptándose al label
    public void mostrarImgPersonaje(String personaje) {
        File direc, dir = new File("");
        try {
            if ((direc = new File("src/personajes/" + personaje + ".jpg")).exists()) {
                dir = new File(direc.getPath());
            } else if ((direc = new File("src/personajes/" + personaje + ".png")).exists()) {
                dir = new File(direc.getPath());
            } else if ((direc = new File("src/personajes/" + personaje + ".gif")).exists()) {
                dir = new File(direc.getPath());
            } else {
                System.out.println("aquiiiiiii");
            }
            //Nota: getPath() es para obtener la dirección relativa del archivo
            ImageIcon imag = new ImageIcon(dir.getPath());
            //A la imagen seleccionada le asignamos las dimensiones del label
            Icon imagen = new ImageIcon(imag.getImage().getScaledInstance(lblFotoPersonaje.getWidth(), lblFotoPersonaje.getHeight(), Image.SCALE_DEFAULT));
            //Al label le asignamos la imagen seleccionada
            lblFotoPersonaje.setIcon(imagen);
            //Lo dibujamos
            this.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtDialogo = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        lbPregunta = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtPersonaje = new javax.swing.JTextField();
        txtCaract = new javax.swing.JTextField();
        btBuscarFoto = new javax.swing.JButton();
        lblFotoPersonajeBuscado = new javax.swing.JLabel();
        bAgregar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbCaract = new javax.swing.JLabel();
        lbP1 = new javax.swing.JLabel();
        bRaravez = new javax.swing.JButton();
        bEnparte = new javax.swing.JButton();
        bNormalmente = new javax.swing.JButton();
        bProbablemente = new javax.swing.JButton();
        bAveces = new javax.swing.JButton();
        bSi = new javax.swing.JButton();
        bNo = new javax.swing.JButton();
        bTalVez = new javax.swing.JButton();
        bIrrelevante = new javax.swing.JButton();
        bNoSe = new javax.swing.JButton();
        lblFotoPersonaje = new javax.swing.JLabel();
        linea8 = new javax.swing.JLabel();
        linea5 = new javax.swing.JLabel();
        linea6 = new javax.swing.JLabel();
        linea7 = new javax.swing.JLabel();
        linea4 = new javax.swing.JLabel();
        linea3 = new javax.swing.JLabel();
        linea2 = new javax.swing.JLabel();
        linea1 = new javax.swing.JLabel();
        linea = new javax.swing.JLabel();
        lbMickey = new javax.swing.JLabel();
        lbReglas = new javax.swing.JLabel();
        lbCopa = new javax.swing.JLabel();
        lbMusic = new javax.swing.JLabel();
        lbReiniciar = new javax.swing.JLabel();
        lbMulti = new javax.swing.JLabel();
        lbDialogo = new javax.swing.JLabel();
        lbMago = new javax.swing.JLabel();
        lbPergaminoReglas = new javax.swing.JLabel();
        lbP = new javax.swing.JLabel();
        lbFondo = new javax.swing.JLabel();

        txtDialogo.setColumns(20);
        txtDialogo.setRows(5);
        jScrollPane1.setViewportView(txtDialogo);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mateo P. & María Z.");
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbPregunta.setFont(new java.awt.Font("Forte", 0, 20)); // NOI18N
        lbPregunta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPregunta.setText("PERSONAJE");
        jPanel2.add(lbPregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 520, 130));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Brush Script MT", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Mi personaje  ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 102, 20));

        txtPersonaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPersonajeActionPerformed(evt);
            }
        });
        jPanel1.add(txtPersonaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 140, 30));

        txtCaract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCaractActionPerformed(evt);
            }
        });
        jPanel1.add(txtCaract, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 200, 30));

        btBuscarFoto.setFont(new java.awt.Font("Brush Script MT", 1, 18)); // NOI18N
        btBuscarFoto.setText("Buscar foto");
        btBuscarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarFotoActionPerformed(evt);
            }
        });
        jPanel1.add(btBuscarFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, -1, 30));

        lblFotoPersonajeBuscado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(lblFotoPersonajeBuscado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 191, 130));

        bAgregar.setFont(new java.awt.Font("Brush Script MT", 1, 18)); // NOI18N
        bAgregar.setText("Agregar");
        bAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(bAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, -1, 30));

        jLabel1.setFont(new java.awt.Font("Brush Script MT", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Personaje");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 102, 20));

        lbCaract.setFont(new java.awt.Font("Brush Script MT", 1, 18)); // NOI18N
        lbCaract.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCaract.setText("¿En qué se diferencia de quién dije?");
        jPanel1.add(lbCaract, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 280, 20));

        lbP1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/pergaminoRespuestas.png"))); // NOI18N
        lbP1.setText(" ");
        jPanel1.add(lbP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 430, 460));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 490, 460));

        bRaravez.setFont(new java.awt.Font("Brush Script MT", 0, 24)); // NOI18N
        bRaravez.setText("* Rara vez *");
        bRaravez.setBorder(null);
        bRaravez.setBorderPainted(false);
        bRaravez.setContentAreaFilled(false);
        bRaravez.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bRaravez.setFocusPainted(false);
        bRaravez.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRaravezActionPerformed(evt);
            }
        });
        jPanel2.add(bRaravez, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 490, 320, 30));

        bEnparte.setFont(new java.awt.Font("Brush Script MT", 0, 24)); // NOI18N
        bEnparte.setText("* En parte *");
        bEnparte.setBorder(null);
        bEnparte.setBorderPainted(false);
        bEnparte.setContentAreaFilled(false);
        bEnparte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bEnparte.setFocusPainted(false);
        bEnparte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEnparteActionPerformed(evt);
            }
        });
        jPanel2.add(bEnparte, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 530, 320, 30));

        bNormalmente.setFont(new java.awt.Font("Brush Script MT", 0, 24)); // NOI18N
        bNormalmente.setText("* Normalmente *");
        bNormalmente.setBorder(null);
        bNormalmente.setBorderPainted(false);
        bNormalmente.setContentAreaFilled(false);
        bNormalmente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bNormalmente.setFocusPainted(false);
        bNormalmente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNormalmenteActionPerformed(evt);
            }
        });
        jPanel2.add(bNormalmente, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 460, 320, 20));

        bProbablemente.setFont(new java.awt.Font("Brush Script MT", 0, 24)); // NOI18N
        bProbablemente.setText("*Probablemente *");
        bProbablemente.setBorder(null);
        bProbablemente.setBorderPainted(false);
        bProbablemente.setContentAreaFilled(false);
        bProbablemente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bProbablemente.setFocusPainted(false);
        bProbablemente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bProbablementeActionPerformed(evt);
            }
        });
        jPanel2.add(bProbablemente, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, 320, 30));

        bAveces.setFont(new java.awt.Font("Brush Script MT", 0, 24)); // NOI18N
        bAveces.setText("*A veces *");
        bAveces.setBorder(null);
        bAveces.setBorderPainted(false);
        bAveces.setContentAreaFilled(false);
        bAveces.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bAveces.setFocusPainted(false);
        bAveces.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAvecesActionPerformed(evt);
            }
        });
        jPanel2.add(bAveces, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 430, 320, 20));

        bSi.setFont(new java.awt.Font("Brush Script MT", 0, 24)); // NOI18N
        bSi.setText("* Sí *");
        bSi.setBorder(null);
        bSi.setBorderPainted(false);
        bSi.setContentAreaFilled(false);
        bSi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bSi.setFocusPainted(false);
        bSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSiActionPerformed(evt);
            }
        });
        jPanel2.add(bSi, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 190, 320, 30));

        bNo.setFont(new java.awt.Font("Brush Script MT", 0, 24)); // NOI18N
        bNo.setText("* No *");
        bNo.setBorder(null);
        bNo.setContentAreaFilled(false);
        bNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bNo.setFocusPainted(false);
        bNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNoActionPerformed(evt);
            }
        });
        jPanel2.add(bNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 230, 320, 30));

        bTalVez.setFont(new java.awt.Font("Brush Script MT", 0, 24)); // NOI18N
        bTalVez.setText("* Tal vez *");
        bTalVez.setBorder(null);
        bTalVez.setContentAreaFilled(false);
        bTalVez.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bTalVez.setFocusPainted(false);
        bTalVez.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTalVezActionPerformed(evt);
            }
        });
        jPanel2.add(bTalVez, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, 320, 30));

        bIrrelevante.setFont(new java.awt.Font("Brush Script MT", 0, 24)); // NOI18N
        bIrrelevante.setText("* Irrelevante *");
        bIrrelevante.setBorder(null);
        bIrrelevante.setContentAreaFilled(false);
        bIrrelevante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bIrrelevante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bIrrelevanteActionPerformed(evt);
            }
        });
        jPanel2.add(bIrrelevante, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 350, 320, 30));

        bNoSe.setFont(new java.awt.Font("Brush Script MT", 0, 24)); // NOI18N
        bNoSe.setText("* No sé *");
        bNoSe.setBorder(null);
        bNoSe.setContentAreaFilled(false);
        bNoSe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bNoSe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNoSeActionPerformed(evt);
            }
        });
        jPanel2.add(bNoSe, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 310, 320, 30));

        lblFotoPersonaje.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lblFotoPersonaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 270, 310, 290));

        linea8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/linea.png"))); // NOI18N
        jPanel2.add(linea8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 450, 310, 10));

        linea5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/linea.png"))); // NOI18N
        jPanel2.add(linea5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 480, 310, 10));

        linea6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/linea.png"))); // NOI18N
        jPanel2.add(linea6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 520, 310, 10));

        linea7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/linea.png"))); // NOI18N
        jPanel2.add(linea7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 420, 310, 10));

        linea4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/linea.png"))); // NOI18N
        jPanel2.add(linea4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 380, 310, 10));

        linea3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/linea.png"))); // NOI18N
        jPanel2.add(linea3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 340, 310, 10));

        linea2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/linea.png"))); // NOI18N
        jPanel2.add(linea2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 300, 310, 10));

        linea1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/linea.png"))); // NOI18N
        jPanel2.add(linea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, 310, 10));

        linea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/linea.png"))); // NOI18N
        jPanel2.add(linea, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, 310, 10));

        lbMickey.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/mickey32.png"))); // NOI18N
        lbMickey.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbMickey.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbMickeyMouseClicked(evt);
            }
        });
        jPanel2.add(lbMickey, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 70, 30, 30));

        lbReglas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/reglas32.png"))); // NOI18N
        lbReglas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbReglas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbReglasMouseClicked(evt);
            }
        });
        jPanel2.add(lbReglas, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 250, 30, 30));

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
        jPanel2.add(lbMusic, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 200, 40, 30));

        lbReiniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/reiniciar32.png"))); // NOI18N
        lbReiniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbReiniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbReiniciarMouseClicked(evt);
            }
        });
        jPanel2.add(lbReiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 110, 30, 30));

        lbMulti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/multiMenu.png"))); // NOI18N
        lbMulti.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbMulti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbMultiMouseClicked(evt);
            }
        });
        jPanel2.add(lbMulti, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 30, 30));

        lbDialogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/dialogo2.png"))); // NOI18N
        jPanel2.add(lbDialogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 530, 160));

        lbMago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/magoG1.png"))); // NOI18N
        jPanel2.add(lbMago, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 430, 480));

        lbPergaminoReglas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/pergaminoReglas3.png"))); // NOI18N
        jPanel2.add(lbPergaminoReglas, new org.netbeans.lib.awtextra.AbsoluteConstraints(-470, 140, 480, 450));

        lbP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/pergaminoRespuestas.png"))); // NOI18N
        lbP.setText(" ");
        jPanel2.add(lbP, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, 430, 450));

        lbFondo.setBackground(new java.awt.Color(0, 255, 255));
        lbFondo.setForeground(new java.awt.Color(255, 255, 255));
        lbFondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbFondoMouseClicked(evt);
            }
        });
        jPanel2.add(lbFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 590));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSiActionPerformed
        if (this.nodo.ders != null) {
            this.nodo = this.nodo.ders;
            resSi();
            lbPregunta.setText(this.res);
        } else {
            this.res = "¡¡ADIVINÉ!!";
            //Impide que opriman este botón
            lbReglas.setVisible(false);
            //Ponemos al Mickey contento
            lbMago.setIcon(new ImageIcon(new ImageIcon(getClass().
                    getResource("/pictures/magoG1.png")).getImage()));
            lbPregunta.setText(this.res);
            todosBotones(false);
            //Agrega el puntaje
            if (puntaje <= 15) {
                JOptionPane.showMessageDialog(null, "<html><center> He ganado otra vez"
                        + " (Puntaje: " + JFGame.puntaje + ")</html>",
                        "Hey amiguito", 0, new javax.swing.ImageIcon(getClass()
                                .getResource("/Pictures/mickey32.png")));
            } else {
                JOptionPane.showMessageDialog(null, "<html><center> "
                        + "Me ha costado mucho, la victoria es tuya. (Puntaje: "
                        + "" + JFGame.puntaje + ")</html>");
            }

            String us = JFInicio.usuario;
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            String fecha = dateFormat.format(date);
            long fin = System.currentTimeMillis();
            long time = fin - ini;
            time = time / 1000;
            String tiempo = Long.toString(time);
            try {
                File file = new File("usuarios.txt");
                FileWriter escribir = new FileWriter(file, true);
                PrintWriter linea = new PrintWriter(escribir);
                met.crearArchivo(file);
                linea.println(us + "|" + JFGame.puntaje + "|" + tiempo + "|" + fecha);
                linea.close();
                escribir.close();
                puntaje = 1;
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_bSiActionPerformed

    private void bTalVezActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTalVezActionPerformed
        //Guardamos el otro hijo en la pila de dudas por si nos llegamos a 
        //equivocar al final
        met.guardarDuda(nodo.izqn);
        this.nodo = nodo.ders;
        resSi();
        lbPregunta.setText(res);
    }//GEN-LAST:event_bTalVezActionPerformed

    private void bIrrelevanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bIrrelevanteActionPerformed
        //Guardamos la otra respuesta en la pila de dudas por si nos llegamos a 
        //equivocar al final
        met.guardarDuda(this.nodo.ders);
        this.nodo = this.nodo.izqn;
        resNo();
        lbPregunta.setText(res);
    }//GEN-LAST:event_bIrrelevanteActionPerformed

    private void bNoSeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNoSeActionPerformed
        int op = random(0, 1);
        //En los dos casos guardamos la otra respuesta por si nos llegamos a
        //equivocar
        puntaje--;
        if (op == 0) {
            met.guardarDuda(this.nodo.izqn);
            this.nodo = this.nodo.ders;
            resSi();
        } else {
            met.guardarDuda(this.nodo.ders);
            this.nodo = this.nodo.izqn;
            resNo();
        }
        lbPregunta.setText(res);
    }//GEN-LAST:event_bNoSeActionPerformed

    private void bNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNoActionPerformed
        if (this.nodo.izqn != null) {
            this.nodo = this.nodo.izqn;
            resNo();
            lbPregunta.setText(this.res);
        } else {
            //Revisamos si la pila no está vacía para intentar seguir adivinando
            if (!met.pilaDudaVacia()) {
                this.nodo = met.nodoNoRendirse();
                resNo();
                visibilidadBotones(true);
                lblFotoPersonaje.setVisible(false);
                lbPregunta.setText(this.res);
            } else {
                sw = true;
                lbReglas.setVisible(false);
                JOptionPane.showMessageDialog(null, "<html><center> Me has ganado"
                        + " (Puntaje: " + JFGame.puntaje + ") <br> Dime en que se diferenciaba "
                        + "tu personaje con el mío y muestrame su rostro.</html>",
                        "Hey amiguito", 0, new javax.swing.ImageIcon(getClass()
                                .getResource("/Pictures/mickey32.png")));
                sw = false;
                todosBotones2(false);
                jPanel1.setOpaque(false);
                jPanel1.setVisible(true);
                String us = JFInicio.usuario;
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                String fecha = dateFormat.format(date);
                long fin = System.currentTimeMillis();
                long time = fin - ini;
                time = time / 1000;
                String tiempo = Long.toString(time);
                try {
                    File file = new File("usuarios.txt");
                    FileWriter escribir = new FileWriter(file, true);
                    PrintWriter linea = new PrintWriter(escribir);
                    met.crearArchivo(file);
                    linea.println(us + "|" + JFGame.puntaje + "|" + tiempo + "|" + fecha);
                    linea.close();
                    escribir.close();
                    puntaje = 1;
                } catch (Exception e) {
                    System.out.println(e);
                }
                todosBotones(false);
                lbPregunta.setText("Dame tu conocimiento");
                lbCaract.setText("¿En qué se diferencia de " + nodo.dato + "?");
            }

        }
    }//GEN-LAST:event_bNoActionPerformed

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

    private void lbReglasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbReglasMouseClicked
        lbMago.setIcon(new ImageIcon(new ImageIcon(getClass().
                getResource("/pictures/magoG2.png")).getImage()));
        AnimationClass pReglas = new AnimationClass();
        pReglas.jLabelXRight(-470, 200, 10, 5, lbPergaminoReglas);

        //Izquierda
        AnimationClass pReglass = new AnimationClass();
        pReglass.jLabelXLeft(200, -470, 10, 5, lbPergaminoReglas);

        if (this.reglaVista == false) {
            visibilidadBotones(false);
            todosBotones(false);
            this.reglaVista = true;
            AudioClip audioReglas = java.applet.Applet.newAudioClip(getClass().getResource("/pictures/audioReglas.wav"));
            audioReglas.play();
        } else {
            visibilidadBotones(true);
            todosBotones(true);
            this.reglaVista = false;
            lbMago.setIcon(new ImageIcon(new ImageIcon(getClass().
                    getResource("/pictures/magoG1.png")).getImage()));
        }


    }//GEN-LAST:event_lbReglasMouseClicked

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
        //Inicia o detiene la música
        if (met.play == 1) {
            lbMusic.setIcon(new ImageIcon(getClass().getResource("/pictures/musicStop.png")));
            met.sonido.stop();
            met.play = 0;
        } else {
            lbMusic.setIcon(new ImageIcon(getClass().getResource("/pictures/musicPlay.png")));
            //Reproduce el sonido una y otra vez
            met.sonido.loop();
            met.play = 1;
        }
    }//GEN-LAST:event_lbMusicMouseClicked

    private void lbFondoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFondoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbFondoMouseClicked

    private void lbMultiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMultiMouseClicked
        //Derecha
        AnimationClass mickey = new AnimationClass();
        mickey.jLabelXRight(-30, 10, 10, 5, lbMickey);

        AnimationClass music = new AnimationClass();
        music.jLabelXRight(-30, 10, 10, 5, lbMusic);

        AnimationClass copa = new AnimationClass();
        copa.jLabelXRight(-30, 10, 10, 5, lbCopa);

        AnimationClass reglas = new AnimationClass();
        reglas.jLabelXRight(-30, 10, 10, 5, lbReglas);

        AnimationClass reiniciar = new AnimationClass();
        reiniciar.jLabelXRight(-30, 10, 10, 5, lbReiniciar);

        //Izquierda
        AnimationClass mickeyy = new AnimationClass();
        mickeyy.jLabelXLeft(10, -30, 10, 5, lbMickey);

        AnimationClass musicc = new AnimationClass();
        musicc.jLabelXLeft(10, -30, 10, 5, lbMusic);
        

        AnimationClass copaa = new AnimationClass();
        copaa.jLabelXLeft(10, -30, 10, 5, lbCopa);

        AnimationClass reglass = new AnimationClass();
        reglas.jLabelXLeft(10, -30, 10, 5, lbReglas);

        AnimationClass reiniciarr = new AnimationClass();
        reiniciarr.jLabelXLeft(10, -30, 10, 5, lbReiniciar);
    }//GEN-LAST:event_lbMultiMouseClicked

    private void lbReiniciarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbReiniciarMouseClicked
        //Recarga el juego
        met.sonido.stop();
        JFGame game = new JFGame();
        this.dispose();
        game.setLocationRelativeTo(null);
        game.setVisible(true);

    }//GEN-LAST:event_lbReiniciarMouseClicked

    private void txtPersonajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPersonajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonajeActionPerformed

    private void txtCaractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCaractActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCaractActionPerformed

    private void btBuscarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarFotoActionPerformed
        //Este try catch es para que abra el buscador de la imagen con el modelo
        //de Windows (Sólo por un lindo diseño)
        try {
            this.setLocationRelativeTo(this);
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JFGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Fin try-catch

        //El personaje va a escoger una imagen que guardaremos junto a los archivos
        //de  nuestro proyecto
        //Aquí se instancia el buscador
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("¡Busca la imagen de tu personaje!  ;)");
        //Si oprime el botón aceptar entra en la condición
        if (fc.showDialog(null, "¡Así luce el personaje!") == JFileChooser.APPROVE_OPTION) {
            direccion = fc.getSelectedFile();
            terminaEn = met.terminaEn(direccion.getName());
            if (direccion.getName().endsWith("jpg")
                    || direccion.getName().endsWith("png")
                    || direccion.getName().endsWith("gif")) {
                //Esta variable es muy importante porque es la que guarda la imagen
                bytesImag = met.abrirImagen(direccion);
                ImageIcon imagSel = new ImageIcon(direccion.toString());
                //A la imagen seleccionada le asignamos las dimensiones del label
                Icon imagen = new ImageIcon(imagSel.getImage().getScaledInstance(lblFotoPersonajeBuscado.getWidth(), lblFotoPersonajeBuscado.getHeight(), Image.SCALE_DEFAULT));
                //Al label le asignamos la imagen seleccionada
                lblFotoPersonajeBuscado.setIcon(imagen);
                //Lo dibujamos
                this.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una imagen");
            }

        }
    }//GEN-LAST:event_btBuscarFotoActionPerformed

    private void bAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAgregarActionPerformed
        String nomPer = txtPersonaje.getText();
        if (nomPer != "" && nomPer != null) {
            //Al nombre de la imagen le añadimos el .tipodeimagen
            nomPer = nomPer + terminaEn;
            //Se específica la dirección dentro del directorio del proyecto en la carpeta de 
            //personajes
            direccion = new File("src/personajes/" + nomPer);
            if (direccion.getName().endsWith("jpg")
                    || direccion.getName().endsWith("png")
                    || direccion.getName().endsWith("gif")) {
                //Se guarda la imagen dándole la dirección y "los bytes de la imagen"
                String respuesta = met.guardarImagen(direccion, bytesImag);
                //NOTA: Sí es necesario que devuelva ese String porque o sino
                //se daña la imagen
                //Insertamos el personaje en el árbol
            }
            met.insertar(txtPersonaje.getText(), txtCaract.getText(), nodo);
            JOptionPane.showMessageDialog(null, "Aprendí la lección. Juguemos de nuevo", "Hey amiguito", 0,
                    new javax.swing.ImageIcon(getClass().getResource("/Pictures/mickey32.png")));
            met.sonido.stop();
            ini = System.currentTimeMillis();
            JFGame game = new JFGame();
            this.dispose();
            game.setLocationRelativeTo(null);
            game.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "No nos ha indicado el nombre del personaje");
        }
    }//GEN-LAST:event_bAgregarActionPerformed

    private void bAvecesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAvecesActionPerformed
        //Guardamos el otro hijo en la pila de dudas por si nos llegamos a 
        //equivocar al final
        met.guardarDuda(nodo.izqn);
        this.nodo = nodo.ders;
        resSi();
        lbPregunta.setText(res);
    }//GEN-LAST:event_bAvecesActionPerformed

    private void bRaravezActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRaravezActionPerformed
        //Guardamos la otra respuesta en la pila de dudas por si nos llegamos a 
        //equivocar al final
        met.guardarDuda(this.nodo.ders);
        this.nodo = this.nodo.izqn;
        resNo();
        lbPregunta.setText(res);
    }//GEN-LAST:event_bRaravezActionPerformed

    private void bEnparteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEnparteActionPerformed
       //Guardamos el otro hijo en la pila de dudas por si nos llegamos a 
        //equivocar al final
        met.guardarDuda(nodo.izqn);
        this.nodo = nodo.ders;
        resSi();
        lbPregunta.setText(res);
    }//GEN-LAST:event_bEnparteActionPerformed

    private void bNormalmenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNormalmenteActionPerformed
        //Guardamos el otro hijo en la pila de dudas por si nos llegamos a 
        //equivocar al final
        met.guardarDuda(nodo.izqn);
        this.nodo = nodo.ders;
        resSi();
        lbPregunta.setText(res);
    }//GEN-LAST:event_bNormalmenteActionPerformed

    private void bProbablementeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bProbablementeActionPerformed
        //Guardamos el otro hijo en la pila de dudas por si nos llegamos a 
        //equivocar al final
        met.guardarDuda(nodo.izqn);
        this.nodo = nodo.ders;
        resSi();
        lbPregunta.setText(res);
    }//GEN-LAST:event_bProbablementeActionPerformed

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
            java.util.logging.Logger.getLogger(JFGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFGame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAgregar;
    private javax.swing.JButton bAveces;
    private javax.swing.JButton bEnparte;
    private javax.swing.JButton bIrrelevante;
    private javax.swing.JButton bNo;
    private javax.swing.JButton bNoSe;
    private javax.swing.JButton bNormalmente;
    private javax.swing.JButton bProbablemente;
    private javax.swing.JButton bRaravez;
    private javax.swing.JButton bSi;
    private javax.swing.JButton bTalVez;
    private javax.swing.JButton btBuscarFoto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCaract;
    private javax.swing.JLabel lbCopa;
    private javax.swing.JLabel lbDialogo;
    private javax.swing.JLabel lbFondo;
    private javax.swing.JLabel lbMago;
    private javax.swing.JLabel lbMickey;
    private javax.swing.JLabel lbMulti;
    private javax.swing.JLabel lbMusic;
    private javax.swing.JLabel lbP;
    private javax.swing.JLabel lbP1;
    private javax.swing.JLabel lbPergaminoReglas;
    private javax.swing.JLabel lbPregunta;
    private javax.swing.JLabel lbReglas;
    private javax.swing.JLabel lbReiniciar;
    private javax.swing.JLabel lblFotoPersonaje;
    private javax.swing.JLabel lblFotoPersonajeBuscado;
    private javax.swing.JLabel linea;
    private javax.swing.JLabel linea1;
    private javax.swing.JLabel linea2;
    private javax.swing.JLabel linea3;
    private javax.swing.JLabel linea4;
    private javax.swing.JLabel linea5;
    private javax.swing.JLabel linea6;
    private javax.swing.JLabel linea7;
    private javax.swing.JLabel linea8;
    private javax.swing.JTextField txtCaract;
    private javax.swing.JTextArea txtDialogo;
    private javax.swing.JTextField txtPersonaje;
    // End of variables declaration//GEN-END:variables
}
