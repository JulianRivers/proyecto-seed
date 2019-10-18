/**
 * ---------------------------------------------------------------------
 * $Id: ArbolB.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;
import java.util.Iterator;

/**
 * Implementacion de Clase para el manejo de un ArbolB.
 * @param <T> Tipo de datos a almacenar en el ArbolB.
 * @author Yulieth Pabon
 * @version 1.0
 */

public class ArbolB <T>
{
    
    ////////////////////////////////////////////////////////////
    // ArbolB - Atributos //////////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * orden del árbol
     */
    private int n; 
    
    /**
     *numero maximo de llaves
     */ 
    private int m; 
    
    /**
     * numero maximo de apuntadores
     */
    private int m1;
    
    /**
     * raiz del arbol
     */
    private Pagina raiz;
    
    
   
    ////////////////////////////////////////////////////////////
    // ArbolB - Implementacion de Metodos //////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Crea un arbol B  vacio con orden por defecto de 2. <br>
     * <b>post: </b> Se creo un arbol B vacio por defecto. <br>
     */
    public ArbolB(){
        this.raiz = null;
        this.n=2;
         this.m=n*2;
        this.m1= (this.m)+1;
    }
    
    /**
     * Crea un arbol B  vacio con orden especifico. <br>
     * <b>post: </b> Se creo un arbol B vacio con orden especifico. <br>
     * @param n de tipo entero que indica el numero el orden del arbol B. <br>
     */
    public ArbolB(int n){
        if (n<=0){
             System.err.println("Tamano del orden del arbol no es válido");
             return;
         }
        this.raiz = null;
        this.n=n;
        this.m=n*2;
        this.m1= (this.m)+1;
    }

    /**
     * Metodo que permite conocer la raiz del Arbol B. <br>
     * <b>post: </b> Se obtuvo la raiz del Arbol B. <br>
     * @return Un objeto de tipo NodoB<T> que es la raiz del Arbol B. <br>
     */
    public Pagina getRaiz() {
        return raiz;
    }

    /**
     * Metodo que permite modificar la raiz del Arbol B. <br>
     * <b>post: </b> Se modifico la raiz del Arbol B. <br>
     * @param raiz Objeto de tipo NodoB<T> que representa la nueva raiz del Arbol. <br>
     */
    protected void setRaiz(Pagina raiz) {
        this.raiz = raiz;
    }
    
    /**
     * Metodo que permite saber el numero de orden del arbol. <br>
     * <b>post: </b> Se retorno el numero de orden del arbol. <br>
     * @return el numero de orden del arbol.
     */
     public int getN() {
        return n;
    }

    /**
     * Metodo que permite saber el numero maximo de elementos que puede almacenar el arbol por pagina. <br>
     * <b>post: </b> Se retorno el numero maximo de elementos que puede almacenar el arbol por pagina. <br>
     * @return el numero maximo de elementos que puede almacenar el arbol por pagina.
     */
    public int getM() {
        return m;
    }

    /**
     * Metodo que permite saber el numero maximo de apuntadores que puede almacenar el arbol por pagina. <br>
     * <b>post: </b> Se retorno el numero maximo de apuntadores que puede almacenar el arbol por pagina. <br>
     * @return el numero maximo de apuntadores que puede almacenar el arbol por pagina.
     */
    public int getM1() {
        return m1;
    }

