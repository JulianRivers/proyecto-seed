/**
 * ---------------------------------------------------------------------
 * $Id: ArbolEnerio.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;

import java.util.Iterator;

/**
 * Implementacion de Clase para el manejo de un Arbol Eneario.
 * @param <T> Tipo de datos a almacenar en el Arbol Eneario.
 * @author Uriel Garcia
 * @version 1.0
 */
public class ArbolEneario<T>{    
    
    ////////////////////////////////////////////////////////////
    // ArbolEneario - Atributos ////////////////////////////////
    ////////////////////////////////////////////////////////////
 
    /**
     * Nodo raiz del Arbol Eneario
     */
    private NodoEneario<T> raiz;
    
    
    
    ////////////////////////////////////////////////////////////
    // ArbolEneario - Implementacion de Metodos ////////////////
    ////////////////////////////////////////////////////////////

    /**
     * Crea un Arbol Eneario vacio. <br>
     * <b>post: </b> Se creo un Arbol Eneario vacio.<br>
     */
    public ArbolEneario(){
        this.raiz=null;
    }
    
    /**
     * Crea un Arbol Eneario con una raiz predefinida. <br>
     * <b>post: </b> Se creo un nuevo Arbol con su raiz definida.<br>
     * @param raiz  Un objeto de tipo T que representa del dato en la raiz del Arbol.
     */
    public ArbolEneario(NodoEneario<T> raiz){
        this.raiz=raiz;
    }
    
    /**
     * Metodo que permite conocer el objeto en la raiz del Arbol Eneario. <br>
     * <b>post: </b> Se obtuvo la raiz del Arbol Eneario.<br>
     * @return Un objeto de tipo NodoEneario<T> que es la raiz del Arbol Eneario.
     */
    public T getObjRaiz() {
        return raiz.getInfo();
    }

    /**
     * Metodo que permite conocer la raiz del Arbol Eneario. <br>
     * <b>post: </b> Se obtuvo la raiz del Arbol Eneario.<br>
     * @return Un objeto de tipo NodoEneario<T> que es la raiz del Arbol Eneario.
     */
    public NodoEneario<T> getRaiz() {
        return raiz;
    }

    /**
     * Metodo que permite modificar la raiz del Arbol Eneario. <br>
     * <b>post: </b> Se modifico la raiz del Arbol Eneario. <br>
     * @param raiz Objeto de tipo NodoEneario<T> que representa la nueva raiz del Arbol.
     */
    public void setRaiz(NodoEneario<T> raiz) {
        this.raiz = raiz;
    }
    
    /**
     * Metodo que permite insertar un nuevo NodoEneario como hijo de un NodoEneario. <br>
     * <b>post: </b> Se inserto un nuevo dato como hijo del Nodo indicado. <br>
     * @param padre Es de tipo T y representa el dato del padre del nuevo NodoEneario. <br>
     * @param dato Es de tipo T y representa el nuevo dato a insertar en el Arbol Eneario. <br>
     * @return Un valor de tipo boolean que representa el exito de la operacion indicada o la razon del error.
     */
    public boolean insertarHijo(T padre, T dato){
       NodoEneario<T> nuevo = new NodoEneario(dato); 
       //El arbol se encuentra vacio
       if(this.esVacio()){
           this.setRaiz(nuevo);
           return (true); 
       }
       NodoEneario p = this.buscar(padre);
       NodoEneario n = this.buscar(dato);
       if(n!=null || p==null)
           return (false); //Ya existe dato o padre no existe
       if(this.esHoja(p)){
           p.setHijo(nuevo);
           return (true); 
       }
       NodoEneario<T> q = p.getHijo();
       p.setHijo(nuevo);
       nuevo.setHermano(q);
       return (true);
    }
    
    /**
     * Metodo que permite insertar un nuevo NodoEneario como hermano de un NodoEneario. <br>
     * <b>post: </b> Se inserto un nuevo dato como hermano del Nodo indicado. <br>
     * @param hermano Es de tipo T y representa el dato del hermano del nuevo NodoEneario. <br>
     * @param dato Es de tipo T y representa el nuevo dato a insertar en el Arbol Eneario. <br>
     * @return Un valor de tipo boolean que representa el exito de la operacion indicada o la razon del error.
     */
    public boolean insertarHermano(T hermano, T dato){
       NodoEneario<T> nuevo = new NodoEneario(dato); 
       //El arbol se encuentra vacio
       if(this.esVacio()){
           this.raiz = nuevo;
           return (true); 
       }
       NodoEneario h = this.buscar(hermano);
       NodoEneario n = this.buscar(dato);
       //Si es la raiz
       if(this.raiz==h || h==null || n!=null)
           return (false); //Hermano de la raiz, hermano Null o ya existe
       NodoEneario<T> sigH = h.getHermano();
       h.setHermano(nuevo);
       nuevo.setHermano(sigH);
       return (true);
    }
    
