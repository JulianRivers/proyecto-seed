/**
 * ---------------------------------------------------------------------
 * $Id: ArbolBinarioBusqueda.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;

import java.util.Iterator;

/**
 * Implementacion de Clase para el manejo de un Arbol Binario de busqueda (ordenado). <br>
 * @param <T> Tipo de dato a almacenar en el Arbol Binario de Busqueda. <br>
 * @author Marco Adarme
 * @version 2.0
 */
public class ArbolBinarioBusqueda <T> extends ArbolBinario<T> {

    
    ////////////////////////////////////////////////////////////
    // ArbolBinarioDeBusqueda  - Implementacion de Metodos /////
    ////////////////////////////////////////////////////////////
    
   /**
    * Crea un Arbol Binario de Busqueda vacio. <br>
    * <b>post: </b> Se creo un Arbol Binario de Busqueda vacio. <br>
    */
    public ArbolBinarioBusqueda(){
        super();
    }
    
    /**
     * Crea un arbol con una raiz predefinida. <br>
     * <b>post: </b> Se creo un Arbol Binario de Busqueda con raiz predeterminada. <br>
     * @param raiz  un tipo T, almacena la direccion de memoria de un nodo de un Arbol Binario de Busqueda. <br>
     */
     public ArbolBinarioBusqueda(T raiz){
         super(raiz);
     }
     
     @Override
     public NodoBin<T> getRaiz(){
         return (super.getRaiz());
     }
     
     /**
     * Metodo que permite conocer el objeto raiz del Arbol AVL. <br>
     * <b>post: </b> Se retorno el objeto raiz del Arbol. <br>
     * @return Un objeto de tipo T que representa el dato en la raiz del Arbol.
     */
    @Override
    public T getObjRaiz() {
        return super.getObjRaiz();
    }
   
    /**
     * Metodo que permite insertar un dato en el Arbol Binario de Busqueda. <br>
     * <b>post: </b> Se inserto un nuevo dato al Arbol Binario de Busqueda. <br>
     * @param dato un elemento tipo T que se desea almacenar en el arbol. <br>
     * @return  true si el elemento fue insertado o false en caso contrario
     */
     public boolean insertar(T dato){
        NodoBin<T> rr=this.esta(dato)?null:insertar(this.getRaiz(), dato);
        if(rr!=null)
             this.setRaiz(rr);
        return (rr!=null);
     }

    /**
     * Metodo que permite insertar un dato en el Arbol Binario de Busqueda segun factor de ordenamiento. <br>
     * <b>post: </b> Se inserto ordenado un nuevo dato al Arbol Binario de Busqueda. <br>
     * @param r de tipo NoboBin<T> que representa la raiz del arbol. <br>
     * @param dato elemento a insertar en el arbol de forma ordenada. <br>
     * @return true si el elemento fue insertado o false en caso contrario
     */
    private NodoBin<T> insertar(NodoBin<T> r, T dato){
        if(r==null)
            return(new NodoBin<T>(dato,null, null));
        int compara=((Comparable)r.getInfo()).compareTo(dato);
        if(compara>0) 
            r.setIzq(insertar(r.getIzq(), dato));
        else
            if(compara<0)  
                    r.setDer(insertar(r.getDer(), dato));
            else{
            System.err.println("Error dato duplicado:"+dato.toString());
            }
        return r;
    }

    /**
     * Metodo que permite borrar un elmento del Arbol Binario de Busqueda. <br>
     * <b>post: </b> Se elimino el elemento en el Arbol Binario de Busqueda. <br>
     * @param x dato que se desea eliminar. <br>
     * @return  el dato borrado o null si no lo encontro
     */
    @Override
    public boolean eliminar(T x){
        if(!this.esta(x)){
            return (false);
        }
        NodoBin<T> z=eliminarABB(this.getRaiz(),x);
        this.setRaiz(z);
        return (true);
    }
    