    /**
     * Metodo que permite modificar el numero de orden del arbol. <br>
     * <b>post: </b> Se modifico el numero de orden del arbol. <br>
     * @param n nuevo orden del arbol
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * Metodo que permite insertar un nuevo dato en el arbol B. <br>
     * <b>post: </b> Se inserto un nuevo dato al arbol B. <br>
     * @param x dato a insertar en el arbol.  <br>
     * @return la pagina donde se inserto x, o null sino se inserto correctamente
     */
     public boolean insertar(T x){
        //pila para guarddar el camino desde la raiz hasta la pagina donde se inserta x
        Pila<Pagina> pila= new Pila<Pagina>();
        //Para trabajar subir y subir1 por referencia se usa si la pagina se rompe
        T []subir= (T[]) new Object[1];
        T []subir1= (T[]) new Object[1];
        //variables auxiliares 
        int posicion=0, i=0, terminar, separar;
        Pagina p = null, nuevo=null, nuevo1;
        if(this.raiz==null){ 
            this.raiz=this.crearPagina(x);
        }
        else{
            posicion= buscar(this.raiz,x, pila);
            if(posicion==-1)
                return (false);
            else{
                terminar=separar=0;
                while((!pila.esVacia()) && (terminar==0)){
                    p= pila.desapilar();
                    if(p.getCont()==this.m){
                        if(separar==0){
                            nuevo=romper(p,null,x,subir);
                            separar=1;
                        }
                    else{
                        nuevo1=romper(p,nuevo,subir[0],subir1);
                        subir[0]=subir1[0];
                        nuevo=nuevo1;
                    }
                  }
                    else{
                        if(separar==1){
                            separar=0;
                            i=donde(p,subir[0]);
                            i=insertar(p, subir[0],i);
                            cderechaApunt(p,i+1);
                            p.getApuntadores()[i+1]= nuevo;

                        }
                        else{   
                            posicion=insertar(p,x,posicion);
                        }
                        terminar=1;
                    }
                }
                if((separar==1)&&(terminar==0)){
                    this.setRaiz(this.crearPagina(subir[0]));
                    this.raiz.getApuntadores()[0]=p;
                    this.raiz.getApuntadores()[1]=nuevo;
                }
            }
        }
        return (true);
    }
     
    /**
     *  Metodo que permite insertar una pagina en el arbol B. <br>
     * <b>post: </b> Se inserto un nuevo dato al arbol B. <br>
     * @param p pagina en donde inserta el dato. <br>
     * @param x apuntador a una pagina del arbol. <br>
     * @param i indicando la posicion donde se desea insertar el dato. <br>
     * @return la posicion donde se inserto el dato.
     */
      private int insertar(Pagina p, T x, int i){
          int j ;
          if(p.getCont()!=0){
           int compara=((Comparable)p.getInfo()[i]).compareTo(x);
            if(compara<0)
                i++;
            else{
                j=p.getCont()-1;
                while(j>=i){
                     p.getInfo()[j+1] = p.getInfo()[j];
                     j=j-1;
                }
            }                
         }
          p.setCont(p.getCont()+1);
          p.getInfo()[i]= x;          
          return (i);
      }
     
    /**
     * Metodo que permite eliminar una dato del arbol B. <br>
     * <b>post: </b> Se elimino el dato del arbol B. <br>
     * @param x dato que se desea eliminar. <br>
     * @return el dato que se elimino o null en caso de no haberse eliminado el dato.
     */
    public boolean eliminar(T x){
        int  posicion,i,k;
        Pagina p,q = null,r,t;  
        Pila<Componente> pila=new Pila<Componente>();
        Componente objeto=new Componente();
        posicion=esta(this.raiz,x,pila);
        if(posicion==-1)
           return (false);//la llave no existe en el arbol
        else{
            objeto= pila.desapilar();
            p=objeto.getP();
            i=objeto.getV();
            if(!this.esHoja(p)){
                t=p;
                k=i;
                pila.apilar(new Componente(p, i+1));
                p=p.getApuntadores()[i+1];
                while(p!=null){
                    pila.apilar(new Componente (p,0));
                    p=p.getApuntadores()[0];
                }
                objeto= pila.desapilar();
                p=objeto.getP();// p pagina que contiene el dato a eliminar
                i=objeto.getV();
                t.getInfo()[k]=p.getInfo()[0];
                x=(T)p.getInfo()[0];
                posicion=0;
            }
            if(p.getCont()>this.n)
                this.retirar(p,posicion);
            else{
                if(!pila.esVacia()){
                    objeto= pila.desapilar();
                    q=objeto.getP();//q pagina que contiene el puntero de la pagina donde esta el dato
                    i=objeto.getV();
                    if(i<q.getCont()){
                        r=q.getApuntadores()[i+1];
                        if(r.getCont()>this.n){
                            this.retirar(p, posicion);
                            this.cambio(p, q, r, i, x);
                        }
                        else{
                            if(i!=0){
                                r=q.getApuntadores()[i-1];
                                if(r.getCont()>this.n){
                                    this.retirar(p, posicion);
                                    this.cambio(p, q, r, (i-1), x);
                                }
                                else{ 
                                    this.unir(q, r, p, (i-1), pila, x, posicion);
                                }
                            }
                            else{ 
                            this.unir(q, r, p, i, pila, x, posicion);
                            }
                        }
                    }
                    else{ 
                         r=q.getApuntadores()[i-1];
                         if(r.getCont()>this.n){
                              this.retirar(p, posicion);
                              this.cambio(p, q, r, (i-1), x);
                    }
                         else
                             this.unir(q, r, p, (i-1), pila, x, posicion);
                  }
                }
                else{ 
                    this.retirar(p, posicion);
                    if(p.getCont()==0){
                        this.setRaiz(null);
                    }
                }
            }
        }
        return true;
    }
    
