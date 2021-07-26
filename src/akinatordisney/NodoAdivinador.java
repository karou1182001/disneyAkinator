
 package akinatordisney;


/**
 *
 * @author María Zapata y Mateo Peralta
 * La lógica de este árbol es que va a ser un árbol de preguntas y personajes
 *tal que los personajes siempre están como nodos hojas, y los demás nodos 
 *serán pregubtas. El hijos izquiero va a representar el "no" y el hijo derecho, 
 *el "sí"
 */
public class NodoAdivinador
{
    
    public String dato;
    public NodoAdivinador izqn, ders;
    
    
    //Constructores
    public NodoAdivinador(String elem) 
    {
        dato= elem;
        NodoAdivinador izqn, ders = null;
    }

    public NodoAdivinador()
    {
        NodoAdivinador izqn, ders = null;
    }
    
    
}
