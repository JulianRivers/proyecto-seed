package ufps.vistas;
import ufps.util.varios.*;
import ufps.util.colecciones_seed.*;
/**
 * Write a description of class Test_archivo_http here.
 * 
 * @author marco adarme
 * @version (a version number or a date)
 */
public class Test_archivo_http
{
   
    public static void main(String args[])
    {
        String url="https://gitlab.com/madarme/archivos-persistencia/raw/master/estudiantes.csv";
        ArchivoLeerURL file=new ArchivoLeerURL(url);
        Object v[]=file.leerArchivo();
        ListaCD<String> l=new ListaCD<String>();
        for(Object datos:v)
            l.insertarAlFinal(datos.toString());
            
            for(String datos:l)
                System.out.println(datos);
            
    }
    

    
}