    /**
     * Metodo que permite retirar un dato del arbol indicada. <br>
     * <b>post: </b> Se elimino el dato del arbol B. <br>
     * @param p pagina de la que se desea retirar el dato.  <br>
     * @param i posicion del dato en el arbol. <br>
     */
    private void retirar(Pagina p, int i){
        while(i<p.getCont()-1){
            p.getInfo()[i]= p.getInfo()[i+1];
            i++;
        }
        p.setCont(p.getCont()-1);
    }
   
    /**
     * Metodo que permite crear fisicamente una pagina en memoria. <br>
     * <b>post: </b> Se creo fisicamente una pagina en memoria. <br>
     * @param x informacion que tendra la nueva hoja. <br>
     * @return la pagina creada.
     */
    private Pagina crearPagina(T x){
        Pagina p= new Pagina(n);
        inicializar(p);
        p.setCont(1);
        p.getInfo()[0]= x;
        return (p);
    }
    
     /**
      * Metodo que permite inicializar una pagina. <br>
     * <b>post: </b> Se inicializo una pagina en el arbol. <br>
      * @param p apuntador a una pagina aun si inicializar. <br>
      */
    private void inicializar(Pagina p){
        int i =0;
        p.setCont(0);
        while(i < this.m1)
            p.getApuntadores()[i++] = null;
    }
    
    /**
     * Metodo que permite evaluar la existencia un dato dentro del Arbol B. <br>
     * <b>post: </b> Se evaluo la existencia de un dato dentro del Arbol. <br>
     * @param dato Representa el dato que se quiere localizar dentro del Arbol Eneario. <br>
     * @return Un objeto de tipo boolean que contiene un true si ubico el dato y false en caso contrario.
     */
    public boolean esta(T dato){
        Pila pi=new Pila();
        return(this.esta(this.raiz, dato, pi)!=(-1));
    }
    /**
     * Metodo que permite determinar si un elemento se encuentra en el arbol. <br>
     * <b>post: </b>  Se evaluo la existencia de un dato dentro del Arbol. <br>
     * @param p pagina que contiene las paginas de la busqueda. <br>
     * @param x posicion del apuntador de las paginas. <br>
     * @param pi estructura que almacenara el camino de la busqueda de X. <br>
     * @return posicion de X dentro de la pagina donde se encontro, de no ser asi retorna -1.
     */
       private int esta(Pagina p, T x,Pila<Componente> pila){
            int i=0;
            boolean encontro=false;
            int posicion=-1;
            while((p!=null) && !encontro){
                i=0;
               int compara=((Comparable)p.getInfo()[i]).compareTo(x);
                while((compara<0) && (i<(p.getCont()-1))){
                    i++;
                    compara=((Comparable)p.getInfo()[i]).compareTo(x);
                }
                if((compara>0)){
                    pila.apilar(new Componente(p,i));
                    p=p.getApuntadores()[i];
                }
                else 
                    if((compara<0)){
                        pila.apilar(new Componente(p,i+1));
                        p=p.getApuntadores()[i+1];
                    }
                    else {
                        pila.apilar(new Componente(p,i));
                        encontro=true;
                    } 
                }
                if(encontro){
                    posicion=i;
                }
            return (posicion);
    }
            
