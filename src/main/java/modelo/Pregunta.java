/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.util.ArrayList;
/**
 *
 * @author jojusuar
 */
public class Pregunta { //atributos
    private String enunciado;
    private int nivel;
    private String correcta;
    private String posible1;
    private String posible2;
    private String posible3;
    
    //constructor
    public Pregunta(String e, int n, String t, String x, String y, String z){
        enunciado = e;
        nivel = n;
        correcta = t;
        posible1 = x;
        posible2 = y;
        posible3 = z;   
    }
    public ArrayList<String> Opciones(){
     ArrayList<String> literales = new ArrayList<>();
     literales.add(correcta);
     literales.add(posible1);
     literales.add(posible2);
     literales.add(posible3);
     return literales;
    }
    
    //getters
    public String getEnunciado(){
        return enunciado;
    }
    public int getNivel(){
        return nivel;
    }
    public String getCorrecta(){
        return correcta;
    }
    public String getPosible1(){
        return posible1;
    }
    public String getPosible2(){
        return posible2;
    }
    public String getPosible3(){
        return posible3;
    }
    //setters
    public void setEnunciado(String e){
        enunciado = e;
    }
    public void setNivel(int n){
        nivel = n;
    }
    public void setCorrecta(String t){
        correcta = t;
    }
    public void setPosible1(String x){
        posible1 = x;
    }
    public void setPosible2(String y){
        posible2 = y;
    }
    public void setPosible3(String z){
        posible3 = z;
    }
    
}
