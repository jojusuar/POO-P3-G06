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
public class Estudiante {
    private String nombre;
    private String correo;
    private String matricula;
  public Estudiante(String n, String c, String m){
    nombre = n;
    correo = c;
    matricula = m;
  }
  public void usarComodin(TipoComodin t){
  
  }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
  
}

