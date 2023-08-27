/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
/**
 *Representa una pregunta.
 */
public class Pregunta implements Serializable, Comparable<Pregunta>{ //atributos
    private String enunciado;
    private int nivel;
    private String correcta;
    private String posible1;
    private String posible2;
    private String posible3;
    
    //constructor

    /**
     *Constructor por defecto.
     */
    public Pregunta(){
    }

    /**
     *Constructor de la instancia.
     * @param e Enunciado de la Pregunta.
     * @param n Nivel de la Pregunta.
     * @param t Respuesta correcta de la Pregunta.
     * @param x Respuesta falsa 1.
     * @param y Respuesta falsa 2.
     * @param z Respuesta falsa 3.
     */
    public Pregunta(String e, int n, String t, String x, String y, String z){
        enunciado = e;
        nivel = n;
        correcta = t;
        posible1 = x;
        posible2 = y;
        posible3 = z;   
    }
    //getters

    /**
     *
     * @return Devuelve el enunciado de la Instancia.
     */
    public String getEnunciado(){
        return enunciado;
    }

    /**
     *
     * @return Devuelve el nivel de la Pregunta instanciada.
     */
    public int getNivel(){
        return nivel;
    }

    /**
     *
     * @return Devuelve la respuesta correcta de la instancia.
     */
    public String getCorrecta(){
        return correcta;
    }

    /**
     *
     * @return Devuelve la respuesta falsa 1.
     */
    public String getPosible1(){
        return posible1;
    }

    /**
     *
     * @return Devuelve la respuesta falsa 2.
     */
    public String getPosible2(){
        return posible2;
    }

    /**
     *
     * @return Devuelve la respuesta falsa 3.
     */
    public String getPosible3(){
        return posible3;
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
     * @param t Sobreescribe la respuesta correcta.
     */
    public void setCorrecta(String t){
        correcta = t;
    }

    /**
     *
     * @param x Sobreescribe la respuesta falsa 1.
     */
    public void setPosible1(String x){
        posible1 = x;
    }

    /**
     *
     * @param y Sobreescribe la respuesta falsa 1.
     */
    public void setPosible2(String y){
        posible2 = y;
    }

    /**
     *
     * @param z Sobreescribe la respuesta falsa 1.
     */
    public void setPosible3(String z){
        posible3 = z;
    }
    
    /**
     *
     * @return 
     */
    public ArrayList<String> opciones(){//metodo que me da la lista de opciones
        ArrayList<String> literales = new ArrayList<>();
        literales.add(correcta);
        literales.add(posible1);
        literales.add(posible2);
        literales.add(posible3);
        return literales;
    }
    
    /**
     *
     * @return Devuelve un String que representa a la instancia.
     */
    public String toString(){
          return enunciado;
    }

    /**
     * Criterio de ordenamiento natural de la clase Pregunta.
     * @param p Objeto de tipo Pregunta.
     * @return Devuelve un entero del intervalo [-1,1] que representa la comparación.
     */
    public int compareTo(Pregunta p){
        Integer x = this.getNivel();
        Integer y = p.getNivel();
        return x.compareTo(y);
    }
    
}   

