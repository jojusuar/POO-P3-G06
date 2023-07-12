/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author jojusuar
 */
public class PreguntaTrucada {
    private String enunciado;
    private int nivel;
    private String correcta;
    private String posible;
    
    //constructor
    public PreguntaTrucada(String e, int n, String t, String x){
        enunciado = e;
        nivel = n;
        correcta = t;
        posible = x;  
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
    public String getPosible(){
        return posible;
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
    public void setPosible(String x){
        posible = x;
    }
    
}
