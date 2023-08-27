/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Euclasio
 */
public class Materia implements Serializable { //atributos

    private String codigo;
    private String nombre;
    private int niveles;
    private ArrayList<Pregunta> preguntas;

    //constructor

    /**
     *
     */
    public Materia() {
    }

    /**
     *
     * @param c
     * @param n
     * @param v
     * @param p
     */
    public Materia(String c, String n, int v, ArrayList<Pregunta> p) {
        codigo = c;
        nombre = n;
        niveles = v;
        preguntas = p;
    }

    //getters

    /**
     *
     * @return
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return
     */
    public int getNiveles() {
        return niveles;
    }

    /**
     *
     * @return
     */
    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    //setters

    /**
     *
     * @param c
     */
    public void setCodigo(String c) {
        codigo = c;
    }

    /**
     *
     * @param n
     */
    public void setNombre(String n) {
        nombre = n;
    }

    /**
     *
     * @param v
     */
    public void setNiveles(int v) {
        niveles = v;
    }

    /**
     *
     * @param p
     */
    public void setPregunta(Pregunta p) {
        preguntas.add(p);
    }

    /**
     *
     * @param p
     */
    public void removePregunta(Pregunta p) {
        preguntas.remove(p);
    }

    /**
     *
     * @return
     */
    public String toString() {
        return nombre + ": " + codigo;
    }

}
