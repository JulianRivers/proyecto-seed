/**
 * ---------------------------------------------------------------------
 * $Id: Pila.java,v 2.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;


/**
 * Implementacion de la Clase Pila para el manejo de Pilas encadenadas. <br>
 * @param <T> Tipo de datos a almacenar en la pila
 * @author Marco Adarme
 * @version 2.0
 */
public class Pila<T>
{    
    
    ////////////////////////////////////////////////////////////
    // Pila - Atributos ////////////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Elemento que se ubica en el tope de la Pila
     */
    private Nodo<T> tope; 
    
    /**
     * Tamaño de la Pila
     */
    private int tamanio;      
    
    
    
    ////////////////////////////////////////////////////////////
    // Pila - Implementacion de Metodos ////////////////////////
    ////////////////////////////////////////////////////////////
        
    /**
     * Metodo constructor vacio de la clase Pila. <br>
     * <b>post: </b> Se construye una Pila vacia
     */
    public Pila(){
        this.tope = null;
        this.tamanio = 0;
    }
    
    /**
     * Metodo que inserta un elemento en la Pila. <br>
     * <b>post: </b> Se inserto un elemento dentro de la Pila.<br>
     * @param info es de tipo T y contiene la información a insertar en la pila.
     */
    public void apilar(T info){
        if(this.esVacia())
            this.tope = new Nodo<T>(info, null);
        else
            this.tope=new Nodo<T>(info, this.tope);        
        this.tamanio++;
    }

    /**
     * Metodo que retira y devuelve un elemneto de la Pila. <br>
     * <b>post: </b> Se retiro y elimino el elemento tope de la Pila.<br>
     * @return un tipo T y contiene la información retirada de la pila.<br>
     */
    public T desapilar(){
        if(this.esVacia())
            return (null);
        Nodo<T> x=this.tope;
        this.tope = tope.getSig();     
        this.tamanio--;
        if(tamanio==0)
            this.tope=null;
        return(x.getInfo());
    }
    
    /**
     * Elimina todos los datos de la Pila. <br>
     * <b>post: </b> Se elimino todos los datos que se encontraban en la Pila.<br>
     */
    public void vaciar(){        
        this.tope = null; 
        this.tamanio=0;           
    }
    
    /**
     * Metodo devuelve el elemento que se encuentra en el tope de la Pila. <br>
     * <b>post: </b> Se retorno el elemento tope de la Pila.<br>
     * @return El elemento que esta en el tope de la Pila.
     */
    public T getTope(){
        return (this.tope.getInfo());
    }
    
    /**
     * Metodo que retorna el tamaño de la pila. <br>
     * <b>post: </b> Se retorno el tamaño de la Pila.<br>
     * @return un tipo de dato Integer que contiene el tamaño de la Pila.
     */
    public int getTamanio(){
        return (this.tamanio);
    }
    
    /**
     * Metodo que comprueba si la pila esta vacía. <br>
     * <b>post: </b> Se retorno true si la Pila se encuentra vacia y false si no lo esta. <br>
     * @return un tipo boolean, true si es vacía y false si no.
     */    
    public boolean esVacia() {
        return(this.tope==null||this.tamanio==0);
    }

    /**
     * Convierte la pila a una cadena de String. <br>
     * <b>post: </b> Se retorno la representacion en String de la pila. 
     * El String tiene el formato "e1->e2->e3..->en", donde e1, e2, ..., en son los los elementos de la Pila. <br>
     * @return La representacion en String de la Pila.
     */
    @Override
    public String toString( )
    {
        String msj ="";
        Nodo<T> p = tope;
        while(p != null){
            msj += p.getInfo().toString()+"->";
            p = p.getSig();
        }
        return msj;
    }
    
}//Fin de la Clase Pila