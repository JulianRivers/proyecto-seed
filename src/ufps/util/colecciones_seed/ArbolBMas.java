/**
 * ---------------------------------------------------------------------
 * $Id: ArbolBMas.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;
import java.util.Iterator;

/**
 * Implementacion de Clase para el manejo de un ArbolB+.
 * @param <T> Tipo de datos a almacenar en el ArbolB+.
 * @author Yulieth Pabon
 * @version 1.0
 */

public class ArbolBMas <T> extends ArbolB{    
    
    ////////////////////////////////////////////////////////////
    // ArbolB+ - Atributos //////////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     *  apuntador hacia los datos del arbol ascendentemente
     */
    private Pagina<T> vsam;
    
   
    ////////////////////////////////////////////////////////////
    // ArbolB+ - Implementacion de Metodos //////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Crea un arbol B  vacio con orden especifico. <br>
     * <b>post: </b> Se creo un arbol B vacio con orden especifico. <br>
     */
    public ArbolBMas(){
         super();
         this.vsam=super.getRaiz();
    }
    
    /**
     * Crea un arbol B  vacio con orden especifico. <br>
     * <b>post: </b> Se creo un arbol B vacio con orden especifico. <br>
     * @param n de tipo entero que indica el numero el orden del arbol B. <br>
     */
    public ArbolBMas(int n){
         super(n);
         this.vsam=super.getRaiz();
    }

    /**
     * Metodo el apuntador hacia los datos almacenados en el arbol. <br>
     *<b>post: </b> Se retorno el apuntador hacia los datos almacenados en el arbol. <br>
     * @return el apuntador hacia los datos almacenados en el arbol. 
     */
    public Pagina<T> getVsam() {
        return vsam;
    }
   
