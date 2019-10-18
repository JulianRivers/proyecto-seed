/**
 * ---------------------------------------------------------------------
 * $Id: Nodo.java,v 2.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;
 
 /**
 * Implementacion de la Clase Nodo de una Lista Simple.
 * @param <T> Tipo de dato almacenados en el Nodo.
 * @author Marco Adarme
 * @version 2.0
 */
 class Nodo<T>{
    
    ////////////////////////////////////////////////////////////
    // Nodo - Atributos ////////////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Informacion del Nodo
     */
    private T info;
    
    /**
     * Siguiente Nodo 
     */
    private Nodo<T> sig;
    
    
    
    ////////////////////////////////////////////////////////////
    // Nodo - Implementacion de Metodos ////////////////////////
    ////////////////////////////////////////////////////////////

    /**
     * Constructor de un Nodo vacio. <br>
     * <b>post: </b> Se construyo un Nodo vacio con la informacion en NULL.
     */
    public Nodo(){
        this.info=null;
        this.sig=null;        
    }
	
    /**
     * Contructor inicializa los miembros datos de Nodo. <br>
     * <b>post: </b> Se construyo el nodo con los datos especificados.<br>
     * @param info Objeto de tipo T con la informacion del Nodo.<br>
     * @param sig Siguiente Nodo que continua al Nodo actual.
     */
    public Nodo(T info, Nodo<T> sig){
        this.info=info;
        this.sig=sig;
    }
	
    /**
     * Obtiene la información del nodo. <br>
     * <b>post: </b> Se retorno la informacion del Nodo.<br>
     * @return Un objeto tipo T con la informacion del Nodo.
     */
    protected T getInfo(){
        return this.info;
    }
	
    /**
     * Obtiene el Nodo Siguiente. <br>
     * <b>post: </b> Se retorno el siguiente nodo. <br>
     * @return El nodo siguiente o null si el último nodo o si es una Lista con cardinalidad 1. 
     */
    protected Nodo<T> getSig(){        
        return this.sig;        
    }
	
    /**
     * Cambia la informacion del Nodo del campo Info. <br>
     * <b>post: </b> Se edito la informacion del Nodo. <br>
     * @param nuevo Nuevo objeto que desea almacenarse en el Nodo
     */
    protected void setInfo(T nuevo){        
        this.info=nuevo;
    }
	
    /**
     * Cambia el nodo siguiente. <br>
     * <b>post: </b> Se edito el Nodo siguiente.<br>.
     * @param nuevo Nuevo Nodo siguiente.
     */
    protected void setSig(Nodo<T> nuevo){
        this.sig=nuevo;
    }
    
}//Fin de la Clase Nodo