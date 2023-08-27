/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

/**
 *Representa a una Pregunta en la cual se usó el Comodín Fifty-Fifty.
 */
public class PreguntaTrucada implements Serializable{
    private String enunciado;
    private int nivel;
    private String correcta;
    private String posible;
    
    //constructor
    /**
     *Constructor de la instancia.
     * @param e Enunciado de la Pregunta.
     * @param n Nivel de la Pregunta.
     * @param t Respuesta correcta de la Pregunta.
     * @param x Respuesta falsa.
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
     * @return Devuelve el enunciado de la Pregunta trucada.
     */
    public String getEnunciado(){
        return enunciado;
    }

    /**
     *
     * @return Devuelve el nivel de la Pregunta trucada.
     */
    public int getNivel(){
        return nivel;
    }

    /**
     *
     * @return Devuelve la respuesta correcta.
     */
    public String getCorrecta(){
        return correcta;
    }

    /**
     *
     * @return Devuelve la respuesta falsa.
     */
    public String getPosible(){
        return posible;
    }
  
    //setters

    /**
     *
     * @param e Sobreescribe el enunciado de la instancia.
     */
    public void setEnunciado(String e){
        enunciado = e;
    }

    /**
     *
     * @param n Sobreescribe el nivel de la Pregunta instanciada.
     */
    public void setNivel(int n){
        nivel = n;
    }

    /**
     *
     * @param t Sobreescribe la respuesta correcta de la instancia.
     */
    public void setCorrecta(String t){
        correcta = t;
    }

    /**
     *
     * @param x Sobreescribe la respuesta incorrecta de la instancia.
     */
    public void setPosible(String x){
        posible = x;
    }
    
}