    /**
     * Metodo que permite insertar un nuevo dato en el arbol B +. <br>
     * <b>post: </b> Se inserto un nuevo dato al arbol B+. <br>
     * @param x dato a insertar en el arbol.  <br>
     * @return la pagina donde se inserto x, o null sino se inserto correctamente
     */
     public boolean insertarBMas(T x){
        //pila para guarddar el camino desde la raiz hasta la pagina donde se inserta x
        Pila pila= new Pila();
        //Para trabajar subir y subir1 por referencia se usa si la pagina se rompe
        T []subir= (T[]) new Object[1];
        T []subir1= (T[]) new Object[1];
        //variables auxiliares 
        int posicion=0, i=0, terminar, separar;
        Pagina p = null, nuevo=null, nuevo1=null;
        
        
        if(super.getRaiz()==null){ 
            super.setRaiz(this.crearPagina(super.getN(),x));
            vsam=super.getRaiz();
            return (true);
        }               
        else{
            posicion= buscar(super.getRaiz(),x, pila);
            if(posicion==-1)
                return (false);
            else{
                terminar=separar=0;
                while((!pila.esVacia())&&(terminar==0)){
                    p= (Pagina)pila.desapilar();
                    if(p.getCont()==super.getM()){
                        if(separar==0){
                            nuevo=romper(p,null,x,subir,separar);
                            separar=1;
                        }
                    else{
                        nuevo1=romper(p,nuevo,subir[0],subir1,separar);
                        subir[0]=subir1[0];
                        nuevo=nuevo1;
                    }
                  }
                    else{
                        if(separar==1){
                            separar=0;
                            i=donde(p,subir[0]);
                            i=insertar(p, subir[0],i);
                            super.cderechaApunt(p,i+1);
                            p.getApuntadores()[i+1]= nuevo;

                        }
                        else   
                            posicion=insertar(p,x,posicion);
                        terminar=1;
                    }
                }
                if((separar==1)&&(terminar==0)){
                    this.setRaiz(this.crearPagina(super.getN(),subir[0]));
                    super.getRaiz().getApuntadores()[0]=p;
                    super.getRaiz().getApuntadores()[1]=nuevo;
                }
            }
        }
        return true;
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
            if(compara<0){
                i++;
            }
            else{
                j=p.getCont()-1;
                while(j>=i){
                         p.getInfo()[j+1]=p.getInfo()[j];
                         j=j-1;
                }
            }                
         }
            p.getInfo()[i]= x;
          p.setCont(p.getCont()+1);
          return i;
      }
     
    /**
     * Metodo que permite eliminar una dato del arbol B. <br>
     * <b>post: </b> Se elimino el dato del arbol B. <br>
     * @param x dato que se desea eliminar. <br>
     * @return el dato que se elimino o null en caso de no haberse eliminado el dato.
     */
    public boolean eliminarBMas(T x){
        int  posicion,i,k;
        T temp=null;
        Pagina p = null,q = null,r = null,t;
        Pila<Componente> pila=new Pila();
        Componente objeto= null;
        posicion=estaBMas(super.getRaiz(),x,pila);
        if(posicion==-1)
           return false;//la llave no existe en el arbol

           objeto=pila.desapilar();
            p=objeto.getP(); 
            i=objeto.getV();
            if(p.getCont()>super.getN()){
                retirar(p, posicion);
                return true;
            }
            
             if(pila.esVacia()){
                 retirar(p,posicion);
                  if(p.getCont()==0){
                        super.setRaiz(null);
                        vsam=super.getRaiz();
                    }
                  return true;
             }
                    objeto=(Componente)pila.desapilar();
                    q=objeto.getP();//q pagina que contiene el puntero de la pagina donde esta el dato
                    i=objeto.getV();
                    if(i<q.getCont()){
                        r=q.getApuntadores()[i+1];
                        if(r.getCont()>super.getN()){
                            retirar(p, posicion);
                            temp=(T) r.getInfo()[0];
                            retirar(r, 0);
                            retirar(q, i);
                            k=donde(p, temp);
                            insertar(p, temp, k);
                             k=donde(q, (T)r.getInfo()[0]);
                            insertar(q, (T)r.getInfo()[0], k);
                             return true;
                        }
                        }
                    if(i>0){
                        r=q.getApuntadores()[i-1];
                        if(r.getCont()>super.getN()){
                            retirar(p, posicion);
                            temp=(T) r.getInfo()[r.getCont()-1];
                            retirar(r, r.getCont()-1);
                            retirar(q, i-1);
                            k=this.donde(p, temp);
                            insertar(p, temp, k);
                            k=this.donde(q, temp);
                            insertar(q, temp,k);
                             return true;
                          }
                        }
                    if(i>0)
                        i--;
                     unirBMas(q, r, p, i, pila, x, posicion);    
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
    private Pagina crearPagina(int n,T x){
        Pagina p= new Pagina(n);
        inicializar(p);
        p.setCont(1);
        p.getInfo()[0]=(x);
        return p;
    }
    
     /**
      * Metodo que permite inicializar una pagina. <br>
     * <b>post: </b> Se inicializo una pagina en el arbol. <br>
      * @param p apuntador a una pagina aun si inicializar. <br>
      */
    private void inicializar(Pagina p){
        int i =0;
        p.setCont(0);
        while(i < super.getM1())
            p.getApuntadores()[i++] = null;
    }
    
    /**
     * Metodo que permite evaluar la existencia un dato dentro del Arbol B+. <br>
     * <b>post: </b> Se evaluo la existencia de un dato dentro del Arbol. <br>
     * @param dato Representa el dato que se quiere localizar dentro del Arbol Eneario. <br>
     * @return Un objeto de tipo boolean que contiene un true si ubico el dato y false en caso contrario.
     */
    public boolean estaBMas(T dato){
        Pila< Componente> pi=new Pila();
        return(this.estaBMas(super.getRaiz(), dato, pi)!=(-1));
    }
    /**
     * Metodo que permite determinar si un elemento se encuentra en el arbol. <br>
     * <b>post: </b>  Se evaluo la existencia de un dato dentro del Arbol. <br>
     * @param p pagina que contiene las paginas de la busqueda. <br>
     * @param x posicion del apuntador de las paginas. <br>
     * @param pi estructura que almacenara el camino de la busqueda de X. <br>
     * @return posicion de X dentro de la pagina donde se encontro, de no ser asi retorna -1.
     */
       private int estaBMas(Pagina p, T x,Pila<Componente> pi){
            int i=0;
            boolean encontro=false;
            int posicion=-1;
            while((p!=null)&&(!encontro)){
                i=0;
               int compara=((Comparable)p.getInfo()[i]).compareTo(x);
                while((compara<0) && (i<(p.getCont()-1))){
                    i++;
                    compara=((Comparable)p.getInfo()[i]).compareTo(x);
                }
                if((compara>0)){
                    pi.apilar(new Componente(p,i));
                    p=p.getApuntadores()[i];
                }
                else 
                    if((compara<0)){
                         pi.apilar(new Componente(p,i+1));
                         if(p.getApuntadores()[0]!=null)
                            p=p.getApuntadores()[i+1];
                        else 
                             p=null;
                    }
                    else{
                        if(p.getApuntadores()[0]!=null){
                            pi.apilar(new Componente(p,i+1));
                            p=p.getApuntadores()[i+1];
                        }
                        else{
                        pi.apilar(new Componente(p,i));       
                        encontro=true;
                        }                         
                    }                
            }
                if(encontro){
                    posicion=i;
                }
            return posicion;
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
            int i=0;
            boolean encontro = false;
            int posicion = -1;
            while((p!=null)&&(!encontro)){               
                pila.apilar(p);
                i=0;
                int compara=((Comparable)p.getInfo()[i]).compareTo(x);
                while((compara<0)&&(i<(p.getCont()-1))){
                    i++;
                 compara=((Comparable)p.getInfo()[i]).compareTo(x);
                }                    
                 if((compara>0))
                    p=p.getApuntadores()[i];
                else
                    if(compara<0)
                        if(p.getApuntadores()[0]!=null)
                            p = p.getApuntadores()[i+1];
                 else
                            p=null;
                    else if(p.getApuntadores()[0]!=null)
                                p = p.getApuntadores()[i+1];
                 else
                        encontro = true;
            }
            if(!encontro)
                posicion = i;            
            return posicion;
        }
      
    /**
     * Metodo que permite romper una pagina del arbol en dos, para mantener su orden. <br>
     * <b>post: </b> Se dividio una pagina del arbol en dos, para mantener sus caracteristicas. <br>
     * @param p pagina a romper. <br>
     * @param t apuntador de que ser null la pagina es una hoja del arbol. <br>
     * @param q nueva pagina. <br>
     * @param x dato a insertar en el arbol. <br>
     * @param subir contenedor de la pagina a ascender en el arbol. <br>
     * @param separar variable que ajusta el ultimo apuntador. <br>
     * @return la nueva pagina del arbol.
     */
     private Pagina romper(Pagina p, Pagina t, T x, T[] subir, int separar){
        T[] a= (T[]) new Object[super.getM1()];
        int i = 0;
        boolean s= false;
        Pagina []b= new Pagina[super.getM1()+1];
        Pagina temp=null;
        
        if(separar==0){
            temp=p.getApuntadores()[super.getM()];
            p.getApuntadores()[super.getM()]=null;
        }
        
        while((i<super.getM())&&(!s)){
            int compara=((Comparable)p.getInfo()[i]).compareTo(x);
            if(compara<0){ //<-- X es mayor que el dato del arbol
                a[i]= (T) p.getInfo()[i];
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
        
        while((i <= super.getM())){
            a[i]=(T)( p.getInfo()[i-1]);
            b[i+1]=p.getApuntadores()[i];
            p.getApuntadores()[i++]=null;
        }
        
        Pagina q= new Pagina(super.getN());
        inicializar(q);
        i=0;
        if(separar==0){
            p.setCont(super.getN());
            q.setCont(super.getN()+1);
            q.getInfo()[0]=a[super.getN()];
            
            while(i<super.getN()){
                p.getInfo()[i]=a[i];
                p.getApuntadores()[i]=b[i];
                q.getInfo()[i+1]=a[i+super.getN()+1];
                q.getApuntadores()[i]=b[i+super.getN()+1];
                i++;
            }
            q.getApuntadores()[super.getM()]=temp;
            p.getApuntadores()[super.getM()]=q;
        }
        else{
            p.setCont(super.getN());q.setCont(super.getN());
            
            while(i < super.getN()){
                p.getInfo()[i]= a[i];
                p.getApuntadores()[i]=b[i];
                q.getInfo()[i]=a[i+super.getN()+1];
                q.getApuntadores()[i]=b[i+super.getN()+1];
                i++;
            }
        }
        p.getApuntadores()[super.getN()]=b[super.getN()];
        q.getApuntadores()[super.getN()]=b[super.getM1()];
        subir[0]= a[super.getN()];
        return q;
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
    private void unirBMas (Pagina q,Pagina r, Pagina p, int i, Pila pi, T x, int posicion){
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
     /*1*/  if((r.getCont()<super.getN()) && (p.getCont()>super.getN())){
                    super.cambio(r,q,p,i,x);
                    r.getApuntadores()[r.getCont()]=p.getApuntadores()[0];
                    this.cizquierda_apunt(p, 0, p.getCont()+1);
                    terminar=1;
                }
     /*2*/ else
                    if((p.getCont()<super.getN()) && (r.getCont()>super.getN())){
                        super.cambio(p,q,r,i,x);
                        this.cderechaApunt(p, 0);
                        p.getApuntadores()[0]=r.getApuntadores()[r.getCont()+1];
                        r.getApuntadores()[r.getCont()+1]=null;
                        terminar=1;
                    }
                    else  {
                        j=r.getCont();
                        if(r.getApuntadores()[0]==null) 
                            r.getApuntadores()[super.getM()]=p.getApuntadores()[super.getM()];
                        else
                            r.getInfo()[j++]=q.getInfo()[i];
                        k=0;
                        while(k<=p.getCont()-1)
                            r.getInfo()[j++]=(T)p.getInfo()[k++];
                        
                        r.setCont(j);
                        retirar(q, i);
                        k=0;
                        j=super.getM()-p.getCont();
                        
                        while(p.getApuntadores()[k]!=null)
                            r.getApuntadores()[j++]=p.getApuntadores()[k++];                        
                        p=null;
         /*3*/      if(q.getCont()==0){
                            q.getApuntadores()[i+1]=null;
        /*4*/       if(pi.esVacia()){
                            q=null;
                        }
                    }
                    else 
                        this.cizquierda_apunt(q, i+1, q.getCont()+1);
        /*5*/  if(q!=null)
        /*6*/       if(q.getCont()>=super.getN())
                            terminar=1;
                        
                        else{
                            t=q;
       /*7*/            if(!pi.esVacia()){
                                objeto=(Componente)pi.desapilar();
                                q=objeto.getP();
                                i=objeto.getV();
                                compara=((Comparable)q.getInfo()[0]).compareTo(x);
                                if(compara<=0){
                                    p=t;
                                    r=q.getApuntadores()[i-1];
                                    i--;
                                }
                                else{
                                        r=t;
                                        p=q.getApuntadores()[i+1];
                                }
                        }
                        else 
                            terminar=1;
                        }
                 
                else{
                    terminar=1;
                    super.setRaiz(r);
                }
            }
        }
   }   
     
    /**
     * Metodo que retorna un iterador con las hojas del Arbol B+. <br>
     * <b>post: </b> Se retorno un iterador con las hojas del Arbol B+. <br>
     * @return Un objeto Iterador con las hojas del Arbol B+.
     */
    @Override
    public Iterator<T> getHojas(){
        return (super.getHojas());
    }
    
    /**
     * Metodo que permite conocer si un Arbol B+ se encuenta vacio. <br>
     * <b>post: </b> Se evaluo si el Arbol B+ se enecuenta o no vacio. <br>
     * @return Un boolean, true si el Arbol se encuenta vacio, false en caso contrario
     */
    @Override
    public boolean esVacio(){
        return (super.esVacio());
    }
    
    /**
     * Metodo que retorna un iterador con el recorrido in Orden del Arbol B+. <br>
     * <b>post: </b> Se retorno un iterador inOrden para el Arbol.<br>
     * @return Un Iterador en inOrden (Primer apuntador->Primera nformacion de Pagina->Segundo apuntador. estodepende del orden del arbol) para el arbol B. <br>
     */
    @Override
    public Iterator<T> inOrden(){
        return (super.inOrden());
    }

     /**
     * Metodo que permite obtener el peso del Arbol B+. <br>
     * <b>post: </b> Se retorno el numero de elementos en el Arbol B+.<br>
     * @return Un entero con la cantidad de elementos del Arbol B+.
     */
    public int getPesoBMas(){
        return(getPesoBMas(super.getRaiz()));
    }
       /**
     * Metodo de tipo privado que permite conocer el numero de elemento del Arbol B+. <br>
     * <b>post: </b> Se retorno el numero de elementos en el Arbol.<br>
     * @param r Representa la raiz del Arbol, o raiz de subarbol. <br>
     * @return El numero de elementos que contiene el Arbol B+.
     */
    private int getPesoBMas(Pagina<T> r){
        int cant=0;
          while(r.getApuntadores()[0]!=null){
               r=r.getApuntadores()[0];
          }
            while(r!=null){
                cant+=r.getCont();
                r=r.getApuntadores()[super.getM()];
            }
        return (cant);
    }
     /**
     * Metodo que permite obtener la altura del Arbol B+. <br>
     * <b>post: </b> Se retorno la altura del Arbol B+.<br>
     * @return Un entero con la altura del Arbol B+.
     */
    @Override
    public int getAltura(){
        return(super.getAltura());
    }
    
    /**
     * Metodo que permite listar la informacion de la Lista VSAM del Arbol B+. <br>
     * @return Una cadena con la informacion de la lista VSAM. <br>
     */
    public String listar_vsam(){
        return (listar_vsam(this.vsam));
    }

    /**
     * etodo que permite listar la informacion de la Lista VSAM del Arbol B+. <br>
     * @param vsam Pagina que representa el comienzo de la lista VSAM.
     * @return Una cadena con la informacion de la lista VSAM. <br>
     */
    public String listar_vsam(Pagina vsam){
        String msg="";
        int i;
        while(vsam!=null){
            i=0;
            while(i<vsam.getCont())
                msg+=vsam.getInfo()[i++].toString()+"-->";
            vsam=vsam.getApuntadores()[super.getM()];
        }
        return msg;
    }
    
    /**
     * Metodo que permite imprimir el arbol B+. <br>
     * <b>post: </b> Se imprimio los datos que contiene el arbol. <br>
     * @return Una cadena con la informacion del Arbol B+.
     */
    @Override
    public String imprime(){
        return (super.imprime());
    }
    
    /**
     * Metodo que permite clonar la informacion de un Arbol B+. <br>
     * @return Un nuevo Arbol B+ con la informacion duplicada del Arbol.
     */
    @Override
    public ArbolBMas<T> clonar() {
        ArbolBMas<T> clon = new ArbolBMas<T>(this.getN());
        if(super.getRaiz()==null)
            return (clon);
        clon.setRaiz(clonar(super.getRaiz()));
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
     * Metodo que permite limpiar la informacion de Arbol B+. <br>
     */
    public void limpiarBMas() {
        super.setRaiz(null);
        this.vsam = super.getRaiz();
    }
    
    
}//Fin de la clase ArbolB+
