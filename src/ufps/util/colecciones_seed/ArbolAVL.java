/**
 * ---------------------------------------------------------------------
 * $Id: ArbolAVL.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;

import java.util.Iterator;

/**
 * Implementacion de Clase para el manejo de un Arbol AVL.
 * @param <T> Tipo de datos a almacenar en el Arbol AVL.
 * @author Uriel Garcia
 * @version 1.0
 */
public class ArbolAVL<T> extends ArbolBinarioBusqueda<T> {
 
    
    ////////////////////////////////////////////////////////////
    // ArbolAVL - Implementacion de Metodos ////////////////////
    ////////////////////////////////////////////////////////////  
    
    /**
     * Crea un Arbol AVL vacio. <br>
     * <b>post: </b> Se creo un Arbol AVL vacio.<br>
     */
    public ArbolAVL(){
        super();
    }
    
    /**
     * Crea un Arbol AVL con una raiz predefinida. <br>
     * <b>post: </b> Se creo un nuevo Arbol AVL con raiz predeterminada.<br>
     * @param r  Un tipo <T> , almacena la direccion de memoria de un nodo de un Arbol AVL<br>
     */
    public ArbolAVL(T r) {
        super.setRaiz(new NodoAVL<T>(r));
    }
    
    /**
     * Metodo que permite conocer el objeto raiz del Arbol AVL. <br>
     * <b>post: </b> Se retorno el objeto raiz del Arbol. <br>
     * @return Un objeto de tipo T que representa el dato en la raiz del Arbol.
     */
    @Override
    public T getObjRaiz() {
        return (super.getObjRaiz());
    }
        
    /**
     * Metodo que permite insertar un nuevo dato dentro del Arbol AVL sin que se pierda el balance. <br>
     * <b>post: </b> Se inserto un nuevo dato dentro del Arbol AVL. <br>
     * @param nuevo Representa el nuevo que se pretende ingresar al Arbol AVL. <br>
     * @return true o false dependiendo si se pudo o no insertar el nuevo elemento dentro del Arbol
     */
    @Override
    public boolean insertar(T nuevo){
        NodoAVL<T> n = new NodoAVL<T>(nuevo);
        return (insertaAVL((NodoAVL<T>)super.getRaiz(),n));
    }
    
    /**
     * Metodo que permite insertar un nuevo dato dentro del Arbol AVL sin que se pierda el balance. <br>
     * <b>post: </b> Se inserto un nuevo dato dentro del Arbol AVL. <br>
     * @param p Representa la raiz del Arbol AVL en el cual se inserta el nuevo dato. <br>
     * @param q Representa el Nodo<T> que sera insertado dentro del Arbol AVL. <br>
     * @return true o false dependiendo si se pudo o no insertar el nuevo elemento dentro del Arbol
     */
    private boolean insertaAVL(NodoAVL<T> p, NodoAVL<T> q){
        //Si el Arbol se encuentra vacio
        if(this.esVacio()) {
            setRaiz(q);
            return (true);
        }
        int comp = ((Comparable)q.getInfo()).compareTo(p.getInfo());
        if(comp==0)
            return (false); //Esta nodo ya existe
        if(comp<0){
            if(p.getIzq()==null){
                p.setIzq(q);
                q.setPadre(p);
                balancear(p);
                return (true);
            }else{
                return (insertaAVL(p.getIzq(),q));
            }
        }else 
        if(comp>0){
            if(p.getDer()==null){
                p.setDer(q);
                q.setPadre(p);
                //El nodo ha sido insertado, ahora se balancea.
                balancear(p);
                return (true);
            } else {
                return(insertaAVL(p.getDer(),q));
            }
        }
        return false;
    }
    