    /**
     * Metodo que permite encontrar un elemento se encuentra en el arbol. <br>
     * <b>post: </b> Se realizo una busqueda de X en el arbol. <br>
     * @param p pagina que contiene las paginas de la busqueda. <br>
     * @param x posicion del apuntador de las paginas. <br>
     * @param pi estructura que conteniene el camino de la busqueda de X. <br>
     * @return posicion de X dentro de la pagina donde se encontro, de no ser asi retorna -1
     */
        private int buscar(Pagina p,T x, Pila pila){
            int i=-1,posicion;
            boolean encontro= false;
            posicion=-1;
            while((p!=null)&&!(encontro)){
                pila.apilar(p);
                i=0;
                int compara=((Comparable)p.getInfo()[i]).compareTo(x);
                while((compara<0)&&(i<(p.getCont()-1))){
                    i++;
                    compara=((Comparable)p.getInfo()[i]).compareTo(x);
                }
                if((compara>0))
                    p=p.getApuntadores()[i];
                else{
                    if(compara<0)
                        p=p.getApuntadores()[i+1];
                    else
                        encontro=true;
                }
            }
            if(!encontro)
                posicion=i;
            return (posicion);
        }
   
    /**
     * Metodo que indica el lugar fisico donde se debe insertar el dato x. <br>
     * <b>post: </b> Se indica la posicion de un dato en una pagina determinada del arbol. <br>
     * @param p pagina posible a insertar. <br>
     * @param x dato a insertar. <br>
     * @return indice que indica el lugar fisico donde se debe insertar el dato.
     */
    protected int donde(Pagina p, T x){
        int i;
        i=0;
        int compara=((Comparable)p.getInfo()[i]).compareTo(x);
        while((compara<0) && (i<(p.getCont()-1))){
            i++;
            compara=((Comparable)p.getInfo()[i]).compareTo(x);
        }
        return i;
    }
    
    /**
     * Metodo que permite romper una pagina del arbol en dos, para mantener su orden. <br>
     * <b>post: </b> Se dividio una pagina del arbol en dos, para mantener sus caracteristicas. <br>
     * @param p pagina a romper. <br>
     * @param t apuntador de que ser null la pagina es una hoja del arbol. <br>
     * @param x dato a insertar en el arbol. <br>
     * @param subir contenedor de la pagina a ascender en el arbol. <br>
     * @return la nueva pagina del arbol.
     */
     private Pagina romper(Pagina p, Pagina t, T x, T[] subir){
        T[] a= (T[]) new Object[m1];
        int i = 0;
        boolean s= false;
        Pagina []b= new Pagina[this.m1+1];
        while(i<this.m && !s){
            int compara=((Comparable)p.getInfo()[i]).compareTo(x);
            if(compara<0){ //<-- X es mayor que el dato del arbol
                a[i]= (T)p.getInfo()[i];
                b[i]=p.getApuntadores()[i];
                p.getApuntadores()[i++]=null;
            }
            else
                s=true;
        }
        a[i]=x;
        b[i]=p.getApuntadores()[i];
        p.getApuntadores()[i]=null;
        b[++i]=t;
        while((i <= this.m)){
            a[i]=(T) p.getInfo()[i-1];
            b[i+1]=p.getApuntadores()[i];
            p.getApuntadores()[i++]=null;
        }
        Pagina q= new Pagina(this.n);
        inicializar(q);
        p.setCont(n);q.setCont(n);
        i=0;
        while(i < this.n){
            p.getInfo()[i]= a[i];
            p.getApuntadores()[i] = b[i];
            q.getInfo()[i]=a[i+n+1];
            q.getApuntadores()[i]=b[i+n+1];
            i++;
        }
        p.getApuntadores()[n]=b[n];
        q.getApuntadores()[n]=b[m1];
        subir[0]= a[n];
        return (q);
    }
    
