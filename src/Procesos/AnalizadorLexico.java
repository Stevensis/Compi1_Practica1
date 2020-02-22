/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;
import Objetos.*;
import Objetos.Error;
import java.util.ArrayList;
/**
 *
 * @author Steven Sis
 */
public class AnalizadorLexico {
    private String outf;
    private ArrayList<Token> lstToken = new ArrayList<Token>();
    private ArrayList<Error> ListErr = new ArrayList<Error>();
    public int fila = 1, columna = 0, estado = 0, contadorTK = 1, contadorE = 1, columnaiT = 0,posA;
    public boolean comp = true;
    String aux=""; //Va concatener los caracter
    public AnalizadorLexico(){
        
    }
    
    public ArrayList<Token> analizarL(String cadena){
        char c; // Contendra el caracter leido por cada interacion 
        int ascii; //Contendra el caracter leido por cada interacion en codigo ascii
        
        
        for (int i = 0; i < cadena.length(); i++) {
            c=cadena.charAt(i); //Va obtener el caracter de la lectura correspondiente 
            ascii = (int) c; //guardara el codigo ascci del caracter analizado
            if (c == '\n') { fila++; columna = 0; } else { columna++; } // Va tener el conteneo de filas y columnas
            if (c != '\n' && c !=' ' && comp) { columnaiT = columna; comp = false; } //Guardara la posicion del primer caracter del token analizado
            switch(estado){
                case 0: //En el caso inicial vamos a aceptar todos lo token que sea parte de un caracter
                    if (Character.isLetter(c)) {   aux += c;  estado=1;  } //Si viene una letra, va pasar a otro estado
                    else if (Character.isDigit(c)) {   aux += c;  agregarTk(Token.Tipo.NUMERO_ENTERO);  } //va agregar numeros enteros
                    else if (c=='/') {   aux += c;  estado=2;  } // Si viene / es un comienzo de comentario
                    else if (c=='<') {   aux += c;  estado=4;  } // Si viene < es un comienzo de comentario multi linea
                    else if (c=='"') {   estado=7;   } //Comienzo de una cadena
                    else if (c=='%') { aux += c;  agregarTk(Token.Tipo.PORCENTAJE);  }
                    else if (c=='-') { aux += c;  agregarTk(Token.Tipo.MENOS);  }
                    else if (c=='>') { aux += c;  agregarTk(Token.Tipo.MAYORQUE);  }
                    else if (c=='~') { aux += c;  agregarTk(Token.Tipo.EQUIVALENCIA);  }
                    else if (c=='*') { aux += c;  agregarTk(Token.Tipo.ASTERISCO);  }
                    else if (c==';') { aux += c;  agregarTk(Token.Tipo.PUNTO_Y_C);  }
                    else if (c==':') { aux += c;  agregarTk(Token.Tipo.DOS_PUNTOS);  }
                    else if (c==',') { aux += c;  agregarTk(Token.Tipo.COMA);  }
                    else if (c=='|') { aux += c;  agregarTk(Token.Tipo.BARRA_V);  }
                    else if (c=='.') { aux += c;  agregarTk(Token.Tipo.PUNTO);  }
                    else if (c=='+') { aux += c;  agregarTk(Token.Tipo.MAS);  }
                    else if (c=='?') { aux += c;  agregarTk(Token.Tipo.INTERROGACION_DE);  }
                    else if (c=='{') { aux += c;  agregarTk(Token.Tipo.LLAVE_IZ);  }
                    else if (c=='}') { aux += c;  agregarTk(Token.Tipo.LLAVE_DE);  }
                    else if (ascii>32 && ascii<126){ aux += c; agregarTk(Token.Tipo.SIGNO);}
                    else{
                        if(!Character.isWhitespace(c)){
                            aux += c;
                            agregarErrores("Caracter Desconocido");
                        }
                    }
                    break;
                case 1: //Analiza si viene una letra
                    if (Character.isLetter(c)) { aux += c;  estado=1; }
                    else if (Character.isDigit(c)) { aux += c;  estado=1; }
                    else if (c == '_') { aux += c;  estado=1; } 
                    else{ verificarPR(aux); i--; columna--; }
                    break;
                case 2: //Analiza los comentarios
                    if (c=='/') {   aux += c;  estado=3;  }
                    else{ agregarTk(Token.Tipo.DIAGONAL); i--; columna--; }
                    break;
                case 3:
                    if (c=='\n') {   agregarTk(Token.Tipo.COMENTARIO_L); }
                    else{ aux += c;  estado=3; }
                    break;
                case 4:
                    if (c=='!') { aux+="</>";  aux += c;  estado=5;  }
                    else{ agregarTk(Token.Tipo.MENORQUE); i--; columna--; }
                    break;
                case 5:
                    if (c=='!') {   aux += c;  estado=6;  }
                    else{ aux += c;  estado=5;  }
                    break;
                case 6:
                    if (c=='>') {   aux += c;  agregarTk(Token.Tipo.COMENTARIO_ML);  }
                    else{ aux += c;  estado=5;  }
                    break;
                case 7:
                    if (c=='"'){   agregarTk(Token.Tipo.CADENA);  }
                    else { aux += c;  estado=7;}
                    break;
                default:
                    break;
            }
        }
        return lstToken;
    }
    
    //Verificara si aquel token que empieza con una letra, es solo una letra, un id o una palabra reservada
    
    public void verificarPR(String a){
        if (a.length()==1) { agregarTk(Token.Tipo.LETRA); }
        else if(a.equals("CONJ")){agregarTk(Token.Tipo.PALABRA_R);}
        else{agregarTk(Token.Tipo.ID);}
    }
    
     //Metodo que agrega los tokens, va resivir un tipo de token
        public void agregarTk(Token.Tipo tp) {
            lstToken.add(new Token(tp, aux, contadorTK, fila, columnaiT)); //Añade a la lista de token y al mismo tiempo crea un nuevo token
            contadorTK++;//Va sumnar al contador de tokens
            aux = ""; //Reinicia la variable aux que contiene el valor del lexema
            comp = true; //Para que vuelva a guardar nuevamente la culmna inicial del token
            estado = 0;
        }
        
        //Agrega los errores a la lista
        public void agregarErrores(String desc)
        {
            ListErr.add(new Error(aux, contadorE, fila, columnaiT, desc));
            contadorE++; //Va sumnar al contador de tokens
            aux = ""; //Reinicia la variable aux que contiene el valor del lexema
            comp = true; //Para que vuelva a guardar nuevamente la culmna inicial del token
            estado = 0;
        }

    public ArrayList<Error> getListErr() {
        return ListErr;
    }
        
    
}
