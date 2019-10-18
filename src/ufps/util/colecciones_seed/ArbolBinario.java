/**
 * ---------------------------------------------------------------------
 * $Id: ArbolBinario.java,v 2.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;
import java.util.Iterator;

/**
 * Implementacion de Clase para el manejo de un Arbol Binario.
 * @param <T> Tipo de datos a almacenar en el Arbol Binario.
 * @author Marco Adarme
 * @version 2.0
 */
public class ArbolBinario<T>
{
    
    ////////////////////////////////////////////////////////////
    // ArbolBinario - Atributos ////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Nodo raiz del Arbol Binario
     */
    private NodoBin<T> raiz;        
    
    

    ////////////////////////////////////////////////////////////
    // ArbolBinario - Implementacion de Metodos ////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Crea un Arbol Binario vacio. <br>
     * <b>post: </b> Se creo un Arbol Binario vacio.<br>
     */
     public ArbolBinario(){
        this.raiz=null;
     }
     
    /**
     * Crea un Arbol Binario con una raiz predefinida. <br>
     * <b>post: </b> Se creo un nuevo Arbol con su raiz definida.<br>
     * @param raiz  Un objeto de tipo T que representa del dato en la raiz del Arbol. <br>
     */
     public ArbolBinario(T raiz) {
        this.raiz = new NodoBin(raiz);
     }
     
    /**
     * Metodo que permite conocer el objeto de la raiz del Arbol Binario. <br>
     * <b>post: </b> Se obtuvo la raiz del Arbol Binario.<br>
     * @return la raiz del Arbol Binario.
     */
    public T getObjRaiz() {
        return (raiz.getInfo());
    }
     
    /**
     * Metodo que permite conocer la raiz del Arbol Binario. <br>
     * <b>post: </b> Se obtuvo la raiz del Arbol Binario.<br>
     * @return la raiz del Arbol Binario.
     */
    public NodoBin<T> getRaiz() {
        return raiz;
    }
    
    /**
     * Metodo que permite modificar la raiz del Arbol Binario. <br>
     * <b>post: </b> Se modifico la raiz del Arbol Binario.<br>
     * @param raiz representa la nueva raiz del Arbol Binario.
     */
    public void setRaiz(NodoBin<T> raiz) {
        this.raiz = raiz;
    }
        
    /**
     * Metodo que permite insertar un Hijo izquiero al elemento. <br>
     * <b>post: </b> Se inserto un hijo a la izquierda del elemento.<br>
     * @param padre Padre del nuevo elemento. <br>
     * @param hijo Elemento nuevo a insertar. <br>
     * @return  
     */
    public boolean insertarHijoIzq(T padre, T hijo){
        if(this.esVacio()){
            this.setRaiz(new NodoBin<T>(hijo));
            return (true);
        }   
        NodoBin<T> p = this.buscar(padre);
        if(p!=null){
            if(p.getIzq()==null){
                p.setIzq(new NodoBin<T>(hijo));
                return (true);
            }
            return (false);            
        }
        return (false);        
    }
    
    /**
     * Metodo que permite insertar un Hijo derecho al elemento. <br>
     * <b>post: </b> Se inserto un hijo a la derecha del elemento.<br>
     * @param padre Padre del nuevo elemento. <br>
     * @param hijo Elemento nuevo a insertar. <br>
     * @return  
     */
    public boolean insertarHijoDer(T padre, T hijo){
        if(this.esVacio()){
            this.setRaiz(new NodoBin<T>(hijo));
            return (true);
        }   
        NodoBin<T> p = this.buscar(padre);
        if(p!=null){
            if(p.getDer()==null){
                p.setDer(new NodoBin<T>(hijo));
                return (true);
            }
            return (false);            
        }
        return (false);  
    }
    
    /**
     * Metodo que permite eliminar un elemento del Arbol Binario, dada su informacion. <br>
     * <b>post: </b> Se elimino el elemento del Arbol Binario.<br>
     * @param info Dato que se desea eliminar del Arbol Binario. <br>
     * @return true o false dependiendo si se pudo o no eliminar el dato.
     */
    public boolean eliminar(T info){        
        NodoBin<T> r = this.buscar(info);
        if(r==null)
            return (false);        
        boolean tnd = r.getDer()!=null?true:false;
        boolean tni = r.getIzq()!=null?true:false; 
        //Caso 1: No tiene hijos
        if (!tnd && !tni)
            return eliminarC1(r);
        //Caso 2: Tiene solo hijo derecho
        if ( tnd && !tni)
            return eliminarC2(r); 
        //Caso 3: Tiene solo hijo izquierdo
        if ( !tnd && tni )
            return eliminarC2(r); 
        //Caso 4: Tiene ambos hijos
        if ( tnd && tni )
            return eliminarC3(r); 
        return false;
    } 
    
