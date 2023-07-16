/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author jojusuar
 */
public class Juego {//atributos
    private Materia materia;
    private Paralelo paralelo;
    private Estudiante participante;
    private Estudiante companero;
    private int intentoComodines;
    private int puntuacion;
    private int tiempo;
    private String premio;
    private TipoComodin comodin;
    private PreguntaTrucada preguntaTrucada;
    private String fechajuego;
    private int nivelJugador;
    //constructor
    public Juego(Materia m, Paralelo p, Estudiante x, Estudiante y, int s, int t, String h, TipoComodin k, String f){
        materia = m;
        paralelo = p;
        participante = x;
        companero = y;
        intentoComodines = 3;
        puntuacion = s;
        tiempo = t;
        premio = h;
        comodin = k;
        fechajuego = f;
        nivelJugador = 1;
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
    public void usarComodin(TipoComodin comodin,Paralelo curso, Pregunta pregunta, Estudiante companiero){
     if (intentoComodines >0){
         ArrayList<String> opciones = pregunta.opciones();
         switch(comodin){
             case Fifty_Fifty://comodin de 50 50 
                 opciones.remove(opciones.get(0));//saco la respuesta correcta de la lista
                 for(int i=0;i<2;i++){ //elimino una respuesta falsa por iteración
                     int index = (int)(opciones.size()*Math.random());
                     opciones.remove(opciones.get(index));
                 }
                 preguntaTrucada = new PreguntaTrucada(pregunta.getEnunciado(), pregunta.getNivel(), pregunta.getCorrecta(), opciones.get(0));//creo una copia especial de la pregunta en la cual solo hay la respuesta correcta y una falsa
                 System.out.println(preguntaTrucada.getCorrecta());
                 System.out.println(preguntaTrucada.getPosible());
                 break;
             case ConsultaCompanero:
                 int index = (int)(opciones.size()*Math.random());
                 String[] literales = {"A)","B)","C)","D)"};
                 String sugerencia = literales[index];
                 System.out.println(companiero.getNombre()+" cree que la respuesta es: "+sugerencia);
                 break;
             case ConsultaClase:
                 int votos1 = 0;
                 int votos2 = 0;
                 int votos3 = 0;
                 int votos4 = 0;
                 for(Estudiante e: curso.getEstudiantes()){
                     int opcion = (int)(opciones.size()*Math.random());
                     switch(opcion){
                         case 0: votos1++;
                         case 1: votos2++;
                         case 2: votos3++;
                         case 3: votos4++;
                     }
                  int[] votos = {votos1, votos2, votos3, votos4};
                  int max = Arrays.stream(votos).max().getAsInt();
                  if(votos1==max){
                      System.out.println("La mayoría de la clase cree que es: A)");
                  }
                  else if(votos2==max){
                      System.out.println("La mayoría de la clase cree que es: B)");
                  }
                  else if(votos3==max){
                      System.out.println("La mayoría de la clase cree que es: C)");
                  }
                  else if(votos4==max){
                      System.out.println("La mayoría de la clase cree que es: D)");
                  }
                  else{
                      System.out.println("No hubo concenso");
                  }
                      System.out.println("consultar al salón de clase");
                  break;
                 }
                 intentoComodines--;
                 pregunta.mostrarOpciones(pregunta);
         }
     }
    }
   
    
    
    public void mostrarComodines(TipoComodin k){
        System.out.println(k.Fifty_Fifty);
        System.out.println(k.ConsultaCompanero);
        System.out.println(k.ConsultaClase);
        
        
        
     }
     
     
    

    public String toString(){
        String cadena = "Fecha del juego: " + fechajuego + " - Participante: " + participante + " - Nivel Maximo alcanzado: " + nivelJugador + " - Tiempo: " + tiempo + " - Cantidad de preguntas contestadas: " + (nivelJugador-1) + " - Comodines utilizados: " +(2-intentoComodines)+ " - Premio: " + premio;
        return cadena;
    }
}