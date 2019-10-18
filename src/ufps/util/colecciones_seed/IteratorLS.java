/**
 * ---------------------------------------------------------------------
 * $Id: IteratorLS.java,v 2.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 * * @author Marco Adarme
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;
 import java.util.Iterator;
 
/**
 * Implementacion de un Iterador para Lista Simple.
 * @param <T> Tipo de datos sobre los que se iteran.
 * @author Marco Adarme
 * @version 2.0
 */
public class IteratorLS<T> implements Iterator<T>{

    ////////////////////////////////////////////////////////////
    // IteratorLS - Atributos //////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Nodo de la Lista a Iterar
     */
    private Nodo<T> posicion; 
    
    
    
    ////////////////////////////////////////////////////////////
    // IteratorLS - Implementacion de Metodos //////////////////
    ////////////////////////////////////////////////////////////
	
    /**
     * Constructor con parametros de la clase iterador de la clase lista simple. <br>
     * <b> post: </b> Se crea un iterador de lista simple. <br>
     * @param posicion es de tipo Nodo<T> el cual contiene un nodo de la lista a iterar.
     */
    IteratorLS(Nodo<T> pos){            
        this.posicion=pos;            
    }

    /**
     * Metodo que informa si existe otro elemento en la lista para seguir iterando. <br>
     * <b> post: </b> Se retorna si existen aun datos por iterar en la Lista. <br>
     * @return Un tipo boolean que informa si existe o no un dato en la lista desde la posición.
     */
    @Override
    public boolean hasNext(){            
        return (posicion!=null);            
    }

    /**
     * Metodo que retorna un dato de la posición actual del cursor del iterador. <br>
     * <b> post: </b> Se ha retornado el dato en la posicion actual de la iteracion. <br>
     * El cursor queda en la siguiente posición. <br>
     * @return un tipo T que contiene el dato actual
     */
    @Override
    public T next(){            
        if(!this.hasNext()){                
        System.err.println("Error no hay mas elementos");
        return null;                
        }            
        Nodo<T> actual=posicion;
        posicion=posicion.getSig();            
        return(actual.getInfo());
    }

    /**
     *
     */
    @Override
    public void remove(){}

}//Fin de la Clase IteratorLS