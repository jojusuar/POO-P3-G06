/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
/**
 *
 * @author jojusuar
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
     *
     */
    public Pregunta(){
    }

    /**
     *
     * @param e
     * @param n
     * @param t
     * @param x
     * @param y
     * @param z
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
    public String getPosible1(){
        return posible1;
    }

    /**
     *
     * @return
     */
    public String getPosible2(){
        return posible2;
    }

    /**
     *
     * @return
     */
    public String getPosible3(){
        return posible3;
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
    public void setPosible1(String x){
        posible1 = x;
    }

    /**
     *
     * @param y
     */
    public void setPosible2(String y){
        posible2 = y;
    }

    /**
     *
     * @param z
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
     * @param preguntas
     * @return
     */
    public ArrayList<String> mostrarOpciones(Pregunta preguntas){
        ArrayList<String> literales = preguntas.opciones();
        Collections.shuffle(literales);
        System.out.println(preguntas.getEnunciado());
        System.out.println("A)"+literales.get(0));
        System.out.println("B)"+literales.get(1));
        System.out.println("C)"+literales.get(2));
        System.out.println("D)"+literales.get(3));
        return literales;
    }
        
    /**
     *
     * @return
     */
    public String toString(){
          return enunciado;
    }

    /**
     *
     * @param p
     * @return
     */
    public int compareTo(Pregunta p){
        Integer x = this.getNivel();
        Integer y = p.getNivel();
        return x.compareTo(y);
    }
    
}   

