/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author jojusuar
 */
public class Estudiante implements Serializable { //atributos

    private String nombre;
    private String correo;
    private String matricula;

    //constructor

    /**
     *
     * @param n
     * @param c
     * @param m
     */
    public Estudiante(String n, String c, String m) {
        nombre = n;
        correo = c;
        matricula = m;
    }

    //getters

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
    public String getCorreo() {
        return correo;
    }

    /**
     *
     * @return
     */
    public String getMatricula() {
        return matricula;
    }

    //setters

    /**
     *
     * @param n
     */
    public void setNombre(String n) {
        nombre = n;
    }

    /**
     *
     * @param c
     */
    public void setCorreo(String c) {
        correo = c;
    }

    /**
     *
     * @param m
     */
    public void setMatricula(String m) {
        matricula = m;
    }

    /**
     *
     * @return
     */
    public String toString() {
        return nombre;
    }


}
