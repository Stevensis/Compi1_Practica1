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
public class Error {
    private String valor;
        private int contadorE;
        private int fila;
        private int columna;
        private String descripcion;

    public Error(String valor, int contadorE, int fila, int columna, String descripcion) {
        this.valor = valor;
        this.contadorE = contadorE;
        this.fila = fila;
        this.columna = columna;
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getContadorE() {
        return contadorE;
    }

    public void setContadorE(int contadorE) {
        this.contadorE = contadorE;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
        
}
