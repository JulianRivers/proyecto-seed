/**
 * ---------------------------------------------------------------------
 * $Id: Secuencia.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;

/**
 * Implementacion de Clase que representa una vector que almacena objetos de cualquier clase. <br>
 * @param <T> Tipos de datos que se almacenaran en la Secuencia.
 * @autor Marco A. Adarme J.
 * @version 1.0
 */
public class Secuencia<T>
{       
    
    ////////////////////////////////////////////////////////////
    // Secuencia - Atributos ///////////////////////////////////
    ////////////////////////////////////////////////////////////
        
    /**
     * Vector en el cual se almacenan los Objetos de tipo T
     */
    private T vector[];   
    
    /**
     * Cantidad de Objetos dentro de la Secuencia
     */
    private int cant;      
    
    
            
    ////////////////////////////////////////////////////////////
    // Secuencia - Implementacion de Metodos ///////////////////
    ////////////////////////////////////////////////////////////
       
    /**
     * Constructor con parametros de la clase secuencia. <br>
     * <b>post: </b> Se construye una Secuencia vacia. <br>
     * @param n es de tipo integer que contiene el tamaño en capacidad de la Secuencia. <br>
     */
    public Secuencia(int n){        
        if (n<=0){
            System.err.println("Tamaño de secuencia no valido!");
            return;
        }    
        Object r[]=new Object[n];
        cant=0;
        this.vector=(T[])r;
    }
    
    /**
     * Metodo que inserta un nuevo elemento a la secuencia. <br>
     * <b>post: </b> Se inserto un elemento en la Secuencia.<br>
     * @param elem es de tipo T que contiene el elemento a insertar
     */
    public void insertar(T elem){        
        if(this.cant>=this.vector.length)
            System.err.println("No hay más espacio!");
        else
        this.vector[this.cant++]=elem;
    }
    
    /**
     * Metodo que elimina un elemento a la secuencia. <br>
     * <b>post: </b> Se elimino un elemento en la Secuencia.<br>
     * @param elem es de tipo T que contiene el elemento a eliminar
     */
    public void eliminar(T elem){     
        boolean e = false;
        for( int i=0, j=0; i<this.cant; i++){
            if(!this.vector[i].equals(elem)){
                this.vector[j]=vector[i];
                j++;
            }else{
                e=true;
                this.vector[j]=null;
            }
        }
        if(e)
        this.cant--;
    }
    
    /**
     * Metodo que elimina un elemento a la secuencia dada su posicion. <br>
     * <b>post: </b> Se elimino un elemento en la Secuencia.<br>
     * @param pos es de tipo int que contiene la posicion del elemento a eliminar
     */
    public void eliminarP(int pos){        
        if (pos<0 || pos>this.cant){
            System.err.println("Indíce fuera de rango!");
            return;
        }
        boolean e = false;
        for( int i=0, j=0; i < this.cant; i++ ){
            if(i!=pos){
                this.vector[j]=vector[i];
                j++;
            }else{
                e=true;
                this.vector[j]=null;
            }
        }
        if(e)
        this.cant--;
    }

    /**
     * Metodo que vacia la secuencia. <br>
     * <b>post:</b> La Secuencia se encuentra vacia.
     */
    public void vaciar(){
        for( int i = 0; i < this.cant; i++ )
            this.vector[i] = null;
        this.cant = 0;
    }
    
    /**
     * Metodo que retorna un objeto tipo T de la secuencia dada la posición. <br>
     * <b>post:</b> Se ha retornado un elemento de la Secuencia dada su posicion<br>
     * @param i es de tipo integer y contiene la posicion en la secuencia. <br>
     * @return un tipo T que contiene el elemento del nodo en la posicion indicada <br>
     */
    public T get(int i){        
        if (i<0 || i>this.cant){
            System.err.println("Indíce fuera de rango!");
            return (null);
        }
        else
        return (this.vector[i]);
    }

    /**
     * Metodo que cambia un elemento de la secuencia en la posición indicada , por otro. <br>
     * <b>post:</b> Se ha modificado un elemento de la Secuencia.<br>
     * @param i de tipo integer que contiene la posicion de la secuencia que se va ha cambiar.<br>
     * @param nuevo Representa el nuevo objeto que reemplazara al objeto editado. <br>
     */
    public void set(int i, T nuevo){        
        if (i<0 || i>this.cant){
            System.err.println("Indíce fuera de rango!");
            return;
        }
        if(nuevo==null){
            System.err.println("No se pueden ingresar datos nulos!");
            return;
        }
        this.vector[i]=nuevo;
    }

    /**
     * Metodo que recibe un un elemento y comprueba si existe en la secuencia. <br>
     * <b>post:</b> Se ha retornado true si el elemento se encuentra en la Secuencia.<br>
     * @param elem es de tipo T y contiene el elemnto que se va ha buscar. <br>
     * @return un tipo boolean, retorna true si el objeto existe y false en caso contrario.
     */
    public boolean esta(T elem){        
        for(int i=0;i<this.cant;i++)
            if(this.vector[i].equals(elem))
                return true;
            return false;
    }
    
    
    /**
     * Metodo que permite conocer el indice de un Elemento dentro de la Secuencia. <br>
     * <b>post:</b> Se ha retornado el indice del elemento dentro de la Secuencia. <br>
     * @param elem Representa el elemento al cual se le quiere determinar el indice en la Secuencia. <br>
     * @return Un objeto de tipo (int) con la posicion del elemento dentro de la Secuencia.
     */
    public int getIndice(T elem){        
        for(int i=0;i<this.cant;i++)
            if(this.vector[i].equals(elem))
                return (i);
        return (-1);
    }

    /**
     * Metodo que retorna el tamaño lógico de la secuencia, esto es el numero de datos almacenados. <br>
     * <b>post:</b> Se ha retornado el numero de elementos dentro de la secuencia.<br>
     * @return un tipo integer que contiene el tamaño lógico de la secuencia
     */
    public int getTamanio(){
        return this.cant;
    }
    
    /**
     * Metodo que permite conocer si la Secuencia esta vacia. <br>
     * <b>post:</b> Se ha retornado true o false dependiendo si la Secuencia esta vacia.<br>
     * @return de tipo boolean true indica que la Secuencia esta vacia.
     */
    public boolean esVacia(){
        return(this.cant==0);
    }

    /**
     * Metodo que retorna el tamaño real de la secuencia, esto es, el length del vector con o sin elementos. <br>
     * <b>post:</b> Se ha retornado la capacidad de la Secuencia para guardar elementos.<br>
     * @return un tipo integer que contiene el tamaño real de la secuencia
     */
    public int getCapacidad(){
        return (this.vector.length);
    }

    /**
     * Metodo que retorna el contenido de la secuencia en una cadena String. <br>
     * <b>post:</b> Se retorno la representacion en String de la Secuencia. <br>
     * @return de tipo String y contiene el String de datos de la secuencia
     */
    @Override
    public String toString(){
        if(this.esVacia())
            return "Secuencia vacia!";
        String msg="Secuencia -> | ";
        for(int i=0;i<this.cant;i++)
            msg+=this.vector[i].toString()+" | ";
        return (msg);
    }

}// Fin de la Clase Secuencia
