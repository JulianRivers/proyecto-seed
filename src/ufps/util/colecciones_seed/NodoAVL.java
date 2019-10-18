/**
 * ---------------------------------------------------------------------
 * $Id: NodoAVL.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;

/**
 * Implementacion de clase que contiene la informacion de los Nodos del Arbol AVL.
 * @param <T> Tipo de datos a almacenar en el Nodo.
 * @author Uriel Garcia
 * @version 1.0
 */
 class NodoAVL<T> extends NodoBin<T>{
    
    ////////////////////////////////////////////////////////////
    // NodoAVL - Atributos /////////////////////////////////////
    ////////////////////////////////////////////////////////////
        
    /**
     * Padre del NodoAVL
     */
    private NodoAVL<T> padre;
    
    /**
     * Factor de Balance del NodoAVL
     */
    private int bal;
    
    
    
    ////////////////////////////////////////////////////////////
    // NodoAVL - Implementacion de Metodos /////////////////////
    ////////////////////////////////////////////////////////////    
    
    /**
     * Contructor vacio de la clase NodoAVL. <br>
     * <b>post: </b> Se construyo un Nodo AVL vacio con la informacion y sus hijos en NULL. <br>
     */
    public NodoAVL() {
        super();
        this.padre=null;
        this.bal=0;
    }
    
    /**
     * Constructor con parametros de la clase <br>
     * <b>post: </b> Se construyo un Nodo Doble con la informacion dada. <br>
     * @param x es de tipo T el cual posee la información del nodo del arbol
     * @param i es de tipo NodoAVL<T> el cual posee el nodo del lado izquierdo
     * @param d es de tipo NodoAVL<T> el cual posee el nodo del lado derecho
     * @param p es de tipo NodoAVL<T> el cual posee el nodo pardre
     * @param b es de tipo int el cual contiene el factor de Balance del Nodo.
     */
    public NodoAVL(T x, NodoAVL<T> i, NodoAVL<T> d, NodoAVL<T> p, int b) {
        super(x,i,d);
        this.padre=p;
        this.bal=b;
    }
    
    /**
     * Contructor con parametros de la clase que genera un nuevo elemento. <br>
     * <b>post: </b> Se construyo un Nodo Hoja que contiene la informacion dada y sus hijos en null. <br>
     * @param x representa la informacion del nodo hoja del arbol
     */
    public NodoAVL(T x) {        
        super(x);
        this.padre=null;
        this.bal=0;
    }
    
    /**
     * Metodo que permite obtener la informacion del Nodo AVL. <br>
     * <b>post: </b> Se retorno la informacion del Nodo AVL.<br>
     * @return un tipo T que contiene la informacion del Nodo AVL
     */
    @Override
    public T getInfo(){
        return (super.getInfo());
    }
    
    /**
     * Metodo que permite obtener el hijo izquierdo del Nodo AVL. <br>
     * <b>post: </b> Se retorno el hijo izquierdo del Nodo AVL.<br>
     * @return un tipo NodoAVL<T> que contiene la informacion del hijo izquierdo del Nodo AVL
     */
    @Override
    public NodoAVL<T> getIzq() {
        return ((NodoAVL<T>)super.getIzq());
    }
    
    /**
     * Metodo que permite obtener el hijo derecho del Nodo AVL. <br>
     * <b>post: </b> Se retorno el hijo derecho del Nodo AVL.<br>
     * @return un tipo NodoAVL<T> que contiene la informacion del hijo derecho del Nodo AVL
     */
    @Override
    public NodoAVL<T> getDer() {
        return ((NodoAVL<T>)super.getDer());
    }
    
    /**
     * Metodo que permite cambiar la informacion contenida en el NodoAVL. <br>
     * <b>post: </b> Se edito la informacion del NodoAVL. <br>
     * @param info de tipo T y contiene la información del nodo
     */
    @Override
    public void setInfo(T info) {
        super.setInfo(info);
    }
    
    /**
     * Metodo que permite modificar el hijo izquierdo del Nodo AVL. <br>
     * <b>post: </b> Se retorno el nuevo hijo izquierdo del Nodo AVL.<br>
     * @param i es de tipo NodoB<T> que contiene el nodo izquierdo
     */
    
    public void setIzq(NodoAVL<T> i) {
        super.setIzq(i);
    }
    
    /**
     * Metodo que permite modificar el hijo derecho del Nodo AVL. <br>
     * <b>post: </b> Se modifico el hijo derecho del Nodo AVL.<br>
     * @param d es de tipo NodoAVL<T> que contiene el nodo derecho
     */
    public void setDer(NodoAVL<T> d) {
        super.setDer(d);
    }
    
    /**
     * Metodo que permite obtener el padre del Nodo AVL. <br>
     * <b>post: </b> Se retorno el padre del Nodo AVL.<br>
     * @return un tipo NodoAVL<T> que contiene la informacion del padre del Nodo AVL
     */
    public NodoAVL<T> getPadre() {
        return (this.padre);
    }
    
    /**
     * Metodo que permite modificar el padre del Nodo AVL. <br>
     * <b>post: </b> Se modifico el padre del Nodo AVL.<br>
     * @param p es de tipo NodoAVL<T> que contiene el padre.
     */
    public void setPadre(NodoAVL<T> p) {
        this.padre=p;
    }
    
    /**
     * Metodo que permite obtener el factor de Balance del Nodo AVL. <br>
     * <b>post: </b> Se retorno el factor de Balance del Nodo AVL.<br>
     * @return Un objeto de tipo int con el factor de balance del Nodo AVL
     */
    public int getBal() {
        return this.bal;
    }
    
    /**
     * Metodo que permite modificar el factor de Balance del Nodo AVL. <br>
     * <b>post: </b> Se modifico el factor de Balance del Nodo AVL.<br>
     * @param b es de tipo int representa el nuevo factor de balance.
     */
    public void setBal(int b) {
        this.bal = b;
    }
    
}//Fin de la Clase NodoAVL
