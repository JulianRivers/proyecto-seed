/**
 * ---------------------------------------------------------------------
 * $Id: ArbolRojiNegro.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;
import java.util.Iterator;
/**
 * Implementacion de clase para el manejo de un Arbol RojiNegro. <br>
 * @param <T> Tipo de datos a almacenar en el Arbol RojiNegro. <br>
 * @author Yulieth Pabon
 * @version 1.0
 */
public class ArbolRojiNegro<T> extends ArbolBinarioBusqueda<T>{

    ////////////////////////////////////////////////////////////
    // ArbolRojiNegro - Atributos ///////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Nodo que representa las hojas del Arbol Rojinegro con informacion NULA.
     */
    private NodoRN<T> nulo;
    
    ////////////////////////////////////////////////////////////
    // ArbolRojiNegro - Implementacion de Metodos ///////////////////
    ////////////////////////////////////////////////////////////
    
   /**
    * Crea un arbol RojiNegro vacio. <br>
     * <b>post: </b> Se creo un arbol RojiNegro vacio. <br>
    */
     public ArbolRojiNegro() {
        super();
        nulo = new NodoRN<T>();
        nulo.setInfo(null);
        nulo.setPadre(nulo);
        nulo.setIzq(nulo);
        nulo.setDer(nulo);
        nulo.setColor(1);
     }

     /**
     * Crea un arbol con una raiz predefinida. <br>
     * <b>post: </b> Se creo un arbol RojiNegro con una raiz predefinida. <br>
     * @param r Representa la raiz del ArbolRojiNegro que se quiere crear.
     */
     public ArbolRojiNegro(T r) {
         super.setRaiz(new NodoRN<T>(r));
    }
     
    /**
     * Metodo que permite conocer el objeto raiz del Arbol RojiNegro. <br>
     * <b>post: </b> Se retorno el objeto raiz del Arbol. <br>
     * @return Un objeto de tipo T que representa el dato en la raiz del Arbol.
     */
     @Override
    public T getObjRaiz() {
        return (super.getObjRaiz());
    }


    /**
     *  Metodo que permite insertar un dato en el arbol Rojinegro. <br>
     * <b>post: </b> Se inserto un nuevo dato al arbol Rojinegro. <br>
     * @param dato un elemento tipo T que se desea almacenar en el arbol. <br>
     * @return  true si el elemento fue insertado o false en caso contrario.
     */
     @Override
    public boolean insertar(T dato){    
        
         //Insertarlo como en ABB y con color 0.
         NodoRN<T> z = new NodoRN<T>(dato, nulo, nulo, nulo);
         //codigo del PDF
            NodoRN<T> y = nulo;
            NodoRN<T> x = (NodoRN<T>) super.getRaiz();
            while (x!=null && x.getInfo()!=null) {
                y = x;
                int compara=((Comparable)z.getInfo()).compareTo(x.getInfo());
                if (compara<0)
                    x = x.getIzq();
                else
                    x = x.getDer();
            }
            z.setPadre(y);
            if (y.getInfo() == null)
                super.setRaiz(z);
            else{
                int compara=((Comparable)z.getInfo()).compareTo(y.getInfo());
                if (compara<0)
                        y.setIzq(z);
                    else
                        y.setDer(z);
            }       
            z.setIzq(nulo);
            z.setDer(nulo);            
            z.setColor(0);
            corregirInsercion(z);         
         return (true);
    }
     
