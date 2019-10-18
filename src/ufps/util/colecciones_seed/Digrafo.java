/**
 * ---------------------------------------------------------------------
 * $Id: Digrafo.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 * @author Uriel Garcia, Yulieth Pabon
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;

/**
 * Implementacion de la Clase para el manejo de Grafos Dirigidos (Digrafos);
 * Para este Grafo el conjunto de las aristas tiene una dirección definida.
 * @param <T> Tipo de Objetos que se almacenan en los Vertices y Aristas del Grafo
 * @author Uriel Garcia
 * @version 1.0
 */

public class Digrafo<T> 
{
    
    ////////////////////////////////////////////////////////////
    // Digrafo - Atributos /////////////////////////////////////
    ////////////////////////////////////////////////////////////
 
    /**
     * Representa el listado de Vertices del Digrafo
     */
    private ListaCD<Vertice> vertices;
    
    /**
     * Representa el listado de Aristas del Digrafo
     */
    private ListaCD<Arista> aristas;
    
    
    
    ////////////////////////////////////////////////////////////
    // Digrafo - Implementacion de Metodos //////////////////////
    ////////////////////////////////////////////////////////////

    /**
     * Metodo constructor del Digrafo que inicializa el Listado de Vertices y Aristas. <br>
     * <b> post: </b> Se creo un nuevo Digrafo sin Vertices y Aristas.
     */
    public Digrafo() {
        this.vertices = new ListaCD<Vertice>();
        this.aristas = new ListaCD<Arista>();
    }

    /**
     * Metodo que permite conocer el listado de Vertices del Grafo. <br>
     * <b> post: </b> Se retorno el listado de Vertices del Grafo. <br>
     * @return Una ListaCD con el conjunto de Vertices del Grafo.
     */
    public ListaCD<Vertice> getVertices() {
        return vertices;
    }

    /**
     * Metodo que permite conocer el listado de Aristas del Grafo. <br>
     * <b> post: </b> Se retorno el listado de Aristas del Grafo. <br>
     * @return Una ListaCD con el conjunto de Aristas del Grafo.
     */
    public ListaCD<Arista> getAristas() {
        return aristas;
    } 
    
    /**
     * Metodo que permite editar el listado de Vertices del Grafo. <br>
     * <b> post: </b> Se edito el listado de Vertices del Grafo. <br>
     * @param l Representa el nuevo listado de Vertices a reemplazar.
     */
    public void setVertices(ListaCD<Vertice> l) {
        this.vertices = l;
    }

    /**
     * Metodo que permite editar el listado de Aristas del Grafo. <br>
     * <b> post: </b> Se edito el listado de Aristas del Grafo. <br>
     * @param l Representa el nuevo listado de Aristas a reemplazar.
     */
    public void setAristas(ListaCD<Arista> l) {
        this.aristas = l;
    }
    
    /**
     * Metodo que permite insertar un nuevo Vertice dentro en el listado del Grafo. <br>
     * <b> post: </b> Se inserto un Vertice dentro del Grafo. <br>
     * @param info Representa la informacion del Vertice que se desea ingresar al Grafo. <br>
     * @return Un objeto de tipo boolean que representa el resultado de la operacion.
     */
    public boolean insertarVertice(T info){
        Vertice v = new Vertice(info);
        if(esta(v))
            return (false);
        this.vertices.insertarAlFinal(v);        
        return (true);
    }
    
    /**
     * Metodo que permite insertar una nueva Arista en el Listado del Grafo. <br>
     * <b> post: </b> Se inserto una nueva Arista dentro del Listado del Grafo. <br>
     * @param orig Representa el Vertice origen de la Arista del Grafo. <br>
     * @param dest Representa el Vertice destino de la Arista del Grafo. <br>
     * @return Un objeto de tipo boolean que representa el resultado de la operacion.
     */
    public boolean insertarArista(T orig, T dest){
        Vertice<T> o = this.buscarVertice(orig);
        Vertice<T> d = this.buscarVertice(dest);
        if(o==null || d==null)
            return (false);
        o.insertarVecino(d);
        this.aristas.insertarAlFinal(new Arista<T>(o,d,-1));
        return (true);
    }
    
    /**
     * Metodo que permite insertar una nueva Arista con peso en el Listado del Grafo. <br>
     * <b> post: </b> Se inserto una nueva Arista dentro del Listado del Grafo. <br>
     * @param orig Representa el Vertice origen de la Arista del Grafo. <br>
     * @param dest Representa el Vertice destino de la Arista del Grafo. <br>
     * @param peso Representa el peso de la Arista que se desea ingresar. <br>
     * @return Un objeto de tipo boolean que representa el resultado de la operacion.
     */
    public boolean insertarAristaP(T orig, T dest, int peso){
        Vertice<T> o = this.buscarVertice(orig);
        Vertice<T> d = this.buscarVertice(dest);
        if(o==null || d==null || peso<0)
            return (false);
        o.insertarVecino(d);
        this.aristas.insertarAlFinal(new Arista<T>(o,d,peso));
        return (true);
    }
    
    /**
     * Metodo que permite eliminar un Vertice del listado del Grafo; Se eliminan tambien sus relaciones. <br>
     * <b> post: </b> Se elimino el Vertice del grafo, incluso sus relaciones en el mismo. <br>
     * @param info Representa la informacion del Vertice que se desea eliminar. <br>
     */
    public void eliminarVertice(T info){
        ListaCD<Vertice> vaux = new ListaCD<Vertice>();
        ListaCD<Arista> aaux = new ListaCD<Arista>();        
        Vertice v = this.buscarVertice(info);
        if(v==null)
            return;
        //Eliminar todos los vertices
        for(Arista a: this.aristas){
            if(!a.getVertA().equals(v) && !a.getVertB().equals(v))
                aaux.insertarAlFinal(a);
        }
        //Actualizado el listado de aristas
        this.aristas = aaux;
        for(Vertice vert : this.vertices){
            if(!vert.equals(v))
                vaux.insertarAlFinal(vert);
            else
            vert.eliminarVecino(v);
        }
        //Actualizo el listado de Vertices
        this.vertices = vaux;
    }
  
    /**
     * Metodo que permite eliminar una Arista dentro del Grafo. <br>
     * <b> post: </b> Se elimino la Arista del Grafo. <br>
     * @param orig Representa la informacion del Vertice origen de la Arista. <br>
     * @param dest Representa la informacion del Vertice destino de la Arista. <br>
     * @return  
     */
    public boolean eliminarArista(T orig, T dest){
        ListaCD<Arista> aaux = new ListaCD<Arista>();        
        Vertice v1 = this.buscarVertice(orig);
        Vertice v2 = this.buscarVertice(dest);
        if(v1==null || v2==null)
            return false;
        //Eliminar todos los vertices
        for(Arista a: this.aristas){
            Vertice vOrig = a.getVertA();
            Vertice vDest = a.getVertB();
            boolean es = vOrig.equals(v1)&&vDest.equals(v2);
            if(!es)
                aaux.insertarAlFinal(a);
            else{
                v1.eliminarVecino(v2);
            }
        }
        this.aristas = aaux;     
        return (true);
    }
    
    /**
     * Metodo que permite buscar un Vertice dentro del listado de Vertices en el Grafo. <br>
     * <b> post: </b> Se retorno el Vertice consultado dentro del Listado. <br>
     * @param info Representa la informacion del Vertice consultado. <br>
     * @return Un Objeto de tipo Vertice que representa el Vertice consultado. <br>
     */
    public Vertice buscarVertice(T info){
        for(Vertice v: this.vertices){
            if(v.getInfo().equals(info))
                return (v);
        }
        return (null);
    }
    
