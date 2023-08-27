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
 * @author jojusuar
 */
public class Paralelo implements Serializable{//atributos
    private ArrayList<Estudiante> estudiantes;
    private Materia materia;
    private Termino termino;
    private int numero;
    //constructor

    /**
     *
     * @param e
     * @param m
     * @param t
     * @param n
     */
    public Paralelo(ArrayList<Estudiante> e, Materia m, Termino t, int n){
        estudiantes = e;
        materia = m;
        termino = t;
        numero = n;
    }
    //getters

    /**
     *
     * @return
     */
    public ArrayList<Estudiante> getEstudiantes(){
        return estudiantes;
    }

    /**
     *
     * @return
     */
    public Materia getMateria(){
        return materia;
    }

    /**
     *
     * @return
     */
    public Termino getTermino(){
        return termino;
    }

    /**
     *
     * @return
     */
    public int getNumero(){
        return numero;
    }
    //setters

    /**
     *
     * @param e
     */
    public void setEstudiantes(ArrayList<Estudiante> e){
        estudiantes = e;
    }

    /**
     *
     * @param m
     */
    public void setMateria(Materia m){
        materia = m;
    }

    /**
     *
     * @param t
     */
    public void setTermino(Termino t){
        termino = t;
    }

    /**
     *
     * @param n
     */
    public void setNumero(int n){
        numero = n;
    }

    /**
     *
     * @param e
     */
    public void agregarEstudiantes(Estudiante e){
        estudiantes.add(e);
    }

    /**
     *
     * @return
     */
    public String toString(){
        String cadena = "Materia: "+ materia +", Termino academico: "+ termino +", Numero de Paralelo: " + numero;
        return cadena;
    }
}
