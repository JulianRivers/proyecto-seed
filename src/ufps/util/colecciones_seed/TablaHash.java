/**
 * ---------------------------------------------------------------------
 * $Id: TablaHash.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;

/**
 * Implementacion de Clase para el manejo de Tablas Hash. <br>
 * @param <Clave> 
 * @param <T> Tipo de datos a almacenar dentro de la Tabla Hash. <br>
 * @author Yulieth Pabon
 * @version 1.0
 */

public class TablaHash <Clave, T > {
    
    ////////////////////////////////////////////////////////////
    // TablaHash - Atributos ////////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * numero de datos almacenados en la tabla
     */
    private int numeroDatos;
    
    /**
     * el tamaño de slots de la tabla
     */
    private int numeroSlots;
    
    /**
     * lista de objetos a almacenar en la tabla
     */
    private ListaCD<InformacionDeEntrada <Clave,T>> [ ] informacionEntrada;

    ////////////////////////////////////////////////////////////
    // TablaHash - Implementacion de Metodos ////////////////////
    ////////////////////////////////////////////////////////////  
    
    /**
     * Constructor de una tabla hash con 23 slots. <br>
     * <b> post:</b> Se creo una tabla hash con 23 slots. <br>
     */
    public TablaHash() {
        this.numeroDatos = 0;
        this.numeroSlots = 11;
         this.informacionEntrada = new ListaCD [this.numeroSlots ];
         // inicializar los Slots de la tabla 
         this.inicializarListas( );
    }
    
    /**
     * Constructor de una tabla hash con numero Slots de la tabla espeficos. <br>
     * <b> post:</b> Se creo tabla hash con numeroSlots de la tabla especificos. <br>
     * @param numeroSlots numero que representa los slots de la tabla. <br>
     */
    public TablaHash( int numeroSlots) {
        this.numeroDatos =0;
        this.numeroSlots = numeroSlots;
        this.informacionEntrada =  new ListaCD [numeroSlots];
         // inicializar los Slots de la tabla 
         this.inicializarListas( );
    }
     /**
      * Metodo que permite insertar o modificar en la tabla un objeto con su respectiva clave. <br>
     * <b> post:</b> Se inserto o modifico un objeto en la tabla fragmentada . <br>
     * @param clave representa la clave del objeto a insertar o modificar. <br>
      * @param objeto representa el objeto a insertar en la tabla. <br>
      * @return el objeto insertado.
      */ 
    public T insertar( Clave clave, T objeto ) {
        int indice=0;
        InformacionDeEntrada<Clave,T> objetoAnterior=null;
        if(clave==null){
            throw new RuntimeException("La Clave de Objeto no puede ser vacia!!!");
        }
        else{
            indice =index(clave);
            objetoAnterior = this.registrarEntrada(indice, clave );
            if( objetoAnterior== null ){ // Si la clave del objeto no se encuentra en la tabla lo insertamos
                InformacionDeEntrada<Clave,T> nuevoObjeto = new InformacionDeEntrada( clave, objeto );
                this.informacionEntrada[ indice ].insertarAlFinal(nuevoObjeto);
                this.numeroDatos+=1;
                return nuevoObjeto.getObjeto();
            }
            else  // si la clave esta se encuentra en la tabla modificamos el objeto
                 objetoAnterior.setObjeto( objeto);
        }
            return (T)objetoAnterior.getObjeto();
    }

    /**
     * Metodo que permite eliminar un objeto entrada de la tabla fragmentada. <br>
     * <b> post:</b> Se elimino el objeto en la tabla fragmentada . <br>
     * @param clave representa la clave del objeto que se desea eliminar. <br>
     * @return  el objeto que se elimino o null en caso de que no exista en la tabla un objeto con esa clave.
     */
    public T eliminar( Clave clave ) {
        int i=0;
        InformacionDeEntrada objeto;
           if(clave==null){
               throw new RuntimeException("La Clave de Objeto no puede ser vacia!!!");
           }
           else{
                int indice =index(clave);
               ListaCD<InformacionDeEntrada<Clave,T>> listaObjeto = this.informacionEntrada[ indice ];
               objeto = new InformacionDeEntrada( clave );
               i=listaObjeto.getIndice(objeto);
                    if(i==-1)
                        objeto=null;
                    else{
                       objeto = ( InformacionDeEntrada )listaObjeto.eliminar(i);
                       this.numeroDatos--;
                    }
               }
               return (T)objeto.getObjeto();
       }
    