    /**
     * Metodo que permite buscar una Arista dentro del listado de Aristas en el Grafo. <br>
     * <b> post: </b> Se retorno la Arista consultada dentro del Listado. <br>
     * @param info1 Representa la informacion del Vertice en un extremo de la Arista. <br>
     * @param info2 Representa la informacion del Vertice en un extremo de la Arista. <br>
     * @return Un Objeto de tipo Arista que representa la Arista consultada. <br>
     */
    public Arista buscarArista(T info1, T info2){
        for(Arista a : this.aristas){
            if(a.equalsD(new Arista(new Vertice(info1),new Vertice(info2),-1)))
                return (a);
        }
        return (null);
    }
    
    /**
     * Metodo que permite evaluar la existencia de un Vertice dentro del Grafo. <br>
     * <b> post: </b> Se evaluo la existencia de un Vertice dentro del Grafo. <br>
     * @param x Representa el Vertice que se desea consultar. <br>
     * @return Un Objeto de tipo boolean que representa el resultado de la operacion.
     */
    public boolean esta(Vertice x){
        for(Vertice v: this.vertices){
            if(v.equals(x))
                return (true);
        }
        return (false);
    }
    
    ////////////////////////////////////////////////////////////
    // Digrafo - Representacion de los Grafo ///////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Metodo que permite conocer la matriz de adyacencia del Grafo, que es una de sus representaciones; <br>
     * La primera fila y columna de la matriz representan la informacion de los Vertices, por lo que no posee valores. <br>
     * <b> post: </b> Se retorno la representacion de la matriz de adyacencia del Grafo. <br>
     * @return Una matriz de Object con la representacion del Grafo en su matriz de Adyacencia.
     */
    public Object[][] getMatrizAdyacencia(){       
        Object m[][] = new Object[this.vertices.getTamanio()+1][this.vertices.getTamanio()+1];    
        //Coloco los datos
        int k=1;
        for(Vertice v: this.vertices){
            m[0][k] = v.getInfo().toString();
            m[k++][0] = v.getInfo().toString();            
        }   
        for(int i=1; i<m[0].length; i++)
            for(int j=1; j<m.length; j++)
                m[i][j]=0;
        //Creo la relacion entre los vertices
        m[0][0] = 0;
        for(Arista a: this.aristas){
            int i=vertices.getIndice(a.getVertA())+1;
            int j=vertices.getIndice(a.getVertB())+1;              
            m[i][j]= ((Integer) m[i][j])+1;   
        }  
        return (m);
    }
    
    /**
     * Metodo que permite conocer la Lista de Adyacencia de Grafo; Es una mas de sus representacione; <br>
     * Se ha implementado de manera que se represente atravez de un Arreglo de ListasCD de Vertices. <br>
     * <b> post: </b> Se retorno la lista de Adyacencias del Grafo. <br>
     * @return Un Objecto de tipo Array de Listas (ListaCD[]) que representa el grado como Lista de Adyacencias.
     */
    public ListaCD[] getListaAdyacencia(){
        ListaCD lad[]= new ListaCD[this.vertices.getTamanio()];
        int i=0;
        while(i<this.vertices.getTamanio()){
            Vertice v = this.vertices.get(i);
            int j=0;
            ListaCD<Vertice<T>> l = new ListaCD<Vertice<T>>();
            l.insertarAlFinal(v);
            while(j<v.getVecinos().getTamanio()){
                l.insertarAlFinal((Vertice<T>) v.getVecinos().get(j++));
            }
            lad[i++]=l;
        }
        return (lad);
    }
    
    /**
     * Metodo que permite conocer la Matriz de Incidencia del Grafo; Otra de sus representaciones; <br>
     * La primera fila y columna representan la informacion de los Vertices y Aristas, debe tenerse en cuenta. <br>
     * <b> post: </b> Se retorno el Grafo representado por su matriz de Incidencia. <br>
     * @return Una matriz de Object con la representacion del Grafo en su matriz de Incidencia.
     */
    public Object[][] getMatrizIncidencia(){       
        Object m[][] = new Object[this.vertices.getTamanio()+1][this.aristas.getTamanio()+1];    
        //Coloco los datos de los vertices
        int k=1;
        for(Vertice v: this.vertices){
            m[k++][0] = v.getInfo().toString();           
        }   
        k = 1;
        //Coloco el peso de las aristas
        for(Arista a: this.aristas){
            m[0][k] = "e"+k; 
            k++;
        }
        for(int i=1; i<m.length; i++)
            for(int j=1; j<m[0].length; j++)
                m[i][j]=0;
        //Creo la relacion entre los vertices
        m[0][0] = 0;
        k=1;
        for(Arista a: this.aristas){
            int i=vertices.getIndice(a.getVertA())+1;
            int j=vertices.getIndice(a.getVertB())+1;
            if(i==j)
                m[i][k++]= 2;
            else{
                m[i][k]= 1;
                m[j][k++]= -1;  
            }  
        }  
        return (m);
    }
    
    ////////////////////////////////////////////////////////////
    // Digrafo - Tipos de Grafos ///////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Metodo que permite evaluar si el Grafo es un Multigrado; es decir, si existen mas de <br>
     * una relacion entre dos Vertices A y B. <br>
     * <b> post: </b> Se evaluo si el Grafo creado es un Multigrafo. <br>
     * @return Un objeto de tipo boolean con el resultado de la operacion.
     */
    public boolean esMultigrafo(){
        Object m[][] = this.getMatrizAdyacencia();
        for(int i=1; i<m[0].length;i++){
            for(int j=1; j<m.length;j++){
                if(i!=j && ((Integer)m[i][j])>1)
                    return(true);
            }
        }
        return (false);
    }
    
    /**
     * Metodo que permite evaluar si el Grafo es un PseudoGrafo; es decir, si existe una<br>
     * relacion entre el mismo Vertice. <br>
     * <b> post: </b> Se evaluo si el Grafo creado es un Pseudografo.<br>
     * @return Un objeto de tipo boolean con el resultado de la operacion.
     */
    public boolean esPseudoGrafo(){
        Object m[][] = this.getMatrizAdyacencia();
        int i=0;
        while(i<m.length){
            if(((Integer) m[i][i++])!=0)
                return (true);
        }
        return (false);
    }
    
    /**
     * Metodo que permite conocer si un Grafo evaluado es SImple; Un Grafo es simple cuando <br>
     * No es un Pseudografo ni un Multigrafo. <br>
     * <b>post:</b> Se evaluo si el Grafo creado es un Grafo Simple. <br>
     * @return Un objeto de tipo boolean con el resultado de la operacion.
     */
    public boolean esDigrafoSimple(){
        return (!this.esMultigrafo()&&!this.esPseudoGrafo());
    }
    
    /**
     * Metodo que permite conocer si un Grafo es Ponderado; Un Grafo es ponderado cuando <br>
     * sus Aristas poseen un valor que representa costos, tiempos, distancias. <br>
     * <b>post:</b> Se evaluo si el Grafo creado es un Grafo Ponderado. <br>
     * @return Un objeto de tipo boolean con el resultado de la operacion.
     */
    public boolean esGrafoPonderado() {
        for(Arista a: this.aristas){
            if(a.getPeso()==-1)
                return (false);
        }
        return (true);
    }
    
    /**
     * Metodo que permite conocer si un Grafo es Nulo; Un Grafo es Nulo cuando <br>
     * posee una cantidad de Vertices que no se encuentran relacionados NINGUNO, Para el caso <br>
     * se dice que el Arbol es N(n), donde n es el numero de Vertices.
     * <b>post:</b> Se evaluo si el Grafo creado es un Grafo NUlo. <br>
     * @return Un objeto de int con el numero de vertices NULOS, o -1 si no es NULO
     */
    public int esGrafoNulo(){
        if(!this.aristas.esVacia())
            return (-1);
        return (this.vertices.getTamanio());
    }
    
    /**
     * Metodo que permite conocer si un Grafo es Conexo; Un Grafo es conexo cuando <br>
     * se puede acceder a cada uno de sus vertices de alguna forma. <br>
     * <b>post:</b> Se evaluo si el Grafo creado es un Grafo Conexo. <br>
     * @return Un objeto de tipo boolean con el resultado de la operacion.
     */
    public boolean esConexo(){
        this.limpiaVisitasV();
        boolean x;
        Vertice v = this.vertices.get(0);
        v.setVisit(true);
        this.visitarVecinos(v);
        x = this.visitadosTodos();
        this.limpiaVisitasV();
        return (x);
    }
    
