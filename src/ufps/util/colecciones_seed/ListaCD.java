/**
 * ---------------------------------------------------------------------
 * $Id: ListaCD.java,v 2.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 * * @author Marco Adarme
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;
import java.util.Iterator;
 
 /**
 * Implementacion de Clase Para el manejo de una Lista Circular Doble Enlazada<T>.
 * @param <T> Tipo de datos a almacenar en la Lista Circular Doble Enlazada.
 * @author Marco Adarme
 * @version 2.0
 */
public class ListaCD <T> implements Iterable<T>
{
    
    ////////////////////////////////////////////////////////////
    // ListaCD - Atributos /////////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Representa el Nodo cabecera de la Lista, no posee informacion
     */
    private NodoD<T> cabeza;  
    
    /**
     * Representa el tamaño de la Lista
     */
    private int tamanio=0;    
    
    ////////////////////////////////////////////////////////////
    // ListaCD - Implementacion de Metodos /////////////////////
    //////////////////////////////////////////////////////////// 
    
    /**
     * Constructor de la Clase Lista Circular Doble Enlazada, Crea
     * un nodo que sirve como cabezaecera de la ListaCD<T>. <br>
     * <b>post: </b> Se construyo una lista circular doble vacia.
     */
    public ListaCD() {
        this.cabeza=new NodoD<T> (null,null,null);
        this.cabeza.setSig(cabeza);
        cabeza.setAnt(cabeza);        
    }

    /**
     * Metodo que permite adicionar un Elemento al Inicio de la Lista. <br>
     * <b>post: </b> Se inserto un nuevo elemento al inicio de la Lista.<br>
     * @param dato Informacion que desea almacenar en la Lista. La informacion
     * debe ser un Objeto.
     */
    public void insertarAlInicio(T dato){
            NodoD<T> x=new NodoD<T> (dato, cabeza.getSig(), cabeza);
            cabeza.setSig(x);
            x.getSig().setAnt(x);
            this.tamanio++;
        }

