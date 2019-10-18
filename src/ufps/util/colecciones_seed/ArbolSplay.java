/**
 * ---------------------------------------------------------------------
 * $Id: ArbolSplay.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;

import java.util.Iterator;

/**
 * Implementacion de Clase para el manejo de un Arbol Splay.
 * @param <T> Tipo de dato a almacenar en el Arbol Splay
 * @author Uriel Garcia
 * @version 1.0
 */
public class ArbolSplay<T> extends ArbolBinarioBusqueda <T> {
    

    ////////////////////////////////////////////////////////////
    // ArbolSplay - Implementacion de Metodos ///////////////////
    ////////////////////////////////////////////////////////////

    /**
     * Crea un Arbol Splay vacio con sus datos Nulos. <br>
     * <b>post: </b> Se creo un Arbol Splay vacio. <br>
     */
     public ArbolSplay()    {
         super();
     }

     /**
      * Crea un Arbol Splay con una raiz predefinida. <br>
      * <b>post: </b> Se creo un Arbol con raiz predeterminada. <br>
      * @param raiz Un objeto de tipo T , almacena la direccion de memoria de un nodo de un Arbol. <br>
      */
      public ArbolSplay(T raiz) {
         super(raiz);
      }
      
     /**
      * Metodo que permite conocer el objeto almacenado en la raiz del Arbol Splay. <br>
      * <b>post: </b> Se obtuvo la raiz del Arbol Splay.<br>
      * @return la raiz del Arbol Binario.
      */
     @Override
     public T getObjRaiz() {
         return (super.getObjRaiz());
     }

     /**
      * Metodo que permite insertar un dato en el Arbol Splay de manera que este se ubique en la raiz. <br>
      * <b>post: </b> Se inserto un nuevo dato al Arbol Splay. <br>
      * @param dato un elemento tipo T que se desea almacenar en el arbol. <br>
      * @return true si el elemento fue insertado exitosamente o false en caso contrario
      */
     @Override
     public boolean insertar(T dato){
         //Si el arbol se encuentra vacio
         if (esVacio()) {
             super.setRaiz(new NodoBin<T>(dato));
             return (true);
         }        
         super.setRaiz(buscarAS(dato));
         int cmp = ((Comparable)dato).compareTo(super.getRaiz().getInfo());        
         // Si el dato es menor a la raiz
         if (cmp < 0) {
             NodoBin<T> n = new NodoBin<T>(dato);
             n.setIzq(super.getRaiz().getIzq());
             n.setDer(super.getRaiz());
             super.getRaiz().setIzq(null);
             super.setRaiz(n);
             return (true);
         }
         // Si el dato es mayor a la raiz
         else if (cmp > 0) {
             NodoBin<T> n = new NodoBin<T>(dato);
             n.setDer(super.getRaiz().getDer());
             n.setIzq(super.getRaiz());
             super.getRaiz().setDer(null);
             super.setRaiz(n);
             return (true);
         }
         return (false);
     }

    /**
     * Metodo que permite trasladar un nodo x, que es el nodo al que se accede, a la raíz. <br>
     * <b>post: </b> Se realizo la reestructuracion del Arbol Splay deacuerdo al dato accedido recientemente. <br>
     * @param r Representa la raiz del Arbol desde la cual arranca la busqueda del dato. <br>
     * @param dato Un elemento tipo T que se desea ser accedido y que sera ascendio a la raiz. <br>
     * @return Un objeto de tipo NodoBin<T> que representa la nueva raiz del Arbol a cual debera ser actualizada.
     */
    private NodoBin<T> biselar(NodoBin<T> r, T dato) {
        if (r == null) 
            return (null);
        int cmp1 = ((Comparable)dato).compareTo(r.getInfo());
        //Si el dato es menor a la raiz
        if (cmp1<0){
            if (r.getIzq() == null) {
                return (r);
            }
            int cmp2 = ((Comparable)dato).compareTo(r.getIzq().getInfo());
            //Si es dato es menor que el hijo
            if (cmp2<0){
                r.getIzq().setIzq(biselar(r.getIzq().getIzq(),dato));
                r = rDerecha(r);
            }
            //Si el dato es mayor que el hijo
            else if (cmp2>0) {
                r.getIzq().setDer(biselar(r.getIzq().getDer(), dato));
                if (r.getIzq().getDer()!= null)
                    r.setIzq(rIzquierda(r.getIzq()));
            }            
            if (r.getIzq()== null) 
                return (r);
            else
                return (rDerecha(r));
        }
        //El dato es menor a la raiz
        else if (cmp1>0) { 
            // dato not in tree, so we're done
            if (r.getDer() == null) {
                return (r);
            }
            int cmp2 = ((Comparable)dato).compareTo(r.getDer().getInfo());
            //Si el dato es menor que el hijo
            if (cmp2<0){
                r.getDer().setIzq(biselar(r.getDer().getIzq(), dato));
                if (r.getDer().getIzq() != null)
                    r.setDer(rDerecha(r.getDer()));
            }
            //Si el dato es mayor que el hijo
            else if (cmp2>0) {
                r.getDer().setDer(biselar(r.getDer().getDer(), dato));
                r = rIzquierda(r);
            }
            if (r.getDer()==null) 
                return (r);
            else  
                return (rIzquierda(r));
        }
        else return (r);
    }
    
