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
public class Conjunto {
    private String id;
    private ArrayList<Character> conjunto;

    public Conjunto() {
    }

    public Conjunto(String id, ArrayList<Character> conjunto) {
        this.id = id;
        this.conjunto = conjunto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Character> getConjunto() {
        return conjunto;
    }

    public void setConjunto(ArrayList<Character> conjunto) {
        this.conjunto = conjunto;
    }
    
}
