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
public class Juego {//atributos
    private Materia materia;
    private Paralelo paralelo;
    private Estudiante participante;
    private Estudiante companero;
    private int puntuacion;
    private int tiempo;
    private String premio;
    private TipoComodin comodin;
    //constructor
    public Juego(Materia m, Paralelo p, Estudiante x, Estudiante y, int s, int t, String h, TipoComodin k){
        materia = m;
        paralelo = p;
        participante = x;
        companero = y;
        puntuacion = s;
        tiempo = t;
        premio = h;
        comodin = k;
    }
    //getters
    public Materia getMateria(){
        return materia;
    }
    public Paralelo getParalelo(){
        return paralelo;
    }
    public Estudiante getParticipante(){
        return participante;
    }
    public Estudiante getCompanero(){
        return companero;
    }
    public int getPuntuacion(){
        return puntuacion;
    }
    public int getTiempo(){
        return tiempo;
    }
    public String getPremio(){
        return premio;
    }
    public TipoComodin getComodin(){
        return comodin;
    }
    //setters
    public void setMateria(Materia m){
        materia = m;
    }
    public void setParalelo(Paralelo p){
        paralelo = p;
    }
    public void setParticipante(Estudiante x){
        participante = x;
    }
    public void setCompanero(Estudiante y){
        companero = y;
    }
    public void setPuntuacion(int s){
        puntuacion = s;
    }
    public void setTiempo(int t){
        tiempo = t;
    }
    public void setPremio(String h){
        premio = h;
    }
    public void setComodin(TipoComodin k){
        comodin = k;
    }
}
