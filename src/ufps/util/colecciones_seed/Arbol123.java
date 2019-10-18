/**
 * ---------------------------------------------------------------------
 * $Id: Arbol123.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;

import java.util.Iterator;

/**
 * Implementacion de Clase para el manejo de un Arbol Binario Ordenado 1-2-3. <br>
 * @param <T> Tipo de datos a almacenar en el Arbol 1-2-3. <br>
 * @author Uriel Garcia
 * @version 1.0
 */
public class Arbol123 <T>{
    
    ////////////////////////////////////////////////////////////
    // Arbol123 - Atributos ////////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Nodo raiz del Arbol 1-2-3
     */
    private Nodo123<T> raiz;
    
    
    
    ////////////////////////////////////////////////////////////
    // Arbol123 - Implementacion de Metodos ////////////////////
    ////////////////////////////////////////////////////////////  
    
    /**
     * Crea un Arbol1-2-3 vacio.
     * <b>post: </b> Se creo un Arbol 1-2-3 vacio.<br>
     */
    public Arbol123(){
        this.raiz= null;
    }

    /**
     * Metodo que permite conocer la raiz del Arbol 1-2-3. <br>
     * <b>post: </b> Se obtuvo la raiz del Arbol 1-2-3.<br>
     * @return Un objeto de tipo Nodo1-2-3 que es la raiz del Arbol 1-2-3.
     */
    public Nodo123<T> getRaiz() {
        return raiz;
    }
    
    /**
     * Metodo que permite conocer el dato menor en la raiz del Arbol 1-2-3. <br>
     * <b>post: </b> Se obtuvo la raiz del Arbol 1-2-3.<br>
     * @return Un objeto de tipo Nodo1-2-3 que es la raiz del Arbol 1-2-3.
     */
    public T getInfoMenRaiz() {
        return raiz.getInfoMen();
    }
    
    /**
     * Metodo que permite conocer el dato mayor en la raiz del Arbol 1-2-3. <br>
     * <b>post: </b> Se obtuvo la raiz del Arbol 1-2-3.<br>
     * @return Un objeto de tipo Nodo1-2-3<T> que es la raiz del Arbol 1-2-3.
     */
    public T getInfoMayRaiz() {
        return raiz.getInfoMay();
    }

    /**
     * Metodo que permite modificar la raiz del Arbol 1-2-3. <br>
     * <b>post: </b> Se modifico la raiz del Arbol 1-2-3. <br>
     * @param raiz Objeto de tipo Nodo123<T> que representa la nueva raiz del Arbol.
     */
    private void setRaiz(Nodo123<T> raiz) {
        this.raiz = raiz;
    }
    
    /**
     * Metodo que permite insertar un nuevo dato dentro del Arbol 1-2-3 y que este mantenga sus propiedades. <br>
     * <b>post: </b> Se inserto un nuevo dato dentro del Arbol 1-2-3. <br>
     * @param nuevo Representa el nuevo que se pretende ingresar al Arbol 1-2-3. <br>
     * @return true o false dependiendo si se pudo o no insertar el nuevo elemento dentro del Arbol.
     */
    public boolean insertar(T nuevo){
        if(this.esta(nuevo))
            return (false);
        if(this.esVacio()){
            this.setRaiz(new Nodo123(nuevo,null));
            return (true);
        }            
        return (this.insertar(this.raiz,nuevo));
    }
    
