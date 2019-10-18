/**
 * ---------------------------------------------------------------------
 * $Id: ArbolHeap.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;
import java.util.Iterator;


/**
 * Implementacion de clase para manejo de Arboles Heaps. <br>
 * @param <T> Tipo de datos a almacenar dentro del Arbol. <br>
 * @author Uriel Garcia
 * @version 1.0
 */
public class ArbolHeap<T>
{
    
    ////////////////////////////////////////////////////////////
    // ArbolHeap - Atributos ///////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Representa el vector con los datos almacenado en el Arbol Heap
     */
    private T[] datos;
    
    /**
     * Representa el peso del Arbol
     */
    private int peso;
    
    /**
     * Tamaño predefinido del Heap
     */
    private final static int def = 200;
    
    
    
    ////////////////////////////////////////////////////////////
    // ArbolHeap - Implementacion de Metodos ///////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Crea un Arbol Heap vacio con un tamaño predefinido. <br>
     * <b>post: </b> Se creo un Arbol Heap vacio.<br>
     */
    public ArbolHeap(){
        //def es un tamaño por defecto para el Arbol.
        T[] temp = (T[]) new Object[def];
        this.datos = temp;
        this.peso = 0;
    }
    
    /**
     * Crea un Arbol Heap vacio, y se le define un tamaño ingresado. <br>
     * <b>post: </b> Se creo un Arbol Heap vacio.<br>
     * @param cap Representa la capacidad para almacenar datos del Heap.
     */
    public ArbolHeap(int cap){
        T[] temp = (T[]) new Object[cap];
        this.datos = temp;
        this.peso = 0;
    } 
    
    /**
     * Metodo que permite obtener la raiz del Arbol Heap; Es decir, el dato de MAYOR valor dentro del Arbol. <br>
     * <b>post: </b> Se retorno la raiz del Heap, es decir el dato mayor. <br>
     * @return Un objeto de tipo T que representa la raiz del Heap.
     */
    public T getRaiz(){
        T r = null;
        if(!this.esVacio())
            r = this.datos[0];
        return (r);
    }
    
    /**
     * Metodo que permite insertar un nuevo elemento dentro del Arbol Heap. <br>
     * <b>post: </b> Se ha insertado un nuevo elemento dentro del Heap.<br>
     * @param nuevo Representa el dato a insertar dentro del Heap.
     */
    public void insertar(T nuevo){        
        if(peso>=this.datos.length)
            return; //El Arbol se encuentra lleno.        
        if(this.esVacio()){
            this.datos[peso++] = nuevo;
            return;
        }        
        int indice = peso++;
        this.datos[indice] = nuevo; 
        while ((indice!=0)&&(((Comparable)this.datos[indice]).compareTo(this.datos[this.getPosPadre(indice)]))>0){
            this.datos = intercambiar(this.datos, indice, this.getPosPadre(indice));
            indice = this.getPosPadre(indice);
        }
    }
    
    /**
     * Metodo que permite eliminar el primer elemento (raiz) de Arbol Heap; El elemento MAYOR. <br>
     * <b>post: </b> Se retorno y elimino el elemento raiz del Arbol. El dato MAYOR. <br>
     * @return El elemento Mayor del Arbol que se ubica en la raiz.
     */
    public T eliminarRaiz(){
        if(peso<=0)
            return (null);
        this.datos = intercambiar(this.datos, 0, --peso);
        if (peso!=0)
          reorganiza(0); 
        T x = this.datos[peso];
        this.datos[peso] = null;
        return (x);
    }
    
    /**
     * Metodo que permite reorganizar los datos del Arbol despues de una eliminacion. <br>
     * <b>post: </b> Se reorganizaron los datos del Arbol Heap. 
     */
    private void reorganiza(int pos) {
      if(pos<0 || pos>peso){
          return;
      }
      while (!esHoja(pos)) {
        int j = getHijoIzq(pos);
        if ((j<(peso-1)) && (((Comparable)this.datos[j]).compareTo(this.datos[j+1]) < 0)) 
            j++;
        if (((Comparable)this.datos[pos]).compareTo(this.datos[j]) >= 0) return;
            this.datos =intercambiar(this.datos, pos, j);
            pos = j;
      }
    }
    
    /**
     * Metodo que permite saber si un elemento ingresado es una hoja del Arbol Heap. <br>
     * @param pos Posicion del elemento dentro del Arbol Heap. <br>
     * @return true o false si el elemento es o no una hoja dentro del Arbol Heap.
     */
    private boolean esHoja(int pos){ 
        return ((pos>=peso/2) && (pos<peso)); 
    }
    