    /**
     * Metodo que permite balancear el Arbol AVL de manera que siga manteniendo sus propiedades. <br>
     * <b>post: </b> EL Arbol AVL ha sido balanceado, por lo que sigue cumpliendo con sus propiedades. <br>
     * @param r Representa el Nodo del Arbol desde el cual se quiere realizar el balance.
     */
    private void balancear(NodoAVL<T> r){ 
        // Se actualiza el factor de balance del Nodo
        setBalance(r);
        int balance = r.getBal();
        // Se evaua el balance
        if(balance==-2){
            if(getAlturaNodo(r.getIzq().getIzq())>=getAlturaNodo(r.getIzq().getDer())) {
                r = rDerecha(r);
            } else {
                r = drIzqDer(r);
            }
        } 
        else 
            if(balance==2){
                if(getAlturaNodo(r.getDer().getDer())>=getAlturaNodo(r.getDer().getIzq())) {
                    r = rIzquierda(r);} 
                else{
                    r = drDerIzq(r);}
            }
        // Se modifica el padre
        if(r.getPadre()!=null){
            balancear(r.getPadre());
        }else{
            this.setRaiz(r);
        }
    }
    
    /**
     * Metodo que permite Balancear el Arbol AVL.
     */
    public void balancearAltura(){
        balancearAltura((NodoAVL<T>)super.getRaiz());
    }
    
    /**
     * Metodo de tipo privado que permite balancear la altura del ArbolAVL.
     * @param r Representa la raiz del Arbol o subArbol.
     */
    private void balancearAltura(NodoAVL<T> r){
        if(r==null)
            return;
        this.setBalance(r);
        balancearAltura(r.getIzq());
        balancearAltura(r.getDer());        
    }
    
    /**
     * Metodo que permite modificar el factor de balance de un Nodo de acuerdo a sus nuevas condiciones. <br>
     * <b>post: </b> Se ha modificado el factor de balance del NodoAVL<T> indicado. <br>
     * @param r Representa el NodoAVL<T> el cual sera recalculado su nuevo factos de balance.
     */
    private void setBalance(NodoAVL<T> r) {
        r.setBal(getAlturaNodo(r.getDer())-getAlturaNodo(r.getIzq()));;
    }
    
    /**
     * Metodo que permite conocer la altura de un Nodo dentro del Arbol AVl para determinar su balance. <br>
     * <b>post: </b> Se retorno la altura del Nodo dentro del ArbolAVL. <br>
     * @param r Representa el NodoAVL<T> del cual se pretende conocer su altura. <br>
     * @return Un objeto de tipo int con la altura del Nodo dentro del ArbolAVL.
     */
    private int getAlturaNodo(NodoAVL<T> r) {
        if(r==null)
            return -1;        
        if(r.getIzq()==null && r.getDer()==null)
            return 0;        
        if(r.getIzq()==null)
            return 1+getAlturaNodo(r.getDer());        
        if(r.getDer()==null)
            return 1+getAlturaNodo(r.getIzq());        
        return 1+getMax(getAlturaNodo(r.getIzq()),getAlturaNodo(r.getDer()));        
    }
    
    /**
     * Metodo que permite obtener el valor maximo entre dos valores a evaluar. <br>
     * <b>post: </b> Se retorno el valor maximo de dos datos evaluados. <br>
     * @param a Representa el primer valor a evaluar. <br>
     * @param b Representa el segundo valor a evaluar. <br>
     * @return Un objeto de tipo int con el dato de Mayor valor entre los datos evaluados.
     */
   private int getMax(int a, int b) {
        if(a>=b)
         return a;
        return b;
   }
   
    /**
     * Metodo que permite efectuar una doble rotacion hacia la derecha de un Nodo. <br>
     * <b>post: </b> Se realizo una doble rotacion a la derecha. <br>
     * @param r Nodo que se encuentra desbalanceado y no cumple la propiedad. <br>
     * @return Un objeto de tipo NodoAVL<T> con las rotaciones ya realizadas. <br>
     */
    private NodoAVL<T> drIzqDer(NodoAVL<T> r) {
        r.setIzq(rIzquierda(r.getIzq()));
        return rDerecha(r);
    }
    
