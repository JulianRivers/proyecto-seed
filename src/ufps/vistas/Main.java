package ufps.vistas;

import ufps.util.colecciones_seed.TablaHash;
import ufps.util.colecciones_seed.TablaHash_AB;

public class Main {
    public static void main(String[] args) {
/*
        TablaHash tablaHash = new TablaHash<>();
        tablaHash.insertar("hola",35);
        tablaHash.insertar("juan", "35");
        tablaHash.insertar(345,"xd");
        tablaHash.insertar("hola","xd");
        tablaHash.eliminar("hola");
        System.out.println(tablaHash.imprimir());

 */


        TablaHash_AB<String,Integer> tablaHashAb = new TablaHash_AB<>();
        tablaHashAb.insertar("hola", 35);
        tablaHashAb.insertar("juan", 7);
        tablaHashAb.insertar(345,23);
        tablaHashAb.insertar("aloh",45);
        tablaHashAb.insertar("fota",76);
        tablaHashAb.insertar("vcxv",32);
        tablaHashAb.insertar("5342",13);
        tablaHashAb.insertar("sfdaf",76);
        tablaHashAb.insertar("req4",9);
        System.out.println(tablaHashAb.imprimir());

    }
}
