/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author jojusuar
 */
public class UtilitariaJuego {
    public static void jugar(Scanner input, ArrayList<Paralelo> paralelos, ArrayList<Estudiante> participantes, ArrayList<Juego> juegos){
        // Inicializamos valores
                Materia materiaEscogida=null;
                Paralelo paraleloEscogido=null;
                int nPreguntas;
                String participante;
                String consulta;
                
                System.out.println("Ingrese la fecha en formato YYYY-MM-DD");
                String fecha = input.nextLine();
                //Creamos los estudiantes para apoyo y participante
                Estudiante estudiante = new Estudiante(null,null,null);
                Estudiante apoyo = new Estudiante(null,null,null);
                    
                System.out.println("-----Nuevo juego-----");
                //pedida de datos antes de comenzar a jugar
                
                System.out.println("Ingrese el nombre la materia");
                String materia = input.nextLine();
                System.out.println("Ingrese el número del paralelo");
                int paralel = input.nextInt();
                input.nextLine();
                System.out.println("Ingrese el nivel hasta el cual jugar:");
                nPreguntas = input.nextInt();
                input.nextLine();
                //se escoge paralelo, que tiene como atributo materia             
                for(Paralelo elemento:paralelos){
                    if(paralel == elemento.getNumero()){
                        Materia materiaparalelo = elemento.getMateria();
                        if(materia.equals(materiaparalelo.getNombre())){
                            paraleloEscogido = elemento;
                        }
                    }
                }
                materiaEscogida = paraleloEscogido.getMateria();
                //Validamos que el numero de preguntas no sea mayor al numero de niveles
                while(nPreguntas>materiaEscogida.getNiveles()){
                    System.out.println("No existe tal nivel en la materia. Ingrese un nivel válido:");
                    nPreguntas = input.nextInt();
                    input.nextLine();
                }

                System.out.println("Ingrese la matricula del participante o ingrese aleatorio para seleccionar un participante aleatorio del listado:");
                participante = input.nextLine();
                //Se selecciona un participante aleatorio
                if (participante.equals("aleatorio")){
                    Random random = new Random();
                    int indice = random.nextInt(participantes.size());
                    estudiante = participantes.get(indice);
                //Se selecciona el participante deseado
                }else{
                    for(Estudiante pr: participantes){
                        if(pr.getMatricula().equals(participante)){
                        estudiante = pr;
                        }
                    }
                }
                System.out.println("El concursante seleccionado es "+estudiante.getNombre()); 
                System.out.println("");
                System.out.println("Ingrese la matricula del compañero de apoyo o ingrese aletaorio para seleccionar un companiero aleatorio del listado");
                consulta = input.nextLine();                  
                    //Se selecciona un participante aleatorio
                if (consulta.equals("aleatorio")){
                    Random random = new Random();
                    int indice = random.nextInt(participantes.size());
                    apoyo = participantes.get(indice);
                    while(participantes.get(indice).getNombre().equals(estudiante.getNombre())){
                    indice = random.nextInt(participantes.size());
                    apoyo = participantes.get(indice);
                    }
                    //Se selecciona el estudiante deseado
                }else{
                    for(Estudiante pr: participantes){
                        if(pr.getMatricula().equals(consulta)){
                            apoyo = pr;
                        }
                    }
                }
                System.out.println("El companiero de consulta seleccionado es "+apoyo.getNombre());//Se muestra al estudiante seleccionado con su info respectiva
                System.out.println("");
                System.out.println("El juego tiene la siguiente configuracion");
                System.out.println("Preguntas sobre la materia "+materiaEscogida.getNombre()+" con el total de "+materiaEscogida.getNiveles()+" niveles");
                System.out.println("Participante a jugar:"+estudiante.getNombre()+" con matricula:"+estudiante.getMatricula()+" del paralelo "+paraleloEscogido.getNumero()+"Materia: "+paraleloEscogido.getMateria());
                String premio = null;  //inicializando variables donde se guardan los datos del juego
                String fechaJuego =null;
                int tiempo=0;
                int puntuacion=0;
                TipoComodin comodin = null;
                Juego j1 = new Juego(paraleloEscogido,estudiante,apoyo,puntuacion,tiempo,premio,comodin,fechaJuego);//objeto juego que se genera en cada paritda
                j1.setFechajuego(fecha);
                System.out.println("Comenzando juego...");
                System.out.println("Cargando...");
                System.out.println("");
                int incorrectas = 0;//variables para validar respuestas correctas e incorrectas
                int correctas = 0;
                
                ArrayList<Pregunta> preguntando = materiaEscogida.getPreguntas();
                while(incorrectas<=0 && correctas<materiaEscogida.getNiveles()){//ciclo para seguir jugando hasta que el participante se 
                    //equivoque o conteste todo correctamente
                    for(Pregunta prt: preguntando){//recorrido de cada pregunta
                        ArrayList<String> respuestas = prt.mostrarOpciones(prt);//muestra de las opciones
                        System.out.println("Ingrese su respuesta o ingrese '*' para usar un comodín:");
                        String respuesta = input.nextLine();
                        boolean usando50 = false;
                        String respuesta50 = "";
                        while(respuesta.equals("*")){//ciclo para generar los comodines en caso de que el usuario lo pida
                            System.out.println("Comodines disponiles");
                            j1.mostrarComodines(comodin);
                            if(j1.getIntentoComodines()>0){//validacion que detecta cuantos comodines va usando el jugador
                                System.out.println("Ingrese comodin a usar");
                                String komodin=input.nextLine();//obtencion del comodin que quiere usar el jugador
                                comodin = TipoComodin.valueOf(komodin);
                                if(komodin.equals("Fifty_Fifty")){
                                    usando50 = true;
                                }
                                respuesta50 = j1.usarComodin( comodin, paraleloEscogido, prt, apoyo);//implmentacion del comodin 
                                System.out.println("Ingrese su respuesta");
                                respuesta = input.nextLine();
                            }else{
                                System.out.println("Se te acabaron los comodines");//lo que ocurre cuando el usuario ha usado todos sus comodines
                                System.out.println("Ingrese su respuesta");
                                break;
                            }
                        }
                        String seleccion = "";
                        if(usando50){//validacion de la seleccion del literal correcto por parte del jugador  en el comodin de 50_50
                            if(respuesta.equals("A")){
                                seleccion = respuesta50;
                            }else if(respuesta.equals("B")){
                                seleccion = respuestas.get(1);
                            }else{
                                System.out.println("Respuesta invalida");
                            }
                        }else{
                            if (respuesta.equals("A")){//validacion del literal correcto
                                seleccion = respuestas.get(0);
                            }else if(respuesta.equals("B")){
                                seleccion = respuestas.get(1);
                            }else if(respuesta.equals("C")){
                                seleccion = respuestas.get(2);
                            }else if(respuesta.equals("D")){
                                seleccion = respuestas.get(3);
                            }else if(respuesta.equals("*")){
                                System.out.println("<Usando comodin>");
                            }else{
                                System.out.println("Respuesta invalida");
                            }
                        }
                        if (seleccion.equals(prt.getCorrecta())){//si el usuario acierta aumenta la variable de respuestas correcta
                            System.out.println("respuesta correcta");
                            correctas++;
                        }else if (respuesta!=prt.getCorrecta()){//si se equivoca, pierde
                            incorrectas++;
                            System.out.println("Respuesta incorrecta, game over");
                            break;
                        }
                    }
                }
                System.out.println("Felicidades!! haz completado todos los niveles");//cuando pasa todos sus niveles, se acaba el bucle y se lo felicita
                System.out.println("Ingrese el premio a recibir para el ganador");//se le pide al profesor el premio del ganador
                premio = input.nextLine();
                j1.setPremio(premio);
                System.out.println("El participante ha ganado lo siguiente:"+j1.getPremio());
                System.out.println("Gracias por jugar");
                int preguntasRespondidas = correctas + incorrectas;
                j1.setPreguntasRespondidas(preguntasRespondidas);//obtencion del total de preguntas respondidas
                j1.setNivelJugador(correctas);//para las correctas
                juegos.add(j1);        //se aniade dicha info a a lista general de juegos en caso de que se requiera el reporte de la partida
    }
}