    /**
     * Metodo que permite efectuar una doble rotacion hacia la izquierda de un Nodo. <br>
     * <b>post: </b> Se realizo una doble rotacion a la izquierda. <br>
     * @param r Nodo que se encuentra desbalanceado y no cumple la propiedad. <br>
     * @return Un objeto de tipo NodoAVL<T> con las rotaciones ya realizadas. <br>
     */
    private NodoAVL<T> drDerIzq(NodoAVL<T> r) {
        r.setDer(rDerecha(r.getDer()));
        return rIzquierda(r);
    }
    
    /**
     * Metodo que permite efectuar una rotacion simple hacia la izquierda de un Nodo. <br>
     * <b>post: </b> Se realizo una rotacion simple a la izquierda. <br>
     * @param r Nodo que se encuentra desbalanceado y no cumple la propiedad. <br>
     * @return Un objeto de tipo NodoAVL<T> con las rotaciones ya realizadas. <br>
     */
    private NodoAVL<T> rIzquierda(NodoAVL<T> r) { 
        NodoAVL<T> v = r.getDer();
        v.setPadre(r.getPadre());
        r.setDer(v.getIzq());
        if(r.getDer()!=null){
            r.getDer().setPadre(r);
        }
        v.setIzq(r);
        r.setPadre(v);
        if(v.getPadre()!=null){
            if(v.getPadre().getDer()==r){
                v.getPadre().setDer(v);
            } else 
            if(v.getPadre().getIzq()==r){
                v.getPadre().setIzq(v);
            }
        }
        setBalance(r);
        setBalance(v);
        return (v);
    }
    
    /**
     * Metodo que permite efectuar una rotacion simple hacia la derecha de un Nodo. <br>
     * <b>post: </b> Se realizo una rotacion simple a la derecha. <br>
     * @param r Nodo que se encuentra desbalanceado y no cumple la propiedad. <br>
     * @return Un objeto de tipo NodoAVL<T> con las rotaciones ya realizadas. <br>
     */
    private NodoAVL<T> rDerecha(NodoAVL<T> r){ 
        NodoAVL<T> v = r.getIzq();
        v.setPadre(r.getPadre());

        r.setIzq(v.getDer());
        if(r.getIzq()!=null) {
            r.getIzq().setPadre(r);
        }

        v.setDer(r);
        r.setPadre(v);
        if(v.getPadre()!=null){
            if(v.getPadre().getDer()==r){
                v.getPadre().setDer(v);
            } else 
                if(v.getPadre().getIzq()==r){
                    v.getPadre().setIzq(v);
                }
        }
        setBalance(r);
        setBalance(v);
        return (v);
    }
    
    /**
     * Metodo que permite eliminar un dato del ArbolAVL; manteniendo el Arbol sus propiedades de balanceado. <br>
     * <b>post: </b> Se elimino un elemento del ArbolAVL y este ha mantenido sus propiedades.
     * @param dato Representa el Objeto de tipo T que se desea eliminar del Arbol.
     * @return Un objeto de tipo boolean con true si el dato ha sido eliminado correctamente.
     */
    @Override
    public boolean eliminar(T dato) {
        if(this.esVacio() || !this.esta(dato))
            return (false);
        return(eliminarAVL((NodoAVL<T>)super.getRaiz(),dato));
    }
    
    /**
     * Metodo que permite eliminar un dato del ArbolAVL; manteniendo el Arbol sus propiedades de balanceado. <br>
     * <b>post: </b> Se elimino un elemento del ArbolAVL y este ha mantenido sus propiedades. <br>
     * @param p Representa la raiz del ArbolAVL sobre el cual se va a realizar la eliminacion. <br>
     * @param q Rerpesenta el Objeto de tipo T que desea ser eliminado del Arbol. <br>
     * @return true o false dependiendo se si se puedo eliminar el dato del Arbol.
     */
    private boolean eliminarAVL(NodoAVL<T> p, T q) {
        int comp = ((Comparable)p.getInfo()).compareTo(q);
        if(comp==0)
            return(eliminaAVL(p));
        if(comp>0)
            return (eliminarAVL(p.getIzq(),q));
        else 
            return (eliminarAVL(p.getDer(),q));
    }
    
