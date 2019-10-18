/**
 * ---------------------------------------------------------------------
 * $Id: NodoD.java,v 2.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;

/**
 * Implementacion de clase que contiene la informacion de los nodos de una lista Doble
 * @param <T> Tipo de datos a almacenar dentro del Nodo Doble.
 * @author Marco Adarme
 * @version 2.0
 */

class NodoD<T> implements java.io.Serializable
{
    
    ////////////////////////////////////////////////////////////
    // NodoD - Atributos ///////////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Informacion del Nodo
     */
    private T info;      
    
    /**
     * Nodo Anterior 
     */
    private NodoD<T> ant;   
    
    /**
     * Nodo Siguiente 
     */
    private NodoD<T> sig;   

       
    
    ////////////////////////////////////////////////////////////
    // NodoD - Implementacion de Metodos ///////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Constructor de un Nodo Doble vacio. <br>
     * <b>post: </b> Se construyo un Nodo Doble vacio con la informacion en NULL.
     */
    public NodoD() {
        this.info=null;
        this.ant=null;
        this.sig=null; 
    }

    /**
     * Constructor con parametros de la clase NodoD <br>
     * <b>post: </b> Se construyo un nodo doble con los datos especificados.<br>
     * @param info de tipo T y contiene la informacion del nodo
     * @param sig es del tipo NodoD<T> y contiene la direccion del Nodo siguiente
     * @param ant es de tipo NodoD<T> y contiene la direccion del Nodo anterior
     */
    public NodoD(T info, NodoD<T> sig, NodoD<T> ant){        
        this.info=info;
        this.sig=sig;
        this.ant=ant;        
    }
    
    /**
     * Metodo que permite obtener el contenido del Nodo doble<br>
     * <b>post: </b> Se retorno la informacion del Nodo doble.<br>
     * @return un tipo T que contiene la informacion del Nodo doble
     */
    protected T getInfo(){        
        return(this.info);        
    }
    
    /**
     * Metodo que permite obtener la dirección del Nodo anterior al actual<br>
     * <b>post: </b> Se retorno la informacion del Nodo anterior al actual.<br>
     * @return un Nodo<T> que contiene 
     */
    protected NodoD<T> getAnt(){        
        return (this.ant);        
    }
    
    /**
     * Metodo que permite devolver el nodo siguiente al que apunta el Nodo doble<br>
     * <b>post: </b> Se retorno la informacion del Nodo siguiente al actual.<br>
     * @return un tipo NodoD<T> que contiene el nodo siguiente
     */
    protected NodoD<T> getSig(){        
        return (this.sig);        
    }
    
    /**
     * Metodo que permite cambiar la informacion contenida en el Nodo doble<br>
     * <b>post: </b> Se edito la informacion del Nodo Doble. <br>
     * @param info es de tipo T y contiene la informacion nueva del nodo dooble
     */
    protected void setInfo(T info){        
        this.info = info;            
    }

    /**
     * Metodo que permite editar la dirección del nodo anterior por una nueva<br>
     * <b>post: </b> Se edito la informacion del Nodo anterior del nodo actual. <br>
     * @param ant es de tipo NodoD<T> y contiene la nueva dirección del nodo anterior
     */
    protected void setAnt(NodoD<T> ant){        
        this.ant=ant;        
    }
    
    /**
     * Metodo que permite cambiar el nodo siguiente del Nodo doble actual<br>
     * <b>post: </b> Se edito la informacion del Nodo siguiente del nodo actual. <br>
     * @param sig es de tipo NodoD<T> y contien la informacion del nuevo nodo siguiente
     */
    protected void setSig(NodoD<T> sig){        
        this.sig=sig;	        
    }

} //Fin de la Clase NodoD