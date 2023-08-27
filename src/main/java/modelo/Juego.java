/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author jojusuar
 */
public class Juego implements Serializable{//atributos
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
    private int preguntasRespondidas;
    private int nivelJugador;
    private ArrayList<Pregunta> preguntasDelJuego;
    private ArrayList<PreguntaRespondida> pRespondidas;
    private int com50=3;
    private int comCon=3;
    private int comSal=3;
    //constructor

    /**
     *
     * @param p
     * @param x
     * @param y
     * @param s
     * @param t
     * @param h
     * @param k
     * @param f
     */
    public Juego(Paralelo p, Estudiante x, Estudiante y, int s, int t, String h, TipoComodin k, String f){
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
        preguntasRespondidas = 0;
        preguntasDelJuego = new ArrayList<>();
        pRespondidas = new ArrayList<>();
    }
    //getters

    /**
     *
     * @return
     */
    public Paralelo getParalelo(){
        return paralelo;
    }

    /**
     *
     * @return
     */
    public Estudiante getParticipante(){
        return participante;
    }

    /**
     *
     * @return
     */
    public Estudiante getCompanero(){
        return companero;
    }

    /**
     *
     * @return
     */
    public int getPuntuacion(){
        return puntuacion;
    }

    /**
     *
     * @return
     */
    public int getTiempo(){
        return tiempo;
    }

    /**
     *
     * @return
     */
    public String getPremio(){
        return premio;
    }

    /**
     *
     * @return
     */
    public TipoComodin getComodin(){
        return comodin;
    }

    /**
     *
     * @return
     */
    public int getIntentoComodines(){
        return intentoComodines;
    }

    /**
     *
     * @return
     */
    public ArrayList<Pregunta> getPreguntasDelJuego(){
        return preguntasDelJuego;
    }

    /**
     *
     * @return
     */
    public String getFechaJuego(){
        return fechajuego;
    }

    /**
     *
     * @return
     */
    public int getNivelJugador(){
        return nivelJugador;
    }

    /**
     *
     * @return
     */
    public int getPreguntasRespondidas(){
        return preguntasRespondidas;
    }

    /**
     *
     * @return
     */
    public ArrayList<PreguntaRespondida> getpRespondidas() {
        return pRespondidas;
    }
    
    //setters

    /**
     *
     * @param p
     */
    public void setParalelo(Paralelo p){
        paralelo = p;
    }

    /**
     *
     * @param x
     */
    public void setParticipante(Estudiante x){
        participante = x;
    }

    /**
     *
     * @param y
     */
    public void setCompanero(Estudiante y){
        companero = y;
    }

    /**
     *
     * @param s
     */
    public void setPuntuacion(int s){
        puntuacion = s;
    }

    /**
     *
     * @param t
     */
    public void setTiempo(int t){
        tiempo = t;
    }

    /**
     *
     * @param h
     */
    public void setPremio(String h){
        premio = h;
    }

    /**
     *
     * @param k
     */
    public void setComodin(TipoComodin k){
        comodin = k;
    }

    /**
     *
     * @param n
     */
    public void setNivelJugador(int n ){
        nivelJugador = n;
    }

    /**
     *
     * @param s
     */
    public void setFechajuego(String s){
        fechajuego = s;
    }

    /**
     *
     * @param n
     */
    public void setPreguntasRespondidas(int n){
        preguntasRespondidas = n;
    }

    /**
     *
     * @param p
     */
    public void setPreguntasDelJuego(ArrayList<Pregunta> p){
        preguntasDelJuego = p;
    }

    /**
     *
     * @param pRespondidas
     */
    public void setpRespondidas(ArrayList<PreguntaRespondida> pRespondidas) {
        this.pRespondidas = pRespondidas;
    }
    
    /**
     *
     * @param comodin
     * @param curso
     * @param pregunta
     * @param companiero
     * @return
     */
    public String usarComodin(TipoComodin comodin,Paralelo curso, Pregunta pregunta, Estudiante companiero){
        String respuesta50 = "";
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
                    System.out.println("A) "+preguntaTrucada.getCorrecta());
                    System.out.println("B) "+preguntaTrucada.getPosible());
                    respuesta50 = preguntaTrucada.getCorrecta();
                    intentoComodines--;
                    break;

                case ConsultaCompanero:
                    int index = (int)(opciones.size()*Math.random());
                    String[] literales = {"A)","B)","C)","D)"};
                    String sugerencia = literales[index];
                    System.out.println(companiero.getNombre()+" cree que la respuesta es: "+sugerencia);
                    intentoComodines--;
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
                        }else if(votos2==max){
                            System.out.println("La mayoría de la clase cree que es: B)");
                        }else if(votos3==max){
                            System.out.println("La mayoría de la clase cree que es: C)");
                        }else if(votos4==max){
                            System.out.println("La mayoría de la clase cree que es: D)");
                        }else{
                            System.out.println("No hubo concenso");
                        }
                            System.out.println("consultar al salón de clase");
                        intentoComodines--;
                        break;
                    }
            }
        }
        return respuesta50;
    }
   
    /**
     *
     * @param k
     */
    public void mostrarComodines(TipoComodin k){
        //System.out.println(k.Fifty_Fifty);
        //System.out.println(k.ConsultaCompanero);
        //System.out.println(k.ConsultaClase);
        if(intentoComodines>=3){
        System.out.println("comodines actuales:");
        System.out.println(k.Fifty_Fifty);
        System.out.println(k.ConsultaCompanero);
        System.out.println(k.ConsultaClase);
        
        }
        
         
        if((intentoComodines>=2) && (k==TipoComodin.Fifty_Fifty)){
        System.out.println(k.ConsultaCompanero);
        System.out.println(k.ConsultaClase);  
        com50--;
        k=null;
            
        }
        if((intentoComodines>=2) && (k==TipoComodin.ConsultaCompanero)){
        System.out.println(k.Fifty_Fifty);
        System.out.println(k.ConsultaClase); 
        comCon--;
        k=null;
        }
        if((intentoComodines>=2) && (k==TipoComodin.ConsultaClase)){
        System.out.println(k.Fifty_Fifty);
        System.out.println(k.ConsultaCompanero);
        comSal--;
        k=null;
        }
        if(intentoComodines>=1 && comSal<=2 && k==TipoComodin.Fifty_Fifty  ){
        
        System.out.println(k.ConsultaCompanero);
        
        }
        if(intentoComodines>=1 && comCon<=2 && k==TipoComodin.Fifty_Fifty ){
        System.out.println(k.ConsultaClase);
        
        }
        if(intentoComodines>=1 && comSal<=2 && k==TipoComodin.ConsultaCompanero ){
        System.out.println(k.Fifty_Fifty);
        }
        if(intentoComodines>=1 && com50<=2 && k==TipoComodin.ConsultaCompanero ){
        System.out.println(k.ConsultaClase);
            
    
        }
        if(intentoComodines>=1 && comCon<=2 && k==TipoComodin.ConsultaClase ){
        System.out.println(k.Fifty_Fifty);
        
        }
        if(intentoComodines>=1 && com50<=2 && k==TipoComodin.ConsultaClase  ){
        System.out.println(k.ConsultaCompanero);
        
        
        
     }
    }
     
    /**
     *
     * @return
     */
    public String toString(){
        String cadena = "Fecha del juego: " + fechajuego + " - Participante: " + participante + " - Nivel Maximo alcanzado: " + nivelJugador + " - Tiempo: " + tiempo + " - Cantidad de preguntas contestadas: " + preguntasRespondidas + " - Comodines utilizados: " +(3-intentoComodines)+ " - Premio: " + premio;
        return cadena;
    }
}