    /**
     * Metodo de tipo privado que permite eliminar de un Arbol Binario para el Caso 1. <br>
     * <b>post: </b> Se elimino el elemento del Arbol Binario.<br>
     * @param r Nodo que se desea eliminar del Arbol Binario. <br>
     * @return true o false dependiendo si se pudo o no eliminar el dato.
     */
    private boolean eliminarC1(NodoBin<T> r){
        NodoBin<T> p = this.getPadre(r);
        if(p==null){
            if(this.getRaiz()!=r)
                return (false);
            this.setRaiz(null);
            return (true);
        }            
        NodoBin<T> hi = p.getIzq();
        NodoBin<T> hd = p.getDer();
        if(hi==r){
            this.getPadre(r).setIzq(null);
            return true;
        } 
        if (hd==r){
            this.getPadre(r).setDer(null);
            return true;
        } 
        return (false);
    }
    
    /**
     * Metodo de tipo privado que permite eliminar de un Arbol Binario para el Caso 2. <br>
     * <b>post: </b> Se elimino el elemento del Arbol Binario.<br>
     * @param r Nodo que se desea eliminar del Arbol Binario. <br>
     * @return true o false dependiendo si se pudo o no eliminar el dato.
     */
    private boolean eliminarC2(NodoBin<T> r){
        NodoBin<T> p = this.getPadre(r);
        NodoBin<T> ha = r.getIzq()!=null?r.getIzq():r.getDer(); 
        if(p==null){
            this.setRaiz(ha);
            return (true);
        }
        NodoBin<T> hi = p.getIzq();
        NodoBin<T> hd = p.getDer();
        if (hi==r){
            this.getPadre(r).setIzq(ha);
            r.setDer(null);
            r.setIzq(null); 
            return true;
        } 
        if (hd==r) {
            this.getPadre(r).setDer(ha);
            r.setDer(null);
            r.setIzq(null); 
            return true;
        } 
        return false;
    }
    
    /**
     * Metodo de tipo privado que permite eliminar de un Arbol Binario para el Caso 3. <br>
     * <b>post: </b> Se elimino el elemento del Arbol Binario.<br>
     * @param r Nodo que se desea eliminar del Arbol Binario. <br>
     * @return true o false dependiendo si se pudo o no eliminar el dato.
     */
    private boolean eliminarC3(NodoBin<T> r){
        NodoBin<T> masIzq = this.masIzquierda(r.getDer());
        if (masIzq!=null){            
            this.eliminar((T) masIzq.getInfo());
            r.setInfo(masIzq.getInfo());            
            return (true);
        }
        return (false);
    }
    
    /**
     * Metodo de tipo privado que permite conocer el Nodo mas a la izquierda, Caso eliminacion 3. <br>
     * <b>post: </b> Se retorno el Nodo mas a la izquierda que se desea eliminar.<br>
     * @param r Nodo que se desea eliminar del Arbol Binario. <br>
     * @return Nodo Binario ubicado mas a la izquierda del Nodo indicado.
     */
    private NodoBin<T> masIzquierda(NodoBin<T> r) {
        if (r.getIzq()!=null){
            return (masIzquierda(r.getIzq()));
        }
        return (r);
    }
    
    /**
     * Metodo que retorna true si existe un dato en el arbol binario, o false en caso contrario. <br>
     * <b>post: </b> Se retorno true si el elemento se encuentra en el Arbol.<br>
     * @param info representa la informacion del elemento que se desea buscar. <br>
     * @return Un boolean, true si el dato esta o false en caso contrario.
     */
     public boolean esta(T info){
        return (esta(this.raiz,info));
    }

     /**
      * Metodo de tipo privado que retorna true si existe un dato en el arbol binario, o false en caso contrario. <br>
      * Es necesario para que el metodo funcione que los objetos almacenados en el arbol tengan sobreescrito el metodo equals.<br>
      * <b>post: </b> Se retorno true si el elemento se encuentra en el Arbol.<br>
      * @param r representa la raiz del arbol binario, o raiz de subarbol. <br>
      * @param info representa la informacion del elmento que se desea buscar. <br>
      * @return true si el dato esta o false en caso contrario.
      */
    private boolean esta(NodoBin<T> r, T info){
        if(r==null)
            return (false);
        if(r.getInfo().equals(info))
            return (true);
        return(esta(r.getIzq(),info) || esta(r.getDer(),info));
    }
    