     /**
      * Metodo de tipo privado que permite eliminar un dato en el Arbol Binario de Busqueda segun factor de ordenamiento, manteniendo su propiedad de orden,
      * para esto se busca el menor de los derechos y lo intercambia por el dato que desea eliminar. La idea del algoritmo es que el dato a eliminar 
      * se coloque en una hoja o en un nodo que no tenga una de sus ramas. <br>
      * <b>post: </b> Se elimino el elemento Arbol Binario de Busqueda. <br>
      * @param r de tipo NoboBin<T> que representa la raiz del arbol. <br>
      * @param dato elemento que se desea eliminar del arbol. <br>
      * @return el dato borrado o null si no lo encontro
      */
    private NodoBin<T> eliminarABB(NodoBin<T> r, T x){
        if (r==null)
                return null;//<--Dato no encontrado		
        int compara=((Comparable)r.getInfo()).compareTo(x);
        if(compara>0)
                r.setIzq(eliminarABB(r.getIzq(), x));
        else
            if(compara<0)
                    r.setDer(eliminarABB(r.getDer(), x));
            else{
                if(r.getIzq()!=null && r.getDer()!=null){
                     NodoBin<T> cambiar=this.masIzquierda(r.getDer());
                     T aux=cambiar.getInfo();
                     cambiar.setInfo(r.getInfo());
                     r.setInfo(aux);
                     r.setDer(eliminarABB(r.getDer(),x));
                    }
                else{
                    r=(r.getIzq()!=null)? r.getIzq():r.getDer();
                 }
            }
        return r;
    }

    /**
     * Metodo que busca el menor dato del arbol. El menor dato del arbol se encuentra en el nodo mas izquierdo. <br>
     * <b>post: </b> Se retorno el nodo mas izquierdo del arbol. <br>
     * @param r reprenta la raiz del arbol. <br>
     * @return el nodo mas izquierdo del arbol
     */
    @SuppressWarnings("empty-statement")
    protected NodoBin<T> masIzquierda(NodoBin<T> r){
        for(; r.getIzq()!=null; r=r.getIzq());
        return(r);
    }
    
    /**
     * Metodo que permite evaluar la existencia de un dato dentro del Arbol Binario de Busqueda es necesario para que el metodo funcione 
     * que los objetos almacenados en el arbol tengan sobreescrito el metodo equals. <br>
     * <b>post: </b> Se retorno true si el elemento se encuentra en el Arbol. <br>
     * @param x representa la informacion del elemento que se encontrar en el arbol. <br>
     * @return un boolean , true si el dato esta o false en caso contrario.
     */
    public boolean estaABB(T x){
        return(esta(this.getRaiz(), x));
    }
    
    /**
     * Metodo que permite conocer si un elemento especifico se encuentra en el arbol. <br>
     * <b>post: </b> Se retorno true si el elemento se encuentra en el arbol. <br>
     * @param r representa la raiz del arbol. <br>
     * @param x representa la informacion del elemento que se encontrar en el arbol. <br>
     * @return un boolean , true si el dato esta o false en caso contrario.
     */
    private boolean esta(NodoBin<T> r, T x){
        if (r==null)
            return (false);
        int compara=((Comparable)r.getInfo()).compareTo(x);
        if(compara>0)
            return(esta(r.getIzq(),x));
        else
            if(compara<0)
                return(esta(r.getDer(),x));
            else
                return (true);
    }
    
    /**
     * Metodo que permite consultar un elemento existente dentro del Arbol Binario de Busqueda. <br>
     * <b>post: </b> Se retorno un NodoBin<T> perteneciente al dato buscado. <br>
     * @param info Elemento a ubicar dentro del Arbol Binario de Busqueda. <br>
     * @return Un objeto de tipo NodoBin<T> que representa el objeto buscado.
     */
    protected NodoBin<T> buscar(T info){
        return (buscar(this.getRaiz(),info));
    }
   
    /**
     * Metodo que permite consultar un elemento existente dentro del Arbol Binario de Busqueda. <br>
     * <b>post: </b> Se retorno un NodoBin<T> perteneciente al dato buscado. <br>
     * @param info Elemento a ubicar dentro del Arbol Binario de Busqueda. <br>
     * @param r Representa la raiz del Arbol. <br>
     * @return Un objeto de tipo NodoBin<T> que representa el objeto buscado.
     */
    protected NodoBin<T> buscar(NodoBin<T> r, T info){
        if(r==null)
            return (null);
        if(r.getInfo().equals(info))
            return r;
        else
        {
            NodoBin<T> aux = (r.getIzq()==null)?null:buscar(r.getIzq(),info);
            if(aux!=null)
                return (aux);
            else
                return (r.getDer()==null)?null:buscar(r.getDer(),info);
        }
    }
    
    /**
     * Metodo que retorna un iterador con las hojas del Arbol Binario de Busqueda. <br>
     * <b>post: </b> Se retorno un iterador con las hojas del Arbol Binario de Busqueda.<br>
     * @return un iterador con las hojas del Arbol Binario de Busqueda.
     */
    @Override
    public Iterator<T> getHojas(){
        return (super.getHojas());
    }
        
