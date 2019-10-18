/**
 * ---------------------------------------------------------------------
 * $Id: Vertice.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;



/**
 * Implementacion de Clase para el manejo de los Vertices o Nodos de un Grafo; <br>
 * Representa la unidad fundamental de la que estan formados los grafos. <br>
 * @param <T> Tipos de datos que se almacenan en el Vertice. 
 * @author Uriel García
 * @version 1.0
 */
public class Vertice<T> 
{
    
    ////////////////////////////////////////////////////////////
    // Vertice - Atributos /////////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Informacion almacenada en el Vertice
     */
    private T info;
    
    /**
     * Listado de Vertices adyacentes al actual
     */
    private ListaCD<Vertice> vecinos;
    
    /**
     * Representa el Vertice predecesor en un recorrido
     */
    private Vertice predecesor;
    
    /**
     * Representa un valor que indica si el Vertice ha sido visitado;
     * True=visitado, False=No visitado
     */
    private boolean esVisit;
    
    
    
    ///////////////////////////////////////////////////////////
    // Vertice - Implementacion de Metodos ////////////////////
    ///////////////////////////////////////////////////////////

    /**
     * Metodo constructor que permite crear un nuevo Vertice con al informacion presente en el mismo. <br>
     * <b>post: </b> Se construyo un nuevo Vertice con la informacion ingresada.
     * @param info Objeto de tipo T que representa la informacion del Vertice.
     */
    public Vertice(T info) {
        this.info = info;
        this.vecinos = new ListaCD<Vertice>();
        this.esVisit = false;
        this.predecesor = null;
    }

    /**
     * Obtiene la información del Vertice. <br>
     * <b>post: </b> Se retorno la informacion del Vertice.<br>
     * @return Un objeto tipo T con la informacion del Vertice.
     */
    public T getInfo() {
        return info;
    }
    
    /**
     * Obtiene el Listado de Vertices adyacentes/vecinos al Vertice inidicado. <br>
     * <b>post: </b> Se retorno el Listado de Vertices vecinos del vertice seleccionado<br>
     * @return Una ListaCD cada uno de los Vertices adyacentes al Vertice indicado.
     */
    public ListaCD<Vertice> getVecinos() {
        return vecinos;
    }
    
    /**
     * Obtiene el Vertice predecesor en el recorrido al vertice actual. <br>
     * <b>post: </b> Se retorno un Vertice con el predecesor del Vertice actual. <br>
     * @return Un Vertice que representa el predecesor del vertice actual.
     */
    public Vertice getPredecesor() {
        return predecesor;
    }
    
    /**
     * Metodo que permite evaular si un Vertice ha sido visitado en un recorrido. <br>
     * <b>post: </b> Se retorno un boolean que evalua si el Vertice ha sido visitado. <br>
     * @return Un Objeto de tipo boolean que evalua si el Vertice ha sido visitado
     */
    public boolean getVisit() {
        return esVisit;
    }
    
    /**
     * Metodo que permite modificar la informacion presente en el Vertice. <br>
     * <b>post: </b> Se edito la informacion del Vertice. <br>
     * @param info Representa la nueva Informaciond el Vertice.
     */
    public void setInfo(T info) {
        this.info = info;
    }
    
    /**
     * Metodo que permite editar el listado de Vertices adyacentes al Vertice actual. <br>
     * <b> post: </b> Se edito el listado de Vertices adyacentes al Vertice actual. <br>
     * @param vec Representa el nuevo listado de Vertices adyacentes al Vertice actual. <br>
     */
    public void setVecinos(ListaCD<Vertice> vec) {
        this.vecinos = vec;
    }
    
    /**
     * Metodo que permite editar el Vertice predecesor al Vertice actual en el recorrido. <br>
     * <b> post: </b> Se edito el Vertice predecesor el Vertice actual en el recorrido. <br>
     * @param predecesor Representa el Vertice predecesor al Vertice actual. <br>
     */
    public void setPredecesor(Vertice predecesor) {
        this.predecesor = predecesor;
    }
    
