/**
 * ---------------------------------------------------------------------
 * $Id: ListaD.java,v 2.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 * @author Uriel Garcia, Yulieth Pabon, Marco Adarme
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;
import java.util.Iterator;

/**
 * Implementacion de la Clase Para el manejo de una Lista Doble Enlazada<T>
 * @param <T> Tipo de datos a almacenar en la Lista Doble.
 * @author Marco Adarme
 * @version 2.0
 */
public class ListaD<T> implements Iterable<T>
{
    
    ////////////////////////////////////////////////////////////
    // ListaD - Atributos //////////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Representa el Nodo cabecera de la Lista
     */
    private NodoD<T> cabeza;     

    /**
     * Representa el tamaño de la Lista
     */
    private int tamanio;   
    
    
    

    ////////////////////////////////////////////////////////////
    // ListaD - Implementacion de Metodos //////////////////////
    //////////////////////////////////////////////////////////// 
    
    /**
     * Constructor de la Clase Lista Doble Enlazada, por defecto la cabeza es NULL. <br>
     * <b>post: </b> Se construyo una lista doble vacia.
     */
    public ListaD(){
        this.cabeza=null;
        this.tamanio=0;
    }		

    /**
     * Adiciona un Elemento al Inicio de la Lista doble. <br>
     * <b>post: </b> Se inserto un nuevo elemento al inicio de la Lista Doble.<br>
     * @param x Informacion que desea almacenar en la Lista doble. La informacion debe ser un Objeto.
     */
    public void insertarAlInicio(T x){
        if (this.cabeza==null)
            this.cabeza=new NodoD<T>(x,null,null);
        else{                
            this.cabeza=new NodoD<T>(x, this.cabeza, null);
            this.cabeza.getSig().setAnt(this.cabeza);
        }            
        this.tamanio++;			
    }

    /**
     * Inserta un Elemento al Final de la Lista. <br>
     * <b>post: </b> Se inserto un nuevo elemento al final de la Lista Doble.<br>
     * @param x Informacion que desea almacenar en la Lista. La informacion debe ser un Objecto. <br>
     */
    public void insertarAlFinal(T x){
        if(this.cabeza==null)
            this.insertarAlInicio(x);
        else{                
            NodoD<T> ult;                
            try {                    
                ult = this.getPos(this.tamanio - 1);
                if(ult==null)
                    return;
                ult.setSig(new NodoD<T>(x, null, ult));
                this.tamanio++;
            } catch (ExceptionUFPS ex) {                    
               System.err.println(ex.getMessage());                    
            }
        }
    }
    
    /**
     * Metodo que inserta un Elemento  de manera Ordenada desde la cabeza de la Lista. <br>
     * <b>post: </b> Se inserto un nuevo elemento en la posicion segun el Orden de la Lista.<br>
     * @param info Información que desea almacenar en la Lista de manera Ordenada.
     */
    public void insertarOrdenado(T info){ 
        Comparable x=(Comparable)(info);
        if(this.esVacia()|| x.compareTo(this.cabeza.getInfo())<=0){
            this.insertarAlInicio(info);
            return;
        }
        NodoD<T> nuevo=new NodoD<T>(info, null, null);
        NodoD<T> p=this.cabeza;
        
        for(;(p!=null && x.compareTo(p.getInfo())>=0); p=p.getSig()){}
        if(p==null)
            this.insertarAlFinal(info);
        else{
            nuevo.setAnt(p.getAnt());
            nuevo.setSig(p);
            p.setAnt(nuevo);
            nuevo.getAnt().setSig(nuevo);
            this.tamanio++;
        }
    }
    
    /**
     * Metodo que remueve un elemento de la lista con la posicion de esta en la lista. <br>
     * <b>post: </b> Se elimina un elemento de la Lista dada una posicion determinada.<br>
     * @param i es de tipo integer que contiene la posicion del elemento en la lista
     * @return De tipo T que contiene el elemento removido de la lista
     */
    public T eliminar(int i){
       try {                
            NodoD<T> x;                
            x = this.getPos(i); 
            if(x==this.cabeza){
                //Mover el Nodo cabeza
                this.cabeza=this.cabeza.getSig();
                //Referencias de Nodo x a null
        }
        else {
            x.getAnt().setSig(x.getSig());
            if(x.getSig()!=null)//Si no es el ultimo nodo
                x.getSig().setAnt(x.getAnt());
            }                
        //Libero Nodo x              
        x.setAnt(null);
        x.setSig(null);                
        this.tamanio--;                
        return(x.getInfo());                
        }catch (ExceptionUFPS ex) {
            System.err.println(ex.getMessage());
        }            
        return(null);        
    }

