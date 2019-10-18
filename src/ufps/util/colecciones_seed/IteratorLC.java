/**
 * ---------------------------------------------------------------------
 * $Id: IteratorLC.java,v 2.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;
import java.util.Iterator;

/**
 * Implementacion de un Iterador para Lista Circular.
 * @param <T> Tipo de datos sobre los que se iteran.
 * @author Marco Adarme
 * @version 2.0
 */
public class IteratorLC<T> implements Iterator<T>
{
    
    ////////////////////////////////////////////////////////////
    // IteratorLC - Atributos //////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /*
     * Representa el Nodo cabeza del Iterator
     */
    private Nodo<T> cabeza;  
    
    /*
     * Representa la posicion actual del Iterator
     */
    private Nodo<T> posicion;    
    
    
    
	
    ////////////////////////////////////////////////////////////
    // IteratorLC - Implementacion de Metodos //////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Constructor con parametros de la clase IteratorLC. <br>
     * <b> post: </b> Se crea un iterador de Lista Circular. <br>
     * @param cab es de tipo Nodo<T> que contiene el nodo cabeza de la lista.
     */
    IteratorLC(Nodo<T> cab){            
        this.cabeza=cab;
        this.posicion=this.cabeza.getSig();            
    }

    /**
     * Método que informa si existe otro elemento en la lista para seguir iterando. <br>
     * <b> post: </b> Se retorna si existen aun datos por iterar en la Lista. <br>
     * @return Un tipo boolean que informa si existe o no un dato en la lista desde la posición.
     */
    @Override
    public boolean hasNext(){            
        return (this.posicion!=this.cabeza);                
    }

    /**
     * Método que retorna un dato de la posición actual del cursor del iterador. <br>
     * <b> post: </b> Se ha retornado el dato en la posicion actual de la iteracion. <br>
     * El cursor queda en la siguiente posición. <br>
     * @return un tipo T que contiene el dato actual
     */
    @Override
    public T next(){            
        if(!this.hasNext())
            return (null);
        Nodo<T> aux = posicion;
        this.posicion=this.posicion.getSig();
        return(aux.getInfo());
    }
    
    /**
     *
     */
    @Override
    public void remove(){}
	
}//Fin de la Clase IteratorLC