    /**
     * Metodo de tipo privado que permite insertar un nuevo dato dentro del Arbol 1-2-3 y que este mantenga
     * sus propiedades como tal de ordenamiento de los datos. <br>
     * @param r Representa la raiz del Arbol o del subArbol. <br>
     * @param dato Representa el nuevo dato de tipo T a insertar dentro del Arbol. <br>
     * @return Un objeto de tipo boolean que permite evaluar el exito de la operacion realizada.
     */
    private boolean insertar(Nodo123<T> r, T dato) {
        int comp1=0, comp2=0;
        boolean hayInfoI=false, hayInfoF=false;
        //Si existe el InfoMenor
        if(r.getInfoMen()!=null){
            hayInfoI = true;
            comp1 = ((Comparable)dato).compareTo(r.getInfoMen());
        }
        //Si existe el InfoMayor
        if(r.getInfoMay()!=null){
            hayInfoF = true;
            comp2 = ((Comparable)dato).compareTo(r.getInfoMay());
        }        
        if(r.getInfoMen()==null && r.getInfoMay()==null){
            r.setInfoMen(dato);
            return (true);
        } 
        if(r.getInfoMay()==null){
            if(hayInfoI&&comp1>0){
                r.setInfoMay(dato);
                return (true);
            }
            if(hayInfoI&&comp1<0){
                //cambio de posiciones los infos
                if(r.getIzq()==null){
                    r.setInfoMay(r.getInfoMen());
                    r.setInfoMen(dato);
                    return (true);
                }else{
                    return (insertar(r.getIzq(),dato));
                }
            }
        }
        //Si es menor que InfoMenor
        if(hayInfoI&&comp1<0){
            if(r.getIzq()==null)
                r.setIzq(new Nodo123());
            return (this.insertar(r.getIzq(),dato));
        }
        //Si es mayor que InfoMenor pero menor que InfoMayor
        if((hayInfoI&&comp1>0) && (hayInfoF&&comp2<0)){
            //Insertar por el medio
            if(r.getMed()==null)
                r.setMed(new Nodo123());
            return (this.insertar(r.getMed(),dato));
        }
        //Si es mayor que InfoMayor
        if((hayInfoI&&comp1>0) && (hayInfoF&&comp2>0)){
            //Insertar por la derecha
            if(r.getDer()==null)
                r.setDer(new Nodo123());
            return (this.insertar(r.getDer(),dato));
        }
        return (false);
    }
    
    /**
     * Metodo que permite eliminar un dato del Arbol1-2-3; manteniendo el Arbol sus propiedades de ordenamiento. <br>
     * <b>post: </b> Se elimino un elemento del Arbol1-2-3 y este ha mantenido sus propiedades.
     * @param dato Representa el Objeto de tipo T que se desea eliminar del Arbol.
     * @return  
     */
    public boolean eliminar(T dato){
        if(this.esVacio() || !this.esta(dato))
            return (false);
        this.setRaiz(eliminar(this.raiz,dato));
        return (true);
    }
    