    /**
     * Metodo que permite efectuar una rotacion simple hacia la derecha de un Nodo. <br>
     * <b>post: </b> Se realizo una rotacion simple a la derecha. <br>
     * @param r Nodo que se encuentra desbalanceado y no cumple la propiedad. <br>
     * @return Un objeto de tipo NodoBin<T> con las rotaciones ya realizadas. <br>
     */
    private NodoBin<T> rDerecha(NodoBin<T> r) {
        NodoBin<T> x = r.getIzq();
        r.setIzq(x.getDer());
        x.setDer(r);
        return x;
    }
    
    /**
     * Metodo que permite efectuar una rotacion simple hacia la izquierda de un Nodo. <br>
     * <b>post: </b> Se realizo una rotacion simple a la izquierda. <br>
     * @param r Nodo que se encuentra desbalanceado y no cumple la propiedad. <br>
     * @return Un objeto de tipo NodoBin<T> con las rotaciones ya realizadas. <br>
     */
    private NodoBin<T> rIzquierda(NodoBin<T> r) {
        NodoBin<T> x = r.getDer();
        r.setDer(x.getIzq());
        x.setIzq(r);
        return x;
    }
    
    /**
     * Metodo que permite eliminar un dato del Arbol Splay; El dato mas proximo al eliminado se pone en la raiz. <br> 
     * <b>post: </b> Se ha eliminado el dato accedido y el Arbol Splay ha sido biselado de manera correcta. <br>
     * @param dato Un elemento de tipo T que es ascendido a la raiz y posteriormente eliminado. <br>
     * @return Un objeto de tipo boolean con true si el dato ha sido eliminado correctamente.
     */
     @Override
    public boolean eliminar(T dato) {
        if (esVacio())
            return (false);      
        super.setRaiz(buscarAS(dato));
        int cmp = ((Comparable)dato).compareTo(super.getRaiz().getInfo());        
        //Si se encontro el elemento
        if (cmp==0){
            if (super.getRaiz().getIzq()==null){
                super.setRaiz(super.getRaiz().getDer());
            } 
            else {
                    NodoBin<T> x = super.getRaiz().getDer();
                    super.setRaiz(super.getRaiz().getIzq());
                    super.setRaiz(biselar(super.getRaiz(), dato));
                    super.getRaiz().setDer(x);
            }
            return (true);
        }
        //El dato no fue encontrado
        return (false);
    }
    
    /**
     * Metodo que permite hallar un elemento dentro del Arbol Splay; Una vez ubicado el dato se
     * realiza el proceso de biselacion sobre el Arbol de manera que el dato consultado queda en la raiz.<br>
     * <b>post: </b> Se retorno un Objeto de tipo NodoBin<T> que representa la raiz de Arbol Splay una vez biscelado.<br>
     * @param dato Representa el elemento el cual se desea evaluar su existencia en el Arbol. <br>
     * @return un Objeto de tipo NodoBin<T> que representa la raiz de Arbol Splay una vez biselado.
     */
    private NodoBin<T> buscarAS(T dato){
        if(esVacio())
            return (null);
        return (biselar(super.getRaiz(), dato));
    }
    
    /**
     * Metodo que permite evaluar la existencia de un objeto dentro del Arbol Splay; 
     * El elemento es ubicado en la raiz de Arbol y se realiza proceso de biselacion. <br>
     * <b>post: </b> Se retorno true si el elemento se encuentra en el Arbol.<br>
     * @param dato Representa el elemento el cual se desea evaluar su existencia en el Arbol. <br>
     * @return Un boolean , true si el dato esta o false en caso contrario.
     */
    public boolean estaAS(T dato){
        if(esVacio())
            return (false);
        super.setRaiz(buscarAS(dato)); 
        return (super.getRaiz().getInfo().equals(dato));
    }
    
