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

public class Main {
    public static void main(String[] args){
        ArrayList<Estudiante> participantes = new ArrayList<>();
        ArrayList<Materia> materias = new ArrayList<>(); 
        ArrayList<Paralelo> paralelos = new ArrayList<>();
        ArrayList<Termino> terminos = new ArrayList<>();
        ArrayList<Juego> juegos = new ArrayList<>();
        

        //OBJETOS REQUERIDOS PARA TESTEAR SEGÚN EL DOCUMENTO
        ArrayList<Pregunta> preguntasPOO = new ArrayList<>();
        int year = 2023;
        int periodo = 1;
        int lvlmat = 2;
        int lvlpreg1 = 1;
        int lvlpreg2 = 2;
        Termino t1 = new Termino(year, periodo);
        Pregunta p1 = new Pregunta("Pregunta 1", lvlpreg1, "si", "no","no","no");
        Pregunta p2 = new Pregunta("Pregunta 2", lvlpreg1, "si", "no","no","no");
        Pregunta p3 = new Pregunta("Pregunta 3", lvlpreg1, "si", "no","no","no");
        Pregunta p4 = new Pregunta("Pregunta 4", lvlpreg2, "si", "no","no","no");
        Pregunta p5 = new Pregunta("Pregunta 5", lvlpreg2, "si", "no","no","no");
        Pregunta p6 = new Pregunta("Pregunta 6", lvlpreg2, "si", "no","no","no");
        terminos.add(t1);
        preguntasPOO.add(p1);
        preguntasPOO.add(p2);
        preguntasPOO.add(p3);
        preguntasPOO.add(p4);
        preguntasPOO.add(p5);
        preguntasPOO.add(p6);
        Materia poo = new Materia("CCPG1052", "POO",lvlmat,preguntasPOO);
        materias.add(poo);
        participantes.add(new Estudiante("ACELDO TORRES MARIA GRAZIA", "maactorr@espol.edu.ec", "202110136"));
        participantes.add(new Estudiante("AGUILAR TINOCO JEAN CARLOS", "jcaguila@espol.edu.ec", "202108643"));
        participantes.add(new Estudiante("AMORETTI SANCHEZ JUAN CARLOS", "jamorett@espol.edu.ec", "202111928"));
        participantes.add(new Estudiante("ANDRADE VELASCO ANGELLO BERNIE", "angbeand@espol.edu.ec", "202105946"));
        participantes.add(new Estudiante("ARAUJO ORTEGA DIEGO ENZO JAVIER", "dienarau@espol.edu.ec", "202211355"));
        int numcurso = 3;
        Paralelo curso = new Paralelo(participantes, poo, t1, numcurso );
        paralelos.add(curso);
        
        //MENÚ PRINCIPAL
        Scanner input = new Scanner(System.in);
        int query = 0;
        boolean flagMenu= false; // declaro una bandera para volver al menú principal
        do{
            flagMenu = false; // cada iteración la devuelvo a false, para que el programa no corra eternamente
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
                boolean flag2 = false; //bandera para volver al segundo nivel del menú
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
                    flag2 = false;
                    switch(query2){
    // 1.1. ADMINISTRAR TERMINOS ACADEMICOS
                        case 1:
                            int query3 = 0;
                            boolean flag3 = false; // bandera para volver al tercer nivel del menú al ejecutar métodos utilitarios
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
                                flag3 = false; 
                                switch(query3){
        //1.1.1. INGRESAR TERMINO
                                    case 1:
                                        UtilitariaConfig.ingresarTermino(terminos, input);
                                        flag3 = true; // al hacer true esta bandera, toda opción de cuarto nivel que la ejecute vuelve al tercer nivel automáticamente tras realizarse
                                        break;
        //1.1.2. EDITAR TERMINO
                                    case 2:
                                        UtilitariaConfig.editarTermino(terminos, input);
                                        flag3 = true;
                                        break;
        //1.1.3. CONFIGURAR TERMINO PARA JUEGO
                                    case 3:
                                        UtilitariaConfig.seleccionarTermino(terminos, input);
                                        flag3 = true;
                                        break;
                                    case 4:
                                        flag2 = true; // esta bandera nos permite volver al nivel dos del menú
                                        break;
                                        
                                }
                            }while(flag3);
                            break;
    //1.2. ADMINISTRAR MATERIAS Y PARALELOS
                        case 2:
                            int query4 = 0;
                            flag3 = false;
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
                                flag3 = false;
                                switch(query4){
        //1.2.1. INGRESAR MATERIA
                                    case 1:
                                        UtilitariaConfig.ingresarMateria(materias, input);
                                        flag3 = true;
                                        break;
        //1.2.2. EDITAR MATERIA
                                    case 2:
                                        UtilitariaConfig.editarMateria(materias, input);
                                        flag3 = true;
                                        break;
        //1.2.3. AGREGAR PARALELO
                                    case 3:
                                        UtilitariaConfig.agregarParalelo(materias, paralelos, participantes, input);
                                        flag3 = true;
                                        break;
        //1.2.4. ELIMINAR PARALELO
                                    case 4:
                                        UtilitariaConfig.eliminarParalelo(materias, paralelos, participantes, input);
                                        flag3 = true;
                                        break;
                                    case 5:
                                        flag2 = true;
                                        break;
                                }
                            }while(flag3);
                            break;
    // 1.3. ADMINISTRAR PREGUNTAS
                        case 3:
                            int query5 = 0;
                            flag3 = false;
                            do{
                                System.out.println("-----Administrar preguntas-----");
                                System.out.println("1. Visualizar preguntas");
                                System.out.println("2. Agregar pregunta");
                                System.out.println("3. Eliminar pregunta");
                                System.out.println("4. Regresar");
                                query5 = input.nextInt();
                                input.nextLine();
                                flag3 = false;
                                switch(query5){
                                    case 1:
                                        UtilitariaConfig.visualizarPreguntas(materias, input);
                                        flag3 = true;
                                        break;
                                    case 2:
                                        UtilitariaConfig.agregarPreguntas(materias, input);
                                        flag3 = true;
                                        break;
                                    case 3:
                                       UtilitariaConfig.eliminarPregunta(materias, input);
                                        flag3 = true;
                                        break;
                                    case 4:
                                        flag2 = true;
                                        break;
                                }
                            }while(flag3);
                            break;
                        case 4:
                            flagMenu = true;
                            break;
                    }
                }while(flag2);
                break;
//-------CASO 2 NUEVO JUEGO-------
            case 2:
                UtilitariaJuego.jugar(input, paralelos, participantes, juegos);
                flag2 = false;
                flagMenu = true;
                break;

//-------CASO 3 GENERAR REPORTE-------
            case 3:
                UtilitariaReporte.generarReporte(input, juegos);
                flagMenu = true;
                break;
                
            case 4:
                System.out.println("Vuelva pronto!");
                flagMenu = false; //con esto, el while se rompe y se cierra el programa
                break;
        }
        }while(flagMenu);
        input.close(); //se cierra Scanner
    }
    
}