    /**
     * Metodo que permite eliminar un dato del Arbol Eneario. <br>
     * <b>post: </b> Se elimino un dato del Arbol Eneario. <br>
     * @param dato Representa la informacion del dato que se desea eliminar del Arbol. <br>
     * @return Un objeto de tipo boolean con true si se elimino el dato y false en caso contrario.
     */
    public boolean eliminar(T dato){
        if(!this.esta(dato))
            return (false);
        return (elimina(dato));
    }
    
    /**
     * Metodo de tipo privado que permite eliminar un dato del Arbol Eneario. <br>
     * <b>post: </b> Se elimino un dato del Arbol Eneario. <br>
     * @param dato Representa la informacion del dato que se desea eliminar del Arbol. <br>
     * @return Un objeto de tipo boolean con true si se elimino el dato y false en caso contrario.
     */
    private boolean elimina(T dato){
        NodoEneario<T> n,h,p,s;
        n = this.getPadre(dato); 
        //Es un hijo (Primer hijo a la izquierda)
        if(n!=null){
            h = n.getHijo();
            //Si tiene hijos, subo al primer hijo
            if(h.getHijo()!=null){
                s = h.getHijo();
                n.setHijo(s);
                p = s;
                while(s!=null){
                    p = s;
                    s = s.getHermano();
                }
                p.setHermano(h.getHermano()); 
            }else{
                n.setHijo(h.getHermano());
            }           
            return (true);
        }
        //Si es un hermano (Es un hijo pero no el primero)
        n = this.getHermano(dato);
        if(n!=null){
            h = n.getHermano();
            //Si tiene hijos, subo al primer hijo
            if(h.getHijo()!=null){
                s = h.getHijo();
                n.setHermano(s);
                p = s;
                while(s!=null){
                    p = s;
                    s = s.getHermano();
                }
                p.setHermano(h.getHermano());
            }else{
                n.setHermano(n.getHermano().getHermano());
            }
            return (true);
        }
        //Si es la raiz del Arbol
        return (eliminaR(dato));
    }
    
    /**
     * Metodo de tipo privado que permite eliminar la raiz del Arbol Eneario, estructurando sus datos. <br>
     * <b>post: </b> Se elimino un dato que es la raiz del Arbol Eneario. <br>
     * @param dato Representa la informacion del dato que se desea eliminar del Arbol. <br>
     * @return Un objeto de tipo boolean con true si se elimino el dato y false en caso contrario.
     */
    private boolean eliminaR(T dato){
        if(this.raiz.getInfo()!=dato)
            return (false);
        NodoEneario<T> h,p,s;
        this.setRaiz(this.raiz.getHijo());
        //Si tenia hijos
        if(this.raiz!=null){
            h = this.raiz.getHijo();
            s = this.raiz.getHermano();
            //Si no tiene hijos
            if(h==null){
                this.raiz.setHijo(s);
            }else{
                p = h;
                while(h!=null){
                    p = h;
                    h = h.getHermano();
                }
                p.setHermano(s);
            }
        }
        return (true);
    }
    
    /**
     * Metodo que permite conocer el NodoEneario padre de un dato dentro del Arbol. <br>
     * <b>post: </b> Se retorno el padre del elemento indicado. <br>
     * @param info Representa el dato del cual se quiere conocer el NodoEneario padre. <br>
     * @return Un objeto de tipo NodoEneario<T> que representa el padre del dato consultado.
     */
    private NodoEneario<T> getPadre(T info){
        return(gPadre(this.raiz,null,info));
    }
    
