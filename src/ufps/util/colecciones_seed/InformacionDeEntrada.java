/**
 * ---------------------------------------------------------------------
 * $Id: InformacionDeEntrada.java,v 1.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 *
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;

/**
 * Implementacion de Clase para el manejode los Obejtos almacenados dentro de la Tabla Hash. <br>
 * @param <Clave> Representa la clave del Objeto ingresado en la Tabla. <br>
 * @param <T> Tipo de datos almacenados en la Informacion de entrada. <br>
 * @author Yulieth Pabon
 * @version 1.0
 */

public class InformacionDeEntrada  <Clave, T > extends Object{
    
    ////////////////////////////////////////////////////////////
    // InformacionDeEntrada - Atributos ////////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Objeto a almacenar
     */
    private T objeto;

    /**
     * Clave del objeto de la entrada
     */
    private Clave clave;

    ////////////////////////////////////////////////////////////
    // InformacionDeEntrada - Implementacion de Metodos ////////////////////
    ////////////////////////////////////////////////////////////  

    /**
     * Constructor de la entrada de la tabla con un objeto especifico y su respectiva clave.  <br>
     * <b>post: </b> Se creo una entrada con un objeto especifico y su respectiva clave. <br>
     * @param clave la clave del objeto. No debe ser vacia. <br>
     * @param objeto El objeto de la entrada. <br>
     */
    public InformacionDeEntrada( Clave clave, T objeto ){
        this.clave=clave;
        this.objeto=objeto;
    }

    /**
     * Constructor de la entrada de la tabla con clave especifica. <br>
     * <b>post: </b>  Se creo una entrada con una clave especifica de un objeto nulo. <br>
     * @param clave la clave de la entrada,esta no puede ser vacia.  <br>
     */
    public InformacionDeEntrada( Clave clave ){
        this.objeto = null;
        this.clave = clave;
    }
    
    /**
     * Metodo que permite retornar el objeto de la entrada de la tabla. <br>
     * <b>post: </b> Se retorno el objeto de la entrada de la tabla. <br>
     * @return el objeto de la entrada de la tabla. 
     */
    public T getObjeto( ) {
        return this.objeto;
    }
    
      /**
     * Metodo que permite retornar la clave de la entrada de la tabla. <br>
     * <b>post: </b> Se retorno la clave de la entrada de la tabla.<br>
     * @return clave de la entrada de la tabla.
     */
    public Clave getClave( ){
        return this.clave;
    }

    /**
     * Metodo que permite modificar el objeto de la entrada de la tabla. <br>
     * <b>post: </b> Se modifico el objeto de la entrada.<br>
     * @param obje objeto de la entrada de la tabla.<br>
     */
    public void setObjeto( T obje){
        this.objeto=obje;
    }

    /**
     * Metodo que permite imprimir los atributos de la entrada de la tabla. <br>
     * <b>post: </b> Se retorna en una cadena de caracteres la informacion de la entrada de la tabla. <br>
     * @return cadena de caracteres que representan la informacion de la entrada de la tabla. <br>
     */
    @Override
    public String toString( ){
        return ("Clave: " + this.clave.toString( ) + " del Objeto: " + this.objeto.toString( ) + "\n");
    }

    /**
     * Metodo que permite verificar si dos objetos son iguales. <br>
     * <b>post: </b> Se verifico si dos objetos poseen la misma clave. <br>
     * @return true si dos objetos poseen la misma clave.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        InformacionDeEntrada<Clave, T> x= (InformacionDeEntrada<Clave, T>) obj;
        return (this.clave.equals(x.getClave()));
    }
       
    
}