    /**
     * Metodo que retorna un iterador con las hojas del Arbol Splay. <br>
     * <b>post: </b> Se retorno un iterador con las hojas del Arbol.<br>
     * @return Un iterador con las hojas delArbol.
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
     * @return un iterador en preorden (padre->hijoIzq->hijoDer) para el Arbol Splay.
     */
     @Override
     public Iterator<T> preOrden(){
         return (super.preOrden());
    }
    
    /**
     * Metodo que retorna un iterador con el recorrido in Orden del Arbol. <br>
     * <b>post: </b> Se retorno un iterador inOrden para el arbol.<br>
     * @return un iterador en inOrden (hijoIzq->padre->hijoDer) para el Arbol Splay. <br>
     */
     @Override
    public Iterator<T> inOrden(){
        return (super.inOrden());
    }

    /**
     * Metodo que retorna un iterador con el recorrido postOrden del Arbol. <br>
     * <b>post: </b> Se retorno un iterador preOrden para el arbol.<br>
     * @return un iterador en postOrden (hijoIzq->hijoDer->padre) para el Arbol Splay. <br>
     */
     @Override
    public Iterator<T> postOrden(){
        return (super.postOrden());
    }
    
    /**
     * Metodo que permite retornar un iterador con el recorrido por niveles del Arbol. <br>
     * <b>post: </b> Se retorno el recorrido por niveles del Arbol Splay.<br>
     * @return un un iterador con el recorrido por niveles del Arbol Splay.
     */
     @Override
    public Iterator<T> impNiveles(){
        return (super.impNiveles());
    }
    
    /**
     * Metodo que permite obtener el peso del Arbol Splay. <br>
     * <b>post: </b> Se retorno el numero de elementos en el Arbol Splay.<br>
     * @return Un entero con la cantidad de elementos del Arbol Splay.
     */
     @Override
    public int getPeso(){
        return(super.getPeso());
    }
    
    /**
     * Metodo que permite saber si el Arbol Splay se encuentra vacio. <br>
     * <b>post: </b> Se retorno true si el arbol no contiene elementos.<br>
     * @return true si no hay datos en el Arbol Splay.
     */
    @Override
    public boolean esVacio(){
        return(super.esVacio());
    }
    
    /**
     * Metodo que permite obtener la altura del Arbol Splay. <br>
     * <b>post: </b> Se retorno la altura del Arbol Splay.<br>
     * @return Un entero con la altura del Arbol Splay.
     */
     @Override
    public int getAltura(){
        return(super.getAltura());
    }
    
     /**
     * Metodo que permite clonar la informacion de un Arbol Splay en un nuevo objeto ArbolSplay. <br>
     * @return Un objeto de tipo ArbolSplay con la informacion del Arbol duplicada.
     */
    @Override
    public ArbolSplay<T> clonar(){
        ArbolSplay<T> t=new ArbolSplay<T>();
        t.setRaiz(clonarAS(this.getRaiz()));
        return(t);
    }

    /**
     * Metodo que permite clonar la informacion de un Arbol Splay en un nuevo objeto ArbolSplay. <br>
     * @param r Representa a raiz del Arbol Splay. <br>
     * @return Un objeto de tipo ArbolSplay con la informacion del Arbol duplicada.
     */
    private NodoBin<T> clonarAS(NodoBin<T> r){				
        if(r==null)
            return r;
        else
        {
            NodoBin<T> aux=new NodoBin<T>(r.getInfo(), clonarAS(r.getIzq()), clonarAS(r.getDer()));
            return aux;
        }
    }
    
    /**
     * Metodo que permite conocer por consola la informacion del Arbol Binario.
     */
     @Override
    public void imprime(){
        System.out.println(" ----- Arbol Splay ----- ");
        imprimeAS(super.getRaiz());
    }
    
    /**
     * Metodo de tipo privado que permite mostrar por consola la informacion del Arbol Splay. <br>
     * @param n Representa la raiz del ArbolSplay o de alguno de sus subarboles.
     */
    public void imprimeAS(NodoBin<T> n) {
        int l = 0;
        int r = 0;
        if(n==null)
            return;
        if(n.getIzq()!=null) {
         l = Integer.parseInt(n.getIzq().getInfo().toString());
        }
        if(n.getDer()!=null) {
         r = Integer.parseInt(n.getDer().getInfo().toString());
        }       
        System.out.println("NodoIzq: "+l+"\t Info: "+n.getInfo()+"\t NodoDer: "+r);
        if(n.getIzq()!=null) {
         imprimeAS(n.getIzq());
        }
        if(n.getDer()!=null) {
         imprimeAS(n.getDer());
        }
    }
   
}// Fin de la Clase ArbolSplay.