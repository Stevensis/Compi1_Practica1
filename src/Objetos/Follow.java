/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.ArrayList;

/**
 *
 * @author Steven Sis
 */
public class Follow {
    private Token terminal;
    private int numeracion;
    private ArrayList<Integer> siguientes;

    public Token getTerminal() {
        return terminal;
    }

    public void setTerminal(Token terminal) {
        this.terminal = terminal;
    }

    public int getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(int numeracion) {
        this.numeracion = numeracion;
    }

    public ArrayList<Integer> getSiguientes() {
        return siguientes;
    }

    public void setSiguientes(ArrayList<Integer> siguientes) {
        this.siguientes = siguientes;
    }
    
    
}
