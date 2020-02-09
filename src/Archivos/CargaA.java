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
import java.io.FileWriter;
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
    private String nombreA="";

    public String getNombreA() {
        return nombreA;
    }

    public void setNombreA(String nombreA) {
        this.nombreA = nombreA;
    }
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
   file.setFileFilter(filtroImagen); //aplica el filtro 
   file.showOpenDialog(this); //abre la ventana
   /**abrimos el archivo seleccionado*/
   
   File abre=file.getSelectedFile();
   nombreA = abre.getName(); //se obtiene el nombre del archivo
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
   this.dispose(); //cierra el proceso de la venta
   }
   catch(IOException ex)
   {
     JOptionPane.showMessageDialog(null,ex+"" +
           "\nNo se ha encontrado el archivo",
                 "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
    }
  
  return texto;//El texto se almacena en el JTextArea
}
    public void guardarArchivo(String texto, String filter) {
 try
 {
  String nombre="";
  JFileChooser file=new JFileChooser();
  file.showSaveDialog(this);
  File guarda =file.getSelectedFile();
 
  if(guarda !=null)
  {
   /*guardamos el archivo y le damos el formato directamente,
    * si queremos que se guarde en formato doc lo definimos como .doc*/
    FileWriter  save=new FileWriter(guarda+filter);
    save.write(texto);
    save.close();
    JOptionPane.showMessageDialog(null,
         "El archivo se a guardado Exitosamente",
             "Información",JOptionPane.INFORMATION_MESSAGE);
    }
  this.dispose();
 }
  catch(IOException ex)
  {
   JOptionPane.showMessageDialog(null,
        "Su archivo no se ha guardado",
           "Advertencia",JOptionPane.WARNING_MESSAGE);
  }
 }

}
