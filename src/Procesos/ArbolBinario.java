/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Objetos.Lexema;
import Objetos.Nodo;
import Objetos.Token;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Steven Sis
 */
public class ArbolBinario {
    private ArrayList<Token> lstToken; //Va contener la lista de tokens que conforma la expresion regular
    private ArrayList<Lexema> lstLexema = new ArrayList<Lexema> ();
    private String nombreExpresion;
    private int hoja;
    private Nodo raiz;
    private int i=0; //Va llegar el control de tokens analizado en la lista de tokens que conforma la expresion regular 
    private String arbol="";
    public ArbolBinario(ArrayList<Token> lstToken, String nombreExpresion) {
        this.lstToken = lstToken;
        this.lstToken.add(new Token(Token.Tipo.NUMERAL,"#",lstToken.size()+1,0,0));
        raiz = createArbol();
        this.nombreExpresion = nombreExpresion;
    }
    
    public Nodo createArbol(){
        switch(lstToken.get(i).getTipoT()){
            case ASTERISCO:
                Nodo nodoAsterisco = new Nodo(lstToken.get(i));i++;
                Nodo izquierdo = createArbol(); //Para el asterisco solo va existe un nodo, se tomara el asterisco
                nodoAsterisco.setIzquierda(izquierdo); // se añade el nodo hijo 
                nodoAsterisco.setAnulable(true); //Se añade la anulabilidad, el asterisco siempre es anulable
                nodoAsterisco.setPrimeros(izquierdo.getPrimeros()); //Se añade loz primeros, los primeros seran los primeros de su nodo hijo
                nodoAsterisco.setUltimos(izquierdo.getUltimos()); //Se añade los ultimos, los ultimos seran los ultimos de su nodo hijo
                return nodoAsterisco;
            case MAS:
                Nodo nodoMas = new Nodo(lstToken.get(i));i++;
                Nodo izquierdoM = createArbol(); //Para el mas solo va existe un nodo, se tomara el asterisco
                nodoMas.setIzquierda(izquierdoM); //Se añade el nodo hijo
                nodoMas.setAnulable(izquierdoM.isAnulable()); //Si el hijo es anulable el padre tambien y viseversa 
                nodoMas.setPrimeros(izquierdoM.getPrimeros()); //Los primeros del hijo son los primeros del padre
                nodoMas.setUltimos(izquierdoM.getUltimos());//Los ultimos del hijo son los ultimos del padre
                return nodoMas;
            case PUNTO:
                Nodo nodoPunto = new Nodo(lstToken.get(i)); i++;
                Nodo izquierdoP = createArbol(); 
                Nodo derechoP = createArbol(); 
                nodoPunto.setIzquierda(izquierdoP);//añade nodo izquierdo a padre
                nodoPunto.setDerecha(derechoP); //Añade nodo derecho a padre
                nodoPunto.setAnulable(izquierdoP.isAnulable() && derechoP.isAnulable()); //Los dos hijos tienen que ser anulables para que el padre sea anulable
                /*Para primeros Se usa la condicion If (Anulable(C1))     
                                        F=F(C1)+F(C2)
                                        Else
                                        F=F(C1)*/
                if (izquierdoP.isAnulable()) {
                    nodoPunto.getPrimeros().addAll(izquierdoP.getPrimeros());
                    nodoPunto.getPrimeros().addAll(derechoP.getPrimeros());
                }else{
                    nodoPunto.getPrimeros().addAll(izquierdoP.getPrimeros());
                }
                 /*Para ultimos Se usa la condicion If (Anulable(C2))
                                                    L=L(C1)+L(C2)
                                                    Else
                                                    L=L(C2)*/
                if (derechoP.isAnulable()) {
                nodoPunto.getUltimos().addAll(izquierdoP.getUltimos());
                nodoPunto.getUltimos().addAll(derechoP.getUltimos());
                }else{
                    nodoPunto.getUltimos().addAll(derechoP.getUltimos());
                }
                return nodoPunto;
            case INTERROGACION_DE:
                Nodo nodoI = new Nodo(lstToken.get(i)); i++;
                Nodo izquierdoI = createArbol();
                nodoI.setIzquierda(izquierdoI); //Solo tendra un hijo, lo denotaremos como izquierdo
                nodoI.setAnulable(true); //Siempre sera anulable
                nodoI.setPrimeros(izquierdoI.getPrimeros()); //Los primeros del hijo son los primeros del padre
                nodoI.setUltimos(izquierdoI.getUltimos()); //Los ultimos del hijo son los ultimos del padre
                return nodoI;
            case BARRA_V:
                Nodo nodoO = new Nodo(lstToken.get(i)); i++;
                Nodo izquierdoO = createArbol(); 
                Nodo derechoO = createArbol(); 
                nodoO.setIzquierda(izquierdoO);//añade nodo izquierdo a padre
                nodoO.setDerecha(derechoO); //Añade nodo derecho a padre
                nodoO.setAnulable(izquierdoO.isAnulable() || derechoO.isAnulable()); //Con que un hijo sea anulable, los demas seran anulables
                //Para los primeros y ultimos, sera la concatenacion de primeros de ambos hijos y la concatenacion de los ultimos de ambos hijos
                nodoO.getPrimeros().addAll(izquierdoO.getPrimeros());
                nodoO.getPrimeros().addAll(derechoO.getPrimeros());
                nodoO.getUltimos().addAll(izquierdoO.getUltimos());
                nodoO.getUltimos().addAll(derechoO.getUltimos());
                nodoO.noDuplicar();
                return nodoO;
            case ID:
                Nodo nodoID = new Nodo(lstToken.get(i)); i++;
                nodoID.setAnulable(false);   hoja++;
                nodoID.setContadorH(hoja);
                nodoID.setPrimeros(nodoID.getContadorH());
                nodoID.setUltimos(nodoID.getContadorH());
                nodoID.noDuplicar();
                return nodoID;
            case CADENA:
                Nodo nodoC = new Nodo(lstToken.get(i)); i++;
                nodoC.setAnulable(false); hoja++;
                nodoC.setContadorH(hoja);
                nodoC.setPrimeros(nodoC.getContadorH());
                nodoC.setUltimos(nodoC.getContadorH());
                nodoC.noDuplicar();
                return nodoC;
            case NUMERAL:
                Nodo nodoNumeral = new Nodo(lstToken.get(i)); i++;
                nodoNumeral.setAnulable(false); hoja++;
                nodoNumeral.setContadorH(hoja);
                nodoNumeral.setPrimeros(nodoNumeral.getContadorH());
                nodoNumeral.setUltimos(nodoNumeral.getContadorH());
                nodoNumeral.noDuplicar();
                return nodoNumeral;
            default:
                
                break;
        }
    return null;
    }
    
