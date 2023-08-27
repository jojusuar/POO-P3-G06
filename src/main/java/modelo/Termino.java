/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author jojusuar
 */
public class Termino implements Serializable{//atributos
    private int anio;
    private int numTermino;
    //constructor

    /**
     *
     * @param a
     * @param n
     */
    public Termino(int a, int n){
        anio = a;
        numTermino = n;
    }
    //getters

    /**
     *
     * @return
     */
    public int getAnio(){
        return anio;
    }

    /**
     *
     * @return
     */
    public int getNumTermino(){
        return numTermino;
    }
    //setters

    /**
     *
     * @param a
     */
    public void setAnio(int a){
        anio = a;
    }

    /**
     *
     * @param n
     */
    public void setNumTermino(int n){
        numTermino = n;
    }

    /**
     *
     * @return
     */
    public String toString(){
        String cadena = anio + "-" + numTermino;
        return cadena;
    }
}
