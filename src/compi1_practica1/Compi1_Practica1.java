/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compi1_practica1;

import Archivos.*;

/**
 *
 * @author aaron
 */
public class Compi1_Practica1 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       CargaA m = new CargaA();
       LeeFichero l = new LeeFichero();
      // l.lee("Er");
       String obtener = m.abrirArchivo("er");
       
        System.out.println(obtener);
        System.out.println(m.getNombreR());
        obtener += "asd";
        System.out.println(m.getNombreA());
        m.guardarArchivo(obtener, ".er");
        System.out.println("\n se guardo archivo");
    }

    
    
}
