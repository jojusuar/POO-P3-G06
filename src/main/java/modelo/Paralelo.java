/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * Representa a un Paralelo.
 */
public class Paralelo implements Serializable{//atributos
    private ArrayList<Estudiante> estudiantes;
    private Materia materia;
    private Termino termino;
    private int numero;
    //constructor

    /**
     * Constructor de la instancia.
     * @param e Lista de Estudiantes inscritos en el Paralelo.
     * @param m Materia asociada al Paralelo.
     * @param t Término asociado al Paralelo.
     * @param n Número del Paralelo.
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
     * @return Devuelve la lista de Estudiantes de la instancia.
     */
    public ArrayList<Estudiante> getEstudiantes(){
        return estudiantes;
    }

    /**
     *
     * @return Devuelve la Materia Asociada a la instancia.
     */
    public Materia getMateria(){
        return materia;
    }

    /**
     *
     * @return devuelve el Término asociado a la instancia.
     */
    public Termino getTermino(){
        return termino;
    }

    /**
     *
     * @return Devuelve el número del Paralelo instanciado.
     */
    public int getNumero(){
        return numero;
    }
    //setters

    /**
     *
     * @param e Sobreescribe la lista de Estudiantes de la instancia.
     */
    public void setEstudiantes(ArrayList<Estudiante> e){
        estudiantes = e;
    }

    /**
     *
     * @param m Sobreescribe la materia asociada a la instancia.
     */
    public void setMateria(Materia m){
        materia = m;
    }

    /**
     *
     * @param t Sobreescribe el Término asociado a la instancia.
     */
    public void setTermino(Termino t){
        termino = t;
    }

    /**
     *
     * @param n Sobreescribe el número del Paralelo instanciado.
     */
    public void setNumero(int n){
        numero = n;
    }

    /**
     *
     * @param e Añade un Estudiante a la lista del Paralelo.
     */
    public void agregarEstudiantes(Estudiante e){
        estudiantes.add(e);
    }

    /**
     *
     * @return Devuelve un String que representa a la instancia.
     */
    public String toString(){
        String cadena = "Materia: "+ materia +", Termino academico: "+ termino +", Numero de Paralelo: " + numero;
        return cadena;
    }
}