    /**
     * Metodo que permite eliminar un dato del ArbolAVL; manteniendo el Arbol sus propiedades de balanceado. <br>
     * <b>post: </b> Se elimino un elemento del ArbolAVL y este ha mantenido sus propiedades. <br>
     * @param q Representa el NodoAVL<T> que debe ser eliminado del Arbol. <br>
     * @return true o false dependiendo se si se puedo eliminar el dato del Arbol.
     */
    private boolean eliminaAVL(NodoAVL<T> q){
        NodoAVL<T> s;
        //Si el Nodo es una hoja
        if(q.getIzq()==null || q.getDer()==null){        
            //Si el Nodo es la raiz
            if(q.getPadre()==null){
                if(q.getIzq()!=null){
                    q.getIzq().setPadre(null);
                    this.setRaiz(q.getIzq());
                }else{
                    if(q.getDer()!=null){
                        q.getDer().setPadre(null);
                        this.setRaiz(q.getDer());
                    }else
                        setRaiz(null);
                }
                return (true);
            }
            s = q;
        }
        else{
            // Se recupera el hijo sucesor al Nodo
            s = getSucesor(q);
            q.setInfo(s.getInfo());
        }
        NodoAVL<T> p;
        if(s.getIzq()!=null){
            p = s.getIzq();
        } 
        else{
            p = s.getDer();
        }
        if(p!=null){
            p.setPadre(s.getPadre());
        }
        if(s.getPadre()==null){
            this.setRaiz(p);
        }else{
            if(s==s.getPadre().getIzq()){
                s.getPadre().setIzq(p);
            }
            else{
                s.getPadre().setDer(p);
            }
            // Se realiza el balanceo del Arbol
            balancear(s.getPadre());        
        }
        s = null;
        return (true);
    }
    
    /**
     * Metodo que permite encontrar el Nodo sucesor al Nodo que se pretende eliminar. <br>
     * <b>post: </b> Se retorno el sucesor al NodoAVL<T> que se desea eliminar de Arbol. <br>
     * @param q Representa el NodoAVL<T> sobre el cual se desea evaluar su sucesor. <b>
     * @return Un objeto de tipo NodoAVL<T> que representa el sucesor al Nodo que se pretende elimianr.
     */
    private NodoAVL<T> getSucesor(NodoAVL<T> q) {
        if(q.getDer()!=null){
            NodoAVL<T> r = q.getDer();
            while(r.getIzq()!=null){
                r = r.getIzq();
            }
            return r;
        } 
        else{
            NodoAVL<T> p = q.getPadre();
            while(p!=null && q==p.getDer()){
                q = p;
                p = q.getPadre();
            }
            return p;
        }
    }
    
    /**
     * Metodo que permite evaluar la existencia de un objeto dentro del Arbol AVL. <br>
     * <b>post: </b> Se retorno true si el elemento se encuentra en el Arbol.<br>
     * @param x Representa el elemento el cual se desea evaluar su existencia en el Arbol. <br>
     * @return Un boolean , true si el dato esta o false en caso contrario.
     */
    @Override
    public boolean esta(T x){
        return(super.estaABB(x));
    }
    
    /**
     * Metodo que retorna un iterador con las hojas del Arbol AVL. <br>
     * <b>post: </b> Se retorno un iterador con las hojas del Arbol.<br>
     * @return un iterador con las hojas delArbol.
     */
    @Override
    public Iterator<T> getHojas(){
        return (super.getHojas());
    }
    
    /**
     * Metodo que permite determinar el numero de Nodo hojas dentro del Arbol. <br>
     * <b>post: </b> Se retorno el numero de hojas del Arbol. <br>
     * @return El numero de hojas existentes en el Arbol.
     */
    @Override
    public int contarHojas(){
        return (super.contarHojas());
    }
    
