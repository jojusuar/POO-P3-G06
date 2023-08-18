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
public class Estudiante { //atributos
    private String nombre;
    private String correo;
    private String matricula;
    //constructor
    public Estudiante(String n, String c, String m){
        nombre = n;
        correo = c;
        matricula = m;
    }
    //getters
    public String getNombre(){
        return nombre;
    }
    public String getCorreo(){
        return correo;
    }
    public String getMatricula(){
        return matricula;
    }
    //setters
    public void setNombre(String n){
        nombre = n;
    }
    public void setCorreo(String c){
        correo = c;
    }
    public void setMatricula(String m){
        matricula = m;
    }
    public String toString(){
        return nombre;
    }
}