    /**
     * Metodo de tipo privado que permite visitar los Vecinos de un Vertice y marcalos como visitados. <br>
     * @param v Representa el Vertice del cual se quieren visitar sus vecinos. <br>
     */
    private void visitarVecinos(Vertice v){        
        ListaCD l = v.getVecinos();
        for(Object obj : l){
            Vertice v2 = (Vertice) obj;
            v2 = this.buscarVertice((T) v2.getInfo());
            if(v2!=null && !v2.getVisit()){
                v2.setVisit(true);
                if(this.visitadosTodos())
                    return;
                this.visitarVecinos(v2);   
            }                   
        }           
    }
    
    /**
     * Metodo de tipo privado que permite evaluar si todos los vertices del Grafo han sido visitados. <br>
     * @return Un objeto de tipo boolean qeu es true= si ha visitado todos los Vertices en el recorrido.
     */
    private boolean visitadosTodos(){
        for(Vertice v : this.vertices)
            if(!v.getVisit())
                return (false);
        return (true);
    }
    
    /**
     * Metodo de tipo privado que permite limpiar la visita de los Vertices dentro de un recorrido. <br>
     */
    private void limpiaVisitasV(){
        for(Vertice v : this.vertices)
            v.setVisit(false);
    }
    
    /**
     * Metodo de tipo privado que permite limpiar la visita de las Aristas dentro de un recorrido. <br>
     */
    private void limpiaVisitasA(){
        for(Arista a : this.aristas)
            a.setVisit(false);
    }
    
    /**
     * Metodo que permite conocer si un Grafo es Fuertemente Conexo; Un Grafo es Fuertemente conexo cuando <br>
     * se puede acceder a cada uno de sus vertices desde cada uno de ellos. <br>
     * <b>post:</b> Se evaluo si el Grafo creado es un Grafo Fuertemente Conexo. <br>
     * @return Un objeto de tipo boolean con el resultado de la operacion.
     */
    public boolean esFuertementeConexo(){
        for(Vertice v1: this.vertices){
            for(Vertice v2: this.vertices){
                if(!v1.equals(v2) && !this.existeRutaEntre((T)v1.getInfo(),(T)v2.getInfo()))
                return (false);
            }
        }
        return (true);
    }
    
    /**
     * Metodo que permite conocer si un Grafo es Regular; Un Grafo es Regular cuando <br>
     * el grado de todos sus vertices el mismo. <br>
     * <b>post:</b> Se evaluo si el Grafo creado es un Grafo Regular. <br>
     * @return Un objeto de tipo boolean con el resultado de la operacion.
     */
    public boolean esGrafoRegular(){ 
        if(this.esMultigrafo() || this.esPseudoGrafo())
            return (false);
        int grado = -2;
        for(Vertice v: this.vertices){
            if(grado==-2)
                grado = this.getGradoEntradaVert((T) v.getInfo()) +  this.getGradoSalidaVert((T) v.getInfo());
            if(grado!= (this.getGradoEntradaVert((T) v.getInfo()) +  this.getGradoSalidaVert((T) v.getInfo())))
                return (false);
        }    
        return (true);
    }
    
    /**
     * Metodo que permite conocer si un Grafo es Completo; Un Grafo es Completo cuando <br>
     * se posee una (y sola una) Arista entre cada uno de los Vertices del Grafo, se representa por K(n). <br>
     * <b>post:</b> Se evaluo si el Grafo creado es un Grafo Completo. <br>
     * @return Un objeto de tipo int con el Numero de vertices del Grafo completo o -1 si no lo es.
     */
    public int esCompleto(){
        Object m[][] = this.getMatrizAdyacencia();
        for(int i=1; i<m[0].length;i++){
            for(int j=1; j<m.length;j++){
                if((i==j && ((Integer)m[i][j])!=0) || (i!=j && ((Integer)m[i][j])!=1))
                    return(-1);
            }
        }
        return (vertices.getTamanio());        
    }       

    /**
     * Metodo que permite conocer si un Grafo Dirigido es Simetrico; Un Digrafo es Simetrico cuando <br>
     * existe una Arista entre cada unos de sus vertices y en ambos sentidos; Ej: A->B, B->A. <br>
     * <b>post:</b> Se evaluo si el Digrafo creado es un Grafo Simetrico. <br>
     * @return Un objeto de tipo boolean con el resultado de la operacion.
     */
    public boolean esSimetrico(){
        Object m[][] = this.getMatrizAdyacencia();
        for(int i=1; i<m[0].length;i++){
            for(int j=1; j<m.length;j++){
                if((i==j && ((Integer)m[i][j])!=0) || (i!=j && ((Integer)m[i][j])!=(Integer)m[j][i]))
                    return(false);
            }
        }
        return (true);        
    }
    
    /**
     * Metodo que permite conocer si un Grafo es un Ciclo; Un Grafo es un ciclo cuando <br>
     * sus Vertices y Aristas forman un figura que se asemeja a un polígono de n lados; <br>
     * Se representa con C(n) donde n es el numero de Vertices. <br>
     * <b>post:</b> Se evaluo si el Grafo creado es un Grafo Ciclico. <br>
     * @return Un Objeto de tipo int que representa el numero de Vertices o -1 si no es ciclo.
     */
    public int esGrafoCiclo(){
        int c = this.vertices.getTamanio(); 
        if(c<3 || c!=this.aristas.getTamanio())
            return (-1);
        Object m[][] = this.getMatrizAdyacencia();
        for(int i=1; i<m[0].length;i++){
            for(int j=1; j<m.length;j++){
                //si es pseudografo o es multigrafo
                if((i==j && ((Integer)m[i][j])!=0) || (i!=j && ((Integer)m[i][j])>1))
                    return (-1);
                if(getGradoEntradaVert((T) vertices.get(j-1).getInfo())!=1 ||
                        getGradoSalidaVert((T) vertices.get(j-1).getInfo())!=1){
                    return (-1);
                 }             
            }
        }
        return (c);
    }
    
    ////////////////////////////////////////////////////////////
    // Digrafo - Recorrido para Grafos Conexos /////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Metodo que permite conocer si un Grafo es GrafoHamiltoniano, es decir, represente un circuito Hamiltoniano; <br>
     * Un Grafo es Hamiltoniano si existe  una sucesión de aristas adyacentes, que visita todos los vértices del grafo una sola vez. <br>
     * <b>post:</b> Se evaluo si el Grafo creado es un Grafo Hamiltoniano. <br>
     * @return Un objeto de tipo boolean con el resultado de la operacion.
     */
    public boolean esGrafoHamiltoniano(){   
        if(this.vertices.getTamanio()<3)
            return (false);
        return (!this.getCicloHamiltoniano().esVacia());
    }
    
    /**
     * Metodo que permite conocer si un Grafo es GrafoHamiltoniano, es decir, represente un circuito Hamiltoniano; <br>
     * Un Grafo es Hamiltoniano si existe  una sucesión de aristas adyacentes, que visita todos los vértices del grafo una sola vez. <br>
     * <b>post:</b> Se retorno el Ciclo Hamiltoniano del grafo. <br>
     * @return Un objeto de tipo ListaCD que representa el recorrido del Grafo Hamiltoniano.
     */
    public ListaCD<Vertice> getCicloHamiltoniano(){
        ListaCD<Vertice> l = new ListaCD<Vertice>();
        Vertice v = this.vertices.get(0);
        this.limpiaVisitasV();
        l.insertarAlInicio(v);            
        if(!getCHamiltoniano(v,l, v))
            l.eliminar(l.getTamanio()-1);
        else 
            l.insertarAlFinal(v);
        this.limpiaVisitasV();
        return (l);
    }
    