    /**
     * Metodo que permite conocer si se encuentra un objeto asociado con la clave dada. <br>
     * <b>post:<b> Se consulto si se encuentra un objeto asociado con la clave dada. <br>
     * @param clave dato a verificar si se encuentra en la tabla. <br>
     * @return true de encontrar un objeto asociado con la clave dada. <br>
     */
    public boolean esta(Clave clave){
        return(this.getObjeto(clave)!=null);
    }

    /**
     * Método que permite obtener el objeto asociado con la clave especificada. <br>
     * <b> post:</b> Se obtuvo el objeto de la tabla fragmentada, que posee esa clave . <br>
     * @param clave representa la clave del objeto que se desea obtener. <br>
     * @return El objeto asociado con la clave o null si no existe objeto con esa clave.
     */
     public Object getObjeto( Clave clave ) {
         InformacionDeEntrada objeto;
        if ( clave == null )
            throw new IllegalArgumentException("Clave null no permitida" );
        else{
            int indice =index(clave);
            ListaCD<InformacionDeEntrada<Clave,T>> listaObjeto = this.informacionEntrada[ indice ];
            objeto= new InformacionDeEntrada( clave );
            int i=listaObjeto.getIndice(objeto);
             if(i==-1)
                    return null;
             else{
                objeto = listaObjeto.get(i);
              }
          }
          return objeto.getObjeto();
      }
     
    /**
    * Metodo que permite registrar una entrada con la clave especificada dentro del slot indicado en la tabla. <br>
    * <b>pre: </b> clave!=null, indice!=null, indice>=0. <br>
    * <b>post: </b> Se registro la entrada en el slot de la tabla indicado. <br>
    * @param indice representa el indice de la tabla, donde se registrara la clave con su respectivo objeto. <br>
    * @param clave representa la clave del objeto a registrar en la tabla. <br>
    * @return la nueva entrada a la tabla o null de no encontrar una entrada asociada a la clave del objeto.
    */
    private InformacionDeEntrada registrarEntrada( int indice, Clave clave ){
        InformacionDeEntrada<Clave,T> objeto = new InformacionDeEntrada( clave );
        ListaCD<InformacionDeEntrada<Clave,T>> listaEntradas = this.informacionEntrada[ indice ];//Slot de la tabla donde deberia encontrarse el objeto
        int i=listaEntradas.getIndice(objeto);
        if(i==-1)
            objeto=null;
        else
            objeto = listaEntradas.get(i);
        return objeto;
    }
    
    /**
    * Metodo que permite dispersar el codigo hash de la clave especifica, para garantizar una mejor distribucion en la asignacion de las entradas. <br>
    * <b>pre: </b> hcode!=null. <br>
    * <b> post:</b> Se obtuvo el indice de asignacion de slot en la tabla para la clave de la entrada. <br>
    * @param clave 
    * @return indice de asignacion de slot en la tabla para la clave de la entrada. <br>
    */
    public int index( Clave clave ){
        int hcode=clave.hashCode();
        double num= ((Math.sqrt(5.0) - 1.0)/2.0);//numero que se utiliza para mejor distribucion de las entradas en la tabla hash.
        double t = (Math.abs( hcode ) * num);//(this.numeroDatos+1); //valor absoluto de hash de objeto 
        return ((int) ((t - (int)t) *( this.numeroSlots) ));
    }
        
    /**
     * Metodo que permite eliminar las entradas de la tabla hash. <br>
     * <b> post:</b> Se eliminaron todos los datos antes almacendos en la tabla. <br>
     */
    public void eliminarTodo(){
        this.numeroDatos = 0;
        for ( int i = 0; i < this.informacionEntrada.length; i++ )
            this.informacionEntrada[ i ] = null;
    }

    /**
     * Metodo que permite obtener el numero de objetos almacenados en la tabla hash. <br>
     * <b> post:</b> Se obtuvo el numero de objetos almacenados en la tabla hash. <br>
     * @return el numero de objetos almacenados en la tabla hash.
     */
    public int getNumeroDatos() {
        return numeroDatos;
    }

