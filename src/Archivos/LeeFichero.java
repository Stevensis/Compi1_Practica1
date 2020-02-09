/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivos;

import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LeeFichero extends javax.swing.JFrame{
   public void lee(String filter) {
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      String filtermi = filter.toUpperCase(); //El filtro lo convierte a mayusculas
       String filterma = filter.toLowerCase(); //El filtro lo convierte en minusculas
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         JFileChooser file=new JFileChooser();
   FileNameExtensionFilter filtroImagen=new FileNameExtensionFilter(filtermi,filterma);
   file.setFileFilter(filtroImagen);
   file.showOpenDialog(this);
   this.dispose();
   
          
          archivo = new File ("C:\\Users\\aaron\\Documents\\Prueba.er");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null)
            System.out.println(linea);
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
   }
}
