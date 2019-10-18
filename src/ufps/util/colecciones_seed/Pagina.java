/**
 * ---------------------------------------------------------------------
 * $Id: Pagina.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;

/**
 * Implementacion de Clase para el manejo de los Nodos (denominados PAGINAS) de un ArbolB.
 * @param <T> Tipo de datos a almacenar en el ArbolB.
 * @author Yulieth Pabon
 * @version 1.0
 */
public class Pagina <T>{

    ////////////////////////////////////////////////////////////
    // Pagina - Atributos /////////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * orden del arbol
     */
    private int n; 
    
    /**
     * numero maximo de llaves
     */
    private int m; 
    
    /**
     * numero maximo de apuntadores
     */
    private int m1;
    
    /**
     * numero de llaves de pagina
     */
    private int cont;  
    
    /**
     * llaves clasificadas ascendentemente
     */
    private T[] info; 
    
    /**
     * direcciones de los hijos de la pagina 
     */
    private Pagina[] apuntadores; 

    
    
    ////////////////////////////////////////////////////////////
    // Pagina - Implementacion de Metodos /////////////////////
    ////////////////////////////////////////////////////////////
      
    /**
     * Crea una pagina con orden especifico. <br>
     * <b>post: </b> Se creo una pagina con orden especifico. <br>
     * @param n de tipo entero que indica el numero el orden de la pagina. <br>
     */
    public Pagina(int n){
        this.n = n;
        this.m = n*2;
        this.m1 = this.m+1;
        this.info= (T[]) new Object[m];
        for(int i=0; i<info.length;i++)
            info[i]=null;
        this.apuntadores = new Pagina[this.m1];
        for(int i=0; i<apuntadores.length;i++)
            apuntadores[i]=null;
    }
    
  
    
    /**
     * Metodo que permite saber el numero de orden de la pagina. <br>
     * <b>post: </b> Se retorno el numero de orden de la pagina. <br>
     * @return el numero de orden de la pagina
     */
     public int getN() {
        return n;
    }

    /**
     * Metodo que permite saber el numero de llaves que puede almacenar la pagina. <br>
     * <b>post: </b> Se retorno el numero de llaves que puede almacenar la pagina. <br>
     * @return el numero de llaves que puede almacenar la pagina
     */
    public int getCont() {
        return cont;
    }

    /**
     * Metodo que permite saber la informacion que almacena la pagina. <br>
     * <b>post: </b> Se retorno ela informacion que almacena la pagina. <br>
     * @return la informacion que esta  almacenada en la pagina
     */
    public T[] getInfo() {
        return info;
    }

    /**
     * Metodo que permite saber el numero maximo de elementos que puede almacenar la pagina. <br>
     * <b>post: </b> Se retorno el numero maximo de elementos que puede almacenar la pagina. <br>
     * @return el numero maximo de elementos que puede almacenar la pagina
     */
    public int getM() {
        return m;
    }

    /**
     * Metodo que permite saber el numero maximo de apuntadores que puede almacenar la pagina. <br>
     * <b>post: </b> Se retorno el numero maximo de apuntadores que puede almacenar la pagina. <br>
     * @return el numero maximo de apuntadores que puede almacenar la pagina
     */
    public int getM1() {
        return m1;
    }

    /**
     * Metodo que permite obtener los apuntadores de la pagina. <br>
     * <b>post: </b> Se retorno los apuntadore de la pagina. <br>
     * @return un arreglo con los apuntadores de la pagina
     */
    public Pagina[] getApuntadores() {
        return apuntadores;
    }
    
    /**
     * Metodo que permite modificar el numero de orden de la pagina. <br>
     * <b>post: </b> Se modifico el numero de orden de la pagina. <br>
     * @param n numero del orden de la pagina. <br>
     */
    public void setN(int n) {
        this.n = n;
    }
   
    /**
     * Metodo que permite modificar el numero de llaves de la pagina. <br>
     * <b>post: </b> Se modifico el numero de llaves de la pagina. <br>
     * @param cont numero de llaves de la pagina. <br>
     */
    public void setCont(int cont) {
        this.cont = cont;
    }

    /**
     * Metodo que permite modificar los apuntadores de la pagina. <br>
     * <b>post: </b> Se modifico los apuntadores de la pagina. <br>
     * @param apuntadores conjunto de apuntadores de la pagina. <br>
     */
    public void setApuntadores(Pagina[] apuntadores) {
        this.apuntadores = apuntadores;
    }

    /**
     * Metodo que permite modificar la informacion de la pagina. <br>
     * <b>post: </b> Se modifico la informacion de la pagina. <br>
     * @param info conjunto de informacion que tendra la pagina. <br>
     */
    public void setInfo(T[] info) {
        this.info = info;
    }

    @Override
    public String toString(){
        String msg= "  Informacion de la pagina";
        int i=0;
        while(i<this.getCont()){
        msg+=" --> "+this.info[i++].toString();}
        return msg;
    }
    
}//Fin de la clase Pagina
