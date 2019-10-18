package ufps.util.varios;
import java.io.*;
public class ArchivoEscribirTexto
{
	
	private String filename;
	private PrintWriter escr;
	
	public ArchivoEscribirTexto(String filename, boolean siDeseaAdd)
		{
			
			try{
				this.filename=filename;
				escr= new PrintWriter(new BufferedWriter(new FileWriter(filename,siDeseaAdd)));
			}
			catch( IOException e)
			{}
		}
		
	public void println(String msg) throws IOException
		{
			escr.println(msg);
		}
		
		
		public void println(int msg) throws IOException
		{
			escr.println(msg);
                        
		}
		
		
	public void println(float msg) throws IOException
		{
			escr.println(msg);
		}
		
		
	public void println(byte msg) throws IOException
		{
			escr.println(msg);
		}
		
	public void println(boolean msg) throws IOException
		{
			escr.println(msg);
		}
		
	public void println(short msg) throws IOException
		{
			escr.println(msg);
		}
		
		
	public void println(double msg) throws IOException
		{
			escr.println(msg);
		}
		
	public void println(char msg) throws IOException
		{
			escr.println(msg);
		}
		
		
	/********************************print*****************************/	
	public <T> void escribir(T datos) 
		{
                    try{
                   escr.println(datos.toString());
                    }
                    catch(Exception e)
			{
                        System.err.print(e.getMessage());
                        }
		
		}
		
		
		
	public void close() 
		{
                    try
                    {
			this.escr.close();
                    }
                    catch(Exception e)
			{
                        System.err.print(e.getMessage());
                        }
		}
	
}