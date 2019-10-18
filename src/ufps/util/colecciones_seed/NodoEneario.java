/**
 * ---------------------------------------------------------------------
 * $Id: NodoEneario.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;

/**
 * Implementacion de Clase que contiene la informacion de los Nodos de Arbol Eneario.
 * @param <T> Tipo de datos a almacenar en el Nodo.
 * @author Uriel Garcia
 * @version 1.0
 */

class  NodoEneario<T>
{
    
    ////////////////////////////////////////////////////////////
    // NodoEneario - Atributos /////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Representa la informacion del Nodo
     */
    private T info;
    
    /**
     * Referencia su hijo mas a la izquierda
     */
    private NodoEneario<T> hijo;        
    
    /**
     * Referencia a su hermano mas a su derecha
     */
    private NodoEneario<T> hermano;     
    
    

    ////////////////////////////////////////////////////////////
    // NodoEneario - Implementacion de Metodos /////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Contructor con el dato de la clase que genera un nuevo NodoEneario. <br>
     * <b>post: </b> Se construyo un Nodo que contiene la informacion dada y sus hijos en null. <br>
     * @param info Representa la informacion del nuevo NodoEneario creado.
     */
    public NodoEneario(T info){
        this.info= info ;
        this.hijo=null;
        this.hermano=null;
    }
    
    /**
     * Constructor con todos los parametros de la Clase para generar un nuevo NodoEneario. <br>
     * <b>post: </b> Se construyo un NodoEneario con la informacion de su primer Hijo y Hermano. <br>
     * @param info Es de tipo T el cual posee la información del Nodo del Arbol. <br>
     * @param hi Es de tipo NodoEneario<T> y representa el hijo mas a la izquierda del Nodo. <br>
     * @param he  Es de tipo NodoEneario<T> y representa el hermano mas cercano al Nodo por la derecha.
     */
    public NodoEneario(T info, NodoEneario<T> hi, NodoEneario<T> he){
        this.info = info;
        this.hijo = hi;
        this.hermano = he;
    }
    
    /**
     * Metodo que permite obtener la informacion del NodoEneario. <br>
     * <b>post: </b> Se retorno la informacion del NodoEneario.<br>
     * @return Un objeto de tipo T que contiene la informacion del NodoEneario.
     */
    public T getInfo() {
        return info;
    }

    /**
     * Metodo que permite cambiar la informacion contenida en el NodoEneario. <br>
     * <b>post: </b> Se edito la informacion del NodoEneario. <br>
     * @param info Es de tipo T y contiene la información del Nodo.
     */
    public void setInfo(T info) {
        this.info = info;
    }
    
    /**
     * Metodo que permite obtener el hijo mas a su izquierda del NodoEneario. <br>
     * <b>post: </b> Se retorno el hijo nas a la izquierda del NodoEneario.<br>
     * @return Un objeto de NodoEneario<T> que contiene la informacion del hijo del Nodo.
     */
    public NodoEneario<T> getHijo() {
        return hijo;
    }

    /**
     * Metodo que permite modificar el hijo mas a la izquierda del NodoEneario. <br>
     * <b>post: </b> Se edito el nuevo hijo izquierdo del Nodo Eneario.<br>
     * @param hijo Es de tipo NodoEneario<T> que contiene el nuevo hijo mas a la izquierda del Nodo.
     */
    public void setHijo(NodoEneario<T> hijo) {
        this.hijo = hijo;
    }

    /**
     * Metodo que permite obtener el hermano mas cercano por la derecha del NodoEneario. <br>
     * <b>post: </b> Se retorno el hermano mas cercano por la derecha del NodoEneario.<br>
     * @return Un objeto de NodoEneario<T> que contiene la informacion del hermano del Nodo.
     */
    public NodoEneario<T> getHermano() {
        return hermano;
    }

    /**
     * Metodo que permite modificar el hermano mas cercano por la derecha del NodoEneario. <br>
     * <b>post: </b> Se edito el nuevo hermano mas cercano por la derecha Eneario.<br>
     * @param hermano Es de tipo NodoEneario<T> que contiene el nuevo hermano a la derecha del Nodo.
     */
    public void setHermano(NodoEneario<T> hermano) {
        this.hermano = hermano;
    }


}// Final de la Clase NodoEneario