    /**
     * Metodo de tipo privado que permite generar el recorrido del Arbol Hamiltoniano. <br>
     * @param v2 Representa el Vertice sobre el cual se encuentra iterando durante el recorrido. <br>
     * @param l Representa la lista donde se almacena el recorrido del Grafo. <br>
     * @param orig Representa el vertics desde el cual comienza el recorrido. <br>
     * @return Un objeto de tipo boolean dependiendo si existe el Grafo Hamiltoniano.
     */
    private boolean getCHamiltoniano(Vertice v2, ListaCD<Vertice> l, Vertice orig){
        if(l.getTamanio()==vertices.getTamanio() && v2.esAdyacente(orig))
            return (true);        
        Vertice v = this.buscarVertice((T) v2.getInfo());
        if(v==null)
            return (false);
        v.setVisit(true);
        for(Object v3: v.getVecinos()){
            Vertice vert = (Vertice) v3;
            if(!vert.getVisit()){
                l.insertarAlFinal(vert);
                if(getCHamiltoniano(vert,l,orig))
                    return (true);
                else l.eliminar(l.getTamanio()-1);
            }
        }
        v.setVisit(false);
        return (false);
    }
    
    /**
     * Metodo que permite conocer si existe un camino Hamiltoniano en el Grafo; <br>
     * Un camino Hamiltoniano es una sucesión de aristas adyacentes, que visita todos los vértices del grafo una sola vez. <br>
     * <b>post:</b> Se evaluo si el Grafo creado posee un camino Hamiltoniano. <br>
     * @return Un objeto de tipo boolean con el resultado de la operacion.
     */
    public boolean hayCaminoHamiltoniano(){
        return (!this.getCaminoHamiltoniano().esVacia());
    }
    
    /**
     * Metodo que permite conocer si existe un camino Hamiltoniano en el Grafo; <br>
     * Un camino Hamiltoniano es una sucesión de aristas adyacentes, que visita todos los vértices del grafo una sola vez. <br>
     * <b>post:</b> Se retorno el camino Hamiltoniano del grafo. <br>
     * @return Un objeto de tipo ListaCD que representa el camino del Grafo Hamiltoniano.
     */
    public ListaCD<Vertice> getCaminoHamiltoniano(){
        ListaCD<Vertice> l = new ListaCD<Vertice>();
        this.limpiaVisitasV();
        for(Vertice v: vertices){            
            l.insertarAlFinal(v);
            if(getCamHamiltoniano(v,l))
                return (l);
            else{
                l.vaciar();
                this.limpiaVisitasV();
            }
        }
        this.limpiaVisitasV();
        return (l);
    }
    
    /**
     * Metodo que permite conocer si existe un camino Hamiltoniano en el Grafo; <br>
     * Un camino Hamiltoniano es una sucesión de aristas adyacentes, que visita todos los vértices del grafo una sola vez. <br>
     * <b>post:</b> Se evaluo si el Grafo creado posee un camino Hamiltoniano. <br>
     * @param v2 Representa el Vertice sobre el cual se esta iterando en el momento del recorrido. <br>
     * @param l Representa el listado donde se almacena el camino Hamiltoniano del Grafo. <br>
     * @return Un objeto de tipo boolean con el resultado de la operacion.
     */
    private boolean getCamHamiltoniano(Vertice v2, ListaCD<Vertice> l){
        if(l.getTamanio()==vertices.getTamanio()){
            return (true);  
        }         
        Vertice v = this.buscarVertice((T) v2.getInfo());
        if(v==null)
            return (false);
        v.setVisit(true);
        for(Object v3: v.getVecinos()){
            Vertice vert = (Vertice) v3;
            if(!vert.getVisit()){
                l.insertarAlFinal(vert);
                if(getCamHamiltoniano(vert,l))
                    return (true);
                else l.eliminar(l.getTamanio()-1);
            }
        }
        v.setVisit(false);
        return (false);
    }
    
   /**
     * Metodo que permite conocer si un Grafo es GrafoEuleriano, es decir, representa un circuito Euleriano; <br>
     * Un Grafo es Euleriano si posee un camino que recorre todas las aristas de un grafo tan solo una única vez, <br>
     * comenzando y terminando en el mismo Vertice. <br>
     * <b>post:</b> Se evaluo si el Grafo creado es un Grafo Euleriano. <br>
     * @return Un objeto de tipo boolean con el resultado de la operacion.
     */
    public boolean esGrafoEuleriano(){
        for(Vertice v : this.vertices){
            int grado = this.getGradoEntradaVert((T) v.getInfo())+this.getGradoSalidaVert((T) v.getInfo());
            if(grado%2!=0)
                return (false);
        } 
        return (!this.getCicloEuleriano().esVacia());
    }
    
    /**
     * Metodo que permite conocer si un Grafo es GrafoEuleriano, es decir, representa un circuito Euleriano; <br>
     * Un Grafo es Euleriano si posee un camino que recorre todas las aristas de un grafo tan solo una única vez, <br>
     * comenzando y terminando en el mismo Vertice. <br>
     * <b>post:</b> Se retorno el ciclo Euleriano del grafo.. <br>
     * @return Un objeto de tipo ListaCD que representa el recorrido del Grafo Euleriano.
     */
    public ListaCD<Vertice> getCicloEuleriano(){
        ListaCD<Vertice> l = new ListaCD<Vertice>();
        for(Vertice v:vertices){
            this.limpiaVisitasA();  
            l.insertarAlFinal(v);
            if( getCEuleriano(v,l))
                return (l);
            else 
                l.vaciar();
        }
        this.limpiaVisitasA();
        return (l);
    }
    
     
    /**
     * Metodo de tipo privado que permite generar el recorrido del Arbol Euleriano. <br>
     * @param v2 Representa el Vertice sobre el cual se encuentra iterando durante el recorrido. <br>
     * @param l Representa la lista donde se almacena el recorrido del Grafo. <br>
     * @return Un objeto de tipo boolean dependiendo si existe el Grafo Euleriano.
     */
    private boolean getCEuleriano(Vertice v2, ListaCD<Vertice> l){
        Vertice orig = l.get(0);
        Vertice v = this.buscarVertice((T) v2.getInfo());
        Arista ari = this.buscarArista((T) v.getInfo(), (T) orig.getInfo());
        if(v==null)
            return (false);
        if(l.getTamanio()==aristas.getTamanio() && v2.esAdyacente(orig) && ari!=null && !ari.getVisit()){
            l.insertarAlFinal(orig);
            return true;
        }
        for(Object v3: v.getVecinos())
        {
            Vertice vert = (Vertice) v3;
            Arista a = this.buscarArista((T) v.getInfo(), (T) vert.getInfo());
            //Si la Arista no ha sido visitada.
            if(!a.getVisit())
            {
                a.setVisit(true);
                l.insertarAlFinal(a.getVertB());
                if(getCEuleriano(a.getVertB(),l))
                    return true;
                else{
                    a.setVisit(false);
                    l.eliminar(l.getTamanio()-1);
                }
            }
        }
        return false;
    }
    
    /**
     * Metodo que permite conocer si existe un camino Euleriano en el Grafo; <br>
     * Un camino Euleriano posee un camino que recorre todas las aristas de un grafo tan solo una única vez. <br>
     * <b>post:</b> Se retorno el camino Euleriano del grafo. <br>
     * @return Un objeto de tipo boolean con el resultado de la operacion.
     */
    public boolean hayCaminoEuleriano(){
       int impares = 0;
        this.limpiaVisitasA(); 
       for(Vertice v : this.vertices){
           int grado = this.getGradoEntradaVert((T) v.getInfo())+this.getGradoSalidaVert((T) v.getInfo());
            if(grado%2!=0)
                impares++;
            if(impares>2)
                return (false);
        }
       if(impares!=2 && impares!=0)
           return (false);
       boolean x =  this.getCaminoEuleriano().esVacia();
        this.limpiaVisitasA(); 
       return(!x);
    }
    