   /**
     * Metodo que permite corregir las propiedades del ArbolRojiNegro despues de realizada la insercion del dato. <br>
     * @param z Representa la raiz del Arbol del arbol RojiNegro. <br>
     */
    private void corregirInsercion(NodoRN<T> z) {
        NodoRN<T> y;
        while (z.getPadre().getColor() == 0){
            if (z.getPadre() == z.getPadre().getPadre().getIzq()) {
                 y = z.getPadre().getPadre().getDer();
                if (y.getColor()==0) {
                    z.getPadre().setColor(1);
                    y.setColor(1);
                    z.getPadre().getPadre().setColor(0);
                    z = z.getPadre().getPadre();
                } 
                else {
                    if (z == z.getPadre().getDer()) {
                        z = z.getPadre();
                        rotarIzq(z);
                    }
                    z.getPadre().setColor(1);
                    z.getPadre().getPadre().setColor(0);
                    rotarDer(z.getPadre().getPadre());
                }
            } 
            else {
                y = z.getPadre().getPadre().getIzq();
                if (y.getColor()==0) {
                    z.getPadre().setColor(1);
                    y.setColor(1);
                    z.getPadre().getPadre().setColor(0);
                    z = z.getPadre().getPadre();
                } 
                else {
                    if (z == z.getPadre().getIzq()) {
                        z = z.getPadre();
                        rotarDer(z);
                    }
                    z.getPadre().setColor(1);
                    z.getPadre().getPadre().setColor(0);
                    rotarIzq(z.getPadre().getPadre());
                    }
            }
        }
        ((NodoRN<T>)super.getRaiz()).setColor(1);
    }
     
     
    /**
     * Metodo que permite buscar un Nodo RojiNegro y conocer su informacion y direccion de memoria. <br>
     * @param r Representa la raiz del Arbol o subarbol del rojinegro. <br>
     * @param info Representa la informacion del Nodo que se quieren ubicar. <br>
     * @return Un objeto de tipo NodoRN<T> con el Nodo que contiene la informacion que desea buscar.
     */
     private NodoRN<T> buscarRN(NodoRN<T> r, T info){
        if(r==null || r.getInfo()==null)
            return (nulo);
        if(r.getInfo().equals(info))
            return r;
        else
        {
            NodoRN<T> aux = ((r.getIzq().getInfo()==null)) ? nulo : buscarRN(r.getIzq(),info);
            if(aux!=nulo && aux.getInfo()!=null)
                return (aux);
            else
                return ((r.getDer().getInfo()==null)) ? nulo : buscarRN(r.getDer(),info);
        }
    }

     /**
      * Metodo de tipo publico que permite eliminar un dato del Arbol RojiNegro. <br>
      * @param x Representa el dato de tipo T que desea ser eliminado del ArbolRojinegro.
      * @return Un objeto de tipo boolean con true si se pudo elimianar exitosamente y false en caso contrario.
      */
    @Override
     public boolean eliminar(T x){
         NodoRN<T> n = buscarRN((NodoRN<T>)super.getRaiz(),x);   
         if(n==nulo || n.getInfo()==null)
             return (false); //No encontrado
         eliminarRN(n);
         return (true);
     }
     
    /**
     * Metodo que permite eliminar un dato de un Arbol Rojinegro. <br>
     * @param z Representa la raiz del Arbol Rojingro. <br>
     * @return Un objeto de tipo NodoRN<T> con la informacion del Nodo desconectado del Arbol. <br>
     */
    public NodoRN<T> eliminarRN(NodoRN<T> z){
        NodoRN<T> x,y;
        if (z.getIzq().getInfo()!=null && z.getDer().getInfo()!=null)
            y=getMayor(z.getIzq());//también sirve buscarMin(z.getDer())
        else
            y=z;
        if (y.getIzq().getInfo()!=null)
            x = y.getIzq();
        else
            x = y.getDer();
        x.setPadre(y.getPadre());
        if (y.getPadre().getInfo()==null)
            super.setRaiz(x);
        else{
                if (y == y.getPadre().getIzq())
                    y.getPadre().setIzq(x);
                else
                    y.getPadre().setDer(x);
        }
        if (y.getInfo()!=z.getInfo())
            z.setInfo(y.getInfo()); //copiar datos adicionales si aplica
        if (y.getColor()==1)
            corregirBorrado(x);
        return (y);
    }