     /**
     * Metodo que permite correr los apuntadores hacia la izquierda. <br>
     * <b>post: </b> Se corrieron los apuntadores de la pagina hacia la izquierda. <br>
     * @param p apuntador de la pagina a recorrer. <br>
     * @param i variable entera que indica en donde se comienza a correr. <br>
     * @param j  
     */
     protected void cizquierda_apunt(Pagina p, int i, int j){
        while(i<j){
            p.getApuntadores()[i]=p.getApuntadores()[i+1];
            i++;
        }
        p.getApuntadores()[i]=null;
    }
     
    /**
     * Metodo que permite correr los apuntadores hacia la derecha. <br>
     * <b>post: </b> Se corrieron los apuntadores de la pagina hacia la derecha. <br>
     * @param p apuntador de la pagina a recorrer. <br>
     * @param i variable entera que indica en donde se comienza a correr. <br>
     */
    protected void cderechaApunt(Pagina p, int i){
        int j;
        j= p.getCont();
        while (j>i){
            p.getApuntadores()[j] = p.getApuntadores()[j-1];
            j--;
        }
    }
    
    /**
     *  Metodo que permite realizar las modificaciones al eliminar un dato, para que el arbol conserve sus caracteristicas. <br>
     * <b>post: </b> Se cambiaron las paginas, para realizar la eliminacion de un dato. <br>
     * @param p apuntador de la pagina donde se encuentra el dato a eliminar. <br>
     * @param q pagina que contiene el apuntador a la pagina donde se encuentra el dato a eliminar. <br>
     * @param r apuntador a pagina hermana del apuntador de la pagina donde se encuentra el dato a eliminar. <br>
     * @param i posicion en la que se encuentra el dato en la pagina. <br>
     * @param x dato que se desea eliminar. <br>
     */
    protected void cambio(Pagina p, Pagina q, Pagina r, int i, T x){
        int k;
        T t ;
        int compara=((Comparable)r.getInfo()[r.getCont()-1 ]).compareTo(x);
        if(compara<0){
            t = (T)q.getInfo()[i];
            retirar(q,i);
            k=0;
            k=insertar(p,t,k);
            t= (T)r.getInfo()[r.getCont()-1];
            retirar(r, r.getCont()-1);
            k=i;
            if(k==-1)
                k=0;
            k=insertar(q,t,k);
        }
        else{
            t= (T)q.getInfo()[i];
            retirar(q,i);
            k=p.getCont()-1;
            if(k==-1)
                k=0;
            k=insertar(p,t,k);
            t= (T)r.getInfo()[0];
            retirar(r,0);
            k=i;
            if(q.getCont()!=0)
                if(k > q.getCont()-1)
                    k=q.getCont()-1;
            k=insertar(q,t,k);
        }
    }
   
