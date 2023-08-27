/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 * Representa a un estudiante.
 */
public class Estudiante implements Serializable { //atributos

    private String nombre;
    private String correo;
    private String matricula;

    //constructor

    /**
     * Constructor de la instancia.
     * @param n Nombre del estudiante.
     * @param c Dirección de correo electrónico del estudiante.
     * @param m Número de matrícula en ESPOL del estudiante.
     */
    public Estudiante(String n, String c, String m) {
        nombre = n;
        correo = c;
        matricula = m;
    }

    //getters

    /**
     *
     * @return Devuelve el String nombre de la instancia.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return Devuelve el String correo de la instancia.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     *
     * @return Devuelve el String matrícula de la instancia.
     */
    public String getMatricula() {
        return matricula;
    }

    //setters

    /**
     *
     * @param n Sobreescribe el nombre de la instancia.
     */
    public void setNombre(String n) {
        nombre = n;
    }

    /**
     *
     * @param c Sobreescribe el correo de la instancia.
     */
    public void setCorreo(String c) {
        correo = c;
    }

    /**
     *
     * @param m Sobreescribe la matrícula de la instancia.
     */
    public void setMatricula(String m) {
        matricula = m;
    }

    /**
     *
     * @return Devuelve el nombre del objeto al intentar convertirlo a String.
     */
    public String toString() {
        return nombre;
    }


}