    /**
     * Metodo que permite corregir laas propiedades del ArbolRojinegro despues de realizada la eliminacion del dato. <br>
     * @param x Representa el NodoRN desde el cual se desea corregir las propiedades del Arbol.
     */
    private void corregirBorrado(NodoRN<T> x) {
        NodoRN<T> w;
        NodoRN<T> padre;
        while (x!=((NodoRN<T>)super.getRaiz()) && x.getColor()==1){
            if (x == x.getPadre().getIzq()) {
                w = x.getPadre().getDer();
                if (w.getColor() == 0) {
                    w.setColor(1);
                    x.getPadre().setColor(0);
                    this.rotarIzq(x.getPadre());
                    w = x.getPadre().getDer();
                }
                if (w.getIzq().getColor()==1 && w.getDer().getColor()==1) {
                    w.setColor(0);
                    x = x.getPadre();
                }
                else {
                    padre = x.getPadre();
                    if (w.getDer().getColor()==1) {
                        w.getIzq().setColor(1);
                        w.setColor(0);
                        this.rotarDer(w);
                        w = padre.getDer();
                    }
                    w.setColor(padre.getColor());
                    padre.setColor(1);
                    w.getDer().setColor(1);                    
                    this.rotarIzq(padre);
                    x = ((NodoRN<T>) super.getRaiz());
                }
            } 
            else {
            //lo mismo, pero intercambiando izq y der
                w = x.getPadre().getIzq();
                if (w.getColor() == 0) {
                    w.setColor(1);
                    x.getPadre().setColor(0);
                    this.rotarDer(x.getPadre());
                    w = x.getPadre().getIzq();
                }
                if (w.getDer().getColor() == 1 && w.getIzq().getColor() == 1) {
                    w.setColor(0);
                    x = x.getPadre();
                } else {
                    padre = x.getPadre();
                    if (w.getIzq().getColor() == 1) {
                        w.getDer().setColor(1);
                        w.setColor(0);
                        this.rotarIzq(w);
                        w = padre.getIzq();
                    }
                    w.setColor(padre.getColor());
                    padre.setColor(1);
                    w.getIzq().setColor(1);
                    this.rotarDer(padre);
                    x = ((NodoRN<T>) super.getRaiz());
            }
            }            
        }
        x.setColor(1);
    }
     /**
      * Metodo que permite conocer el elemento menor al NodoRN recibido. <br>
      * @param r Representa el NodoRn del cual se desea hallar el Nodo menor. <br>
      * @return El Nodo menor por la Izquierda del NodoRn recibido.
      */
     private NodoRN<T> getMenor(NodoRN<T> r){
         return r.getIzq()==nulo ? r : getMenor(r.getIzq());
     }
    
     /**
      * Metodo que permite conocer el elemento mayor al NodoRN recibido. <br>
      * @param r Representa el NodoRn del cual se desea hallar el Nodo mayor. <br>
      * @return El Nodo mayor por la Derecha del NodoRn recibido.
      */
     private NodoRN<T> getMayor(NodoRN<T> r){
         return r.getDer().getInfo()==null ? r : getMayor(r.getDer());
     } 

  
    /** Metodo que permite rotar hacia la izquierda para mantaner la altura negra. <br>
     * <b>post: </b> Se realizao una rotacion hacia la izquierda en el arbol RojiNegro. <br>
    * @param t representa la raiz del arbol <br>
    */
    public void rotarIzq(NodoRN<T> t) {
        NodoRN<T> t2= t.getDer();
        t.setDer(t2.getIzq());
        t2.getIzq().setPadre(t);
        t2.setPadre(t.getPadre());
        if (t.getPadre().getInfo()==null){
            super.setRaiz(t2);
            t2.setPadre(nulo);
        }
        else{
            if (t==t.getPadre().getIzq())
                t.getPadre().setIzq(t2);
            else
                t.getPadre().setDer(t2);
        }
        t2.setIzq(t);
        t.setPadre(t2);
    }

    /** Metodo que permite rotar hacia la derecha para mantaner la altura negra. <br>
     * <b>post: </b> Se realizo una rotacion hacia la derecha en el arbol RojiNegro. <br>
    * @param t representa la raiz del arbol. <br>
    */
    public void  rotarDer(NodoRN<T> t) {
        NodoRN<T> t2 = t.getIzq();
        t.setIzq(t2.getDer());
        t2.getDer().setPadre(t);
        t2.setPadre(t.getPadre());
        if (t.getPadre().getInfo()==null){
            super.setRaiz(t2);
            t2.setPadre(nulo);
        }
        else{
            if (t==t.getPadre().getIzq())
                t.getPadre().setIzq(t2);
            else
                t.getPadre().setDer(t2);
        }
        t2.setDer(t);
        t.setPadre(t2);
    }
    
    /**
     * Metodo que permite saber si existe un dato en el arbol RojiNegro. <br>
     * <b>post: </b> Se retorno true si el elemento se encuentra en el arbol RojiNegro. <br>
     * @param x 
     * @return un boolean , true si el dato esta o false en caso contrario.
     */
     @Override
    public boolean esta(T x){
        return(estaRN((NodoRN<T>)super.getRaiz(),x));
    }
     