    /**
     * Elimina todos los datos de la Lista Doble. <br>
     * <b>post: </b> Se elimino todos los datos que encontraban en la lista doble.<br>
     */
    public void vaciar(){        
        this.cabeza = null;
        this.tamanio=0;
    }
    
    /**
     * Metodo que permite obtener el contenido de un nodo en la lista doble. <br>
     * <b>post: </b> Se obtiene un elemento de la lista dada una posicion determinada.<br>
     * @param i es de tipo integer y contiene la posicion del nodo en la lista doble. <br>
     * @return de tipo T que contiene la informacion en el nodo de la lista doble
     */
    public T get(int i)	{
        NodoD<T> t;
        try {
            t = this.getPos(i);
            return (t.getInfo());    
        } catch (ExceptionUFPS ex) {
             System.err.println(ex.getMessage());
        }
        return (null);
    }
    
    /**
     * Metodo que permite modificar el elemento que se encuentre en una posicion dada. <br>
     * <b>post: </b> Se edita la informacion de un elemento de la lista dada un pasicion determinada.<br>
     * @param i Una Posicion dentro de la Lista doble
     * @param dato es el nuevo valor que toma el elmento en la lista doble
     */
    public void set(int i, T dato){
        try{
            NodoD<T> t=this.getPos(i);                
             t.setInfo(dato);
         }catch(ExceptionUFPS e){
             System.err.println(e.getMessage());
         }
    }
    
    /**
     * Metodo que retorna el tamanio de la lista doble. <br>
     * <b>post: </b> Se retorno el numero de elementos existentes en la Lista Doble.<br>
     * @return de tipo integer que contiene el tamaño del a lista doble
     */
    public int getTamanio(){
        return this.tamanio;
    }
    
    /**
     * Metodo que retorna true si la lista doble se encuentra vacia. <br>
     * <b>post: </b> Retorna si la Lista Doble se encuentra vacia, retorna false si hay elementos en la lista.<br>
     * @return un boolean que es true si esta vacia la lista doble
     */
    public boolean esVacia(){			
        return(this.cabeza==null);            
    }

    /**
     * Metodo que busca un elemento en la lista. <br>
     * <b>post: </b> Retorna true,si el elemento consultado se encuentra en la Lista.<br>
     * @param info que es el valor del elemento a buscar en la Lista. <br>
     * @return Un boolean, si es true encontro el dato en la Lista Doble. <br>
     */
    public boolean esta(T info){
        return (this.getIndice(info)!=-1);
    }
    
    /**
     * Metodo que permite obtener un Iterador para una Lista Doble. <br>
     * <b>post: </b> Retorna una Iterador para la Lista.<br>
     * @return Un objeto de tipo IteratorLD<T> que permite recorrer la Lista.
     */	
    @Override
    public Iterator<T> iterator(){
        return(new IteratorLD<T>(this.cabeza));
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
     * Metodo que permite retornar toda la informacion de los elementos de la Lista en un String. <br>
     * <b>post: </b> Retorna la impresion de los datos de la lista en un String. 
     * El String tiene el formato "e1->e2->e3..->en", donde e1, e2, ..., en son los los elementos de la Lista. <br>
     * @return Un String con los datos de los elementos de la Lista
     */
    @Override
    public String toString(){
        if (this.esVacia())
            return ("Lista Vacia");
        String r="";
        for(NodoD<T> x=this.cabeza;x!=null;x=x.getSig())
            r+=x.getInfo().toString()+"<->";
        return(r);
    }
    
    /**
     * Metodo de tipo privado de la clase que devuelve al elemento en la posicion. <br>
     * <b>post: </b> Retorna el Nodo que se encuentra en esa posicion indicada. <br> 
     * @param i es de tipo integer y contiene la posicion del elemento en la lista. <br>
     * @return un tipo NodoD<T> con el nodo de la posicion.
     */
     private NodoD<T> getPos(int i)throws ExceptionUFPS{        
        if(this.esVacia() || i>this.tamanio  || i<0){
            throw new ExceptionUFPS("El índice solicitado no existe en la Lista Doble");
        }            
        NodoD<T> t=this.cabeza;        
        while(i>0){            
            t=t.getSig();
            i--;            
        }        
        return(t);        
    }

    /**
     * Metodo que obtiene la posicion de un objeto en la Lista. Se recomienda
     * que la clase tenga sobre escrito el metodo equals. <br>
     * <b>post: </b> Retorna la posicion en la que se encuentra el dato buscado. 
     * @param info Objeto que se desea buscar en la Lista
     * @return un int con la posici�n del elemento,-1 si el elemento no se 
     * encuentra en la Lista
     */
    public int getIndice(T info){
        int i=0;
        for(NodoD<T> x=this.cabeza;x!=null;x=x.getSig()){
            if(x.getInfo().equals(info))
                return (i);
            i++;
        }
        return (-1);
    }
    

}//Fin de la Clase ListaD