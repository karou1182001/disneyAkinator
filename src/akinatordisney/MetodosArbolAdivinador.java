
package akinatordisney;

import java.applet.AudioClip;
import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author María Zapata y Mateo Peralta
 * Esta clase es la más importante de todo el proyecto. Debido a que contiene 
 *la mayor parte de los métodos fundamentales. Tales como los de conexión con el
 *archivo para guardar los personajes, los métodos para recuperar el árbol con base
 *al archivo, los métodos para rearmar el archivo cuando se inserta algo al árbol,
 *los métodos de inserción, métodos para guardar imágenes y el puntaje del juagdor.
 */
public class MetodosArbolAdivinador 
{

    public static NodoAdivinador raiz;
    public AudioClip sonido = java.applet.Applet.newAudioClip(getClass().
    getResource("/pictures/musica.wav"));
    public int play=1;
    FileInputStream entrada;
    FileOutputStream salida;
    Stack pilaDudas = new Stack();

    public MetodosArbolAdivinador()
    {
        raiz = null;
    }

    NodoAdivinador a;
    long numero = 1, nivel = 0;

    //MÉTODOS DE LOS BOTONES 
    /**Este método es para guardar las direcciones de los nodos donde el
     usuario contetso respuestas dudosas*/
    public void guardarDuda(NodoAdivinador nodo) {
        if (nodo != null) {
            this.pilaDudas.push(nodo);
        }
    }

    public boolean pilaDudaVacia() {
        boolean b = false;
        if (pilaDudas.empty()) {
            b = true;
        }
        return b;
    }

    public NodoAdivinador nodoNoRendirse() {
        NodoAdivinador nodoNoRendirse = new NodoAdivinador();
        if (!pilaDudas.empty()) {
            nodoNoRendirse = (NodoAdivinador) pilaDudas.pop();
        }
        return nodoNoRendirse;
    }
    //FN.MÉTODOS BOTONES

    //MÉTODOS DE INSERCIÓN DE PERSONAJES
    /**Método de insercion de un elemento en el arbol:
    Si vamos a insertar un personaje es porque Akinator no adivinó, entonces 
    debemos modificar 3 nodos y el archivo auxiliar donde guardamos los personajes.*/
    public void insertar(String personaje, String caract, NodoAdivinador nodo) {
        if (raiz == null) {
            //Si el nodo es la raíz se inserta como primer elemento
            raiz = new NodoAdivinador(personaje);
            guardarEnDatosAkinator(raiz.dato, numero, false, nivel);
        } else {
            //EN EL ÁRBOL
            //A su hijo izquierdo (no) se le va asignar el personaje que pensábamos
            //que era pero no es
            nodo.izqn = new NodoAdivinador(nodo.dato);
            //A su hijo derecho (sí), se le va a asignar el personaje que otorgó
            //el usuario
            nodo.ders = new NodoAdivinador(personaje);
            //Y la nueva raíz de los dos va a ser la característica diferenciadora
            nodo.dato = caract;

            //EN EL ARCHIVO
            //A la par que se va realizando la inserción de datos en el árbol,
            //hay que ir modificando el archivo.
            //No obstante, lo más factible es eliminar el archivo, recorrer el árbol
            //por niveles e insertar de nuevo en el archivo
            eliminarDatosAkinator();
            recorrerPorNivel(raiz);

        }
    }

    public void BusN(String info, NodoAdivinador nodo, String carper, String per) {
        if (nodo == null) {
            return;

        } else {
            if (nodo.dato.equals(info)) {
                insertar(per, carper, nodo);
                return;
            }
        }

        BusN(info, nodo.izqn, carper, per);
        BusN(info, nodo.izqn, carper, per);
    }