     /**
     * Metodo de tipo privado que permite desconectar un Nodo del Arbol1-2-3; manteniendo el Arbol sus propiedades de ordenamiento. <br>
     * @param r Representa la raiz del Arbol o del subArbol sobre el cual se elimina el dato. <br>
     * @param dato Es un objeto de tipo T y representa el dato que se desea eliminar del Arbol. <br>
     * @return Un objeto de tipo Nodo123<T> que representa la raiz del Arbol una vez eliminado el objeto del Arbol.
     */
    private Nodo123<T> eliminar(Nodo123<T> r, T dato) {
        int comp1=0, comp2=0;
        boolean hayInfoI=false, hayInfoF=false;
        //Si existe el InfoMenor
        if(r.getInfoMen()!=null){
            hayInfoI = true;
            comp1 = ((Comparable)dato).compareTo(r.getInfoMen());
        }
        //Si existe el InfoMayor
        if(r.getInfoMay()!=null){
            hayInfoF = true;
            comp2 = ((Comparable)dato).compareTo(r.getInfoMay());
        }
        //Si lo ubica como InfoMenor
        if(hayInfoI && comp1==0){
            if(r.getIzq()!=null){
                r.setInfoMen(getMayor(r.getIzq()));
                r.setIzq(eliminar(r.getIzq(),r.getInfoMen()));
                return (r);
            }
            if(r.getInfoMay()!=null){
                r.setInfoMen(r.getInfoMay());
                r.setInfoMay(r.getDer()!=null?getMayor(r.getDer()):null);
                if(r.getInfoMay()!=null )
                    r.setDer(eliminar(r.getDer(),r.getInfoMay()));
                r.setIzq(r.getMed());
                r.setMed(r.getDer());
                r.setDer(null);
                return (r);
            }
            if(r.getMed()!=null){
                return r.getMed();
            }
            return (null);
        } 
        else
            //Si lo ubico como InfoMayor
        if((hayInfoF&&comp2==0) && r.getInfoMay()!=null){
            if( r.getDer() != null ){
                r.setInfoMay(getMenor(r.getDer()));
                r.setDer(eliminar(r.getDer(), r.getInfoMay()));
                return (r);
            }          
            if(r.getMed()!=null){
                r.setInfoMay(getMayor(r.getMed()));
                r.setMed(eliminar(r.getMed(),r.getInfoMay()));
            }   
            else
            {
                r.setInfoMay(null);
            }
            return (r); 
        }
        //Buscar por la Izquierda
        if((hayInfoI&&comp1<0) && r.getIzq()!=null){
            r.setIzq(eliminar(r.getIzq(),dato));
            return (r);
        } 
        //Buscar por el medio
        if((r.getInfoMay()==null || (hayInfoF&&comp2<0)) && r.getMed()!=null){
            r.setMed(eliminar(r.getMed(),dato));
            return (r);
        } 
        //Buscar por la derecha
        if( r.getDer()!=null ){
            r.setDer(eliminar(r.getDer(),dato));
            return (r);
        }
        return (r);        
    }
     
    /**
     * Metodo de tipo privado que permite conocer el dato de mayor valor teniendo un Nodo123<T> como referencia. <br>
     * @param r Representa la raiz del Arbol o subArbol sobre el cual se quiere conocer el dato mayor. <br>
     * @return Un objeto de tipo T que representa el dato de mayor valor a la derecha de un Nodo. <br>
     */
    private T getMayor(Nodo123<T> r){
        if(r.getDer()!=null)
            return(getMayor(r.getDer()));
        if(r.getInfoMay()!=null)
            return (r.getInfoMay());
        if(r.getMed()!=null)
            return(getMayor(r.getMed()));
        return (r.getInfoMen());
    }
     
    /**
     * Metodo de tipo privado que permite conocer el dato de menor valor teniendo un Nodo123<T> como referencia. <br>
     * @param r Representa la raiz del Arbol o subArbol sobre el cual se quiere conocer el dato menor. <br>
     * @return Un objeto de tipo T que representa el dato de menor valor a la izquierda de un Nodo. <br>
     */
    private T getMenor(Nodo123<T> r){
        Nodo123<T> aux = r;
        while(aux.getIzq()!=null)
            aux = aux.getIzq();
        return (aux.getInfoMen());
    }
    
    /**
     * Metodo que permite evaluar la existencia de un objeto dentro del Arbol 1-2-3. <br>
     * <b>post: </b> Se retorno true si el elemento se encuentra en el Arbol.<br>
     * @param dato Representa el elemento el cual se desea evaluar su existencia en el Arbol. <br>
     * @return Un boolean , true si el dato esta o false en caso contrario.
     */
    public boolean esta(T dato){
        if(this.esVacio())
            return (false);
        return (this.esta(this.raiz,dato));
    }