    /**
     * Metodo de tipo privado que permite conocer el NodoEneario padre de un dato dentro del Arbol. <br>
     * <b>post: </b> Se retorno el padre del elemento indicado. <br>
     * @param r Representa la raiz del Arbol Eneario o un subarbol del mismo. <br>
     * @param t Representa el padre del NodoEneario evaluado. Si es la raiz es NULL. <br>
     * @param dato Representa el dato del cual se quiere conocer el NodoEneario padre. <br>
     * @return Un objeto de tipo NodoEneario<T> que representa el padre del dato consultado.
     */
    private NodoEneario<T> gPadre(NodoEneario<T> r, NodoEneario<T> t, T dato){
        NodoEneario<T> q, s;
        if(r==null)
            return (null);
        if(r.getInfo().equals(dato)){
            return (t);
        }
        q = r.getHijo();
        while(q != null){
            s = gPadre(q,r,dato);
            if(s != null){
                return (s);
            } 
            r = null;
            q = q.getHermano();
        }
        return (null);
    }
    
    /**
     * Metodo que permite conocer el NodoEneario hermano a la izquierda de un dato dentro del Arbol. <br>
     * <b>post: </b> Se retorno el hermano del elemento indicado. <br>
     * @param info Representa el dato del cual se quiere conocer el NodoEneario hermano. <br>
     * @return Un objeto de tipo NodoEneario<T> que representa el hermano del dato consultado.
     */
    private NodoEneario<T> getHermano(T info){
        return(gHermano(this.raiz,null,info));
    }
    
    /**
     * Metodo de tipo privado que permite conocer el NodoEneario hermano de un dato dentro del Arbol. <br>
     * <b>post: </b> Se retorno el hermano del elemento indicado. <br>
     * @param r Representa la raiz del Arbol Eneario o un subarbol del mismo. <br>
     * @param h Representa el hermano del NodoEneario evaluado. Si es la raiz es NULL. <br>
     * @param dato Representa el dato del cual se quiere conocer el NodoEneario hermano. <br>
     * @return Un objeto de tipo NodoEneario<T> que representa el hermano del dato consultado.
     */
    private NodoEneario<T> gHermano(NodoEneario<T> r, NodoEneario<T> h, T dato){
        NodoEneario<T> p=null, q, s;
        if(r==null)
            return (null);
        if(r.getInfo().equals(dato)){
            return (h);
        }
        q = r.getHijo();
        while(q != null){
            s = gHermano(q,p,dato);
            if(s != null){
                return (s);
            }
            p = q;
            q = q.getHermano();
        }
        return (null);
    }
    
    /**
     * Metodo que permite evaluar la existencia un dato dentro del Arbol Eneario. <br>
     * <b>post: </b> Se evaluo la existencia de un dato dentro del Arbol. <br>
     * @param dato Representa el dato que se quiere localizar dentro del Arbol Eneario. <br>
     * @return Un objeto de tipo boolean que contiene un true si ubico el dato y false en caso contrario.
     */
    public boolean esta(T dato){
        if(this.esVacio())
            return (false);
        boolean rta = (this.esta(this.raiz,dato));
        return rta;
    }
    
    /**
     * Metodo que permite evaluar la existencia un dato dentro del Arbol Eneario. <br>
     * <b>post: </b> Se evaluo la existencia de un dato dentro del Arbol. <br>
     * @param r Representa la raiz del Arbol Eneario en el que se buscara el dato. <br>
     * @param dato Representa el dato que se quiere localizar dentro del Arbol Eneario. <br>
     * @return Un objeto de tipo boolean que contiene un true si ubico el dato y false en caso contrario.
     */
    private boolean esta(NodoEneario<T> r, T dato){        
       NodoEneario<T> q;
       boolean s;
       if(r==null)
           return (false);
       if(r.getInfo().equals(dato))
           return (true);
       q = r.getHijo();
       while(q != null){
           s = esta(q, dato);
           if(s)
               return (true);
           q = q.getHermano();
       }
       return (false);
    }
    
    /**
     * Metodo que permite buscar un dato dentro del Arbol Eneario y retornar el Nodo que lo contiene. <br>
     * <b>post: </b> Se retorno el NodoEneario<T> que representa la ubicacion del dato en el Arbol. <br>
     * @param dato Representa el dato que se quiere localizar dentro del Arbol Eneario. <br>
     * @return Un objeto de tipo NodoEneario<T> que representa la ubicacion del dato dentro del Arbol.
     */
    private NodoEneario<T> buscar(T dato){
        if(this.esVacio())
            return (null);
        return (this.buscar(this.raiz,dato));
    }
    