    /**
     * Metodo que permite consultar un elemento existente dentro del Arbol Binario. <br>
     * <b>post: </b> Se retorno un NodoBin<T> perteneciente al dato buscado. <br>
     * @param info Elemento a ubicar dentro del Arbol Binario.
     * @return Un objeto de tipo NodoBin<T> que representa el objeto buscado.
     */
    private NodoBin<T> buscar(T info){
        return (buscar(this.raiz,info));
    }
   
    /**
     * Metodo que permite consultar un elemento existente dentro del Arbol Binario. <br>
     * <b>post: </b> Se retorno un NodoBin<T> perteneciente al dato buscado. <br>
     * @param info Elemento a ubicar dentro del Arbol Binario. <br>
     * @param r Representa la raiz del Arbol. <br>
     * @return Un objeto de tipo NodoBin<T> que representa el objeto buscado.
     */
    private NodoBin<T> buscar(NodoBin<T> r, T info){
        if(r==null)
            return (null);
        if(r.getInfo().equals(info))
            return (r);
        else
        {
            NodoBin<T> aux=(r.getIzq()==null)?null:buscar(r.getIzq(),info);
            if(aux!=null)
                return (aux);
            else
                return (r.getDer()==null)?null:buscar(r.getDer(),info);
        }
    }
    
    /**
     * Modificar la informacion de un Nodo dentro del Arbol Binario. <br>
     * <b>post: </b> Se edito la informacion de un Nodo del Arbol. <br>
     * @param info1 Elemento dentro del Arbol Binario que se quiere modificar. <br>
     * @param info2 Nueva informacion del elemento que se desea modificar. <br>
     * @return Un boolean si se puede editar la informacion del elemento.
     */
    public boolean setDato(T info1, T info2){
        if(!this.esta(info1) || this.esta(info2))
            return (false);
        return (setDato(this.raiz, info1,info2));
    }

    /**
     * Modificar la informacion de un Nodo dentro del Arbol Binario. <br>
     * <b>post: </b> Se edito la informacion de un Nodo del Arbol. <br>
     * @param info1 Elemento dentro del Arbol Binario que se quiere modificar. <br>
     * @param info2 Nueva informacion del elemento que se desea modificar. <br>
     * @param r Representa la raiz del Arbol Binario. <br>
     * @return Un boolean si se puede editar la informacion del elemento.
     */
    private boolean setDato(NodoBin<T> r, T info1, T info2){
        if(r==null)
            return (false);        
        if(r.getInfo().equals(info1)){
            r.setInfo(info2);
            return (true);
        }
        return(setDato(r.getIzq(),info1,info2) || setDato(r.getDer(),info1,info2));
    }
    
    /**
     * Metodo que dado un dato almacenado en el arbol, retorna el padre de ese dato. <br>
     * <b>post: </b> Se retorno el padre del nodo que contiene la informacion dada.<br>
     * @param r Dato que se desea buscar. <br>
     * @return El padre del dato almacenado en el arbol, null en caso no existir el dato
     */ 
     protected NodoBin<T> getPadre(NodoBin<T> r){
        if(r==null || this.raiz==null)
            return null;
        NodoBin<T> x=getPadre(this.raiz,r.getInfo());
        if(x==null)
            return null;
        return(x);
     }
     
     /**
      * Metodo de tipo privado que dado un dato almacenado en el arbol, retorna el padre de ese dato.Se parte
      * de la premisa que el arbol no contiene elementos repetidos. <br>
      * <b>post: </b> Se retorno el padre del nodo que contiene la informacion dada.<br>
      * @param x representa la raiz del arbol, o raiz de subarbol
      * @param info dato que se desea buscar
      * @return el padre del dato almacenado en el arbol, null en caso no existir el dato
      */
    private NodoBin<T> getPadre(NodoBin<T> x, T info){
        if(x==null)
            return null;
        if((x.getIzq()!=null && x.getIzq().getInfo().equals(info))|| (x.getDer()!=null && x.getDer().getInfo().equals(info)))
            return (x);
        NodoBin<T> y=getPadre(x.getIzq(),info);
        if(y==null)
            return(getPadre(x.getDer(),info));
        else
            return(y);
    }
    
    /**
     * Metodo que retorna un iterador con las hojas del arbol binario. <br>
     * <b>post: </b> Se retorno un iterador con las hojas del arbol binario.<br>
     * @return un iterador con las hojas del arbol binario 
     */
    public Iterator<T> getHojas(){
        ListaCD<T> l=new ListaCD<T>();
        getHojas(this.raiz, l);
        return (l.iterator());
    }