    /**
     * Metodo privado que permite conocer si un elemento especifico se encuentra en el Arbol. <br>
     * <b>post: </b> Se retorno true si el elemento se encuentra en el Arbol.<br>
     * @param r Representa la raiz del Arbol o del subArbol. <br>
     * @param dato Representa el elemento que se desea encontrar del Arbol <br>
     * @return Un boolean , true si el dato esta o false en caso contrario.
     */
    private boolean esta(Nodo123<T> r, T dato) {        
        int comp1=0, comp2=0;
        boolean hayInfoI=false, hayInfoF=false;
        //Existe el InfoMenor
        if(r.getInfoMen()!=null){
            hayInfoI = true;
            comp1 = ((Comparable)dato).compareTo(r.getInfoMen());
        }
        //Existe el InfoMayor
        if(r.getInfoMay()!=null){
            hayInfoF = true;
            comp2 = ((Comparable)dato).compareTo(r.getInfoMay());
        } 
        if((hayInfoI&&comp1==0) || (hayInfoF&&comp2==0))
            return (true);   
        //Busca por izquierda
        if(hayInfoI&&comp1<0){
            if(r.getIzq()==null)
                return (false);
            return (this.esta(r.getIzq(),dato));
        }
        //Busca por el medio
        if(hayInfoF&&comp2<0){
            if(r.getMed()==null)
                return (false);
            return (this.esta(r.getMed(), dato));
        }
        //Busca por la derecha
        if(r.getDer()!=null)            
        return (this.esta(r.getDer(), dato));
        //El objeto no ha sido encontrado
        return (false);
    }
    
    /**
     * Metodo que permite buscar un dato dentro del Arbol 1-2-3 y retornar el Nodo que lo contiene. <br>
     * <b>post: </b> Se retorno el Nodo123<T> que representa la ubicacion del dato en el Arbol. <br>
     * @param dato Representa el dato que se quiere localizar dentro del Arbol 1-2-3. <br>
     * @return Un objeto de tipo Nodo123<T> que representa la ubicacion del dato dentro del Arbol.
     */
    public Nodo123<T> buscar(T dato){
        if(this.esVacio())
            return (null);
        return (this.buscar(this.raiz,dato));
    }

    /**
     * Metodo que permite buscar un dato dentro del Arbol 1-2-3 y retornar el Nodo que lo contiene. <br>
     * <b>post: </b> Se retorno el Nodo123<T> que representa la ubicacion del dato en el Arbol. <br>
     * @param r Representa la raiz del Arbol 1-2-3 en el que se buscara el dato. <br>
     * @param dato Representa el dato que se quiere localizar dentro del Arbol 1-2-3. <br>
     * @return Un objeto de tipo Nodo123<T> que representa la ubicacion del dato dentro del Arbol.
     */
    private Nodo123<T> buscar(Nodo123<T> r, T dato) {
        int comp1=0, comp2=0;
        boolean hayInfoI=false, hayInfoF=false;
        //Si existe el InfoMenor
        if(r.getInfoMen()!=null){
            hayInfoI = true;
            comp1 = ((Comparable)dato).compareTo(r.getInfoMen());
        }
        //Si existe el InfoMAyor
        if(r.getInfoMay()!=null){
            hayInfoF = true;
            comp2 = ((Comparable)dato).compareTo(r.getInfoMay());
        }         
        if((hayInfoI&&comp1==0) || (hayInfoF&&comp2==0))
            return (r);     
        //Buscar por la izquierda
        if(hayInfoI&&comp1<0){
            if(r.getIzq()==null)
                return (null);
            return (this.buscar(r.getIzq(),dato));
        }
        //Buscar por el medio
        if(hayInfoF&&comp2<0){
            if(r.getMed()==null)
                return (null);
            return (this.buscar(r.getMed(), dato));
        }
        //Buscar por la derecha
        if(r.getDer()!=null)            
        return (this.buscar(r.getDer(), dato));
        //El objeto no ha sido encontrado
        return (null);
    }
    
    /**
     * Metodo que retorna un iterador con las hojas del Arbol 1-2-3. <br>
     * <b>post: </b> Se retorno un iterador con las hojas del Arbol.<br>
     * @return Un iterador con las hojas del Arbol.
     */
    public Iterator<T> getHojas(){
        ListaCD<T> l=new ListaCD<T>();
        this.getHojas(this.raiz,l);
        return (l.iterator());
    }

