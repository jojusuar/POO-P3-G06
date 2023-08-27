/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *Representa a un término académico universitario.
 */
public class Termino implements Serializable{//atributos
    private int anio;
    private int numTermino;
    //constructor

    /**
     * Constructor de la instancia.
     * @param a Año del término.
     * @param n Período académico del término.
     */
    public Termino(int a, int n){
        anio = a;
        numTermino = n;
    }
    //getters

    /**
     *
     * @return Devuelve el año de la instancia.
     */
    public int getAnio(){
        return anio;
    }

    /**
     *
     * @return Devuelve el período académico de la instancia.
     */
    public int getNumTermino(){
        return numTermino;
    }
    //setters

    /**
     *
     * @param a Sobreescribe el año de la instancia.
     */
    public void setAnio(int a){
        anio = a;
    }

    /**
     *
     * @param n Sobreescribe el período académico de la instancia.
     */
    public void setNumTermino(int n){
        numTermino = n;
    }

    /**
     *
     * @return Devuelve un String que representa a la instancia.
     */
    public String toString(){
        String cadena = anio + "-" + numTermino;
        return cadena;
    }
}
