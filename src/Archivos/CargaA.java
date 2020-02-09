/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author aaron
 */
public class CargaA extends javax.swing.JFrame{
    File archivo1;
    FileInputStream entrada;

    public CargaA() {
    }
    //Metodo para abrir un archivo, solo funciona para un tipo de filtro
    public String abrirArchivo(String filter) {
  String filtermi = filter.toUpperCase(); //El filtro lo convierte a mayusculas
  String filterma = filter.toLowerCase(); //El filtro lo convierte en minusculas
  String aux="";   
  String texto="";
  try
  {
   /**llamamos el metodo que permite cargar la ventana*/
   JFileChooser file=new JFileChooser();
   FileNameExtensionFilter filtroImagen=new FileNameExtensionFilter(filtermi,filterma);
   file.setFileFilter(filtroImagen);
   file.showOpenDialog(this);
   /**abrimos el archivo seleccionado*/
   File abre=file.getSelectedFile();
   /**recorremos el archivo, lo leemos para plasmarlo
   *en el area de texto*/
   if(abre!=null)
   {     
      FileReader archivos=new FileReader(abre);
      BufferedReader lee=new BufferedReader(archivos);
      while((aux=lee.readLine())!=null)
      {
         texto+= aux+ "\n";
      }
         lee.close();
         archivos.close();
    }
   this.dispose();
   }
   catch(IOException ex)
   {
     JOptionPane.showMessageDialog(null,ex+"" +
           "\nNo se ha encontrado el archivo",
                 "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
    }
  
  return texto;//El texto se almacena en el JTextArea
}
    public String[][] getMatriz(){
        String cadena = abrirArchivo("er");
        String[][] mm = new String[12][12];
        int contador=0;
        String[] separar = cadena.split("\n");
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                mm[i][j]=separar[i].charAt(j)+"";
            }
        }
        return mm;
    }
}
