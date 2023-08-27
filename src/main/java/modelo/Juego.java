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
 * Representa a una sesión del juego.
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
     * Constructor de la instancia.
     * @param p Paralelo del cual se obtienen los demás atributos.
     * @param x Un Estudiante, debe pertenecer a la lista de estudiantes del Paralelo.
     * @param y Otro Estudiante, debe pertenecer a la lista de estudiantes del Paralelo.
     * @param s Puntuación del estudiante en el juego.
     * @param t Tiempo transcurrido desde el inicio del juego hasta ganar o perder.
     * @param h Recompensa asignada al Estudiante al ganar.
     * @param k Elemento del enum TipoComodin.
     * @param f Fecha del juego, tomada de la fecha local del sistema.
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
     * @return Devuelve el Paralelo de la instancia.
     */
    public Paralelo getParalelo(){
        return paralelo;
    }

    /**
     *
     * @return Devuelve el Estudiante principal de la instancia.
     */
    public Estudiante getParticipante(){
        return participante;
    }

    /**
     *
     * @return Devuelve el Estudiante de apoyo de la instancia.
     */
    public Estudiante getCompanero(){
        return companero;
    }

    /**
     *
     * @return Devuelve la puntuación máxima de la sesión.
     */
    public int getPuntuacion(){
        return puntuacion;
    }

    /**
     *
     * @return Devuelve el tiempo total transcurrido durante la sesión.
     */
    public int getTiempo(){
        return tiempo;
    }

    /**
     *
     * @return Devuelve el premio asignado al ganador.
     */
    public String getPremio(){
        return premio;
    }

    /**
     *
     * @return Deuelve el Comodín.
     */
    public TipoComodin getComodin(){
        return comodin;
    }

    /**
     *
     * @return Devuelve las veces que se ha usado comodines durante la sesión.
     */
    public int getIntentoComodines(){
        return intentoComodines;
    }

    /**
     *
     * @return Devuelve las preguntas seleccionadas para la sesión.
     */
    public ArrayList<Pregunta> getPreguntasDelJuego(){
        return preguntasDelJuego;
    }

    /**
     *
     * @return Devuelve la fecha del juego.
     */
    public String getFechaJuego(){
        return fechajuego;
    }

    /**
     *
     * @return Devuelve el nivel de la última pregunta acertada.
     */
    public int getNivelJugador(){
        return nivelJugador;
    }

    /**
     *
     * @return Devuelve el nivel de la última pregunta mostrada.
     */
    public int getPreguntasRespondidas(){
        return preguntasRespondidas;
    }

    /**
     *
     * @return Devuelve la lista de las preguntas mostradas hasta ganar o perder.
     */
    public ArrayList<PreguntaRespondida> getpRespondidas() {
        return pRespondidas;
    }
    
    //setters

    /**
     *
     * @param p Sobreescribe el Paralelo del Juego.
     */
    public void setParalelo(Paralelo p){
        paralelo = p;
    }

    /**
     *
     * @param x Sobreescribe al Estudiante principal del juego.
     */
    public void setParticipante(Estudiante x){
        participante = x;
    }

    /**
     *
     * @param y Sobreescribe al Estudiante secundario del juego.
     */
    public void setCompanero(Estudiante y){
        companero = y;
    }

    /**
     *
     * @param s Sobreescribe la puntuación del Estudiante.
     */
    public void setPuntuacion(int s){
        puntuacion = s;
    }

    /**
     *
     * @param t Sobreescribe la duración total de la sesión.
     */
    public void setTiempo(int t){
        tiempo = t;
    }

    /**
     *
     * @param h Sobreescribe el premio recibido.
     */
    public void setPremio(String h){
        premio = h;
    }

    /**
     *
     * @param k Sobreescribe el comodín.
     */
    public void setComodin(TipoComodin k){
        comodin = k;
    }

    /**
     *
     * @param n Sobreescribe el nivel alcanzado en la sesión.
     */
    public void setNivelJugador(int n ){
        nivelJugador = n;
    }

    /**
     *
     * @param s Sobreescribe la fecha del juego.
     */
    public void setFechajuego(String s){
        fechajuego = s;
    }

    /**
     *
     * @param n Sobreescribe el número de preguntas respondidas en el juego.
     */
    public void setPreguntasRespondidas(int n){
        preguntasRespondidas = n;
    }

    /**
     *
     * @param p  Sobreescribe la lista de preguntas mostradas en el juego.
     */
    public void setPreguntasDelJuego(ArrayList<Pregunta> p){
        preguntasDelJuego = p;
    }

    /**
     *
     * @param pRespondidas Sobreescribe la lista de preguntas respondidas en el juego.
     */
    public void setpRespondidas(ArrayList<PreguntaRespondida> pRespondidas) {
        this.pRespondidas = pRespondidas;
    }
    
    /**
     * El Estudiante usa un comodín.
     * @param comodin Comodín utilizado.
     * @param curso Paralelo de la sesión.
     * @param pregunta Pregunta en la cual se usa el comodín.
     * @param companiero Estudiante de apoyo.
     * @return Devuelve un String que representa el uso del comodín.
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
     * @return Devuelve un String que representa a la instancia.
     */
    public String toString(){
        String cadena = "Fecha del juego: " + fechajuego + " - Participante: " + participante + " - Nivel Maximo alcanzado: " + nivelJugador + " - Tiempo: " + tiempo + " - Cantidad de preguntas contestadas: " + preguntasRespondidas + " - Comodines utilizados: " +(3-intentoComodines)+ " - Premio: " + premio;
        return cadena;
    }
}