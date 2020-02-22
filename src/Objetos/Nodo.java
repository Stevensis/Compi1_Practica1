/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Steven Sis
 */
public class Nodo {
    private Token valor;
    private int contadorH=0;
    private Nodo izquierda,derecha;
    ArrayList<Integer> primeros;
    ArrayList<Integer> ultimos;
    boolean anulable;

    public Nodo(Token valor) {
        this.valor = valor;
        this.izquierda=this.derecha=null;
        primeros = new ArrayList<>();
        ultimos = new ArrayList<>();
    }

    public Token getValor() {
        return valor;
    }

    public void setValor(Token valor) {
        this.valor = valor;
    }

    public int getContadorH() {
        return contadorH;
    }

    public void setContadorH(int contadorH) {
        this.contadorH = contadorH;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }

    public ArrayList<Integer> getPrimeros() {
        return primeros;
    }

    public void setPrimeros(ArrayList<Integer> primeros) {
        this.primeros = primeros;
    }

    public ArrayList<Integer> getUltimos() {
        return ultimos;
    }

    public void setUltimos(ArrayList<Integer> ultimos) {
        this.ultimos = ultimos;
    }

    public boolean isAnulable() {
        return anulable;
    }

    public void setAnulable(boolean anulable) {
        this.anulable = anulable;
    }
    
    public void setPrimeros(int a) {
        this.primeros.add(a);
    }


    public void setUltimos(int a) {
        this.ultimos.add(a);
    }

    public void noDuplicar() {
        Set<Integer> lt = new HashSet<>(this.primeros);
        Set<Integer> ls = new HashSet<>(this.ultimos);
        this.primeros.clear();
        this.primeros.addAll(lt);
        this.ultimos.clear();
        this.ultimos.addAll(ls);
    }
    
}
