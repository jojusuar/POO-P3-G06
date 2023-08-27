/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *Representa una materia.
 */
public class Materia implements Serializable { //atributos

    private String codigo;
    private String nombre;
    private int niveles;
    private ArrayList<Pregunta> preguntas;

    //constructor

    /**
     *Constructor por defecto.
     */
    public Materia() {
    }

    /**
     * Constructor de la instancia.
     * @param c Código único de la materia.
     * @param n Nombre de la materia.
     * @param v Nivel máximo de las preguntas aceptadas en la materia.
     * @param p Preguntas asociadas a la materia.
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
     * @return Devuelve el código de la instancia.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     *
     * @return Devuelve el nombre de la instancia.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return Devuelve el nivel máximo de las preguntas en la instancia.
     */
    public int getNiveles() {
        return niveles;
    }

    /**
     *
     * @return Devuelve la lista de preguntas asociadas a la instancia.
     */
    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    //setters

    /**
     *
     * @param c Sobreescribe el código de la instancia.
     */
    public void setCodigo(String c) {
        codigo = c;
    }

    /**
     *
     * @param n Sobreescribe el nombre de la materia.
     */
    public void setNombre(String n) {
        nombre = n;
    }

    /**
     *
     * @param v Sobreescribe el nivel máximo de las preguntas de la instancia.
     */
    public void setNiveles(int v) {
        niveles = v;
    }

    /**
     *
     * @param p Sobreescribe la lista de preguntas asociadas a la instancia.
     */
    public void setPregunta(Pregunta p) {
        preguntas.add(p);
    }

    /**
     * Elimina una pregunta de la lista de preguntas de la instancia.
     * @param p Pregunta a eliminar.
     */
    public void removePregunta(Pregunta p) {
        preguntas.remove(p);
    }

    /**
     *
     * @return Devuelve un String que representa a la instancia.
     */
    public String toString() {
        return nombre + ": " + codigo;
    }

}
