/**
 * ---------------------------------------------------------------------
 * $Id: ListaS.java,v 2.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;
import java.util.Iterator;

/**
 * Implementacion de la Clase Lista Simple para el manejo de Listas Encadenadas.
 * @param <T> Tipo de datos a almacenar en la lista.
 * @author Marco Adarme
 * @version 2.0
 */
public class ListaS<T> implements Iterable<T>
{
    
    ////////////////////////////////////////////////////////////
    // ListaS - Atributos //////////////////////////////////////
    ////////////////////////////////////////////////////////////

    /**
     * Representea el Nodo cabecera de la Lista
     */
    private Nodo<T> cabeza;
    
    /**
     * Representa el tamaño de la Lista
     */
    private int tamanio; 
    
    
    
    ////////////////////////////////////////////////////////////
    // ListaS - Implementacion de Metodos //////////////////////
    ////////////////////////////////////////////////////////////

    /**
     * Constructor de la Clase Lista Simple Enlazada, por defecto la cabeza es NULL. <br>
     * <b>post: </b> Se construyo una lista vacia.
     */
    public ListaS(){        
        this.cabeza=null;
        this.tamanio=0;    
    }
    
    /**
     * Metodo que inserta un Elemento al Inicio de la Lista. <br>
     * <b>post: </b> Se inserto un nuevo elemento al inicio de la Lista.<br>
     * @param x Informacion que desea almacenar en la Lista. La informacion debe ser un Objeto.
     */
    public void insertarAlInicio(T x){        
        this.cabeza=new Nodo<T>(x, this.cabeza);
        this.tamanio++;        
    }

    /**
     * Metodo que inserta un Elemento al Final de la Lista. <br>
     * <b>post: </b> Se inserto un nuevo elemento al final de la Lista.<br>
     * @param x Información que desea almacenar en la Lista. 
     */
    public void insertarAlFinal(T x){        
        if(this.cabeza==null)
            this.insertarAlInicio(x);
        else {            
            try {                
                Nodo<T> ult=this.getPos(this.tamanio-1);
                if(ult==null)
                    return;
                ult.setSig(new Nodo<T>(x, null));
                this.tamanio++;                
            }catch(ExceptionUFPS e) {                
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
                while(x!=null){
                    Comparable comparador=(Comparable)info;
                    int rta=comparador.compareTo(x.getInfo());
                    if(rta<0)
                        break;
                    y=x;
                    x=x.getSig();
                }
            if(x==y)
                this.insertarAlInicio(info);
            else{
                y.setSig(new Nodo<T>(info, x));
                this.tamanio++;
                }
            }
    }

    /**
     * Metodo que elimina un elemento dada una posición. <br>
     * <b>post: </b> Se elimino el dato en la posicion de la lista indicada.<br>
     * @param i Una posición en la Lista <br>
     * @return El elemento que elimino. Si la posición no es válida retorna NULL.
     */
    public T eliminar(int i) {        
        if(this.esVacia())
            return null;        
        Nodo<T> t=this.cabeza;        
        if(i==0)
            this.cabeza=this.cabeza.getSig();
        else{            
            try {                
                Nodo<T> y=this.getPos(i-1);
                t=y.getSig();
                y.setSig(t.getSig());                
            }catch(ExceptionUFPS e){                
                    System.err.println(e.getMensaje());
                    return (null);
            }            
        }        
        t.setSig(null);        
        this.tamanio--;        
        return(t.getInfo());        
    }

    /**
     * Metodo que elimina todos los datos de la Lista Simple. <br>
     * <b>post:</b> La Lista Simple se encuentra vacia.
     */
    public void vaciar(){        
        this.cabeza=null; 
        this.tamanio=0;           
    }

    /**
     * Metodo que retorna el elemento que se encuentre en una posicion dada. <br>
     * <b>post: </b> Se retorno el elemento indicado por la posicion recibida.<br>
     * @param i Una Posición dentro de la Lista. <br>
     * @return El objeto que se encuentra en esa posición. El objeto <br>
     * retorna su valor parametrizada "T". Si la posición no se <br>
     * encuentra en la Lista retorna null.
     */
    public T get(int i) {        
        try {            
            Nodo<T> t=this.getPos(i);
            return (t.getInfo());            
        }catch(ExceptionUFPS e) {            
            System.err.println(e.getMensaje());   
            return (null);
        }        
           
    }

    /**
     * Metodo que edita el elemento que se encuentre en una posición dada. <br>
     * <b>post: </b> Se edito la informacion del elemento indicado por la posicion recibida.<br>
     * @param i Una Posición dentro de la Lista. <br>
     * @param dato es el nuevo valor que toma el elmento en la lista
     */        
    public void set(int i, T dato){        
        try{            
            Nodo<T> t=this.getPos(i);
             t.setInfo(dato);            
        }catch(ExceptionUFPS e){            
            System.err.println(e.getMensaje());            
        }        
    } 

    /**
      * Metodo que obtiene la cantidad de elementos de la Lista. <br>
      * <b>post: </b> Se retorno el numero de elementos existentes en la Lista.<br>
      * @return int con el tamaño de la lista. Si la Lista esta vacía retorna 0
      */
    public int getTamanio() {        
        return (this.tamanio);        
    }

    /**
     * Metodo que verifica si la Lista esta o no vacia. <br>
     * <b>post: </b> Se retorno true si la lista se encuentra vacia, false si tiene elementos.<br>
     * @return true si la lista esta vacia , false si contiene elementos.
     */
    public boolean esVacia(){        
        return(this.cabeza==null);        
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
     * Metodo que crea para la lista simple un elemento Iterator.
     * <b>post: </b> Se retorno un Iterator para la Lista.<br>
     * @return Un iterator tipo <T> de la lista.
     */
    @Override
    public Iterator<T> iterator() {        
        return new IteratorLS<T>(this.cabeza) {};        
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
    public String toString() {        
        if (this.esVacia())
            return ("Lista Vacia");        
        String r="";        
        for(Nodo<T> x=this.cabeza;x!=null;x=x.getSig())
            r+=x.getInfo().toString()+"->";        
        return(r);        
    }
    
    /**
     * Metodo privado de la clase que devuelve al elemento en la posicion. <br>
     * <b>post: </b> Se retorno el Nodo que se encuentra en esa posicion indicada. <br> 
     * @param i Una posici�n en la Lista. <br>
     * @return El elemento encontrado. Si la posici�n no es v�lida
     * retorna null
     */
    private Nodo<T> getPos(int i)throws ExceptionUFPS{        
        if(this.esVacia() || i>this.tamanio  || i<0){
            throw new ExceptionUFPS("El índice solicitado no existe en la Lista Simple");
        }            
        Nodo<T> t=this.cabeza;        
        while(i>0){            
            t=t.getSig();
            i--;            
        }        
        return(t);        
    }

    /**
     * Metodo que obtiene la posición de un objeto en la Lista. <br>
     * <b>post: </b> Se retorno la posicion en la que se encuentra el dato buscado. 
     * @param info Objeto que se desea buscar en la Lista <br>
     * @return un int con la posición del elemento,-1 si el elemento no se 
     * encuentra en la Lista
     */
    public int getIndice(T info){        
        int i=0;       
        for(Nodo<T> x=this.cabeza;x!=null;x=x.getSig()){            
            if(x.getInfo().equals(info))
                return (i);            
            i++;            
        }        
        return (-1);        
    }    
    
    
}//Fin de la Clase ListaS