    /**
     * Metodo que permite eliminar un elemento cualquiera del Arbol Heap. <br>
     * <b>post: </b> Se retorno y elimino el elemento eliminado del Heap. <br>
     * @param info Representa el dato que se desea eliminar del Heap. <br>
     * @return El elemento eliminado del Arbol Heap.
     */
    public T eliminar(T info){
        int pos = this.getPos(info);
        T x;
        if(pos==(-1))
            return (null);
        if (pos==(peso-1)){
            x = this.datos[peso-1];
            this.peso--;
            this.datos[peso] = null;
            return (x);
        }            
        else
        {
            this.datos =intercambiar(this.datos, pos, --peso);
            while ((pos>0) && (((Comparable)this.datos[pos]).compareTo(this.datos[this.getPosPadre(pos)])>0)){
                this.datos = intercambiar(this.datos, pos,this.getPosPadre(pos));
                pos = this.getPosPadre(pos);
            }
            if(peso!=0) 
                reorganiza(pos);
        }
        x = this.datos[peso];
        this.datos[peso] = null;
        return (x);
    }
    
    /**
     * Metodo de tipo privado que permite intercambiar la informacion de un vector dadas sus posiciones. <br>
     * <b>post: </b> Se cambio la informacion de cada una de las posicion del vector indicadas. <br>
     * @param h Vector que representa el Heap que se pretende editar la informacion. <br>
     * @param p1 Posicion del primer elemento al que se le desea cambiar la informacion. <br>
     * @param p2 Posicion del segundo elemento al que se le desea cambiar la informacion. <br>
     * @return Un objeto de tipo T[] que representa el Heap con la informacion modificada. <br>
     */
    private T[] intercambiar(T[] h, int p1, int p2) {
        T temp = h[p1];
        h[p1]=h[p2];
        h[p2]=temp;
        return (h);
    }
    
    /**
     * Metodo que permite obtener los datos existentes dentro del Arbol Heap. <br>
     * <b>post: </b> Se retornaron los datos del Heap en un objeto T[]. <br>
     * @return Un objeto de tipo T[] con los elementos insertados dentro del Heap.
     */
    public T[] getDatos(){
        return (this.datos);
    }
    
    /**
     * Metodo que permite conocer si un dato se encuentra dentro del Arbol Heap. <br>
     * <b>post: </b> Se evaluo la existencia de un elemento dentro del Heap. <br>
     * @param info Representa el objeto que se desea consultar dentro del Heap. <br>
     * @return true o false dependiendo se si el dato se encuentra dentro del Heap. <br>
     */
    public boolean esta(T info){
        for(int i=0; i<this.peso; i++)
            if(this.datos[i].equals(info))
                return (true);
        return (false);
    }
    
    /**
     * Metodo que permite obtener un Iterador para recorrer el Arbol Heap por niveles. <br>
     * <b>post: </b> Se retorno el recorrido por niveles del Heap por medio de un Iterados. <br>
     * @return Un objeto de tipo Iterator con el recorrido por niveles del Heap.
     */
    public Iterator<T> impNiveles( )
    {
        ListaCD<T> l=new ListaCD<T>();
        for(int i=0; i<this.peso; i++){
            l.insertarAlFinal(this.datos[i]);
        }
        return (l.iterator());
    }
    
    /**
     * Metodo que permite conocer el peso del Arbol Heap, es decir el numero de elementos insertados. <br>
     * <b>post: </b> Se retorno el peso del Heap. <br>
     * @return Un objeto de tipo int con el peso del Heap.
     */
    public int getPeso(){
        return (this.peso);
    }
    
    /**
     * Metodo que permite conocer si un Arbol Heap se encuentra vacio; no posee elementos. <br>
     * <b>post: </b> Se evaluo si el Heap se encuentra vacio. <br>
     * @return true o false dependiendo de si el Arbol Heap se encuentra vacio o no.
     */
    public boolean esVacio(){
        return (this.peso<1);
    }
    
    /**
     * Metodo que permite conocer la altura del Arbol Heap. <br>
     * <b>post: </b> Se retorno la altura del Heap. <br>
     * @return Un objeto de tipo int con la altura del Arbol Heap.
     */
    public int getAltura(){
        int alt=0;
        while(Math.pow(2,alt)<=peso)
            alt++;
        return (alt);
    }
    
    /**
     * Metodo que permite utilizar un Arbol Heap para ordenar un vector por HeapSort. <br>
     * <b>post: </b> Se retorno un vector con los datos ordenados. <br>
     * @return Un objeto de tipo T[] con la informacion (datos) ordenados.
     */
    public T[] heapSort(){        
        T aux[] = (T[]) new Object[this.datos.length];                
        for(int i=this.peso-1;i>=0;i--){
            aux[i]=this.eliminarRaiz();
        }
        return (aux);
    }
    