    /**
     * Metodo de tipo privado que retorna un iterador con las hojas del arbol binario. <br>
     * <b>post: </b> Se retorno un iterador con las hojas del arbol binario.<br>
     * @param r representa la raiz del arbol, o raiz de subarbol. <br>
     * @param l Lista para el almacenamiento de los datos del arbol. <br>
     */
    private void getHojas(NodoBin<T> r, ListaCD<T> l){
        if (r!=null){
            if(this.esHoja(r))
                l.insertarAlFinal(r.getInfo());
            getHojas(r.getIzq(), l);
            getHojas(r.getDer(), l);
        }
    }
    
    /**
     * Metodo de tipo privado que permite saber si un elemento es una hoja. <br>
     * <b>post: </b> Se retorno true si es nodo hoja del arbol binario.<br>
     * @param x representa la raiz del arbol, o raiz de subarbol. <br>
     * @return true si sus dos hijos son null. <br>
     */
    private boolean esHoja(NodoBin<T> x){
        return (x!=null && x.getIzq()==null && x.getDer()==null);
    }
    
    /**
     * Metodo que permite determinar el numero de Nodo hojas dentro del Arbol Binario. <br>
     * <b>post: </b> Se retorno el numero de hojas del Arbol. <br>
     * @return El numero de hojas existentes en el Arbol Binario.
     */
    public int contarHojas(){
        return (contarHojas(this.raiz));
    }
    
    /**
     * Metodo que permite determinar el numero de Nodo hojas dentro del Arbol Binario. <br>
     * <b>post: </b> Se retorno el numero de hojas del Arbol. <br>
     * @param r representa la raiz del arbol, o raiz de subarbol. <br>
     * @return El numero de hojas existentes en el Arbol Binario.
     */
    private int contarHojas(NodoBin<T> r){
        if(r==null)
            return (0);
        if(this.esHoja(r))
            return (1);
        int chi = contarHojas(r.getIzq());
        int chd = contarHojas(r.getDer());
        return (chi+chd);
    }
    
    /**
     *  Metodo que retorna un iterador con el recorrido preOrden del Arbol Binario. <br>
     * <b>post: </b> Se retorno un iterador en preOrden para el arbol.<br>
     * @return un iterador en preorden (padre->hijoIzq->hijoDer) para el arbol binario.
     */
     public Iterator<T> preOrden(){
         ListaCD<T> l=new ListaCD<T>();
         preOrden(this.getRaiz(),l);
         return (l.iterator());
        }

     /**
      * Metodo que tipo privado que retorna un iterador con el recorrido preOrden del Arbol Binario. <br>
      * <b>post: </b> Se retorno un iterador en preOrden para el arbol.<br>
      * @param r representa la raiz del arbol, o raiz de subarbol. <br>
      * @param l Lista para el almacenamiento de los datos del arbol. <br>
      */
    private void  preOrden(NodoBin<T> r, ListaCD<T> l){
        if(r!=null){
            l.insertarAlFinal(r.getInfo());
        preOrden(r.getIzq(), l);
        preOrden(r.getDer(), l);
        }
    }

   /**
     * Metodo que retorna un iterador con el recorrido in Orden del Arbol Binario. <br>
     * <b>post: </b> Se retorno un iterador inOrden para el arbol.<br>
     * @return un iterador en inOrden (hijoIzq->padre->hijoDer) para el arbol binario. <br>
     */
    public Iterator<T> inOrden(){
        ListaCD<T> l=new ListaCD<T>();
        inOrden(this.getRaiz(),l);
        return (l.iterator());
    }

    /**
     * Metodo de tipo privado que retorna un iterador con el recorrido in Orden del Arbol Binario. <br>
     * <b>post: </b> Se retorno un iterador inOrdenpara el arbol.<br>
     * @param r representa la raiz del arbol, o raiz de subarbol. <br>
     * @param l Lista para el almacenamiento de los datos del arbol. <br>
     */
    private void  inOrden(NodoBin<T> r, ListaCD<T> l){
        if(r!=null){
            inOrden(r.getIzq(), l);
        l.insertarAlFinal(r.getInfo());
        inOrden(r.getDer(), l);
        }
    }

