/**
 * ---------------------------------------------------------------------
 * $Id: Nodo123.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 * * @author Uriel Garcia, Yulieth Pabon
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;

/**
 * Implementacion de clase para el manejo de Nodos de Arboles 123.
 * @param <T> Tipo de datos que se desea almacenar dentro del Nodo.
 * @author Uriel Garcia
 * @version 1.0
 */
 class Nodo123<T>{
    
    ////////////////////////////////////////////////////////////
    // Nodo123 - Atributos /////////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Representa la informacion del Nodo que es de menor valor
     */
    private T infoMen;
    
    /**
     * Representa la informacion del Nodo que es de mayor valor
     */
    private T infoMay;
    
    /**
     * Representa el Hijo izquierdo del Nodo
     */
    private Nodo123<T> izq;
    
    /**
     * Representa el Hijo medio del Nodo
     */
    private Nodo123<T> med;
    
    /**
     * Representa el Hijo derecho del Nodo
     */
    private Nodo123<T> der;
    
    
    
    ////////////////////////////////////////////////////////////
    // Nodo123 - Implementacion de Metodos /////////////////////
    ////////////////////////////////////////////////////////////

    /**
     * Contructor vacio que genera un nuevo Nodo123 con sus datos en NULL. <br>
     * <b>post: </b> Se construyo un Nodo que contiene la informacion dada y sus hijos en null. <br>
     */
    public Nodo123() {
        this.infoMen = null;
        this.infoMay = null;
        this.izq = null;
        this.med = null;
        this.der = null;
    }

    /**
     * Contructor con parametros que genera un nuevo Nodo123 con los datos asignados. <br>
     * <b>post: </b> Se construyo un Nodo que contiene la informacion recibida pero sus hijos en null. <br>
     * @param i1 Representa la informacion menor del nuevo Nodo123 creado. <br>
     * @param i2 Representa la informacion mayor del nuevo Nodo123 creado. <br>
     */
    public Nodo123(T i1, T i2) {
        this.infoMen = i1;
        this.infoMay = i2;
        this.izq = null;
        this.med = null;
        this.der = null;
    }

    /**
     * Metodo que permite obtener la informacion menor del Nodo123. <br>
     * <b>post: </b> Se retorno la informacion menor del Nodo123.<br>
     * @return Un objeto de tipo T que contiene la informacion menor del Nodo.
     */
    public T getInfoMen() {
        return infoMen;
    }

    /**
     * Metodo que permite cambiar la informacion menor contenida en el Nodo123. <br>
     * <b>post: </b> Se edito la informacion menor del Nodo123. <br>
     * @param info1 Es de tipo T y contiene la información menor del Nodo.
     */
    public void setInfoMen(T info1) {
        this.infoMen = info1;
    }

    /**
     * Metodo que permite obtener la informacion mayor del Nodo123. <br>
     * <b>post: </b> Se retorno la informacion mayor del Nodo123.<br>
     * @return Un objeto de tipo T que contiene la informacion mayor del Nodo.
     */
    public T getInfoMay() {
        return infoMay;
    }

    /**
     * Metodo que permite cambiar la informacion mayor contenida en el Nodo123. <br>
     * <b>post: </b> Se edito la informacion mayor del Nodo123. <br>
     * @param info2 Es de tipo T y contiene la información mayor del Nodo.
     */
    public void setInfoMay(T info2) {
        this.infoMay = info2;
    }

    /**
     * Metodo que permite obtener el hijo izquierdo del Nodo123. <br>
     * <b>post: </b> Se retorno el hijo izquierdo del Nodo123.<br>
     * @return Un tipo Nodo123<T> que contiene la informacion del hijo izquierdo del Nodo.
     */
    public Nodo123<T> getIzq() {
        return izq;
    }

    /**
     * Metodo que permite modificar el hijo izquierdo del Nodo123. <br>
     * <b>post: </b> Se edito el nuevo hijo izquierdo del Nodo123.<br>
     * @param izq Es de tipo Nodo123<T> que contiene el Nodo Izquierdo.
     */
    public void setIzq(Nodo123<T> izq) {
        this.izq = izq;
    }

    /**
     * Metodo que permite obtener el hijo en el medio del Nodo123. <br>
     * <b>post: </b> Se retorno el hijo en el medio del Nodo123.<br>
     * @return Un tipo Nodo123<T> que contiene la informacion del hijo en el medio del Nodo.
     */
    public Nodo123<T> getMed() {
        return med;
    }

    /**
     * Metodo que permite modificar el hijo en el medio del Nodo123. <br>
     * <b>post: </b> Se edito el nuevo hijo en el medio del Nodo123.<br>
     * @param med Es de tipo Nodo123<T> que contiene el Nodo Hijo en el medio.
     */
    public void setMed(Nodo123<T> med) {
        this.med = med;
    }

    /**
     * Metodo que permite obtener el hijo derecho del Nodo123. <br>
     * <b>post: </b> Se retorno el hijo derecho del Nodo123.<br>
     * @return Un tipo Nodo123<T> que contiene la informacion del hijo derecho del Nodo.
     */
    public Nodo123<T> getDer() {
        return der;
    }

    /**
     * Metodo que permite modificar el hijo derecho del Nodo123. <br>
     * <b>post: </b> Se edito el nuevo hijo derecho del Nodo123.<br>
     * @param der Es de tipo Nodo123<T> que contiene el Nodo derecho.
     */
    public void setDer(Nodo123<T> der) {
        this.der = der;
    }
   
}// Fin de la Clase Nodo123
