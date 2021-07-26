
package akinatordisney;


import java.io.IOException;
import jframes.JFInicio;

/**
 *
 * @author María Zapata y Mateo Peralta
 * En esta clase sólo se llama al JFrame de inicio para iniciar el juego
 */
public class AkinatorDisney 
{

    /**Llamamos a la pantalla de inicio*/
    public static void main(String[] args) throws IOException 
    {
        //Abrir primer JFrame
        JFInicio p= new JFInicio();
        p.setSize(1010, 580);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
       
    }
    
}