    /**
     * Metodo que permite buscar un dato dentro del Arbol Eneario y retornar el Nodo que lo contiene. <br>
     * <b>post: </b> Se retorno el NodoEneario<T> que representa la ubicacion del dato en el Arbol. <br>
     * @param r Representa la raiz del Arbol Eneario en el que se buscara el dato. <br>
     * @param dato Representa el dato que se quiere localizar dentro del Arbol Eneario. <br>
     * @return Un objeto de tipo NodoEneario<T> que representa la ubicacion del dato dentro del Arbol.
     */
    private NodoEneario<T> buscar(NodoEneario<T> r, T dato){
       NodoEneario<T> q, s;
       if(r==null)
           return (r);
       if(r.getInfo().equals(dato))
           return (r);
       q = r.getHijo();
       while(q != null){
           s = buscar(q, dato);
           if(s != null){
               return (s);
           }
           q = q.getHermano();
       }
       return (null);
    }
    
    /**
     * Metodo que permite conocer los hijos de un dato insertado dentro del Arbol y retornarlos en un Iterator. <br>
     * <b>post: </b> Se retorno un Iterador con los hijos del dato evaluado. <br>
     * @param padre Representa el dato del cual se quieren conocer los hijos insertados en dicho subarbol. <br>
     * @return Un objeto de tipo Iterator con los hijos del dato evaluado.
     */
    public Iterator<T> getHijos(T padre){
       ListaCD<T> l=new ListaCD<T>();
       NodoEneario p = this.buscar(this.raiz, padre);
       if(p==null)
           return (l.iterator()); //Este Nodo no existe
       NodoEneario q = p.getHijo();
       while(q!=null){
           l.insertarAlFinal((T)q.getInfo());
           q = q.getHermano();
       }
       return(l.iterator());
   }
   
    
    /**
     * Metodo que retorna un iterador con las hojas del Arbol Eneario. <br>
     * <b>post: </b> Se retorno un iterador con las hojas del Arbol Eneario. <br>
     * @return Un objeto Iterador con las hojas del Arbol Eneario.
     */
    public Iterator<T> getHojas(){
        ListaCD<T> l=new ListaCD();
        getHojas(this.raiz, l);
        return (l.iterator());
    }

    /**
     * Metodo de tipo privado que retorna un iterador con las hojas del Arbol Eneario. <br>
     * <b>post: </b> Se retorno un iterador con las hojas del Arbol Eneario.<br>
     * @param r representa la raiz del arbol, o raiz de subarbol. <br>
     * @param l Lista para el almacenamiento de los datos del arbol. <br>
     */
    private void getHojas(NodoEneario<T> r, ListaCD<T> l){
        NodoEneario<T> q;
        if(r==null)
            return ;
        q = r.getHijo();
        if(q==null){
            l.insertarAlFinal(r.getInfo());
            return ;
        }
        while(q != null){
        getHojas(q,l);
        q = q.getHermano();
        }
    }
    
    /**
     * Metodo que permite evaluar si un NodoEneario es una Hoja del Arbol Eneario. <br>
     * <b>post: </b> Se evaluo si el NodoEneario<T> es o no una hoja del Arbol. <br>
     * @param r Representa el Nodo a ser evaluado si es o no una Hoja del Arbol. <br>
     * @return Un objeto de tipo boolean que es true si el Nodo es una Hoja y false en caso contrario.
     */
    private boolean esHoja(NodoEneario<T> r){
        return (r.getHijo()==null);
    }
    
    /**
     * Metodo que permite determinar el numero de Nodo hojas dentro del Arbol Eneario. <br>
     * <b>post: </b> Se retorno el numero de hojas del Arbol. <br>
     * @return El numero de hojas existentes en el Arbol Eneario.
     */
    public int contarHojas(){
        return (contarHojas(this.raiz));
    }
    
    /**
     * Metodo de tipo privado que permite determinar el numero de Nodo hojas dentro del Arbol Eneario. <br>
     * <b>post: </b> Se retorno el numero de hojas del Arbol. <br>
     * @param r representa la raiz del arbol, o raiz de subarbol. <br>
     * @return El numero de hojas existentes en el Arbol Eneario.
     */
    private int contarHojas(NodoEneario<T> r){
        NodoEneario q;
        if(r==null)
            return (0);
        q = r.getHijo();
        if(q==null){
            return (1);
        }
        int acum = 0;
        while(q != null){
            acum += contarHojas(q);
            q = q.getHermano();
        }
        return (acum);
    }
    