    /**Recorrido por niveles. 
    Este recorrido es necesario al momento de insertar
    //un nodo en el árbol. Esto por hay que hacer varias modificaciones al árbol
    //que por consecuencia cambian el orden en el que está el archivo. Entonces
    //toca recorrer el árbol por niveles para volver a llenar el archivo.*/
    public void recorrerPorNivel(NodoAdivinador nodo) {
        long numero = 1, nivel = 0;
        //Si el árbol está vacío
        if (nodo != null) {
            Queue q = new LinkedList();
            Queue p = new LinkedList();
            Queue n = new LinkedList();

            q.add(nodo);
            p.add(nivel);
            n.add(numero);
            while (!q.isEmpty()) {
                //Aquí se utiliza un nodo auxiliar para recorrer y se le asigna 
                //el dato que se elimina
                NodoAdivinador aux = (NodoAdivinador) q.remove();
                //Y eliminamos el dato también de p
                long niv = (long) p.remove();
                long num = (long) n.remove();

                //Ver si es una característica o personaje
                if (aux.ders != null && aux.izqn != null) {
                    //Si es caracerística
                    guardarEnDatosAkinator(aux.dato, num, true, niv);
                } else {
                    //Si es personaje
                    guardarEnDatosAkinator(aux.dato, num, false, niv);
                }

                if (aux.izqn != null) {
                    q.add(aux.izqn);
                    p.add(niv + 1);
                    n.add(2 * num);
                }
                if (aux.ders != null) {
                    q.add(aux.ders);
                    p.add(niv + 1);
                    n.add(2 * num + 1);
                }
            }
        }
    }
    /**Método para eliminar el contenido del archivo*/
    public void eliminarDatosAkinator() {
        try {
            //Instanciar archivo
            File file = new File("datosAkinator.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write("");
            bw.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    /**Método para guardar datos en el archivo Datos Akinator*/
    public void guardarEnDatosAkinator(String dato, long num, boolean preg,
            long nivel) {
        try {
            //-VARIABLES
            //Instanciar archivo
            File file = new File("datosAkinator.txt");
            //Variable para escribir en el archivo (Se le indica el archivo y el true
            //es para que escriba al final de él)
            FileWriter escribir = new FileWriter(file, true);
            PrintWriter linea = new PrintWriter(escribir);
            //FN. VARIABLES

            //Verificar si el archivo existe y sino, crear uno
            //NOTA: El archivo se encuentra guardado en el mismo directorio que el 
            //proyecto
            crearArchivo(file);
            linea.println(num + "|" + dato + "|" + preg + "|" + nivel);
            linea.close();
            escribir.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    /**Método para crear un archivo en caso de que no exista*/
    public void crearArchivo(File archivo) {
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
            }
        }
    }
    //FN. MÉTODOS DE INSERCIÓN DE PERSONAJES

    //MÉTODO DE RECUPERACIÓN DE DATOS
    /**Como un árbol se almacena en memoria pricipal, cada vez que se cierra el 
    programa o se cambia de clase, toca recuperar la información que tenía el
    árbol con el archivo auxiliar que hemos creado.*/
    public NodoAdivinador recuperarArbol(NodoAdivinador nodo, long n, boolean esraiz) {
        try {
            //Para instanciar, leer, y ver lo que almacena al archivo
            File file = new File("datosAkinator.txt");
            FileReader leer = new FileReader(file);
            BufferedReader alm = new BufferedReader(leer);
            String cadena, dato, numero;
            long num;
            while ((cadena = alm.readLine()) != null) {
                //Como | es un caracter especial, hay que colocar \\
                numero = cadena.split("\\|")[0];
                num = Integer.parseInt(numero);
                dato = cadena.split("\\|")[1];

                if (num == 2 * n) {
                    nodo.izqn = new NodoAdivinador(dato);
                    raiz = recuperarArbol(nodo.izqn, 2 * n, false);
                } else if (num == 2 * n + 1) {
                    nodo.ders = new NodoAdivinador(dato);
                    raiz = recuperarArbol(nodo.ders, 2 * n + 1, false);
                } else if (esraiz == true) {
                    raiz = new NodoAdivinador(dato);
                    raiz = recuperarArbol(raiz, 1, false);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return raiz;
    }
    //F. MÉTODO DE RECUPERACIÓN DE DATOS

    //MÉTODOS PARA PUNTAJES DE USUARIOS
    /**Método para guardar el puntaje del usuario en el archivo*/
    public void usuarios(String nombre, long puntaje, double tiempo, Date fecha) {
        try {
            //-VARIABLES
            //Instanciar archivo
            File file = new File("usuarios.txt");
            //Variable para escribir en el archivo (Se le indica el archivo y el true
            //es para que escriba al final de él)
            FileWriter escribir = new FileWriter(file, true);
            PrintWriter linea = new PrintWriter(escribir);
            //FN. VARIABLES

            //Verificar si el archivo existe y sino, crear uno
            //NOTA: El archivo se encuentra guardado en el mismo directorio que el 
            //proyecto
            crearArchivo(file);
            linea.println(nombre + "|" + puntaje + "|" + tiempo + "|" + fecha);
            linea.close();
            escribir.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    //FN.MÉTODOS PARA PUNTAJES DE USUARIOS

    //MÉTODOS PARA BÚSQUEDA DE IMAGEN PARA ASIGNARSELA AL PERSONAJE
    /**Método para abrir la imagen*/
    public byte[] abrirImagen(File archivo) {
        byte[] bytesImag = new byte[1024 * 1000];
        try {
            entrada = new FileInputStream(archivo);
            entrada.read(bytesImag);
        } catch (Exception e) {
        }
        return bytesImag;
    }

    /**Método para guardar una imagen*/
    public String guardarImagen(File archivo, byte[] bytesImag) {
        String respuesta = "";
        try {
            salida = new FileOutputStream(archivo);
            salida.write(bytesImag);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "La imagen no pudo ser guardada");
        }
        return respuesta;
    }

    /**Metodo para saber que tipo de imagen es*/
    public String terminaEn(String palabra) {
        String terminaEn = "";
        if (palabra.endsWith("jpg")) {
            terminaEn = ".jpg";
        } else if (palabra.endsWith("png")) {
            terminaEn = ".png";
        } else if (palabra.endsWith("gif")) {
            terminaEn = ".gif";
        }

        return terminaEn;
    }
    //FN. MÉTOFOS DE BÚSQUEDA DE IMAGEN

}