    /**
     * Metodo que permite unir dos paginas, para conservar las caracteristicas del arbol B. <br>
     * <b>post: </b> Se unio dos paginas para mantener las caracteristicas de arbol B. <br>
     * @param q pagina que contiene apuntadores hacia las paginas r y p. <br> 
     * @param r pagina que tendra la la informacion de la paginas. <br>
     * @param p pagina que contiene la llave que se desea eliminar
     * @param i posicion en r donde se debe incoporar la llave que se retira de q
     * @param pi almacena el camino entre q y la raiz del arbol
     * @param x dato que se desea eliminar
     * @param posicion posicion donde se encuentra la llave que se retirada de la pagina
     */
    private void unir (Pagina q,Pagina r, Pagina p, int i, Pila<Componente> pila, T x, int posicion){
        int terminar=0,j = 0,k;
        Pagina t = null;
        Componente objeto= new Componente();
        retirar(p,posicion);
        int compara=((Comparable)r.getInfo()[0]).compareTo(x);
        if(compara>0){
            t=p;
            p=r;
            r=t;
        }
        while(terminar==0){
     /*1*/  if((r.getCont()<this.n) && (p.getCont()>this.n)){
                    cambio(r,q,p,i,x);
                    r.getApuntadores()[r.getCont()]=p.getApuntadores()[0];
                    this.cizquierda_apunt(p, 0, p.getCont()+1);
                    terminar=1;
                }
     /*2*/ else
                    if((p.getCont()<this.n) && (r.getCont()>this.n)){
                        cambio(p,q,r,i,x);
                        this.cderechaApunt(p, 0);
                        p.getApuntadores()[0]=r.getApuntadores()[r.getCont()+1];
                        r.getApuntadores()[r.getCont()+1]=null;
                        terminar=1;
                    }
                    else  {
                        j=r.getCont();
                        r.getInfo()[j++]=q.getInfo()[i]; 
                        k=0;
                        while(k<=p.getCont()-1){
                            r.getInfo()[j++]=(T)p.getInfo()[k++];
                        }
                        r.setCont(j);
                        this.retirar(q, i);
                        k=0;
                        j=this.m-p.getCont();
                        while(p.getApuntadores()[k]!=null){
                            r.getApuntadores()[j++]=p.getApuntadores()[k++];
                        }
                        p=null;
         /*3*/      if(q.getCont()==0){
                            q.getApuntadores()[i+1]=null;
                /*4*/       if(pila.esVacia()){
                                    q=null;
                            }
                    }
                    else 
                        this.cizquierda_apunt(q, i+1, q.getCont()+1);
        /*5*/  if(q!=null){
        /*6*/       if(q.getCont()>=this.n){
                            terminar=1;
                        }
                        else{
                            t=q;
       /*7*/            if(!pila.esVacia()){
                                objeto= pila.desapilar();
                                q=objeto.getP();
                                i=objeto.getV();
                                compara=((Comparable)q.getInfo()[0]).compareTo(x);
                                if(compara<=0){
                                    p=t;
                                    r=q.getApuntadores()[i-1];
                                    i=i-1;
                                }
                                else{
                                        r=t;
                                        p=q.getApuntadores()[i+1];
                                }
                        }
                        else 
                            terminar=1;
                        }
                 }
                else{
                    terminar=1;
                    this.setRaiz(r);
                }
            }
        }
   }   
     
    /**
     * Metodo que retorna un iterador con las hojas del Arbol B. <br>
     * <b>post: </b> Se retorno un iterador con las hojas del Arbol B. <br>
     * @return Un objeto Iterador con las hojas del Arbol B.
     */
    public Iterator<T> getHojas(){
        ListaCD<T> l=new ListaCD();
        getHojas(this.raiz, l);
        return (l.iterator());
    }

    /**
     * Metodo de tipo privado que retorna un iterador con las hojas del Arbol B. <br>
     * <b>post: </b> Se retorno un iterador con las hojas del Arbol B.<br>
     * @param r representa la raiz del arbol, o raiz de subarbol. <br>
     * @param l Lista para el almacenamiento de los datos del arbol. <br>
     */
    private void getHojas(Pagina<T> r, ListaCD<T> l){
        if(r==null)
            return;
        if(this.esHoja(r)){
            for(int j=0; j<r.getCont();j++)
                l.insertarAlFinal(r.getInfo()[j]);
        } 
        for(int i=0; i<r.getCont()+1;i++){
             getHojas(r.getApuntadores()[i],l);
        }         
    }
    
    /**
     * Metodo que retorna un iterador con las hojas del Arbol B. <br>
     * <b>post: </b> Se retorno un iterador con las hojas del Arbol B. <br>
     * @return Un objeto Iterador con las hojas del Arbol B.
     */
    public int contarHojas(){
        return contarHojas(this.raiz);
    }

    /**
     * Metodo de tipo privado que retorna un iterador con las hojas del Arbol B. <br>
     * <b>post: </b> Se retorno un iterador con las hojas del Arbol B.<br>
     * @param r representa la raiz del arbol, o raiz de subarbol. <br>
     * @param l Lista para el almacenamiento de los datos del arbol. <br>
     */
    private int contarHojas(Pagina<T> r){
        if(r==null)
            return 0;
        int cont=0;
        if(this.esHoja(r))
            cont++;
        for(int i=0; i<r.getCont()+1;i++){
             cont+=contarHojas(r.getApuntadores()[i]);
         }      
        return (cont);
    }
    
    
    
