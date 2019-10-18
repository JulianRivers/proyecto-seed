/**
 * ---------------------------------------------------------------------
 * $Id: ListaC.java,v 2.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;
import java.util.Iterator;


/**
 * Implementacion de la Clase para el manejo de Lista Circular.
 * @param <T> Tipo de datos a almacenar en la lista.
 * @author Marco Adarme
 * @version 2.0
 */
public class ListaC<T> implements Iterable<T>
{    
    
    ////////////////////////////////////////////////////////////
    // ListaC - Atributos //////////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Representa el Nodo cabecera de la Lista
     */
    private Nodo<T> cabeza;  
    
    /**
     * Representa el tamaño de la Lista
     */
    private int tamanio=0;    
    
    
        
    ////////////////////////////////////////////////////////////
    // ListaC - Implementacion de Metodos //////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Constructor de la Clase Lista Circular Enlazada, por defecto la cabeza es NULL. <br>
     * <b>post: </b> Se construyo una lista vacia.
     */
    public ListaC() {
        this.cabeza=new Nodo<T> (null,null);
        this.cabeza.setSig(cabeza);     
    }
    
    /**
     * Metodo que permite insertar un Elemento al Inicio de la Lista. <br>
     * <b>post: </b> Se inserto un nuevo elemento al inicio de la Lista.<br>
     * @param dato Informacion que desea almacenar en la Lista. La informacion
     * debe ser un Objeto.
     */
    public void insertarAlInicio(T dato){        
        Nodo<T> x=new Nodo<T>(dato, cabeza.getSig());
        cabeza.setSig(x);
        this.tamanio++;
    }

    /**
     * Inserta un Elemento al Final de la Lista. <br>
     * <b>post: </b> Se inserto un nuevo elemento al final de la Lista.<br>
     * @param x Representa el dato a Insertar al final de la Lista.
     */	
    public void insertarAlFinal(T x){
        if(this.esVacia())
            this.insertarAlInicio(x);
        else {            
            try {                
                    Nodo<T> ult=this.getPos(this.tamanio-1);
                    ult.setSig(new Nodo<T>(x, this.cabeza));
                    this.tamanio++;                
                }catch(ExceptionUFPS e){                
                    System.err.println(e.getMensaje());                
                }            
            }
    }
    
    /**
     * Metodo que inserta un Elemento  de manera Ordenada desde la cabeza de la Lista. <br>
     * <b>post: </b> Se inserto un nuevo elemento en la posicion segun el Orden de la Lista.<br>
     * @param info Información que desea almacenar en la Lista de manera Ordenada.
     */
    public void insertarOrdenado(T info){
        if (this.esVacia())
            this.insertarAlInicio(info);
        else{
            Nodo<T> x=this.cabeza;
            Nodo<T> y=x;
            x = x.getSig();
            while(x!=this.cabeza){
                Comparable comparador=(Comparable)info;
                int rta=comparador.compareTo(x.getInfo());
                if(rta<0)
                    break;
                y=x;
                x=x.getSig();
            }
            if(x==cabeza.getSig())
                this.insertarAlInicio(info);
            else{
                y.setSig(new Nodo<T>(info, x));
                this.tamanio++;
                }
            }
     }

    /**
     * Metodo que permite eliminar un elemento de la lista dada una posicion. <br>
     * <b>post: </b> Se elimino el dato en la posicion indicada de la lista.<br>
     * @param i Posicion del objeto. Debe ir desde 0 hasta el tamaño de la lista menos uno.  <br>
     * @return Retorna el objeto eliminado de la Lista.
     */
    public T eliminar(int i){
        try{
            Nodo<T> x;
            if(i==0){
                x = this.cabeza.getSig();
                this.cabeza.setSig(x.getSig());
                this.tamanio--;
                return (x.getInfo());
            }
            x=this.getPos(i-1);
            if(x==null)
                return (null);
            Nodo<T> y = x.getSig();
            x.setSig(y.getSig());
            this.tamanio--;
            return(y.getInfo());
           }catch(ExceptionUFPS ex) {
                System.err.println(ex.getMensaje());
            }
        return(null);	    	    
    }
    
    /**
     * Metodo que elimina todos los datos de la Lista Circular. <br>
     * <b>post: </b> Elimina todos los datos que contenga la lista circular.<br>
     */
    public void vaciar(){        
        this.cabeza.setSig(cabeza);
        this.tamanio=0;
    }

    /**
     * Metodo que retorna el Objeto de la posicion i. <br>
     * <b>post: </b> Se retorno el elemento indicado por la posicion recibida i.<br>
     * @param i posicion de un elemento de la lista. <br>
     * @return Devuelve el Objeto de la posicion especificada ,null en caso contrario.
     */	
    public T get(int i){        
        try {           
                Nodo<T> x=this.getPos(i);
                if(x==null)
                    return (null);
                return(x.getInfo());
            }catch (ExceptionUFPS ex) {
                System.err.println(ex.getMensaje());
            }
            return (null);
    }
    