    /**
     * Metodo que retorna un iterador con el recorrido postOrden del Arbol Binario. <br>
     * <b>post: </b> Se retorno un iterador postOrden para el arbol.<br>
     * @return un iterador en postOrden (hijoIzq->hijoDer->padre) para el arbol binario. <br>
     */
    public Iterator<T> postOrden(){
        ListaCD<T> l=new ListaCD<T>();
        postOrden(this.getRaiz(),l);
        return (l.iterator());
    }

    /**
     * Metodo de tipo privado que retorna un iterador con el recorrido postOrden del Arbol Binario. <br>
     * <b>post: </b> Se retorno un iterador postOrden para el arbol.<br>
     * @param r representa la raiz del arbol, o raiz de subarbol. <br>
     * @param l Lista para el almacenamiento de los datos del arbol
     */
    private void  postOrden(NodoBin<T> r, ListaCD<T> l){
        if(r!=null){
            postOrden(r.getIzq(), l);
            postOrden(r.getDer(), l);
            l.insertarAlFinal(r.getInfo());
        }
    }

    /**
     * Metodo que permite retornar en un String el recorrido preOrden del Arbol Binario. <br>
     * <b>post: </b> Se retorno un iterador en preOrden para el arbol.<br>
     * @return un String el recorrido preOrden del arbol
     */
    public String preOrden_Iterativo(){
        return(preOrden_Iterativo(this.raiz));
    }

    /**
     * Metodo de tipo privado que permite retornar en un String el recorrido preOrden del arbol. <br>
     * <b>post: </b> Se retorno un iterador en preOrden para el arbol.<br>
     * @param r representa la raiz del arbol, o raiz de subarbol
     * @return  un String el recorrido preOrden del arbol
     */
    private String preOrden_Iterativo(NodoBin<T> r){
        Pila<NodoBin> p=new Pila<NodoBin>();
        String rr="";
        while(r!=null){
            p.apilar(r);
            rr+=r.getInfo().toString()+"-";
            r=r.getIzq();
        }
        while(!p.esVacia()){
            r=p.desapilar();
            r=r.getDer();
            while(r!=null){
                rr+=r.getInfo().toString()+"-";
                p.apilar(r);
                r=r.getIzq();
            }
        }
        return(rr);
    }

    /**
     * Metodo que permite retornar en un String el recorrido inOrden del Arbol Binario. <br>
     * <b>post: </b> Se retorno un iterador en inOrden para el arbol.<br>
     * @return un String el recorrido inOrden del arbol
     */
    public String inOrden_Iterativo(){
        return(inOrden_Iterativo(this.raiz));
    }

    /**
     * Metodo de tipo privado que permite retornar en un String el recorrido inOrden del Arbol Binario. <br>
     * <b>post: </b> Se retorno un iterador en inOrden para el arbol.<br>
     * @param r representa la raiz del arbol, o raiz de subarbol
     * @return  un String el recorrido inOrden del arbol
     */
    private String inOrden_Iterativo(NodoBin<T> r){
        Pila<NodoBin> p=new Pila<NodoBin>();
        String rr="";
        while(r!=null){
            p.apilar(r);
            r=r.getIzq();
        }
        while(!p.esVacia()){
            r=p.desapilar();
            rr+=r.getInfo().toString()+"-";
            r=r.getDer();
            while(r!=null){
                p.apilar(r);
                r=r.getIzq();
            }
        }   
        return(rr);
    }
    
    /**
     * Metodo que permite retornar en un String el recorrido postOrden del Arbol Binario. <br>
     * <b>post: </b> Se retorno un iterador en postOrden para el arbol.<br>
     * @return un String el recorrido inOrden del arbol
     */
    public String postOrden_Iterativo(){
        return(postOrden_Iterativo(this.raiz));
    }
    
    /**
     * Metodo de tipo privado que permite retornar en un String el recorrido postOrden del Arbol Binario. <br>
     * <b>post: </b> Se retorno un iterador en postOrden para el arbol.<br>
     * @param r representa la raiz del arbol, o raiz de subarbol. <br>
     * @return  un String el recorrido postOrden del arbol
     */
    private String postOrden_Iterativo(NodoBin<T> r){
        Pila<NodoBin> pila = new Pila<NodoBin>();
        NodoBin<T> tope = null;
        String rr="";
        while(r!=null)
        {
            if(r.getIzq()!=null && r.getIzq()!=tope && r.getDer()!=tope)
            { 
                pila.apilar(r);
                r=r.getIzq();
            }
            else if(r.getIzq()==null && r.getDer()==null && r!=tope)
            {
                rr+=r.getInfo().toString()+"-";
                tope=r;
                if(!pila.esVacia())
                    r = pila.desapilar();
                else r =null;
            }
            else if(r.getDer()!=null && tope!=r.getDer())
            {
                pila.apilar(r);
                r=r.getDer();
            }
            else if(r.getDer()!=null && tope==r.getDer())
            {
                rr+=r.getInfo().toString()+"-";
                tope=r;                
                if(!pila.esVacia())
                    r = pila.desapilar();
                else r =null;               
            }
        }
        return (rr);
    }
    