    /**
     * Metodo que permite marcar la visita de un Vertice dentro de un recorrido. <br>
     * <b> post: </b> Se marco la visita de un Vertice con un true o false. <br>
     * @param esVisit Es de tipo boolean y representa true=visitado, false=No visitado.
     */
    public void setVisit(boolean esVisit) {
        this.esVisit = esVisit;
    }
    
    /**
     * Metodo que permite evaluar si dos Vertices son iguales, comparando sus informaciones. <br>
     * <b> post: </b> Se retorno un valor booleano para evaluar la igualdad de los Vertices. <br>
     * @param v Representa el segundo Vertice en comparacion. <br>
     * @return Un objeto de tipo boolean que evalua la igualdad de la informacion de los Vertices.
     */
    public boolean equals(Vertice v){
        return (this.info.equals(v.getInfo()));
    }
    
    /**
     * Metodo que permite insertar un Vertice adyacente/vecino al Vertice actual. <br>
     * <b> post: </b> Se inserto un Vertice vecino al Vertice actual. <br>
     * @param v Representa el Vertice que sera insertado como vecino del Vertice actual.
     */
    public void insertarVecino(Vertice v){
        this.vecinos.insertarAlFinal(v);
    }
    
    /**
     * Metodo que permite eliminar un Vertice adyacente al Vertice actual. <br>
     * <b> post: </b> Se elimino un Vertice vecino del Vertice actual. <br>
     * @param v Un objeto de tipo Vertice que representa al Vertice a eliminar.
     */
    public void eliminarVecino(Vertice v){
        ListaCD<Vertice> l = new ListaCD<Vertice>();
        for(Vertice vert: this.vecinos){
            if(!vert.equals(v))
                l.insertarAlFinal(vert);
        }
        this.vecinos = l;
    }
    
    /**
     * Metodo que permite evaluar si un Vertice es adyacente a otro Vertice indicado. <br>
     * <b> post: </b> Se evaluo la adyacencia de dos vertices dentro del Grafo. <br>
     * @param v Un Objeto de tipo Vertice del cual se quiere evaluar si es adyacente al actual. <br>
     * @return Un objeto de tipo boolen con el resultado de la operacion realizada.
     */
    public boolean esAdyacente(Vertice v){
        for(Vertice vert: this.vecinos)
            if(vert.getInfo().equals(v.getInfo()))
                return (true);
        return (false);
    }
    
    /**
     * Metodo que permite evaluar si un Vertice es un Vertice Aislado; Si no posee Aristas. <br>
     * <b> post: </b> Se evaluo si el Vertice se encuentra aislado de los demas Vertices <br>
     * @return Un objeto de tipo boolean que almacena un true=si es aislado y false= en caso contrario
     */
    public boolean esAisladoND() {
        for(Vertice v: this.vecinos){
            if(!v.equals(this))
                return (false);
        }
        return (true);
    }
    
    /**
     * Metodo que permite evaluar si un Vertice es un Vertice Hoja; Posee una sola Arista asociada. <br>
     * <b> post: </b> Se evaluo si el Vertice es una Hoja del Grafo <br>
     * @return Un objeto de tipo boolean que almacena un true=si es hoja y false= en caso contrario
     */
    public boolean esHojaND(){
        int i=0;
        for(Vertice v: this.vecinos){
            if(!v.equals(this)){
                if(++i>1)
                    return (false);
            }                
        }
        return (i==1);
    }
    /**
     * Metodo que permite conocer el Grado de un vertice para un Grafo No Dirigido. <br>
     * @return El numero de vertices adyacentes a determinado Vertice.
     */
    public int getGradoND() {
        return (this.vecinos.getTamanio());
    }
    
    /**
     * Metodo que sirve de Interfaz del Metodo toString() del Objeto. <br>
     * @return Una cadena de String con la información del Vertice.
     */
    @Override
    public String toString(){
        return (this.info.toString());
    }

    
    
}// Fin de la Clase Vertice
