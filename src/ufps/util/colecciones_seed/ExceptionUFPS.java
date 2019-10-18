/**
 * ---------------------------------------------------------------------
 * $Id: ExceptionUFPS.java,v 2.0 2013/08/23 
 * Universidad Francisco de Paula Santander 
 * Programa Ingenieria de Sistemas
 * * @author Marco Adarme
 * Proyecto: SEED_UFPS
 * ----------------------------------------------------------------------
 */

package ufps.util.colecciones_seed;

/**
 * Implementacion de clase para manejo de las excepciones generadas en algunas Estructuras de Datos. <br>
 * @author Marco A. Adarme J.
 */
public class ExceptionUFPS extends Exception
{
    
    /**
     * Constructor con mensaje
     * @param mensaje Mensaje de error
     */
    public ExceptionUFPS(String mensaje)
    {
        super(mensaje);
    }
    
    /**
     * Metodo que retorna el mensaje de error para la excepcion generada. <br>
     * @return Mensaje de error representativo de la Excepcion generada.
     */
    public String getMensaje(){
        return (super.getMessage());
    }
}
