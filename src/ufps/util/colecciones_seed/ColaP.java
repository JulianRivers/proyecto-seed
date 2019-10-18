/**
 * ---------------------------------------------------------------------
 * $Id: ColaP.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;

/**
 * Implementacion de clase para el manejo de una Cola de Prioridad.
 * @param <T> Tipo de datos a almacenar en la Cola
 * @author Uriel Garcia
 * @version 1.0
 */
public class ColaP<T> extends Cola<T>{
    
          
    ////////////////////////////////////////////////////////////
    // ColaP - Implementacion de Metodos ///////////////////////
    ////////////////////////////////////////////////////////////    
    
    /**
     * Constructor de la Clase Cola, por defecto el primer y ultimo nodo es NULL y su tamaño es 0. <br>
     * <b>post: </b> Se construyo una Cola.
     */
    public ColaP(){
        super();
        super.setInicio(new NodoP<T>(null,null,null,0));
        NodoP<T> x = (NodoP<T>)super.getInicio();
        x.setSig(x);
        x.setAnt(x);
    }
    
    /**
     * Metodo que permite agregar un elemento a la Cola. <br>
     * <b>post: </b> Se inserto un nuevo elemento a la Cola.<br>
     * @param info es de tipo T y contiene la informacion a en colar. <br>
     * @param p es de tipo entero y representa la prioridad del elemento. 
     */
    public void enColar(T info, int p){        
        NodoP<T> nuevo=new NodoP<T>(info,null,null,p);    
        if(this.esVacia()){        
            NodoP<T> x = new NodoP<T>(info,(NodoP<T>)super.getInicio(),(NodoP<T>)super.getInicio().getAnt(),p);
            ((NodoP<T>)super.getInicio()).getAnt().setSig(x);
            ((NodoP<T>)super.getInicio()).setAnt(x);
            this.aumentarTamanio();
        }
        else{
            if(((NodoP<T>)super.getInicio().getSig()).getPrioridad()<nuevo.getPrioridad()){
                //Inserta al inicio
                nuevo.setSig(((NodoP<T>)super.getInicio()).getSig());
                ((NodoP<T>)super.getInicio()).getSig().setAnt(nuevo);
                ((NodoP<T>)super.getInicio()).setSig(nuevo);
                nuevo.setAnt(((NodoP<T>)super.getInicio()));
                super.aumentarTamanio();
            }else{
                //NodoP iterado
                NodoP<T> c = ((NodoP<T>)super.getInicio()).getSig();
                boolean ins = false;
                while(c!=((NodoP<T>)super.getInicio()) && !ins){
                    if(c.getSig()!=((NodoP<T>)super.getInicio()) && c.getSig().getPrioridad()<nuevo.getPrioridad()){
                        nuevo.setSig(c.getSig());
                        c.getSig().setAnt(nuevo);
                        c.setSig(nuevo);
                        nuevo.setAnt(c);
                        super.aumentarTamanio();
                        ins = true;
                    }else{
                    c = c.getSig();
                    }
                }
                //Si no inserto, es porque tiene la menor prioridad
                if(c == ((NodoP<T>)super.getInicio())){
                    NodoP<T> x = new NodoP<T>(info,(NodoP<T>)super.getInicio(),(NodoP<T>)super.getInicio().getAnt(),p);
                    ((NodoP<T>)super.getInicio()).getAnt().setSig(x);
                    ((NodoP<T>)super.getInicio()).setAnt(x);
                    this.aumentarTamanio();
                }
            }            
        }
    }

    /**
     * Metodo que permite retirar el primer elemento que fue insertado en la Cola. <br>
     * <b>post: </b> Se elimina el primer elemento que fue insertado en la Cola. <br>
     * @return un tipo T que contiene la informacion del nodo retirado
     */
    @Override
    public T deColar(){ 
       return (super.deColar());
    }

    /**
     * Metodo que permite elimar todos los datos que contiene la Cola. <br>
     * <b>post: </b> Se elimino todos los datos que se encontraban en la Cola.<br>
     */
    @Override
    public void vaciar(){
        super.vaciar();
    }
    /**
     * Metodo que permite conocer el primer elemento que fue insertado en la Cola. <br>
     * <b>post: </b> Se obtiene el primer elemento que fue insertado en la Cola.<br>
     * @return el primer elemento que fue insertado en la cola
     */
    @Override
    public T getInfoInicio() {
        return (super.getInfoInicio());
    }

    /**
     * Metodo que retorna el tamaño de la Cola. <br>
     * <b>post: </b> Se retorno el numero de elementos existentes en la Cola.<br>
     * @return un tipo integer qeu contiene el tamaño de la Cola.
     */
    @Override
    public int getTamanio(){
        return(super.getTamanio());
    }

    /**
     * Metodo que permite evaluar si la Cola se encuentra o no vacia. <br>
     * <b>post: </b> Retorna si la Cola se encuentra vacia, retorna false si hay elementos en la Cola.<br>
     * @return Un tipo boolean, true si es vacio y false si contiene nodos
     */
    @Override
    public boolean esVacia(){
         return(super.esVacia());           
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
        NodoP<T> c = ((NodoP<T>)super.getInicio());
        NodoP<T> x = c;
        x = x.getSig();
        while(x!=((NodoP<T>)super.getInicio())){
            msj += x.toString()+"->";
            x = x.getSig();
        }
        return msj;
    }

    public ColaP<T> clonar(){
        ColaP<T> clon = new ColaP();
        NodoP<T> c = ((NodoP<T>)super.getInicio());
        NodoP<T> x = c;
        x = x.getSig();
        while(x!=((NodoP<T>)super.getInicio())){
            clon.enColar(x.getInfo(), x.getPrioridad());
            x = x.getSig();
        }
        return (clon);
    }
    
    
}//Fin de la Clase Cola de Prioridad

