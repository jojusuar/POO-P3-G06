/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author jojusuar
 */
public class Juego {//atributos
    private Materia materia;
    private Paralelo paralelo;
    private Estudiante participante;
    private Estudiante companero;
    private int intentoComodines = 3;
    private int puntuacion;
    private int tiempo;
    private String premio;
    private TipoComodin comodin;
    //constructor
    public Juego(Materia m, Paralelo p, Estudiante x, Estudiante y, int s, int t, String h, TipoComodin k){
        materia = m;
        paralelo = p;
        participante = x;
        companero = y;
        puntuacion = s;
        tiempo = t;
        premio = h;
        comodin = k;
    }
    //getters
    public Materia getMateria(){
        return materia;
    }
    public Paralelo getParalelo(){
        return paralelo;
    }
    public Estudiante getParticipante(){
        return participante;
    }
    public Estudiante getCompanero(){
        return companero;
    }
    public int getPuntuacion(){
        return puntuacion;
    }
    public int getTiempo(){
        return tiempo;
    }
    public String getPremio(){
        return premio;
    }
    public TipoComodin getComodin(){
        return comodin;
    }
    //setters
    public void setMateria(Materia m){
        materia = m;
    }
    public void setParalelo(Paralelo p){
        paralelo = p;
    }
    public void setParticipante(Estudiante x){
        participante = x;
    }
    public void setCompanero(Estudiante y){
        companero = y;
    }
    public void setPuntuacion(int s){
        puntuacion = s;
    }
    public void setTiempo(int t){
        tiempo = t;
    }
    public void setPremio(String h){
        premio = h;
    }
    public void setComodin(TipoComodin k){
        comodin = k;
    }
    public void usarComodin(TipoComodin comodin,Paralelo companeros, Pregunta opciones, Estudiante companiero){
     if (intentoComodines >0){   
     switch(comodin){
        case Fifty_Fifty://comodin de 50 50 
            Random random = new Random();
            for(int i = 0; i<2;i++){// for para eliminar dos respuestas al azar
             ArrayList<String> literales = opciones.Opciones();//metodo que crea la lista de opciones
             int tamanio = literales.size();
             int valorAleatorio = random.nextInt(tamanio);
             String seleccionado = literales.get(valorAleatorio);
             if (seleccionado.equals(opciones.getCorrecta())){//condiciones para ver cual literal fue escogido y eliinar segun eso
                 opciones.setCorrecta("Respuesta eliiminada");
             }
             else if(seleccionado.equals(opciones.getPosible1())) {
                 opciones.setPosible1("Respuesta eliiminada");
             }
             else if(seleccionado.equals(opciones.getPosible2())) {
                 opciones.setPosible2("Respuesta eliiminada");
                 
            }
             else if(seleccionado.equals(opciones.getPosible3())) {
                 opciones.setPosible3("Respuesta eliiminada");
           
             }
            }
        case ConsultaCompanero:

            System.out.println("consultar al compañero"+companiero.getNombre());
            ;
        case ConsultaClase:
            System.out.println("consultar al salon de clase");
            ;

     }
       }
     intentoComodines = intentoComodines - 1;
    }
}