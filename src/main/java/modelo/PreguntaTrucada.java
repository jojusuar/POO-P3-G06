/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author jojusuar
 */
public class PreguntaTrucada implements Serializable{
    private String enunciado;
    private int nivel;
    private String correcta;
    private String posible;
    
    //constructor

    /**
     *
     * @param e
     * @param n
     * @param t
     * @param x
     */
    public PreguntaTrucada(String e, int n, String t, String x){
        enunciado = e;
        nivel = n;
        correcta = t;
        posible = x;  
    }
    //getters

    /**
     *
     * @return
     */
    public String getEnunciado(){
        return enunciado;
    }

    /**
     *
     * @return
     */
    public int getNivel(){
        return nivel;
    }

    /**
     *
     * @return
     */
    public String getCorrecta(){
        return correcta;
    }

    /**
     *
     * @return
     */
    public String getPosible(){
        return posible;
    }
  
    //setters

    /**
     *
     * @param e
     */
    public void setEnunciado(String e){
        enunciado = e;
    }

    /**
     *
     * @param n
     */
    public void setNivel(int n){
        nivel = n;
    }

    /**
     *
     * @param t
     */
    public void setCorrecta(String t){
        correcta = t;
    }

    /**
     *
     * @param x
     */
    public void setPosible(String x){
        posible = x;
    }
    
}
