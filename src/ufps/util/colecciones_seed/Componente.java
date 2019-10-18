/**
 * ---------------------------------------------------------------------
 * $Id: Componente.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 * @author Uriel Garcia, Yulieth Pabon
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;

/**
 * Implementacion de Clase que permite almacenar la ruta para buscar un determinado dato en el arbol B.
 * @author Yuieth Pabon
 * @version 1.0
 */
public class Componente {
    
    ////////////////////////////////////////////////////////////
    // Componente - Atributos /////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * pagina que contiene el apuntador a la proxima pagina
     */
    private Pagina p; 
    
    /**
     * indice del apuntador de la proxima pagina
     */
    private int v; 
    
    

    ////////////////////////////////////////////////////////////
    // Componente - Implementacion de Metodos /////////////////
    ////////////////////////////////////////////////////////////
   
    /**
     * Crea un componente vacio. <br>
     * <b>post: </b> Se creo un componente vacio. <br>
     */
    public Componente() {
    }

    /**
     * Crea un componente con datos especificos. <br>
     * <b>post: </b> Se creo un componente con un apuntador y un indice en especifico. <br>
     * @param p apuntador del componente a la pagina. <br>
     * @param v indice del apuntador a la proxima pagina. <br>
     */
    public Componente(Pagina p, int v) {
        this.p = p;
        this.v = v;
    }

    /**
     * Metodo que permite obtener pagina que contiene el apuntador a la proxima pagina. <br>
     * <b>post: </b> Se retorno el apuntador de la pagina. <br>
     * @return el apuntador de la pagina.
     */
    public Pagina getP() {
        return p;
    }

    /**
     * Metodo que permite retornar el indice del apuntador de la proxima pagina. <br>
     * <b>post: </b> Se retorno el indice del apuntador de la proxima pagina. <br>
     * @return el indice del apuntador de la proxima pagina.
     */
    public int getV() {
        return v;
    }

    /**
     * Metodo que permite modificar pagina que contiene el apuntador a la proxima pagina. <br>
     * <b>post: </b> Se modifico pagina que contiene el apuntador a la proxima pagina. <br>
     * @param p pagina que contiene el apuntador a la proxima pagina. <br>
     */
    public void setP(Pagina p) {
        this.p = p;
    }

    /**
     * Metodo que permite modificar el indice del apuntador de la proxima pagina<br>
     * <b>post: </b> Se modifico el indice del apuntador de la proxima pagina.<br>
     * @param v 
     */
    public void setV(int v) {
        this.v = v;
    }
    
}//Fin de la clase Componente
