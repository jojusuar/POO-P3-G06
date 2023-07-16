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
public class Main {
    public static void main(String[] args){
        
        Scanner input = new Scanner(System.in);
        int query = 0;
        boolean flagMenu= false;
        do{
            flagMenu = false;
            System.out.println("-----MENÚ PRINCIPAL-----");
            System.out.println("Ingrese el número para elegir una acción: ");
            System.out.println("1. Configuraciones");
            System.out.println("2. Nuevo juego");
            System.out.println("3. Reporte");
            System.out.println("4. Salir");
            query = input.nextInt();
            input.nextLine();
            switch(query){
            case 1:
                int query2 = 0;
                boolean flagConfig = false;
                do{
                    System.out.println("-----Configuraciones-----");
                    System.out.println("1. Administrar términos académicos");
                    System.out.println("2. Administrar materias y paralelos");
                    System.out.println("3. Administrar preguntas");
                    System.out.println("4. Regresar");
                    query2 = input.nextInt();
                    input.nextLine();
                    flagConfig = false;
                    switch(query2){
                        case 1:
                            int query3 = 0;
                            boolean flagTermino = false;
                            do{
                                System.out.println("-----Administrar términos académicos-----");
                                System.out.println("<<MOSTRANDO LISTA DE TÉRMINOS>>");
                                System.out.println("1. Ingresar término");
                                System.out.println("2. Editar término");
                                System.out.println("3. Configurar término para juego");
                                System.out.println("4. Regresar");
                                query3 = input.nextInt();
                                input.nextLine();
                                flagTermino = false;
                                switch(query3){
                                    case 1:
                                        System.out.println("<<INGRESANDO TÉRMINO>>");
                                        flagTermino = true;
                                        break;
                                    case 2:
                                        System.out.println("<<EDITANDO TÉRMINO>>");
                                        flagTermino = true;
                                        break;
                                    case 3:
                                        System.out.println("<<SELECCIONANDO TÉRMINO>>");
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
            
            case 2:
                int query4 = 0;
                flagConfig = false;
                do{
                    System.out.println("-----Nuevo juego-----");
                    System.out.println("1. Administrar términos académicos");
                    System.out.println("2. Administrar materias y paralelos");
                    System.out.println("3. Administrar preguntas");
                    System.out.println("4. Regresar");
                    query4 = input.nextInt();
                    input.nextLine();
                    flagConfig = false;
                    switch(query4){
                        case 1:
                            int query5 = 0;
                            boolean flagTermino = false;
                            do{
                                System.out.println("-----Administrar términos académicos-----");
                                System.out.println("<<MOSTRANDO LISTA DE TÉRMINOS>>");
                                System.out.println("1. Ingresar término");
                                System.out.println("2. Editar término");
                                System.out.println("3. Configurar término para juego");
                                System.out.println("4. Regresar");
                                query5 = input.nextInt();
                                input.nextLine();
                                flagTermino = false;
                                switch(query5){
                                    case 1:
                                        System.out.println("<<INGRESANDO TÉRMINO>>");
                                        flagTermino = true;
                                        break;
                                    case 2:
                                        System.out.println("<<EDITANDO TÉRMINO>>");
                                        flagTermino = true;
                                        break;
                                    case 3:
                                        System.out.println("<<SELECCIONANDO TÉRMINO>>");
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
                
            case 3:
                System.out.println("----Generar Reporte----");
                System.out.println("Ingrese el termino academico: ");
                String termino = input.nextLine();
                System.out.println("Ingrese el codigo de materia: ");
                String codigo = input.nextLine();
                System.out.println("Ingrese el paralelo:          ");
                String paralelo = input.nextLine();
                System.out.println("<<GENERANDO REPORTE>>");
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