    /**
     * Metodo que permite obtener el numero de slots de la tabla hash. <br>
     * <b> post:</b> Se obtuvo el numero de slots de la tabla hash. <br>
     * @return el numero de slots de la tabla hash.
     */
    public int getNumeroSlots() {
        return numeroSlots;
    }

    /**
     * Metodo que permite obtener el listado de los objetos de entrada de la tabla hash. <br>
     * <b> post:</b> Se obtuvo el listado de los objetos de entrada de la tabla hash. <br>
     * @return el listado de los objetos de entrada de la tabla hash.
     */
    public ListaCD<InformacionDeEntrada<Clave, T>>[] getInformacionEntrada() {
        return informacionEntrada;
    }

    /**
     * Metodo que permite determinar el numero de Slots ocupados en la Tabla. <br>
     * @return El numero de slots ocupados en la tabla en un entero.
     */
    public int numSlotOcupados(){
        int i=0;
        int cant=0;
        while(i<this.numeroSlots){
            if(!this.informacionEntrada[i].esVacia())
                cant++;
                i++;
        }
        return cant;
    }
    
    /**
     * Metodo que modificar el numero de slots de la tabla hash. <br>
     * <b> post:</b> Se modifico el numero de slots de la tabla hash. <br>
     * @param numeroSlots nuevo tamanio de la tabla hash. <br>
     */
    public void setNumeroSlots(int numeroSlots) {
        this.numeroSlots = numeroSlots;
    }

     /**
     * Metodo que permite modificar el listado de los objetos de entrada de la tabla hash. <br>
     * <b> post:</b> Se modifico el listado de los objetos de entrada de la tabla hash. <br>
     * @param informacionEntrada el nuevo listado de los objetos de entrada de la tabla hash.
     */
    public void setInformacionEntrada(ListaCD<InformacionDeEntrada<Clave, T>>[] informacionEntrada) {
        this.informacionEntrada = informacionEntrada;
    }
    
    /**
     * Inicializa las listas que representan los Slots de la tabla de hashing. <br>
     * <b>pre: </b> informacionEntradas!=null. <br>
     * <b>post: </b> Se inicializaron las lista que representan los slots.
     */
    private void inicializarListas( ){
        for( int i = 0; i < this.informacionEntrada.length; i++ ){
            this.informacionEntrada[i] = new ListaCD( );
        }
    }
 
    /**
     * Metodo que permite obtener un numero primo cercano al valor dado. <br>
     * <b>post: </b> Se obtuvo  un numero primo cercano al valor dado.
     * @param numero representa el valor a obtener el numero primo mas cercano
     * @return  un numero primo cercano al valor dado.
     */
    private int obtenerPrimo( int numero ) {
        int primo = numero -1;
        while( !esPrimo( primo ) ){
            primo+=2;
        }
        return primo;
    }
    
   /**
    *  Metodo que permite verificar si el numero dado es un numero primo. <br>
    * <b>pre: </b> todos los numeros deben tener un divisor primo menor q su raiz cuadrada. <br>
    * <b>post: </b> Se verifico si el mumero dado es primo. <br>
    * @param numero valor a verificar si es primo. <br>
    * @return true si el numero es primo.
    */
    public boolean esPrimo( int numero ){
        boolean esPrimo = false;
        int raizCuadrada = ( int )Math.sqrt( numero );
        for( int i = 3; i <= raizCuadrada && !(esPrimo); i+= 2 )   {
            if( numero % i != 0 ){
                esPrimo = true;
            }
        }
        return esPrimo;
    }

    /**
     * Metodo que permite imprimir los datos almacenados en la tabla. <br>
     *  <b>post: </b> Se retorno una cadena de caracteres que representan los datos almacenados en la tabla. <br>
     * @return cadena de caracteres que representan los datos almacenados en la tabla.
     */
    public String imprimir(){
        String msg="";
         for ( int i = 0; i < this.informacionEntrada.length; i++ )
            if(this.informacionEntrada[ i ] != null)
                msg+="Slot de la tabla numero"+i+" ==>"+this.informacionEntrada[ i ].toString()+"\n";
         return msg;
    }

    /**
     * Metodo que permite determinar si la Tabla se encuentra vacía. <br>
     * @return Un objeto de tipo boolean con true si la tabla se encuentra vacia.
     */
    public boolean esVacia() {
        return (this.numeroDatos==0);
    }

}//Fin de la clase TablaHash