    /**
     * Metodo que permite retornar un iterador con el recorrido por niveles del Arbol Binario. <br>
     * <b>post: </b> Se retorno el recorrido por niveles del Arbol Binario.<br>
     * @return Un iterador con el recorrido por niveles del Arbol Binario.
     */
    public Iterator<T> impNiveles(){
        ListaCD<T> l=new ListaCD<T>();
        if(!this.esVacio()){
            Cola<NodoBin<T>> c=new Cola<NodoBin<T>>();
            c.enColar(this.getRaiz());
            NodoBin<T> x;
                while(!c.esVacia()){
                    x=c.deColar();
                    l.insertarAlFinal(x.getInfo());
                    if(x.getIzq()!=null)
                    c.enColar(x.getIzq());
                    if(x.getDer()!=null)
                    c.enColar(x.getDer());
                }
        }
        return (l.iterator());
    }
    
    /**
     * Metodo que permite obtener el peso del Arbol Binario. <br>
     * <b>post: </b> Se retorno el numero de elementos en el Arbol Binario.<br>
     * @return Un entero con la cantidad de elementos del Arbol Binario.
     */
    public int getPeso(){
        return(getPeso(this.getRaiz()));
    }

    /**
     * Metodo de tipo privado que permite conocer el numero de elemento del Arbol Binario. <br>
     * <b>post: </b> Se retorno el numero de elementos en el arbol.<br>
     * @param r Representa la raiz del arbol, o raiz de subarbol. <br>
     * @return El munero de elementos que contiene el Arbol Binario.
     */
    private int getPeso(NodoBin<T> r){
        if(r==null)
            return 0;
        return (getPeso(r.getIzq())+1+getPeso(r.getDer()));
    }
    
    /**
     * Metodo que permite saber si el Arbol Binario se encuentra vacio. <br>
     * <b>post: </b> Se retorno true si el arbol no contiene elementos.<br>
     * @return true su no hay datos en el Arbol Binario.
     */
    public boolean esVacio(){
        return(this.raiz==null);
    }
    
    /**
     * Metodo que permite obtener la altura del Arbol Binario. <br>
     * <b>post: </b> Se retorno la altura del Arbol Binario.<br>
     * @return Un entero con la altura del Arbol Binario.
     */
    public int getAltura(){
        if(this.raiz==null)
            return (0);
        return(getAltura(this.getRaiz()));
    }

    /**
     * Metodo de tipo privado que permite conocer la altura del Arbol Binario. <br>
     * <b>post: </b> Se retorno la altura del Arbol Binario. <br>
     * @param r Representa la raiz del arbol, o raiz de subarbol. <br>
     * @return Un entero con la altura del Arbol Binario.
     */
    private int getAltura(NodoBin<T> r){
        int ai=0, ad=0;
        if(r.getIzq()!=null)
            ai = getAltura(r.getIzq());
        if(r.getDer()!=null)
            ad = getAltura(r.getDer());
        if(ai>=ad)
            return (ai+1);
        return (ad+1);        
    }
    
    /**
     * Metodo de tipo privado que permite el grado del Nodo de un Arbol. <br>
     * <b>post: </b> Se retorno el grado del Nodo de Arbol Binario. <br>
     * @param info Representa la informacion del Nodo a consultar el Grado. <br>
     * @return Un entero con el grado del Nodo consultado.
     */
    public int getGrado(T info){
        NodoBin nodo = this.buscar(info);
        if(nodo==null)
            return (-1);
        if(this.esHoja(nodo)) 
            return (0);
        int rta = 0;
        if(nodo.getIzq()!=null)
            rta++;
        if(nodo.getDer()!=null)
            rta++;
        return (rta);
    }
    
    /**
     * Indica si el Arbol es completo. <br>
     * <b>post: </b> Se retorno true si el Arbol esta completo o false de lo contrario. <br>
     * @return True si el Arbol es completo o false de lo contrario
     */
    public boolean esCompleto( )
    {
        return(esCompleto(this.raiz));
    }
    