    /**
     * Metodo que permite conocer si un elemento especifico se encuentra en el arbol. <br>
     * <b>post: </b> Se retorno true si el elemento se encuentra en el arbol. <br>
     * @param r representa la raiz del arbol. <br>
     * @param x representa la informacion del elemento que se encontrar en el arbol. <br>
     * @return un boolean , true si el dato esta o false en caso contrario.
     */
    private boolean estaRN(NodoRN<T> r, T x){
        if (r==null || r.getInfo()==null)
            return (false);
        int compara=((Comparable)r.getInfo()).compareTo(x);
        if(compara>0)
            return(estaRN(r.getIzq(),x));
        else
            if(compara<0)
                return(estaRN(r.getDer(),x));
            else
                return (true);
    }

    
    /**
     * Metodo que retorna un iterador con las hojas del arbol binario. <br>
     * <b>post: </b> Se retorno un iterador con las hojas del arbol binario.<br>
     * @return un iterador con las hojas del arbol binario 
     */
    @Override
    public Iterator<T> getHojas(){
        ListaCD<T> l=new ListaCD<T>();
        getHojas((NodoRN<T>) super.getRaiz(), l);
        return (l.iterator());
    }

    /**
     * Metodo de tipo privado que retorna un iterador con las hojas del arbol binario. <br>
     * <b>post: </b> Se retorno un iterador con las hojas del arbol binario.<br>
     * @param r representa la raiz del arbol, o raiz de subarbol. <br>
     * @param l Lista para el almacenamiento de los datos del arbol. <br>
     */
    private void getHojas(NodoRN<T> r, ListaCD<T> l){
        if (r!=null && r.getInfo()!=null){
            if(this.esHoja(r))
                l.insertarAlFinal(r.getInfo());
            getHojas(r.getIzq(), l);
            getHojas(r.getDer(), l);
        }
    }
    
    /**
     * Metodo que permite determinar el numero de Nodo hojas dentro del Arbol Binario. <br>
     * <b>post: </b> Se retorno el numero de hojas del Arbol. <br>
     * @return El numero de hojas existentes en el Arbol Binario.
     */
    @Override
    public int contarHojas(){
        return (contarHojas((NodoRN)super.getRaiz()));
    }
    
    /**
     * Metodo que permite determinar el numero de Nodo hojas dentro del Arbol Binario. <br>
     * <b>post: </b> Se retorno el numero de hojas del Arbol. <br>
     * @param r representa la raiz del arbol, o raiz de subarbol. <br>
     * @return El numero de hojas existentes en el Arbol Binario.
     */
    private int contarHojas(NodoRN<T> r){
        if(r==null || r.getInfo()==null)
            return (0);
        if(this.esHoja(r))
            return (1);
        int chi = contarHojas(r.getIzq());
        int chd = contarHojas(r.getDer());
        return (chi+chd);
    }
    