   /**
    * Metodo que permite insertar un Elemento al Final de la Lista. <br>
    * <b>post: </b> Se inserto un nuevo elemento al final de la Lista.<br>
    * @param dato Informacion que desea almacenar en la Lista. La informacion
    * debe ser un Objeto.
    */
    public void insertarAlFinal(T dato){
        NodoD<T>x=new NodoD<T>(dato, cabeza, cabeza.getAnt());
        cabeza.getAnt().setSig(x);
        cabeza.setAnt(x);
        this.tamanio++;
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
            NodoD<T> x=this.cabeza;
            NodoD<T> y=x;
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
                y.setSig(new NodoD<T>(info, x, y));
                x.setAnt(y.getSig());
                this.tamanio++;
                }
            }
     }

   /**
    * Metodo que permite eliminar un elemento de la lista dada una posicion. <br>
    * <b>post: </b> Se elimino el dato en la posicion indicada de la lista.<br>
    * @param i Posicion del objeto <br>
    * @return el objeto que se elimino de la lista
    */
    public T eliminar(int i){
        try{
            NodoD<T> x;
            if(i==0){
                x = this.cabeza.getSig();
                this.cabeza.setSig(x.getSig());
                this.cabeza.getSig().setAnt(this.cabeza);
                x.setSig(null);
                x.setAnt(null);
                this.tamanio--;
                return (x.getInfo());
            }
            x=this.getPos(i-1);
            NodoD<T> y = x.getSig();
            x.setSig(y.getSig());
            y.getSig().setAnt(x);
            y.setSig(null);
            y.setAnt(null);
            this.tamanio--;
            return(y.getInfo());
        }catch(ExceptionUFPS ex) {
            System.err.println(ex.getMessage());
        }
    return(null);	    	    
    }

    /**
     * Metodo que elimina todos los datos de la Lista Circular Doble. <br>
     * <b>post: </b> Elimina todos los datos que contenga la lista circular doble.<br>
     */
    public void vaciar(){ 
        this.cabeza=new NodoD<T> (null,null,null);
        this.cabeza.setSig(cabeza);
        cabeza.setAnt(cabeza);
        this.tamanio=0;
    }
    
    /**
     * Metodo que retorna el objeto de la posicion i. <br>
     * <b>post: </b> Se retorno el elemento indicado por la posicion recibida i.<br>
     * @param i posicion de un elemento de la lista <br>
     * @return Devuelve el Objeto de la posicion especificada , null en caso contrario
     */	
    public T get(int i){
        try {
                NodoD<T> x=this.getPos(i);
                return(x.getInfo());
            }catch (ExceptionUFPS ex) {
                System.err.println(ex.getMessage());
            }
            return (null);
        }

    /**
     * Metodo que modifica el elemento que se encuentre en una posicion dada. <br>
     * <b>post: </b> Se edito el elemento indicado en la posicion indicada.<br>
     * @param i Una Posicion dentro de la Lista <br>
     * @param dato es el nuevo valor que toma el elmento en la lista
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
     * Metodo que permite conocer el tamaño de la lista. <br>
     * <b>post: </b> Se retorno el numero de elementos existentes en la Lista.<br>
     * @return un int con el tamaño de la lista
     */
    public int getTamanio(){
        return (this.tamanio);
    }
    
    /**
     * Metodo que permite conocer si en la lista se encuentra elementos. <br>
     * <b>post: </b> Se retorno true si la lista no contiene elementos.<br>
     * @return un boolean true si la lista esta vacia, false en caso contrario
     */
    public boolean esVacia(){
        return(cabeza==cabeza.getSig() || this.getTamanio()==0);
    }
    
    /**
     * Metodo que permite buscar un elemento en la lista si lo encuentra retorna true, de lo contrario false. <br>
     * <b>post: </b> Se retorno true si el elemento se encuentra en la Lista.<br>
     * @param info el cual contiene el valor del parametro a buscar en la lista <br>
     * @return un boolean, si es true encontro el dato en la lista y si es 
     * false no.
     */
    public boolean esta(T info)
    {
        return (this.getIndice(info)!=-1);
    }
    
   /**
    * Metodo que permite obtiene Iterador para una Lista Circular Doble. <br>
    * <b>post: </b> Se retorno un Iterador para una Lista Circular Doble.<br>
    * @return IteratorLCD<T>
    */	      
    @Override
    public Iterator<T> iterator(){
        return (new IteratorLCD<T>(this.cabeza));
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
     * Metodo que permite retornar toda la informacion de los elementos de la Lista Circular Doble en un String. <br>
     * <b>post: </b> Retorna la impresion de los datos de la lista Circular Doble en un String. <br>
     * El String tiene el formato "e1->e2->e3..->en", donde e1, e2, ..., en son los los elementos de la Lista Circular Doble. <br>
     * @return Un String con los datos de los elementos de la Lista
     */
    @Override
    public String toString(){
        if (this.esVacia())
            return ("Lista Vacia");
        String r="";
        for(NodoD<T> x=this.cabeza.getSig();x.getInfo()!=null;x=x.getSig())
            r+=x.getInfo().toString()+"<->";
        return(r);
    }
    
    /**
     * Metodo de tipo private, que retorna un nodo con la posicion de este en la
     * lista y ejecuta una exception si sucede un error. <br>
     * @param i es de tipo integer y contiene la posicion del elemento en la lista. <br>
     * @return un tipo NodoD<T> con el nodo de la posicion
     */
    @SuppressWarnings("empty-statement")
    private NodoD<T> getPos(int i)throws ExceptionUFPS{
        if(i<0||i>=this.tamanio){
            throw new ExceptionUFPS("El índice solicitado no existe en la Lista Doble");
        }
        else
        {
            NodoD<T> t=this.cabeza.getSig();        
            while(i>0){            
                t=t.getSig();
                i--;            
            }        
            return(t);   
        }
     
    }

    /**
     * Metodo que busca un elemento de la lista y devuelve su posicion.Los objetos
     * que se almacenan en la lista deben tener el Método equals. <br>
     * <b>post: </b> Retorna el Nodo que se encuentra en esa posicion indicada. <br> 
     * @param dato de tipo T que indica la informacion del nodo a buscar <br>
     * @return un entero que representa la posicion del objeto consultado en la lista
     */
    public int getIndice(T dato)    {
        int i=0;
        for(NodoD<T> x=this.cabeza.getSig();x!=this.cabeza;x=x.getSig()){
            if(x.getInfo().equals(dato))
                return(i);
            i++;
        }
        return (-1);
    }    
    
}//Fin de la Clase ListaCD