    /**
     * Indica si el Arbol es completo. <br>
     * <b>post: </b> Se retorno true si el Arbol esta completo o false de lo contrario. <br>
     * @return True si el Arbol es completo o false de lo contrario
     */
    private boolean esCompleto(NodoBin<T> r)
    {
        if(this.esHoja(r))
            return true;
        else if(r.getIzq()!=null && r.getDer()!=null)
            return esCompleto(r.getIzq()) && esCompleto(r.getDer());
        else
            return false;
    }
    
    /**
     * Indica si el Arbol se encuentra lleno. <br>
     * <b>post: </b> Se retorno true si el Arbol esta lleno o false de lo contrario. <br>
     * @return True si el Arbol esta lleno o false de lo contrario
     */
    public boolean estaLleno()
    {
        return (estaLleno(this.raiz, this.getAltura()));
    }
    
    /**
     * Indica si el Arbol se encuentra lleno. <br>
     * <b>post: </b> Se retorno true si el Arbol esta lleno o false de lo contrario. <br>
     * @param alt La altura para verificar si el arbol esta lleno
     * @return True si el Arbol esta lleno o false de lo contrario
     */
    private boolean estaLleno(NodoBin<T> r, int alt)
    {
        if(this.esHoja(r))
            return (alt==1);
        else if(r.getIzq()==null || r.getDer()==null)
            return false;
        else
            return estaLleno(r.getIzq(),alt-1) && estaLleno(r.getDer(),alt-1);
    }
    
    /**
     * Metodo que elimina las hojas(nodos terminales) del arbol binario.<br>
     * <b>post: </b> Se eliminaron las hojas del arbol binario.<br>
     */
    public void podar(){
        if(this.esHoja(raiz))
            this.setRaiz(null);
        podar(this.raiz);
    }

    /**
     * Metodo de tipo privado que elimina las hojas(nodos terminales) del arbol binario. <br>
     * <b>post: </b> Se eliminaron las hojas del arbol binario.<br>
     * @param x reprenseta la raiz del arbol, o raiz de subarbol
     */
    private void podar(NodoBin<T> x){
        if (x==null)
            return;
        if(this.esHoja(x.getIzq()))
            x.setIzq(null);
        if(this.esHoja(x.getDer()))
            x.setDer(null);
        podar(x.getIzq());
        podar(x.getDer());
    }
    
    /**
     * Metodo que retorna el codigo  Łukasiewicz del arbol binario; Este codigo etiqueta los nodos internos con "a" 
     * y los externos con una "b" y realiza el recorrido en preorden con estas convenciones. <br>
     * <b>post: </b> Se retorno el codigo  Łukasiewicz del Arbol Binario.<br>
     * @return  un String con el codigo  Łukasiewicz del arbol binario
     */ 
    public String Luca()
    {
        return(Luca(this.raiz));
    }

    /**
     * Metodo que retorna el codigo Łukasiewicz del arbol binario; Este codigo etiqueta los nodos internos con "a" 
     * y los externos con una "b" y realiza el recorrido en preorden con estas convenciones. <br>
     * <b>post: </b> Se retorno el codigo  Łukasiewicz del arbol binario.<br>
     * @param r reprenseta la raiz del arbol, o raiz de subarbol
     * @return  un String con el codigo  Łukasiewicz del arbol binario
     */
    private String Luca(NodoBin<T> r){
        if(r==null)
        return("b");
        return("a"+Luca(r.getIzq())+Luca(r.getDer()));
    }
    
    /**
     * Metodo que permite saber si dos Arboles Binarios son iguales de Informacion y estructura. <br>
     * <b>post: </b> Se retorno true si los arboles son Iguales. <br>
     * @param a2 Segundo arbol a evaluar su igualdad con el arbol actual. <br>
     * @return Un boolean dependiendo de si los Arboles son iguales o no.
     */
    public boolean esIgual(ArbolBinario<T> a2){
        return (esIgual(this.raiz, a2.getRaiz()));
    }
    
    /**
     * Metodo que permite saber si dos Arboles Binarios son iguales de Informacion y estructura. <br>
     * <b>post: </b> Se retorno true si los arboles son Iguales. <br>
     * @param r1 Representa el NodoBin del primer Arbol evaluado. <br>
     * @param r2 Representa el NodoBin del segundo Arbol evaluado. <br>
     * @return Un boolean dependiendo de si los Arboles son iguales o no.
     */
    private boolean esIgual(NodoBin<T> r1, NodoBin<T> r2){
        if(r1==null && r2== null)
            return (true);
        if(r1==null || r2== null)
            return (false);
        if(r1.getInfo()==r2.getInfo())
            return (esIgual(r1.getIzq(),r2.getIzq())&&esIgual(r1.getDer(),r2.getDer()));
        else 
        return (false);
    }
    