    /**
     * Metodo que permite determinar si una pagina es hoja. <br>
     * <b>post: </b> Se determino si una pagina es hoja. <br>
     * @param p pagina a determinar si es hoja. <br>
     * @return true en caso de que la pagina sea hoja.
     */
    protected  boolean esHoja(Pagina p){
        int j=0;
        while ((p.getApuntadores()[j]==null) && (j < (p.getCont()-1)))
            j++;
        return(p.getApuntadores()[j]==null);
    }
    
    /**
     * Metodo que permite conocer si un Arbol B se encuenta vacio. <br>
     * <b>post: </b> Se evaluo si el Arbol B se enecuenta o no vacio. <br>
     * @return Un boolean, true si el Arbol se encuenta vacio, false en caso contrario
     */
    public boolean esVacio(){
        return (this.raiz==null);
    }
    

     /**
     * Metodo que permite obtener el peso del Arbol B. <br>
     * <b>post: </b> Se retorno el numero de elementos en el Arbol B.<br>
     * @return Un entero con la cantidad de elementos del Arbol B.
     */
    public int getPeso(){
        if(this.esVacio())
             return (0);
        return(getPeso(this.getRaiz()));
    }

    /**
     * Metodo de tipo privado que permite conocer el numero de elemento del Arbol B. <br>
     * <b>post: </b> Se retorno el numero de elementos en el Arbol.<br>
     * @param r Representa la raiz del Arbol, o raiz de subarbol. <br>
     * @return El numero de elementos que contiene el Arbol B.
     */
    private int getPeso(Pagina<T> r){
        if(r==null)
            return 0;
        int cont=0;
        cont+= r.getCont();
        for(int i=0; i<r.getCont()+1;i++){
             cont+=getPeso(r.getApuntadores()[i]);
         }      
        return (cont);
    }
    
     /**
     * Metodo que permite obtener la altura del Arbol B. <br>
     * <b>post: </b> Se retorno la altura del Arbol B.<br>
     * @return Un entero con la altura del Arbol B.
     */
    public int getAltura(){
        return(getAltura(this.getRaiz()));
    }

    /**
     * Metodo de tipo privado que permite conocer la altura del Arbol B. <br>
     * <b>post: </b> Se retorno la altura del Arbol B. <br>
     * @param r Representa la raiz del Arbol, o raiz de subarbol. <br>
     * @return Un entero con la altura del Arbol B.
     */
    private int getAltura(Pagina<T> r){
       int alt=0;
          while(r!=null){
              alt++;
              r=r.getApuntadores()[0];
          }
          return (alt);
    }
    
    /**
     * Metodo que permite imprimir el arbol B. <br>
     * <b>post: </b> Se imprimio los datos que contiene el arbol. <br>
     * @return 
     */
    public String imprime(){
        String msg="";
        return (this.imprime(this.raiz,msg));
    }
    
     /**
     * Metodo que permite imprimir el arbol B. <br>
     * <b>post: </b> Se imprimio los datos que contiene el arbol. <br>
     * @param r raiz o pagina no hoja del arbol B
     */
     private String  imprime(Pagina r,String msg){
        int i=0;
       while(i<=r.getCont()){
            msg+=r.toString()+"  pagina = "+i+"   ES ="+r.getApuntadores()[i].toString()+"\n" ;
            if(!this.esHoja(r.getApuntadores()[i]))
                 msg+=this.imprime(r.getApuntadores()[i],msg);
            i++;
       }
       return msg;
    }

    /**
     * Metodo que permite clonar la informacion de un Arbol B. <br>
     * @return Un nuevo ArbolB con la informacion del Arbol duplicada. <br>
     */
    public ArbolB<T> clonar() {
        ArbolB<T> clon = new ArbolB<T>(this.getN());
        if(raiz==null)
            return (clon);
        clon.setRaiz(clonar(this.raiz));
        return (clon);
    }

    private Pagina clonar(Pagina r) {
        if(r==null)
            return (null);
        else
        {   
            T info[] = (T[]) new Object[r.getM()];
            for(int i=0; i<r.getCont();i++){
                info[i] = (T) r.getInfo()[i];
            }
            Pagina aux = new Pagina(r.getN());
            aux.setInfo(info);
            aux.setCont(r.getCont());
            for(int i=0; i<aux.getCont()+1;i++){
                aux.getApuntadores()[i] = clonar(r.getApuntadores()[i]);
            }
            return (aux);
        }
    }
    
