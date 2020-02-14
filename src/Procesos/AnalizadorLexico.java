/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;
import Objetos.*;
import java.util.ArrayList;
/**
 *
 * @author Steven Sis
 */
public class AnalizadorLexico {
    private String outf;
    private ArrayList<Token> lstToken = new ArrayList<Token>();
    public int fila = 1, columna = 0, estado = 0, contadorTK = 1, contadorE = 1, columnaiT = 0,posA;
    public boolean comp = true;
    public AnalizadorLexico(){
        
    }
    
    public ArrayList<Token> analizarL(String cadena){
        char c; // Contendra el caracter leido por cada interacion 
        int ascii; //Contendra el caracter leido por cada interacion en codigo ascii
        String aux="";
        
        for (int i = 0; i < cadena.length(); i++) {
            c=cadena.charAt(i);
            ascii = (int) c;
            if (c == '\n') { fila++; columna = 0; } else { columna++; } // Va tener el conteneo de filas y columnas
            if (c != '\n' && c !=' ' && comp) { columnaiT = columna; comp = false; }
        }
        
        return lstToken;
    }
    
    
}
