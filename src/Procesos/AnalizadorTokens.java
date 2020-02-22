/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Objetos.Conjunto;
import Objetos.Lexema;
import Objetos.Nodo;
import Objetos.Token;
import java.util.ArrayList;
/**
 *
 * @author Steven Sis
 */
public class AnalizadorTokens {
    private ArrayList<Token> lstToken;
    private ArrayList<Conjunto> lstConjunto = new ArrayList<Conjunto>();
    private ArrayList<Lexema> lstLexema = new ArrayList<Lexema>(); 
    private ArrayList<ArbolBinario> lstArbol = new ArrayList<ArbolBinario>();
    public AnalizadorTokens(ArrayList<Token> lstToken) {
        this.lstToken = lstToken;
    }

    public ArrayList<Conjunto> getLstConjunto() {
        return lstConjunto;
    }
    
    public void analisisTk(){
        for (int i = 0; i < lstToken.size(); i++) {
            switch(lstToken.get(i).getTipoT()){
                case PALABRA_R:
                    
                        i++;
                        i++;
                        if (lstToken.get(i).getTipoT()==Token.Tipo.ID) {
                           Conjunto conjunto = new Conjunto();
                           conjunto.setId(lstToken.get(i).getValor());
                           i++;
                           i++;
                           i++;
                           i++;
                           if(lstToken.get(i).getTipoT()==Token.Tipo.EQUIVALENCIA){ //Si es una equivalencia va regresar un token antes para optener su ascci
                               int start = (int) lstToken.get(i-1).getValor().charAt(0);
                               i++; //Se adelante un token para optner el ascii donde va terminar
                               int finish = (int) lstToken.get(i).getValor().charAt(0);
                               conjunto.setConjunto(generarConjuntos(start,finish));
                               i++;
                           }else if(lstToken.get(i).getTipoT()==Token.Tipo.COMA){
                               i--;//Regresamos al primer caracter de los conjuntos
                               ArrayList<Character> lstChar = new ArrayList<Character>();
                               do {
                                   if (lstToken.get(i).getTipoT()!=Token.Tipo.COMA) {
                                       lstChar.add(lstToken.get(i).getValor().charAt(0));
                                       i++;
                                   }else{i++;}
                               } while (lstToken.get(i).getTipoT()!=Token.Tipo.PUNTO_Y_C);
                               conjunto.setConjunto(lstChar);
                           }
                           lstConjunto.add(conjunto);
                        }                 
                break;
                case ID:
                    String id = lstToken.get(i).getValor();
                    i++;
                    i++;
                    i++;
                    ArrayList<Token> expresion = new ArrayList<Token>(); //Esta lista de token va guardar los tokens que conforman la expresion regular para luego analizarlos
                    do {
                        if(lstToken.get(i).getTipoT()!=Token.Tipo.LLAVE_IZ && lstToken.get(i).getTipoT()!=Token.Tipo.LLAVE_DE){
                        expresion.add(lstToken.get(i));
                        i++;
                        }else{i++;}
                        
                    } while (lstToken.get(i).getTipoT()!=Token.Tipo.PUNTO_Y_C);
                    ArbolBinario arbolBinario = new ArbolBinario(expresion,id);
                    lstArbol.add(arbolBinario);
                    break;
                case PORCENTAJE:
                    i++;
                    i++;
                    i++;
                    i++;
                    do {
                        if (lstToken.get(i).getTipoT()==Token.Tipo.ID) {
                            Lexema lexema = new Lexema();
                            lexema.setExpresion(lstToken.get(i).getValor());
                            i++;
                            i++;
                            i++;
                            if (lstToken.get(i).getTipoT()==Token.Tipo.CADENA) {
                                lexema.setCadena(lstToken.get(i).getValor());
                                lstLexema.add(lexema);i++;
                            }else{i++;}
                        }else{i++;}
                    } while (lstToken.get(i).getTipoT()!=Token.Tipo.LLAVE_DE);
                    
                    break;
            }
        }
        for (int j = 0; j < lstArbol.size(); j++) {
                        lstArbol.get(j).graficarArbol();
                        lstArbol.get(j).creacionDibujo();
                    }
        for (int i = 0; i <lstLexema.size(); i++) {
            System.out.println("Expresion: "+lstLexema.get(i).getExpresion()+" Tiene Lexema:"+lstLexema.get(i).getCadena());
        }
    }

    public  ArrayList<Character>  generarConjuntos(int inicio, int fin){
        ArrayList<Character> lstChar = new ArrayList<Character>();
        for (int i = inicio; i <= fin; i++) {
            lstChar.add((char)i);
        }
        return lstChar;
    }
}