    /**
     * Metodo que permite limpiar la informacion de los datos ingresados al Heap. <br>
     * <b>post: </b> Se ha limpiado la informacion del Heap. <br>
     */
    public void limpiar(){
        for( ; this.peso>=0 ; this.peso--)
            this.datos[this.peso]=null;
        this.peso = 0;
    }
    
    /**
     * Metodo que permite conocer la posicion del padre de un elemento dentro del Heap. <br>
     * @param hijo Representa el dato del cual se quiere conocer la posicion del padre. <br>
     * @return Un objeto de tipo int con la posicion del padre del elemento consultado.
     */
    private int getPosPadre(int hijo){
        if(hijo<=0)
            return (-1);
        return (hijo-1)/2;
    }
    
    /**
     * Metodo que permite conocer el Hijo izquierdo de un elemento dentro del Heap.
     */
    private int getHijoIzq(int posPadre) {
        return ((2*posPadre)+1);
    }
    
    /**
     * Metodo que permite conocer el Hijo derecho de un elemento dentro del Heap.
     */
    private int getHijoDer(int posPadre) {
        return ((2*posPadre)+2);
    }
    
    /**
     * Metodo que permite conocer la posicion de un dato dentro del Heap. <br>
     */
    private int getPos(T info){
        for(int i=0; i<this.peso; i++){
            if(this.datos[i].equals(info))
                return (i);
        }            
        return (-1);
    }
    
    /**
     * Metodo que permite contar la Hojas de un Arbol Heap. <br>
     * @return Un objeto de tipo int con la cantidad de elemenos Hoja existente en el Arbol.
     */
    public int contarHojas() {
        int cant = 0;
        for(int i=0; i<this.peso; i++){
            if(this.esHoja(i))
                cant++;
        }
        return (cant);
    }
    
    /**
     * Metodo que permite conocer la Hojas del Arbol heap y retornarla en un listado. <br>
     * @return Un objeto iterador de la lista con las hojas del Arbol Heap.
     */
    public Iterator getHojas(){
        ListaCD<T> l = new ListaCD<T>();
        for(int i=0; i<this.peso; i++){
            if(this.esHoja(i))
                l.insertarAlFinal(this.datos[i]);
        }
        return (l.iterator());
    }

    /**
     * Metodo que permite eliminar los elementos Hoja del Arbol heap.
     */
    public void podar() {
        int cant = 0;
        for(int i=0; i<this.peso; i++){
            if(this.esHoja(i)){
                this.datos[i]=null;
                cant++;
            }                
        }
        this.peso= this.peso-cant;
    }
    
    /**
     * Metodo que permite conocer el recorrido en preOrden del arbol Heap.
     * @return Un iterador con el listado del recorrido en preorden del Arbol heap.
     */
    public Iterator<T> preOrden() {
        ListaCD<T> l=new ListaCD<T>();
         preOrden(0,l);
         return (l.iterator());
    }
    
    private void preOrden(int pos, ListaCD<T> l) {
        T r = this.datos[pos];
        if(r!=null){
            l.insertarAlFinal(r);
            preOrden(this.getHijoIzq(pos), l);
            preOrden(this.getHijoDer(pos), l);
        }
    }

    /**
     * Metodo que permite conocer el recorrido en inOrden del arbol Heap.
     * @return Un iterador con el listado del recorrido en inorden del Arbol heap.
     */
    public Iterator<T> inOrden() {
        ListaCD<T> l=new ListaCD<T>();
         inOrden(0,l);
         return (l.iterator());
    }
    
    private void inOrden(int pos, ListaCD<T> l) {
        T r = this.datos[pos];
        if(r!=null){            
            inOrden(this.getHijoIzq(pos), l);
            l.insertarAlFinal(r);
            inOrden(this.getHijoDer(pos), l);
        }
    }
    
    /**
     * Metodo que permite conocer el recorrido en postOrden del arbol Heap.
     * @return Un iterador con el listado del recorrido en postorden del Arbol heap.
     */
    public Iterator<T> postOrden() {
        ListaCD<T> l=new ListaCD<T>();
         postOrden(0,l);
         return (l.iterator());
    }
    
    private void postOrden(int pos, ListaCD<T> l) {
        T r = this.datos[pos];
        if(r!=null){            
            postOrden(this.getHijoIzq(pos), l);
            postOrden(this.getHijoDer(pos), l);
            l.insertarAlFinal(r);
        }
    }
    
    /**
     * Convierte el Heap a una cadena de String. <br>
     * <b>post: </b> Se retorno la representacion en String del Arbol heap. 
     * @return La representacion en String del Heap.
     */
    @Override
    public String toString(){
        String cad = "";
        for(int i=0; i<this.peso; i++){
            cad+=this.datos[i].toString()+"-";
        }
        return (cad);
    }
    
}// Fin de la clase ArbolHeap
    