    /**
     *  Metodo que retorna un iterador con el recorrido preOrden del Arbol Eneario. <br>
     * <b>post: </b> Se retorno un iterador en preOrden para el Arbol.<br>
     * @return Un Iterador en preorden (padre->Primer Hijo en preorden -> Hermano en preorden) para el arbol Eneario.
     */
     public Iterator<T> preOrden(){
         ListaCD<T> l=new ListaCD<T>();
         preOrden(this.getRaiz(),l);
         return (l.iterator());
    }

    /**
    * Metodo que tipo privado que retorna un Iterador con el recorrido preOrden del Arbol Eneario. <br>
    * <b>post: </b> Se retorno un iterador en preOrden para el Arbol.<br>
    * @param r representa la raiz del Arbol, o raiz de subarbol. <br>
    * @param l Lista para el almacenamiento de los datos del Arbol. <br>
    */
    private void  preOrden(NodoEneario<T> r, ListaCD<T> l){
        NodoEneario<T> q;
        if(r!=null){
            l.insertarAlFinal(r.getInfo());
            q = r.getHijo();
            if(q != null){
                preOrden(q,l);
                q = q.getHermano();
                while(q != null){
                    preOrden(q,l);
                    q = q.getHermano();
                }
            }
        }
    }

   /**
     * Metodo que retorna un iterador con el recorrido in Orden del Arbol Eneario. <br>
     * <b>post: </b> Se retorno un iterador inOrden para el Arbol.<br>
     * @return Un Iterador en inOrden (Primer hijo en InOrden->padre->Hermano en inOrden) para el arbol Eneario. <br>
     */
    public Iterator<T> inOrden(){
        ListaCD<T> l=new ListaCD<T>();
        inOrden(this.getRaiz(),l);
        return (l.iterator());
    }

    /**
     * Metodo de tipo privado que retorna un Iterador con el recorrido in Orden del Arbol Eneario. <br>
     * <b>post: </b> Se retorno un iterador inOrdenpara el Arbol.<br>
     * @param r representa la raiz del Arbol, o raiz de subarbol. <br>
     * @param l Lista para el almacenamiento de los datos del Arbol. <br>
     */
    private void  inOrden(NodoEneario<T> r, ListaCD<T> l){
        NodoEneario<T> q;
        if(r!=null){
            q = r.getHijo();
            if(q == null){
                l.insertarAlFinal(r.getInfo());
            }else{
                inOrden(q,l);
                l.insertarAlFinal(r.getInfo());
                q = q.getHermano();
                while(q != null){
                    inOrden(q,l);
                    q = q.getHermano();
                }
            }
        }
    }

    /**
     * Metodo que retorna un iterador con el recorrido postOrden del Arbol Eneario. <br>
     * <b>post: </b> Se retorno un iterador postOrden para el Arbol.<br>
     * @return Un Iterador en postOrden (Hijo en postOrden->Hermano en postOrden->padre) para el Arbol Eneario. <br>
     */
    public Iterator<T> postOrden(){
        ListaCD<T> l=new ListaCD<T>();
        postOrden(this.getRaiz(),l);
        return (l.iterator());
    }

    /**
     * Metodo de tipo privado que retorna un iterador con el recorrido postOrden del Arbol Eneario. <br>
     * <b>post: </b> Se retorno un iterador postOrden para el arbol.<br>
     * @param r representa la raiz del arbol, o raiz de subarbol. <br>
     * @param l Lista para el almacenamiento de los datos del arbol.
     */
    private void  postOrden(NodoEneario<T> r, ListaCD<T> l){        
        NodoEneario<T> q;
        if(r!=null){
            q = r.getHijo();
            while(q != null){
                postOrden(q,l);
                q = q.getHermano();
            }
            l.insertarAlFinal(r.getInfo());
        }
    }
    