    /**
     * Modifica el elemento que se encuentre en una posicion dada. <br>
     * <b>post: </b> Se edito la informacion del elemento indicado por la posicion recibida.<br>
     * @param i Una Posici�n dentro de la Lista. <br>
     * @param dato es el nuevo valor que toma el elmento en la lista
     */        
    public void set(int i, T dato){        
        try{
                Nodo<T> t=this.getPos(i);   
                if(t==null)
                    return;
                t.setInfo(dato);
        }catch(ExceptionUFPS e){
                System.err.println(e.getMensaje());
            }
    }
    
    /**
     * Metodo que devuelve el tamaño de la lista. <br>
     * <b>post: </b> Se retorno el numero de elementos existentes en la Lista.<br>
     * @return un int con el tamaño de la lista
     */    
    public int getTamanio(){
        return (this.tamanio);        
    }

    /**
     * Metodo que retorna true si la lista esta vacia, false en caso contrario. <br>
     * <b>post: </b> Se retorno true si la lista se encuentra vacia, false si tiene elementos.<br>
     * @return un boolean con true o false en caso de que la lista se encuentre vacia.
     */
    public boolean esVacia(){        
        return(cabeza==cabeza.getSig() || this.tamanio==0);        
    }
    
    /**
     * Metodo que busca un elemento en la lista si lo encuentra retorna true, de lo contrario false. <br>
     * <b>post: </b> Se retorno true si se encontro el elementos buscado, false si no fue asi.<br>
     * @param info el cual contiene el valor del parametro a buscar en la lista. <br>
     * @return un boolean, si es true encontro el dato en la lista y si es false no lo encontro.
     */
    public boolean esta(T info) {        
        return (this.getIndice(info)!=-1);        
    }
    
    /**
     * Metodo que crea para la lista circular un elemento Iterator.
     * <b>post: </b> Se retorno un Iterator para la Lista.<br>
     * @return Un iterator tipo <T> de la lista.
     */	      
    @Override
    public Iterator<T> iterator(){
        return (new IteratorLC<T>(this.cabeza));
    }
    
      /**
     * Metodo que permite retornar la informacion de una Lista en un Vector. <br>
     * @return Un vector de Objetos con la informacion de cada posicion de la Lista.
     */
    public Object[] aVector(){
         if(this.esVacia())
                return (null);
        Object vector[]=new Object[this.getTamanio()];
        Iterator<T> it=this.iterator();
        int i=0;
        while(it.hasNext())
            vector[i++]=it.next();
        return(vector);
    }
    
    /**
     * Metodo que retorna toda la informacion de los elementos en un String de la Lista. <br>
     * <b>post: </b> Se retorno la representacion en String de la Lista. 
     * El String tiene el formato "e1->e2->e3..->en", donde e1, e2, ..., en son los los elementos de la Lista. <br>
     * @return Un String con los datos de los elementos de la Lista
     */
    @Override
    public String toString(){ 
        if (this.esVacia())
            return ("Lista Vacia");
        String r="";
        for(Nodo<T> x=this.cabeza.getSig();x.getInfo()!=null;x=x.getSig())
            r+=x.getInfo().toString()+"->";
        return(r);
    }
    
    /**
     * Elemento privado de la clase que devuelve al elemento en la posicion. <br>
     * <b>post: </b> Se retorno el Nodo que se encuentra en esa posicion indicada. <br> 
     * @param i es de tipo integer y contiene la posicion del elemento en la lista. <br>
     * @return un tipo NodoD<T> con el nodo de la posicion.
     */
    @SuppressWarnings("empty-statement")
    private Nodo<T> getPos(int i)throws ExceptionUFPS{
        if(i<0||i>=this.tamanio){
            System.err.println("Error indice no valido en una Lista Circular!");
            return (null);
        }
        Nodo<T> x=cabeza.getSig();
        for( ; i-->0; x=x.getSig());
            return x;
    }
    
    /**
     * Obtiene la posición de un objeto en la Lista. <br>
     * <b>post: </b> Se retorno la posicion en la que se encuentra el dato buscado. 
     * @param dato Representa el datoa a encontrar dentro de la Lista. <br>
     * @return un int con la posición del elemento,-1 si el elemento no se encuentra en la Lista.
     */
    public int getIndice(T dato){
        int i=0;        
        for(Nodo<T> x=this.cabeza.getSig();x!=this.cabeza;x=x.getSig()){
            if(x.getInfo().equals(dato))
            return(i);            
            i++;            
        }    	
        return (-1);
    }    
  

}//Fin de la Clase ListaC