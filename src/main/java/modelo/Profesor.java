/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author jojusuar
 */
public class Profesor implements Serializable{//Atributos
    private ArrayList<Materia> materias;
    private ArrayList<Paralelo> paralelos;
    //constructor

    /**
     *
     * @param m
     * @param p
     */
    public Profesor(ArrayList<Materia> m, ArrayList<Paralelo> p){
        materias = m;
        paralelos = p;
    }
    //getters

    /**
     *
     * @return
     */
    public ArrayList<Materia> getMaterias(){
        return materias;
    }

    /**
     *
     * @return
     */
    public ArrayList<Paralelo> getParalelos(){
        return paralelos;
    }
    //setters

    /**
     *
     * @param m
     */
    public void setMaterias(ArrayList<Materia> m){
        materias = m;
    }

    /**
     *
     * @param p
     */
    public void setParalelos(ArrayList<Paralelo> p){
        paralelos = p;
    }
    //métodos

    /**
     *
     */
    public void mostrarInformacion(){
        System.out.println("Materias del docente:");
        for(Materia m: materias){
            System.out.println(m.getNombre());
        }
        System.out.println("Paralelos del docente:");
        for(Paralelo p: paralelos){
            System.out.println(p.getNumero());
        }
    } 
}