    /**
     * Metodo que permite conocer si existe un camino Euleriano en el Grafo; <br>
     * Un camino Euleriano posee un camino que recorre todas las aristas de un grafo tan solo una única vez. <br>
     * <b>post:</b>  Se retorno el camino Euleriano del grafo. <br>
     * @return Un objeto de tipo ListaCD que representa el camino del Grafo Euleriano.
     */
    public ListaCD<Vertice> getCaminoEuleriano(){        
        ListaCD<Vertice> l = new ListaCD<Vertice>();
        this.limpiaVisitasA(); 
        for(Vertice v : vertices)
        {
            this.limpiaVisitasA();            
            l.insertarAlFinal(v);
            if(getCamEuleriano(v,l))
                return (l);
            else l.vaciar();
        }
        this.limpiaVisitasA(); 
        return (l);
    }
    
    /**
     * Metodo que permite conocer si existe un camino Euleriano en el Grafo; <br>
     * Un camino Euleriano posee un camino que recorre todas las aristas de un grafo tan solo una única vez. <br>
     * <b>post:</b> Se evaluo si el Grafo creado posee un camino Euleriano. <br>
     * @param v2 Representa el Vertice sobre el cual se esta iterando en el momento del recorrido. <br>
     * @param l Representa el listado donde se almacena el camino Euleriano del Grafo. <br>
     * @return Un objeto de tipo boolean con el resultado de la operacion.
     */
    private boolean getCamEuleriano(Vertice v2, ListaCD<Vertice> l){
        Vertice v = this.buscarVertice((T) v2.getInfo());
        if(v==null)
            return (false);
        if((l.getTamanio()-1)==aristas.getTamanio()){
            return true;
        }
        for(Object v3: v.getVecinos())
        {
            Vertice vert = (Vertice) v3;
            Arista a = this.buscarArista((T) v.getInfo(), (T) vert.getInfo());
            //Si la Arista no ha sido visitada.
            if(!a.getVisit())
            {
                a.setVisit(true);
                l.insertarAlFinal(a.getVertB());
                if(getCamEuleriano(a.getVertB(),l))
                    return true;
                else{
                    a.setVisit(false);
                    l.eliminar(l.getTamanio()-1);
                }
            }
        }
        return false;
    }
    
    /**
     * Metodo que permite evaluar la existencia de un camino entre dos vertices del Grafo Conexo. <br>
     * <b> post: </b> Se evaluo la existencia de una ruta entre dos vertices. <br>
     * @param orig Representa el vertice en el orgen de la ruta a consultar. <br>
     * @param dest Representa el vertice en el destino de la ruta a consultar. <br>
     * @return Un objeto de tipo boolean que es true si existe ruta entre los vertices.
     */
    public boolean existeRutaEntre(T orig, T dest){
        this.limpiaVisitasV();   
        Vertice v1 = this.buscarVertice(orig);
        Vertice v2 = this.buscarVertice(dest);
        if(v1==null || v2==null)
            return (false);
        boolean rta = existeRuta(v1,v2);
        this.limpiaVisitasV();            
        return (rta);
    }
    
    /**
     * Metodo de tipo privado que permite evaluar la existencia de un camino entre dos vertices del Grafo Conexo. <br>
     * <b> post: </b> Se evaluo la existencia de una ruta entre dos vertices. <br>
     * @param orig Representa el vertice en el orgen de la ruta a consultar. <br>
     * @param dest Representa el vertice en el destino de la ruta a consultar. <br>
     * @return Un objeto de tipo boolean que es true si existe ruta entre los vertices.
     */
    private boolean existeRuta(Vertice orig, Vertice dest){
        if(orig.esAdyacente(dest))
            return (true);        
        Vertice v = this.buscarVertice((T) orig.getInfo());
        if(v==null)
            return (false);
        v.setVisit(true);
        for(Object v3: v.getVecinos()){
            Vertice vert = (Vertice) v3;
            if(!vert.getVisit()){
                if(existeRuta(vert,dest))
                    return (true);
            }
        }
        v.setVisit(false);
        return (false);
    }
    
    /**
     * Metodo que permite devolver el camino entre dos vertices del Grafo Conexo. <br>
     * <b> post: </b> Se retorno la ruta entre dos Vertices de un Grafo conexo. <br>
     * @param orig Representa el vertice en el orgen de la ruta a consultar. <br>
     * @param dest Representa el vertice en el destino de la ruta a consultar. <br>
     * @return La ruta entre los dos vertices indicados, representados en una Lista.
     */
    public ListaCD<Vertice> getRutaEntre(T orig, T dest){
        ListaCD<Vertice> l = new ListaCD<Vertice>();
         this.limpiaVisitasV();   
        Vertice v1 = this.buscarVertice(orig);
        Vertice v2 = this.buscarVertice(dest);
        if(v1==null || v2==null)
            return (l);
        l.insertarAlInicio(v1);            
        if(!getRuta(v1,v2,l))
            l.eliminar(l.getTamanio()-1);
        else 
            l.insertarAlFinal(v2);
        this.limpiaVisitasV();
        return (l);
    }
    
    /**
     * Metodo de tipo privado que permite devolver el camino entre dos vertices del Grafo Conexo. <br>
     * <b> post: </b> Se retorno la ruta entre dos Vertices de un Grafo conexo. <br>
     * @param orig Representa el vertice en el orgen de la ruta a consultar. <br>
     * @param dest Representa el vertice en el destino de la ruta a consultar. <br>
     * @param l Representa la ruta entre los dos Vertices indicados. <br>
     * @return La ruta entre los dos vertices indicados, representados en una Lista.
     */
    private boolean getRuta(Vertice orig, Vertice dest, ListaCD<Vertice> l){
       if(orig.esAdyacente(dest))
            return (true);        
        Vertice v = this.buscarVertice((T) orig.getInfo());
        if(v==null)
            return (false);
        v.setVisit(true);
        for(Object v3: v.getVecinos()){
            Vertice vert = (Vertice) v3;
            if(!vert.getVisit()){
                l.insertarAlFinal(vert);
                if(getRuta(vert,dest,l))
                    return (true);
                else l.eliminar(l.getTamanio()-1);
            }
        }
        v.setVisit(false);
        return (false);
    }
    
    /**
     * Metodo que permite conocer la longitud en numero de Nodos de camino entre dos vertices del Grafo Conexo. <br>
     * <b> post: </b> Se retorno la longitud de camino de la ruta entre dos Vertices de un Grafo conexo. <br>
     * @param orig Representa el vertice en el orgen de la ruta a consultar. <br>
     * @param dest Representa el vertice en el destino de la ruta a consultar. <br>
     * @return Un objeto de tipo int con la longitud de camino entre los Vertices.
     */
    public int getLongitudDeCamino(T orig, T dest){
        int longi[] = {0};
        this.limpiaVisitasV();   
        Vertice v1 = this.buscarVertice(orig);
        Vertice v2 = this.buscarVertice(dest);
        if(v1==null || v2==null)
            return (-1);          
        if(!longitudDeCam(v1,v2,longi))
            return (-1);
        this.limpiaVisitasV();
        return (longi[0]);
    }
    
    /**
     * Metodo que permite conocer la longitud en numero de Nodos de camino entre dos vertices del Grafo Conexo. <br>
     * <b> post: </b> Se retorno la longitud de camino de la ruta entre dos Vertices de un Grafo conexo. <br>
     * @param orig Representa el vertice en el orgen de la ruta a consultar. <br>
     * @param dest Representa el vertice en el destino de la ruta a consultar. <br>
     * @param longi Representa las longitud de camino para cada uno de los Vertices. <br>
     * @return Un objeto de tipo int con la longitud de camino entre los Vertices.
     */
    private boolean longitudDeCam(Vertice orig, Vertice dest, int longi[]){
       if(orig.esAdyacente(dest)){
           longi[0]++; 
           return (true);        
       }
        Vertice v = this.buscarVertice((T) orig.getInfo());
        if(v==null)
            return (false);
        v.setVisit(true);
        for(Object v3: v.getVecinos()){
            Vertice vert = (Vertice) v3;
            if(!vert.getVisit()){
                longi[0]++;
                if(longitudDeCam(vert,dest,longi))
                    return (true);
                else longi[0]--;
            }
        }
        v.setVisit(false);
        return (false);
    }
    