    /**
     * Metodo que permite saber si dos Arboles Binarios son isomorfos; Misma estructura o forma. <br>
     * <b>post: </b> Se retorno true si los arboles son isomorfos. <br>
     * @param a2 Segundo arbol a evaluar su igualdad con el arbol actual. <br>
     * @return Un boolean dependiendo de si los Arboles son isomorfos o no.
     */
    public boolean esIsomorfo(ArbolBinario<T> a2){
        return (esIsomorfo(this.raiz, a2.getRaiz()));
    }
    
    /**
     * Metodo que permite saber si dos Arboles Binarios son isomorfos; Misma estructura o forma. <br>
     * <b>post: </b> Se retorno true si los arboles son isomorfos. <br>
     * @param r1 Representa el NodoBin del primer Arbol evaluado. <br>
     * @param r2 Representa el NodoBin del segundo Arbol evaluado. <br>
     * @return Un boolean dependiendo de si los Arboles son Isomorfos o no.
     */
    private boolean esIsomorfo(NodoBin<T> r1, NodoBin<T> r2){
        if(r1==null && r2==null)
            return (true);
        if(r1==null || r2==null)
            return (false);
        return (esIsomorfo(r1.getIzq(),r2.getIzq())&&esIsomorfo(r1.getDer(),r2.getDer()));
    }
    
    /**
     * Metodo que permite saber si dos Arboles Binarios son semejantes; Misma informacion, diferente forma. <br>
     * <b>post: </b> Se retorno true si los arboles son Semejantes. <br>
     * @param a2 Segundo arbol a evaluar su igualdad con el arbol actual. <br>
     * @return Un boolean dependiendo de si los Arboles son semejantes o no.
     */
    public boolean esSemejante(ArbolBinario<T> a2){
        if(this.getPeso()!=a2.getPeso())
            return (false);
        return (esSemejante(a2.getRaiz()));
    }

    /**
     * Metodo que permite saber si dos Arboles Binarios son semejantes; Misma informacion, diferente forma. <br>
     * <b>post: </b> Se retorno true si los arboles son Semejantes. <br>
     * @param r1 Representa el NodoBin del primer Arbol evaluado. <br>
     * @param r2 Representa el NodoBin del segundo Arbol evaluado. <br>
     * @return Un boolean dependiendo de si los Arboles son Semejantes o no.
     */
    private boolean esSemejante(NodoBin<T> r) {
        if(r==null)
            return (true);
        if(!this.esta(r.getInfo()))
            return (false);
        return (esSemejante(r.getIzq())&&esSemejante(r.getDer()));
    }
    
     /**
     * Metodo que permite conocer por consola la informacion del Arbol Binario.
     */
    public void imprime(){
        System.out.println(" ----- Arbol Binario ----- ");
        this.imprime(this.raiz);
    }
    
    /**
     * Metodo de tipo privado que permite mostrar por consola la informacion del Arbol Binario. <br>
     * @param n Representa la raiz del ArbolBinario o de alguno de sus subarboles.
     */
    public void imprime(NodoBin<T> n) {
        T l = null;
        T r = null;
        if(n==null)
            return;
        if(n.getIzq()!=null) {
         l = n.getIzq().getInfo();
        }
        if(n.getDer()!=null) {
         r =n.getDer().getInfo();
        }       
        System.out.println("NodoIzq: "+l+"\t Info: "+n.getInfo()+"\t NodoDer: "+r);
        if(n.getIzq()!=null) {
         imprime(n.getIzq());
        }
        if(n.getDer()!=null) {
         imprime(n.getDer());
        }
    }
    
    /**
     * Metodo que permite clonar la informacion de un Arbol Binario Busqueda. <br>
     * @return Un objeto de tipo ArboBinarioBusqueda con la informacion duplicada del Arbol.
     */
    public ArbolBinarioBusqueda<T> clonar(){
        ArbolBinarioBusqueda<T> t=new ArbolBinarioBusqueda<T>();
        t.setRaiz(clonarAB(this.getRaiz()));
        return(t);
    }


    private NodoBin<T> clonarAB(NodoBin<T> r){				
        if(r==null)
            return r;
        else
        {
            NodoBin<T> aux=new NodoBin<T>(r.getInfo(), clonarAB(r.getIzq()), clonarAB(r.getDer()));
            return aux;
        }
    }
   
}//Fin de la Clase ArbolBinario
