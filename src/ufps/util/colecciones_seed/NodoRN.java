/**
 * ---------------------------------------------------------------------
 * $Id: NodoRN.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;

/**
 * Implementacion de Clase contiene la informacion de los Nodos de un Arbol RojiNegro.
 * @param <T> Tipo de datos a almacenar en el Arbol RojiNegro
 * @author Yulieth Pabon
 * @version 1.0
 */
 class NodoRN<T> extends NodoBin<T>{
    
    ////////////////////////////////////////////////////////////
    // NodoRN - Atributos //////////////////////////////////////
    ////////////////////////////////////////////////////////////
  
    /**
     * Nodo padre del Nodo
     */
    private NodoRN<T> padre;
    
    /**
     * Color del Nodo, solo posee dos valores 0 ó 1
     * color=0 (El Nodo es Rojo)
     * color=1 (El Nodo es Negro)
     */
    private int color;   
    
    ////////////////////////////////////////////////////////////
    // NodoRN - Implementacion de Metodos //////////////////////
    ////////////////////////////////////////////////////////////

    /**
     * Crea un Arbol rojinegro vacio. <br>
     * <b>post: </b> Se creo un Arbol RojiNegro vacio. <br>
     */
    public NodoRN() {
        super();
        this.padre=null;
        this.color=0;
    }
    
    /**
     * Constructor con parametros de la clase. <br>
     * <b>post: </b> Se creo un Arbol RojiNegro con los parametros establecidos. <br>
     * @param x Es de tipo T el cual posee la información del Nodo del Arbol. <br>
     * @param i Es de tipo Nodo<T> el cual posee el Nodo del lado izquierdo. <br>
     * @param d Es de tipo Nodo<T> el cual posee el Nodo del lado derecho. <br>
     * @param p  
     * 
     */
    public NodoRN(T x, NodoRN<T> i, NodoRN<T> d, NodoRN<T> p) {
        super(x,i,d);
        this.padre=p;
        this.color=0;
    }


    /**
     * Contructor con parametros de la clase que genera una hoja del Arbol. <br>     
     * <b>post: </b> Se genero una hoja del Arbol RojiNegro con la informacion dada. <br>
     * @param x es de tipo T el cual posee la información del Nodo del Arbol. <br>
     */
    public NodoRN(T x) {
        super(x);
        this.padre=null;
        this.color=0;
    }
    
    /**
     * Metodo que retona la información del Nodo. <br>     
     * <b>post: </b> Se retona la información del Nodo RojiNegro. <br>
     * @return un tipo T que contiene la información del Nodo.
     */
    @Override
    public T getInfo() {
        return ((T)super.getInfo());
    }
    
    /**
     * Metodo el cual retona el padre del Nodo. <br>     
     * <b>post: </b> Se retono el padre del Nodo RojiNegro. <br>
     * @return un tipo Nodo<T> el cual contiene el padre del Nodo.
     */
    public NodoRN<T> getPadre() {
        return this.padre;
    }
    
    /**
     * Metodo el cual retona el Nodo izquierdo. <br>     
     * <b>post: </b> Se retono el hijo izquierdo del Nodo RojiNegro. <br>
     * @return un tipo Nodo<T> el cual contiene el Nodo izquierdo. 
     */
    @Override
    public NodoRN<T> getIzq() {
        return ((NodoRN<T>)super.getIzq());
    }
    
    /**
     * Metodo el cual retona el Nodo derecho. <br>     
     * <b>post: </b> Se retono el hijo derecho del Nodo RojiNegro. <br>
     * @return un tipo Nodo <T> el cual contiene el Nodo derecho.
     */
    @Override
    public NodoRN<T> getDer() {
          return ((NodoRN<T>)super.getDer());
    }
    
    /**
     * Metodo que permite obtener el color del Nodo. <br>     
     * <b>post: </b> Se retono el color del Nodo RojiNegro. <br>
     * @return el color del Nodo.
     */
    public int getColor() {
        return color;
    }
    
    /**
     * Metodo que modifica el contenido del Nodo. <br>     
     * <b>post: </b> Se edito la informacion del Nodo RojiNegro. <br>
     * @param info de tipo T y contiene la información del Nodo. <br>
     */
    @Override
    public void setInfo(T info) {
       super.setInfo(info);
    }
    
    /**
     * Metodo que modifica el padre del Nodo. <br>     
     * <b>post: </b> Se edito el Nodo padre del Nodo RojiNegro. <br>
     * @param p es de tipo NodoB<T> que contiene el padre del Nodo. <br>
     */
    public void setPadre(NodoRN<T> p) {
        this.padre=p;
    }
    
    /**
     * Metodo que modifica el Nodo izquierdo. <br>     
     * <b>post: </b> Se edito el hijo izquierdo del Nodo RojiNegro. <br>
     * @param i es de tipo NodoB<T> que contiene el Nodo izquierdo. <br>
     */
    public void setIzq(NodoRN<T> i) {
       super.setIzq(i);
    }
    
    /**
     * Metodo que modifica el Nodo derecho. <br>     
     * <b>post: </b> Se edito el hijo derecho del Nodo RojiNegro. <br>
     * @param d es de tipo NodoN<T> que contiene el Nodo derecho. <br>
     */
    public void setDer(NodoRN<T> d) {
        super.setDer(d);
    }

    /**
     * Metodo que permite modificar el color del Nodo. <br>     
     * <b>post: </b> Se edito el color del Nodo RojiNegro. <br>
     * @param color es de tipo entero ...0 es rojo y 1 es negro. <br>
     */
    public void setColor(int color){
        this.color = color;
    }

    /**
     * Metodo que permite devolver la informacion de un Nodo con una cadena de caracteres. <br>
     * @return Un objeto de tipo String con la informacion del Nodo concatenada.
     */
    @Override
    public String toString() {
        return "info"+super.getInfo()+ " padre=" + padre + ", color=" + color + '}';
    }
    
}//Fin de la Clase NodoRN

