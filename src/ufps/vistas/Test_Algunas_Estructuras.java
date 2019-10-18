/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.vistas;
import java.util.Iterator;
import ufps.util.colecciones_seed.*;

/**
 *
 * @author MADARME
 */
public class Test_Algunas_Estructuras {
    
    public static void main(String args[])
    {
    crear_ListaSimple();
    crear_ListaDoble();
    crear_Pila();
    crear_ArbolBinarioBusqueda();
    
    }
    private static void crear_ListaSimple()
    {
        ListaS<Integer> l=new ListaS<>();
        for(int i=0;i<10;i++)
        {
            l.insertarAlInicio(i);
        }
        System.out.println("Esto es una lista simple:"+l.toString()+"null");
        System.out.println("Lista Simple-Usando Iteradores para su recorrido:");
        //"Lista Simple-Usando Iteradores para su recorrido:"
        for(int dato:l)
        {
            System.out.print(dato+"->");
        }
        
        System.out.println(l.get(100));
        
        
    }
    
    
    
    private static void crear_ListaDoble()
    {
        ListaD<Integer> l=new ListaD<>();
        for(int i=0;i<10;i++)
        {
            l.insertarAlInicio(i);
        }
        System.out.println("Esto es una lista doble:"+l.toString()+"null");
        System.out.println(l.get(100));
        l.set(100,4);
    }
    
    private static void crear_Pila()
    {
        Pila<Integer> l=new Pila<>();
        for(int i=0;i<10;i++)
        {
            l.apilar(i);
        }
        System.out.println("Esto es una pila:");
        while(!l.esVacia())
             System.out.print(l.desapilar()+"\t");
        System.out.println("");
    }
    
    
     private static void crear_ArbolBinarioBusqueda()
    {
        ArbolBinarioBusqueda<Integer> t=new ArbolBinarioBusqueda<>();
        for(int i=0;i<10;i++)
        {
            int algun_numero=(int)(Math.random()*100)+1;
            t.insertar(algun_numero);
        }
        System.out.println("Esto es un árbol Binario de Búsqueda con su Recorrido Inorden:");
        Iterator<Integer> it=t.inOrden();
        while(it.hasNext())
        {
            System.out.print(it.next()+"\t");
        }
        
    }
    
    
    
}
