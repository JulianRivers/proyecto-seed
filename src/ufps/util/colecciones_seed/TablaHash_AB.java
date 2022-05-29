package ufps.util.colecciones_seed;

public class TablaHash_AB<Clave, T> extends TablaHash{

    private int numeroDatos;

    int numeroSlots;

    ArbolBinarioBusqueda<InformacionDeEntrada <Clave,T>> [] informacionEntrada;

    public TablaHash_AB() {
        this.numeroDatos = 0;
        this.numeroSlots = 11;
        this.informacionEntrada = new ArbolBinarioBusqueda[ this.numeroSlots ];
        // inicializar los Slots de la tabla
        this.inicializarAB( );
    }

    private void inicializarAB() {
            for( int i = 0; i < this.informacionEntrada.length; i++ ){
                this.informacionEntrada[i] = new ArbolBinarioBusqueda<>( );
            }
    }

    @Override
    public T insertar(Object clave, Object objeto ) {
        int indice=0;
        InformacionDeEntrada<Clave,T> objetoAnterior=null;
        if(clave==null){
            throw new RuntimeException("La Clave de Objeto no puede ser vacia!!!");
        } else{
            indice =index(clave);
            objetoAnterior = this.registrarEntrada(indice, (Clave) clave);
            if( objetoAnterior== null ){ // Si la clave del objeto no se encuentra en la tabla lo insertamos
                InformacionDeEntrada<Clave,T> nuevoObjeto = new InformacionDeEntrada( clave, objeto );
                this.informacionEntrada[ indice ].insertar(nuevoObjeto);
                this.numeroDatos+=1;
                return nuevoObjeto.getObjeto();
            }
            else  // si la clave esta se encuentra en la tabla modificamos el objeto
                objetoAnterior.setObjeto((T) objeto);
        }
        return (T)objetoAnterior.getObjeto();
    }

    private InformacionDeEntrada registrarEntrada( int indice, Clave clave ){
        InformacionDeEntrada<Clave,T> objeto = new InformacionDeEntrada( clave );
        ArbolBinarioBusqueda<InformacionDeEntrada<Clave, T>> listaEntradas = this.informacionEntrada[ indice ];//Slot de la tabla donde deberia encontrarse el objeto
        if(!listaEntradas.esta(objeto)) objeto=null;
        else objeto = listaEntradas.buscar(objeto).getInfo();
        return objeto;
    }

    @Override
    public int index( Object clave ){
        int hcode=clave.hashCode();
        double num= ((Math.sqrt(5.0) - 1.0)/2.0);//numero que se utiliza para mejor distribucion de las entradas en la tabla hash.
        double t = (Math.abs( hcode ) * num);//(this.numeroDatos+1); //valor absoluto de hash de objeto
        return ((int) ((t - (int)t) *( this.numeroSlots) ));
    }

    @Override
    public T eliminar(Object clave ) {
        boolean i=false;
        InformacionDeEntrada objeto;
        if(clave==null){
            throw new RuntimeException("La Clave de Objeto no puede ser vacia!!!");
        }
        else{
            int indice =index(clave);
            ArbolBinarioBusqueda<InformacionDeEntrada<Clave, T>> listaObjeto = this.informacionEntrada[ indice ];
            objeto = new InformacionDeEntrada( clave );
            if(!listaObjeto.esta(objeto))
                objeto=null;
            else{
                i = listaObjeto.eliminar(objeto);
                this.numeroDatos--;
            }
        }
        return (T)objeto.getObjeto();
    }

    public Object getObjeto(Object clave ) {
        InformacionDeEntrada objeto;
        if ( clave == null )
            throw new IllegalArgumentException("Clave null no permitida" );
        else{
            int indice =index(clave);
            ArbolBinarioBusqueda<InformacionDeEntrada<Clave, T>> listaObjeto = this.informacionEntrada[ indice ];
            objeto= new InformacionDeEntrada( clave );
            boolean i=listaObjeto.esta(objeto);
            if(i==false)
                return null;
            else{
                objeto = (InformacionDeEntrada) listaObjeto.buscar(objeto).getInfo();
            }
        }
        return  objeto.getObjeto();
    }

    public String imprimir(){
        String msg="";
            for (int i = 0; i < this.informacionEntrada.length; i++)
                if (!this.informacionEntrada[i].esVacio())
                    msg += "Slot de la tabla numero" + i + " ==>" + this.informacionEntrada[i].getObjRaiz().toString() + "\n";
        return msg;
    }
}