    /**
     * Metodo que permite retornar un Iterador con el recorrido por niveles del Arbol Eneario. <br>
     * <b>post: </b> Se retorno el recorrido por niveles del Arbol Eneario.<br>
     * @return Un iterador con el recorrido por niveles del Arbol Eneario.
     */
    public Iterator<T> impNiveles(){
        Cola<NodoEneario<T>> c = new Cola();
        ListaCD<T> l = new ListaCD();
        if(this.esVacio())
            return (l.iterator());
        NodoEneario<T> s, q;
        c.enColar(this.raiz);
        while(!c.esVacia()){
            q = c.deColar();
            if(q!=null){
                l.insertarAlFinal(q.getInfo());
                s = q.getHijo();
                while(s!=null){
                    c.enColar(s);
                    s = s.getHermano();
                }
            }            
        }
        return (l.iterator());
    }
    
    /**
     * Metodo que permite obtener el peso del Arbol Eneario. <br>
     * <b>post: </b> Se retorno el numero de elementos en el Arbol Eneario.<br>
     * @return Un entero con la cantidad de elementos del Arbol Eneario.
     */
    public int getPeso(){
        return(getPeso(this.getRaiz()));
    }

    /**
     * Metodo de tipo privado que permite conocer el numero de elemento del Arbol Eneario. <br>
     * <b>post: </b> Se retorno el numero de elementos en el Arbol.<br>
     * @param r Representa la raiz del Arbol, o raiz de subarbol. <br>
     * @return El munero de elementos que contiene el Arbol Eneario.
     */
    private int getPeso(NodoEneario<T> r){
        NodoEneario<T> q;
        int cant = 0;
        if(r!=null){
            cant++;
            q = r.getHijo();
            if(q != null){
                cant+=getPeso(q);
                q = q.getHermano();
                while(q != null){
                    cant+=getPeso(q);
                    q = q.getHermano();
                }
            }
        }
        return (cant);
    }
    
    /**
     * Metodo que permite conocer si un Arbol Eneario se encuenta vacio. <br>
     * <b>post: </b> Se evaluo si el ArbolEneario se enecuenta o no vacio. <br>
     * @return Un objeto de tipo boolean, true si el Arbol se encuenta vacio, false en caso contrario
     */
    public boolean esVacio(){
        return (this.raiz==null);
    }
    
    /**
     * Metodo que permite conocer el grado del Arbol Eneario, que es su misma gordura. <br>
     * <b>post: </b> Se retorno la gordura del Arbol Eneario, es decir el mayor numero de hijos de un Nodo. <br>
     * @return Un objeto de tipo int con la gordura del Arbol Eneario.
     */
    public int gordura(){
        if(this.esVacio())
            return (0);
       int masGordo = -1;
       Cola<NodoEneario<T>> cola = new Cola<NodoEneario<T>>();
       Cola<Integer> c = new Cola<Integer>();
       NodoEneario<T> s, q;
       int i=0;
       int cont=1,ant=-1;       
       cola.enColar(this.getRaiz());
       c.enColar(i);
       while(!cola.esVacia()){
           q = cola.deColar();
           i = c.deColar();
           if(i!=ant){
               if(masGordo < cont){
                   masGordo = cont;
               }
               cont=0;
               ant=i;
           }
           cont++;
           s = q.getHijo();
           while(s != null){
               cola.enColar(s);
               c.enColar(i+1);
               s = s.getHermano();
           }
       }
       return ((masGordo<cont)?cont:masGordo);
   }
    
    /**
     * Metodo que permite obtener la altura del Arbol Eneario. <br>
     * <b>post: </b> Se retorno la altura del Arbol Eneario.<br>
     * @return Un entero con la altura del Arbol Eneario.
     */
    public int getAltura(){
        if(this.esVacio())
            return (0);
        return(getAltura(this.getRaiz()));
    }

    /**
     * Metodo de tipo privado que permite conocer la altura del Arbol Eneario. <br>
     * <b>post: </b> Se retorno la altura del Arbol Eneario. <br>
     * @param r Representa la raiz del Arbol, o raiz de subarbol. <br>
     * @return Un entero con la altura del Arbol Eneario.
     */
    private int getAltura(NodoEneario<T> r){
        if(this.esHoja(r))
            return (1);
        int maxAltura = 0;
        NodoEneario<T> q;
        if(r!=null){
            q = r.getHijo();            
            if(q != null){
                while(q != null){
                    int auxAltura = getAltura(q);
                    if(auxAltura>maxAltura)
                        maxAltura = auxAltura;
                    q = q.getHermano();
                }
            }
        }
        return (maxAltura+1);
    }

   
}//Final de la Clase Arbol Eneario


    

    