    /**
     * Metodo de tipo privado que retorna un iterador con las hojas del Arbol 1-2-3. <br>
     * <b>post: </b> Se retorno un iterador con las hojas del Arbol.<br>
     * @param r Representa la raiz del Arbol, o raiz de subArbol. <br>
     * @param l Lista para el almacenamiento de los datos del Arbol. <br>
     */
    private void getHojas(Nodo123<T> r, ListaCD<T> l){
        if (r!=null){
            if(this.esHoja(r)){
                if(r.getInfoMen()!=null)
                    l.insertarAlFinal(r.getInfoMen());
                if(r.getInfoMay()!=null)
                l.insertarAlFinal(r.getInfoMay());
            }
            this.getHojas(r.getIzq(), l);
            this.getHojas(r.getMed(), l);
            this.getHojas(r.getDer(), l);
        }
    }
    
    /**
     * Metodo de tipo privado que permite saber si un elemento es una hoja. <br>
     * <b>post: </b> Se retorno true si es nodo hoja del Arbol.<br>
     * @param r Representa la raiz del Arbol, o raiz de subArbol. <br>
     * @return true si es una hoja; es decir, sus dos hijos son null. <br>
     */
    private boolean esHoja(Nodo123<T> r) {
        return(r!=null&&r.getIzq()==null&&r.getMed()==null&&r.getDer()==null);
    }
    
    /**
     * Metodo que permite determinar el numero de Nodo hojas dentro del Arbol. <br>
     * <b>post: </b> Se retorno el numero de hojas del Arbol. <br>
     * @return El numero de hojas existentes en el Arbol.
     */
    public int contarHojas(){
        return (this.contarHojas(this.raiz));
    }
    
    /**
     * Metodo que permite determinar el numero de Nodo hojas dentro del Arbol. <br>
     * <b>post: </b> Se retorno el numero de hojas del Arbol. <br>
     * @return Un objeto de tipo int con el numero de hojas existentes en el Arbol.
     */
    private int contarHojas(Nodo123<T> r){
        if(r==null)
            return (0);
        if(this.esHoja(r))
            return (1);
        int chi = contarHojas(r.getIzq());
        int chm = contarHojas(r.getMed());
        int chd = contarHojas(r.getDer());
        return (chi+chm+chd);
    }
    
    /**
     * Metodo que retorna un iterador con el recorrido preOrden del Arbol. <br>
     * <b>post: </b> Se retorno un iterador en preOrden para el Arbol.<br>
     * @return un iterador en preorden (padre1->hijoIzq->padre2->hijoMed->hijoDer) para el Arbol 1-2-3.
     */
    public Iterator<T> preOrden(){
        ListaCD<T> l=new ListaCD<T>();
        preOrden(this.getRaiz(),l);
        return (l.iterator());
    }

    /**
      * Metodo que tipo privado que retorna un iterador con el recorrido preOrden del Arbol. <br>
      * <b>post: </b> Se retorno un iterador en preOrden para el Arbol.<br>
      * @param r Representa la raiz del Arbol, o raiz de subArbol. <br>
      * @param l Lista para el almacenamiento de los datos del Arbol. <br>
      */
    private void  preOrden(Nodo123<T> r, ListaCD<T> l){
        if(r!=null){
            if(r.getInfoMen()!=null)
            l.insertarAlFinal(r.getInfoMen());            
            if(r.getInfoMay()!=null)
            l.insertarAlFinal(r.getInfoMay());
            preOrden(r.getIzq(), l);          
            preOrden(r.getMed(),l);            
            preOrden(r.getDer(), l);
        }
    }
    
    /**
     * Metodo que retorna un iterador con el recorrido in Orden del Arbol. <br>
     * <b>post: </b> Se retorno un iterador inOrden para el Arbol.<br>
     * @return un iterador en inOrden (hijoIzq->padre1->hijoMed->padre2->hijoDer) para el Arbol 1-2-3. <br>
     */
    public Iterator<T> inOrden(){
        ListaCD<T> l=new ListaCD<T>();
        inOrden(this.getRaiz(),l);
        return (l.iterator());
    }