    /**
     *  Metodo que retorna un iterador con el recorrido preOrden del Arbol. <br>
     * <b>post: </b> Se retorno un iterador en preOrden para el arbol.<br>
     * @return un iterador en preorden (padre->hijoIzq->hijoDer) para el Arbol AVL.
     */
    @Override
     public Iterator<T> preOrden(){
         return (super.preOrden());
    }
    
    /**
     * Metodo que retorna un iterador con el recorrido in Orden del Arbol. <br>
     * <b>post: </b> Se retorno un iterador inOrden para el arbol.<br>
     * @return un iterador en inOrden (hijoIzq->padre->hijoDer) para el Arbol AVL. <br>
     */
    @Override
    public Iterator<T> inOrden(){
        return (super.inOrden());
    }

    /**
     * Metodo que retorna un iterador con el recorrido postOrden del Arbol. <br>
     * <b>post: </b> Se retorno un iterador preOrden para el arbol.<br>
     * @return un iterador en postOrden (hijoIzq->hijoDer->padre) para el Arbol AVL. <br>
     */
    @Override
    public Iterator<T> postOrden(){
        return (super.postOrden());
    }
    
    /**
     * Metodo que permite retornar un iterador con el recorrido por niveles del Arbol. <br>
     * <b>post: </b> Se retorno el recorrido por niveles del Arbol AVL.<br>
     * @return un un iterador con el recorrido por niveles del Arbol AVL.
     */
    @Override
    public Iterator<T> impNiveles(){
        return (super.impNiveles());
    }
    
    /**
     * Metodo que permite obtener el peso del Arbol AVL. <br>
     * <b>post: </b> Se retorno el numero de elementos en el Arbol AVL.<br>
     * @return Un entero con la cantidad de elementos del Arbol AVL.
     */
    @Override
    public int getPeso(){
        return(super.getPeso());
    }
    
    /**
     * Metodo que permite saber si el Arbol AVL se encuentra vacio. <br>
     * <b>post: </b> Se retorno true si el arbol no contiene elementos.<br>
     * @return true si no hay datos en el Arbol AVL.
     */
    @Override
    public boolean esVacio(){
        return (super.esVacio());
    }
    
    /**
     * Metodo que permite obtener la altura del Arbol AVL. <br>
     * <b>post: </b> Se retorno la altura del Arbol AVL.<br>
     * @return Un entero con la altura del Arbol AVL.
     */
    @Override
    public int getAltura(){
        return(super.getAltura());
    }
    
    /**
     * Metodo que permite conocer por consola la informacion del Arbol Binario.
     */
    @Override
    public void imprime(){
        System.out.println(" ----- Arbol AVL ----- ");
        imprimeAVL((NodoAVL<T>) super.getRaiz());
    }
    
    /**
     * Metodo de tipo privado que permite mostrar por consola la informacion del Arbol AVL. <br>
     * @param n Representa la raiz del ArbolAVL o de alguno de sus subarboles.
     */
    public void imprimeAVL(NodoAVL<T> n) {
        int l = 0;
        int r = 0;
        int p = 0;
        if(n==null)
            return;
        if(n.getIzq()!=null) {
         l = Integer.parseInt(n.getIzq().getInfo().toString());
        }
        if(n.getDer()!=null) {
         r = Integer.parseInt(n.getDer().getInfo().toString());
        }
        if(n.getPadre()!=null) {
         p = Integer.parseInt(n.getPadre().getInfo().toString());
        }        
        System.out.println("NodoIzq: "+l+"\t Info: "+n.getInfo()+"\t NodoDer: "+r+"\t Padre: "+p+"\t Balance: "+n.getBal());
        if(n.getIzq()!=null) {
         imprimeAVL(n.getIzq());
        }
        if(n.getDer()!=null) {
         imprimeAVL(n.getDer());
        }
    }
    
    
} //Fin de la clase ArbolAVL