    /**
     * Metodo que permite determinar el numero de Nodo hojas dentro del Arbol Binario de Busqueda. <br>
     * <b>post: </b> Se retorno el numero de hojas del Arbol de Busqueda. <br>
     * @return El numero de hojas existentes en el Arbol Binario de Busqueda.
     */
    @Override
    public int contarHojas(){
        return (super.contarHojas());
    }
    
    /**
     *  Metodo que retorna un iterador con el recorrido preOrden del Arbol Binario de Busqueda. <br>
     * <b>post: </b> Se retorno un iterador en preOrden para el arbol. <br>
     * @return un iterador en preorden (primero la raiz luego los hijos) para el  Arbol Binario de Busqueda.
     */
    @Override
     public Iterator<T> preOrden(){
         return (super.preOrden());
    }

   /**
     * Metodo que retorna un iterador con el recorrido in Orden del Arbol Binario. <br>
     * <b>post: </b> Se retorno un iterador inOrden para el arbol. <br>
     * @return un iterador en inOrden (primero el hijo izquierdo luego la raiz y despues el hijo derecho) para el  Arbol Binario de Busqueda. <br>
     */
    @Override
    public Iterator<T> inOrden(){
        return (super.inOrden());
    }
    /**
     * Metodo que retorna un iterador con el recorrido pos Orden del  Arbol Binario de Busqueda. <br>
     * <b>post: </b> Se retorno un iterador postOrden para el arbol.<br>
     * @return un iterador en postOrden (primero los hijos y luego la raiz) para el  Arbol Binario de Busqueda. <br>
     */
    @Override
    public Iterator<T> postOrden(){
        return (super.postOrden());
    }
  
    /**
     * Metodo que permite retornar un iterador con el recorrido por niveles del Arbol Binario de Busqueda. <br>
     * <b>post: </b> Se retorno el recorrido por niveles del Arbol Binario de Busqueda. <br>
     * @return un un iterador con el recorrido por niveles del Arbol Binario de Busqueda.
     */
    @Override
    public Iterator<T> impNiveles(){
        return (super.impNiveles());
    }
    
    /**
     * Metodo que permite obtener el peso del Arbol Binario de Busqueda. <br>
     * <b>post: </b> Se retorno el numero de elementos en el Arbol Binario de Busqueda. <br>
     * @return Un entero con la cantidad de elementos del Arbol Binario de Busqueda.
     */
    @Override
    public int getPeso(){
        return(super.getPeso());
    }

    /**
     * Metodo que permite saber si el arbol se encuentra vacio. <br>
     * <b>post: </b> Se retorno true si el arbol no contiene elementos. <br>
     * @return true si no hay datos en el arbol
     */
    @Override
    public boolean esVacio(){
        return(super.esVacio());
    }
    
    /**
     * Metodo que permite obtener la altura del Arbol Binario de Busqueda. <br>
     * <b>post: </b> Se retorno la altura del Arbol Binario de Busqueda.<br>
     * @return Un entero con la altura del Arbol Binario de Busqueda.
     */
    @Override
    public int getAltura(){
        return(super.getAltura());
    }
    
     /**
     * Metodo que permite clonar la informacion de un ArbolBinario de Busqueda y retornarla en un nuevo Arbol. <br>
     * @return Un nuevo ArbolBinarioBusqueda con la informacion clonada del actual Arbol.
     */
    @Override
    public ArbolBinarioBusqueda<T> clonar(){
        ArbolBinarioBusqueda<T> t=new ArbolBinarioBusqueda<T>();
        t.setRaiz(clonarABB(this.getRaiz()));
        return(t);
    }


    private NodoBin<T> clonarABB(NodoBin<T> r){				
        if(r==null)
            return r;
        else
        {
            NodoBin<T> aux=new NodoBin<T>(r.getInfo(), clonarABB(r.getIzq()), clonarABB(r.getDer()));
            return aux;
        }
    }
    
    /**
     * Metodo que permite conocer por consola la informacion del Arbol Binario.
     */
    @Override
    public void imprime(){
        System.out.println(" ----- Arbol Binario de Busqueda ----- ");
        this.imprimeABB(super.getRaiz());
    }
    
    /**
     * Metodo de tipo privado que permite mostrar por consola la informacion del Arbol Binario. <br>
     * @param n Representa la raiz del ArbolBinario o de alguno de sus subarboles.
     */
    public void imprimeABB(NodoBin<T> n) {
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
         imprimeABB(n.getIzq());
        }
        if(n.getDer()!=null) {
         imprimeABB(n.getDer());
        }
    }
   
}// Fin de la Clase ArbolBinarioBusqueda.