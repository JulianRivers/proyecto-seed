/**
 * ---------------------------------------------------------------------
 * $Id: NodoP.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;
 
/**
 * Implementacion de la clase NodoP de una Cola de Prioridad.
 * @param <T> Tipo de dato almacenados en el NodoP.
 * @author Uriel Garcia
 * @version 1.0
 */
class NodoP<T> extends NodoD<T>
{
    
    ////////////////////////////////////////////////////////////
    // NodoP - Atributos ////////////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    
    /**
     * Prioridad del NodoP
     */
    private int pri;
    
    
    
    ////////////////////////////////////////////////////////////
    // NodoP - Implementacion de Metodos ////////////////////////
    ////////////////////////////////////////////////////////////

    /**
     * Constructor de un NodoP vacio. <br>
     * <b>post: </b> Se construyo un NodoP vacio con la informacion en NULL.
     */
    public NodoP(){
        super();
        this.pri=0;
    }
	
    /**
     * Contructor inicializa los miembros datos de NodoP. <br>
     * <b>post: </b> Se construyo el nodo con los datos especificados.<br>
     * @param info Objeto de tipo T <br>
     * @param sig Representa el Siguiente NodoP <br>
     * @param ant 
     * @param p Representa la prioridad del NodoP 
     */
    public NodoP(T info, NodoP<T> sig, NodoP<T> ant, int p){
        super(info,sig, ant);
        this.pri=p;
    }
	
    /**
     * Obtiene la información del nodo. <br>
     * <b>post: </b> Se retorno la informacion del NodoP.<br>
     * @return Un objeto tipo T
     */
    @Override
    public T getInfo(){
        return super.getInfo();
    }
	
    /**
     * Obtiene el NodoP Siguiente. <br>
     * <b>post: </b> Se retorno el siguiente nodo. <br>
     * @return El nodo siguiente o null si el último nodo o si es una Lista con cardinalidad 1. 
     */
    @Override
    public NodoP<T> getSig(){        
        return ((NodoP<T>)super.getSig());
    }
	
    /**
     * Cambia la informaci�n del NodoP del campo Info. <br>
     * <b>post: </b> Se edito la informacion del NodoP. <br>
     * @param nuevo Nuevo objeto que desea almacenarse en el NodoP
     */
    @Override
    public void setInfo(T nuevo){        
        super.setInfo(nuevo);
    }
	
    /**
     * Cambia el nodo siguiente. <br>
     * <b>post: </b> Se edito el NodoP siguiente.<br>.
     * @param nuevo Nuevo NodoP siguiente.
     */
    public void setSig(NodoP<T> nuevo){
        super.setSig(nuevo);
    }
    
    /**
     * Obtiene la prioridad del nodo. <br>
     * <b>post: </b> Se retorno la prioridad del NodoP.<br>
     * @return Un valor entero con la prioridad del NodoP.
     */
    public int getPrioridad(){
        return (this.pri);
    }
    
    /**
     * Retorna los datos del NodoP como cadena de String. <br>
     * @return La informacion del NodoP junto con su prioridad
     */
    @Override
    public String toString(){
        return (this.getInfo()+"_"+this.getPrioridad());
    }
    
}//Fin de la Clase NodoP