    public void graficarArbol(){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try{
        File miDir = new File (".");
            fichero = new FileWriter(miDir.getAbsolutePath() + "//" + this.nombreExpresion+".dot");
            pw = new PrintWriter(fichero);
            pw.println("digraph {");
            pw.println("node [shape = rectangle, height=0.5, width=1.2];");
            arbol="";
            cuerpoArbolBinario(raiz);
            pw.print(arbol);
            pw.println("}");
        }catch(Exception e){ System.out.println("Algo salio mal al crear la imagen de ="+this.nombreExpresion);}          
        finally {
                try {
                    if (null != fichero)
                        fichero.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
    }
    
    public void cuerpoArbolBinario(Nodo temp){
        if (temp!=null) {
            cuerpoArbolBinario(temp.getIzquierda());
            if (temp.getValor().getValor().equals("|") || temp.getValor().getValor().equals(">") || temp.getValor().getValor().equals("<") || temp.getValor().getValor().equals("{") || temp.getValor().getValor().equals("}")) {
                arbol+= "\""+ temp.toString()+"\""+"[shape = record, label = \"P: "+temp.getPrimeros().toString()+"|{"+temp.isAnulable()+" |\\"+temp.getValor().getValor()+"}|U: "+temp.getUltimos()+" \"] \n";
            }else{arbol+= "\""+ temp.toString()+"\""+"[shape = record, label = \"P: "+temp.getPrimeros().toString()+"|{"+temp.isAnulable()+" |"+temp.getValor().getValor()+"}|U: "+temp.getUltimos()+" \"] \n";}
           if(temp.getDerecha()!=null){
               arbol += "\""+temp.toString()+"\""+" -> "+"\""+temp.getDerecha().toString()+"\" \n";
           }
           if(temp.getIzquierda()!=null){
               arbol += "\""+temp.toString()+"\""+" -> "+"\""+temp.getIzquierda().toString()+"\" \n";
           } 
           cuerpoArbolBinario(temp.getDerecha());
        }
    }
    
    //Metodo para generar la imagen
    public  void creacionDibujo(){
        try{

            String dotPath = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
            File miDir = new File (".");
            System.out.println(miDir.getCanonicalPath());
            System.out.println(miDir.getAbsoluteFile());
            System.out.println(miDir.getPath());
            String fileInputPath = miDir.getAbsolutePath()+"\\"+ nombreExpresion +".dot";
            String fileOutputPath = miDir.getAbsolutePath()  +"\\"+nombreExpresion+".png";

            String tParam = "-Tpng";
            String tOParam = "-o";

            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;

            Runtime rt = Runtime.getRuntime();
            
            rt.exec( cmd );

            int x=0;
            File comprobar = new File(miDir.getAbsolutePath()  +"\\"+nombreExpresion+".png");
            while (true){
                x++;
                if(comprobar.exists()){
                    if(comprobar.length() > 100){
                        break;
                    }
                }
           //     System.out.println(x);
            }
            

           // ScrollPaneReport scrollPaneReport = new ScrollPaneReport(fileOutputPath);

            File file = new File(miDir.getAbsolutePath()  +"\\"+nombreExpresion+".png");
            Path path = Paths.get(miDir.getAbsolutePath()  +"\\"+nombreExpresion+".png");
            try {
            Desktop.getDesktop().open(file);
            //archivo.delete();

        } catch (Exception ex) {
                System.out.println("<<<<<------Problema con la imagen---------->>>>>>>");
        }
            if(file.exists()){
            //    Files.delete(path);
            }

            File file2 = new File(miDir.getAbsolutePath()  +"\\"+nombreExpresion+".txt");
            Path path2 = Paths.get(miDir.getAbsolutePath()  +"\\"+nombreExpresion+".txt");
            if(file2.exists()){
             //   Files.delete(path2);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }

    }
}