    private boolean esHoja(NodoRN<T> n) {
        return (n!=null && n.getInfo()!=null && (n.getIzq()==null||n.getIzq().getInfo()==null) && (n.getDer()==null||n.getDer().getInfo()==null));
    }
    
    
    /**
     *  Metodo que retorna un iterador con el recorrido preOrden del Arbol Binario. <br>
     * <b>post: </b> Se retorno un iterador en preOrden para el arbol.<br>
     * @return un iterador en preorden (padre->hijoIzq->hijoDer) para el arbol binario.
     */
    @Override
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
        if(r!=null && r.getInfo()!=null){
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
    @Override
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
        if(r!=null && r.getInfo()!=null){
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
    @Override
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
        if(r!=null && r.getInfo()!=null){
            postOrden(r.getIzq(), l);
            postOrden(r.getDer(), l);
            l.insertarAlFinal(r.getInfo());
        }
    }
    
    /**
     * Metodo que permite retornar un iterador con el recorrido por niveles del Arbol RojiNegro. <br>
     * <b>post: </b> Se retorno el recorrido por niveles del Arbol RojiNegro. <br>
     * @return un un iterador con el recorrido por niveles del Arbol RojiNegro.
     */
     @Override
    public Iterator<T> impNiveles(){
        ListaCD<T> l=new ListaCD<T>();
        if(!this.esVacio()){
            Cola<NodoRN<T>> c=new Cola<NodoRN<T>>();
            c.enColar((NodoRN<T>) this.getRaiz());
            NodoRN<T> x;
                while(!c.esVacia()){
                    x=c.deColar();
                    l.insertarAlFinal(x.getInfo());
                    if(x.getIzq()!=null && x.getIzq().getInfo()!=null )
                    c.enColar(x.getIzq());
                    if(x.getDer()!=null && x.getDer().getInfo()!=null)
                    c.enColar(x.getDer());
                }
        }
        return (l.iterator());
    }
    
    /**
     * Metodo que permite obtener el peso del Arbol RojiNegro. <br>
     * <b>post: </b> Se retorno el numero de elementos en el Arbol RojiNegro. <br>
     * @return Un entero con la cantidad de elementos del Arbol RojiNegro.
     */
     @Override
    public int getPeso(){
        return this.getPesoRN((NodoRN<T>)super.getRaiz());
    }
     
    private int getPesoRN(NodoRN<T> r){
        if(r==null || r.getInfo()==null)
            return 0;
        return (getPesoRN(r.getIzq())+1+getPesoRN(r.getDer()));
    }

   /**
     * Metodo que permite saber si el arbol se encuentra vacio. <br>
     * <b>post: </b> Se retorno true si el arbol no contiene elementos. <br>
     * @return true su no hay datos en el arbol.
     */
     @Override
    public boolean esVacio(){
        return (((NodoRN<T>)super.getRaiz())==null || ((NodoRN<T>)super.getRaiz()).getInfo()==null);
    }
    
    /**
     * Metodo que permite obtener la altura del Arbol RojiNegro. <br>
     * <b>post: </b> Se retorno la altura del Arbol RojiNegro.<br>
     * @return Un entero con la altura del Arbol RojiNegro.
     */
     @Override
    public int getAltura(){
         if(super.getRaiz()==null || super.getRaiz().getInfo()==null)
            return (0);
        return(getAltura((NodoRN<T>)this.getRaiz()));
    }
     
     private int getAltura(NodoRN<T> r){
        int ai=0, ad=0;
        if(r.getIzq().getInfo()!=null)
            ai = getAltura(r.getIzq());
        if(r.getDer().getInfo()!=null)
            ad = getAltura(r.getDer());
        if(ai>=ad)
            return (ai+1);
        return (ad+1);        
     }
     
    /**
     * Metodo que permite limpiar la informacion del Arbol Rojinegro.
     */
    public void limpiar(){
        super.setRaiz(null);
    }
    
    /**
     * Metodo que permite clonar la informacion de un Arbol Rojinegro en un nuevo Arbol Rojinegro con la misma informacion. <br>
     * @return Un nuevo ArbolRojiNegro con la misma informacion del ArbolRojiNegro actual. <br>
     */
    @Override
    public ArbolRojiNegro<T> clonar(){
        ArbolRojiNegro<T> t= new ArbolRojiNegro<T>();
        t.setRaiz(clonarRN((NodoRN<T>)getRaiz(),nulo));
        return(t);
    }
     
    private NodoBin<T> clonarRN(NodoRN<T> r, NodoRN<T> p){				
        if(r==null || r.getInfo()==null)
            return (r);
        else
        {
            NodoRN<T> aux = new NodoRN<T>(r.getInfo());
            aux.setColor(r.getColor());
            aux.setPadre(p);
            aux.setIzq(clonarRN(r.getIzq(),aux));
            aux.setDer(clonarRN(r.getDer(),aux));
            return aux;
        }
    }

    /**
     * Metodo que permite imprimir los datos que contiene el Arbol RojiNegro. <br>
     * <b>post: </b> Se imprimio los datos que contiene el Arbol RojiNegro. <br>
     */
     @Override
     public void imprime(){
        this.imprimeRN((NodoRN<T>) getRaiz());
    }
    
     /**
     * Metodo que permite imprimir los datos que contiene el Arbol RojiNegro. <br>
     * <b>post: </b> Se imprimio los datos que contiene el Arbol RojiNegro. <br>
     * @param n representa la raiz o subraiz del Arbol RojiNegro. <br>
     */
    public void imprimeRN(NodoRN<T> n) {
        int l = -1;
        int r = -1;
        int p = -1;
        if(n.getIzq()!=nulo) {
            l = Integer.parseInt(n.getIzq().getInfo().toString());
        }
        if(n.getDer()!=nulo) {
            r = Integer.parseInt(n.getDer().getInfo().toString());
        }
        if(n.getPadre()!=nulo) {
            p = Integer.parseInt(n.getPadre().getInfo().toString());
        }        
        System.out.println("Izquierdo: "+l+" Info: "+n.getInfo()+" Derecha: "+r+" Padre: "+p+" Color: "+n.getColor()+"\n");
        if(n.getIzq()!=nulo) {
            imprimeRN(n.getIzq());
        }
        if(n.getDer()!=nulo) {
            imprimeRN(n.getDer());
        }         
    }   
  
}//Fin de la Clase ArbolRojiNegro