    /**
     * Metodo de tipo privado que retorna un iterador con el recorrido in Orden del Arbol. <br>
     * <b>post: </b> Se retorno un iterador inOrden para el Arbol.<br>
     * @param r Representa la raiz del Arbol, o raiz de subarbol. <br>
     * @param l Lista para el almacenamiento de los datos del Arbol. <br>
     */
    private void  inOrden(Nodo123<T> r, ListaCD<T> l){
        if(r!=null){
            inOrden(r.getIzq(), l);
            if(r.getInfoMen()!=null)
            l.insertarAlFinal(r.getInfoMen());            
            if(r.getInfoMay()!=null)
            l.insertarAlFinal(r.getInfoMay());
            inOrden(r.getMed(),l);                                  
            inOrden(r.getDer(), l);
        }
    }
    
    /**
     * Metodo que retorna un iterador con el recorrido postOrden del Arbol. <br>
     * <b>post: </b> Se retorno un iterador preOrden para el arbol.<br>
     * @return un iterador en postOrden (hijoIzq->hijoMed->hijoDer->padre1->padre2) para el Arbol 1-2-3. <br>
     */
    public Iterator<T> postOrden(){
        ListaCD<T> l=new ListaCD<T>();
        postOrden(this.getRaiz(),l);
        return (l.iterator());
    }

    /**
     * Metodo de tipo privado que retorna un iterador con el recorrido postOrden del Arbol. <br>
     * <b>post: </b> Se retorno un iterador preOrden para el Arbol.<br>
     * @param r Representa la raiz del arbol, o raiz de subarbol. <br>
     * @param l Lista para el almacenamiento de los datos del Arbol
     */
    private void  postOrden(Nodo123<T> r, ListaCD<T> l){
        if(r!=null){            
            postOrden(r.getIzq(), l);            
            postOrden(r.getMed(),l);            
            postOrden(r.getDer(), l);
            if(r.getInfoMen()!=null)
            l.insertarAlFinal(r.getInfoMen());            
            if(r.getInfoMay()!=null)
            l.insertarAlFinal(r.getInfoMay());
        }
    }
    
    /**
     * Metodo que permite retornar un iterador con el recorrido por Niveles del Arbol. <br>
     * <b>post: </b> Se retorno el recorrido por niveles del Arbol 1-2-3.<br>
     * @return Un iterador con el recorrido por niveles del Arbol 1-2-3.
     */
    public Iterator<T> impNiveles(){
        ListaCD<T> l=new ListaCD<T>();
        if(!this.esVacio()){
            Cola<Nodo123<T>> c=new Cola<Nodo123<T>>();
            c.enColar(this.getRaiz());
            Nodo123<T> x;
                while(!c.esVacia()){
                    x=c.deColar();
                    l.insertarAlFinal(x.getInfoMen());
                    l.insertarAlFinal(x.getInfoMay());
                    if(x.getIzq()!=null)
                    c.enColar(x.getIzq());
                    if(x.getMed()!=null)
                    c.enColar(x.getMed());
                    if(x.getDer()!=null)
                    c.enColar(x.getDer());
                }
        }
        return (l.iterator());
    }
    
    /**
     * Metodo que permite obtener el peso del Arbol 1-2-3. <br>
     * <b>post: </b> Se retorno el numero de elementos en el Arbol 1-2-3.<br>
     * @return Un entero con la cantidad de elementos del Arbol 1-2-3.
     */
    public int getPeso(){
        return(getPeso(this.getRaiz()));
    }