    /**
     *  Metodo que retorna un iterador con el recorrido preOrden del Arbol. <br>
     * <b>post: </b> Se retorno un iterador en preOrden para el arbol.<br>
     * @return un iterador en preorden para el Arbol AVL.
     */
    public Iterator<T> preOrden(){
        ListaCD<T> l=new ListaCD();
        preOrden(this.raiz, l);
        return (l.iterator());
    }
    
    private void preOrden(Pagina<T> r, ListaCD<T> l){
        if(r==null)
            return;
        for(int j=0; j<r.getCont();j++)
            l.insertarAlFinal(r.getInfo()[j]);
        for(int i=0; i<r.getCont()+1;i++){
             preOrden(r.getApuntadores()[i],l);
        }         
    }
    
    /**
     * Metodo que retorna un iterador con el recorrido in Orden del Arbol. <br>
     * <b>post: </b> Se retorno un iterador inOrden para el arbol.<br>
     * @return un iterador en inOrden para el Arbol AVL. <br>
     */
    public Iterator<T> inOrden(){
        ListaCD<T> l=new ListaCD();
        inOrden(this.raiz, l);
        return (l.iterator());
    }
    
    private void inOrden(Pagina<T> r, ListaCD<T> l){
        if(r==null)
            return;
        inOrden(r.getApuntadores()[0],l);
        for(int j=0; j<r.getCont();j++)
            l.insertarAlFinal(r.getInfo()[j]);
        for(int i=1; i<r.getCont()+1;i++){
             inOrden(r.getApuntadores()[i],l);
        }         
    }
    
   /**
     * Metodo que retorna un iterador con el recorrido postOrden del Arbol. <br>
     * <b>post: </b> Se retorno un iterador preOrden para el arbol.<br>
     * @return un iterador en postOrden para el Arbol AVL. <br>
     */
    public Iterator<T> postOrden(){
        ListaCD<T> l=new ListaCD();
        postOrden(this.raiz, l);
        return (l.iterator());
    }
    
    private void postOrden(Pagina<T> r, ListaCD<T> l){
        if(r==null)
            return;
        for(int i=0; i<r.getCont()+1;i++){
             postOrden(r.getApuntadores()[i],l);
        }
        for(int j=0; j<r.getCont();j++)
            l.insertarAlFinal(r.getInfo()[j]);
    }
    
    /**
     * Metodo que permite retornar un iterador con el recorrido por niveles del Arbol. <br>
     * <b>post: </b> Se retorno el recorrido por niveles del Arbol AVL.<br>
     * @return un un iterador con el recorrido por niveles del Arbol AVL.
     */
    public Iterator<T> impNiveles(){
        ListaCD<T> l=new ListaCD<T>();
        if(!this.esVacio()){
            Cola<Pagina<T>> c=new Cola<Pagina<T>>();
            c.enColar(this.getRaiz());
            Pagina<T> x;
                while(!c.esVacia()){
                    x=c.deColar();
                    if(x!=null){
                        for(int i=0; i<x.getCont();i++)
                            l.insertarAlFinal(x.getInfo()[i]);
                        for(int j=0;j<x.getCont()+1;j++)
                            c.enColar(x.getApuntadores()[j]);  
                    }                 
                }
        }
        return (l.iterator());
    }
    
    /**
     * Metodo que permite podar las paginas Hoja de un Arbol B.
     */
    public void podar() {
        if(this.esHoja(raiz))
            this.setRaiz(null);
        podar(this.raiz);
    }

    private void podar(Pagina<T> r) {
        if (r==null)
            return;
        for(int i=0; i<r.getCont()+1; i++){
            Pagina apunt = r.getApuntadores()[i];
            if(this.esHoja(apunt))
                r.getApuntadores()[i]=null;
        }
        for(int j=0; j<r.getCont()+1;j++){
            if(r.getApuntadores()[j]!=null){
                podar(r.getApuntadores()[j]);
            }
        }
        
    }
    
    
}//Fin de la Clase Arbol B