    /**
     * Metodo que permite conocer la longitud ponderada de camino entre dos vertices del Grafo Conexo. <br>
     * <b> post: </b> Se retorno la longitud de camino de la ruta entre dos Vertices de un Grafo conexo. <br>
     * @param orig Representa el vertice en el orgen de la ruta a consultar. <br>
     * @param dest Representa el vertice en el destino de la ruta a consultar. <br>
     * @return Un objeto de tipo int con la longitud de camino entre los Vertices.
     */
    public int getLongitudPonderadaDeCamino(T orig, T dest){
        if(!esGrafoPonderado())
            return (-1);
        int longi[] = {0};
        this.limpiaVisitasV();   
        Vertice v1 = this.buscarVertice(orig);
        Vertice v2 = this.buscarVertice(dest);
        if(v1==null || v2==null)
            return (-1);          
        if(!longitudPondeDeCam(v1,v2,longi))
            return (-1);
        this.limpiaVisitasV();
        return (longi[0]);
    }
    
    /**
     * Metodo que permite conocer la longitud en sumatoria del peso de aristas de camino entre dos vertices del Grafo Conexo. <br>
     * <b> post: </b> Se retorno la longitud de camino de la ruta entre dos Vertices de un Grafo conexo. <br>
     * @param orig Representa el vertice en el orgen de la ruta a consultar. <br>
     * @param dest Representa el vertice en el destino de la ruta a consultar. <br>
     * @param longi Representa las longitud de camino para cada uno de los Vertices. <br>
     * @return Un objeto de tipo int con la longitud de camino entre los Vertices.
     */
    private boolean longitudPondeDeCam(Vertice orig, Vertice dest, int longi[]){
       if(orig.esAdyacente(dest)){ 
           longi[0] = (Integer)longi[0] + ((Integer)this.buscarArista((T)orig.getInfo(),(T)dest.getInfo()).getPeso()); 
           return (true);        
       }
        Vertice v = this.buscarVertice((T) orig.getInfo());
        if(v==null)
            return (false);
        v.setVisit(true);
        for(Object v3: v.getVecinos()){
            Vertice vert = (Vertice) v3;
            if(!vert.getVisit()){
                longi[0] = (Integer)longi[0] + ((Integer)this.buscarArista((T)v.getInfo(),(T)vert.getInfo()).getPeso()); 
                if(longitudPondeDeCam(vert,dest,longi))
                    return (true);
                else 
                    longi[0] = longi[0] - ((Integer) this.buscarArista((T)v.getInfo(),(T)vert.getInfo()).getPeso()); 
            }
        }
        v.setVisit(false);
        return (false);
    }
    
    /**
     * Metodo que permite calcular el peso de la ruta minima entre dos Vertices indicados. <br>
     * <b> post:</b> Se retorno la longitud de la ruta minima entre dos Vertices. <br>
     * @param ini Representa el Vertice inicial en el recorrido del Dijsktra. <br>
     * @param fin Representa el Vertice final en el recorrido del Dijsktra. <br>
     * @return Un objeto de tipo int con el valor de la longitud de la ruta Minima.
     */    
    public int longRutaMinimaDijkstra(T ini, T fin){
        if(this.hayPesosNegativosONullos())
            return (-1);
        this.limpiaVisitasV();
        int costos[] = new int[vertices.getTamanio()];
        Vertice vIni = this.buscarVertice(ini);
        Vertice vFin = this.buscarVertice(fin);
        if(vIni==null || vFin==null)
            return (-1);  
        vIni.setVisit(true);              
        this.dijkstra(vIni,vFin,costos);
        return (costos[vertices.getIndice(vFin)]);
    }
    
    /**
     * Metodo que permite calcular el peso de la ruta minima entre dos Vertices indicados. <br>
     * <b> post:</b> Se retorno la longitud de la ruta minima entre dos Vertices. <br>
     * @param vIni Representa el Vertice inicial en el recorrido del Dijsktra. <br>
     * @param vFin Representa el Vertice final en el recorrido del Dijsktra. <br>
     * @param costos Representa los costos minimos de cada vertice en el recorrido. <br>
     */ 
    private void dijkstra(Vertice vIni, Vertice vFin, int costos[]) {        
        if(vIni.equals(vFin)){
            return;            
        }
        for(Object v: vIni.getVecinos()){
            Vertice vert = (Vertice)v;
            Vertice orig = this.buscarVertice((T) vert.getInfo());
            int p = costos[vertices.getIndice(vIni)];
            int c = costos[vertices.getIndice(orig)];
            int pesoAri = ((Integer) buscarArista((T) vIni.getInfo(), (T) orig.getInfo()).getPeso());
            if(!orig.getVisit()){
                orig.setVisit(true);
                if(c==0 || c>(p+pesoAri)){
                    costos[vertices.getIndice(orig)] = (p+pesoAri);
                }
                dijkstra(orig,vFin,costos);
                orig.setVisit(false);
            }
        }
        
    }
    
    /**
     * Metodo de tipo privado que permite calcular el peso de la ruta minima entre dos Vertices indicados. <br>
     * <b> post:</b> Se retorno la ruta minima entre dos Vertices. <br>
     * @param ini Representa el Vertice inicial en el recorrido del Dijsktra. <br>
     * @param fin Representa el Vertice final en el recorrido del Dijsktra. <br>
     * @return Un objeto de tipo int con el valor de la longitud de la ruta Minima.
     */
    public ListaCD<Vertice> rutaMinimaDijkstra(T ini, T fin){
        ListaCD<Vertice> l = new ListaCD<Vertice>();
        if(this.hayPesosNegativosONullos())
            return (l);
        this.limpiaVisitasV();
        int costos[] = new int[vertices.getTamanio()];
        Vertice vIni = this.buscarVertice(ini);
        Vertice vFin = this.buscarVertice(fin);
        if(vIni==null || vFin==null)
            return (l);  
        vIni.setVisit(true);              
        this.dijkstra(vIni,vFin,costos,l);
        //Aqui deberia hacer el recorrido invertido
        vFin = this.buscarVertice(fin);
        while(vFin!=null){
            l.insertarAlInicio(vFin);
            vFin = vFin.getPredecesor();
        }
        this.limpiarPredecesores();
        return (l);
    }
    
    /**
     * Metodo que permite calcular el peso de la ruta minima entre dos Vertices indicados. <br>
     * <b> post:</b> Se retorno la ruta minima entre dos Vertices. <br>
     * @param vIni Representa el Vertice inicial en el recorrido del Dijsktra. <br>
     * @param vFin Representa el Vertice final en el recorrido del Dijsktra. <br>
     * @param costos Representa los costos minimos de cada vertice en el recorrido. <br>
     * @param l Representa el recorrido del camino minimo entre dos vertices. <br>
     */ 
    private void dijkstra(Vertice vIni, Vertice vFin, int costos[], ListaCD<Vertice> l) {        
        if(vIni.equals(vFin)){
            return ;            
        }
        for(Object v: vIni.getVecinos()){
            Vertice vert = (Vertice)v;
            Vertice orig = this.buscarVertice((T) vert.getInfo());
            int p = costos[vertices.getIndice(vIni)];
            int c = costos[vertices.getIndice(orig)];
            int pesoAri = ((Integer) buscarArista((T) vIni.getInfo(), (T) orig.getInfo()).getPeso());
            if(!orig.getVisit()){
                orig.setVisit(true);
                if(c==0 || c>(p+pesoAri)){                    
                    costos[vertices.getIndice(orig)] = (p+pesoAri); 
                    orig.setPredecesor(vIni);
                }
                dijkstra(orig,vFin,costos,l);
                orig.setVisit(false);
            }
        }
    }

