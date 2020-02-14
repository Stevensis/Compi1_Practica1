/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author Steven Sis
 */
public class Token {
    //Se enumeran las posibilidades de tipos que puede ser un token (para llevar un orden)
    public enum Tipo{
        PALABRA_R,
        CADENA,
        COMENTARIO_L,
        COMENTARIO_ML,
        LLAVE_IZ,
        LLAVE_DE,
        PUNTO_Y_C,
        COMILLAS,
        PORCENTAJE,
        ID,
        NUMERO_ENTERO
    }
    
    private Tipo tipoToken;
        private String valor; //El Contenido del token
        private int contadorToken;
        private int fila;
        private int columna;

    public Token(Tipo tipoToken, String valor, int contadorToken, int fila, int columna) {
        this.tipoToken = tipoToken;
        this.valor = valor;
        this.contadorToken = contadorToken;
        this.fila = fila;
        this.columna = columna;
    }

    public String getValor() {
        return valor;
    }

    public int getContadorToken() {
        return contadorToken;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
        
    public String getTipo() {
            switch (tipoToken)
            {
                case PALABRA_R:
                    return "PALABRA_RESERVADA";
                case CADENA:
                    return "DOS_PUNTOS";
                case LLAVE_IZ:
                    return "LLAVE_IZQUIERDA";
                case LLAVE_DE:
                    return "LLAVE_DERECHA";
                case COMENTARIO_L:
                    return "CADENA";
                case COMENTARIO_ML:
                    return "NUMERO_ENTERO";
                case PUNTO_Y_C:
                    return "PUNTO_Y_COMA";
                case PORCENTAJE:
                    return "PORCENTAJE";
                case COMILLAS:
                    return "COMILLAS";
                case ID:
                    return "ID";
                default:
                    return "";
            }
            
        }
}
