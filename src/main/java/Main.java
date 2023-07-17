/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jojusuar
 */
import modelo.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        ArrayList<Estudiante> participantes = new ArrayList<>();
        ArrayList<Materia> materias = new ArrayList<>(); 
        ArrayList<Paralelo> paralelos = new ArrayList<>();
        ArrayList<Termino> terminos = new ArrayList<>();
        ArrayList<Juego> juegos = new ArrayList<>();
        
        materias.add(new Materia());
        
        Scanner input = new Scanner(System.in);
        int query = 0;
        boolean flagMenu= false;
        do{
            flagMenu = false;
            System.out.println("-----MENÚ PRINCIPAL-----");
            System.out.println("1. Configuraciones");
            System.out.println("2. Nuevo juego");
            System.out.println("3. Reporte");
            System.out.println("4. Salir");
            System.out.print("Ingrese el número respectivo para seleccionar una opción: ");
            query = input.nextInt();
            input.nextLine();
            System.out.println("");
            switch(query){
//-------CASO 1 CONFIGURACIONES-------
            case 1:
                int query2 = 0;
                boolean flagConfig = false;
                do{
                    System.out.println("-----Configuraciones-----");
                    System.out.println("1. Administrar términos académicos");
                    System.out.println("2. Administrar materias y paralelos");
                    System.out.println("3. Administrar preguntas");
                    System.out.println("4. Regresar");
                    System.out.print("Seleccione una opcion: ");
                    query2 = input.nextInt();
                    input.nextLine();
                    System.out.println("");
                    flagConfig = false;
                    switch(query2){
    // 1.1. ADMINISTRAR TERMINOS ACADEMICOS
                        case 1:
                            int query3 = 0;
                            boolean flagTermino = false;
                            do{
                                System.out.println("-----Administrar términos académicos-----");//
                                
                                System.out.println("<<MOSTRANDO LISTA DE TÉRMINOS>>");
                                
                                for(Termino t: terminos){
                                    int num = terminos.indexOf(t)+1;
                                    System.out.println(num+". PAO "+t.getNumTermino()+" "+t.getAnio());
                                  }
                                System.out.println("1. Ingresar término");
                                System.out.println("2. Editar término");
                                System.out.println("3. Configurar término para juego");
                                System.out.println("4. Regresar");
                                query3 = input.nextInt();
                                input.nextLine();
                                flagTermino = false;
                                switch(query3){
        //1.1.1. INGRESAR TERMINO
                                    case 1:
                                        UtilitariaConfig.ingresarTermino(terminos, input);
                                        flagTermino = true;
                                        break;
        //1.1.2. EDITAR TERMINO
                                    case 2:
                                        UtilitariaConfig.ingresarTermino(terminos, input);
                                        flagTermino = true;
                                        break;
        //1.1.3. CONFIGURAR TERMINO PARA JUEGO
                                    case 3:
                                        UtilitariaConfig.seleccionarTermino(terminos, input);
                                        flagTermino = true;
                                        break;
                                    case 4:
                                        flagConfig = true;
                                        break;
                                        
                                }
                            }while(flagTermino);
                            break;
    //1.2. ADMINISTRAR MATERIAS Y PARALELOS
                        case 2:
                            int query4 = 0;
                            flagTermino = false;
                            do{
                                System.out.println("-----Administrar materias y paralelos-----");
                                System.out.println("1. Ingresar materia");
                                System.out.println("2. Editar materia");
                                System.out.println("3. Agregar paralelo");
                                System.out.println("4. Eliminar paralelo");
                                System.out.println("5. Regresar");
                                System.out.print("Seleccione una opcion: ");
                                query4 = input.nextInt();
                                input.nextLine();
                                System.out.println("");
                                flagTermino = false;
                                switch(query4){
        //1.2.1. INGRESAR MATERIA
                                    case 1:
                                        UtilitariaConfig.ingresarMateria(materias, input);
                                        flagTermino = true;
                                        break;
        //1.2.2. EDITAR MATERIA
                                    case 2:
                                        UtilitariaConfig.editarMateria(materias, input);
                                        flagTermino = true;
                                        break;
        //1.2.3. AGREGAR PARALELO
                                    case 3:
                                        UtilitariaConfig.agregarParalelo(materias, paralelos, participantes, input);
                                        flagTermino = true;
                                        break;
        //1.2.4. ELIMINAR PARALELO
                                    case 4:
                                        

                                        flagTermino = true;
                                        break;
                                    case 5:
                                        flagConfig = true;
                                        break;
                                }
                            }while(flagTermino);
                            break;
    // 1.3. ADMINISTRAR PREGUNTAS
                        case 3:
                            int query5 = 0;
                            flagTermino = false;
                            do{
                                System.out.println("-----Administrar preguntas-----");
                                System.out.println("1. Visualizar preguntas");
                                System.out.println("2. Agregar pregunta");
                                System.out.println("3. Eliminar pregunta");
                                System.out.println("4. Regresar");
                                query5 = input.nextInt();
                                input.nextLine();
                                flagTermino = false;
                                switch(query5){
                                    case 1:
                                        UtilitariaConfig.visualizarPreguntas(materias, input);
                                        flagTermino = true;
                                        break;
                                    case 2:
                                        
                                        flagTermino = true;
                                        break;
                                    case 3:
                                       UtilitariaConfig.eliminarPregunta(materias, input);
                                        flagTermino = true;
                                        break;
                                    case 4:
                                        flagConfig = true;
                                        break;
                                }
                            }while(flagTermino);
                            break;
                        case 4:
                            flagMenu = true;
                            break;
                    }
                }while(flagConfig);
                break;
//-------CASO 2 NUEVO JUEGO-------
            case 2:
                UtilitariaJuego.jugar(input, paralelos, participantes, juegos);
                flagConfig = false;
                flagMenu = true;
                break;

//-------CASO 3 GENERAR REPORTE-------
            case 3:
                UtilitariaReporte.generarReporte(input, juegos);
                flagMenu = true;
                break;
                
            case 4:
                System.out.println("Vuelva pronto!");
                flagMenu = false;
                break;
        }
        }while(flagMenu);
        input.close();
    }
    
}