    /**
     * Metodo de tipo privado que permite evaluar la existencia de Aristas con peso negativo. <br>
     * @return Un objeto de tipo boolean con true si existen aristas con pesos negativos. <br>
     */
    private boolean hayPesosNegativosONullos() {
        for(Arista a: this.aristas){
            if(a.getPeso()==-1 || (a.getPeso()!=-1&&((Integer)a.getPeso())<0))
                return (true);
        }
        return (false);
    }
    
    /**
     * Metodo que permite calcular la ruta mas corta entre dos Vertices indicados. <br>
     * <b> post:</b> Se retorno la ruta mas corta entre dos Vertices. <br>
     * @param ini Representa el Vertice inicial en el recorrido. <br>
     * @param fin Representa el Vertice final en el recorrido. <br>
     * @return Un objeto de tipo int con el valor de la longitud de la ruta mas corta.
     */
    public int rutaMasCorta(T ini, T fin){
        this.limpiaVisitasV();
        int cant[] = new int[vertices.getTamanio()];
        Vertice vIni = this.buscarVertice(ini);
        Vertice vFin = this.buscarVertice(fin);
        if(vIni==null || vFin==null)
            return (-1);   
        vIni.setVisit(true);             
        int tam =1;
        this.rutaMasCorta(vIni,vFin,cant,tam);
        return (cant[vertices.getIndice(vFin)]);
    }

    /**
     * Metodo de tipo privado que permite retornar la longitud de la ruta mas corta entre 2 vertices. <br>
     * @param vIni Representa el Vertice inicial del recorrido. <br>
     * @param vFin Representa el Vertice final o destino final del recorrido. <br>
     * @param cant Representa la cantidada de iteraciones realizadas sobre e Grafo. <br>
     * @param tam Representa el tamaño la ruta minima entre los 2 vertices.
     */
    private void rutaMasCorta(Vertice vIni, Vertice vFin, int cant[], int tam) {        
        tam++;
        if(vIni.equals(vFin)){
            return;            
        }
        for(Object v: vIni.getVecinos()){
            Vertice vert = (Vertice)v;
            Vertice orig = this.buscarVertice((T) vert.getInfo());
            int c = cant[vertices.getIndice(orig)];
            if(!orig.getVisit()){
                orig.setVisit(true);
                if(c==0)
                    cant[vertices.getIndice(orig)]=tam;
                else 
                if(c==0 || c>tam){
                    cant[vertices.getIndice(orig)] = tam;
                }
                rutaMasCorta(orig,vFin,cant,tam);
                orig.setVisit(false);
            }
        }
        
    }
    
    /**
     * BUSQUEDA EN PROFUNDIDAD
     * Metodo que permite realizar el recorrido del Grafo en Profunidad. <br>
     * <b>post:</b> Se realizo un recorrido del Grafo en profundidad. <br>
     * @param infoVert Representa la informacion del Vertices inicial sobre el cual comienza el recorrido. <br>
     * @return Un objeto de tipo ListaCD con el recorrido del Grafo en profundidad.
     */    
    public ListaCD<Vertice> getBEP(T infoVert)
    {
        ListaCD<Vertice> l = new ListaCD<Vertice>();
        Vertice v = this.buscarVertice(infoVert);
        if(v==null)
            return (l);        
        this.limpiaVisitasV();        
        l.insertarAlFinal(v);
        v.setVisit(true);
        getBEP(v,l);
        return (l);
    }
    
    /**
     * Metodo de tipo privado que permite realizar el recorrido del Grafo en Profunidad. <br>
     * <b>post:</b> Se realizo un recorrido del Grafo en profundidad. <br>
     * @param v Representa el Vertice inicial sobre el cual comienza el recorrido. <br>
     * @param l Representa el listado con el recorrido del Grafo en Profundidad.
     */
    public void getBEP(Vertice v, ListaCD<Vertice> l){
        for(Object v2: v.getVecinos())
        {
            Vertice vert = (Vertice) v2;            
            if(!vert.getVisit())
            {
                l.insertarAlFinal(vert);
                vert.setVisit(true);
                getBEP(vert,l);
            }
        }
    }
    
    /**
     * BUSQUEDA EN ANCHURA
     * Metodo que permite realizar el recorrido del Grafo en Anchura. <br>
     * <b>post:</b> Se realizo un recorrido del Grafo en Anchura. <br>
     * @param infoVert Representa la informacion del Vertices inicial sobre el cual comienza el recorrido. <br>
     * @return Un objeto de tipo ListaCD con el recorrido del Grafo en Anchura.
     */ 
    public ListaCD<Vertice> getBEA(T infoVert)
    {
        ListaCD<Vertice> l = new ListaCD<Vertice>();
        Cola<Vertice> c = new Cola<Vertice>();
        Vertice v = this.buscarVertice(infoVert);
        if(v==null)
            return (l);        
        this.limpiaVisitasV();        
        l.insertarAlFinal(v);
        v.setVisit(true);
        getBEA(v,l,c);
        return (l);
    }
    
    /**
     * Metodo de tipo privado que permite realizar el recorrido del Grafo en Anchura. <br>
     * <b>post:</b> Se realizo un recorrido del Grafo en Anchura. <br>
     * @param v Representa el Vertice inicial sobre el cual comienza el recorrido. <br>
     * @param l Representa el listado con el recorrido del Grafo en Anchura
     * @param c Representa una cola auxiliar como ayuda para el recorrido en Anchura
     */
    public void getBEA(Vertice v, ListaCD<Vertice> l, Cola<Vertice> c){
        if(v==null)
            return;
        for(Object v2: v.getVecinos())
        {
            Vertice vert = (Vertice) v2;            
            if(!vert.getVisit())
            {
                l.insertarAlFinal(vert);
                c.enColar(vert);
                vert.setVisit(true);                
            }
        }
        getBEA(c.deColar(),l,c);
    }
    
    ////////////////////////////////////////////////////////////
    // Digrafo - Otras funcionalidades /////////////////////////
    ////////////////////////////////////////////////////////////
    
    
    /**
     * Metodo que permite conocer si un Grafo se encuentra o no vacio. <br>
     * <b>post:</b> Se evaluo la existencia de datos dentro del Grafo. <br>
     * @return Un objeto de tipo boolean con true si el Grafo se encuentra vacio.
     */
    public boolean esVacio(){
        return (this.vertices.esVacia());
    }
    
    /**
     * Metodo que permite conocer si dos vertices son adyacentes/vecinos. <br>
     * @param orig Representa el Vertice del cual se desea saber si es adyacente. <br>
     * @param dest Representa el Vertice del cual se desea saber si es adyacente. <br>
     * @return Un objeto de tipo boolean que evalua la adyacencia de los dos Vertices.
     */
    public boolean sonVerticesAdyacentes(T orig, T dest){
        Vertice<T> a = this.buscarVertice(orig);
        Vertice<T> b = this.buscarVertice(dest);
        if(a==null || b==null)
            return (false);
        //Vertice a es Adyacenta a b, o es adyacente desde b.
        return (a.esAdyacente(b)||b.esAdyacente(a));
    }
    
    /**
     * Metodo que permite conocer el Grado de salida de un Vertice. <br>
     * <b>post:</b> Se retorno el grado de salida del Vertice. <br>
     * @param info Represente la informacion del Vertice a evaluar. <br>
     * @return Un objeto de tipo int con el Grado de salida del Vertice.
     */
    public int getGradoSalidaVert(T info){
        Vertice v = this.buscarVertice(info);
        if(v==null)
            return (-1);
        Object m[][] = this.getMatrizAdyacencia();
        int pos = vertices.getIndice(v)+1, i=1, grado=0;
        while(i<m.length){
            grado = grado + ((Integer) m[pos][i++]);
        }
        return (grado);
    }
    
