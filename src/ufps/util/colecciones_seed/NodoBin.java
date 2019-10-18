/**
 * ---------------------------------------------------------------------
 * $Id: ArbolBin.java,v 2.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;

/**
 * Implementacion de Clase que contiene la informacion de los Nodos de Arbol Binario.
 * @param <T> Tipo de datos a almacenar en el Nodo.
 * @author Marco Adarme
 * @version 2.0
 */
class NodoBin<T>{
    
    ////////////////////////////////////////////////////////////
    // NodoBin - Atributos /////////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Informacion de nodo binario 
     */
    private T info;
    
    /**
     * Hijo izquierdo del nodo binario
     */
    private NodoBin<T> izq;
    
    /**
     * Hijo derecho del nodo binario
     */
    private NodoBin<T> der;
    
    ////////////////////////////////////////////////////////////
    // NodoBin - Atributos /////////////////////////////////////
    ////////////////////////////////////////////////////////////  
    
    /**
     * Contructor vacio de la clase. <br>
     * <b>post: </b> Se construyo un Nodo Binario vacio con la informacion y sus hijos en NULL. <br>
     */
    public NodoBin() {
        this.info=null;
        this.der=null;
        this.izq=null;
    }
    
    /**
     * Constructor con parametros de la clase. br>
     * <b>post: </b> Se construyo un NodoBin con la informacion dada. <br>
     * @param x es de tipo T el cual posee la información del nodo del arbol. <br>
     * @param i es de tipo Nodo<T> el cual posee el nodo del lado izquierdo. <br>
     * @param d es de tipo Nodo<T> el cual posee el nodo del lado derecho
     */
    public NodoBin(T x, NodoBin<T> i, NodoBin<T> d) {
        this.info=x;
        this.izq=i;
        this.der=d;
    }
    
    /**
     * Contructor con parametros de la clase que genera una hoja del arbol. <br>
     * <b>post: </b> Se construyo un Nodo que contiene la informacion dada y sus hijos en null. <br>
     * @param x representa la informacion del nodo hoja del arbol
     */
    public NodoBin(T x){        
        this.info=x;
        this.izq=this.der=null;        
    }
    
    /**
     * Metodo que permite obtener la informacion del Nodo Binario. <br>
     * <b>post: </b> Se retorno la informacion del Nodo Binario.<br>
     * @return un tipo T que contiene la informacion del Nodo Binario
     */
    public T getInfo(){
        return this.info;
    }
    
    /**
     * Metodo que permite obtener el hijo izquierdo del Nodo Binario. <br>
     * <b>post: </b> Se retorno el hijo izquierdo del Nodo Binario.<br>
     * @return un tipo NodoBin<T> que contiene la informacion del hijo izquierdo del Nodo Binario
     */
    public NodoBin<T> getIzq() {
        return this.izq;
    }
    
    /**
     * Metodo que permite obtener el hijo derecho del Nodo Binario. <br>
     * <b>post: </b> Se retorno el hijo derecho del Nodo Binario.<br>
     * @return un tipo NodoBin<T> que contiene la informacion del hijo derecho del Nodo Binario
     */
    public NodoBin<T> getDer() {
        return this.der;
    }
    
    /**
     * Metodo que permite cambiar la informacion contenida en el Nodo Binario. <br>
     * <b>post: </b> Se edito la informacion del Nodo Doble. <br>
     * @param info de tipo T y contiene la información del nodo
     */
    public void setInfo(T info) {
        this.info=info;
    }
    
    /**
     * Metodo que permite modificar el hijo izquierdo del Nodo Binario. <br>
     * <b>post: </b> Se retorno el nuevo hijo izquierdo del Nodo Binario.<br>
     * @param i es de tipo NodoB<T> que contiene el Nodo izquierdo
     */
    public void setIzq(NodoBin<T> i) {
        this.izq=i;
    }
    
    /**
     * Metodo que permite modificar el hijo derecho del Nodo Binario. <br>
     * <b>post: </b> Se retorno el nuevo hijo derecho del Nodo Binario.<br>
     * @param d es de tipo NodoN<T> que contiene el nodo derecho
     */
    public void setDer(NodoBin<T> d) {
        this.der=d;
    }
    
}//Fin de la Clase NodoBin
