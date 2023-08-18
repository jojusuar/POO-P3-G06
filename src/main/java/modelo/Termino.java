/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author jojusuar
 */
public class Termino {//atributos
    private int anio;
    private int numTermino;
    //constructor
    public Termino(int a, int n){
        anio = a;
        numTermino = n;
    }
    //getters
    public int getAnio(){
        return anio;
    }
    public int getNumTermino(){
        return numTermino;
    }
    //setters
    public void setAnio(int a){
        anio = a;
    }
    public void setNumTermino(int n){
        numTermino = n;
    }
    public String toString(){
        String cadena = anio + "-" + numTermino;
        return cadena;
    }
}
