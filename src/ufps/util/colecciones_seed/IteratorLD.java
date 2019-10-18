/**
 * ---------------------------------------------------------------------
 * $Id: IteratorLD.java,v 2.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;
import java.util.Iterator;
/**
 * Implementacion de clase para el manejo de Iteradores en una Lista Doble Enlazada<T>.
 * @param <T> Tipo de datos a almacenar en la lista
 * @author Marco Adarme
 * @version 2.0
 */
public class IteratorLD<T> implements Iterator<T>
{
    
    ////////////////////////////////////////////////////////////
    // IteratorLD - Atributos //////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Nodo de la Lista a Iterar 
     */
    private NodoD<T> posicion;   
    
    
    
    
    ////////////////////////////////////////////////////////////
    // IteratorLD - Implementacion de Metodos //////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Constructor con parametros de la clase Iterador de una lista doble <br>
     * <b> post: </b> Se crea un iterador de lista doble. <br>
     * @param posicion es un tipo Nodo<T> que posee un nodo de la lista
     */
    IteratorLD(NodoD<T> posicion){            
        this.posicion=posicion;	            
    }

    /**
     * Metodo que informa si existe otro elemento en la lista para seguir iterando<br>
     * <b> post: </b> Se retorna si existen aun datos por iterar en la Lista. <br>
     * @return un tipo boolean que informa si existe o no un dato en la lista, desde la posición 
     * actual del cursor.
     */
    @Override
    public boolean hasNext(){            
        return (posicion!=null);            
    }

    /**
     * Metodo que retorna un dato de la posición actual del cursor del iterador.<br>
     * <b> post: </b> Se ha retornado el dato en la posicion actual de la iteracion. <br>
     * El cursor queda en la siguiente posición.
     * @return un tipo T que contiene el dato actual
     */
    @Override
    public T next(){            
        if(!this.hasNext()){                
            System.err.println("Error no hay mas elementos");
        return null;                
        }            
        NodoD<T> actual=posicion;
        posicion=posicion.getSig();            
        return(actual.getInfo());
    }
    
    /**
     *
     */
    @Override
    public void remove(){}

}//Fin de Clase IteratorLD