    /**
     * Metodo que permite conocer el Grado de entrada de un Vertice. <br>
     * <b>post:</b> Se retorno el grado de entrada del Vertice. <br>
     * @param info Represente la informacion del Vertice a evaluar. <br>
     * @return Un objeto de tipo int con el Grado de entrada del Vertice.
     */
    public int getGradoEntradaVert(T info){
        Vertice v = this.buscarVertice(info);
        if(v==null)
            return (-1);
        Object m[][] = this.getMatrizAdyacencia();
        int pos = vertices.getIndice(v)+1, i=1, grado=0;
        while(i<m.length){
            grado = grado + ((Integer) m[i++][pos]);
        }
        return (grado);
    }
    
    /**
     * Metodo que permite evaluar si un Vertice se encuentra aislado dentro del Grafo. <br>
     * <b>post:</b> Se evaluo si un Vertice es Aislado en el Grafo. <br>
     * @param info Representa la informacion del Vertice que se desea evaluar. <br>
     * @return Un objeto de tipo boolean con true si el vertice es aislado.
     */
    public boolean esVerticeAislado(T info){
        Vertice vert = this.buscarVertice(info);
        if(vert==null)
            return (true);
        for(Vertice v: this.vertices){
            ListaCD<Vertice> l = v.getVecinos();
            for(Vertice v2: l){
                //Si accedido desde algun vertice
                if(!v.equals(vert) && v2.equals(vert))
                    return (false);
            }
        }
        return (true);
    }
    
    /**
     * Metodo que permite conocer los vertices adyacentes/vecinos a un vertice indicado. <br>
     * <b>post:</b> Se retorno el conjunto de Vertices vecinos al info indicado. <br>
     * @param info Representa la informacion del Vertice que se desea evaluar. <br>
     * @return Un objeto de tipo ListaCD con los vertices vecinos al Vertice indicado. 
     */
    public ListaCD<Vertice> getVecinosVertice(T info){
        Vertice v = this.buscarVertice(info);
        if(v==null)
            return (null);
        return (v.getVecinos());
    }

    /**
     * Metodo que permite limpiar el apuntador a los Vertices predecesores en el recorrido realizado. <br>
     */
    private void limpiarPredecesores() {
        for(Vertice v : this.vertices)
            v.setPredecesor(null);
    }
    
    /**
     * Metodo que permite evaluar la existencia de un circuito dentro del Grafo; Un circuito es una secuencia
     * de Vertices en la cual el vertice inicial y vertice final coinciden. <br>
     * <b>post:</b> Se evaluo la existencia de circuitos dentro del Grafo. <br>
     * @return Un boolean con true si existe un circuito dentro del Grafo.
     */
    public boolean hayCircuito(){
        for(Vertice v : this.vertices){
            if(this.existeRuta(v,v)){
                return (true);
            }
        }
        return (false);        
    }

    /**
     * Metodo que permite conocer los circuitos existentes dentro del Grafo. <br>
     * <b>post:</b> Se retorno el listado de Circuitos existentes dentro del Grafo. <br> 
     * @return Un objeto de tipo ListaCD con el conjunto de Circuitos existentes dentro del Grafo
     */
    public ListaCD<ListaCD<Vertice>> getCircuitos() {
        ListaCD<ListaCD<Vertice>> l = new ListaCD<ListaCD<Vertice>>();
        ListaCD<Vertice> l2;
        for(Vertice v : this.vertices){
            l2 = this.getRutaEntre((T)v.getInfo(), (T)v.getInfo()); 
            if(!l2.esVacia()){
                l.insertarAlFinal(l2);
            }
        }
        return (l); 
    }
    
    /**
     * Metodo que permite evaluar si una Arista es un PUENTE dentro del Grafo;  <br>
     * Un PUESTE es una Arista que eliminandose desconecta el Grafo. <br>
     * <b>post:</b> Se evaluo lo existencia de la Arista puente dentro del Grafo. <br> 
     * @param info1 Representa la informacion del primer Vertice de la Arista. <br>
     * @param info2 Representa la informacion del segundo Vertice de la Arista. <br>
     * @return Un objeto de tipo boolean con true si la Arista en un puente del Grafo.
     */
    public boolean esPuente(T info1, T info2){
        Digrafo d = this.clonar();
        Arista a = d.buscarArista(info1, info2);
        boolean rta = false;
        if(a==null)
            return (false);
        d.eliminarArista(info1, info2);
        if(!d.esConexo())
           rta = true;
        return (rta);
    }
    
    /**
     * Metodo que permite evaluar si un Vertice es una Articulacion del Grafo. <br>
     * Una Articulacion es un vertice que eliminandose desconecta el Grafo. <br>
     * <b>post:</b> Se evauo la existencia de una Aritulacion en el Grafo. <br> 
     * @param info Representa la informacion del Vertice que se quiere evaluar como Articulacion. <br>
     * @return Un objeto de tipo boolean con true si el Vertice evaluado es una Articulacion.
     */
    public boolean esArticulacion(T info){
        Digrafo d = this.clonar();
        Vertice v = d.buscarVertice(info);
        boolean rta = false;
        if(v==null)
            return (false);
        d.eliminarVertice(info);
        if(!d.esConexo())
           rta = true;        
        return (rta);
    }
    
    /**
     * Metodo que permite clonar la informacio de un Grafo en un nuevo grafo y retornarlo. <br>
     * <b>post:</b> Se realizo la clonacion de la informacion del Grafo en un nuevo Grafo. <br> 
     * @return Un nuevo Digrafo que representa el grafo con la informacion clonada.
     */
    public Digrafo clonar(){
        Digrafo nuevo = new Digrafo();
        nuevo.setVertices(this.getVertices());
        nuevo.setAristas(this.getAristas());
        return (nuevo);
    }
    
    /**
     * Metodo que permite conocer si un Grafo es un subgrafo de un Grafo mas grande. <br>
     * <b>post:</b> Se evaluo si un grafo hace parte minima de otro grafo. <br> 
     * @param g Representa el grafo el cual se desea saber si es un subgrafo de otro grafo. <br>
     * @return Un objeto boolean con true si es subgrafo y false en caso contrario.
     */
    public boolean esSubGrafo(Digrafo g){
        for(Object v2 : g.getVertices()){
            Vertice v = (Vertice)v2;
            if(!this.vertices.esta(v))
                return (false);
        }
        for(Object a2 : g.getAristas()){
            Arista a = (Arista)a2;
            if(!this.aristas.esta(a))
                return (false);
        }
        return (true);
    }
    
    /**
     * Metodo que permite unir la informaicon de 2 grafos en un solo grafo. <br>
     * <b>post:</b> Se realizo la union de dos grafos en un Grafo unico con toda la informacion. <br> 
     * @param g Representa el Grafo el cual se desea ser unido al Grafo actual. <br>
     */
    public void unirGrafos(Digrafo g){
        for(Object v2 : g.getVertices()){
            Vertice v = (Vertice)v2;
            if(!this.vertices.esta(v))
                this.insertarVertice((T) v.getInfo());
        }
        for(Object a2 : g.getAristas()){
            Arista a = (Arista)a2;
            if(!this.aristas.esta(a))
                this.insertarAristaP((T)a.getVertA().getInfo(), (T)a.getVertB().getInfo(),a.getPeso());
        }
    }
    
    /**
     * Metodo que permite conocer el peso del Grafo. <br>
     * <b>post:</b> Se retorno el peso del Grafo. <br> 
     * @return Un valor int con el peso del Grafo.
     */
    public int getPeso(){
        return (this.vertices.getTamanio());
    }
    
    @Override
    public String toString(){
        String cad = "Vertices: ";
        for(Vertice v:this.vertices){
            cad+="("+v.getInfo()+"),";
        }
        cad+="\nAristas:";
        for(Arista a:this.aristas){
            cad+=a.toString()+",";
        }
        return (cad);
    }
    
}// Fin de la Clase Digrafo - Grafo Dirigido