    /**
     * Metodo de tipo privado que permite conocer el numero de elemento del Arbol 1-2-3. <br>
     * <b>post: </b> Se retorno el numero de elementos en el arbol.<br>
     * @param r Representa la raiz del arbol, o raiz de subarbol. <br>
     * @return El munero de elementos que contiene el Arbol 1-2-3.
     */
    private int getPeso(Nodo123<T> r){
        if(r==null)
            return 0;
        int n=1;
        if(r.getInfoMay()!=null)
            n=2;
        return (getPeso(r.getIzq())+n+getPeso(r.getMed())+getPeso(r.getDer()));
    }
    
    /**
     * Metodo que permite saber si el Arbol 1-2-3 se encuentra vacio. <br>
     * <b>post: </b> Se retorno true si el arbol no contiene elementos.<br>
     * @return true si no hay datos en el Arbol 1-2-3.
     */
    public boolean esVacio() {
        return (this.raiz==null);
    }
    
    /**
     * Metodo que permite obtener la altura del Arbol 1-2-3. <br>
     * <b>post: </b> Se retorno la altura del Arbol 1-2-3.<br>
     * @return Un entero con la altura del Arbol 1-2-3.
     */
    public int getAltura(){
        if(this.raiz==null)
            return (0);
        return(getAltura(this.getRaiz()));
    }

    /**
     * Metodo de tipo privado que permite conocer la Altura del Arbol 1-2-3. <br>
     * <b>post: </b> Se retorno la altura del Arbol 1-2-3. <br>
     * @param r Representa la raiz del arbol, o raiz de subarbol. <br>
     * @return Un entero con la altura del Arbol 1-2-3.
     */
    public int getAltura(Nodo123<T> r){
        int ai=0, am=0, ad=0, alt;
        if(r.getIzq()!=null)
            ai = getAltura(r.getIzq());
        if(r.getMed()!=null)
            am = getAltura(r.getMed());
        if(r.getDer()!=null)
            ad = getAltura(r.getDer());
        alt = ai;
        if(am>=ai)
            alt = am;
        if(ad>=alt)
            alt = ad;
        return (alt+1);        
    }
    
    /**
     * Metodo que permite eliminar los elementos Hoja de un Arbol123.
     */
    public void podar() {
        if(this.esHoja(raiz))
            this.setRaiz(null);
        podar(this.raiz);
    }

    private void podar(Nodo123<T> r) {
        if (r==null)
            return;
        if(this.esHoja(r.getIzq()))
            r.setIzq(null);
        if(this.esHoja(r.getMed()))
            r.setMed(null);
        if(this.esHoja(r.getDer()))
            r.setDer(null);
        podar(r.getIzq());
        podar(r.getMed());
        podar(r.getDer());
    }
    
    /**
     * Metodo que permite conocer por consola la informacion del Arbol.
     */
    public void imprime(){
        imprime(this.raiz);
    }
    
    /**
     * Metodo de tipo privado que permite mostrar por consola la informacion del Arbol 1-2-3. <br>
     * @param r Representa la raiz del Arbol1-2-3 o de alguno de sus subarboles.
     */
    public void imprime(Nodo123<T> r){
        if(this.esVacio())
            return;
        String cad= "";
        cad+="Info:"+r.getInfoMen()+"."+r.getInfoMay()+"";;
        if(r.getIzq()!=null)
            cad+="\t Izq"+r.getIzq().getInfoMen()+"."+r.getIzq().getInfoMay()+"";
        if(r.getMed()!=null)
            cad+="\t Med"+r.getMed().getInfoMen()+"."+r.getMed().getInfoMay()+"";
        if(r.getDer()!=null) 
            cad+="\t Der"+r.getDer().getInfoMen()+"."+r.getDer().getInfoMay()+"";
        System.out.println(cad);
        if(r.getIzq()!=null)
            imprime(r.getIzq());
        if(r.getMed()!=null)
            imprime(r.getMed());
        if(r.getDer()!=null) 
            imprime(r.getDer());
        
    }
    
} //Fin de Clase Arbol1-2-3
