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
public class Paralelo {//atributos
    private ArrayList<Estudiante> estudiantes;
    private Materia materia;
    private Termino termino;
    private int numero;
    //constructor
    public Paralelo(ArrayList<Estudiante> e, Materia m, Termino t, int n){
        estudiantes = e;
        materia = m;
        termino = t;
        numero = n;
    }
    //getters
    public ArrayList<Estudiante> getEstudiantes(){
        return estudiantes;
    }
    public Materia getMateria(){
        return materia;
    }
    public Termino getTermino(){
        return termino;
    }
    public int getNumero(){
        return numero;
    }
    //setters
    public void setEstudiantes(ArrayList<Estudiante> e){
        estudiantes = e;
    }
    public void setMateria(Materia m){
        materia = m;
    }
    public void setTermino(Termino t){
        termino = t;
    }
    public void setNumero(int n){
        numero = n;
    }
    public void agregarEstudiantes(Estudiante e){
        estudiantes.add(e);
    }
    public String toString(){
        String cadena = "Materia: "+ materia +", Termino academico: "+ termino +", Numero de Paralelo: " + numero;
        return cadena;
    }
}
