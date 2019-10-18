/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.util.varios;
import java.net.*;
import java.io.*;
/**
 * Clase que permita leer un archivo de texto plano desde una URL
 * 
 * @author Marco Adarme
 */
public class ArchivoLeerURL {
    
    //Almacena la dirección URL del archivo
    private String direccion;

    
    /**
     *  Permite crear un objeto de tipo ArchivoLeerURL especificando su URL
     * @param direccion la URL donde está el archivo
     */
    public ArchivoLeerURL(String direccion) {
        this.direccion = direccion;
    }

    public ArchivoLeerURL() {
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    /**
     * Método que retorna el archivo en un vector de Object.
     * Cada línea se almacena secuencialmente en cada posición del
     * vector
     * @return  un vector con el archivo almacenado en la URL
     */
    
    public Object []leerArchivo()
    {
    java.util.ArrayList<String> l=new java.util.ArrayList<String>();
    try {
		      // Indicamos la URL donde nos conectamos
		      URL url = new URL(this.getDireccion());
		      // Buffer con los datos recibidos
		      BufferedReader in = null;
		   
		      try {
		        // Volcamos lo recibido al buffer
		        in = new BufferedReader(new InputStreamReader(
		           url.openStream()));
		      } catch(Throwable t){}

		      // Transformamos el contenido del buffer a texto
		      String inputLine;
		      String inputText="";

		      // Mientras haya cosas en el buffer las volcamos a las
		      // cadenas de texto 
		      while ((inputLine = in.readLine()) != null)
		              l.add(inputLine);
		            
		      in.close();
		   
		    } catch (MalformedURLException ex1) {
		      System.out.println("URL erronea: "+ex1.getMessage());
		    } catch (IOException ex2) {
		      System.out.println("Error IO:"+ex2.getMessage());
		    }
 
return (l.toArray());    
    
    }
    
    
    
}
