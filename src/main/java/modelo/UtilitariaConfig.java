/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author jojusuar
 */
public class UtilitariaConfig {
    public static void ingresarTermino(ArrayList<Termino> terminos, Scanner input){
        System.out.println("<<INGRESANDO TÉRMINO>>");
                                        System.out.println("INGRESE AÑO ACADEMICO");
                                        int anioAcademico = input.nextInt();
                                        System.out.println("INGRESE TERMINO ACADEMICO");
                                        int tAcademico = input.nextInt();
                                        Termino t = new Termino(anioAcademico,tAcademico);
                                        terminos.add(t);
    }
    public static void editarTermino(ArrayList<Termino> terminos, Scanner input){
        System.out.println("<<EDITANDO TÉRMINO>>");
                                        System.out.println("SELECCIONE EL NÚMERO DEL TÉRMINO A EDITAR");
                                        int pEditar = input.nextInt()-1;
                                        Termino tEditar = terminos.get(pEditar);
                                        System.out.println("INGRESE EL NUEVO ANIO ACADEMICO");
                                        int nAnio = input.nextInt();
                                        System.out.println("INGRESE EL NUEVO TERMINO ACADEMICO");
                                        int nTermino = input.nextInt();
                                        tEditar.setAnio(nAnio);
                                        tEditar.setNumTermino(nTermino); 
    }
    public static void seleccionarTermino(ArrayList<Termino> terminos, Scanner input){
        System.out.println("<<SELECCIONANDO TÉRMINO>>");
                                        System.out.println("SELECCIONE EL NÚMERO DEL TÉRMINO PARA EL JUEGO");
                                        int opcion = input.nextInt()-1;
                                        Termino tJuego = terminos.get(opcion);
                                        System.out.println("Termino seleccionado:"+"PAO "+tJuego.getNumTermino()+" "+tJuego.getAnio());
                                        
    }
    public static void ingresarMateria(ArrayList<Materia> materias, Scanner input){
        //Se solicitan los datos para la creacion de la nueva materia
                                        System.out.println("<<INGRESANDO MATERIA>>");
                                        System.out.print("Ingrese el codigo de la materia: ");
                                        String codigoM = input.nextLine();
                                        System.out.print("Ingrese el nombre de la materia: ");
                                        String nombreM = input.nextLine();
                                        System.out.print("Ingrese la cantidad de niveles : ");
                                        int nivelesM = input.nextInt();
                                        input.nextLine();
                                        //Se crea un arrayList de preguntaas vacias que se llenaran en la parte de administrar preguntas.
                                        ArrayList<Pregunta> preguntas = new ArrayList<>();
                                        //Se crea la nueva materia
                                        Materia nuevaMateria = new Materia(codigoM, nombreM, nivelesM, preguntas);
                                        // se añade la materia a la lista de materias
                                        materias.add(nuevaMateria);
    }
    public static void editarMateria(ArrayList<Materia> materias, Scanner input){
        System.out.println("<<EDITANDO MATERIA>>");
                                        //Se pide nombre o codigo de materia
                                        System.out.print("Ingrese el codigo o el nombre de la materia a editar: ");
                                        String entrada = input.nextLine();
                                        for(Materia m: materias){
                                            String nMateria = m.getNombre();
                                            String cMateria = m.getCodigo();
                                            //Se compara con el nombre y codigo de cada materia con OR
                                            if ((entrada.equals(nMateria))||(entrada.equals(cMateria))){
                                                //Se pide nuevo nombre y nueva cantidad de niveles
                                                System.out.print("Ingrese nuevo nombre (ingrese '*' si no desea modificar): ");
                                                String nuevoNombre = input.nextLine();
                                                System.out.print("Ingrese nueva cantidad de niveles (ingrese '0' si no desea modificar): ");
                                                int nuevoNivel = input.nextInt();
                                                input.nextLine();
                                                //Se modifican los nombres y niveles
                                                if(!(nuevoNombre.equals("*"))){
                                                    m.setNombre(nuevoNombre);
                                                }
                                                if(nuevoNivel != 0){
                                                    m.setNiveles(nuevoNivel);
                                                }
                                            }
                                        }
                                        System.out.println("");
    }
    public static void agregarParalelo(ArrayList<Materia> materias, ArrayList<Paralelo> paralelos, ArrayList<Estudiante> participantes, Scanner input){
        //Se imprimen las materias disponibles
                                        System.out.println("Seleccione la materia en la cual se desee crear el nuevo paralelo:");
                                        int i = 0;
                                        for(Materia m: materias){
                                            String nombreMateria = m.getNombre();
                                            System.out.println(i +". "+nombreMateria);
                                            i++;
                                        }
                                        //Se selecciona la materia deseada
                                        System.out.print("Seleccione el numero de materia: ");
                                        int indiceMateria = input.nextInt();
                                        input.nextLine();
                                        Materia materiaPar = materias.get(indiceMateria);
                                        //Se ingresa el termino academico
                                        System.out.print("Ingrese termino academico (2023-1): ");
                                        String terminoA = input.nextLine();
                                        String [] cadena = terminoA.split("-");
                                        String anioc = cadena[0];
                                        int anio = Integer.parseInt(anioc);
                                        String terminoc = cadena[1];
                                        int numTermino = Integer.parseInt(terminoc);
                                        //Se crea el objeto termino
                                        Termino termino = new Termino(anio, numTermino);
                                        //Se pide el numero de paralelo
                                        System.out.print("Ingrese el numero de paralelo      : ");
                                        int numPar = input.nextInt();
                                        input.nextLine();
                                        //Se crea el objeto paralelo
                                        Paralelo paraleloGenerado = new Paralelo(participantes, materiaPar, termino, numPar);
                                        //Se añade nuestro paralelo a la lista de paralelos.
                                        paralelos.add(paraleloGenerado);
                                        System.out.println("<<PARALELO CREADO>>");
    }
    public static void eliminarParalelo(ArrayList<Materia> materias, ArrayList<Paralelo> paralelos, ArrayList<Estudiante> participantes, Scanner input){
        System.out.println("Seleccione el paralelo que desea eliminar:");
                                        int n = 0;
                                        for(Paralelo p: paralelos){
                                            System.out.println(n + ". " + p);
                                            n++;
                                        }
                                        System.out.print("Ingrese el numero del paralelo o '*' si desea cancelar: ");
                                        String seleccion = input.nextLine();
                                        if(seleccion.equals("*")){
                                            System.out.println("<<CANCELANDO>>");
                                        }else{
                                            int seleccionN = Integer.parseInt(seleccion);
                                            paralelos.remove(seleccionN);
                                            System.out.println("<<ELIMINANDO PARALELO>>");
                                        }
                                        
    }
    public static void visualizarPreguntas(ArrayList<Materia> materias, Scanner input){
        System.out.println("Seleccione la materia ingresando su código:");
                                        for(Materia m: materias){
                                            System.out.println(m);
                                        }
                                        String code = input.nextLine();
                                        for(Materia m: materias){
                                            if(code.equals(m.getCodigo())){
                                                System.out.println(m.getPreguntas());
                                            }
                                        }
    }
    public static void agregarPreguntas(ArrayList<Materia> materias, Scanner input){
       System.out.println("Seleccione la materia ingresando su código:");
                                        String code2 = input.nextLine();
                                        for(Materia m: materias){
                                            if(code2.equals(m.getCodigo())){
                                                System.out.println("Ingrese el enunciado:");
                                                String enunciado = input.nextLine();
                                                System.out.println("Ingrese el literal correcto:");
                                                String t = input.nextLine();
                                                System.out.println("Ingrese literal falso 1:");
                                                String s = input.nextLine();
                                                System.out.println("Ingrese literal falso 2:");
                                                String x = input.nextLine();
                                                System.out.println("Ingrese literal falso 3:");
                                                String y = input.nextLine();
                                                System.out.println("Ingrese el nivel de la pregunta (de 1 a "+m.getNiveles()+")");
                                                int z = input.nextInt();
                                                input.nextLine();
                                                m.setPregunta(new Pregunta(enunciado, z, t,s,x,y));
                                            }
                                        }
    }
    public static void eliminarPregunta(ArrayList<Materia> materias, Scanner input){
        System.out.println("Seleccione la materia ingresando su código:");
                                        String code3 = input.nextLine();
                                        Materia dummy1  = new Materia();
                                        Pregunta dummy2 = new Pregunta();
                                        for(Materia m: materias){
                                            if(code3.equals(m.getCodigo())){
                                                dummy1 = m;
                                                System.out.println("Ingrese el enunciado de la pregunta a eliminar:");
                                                String del = input.nextLine();
                                                for(Pregunta p: m.getPreguntas()){
                                                    if(del.equals(p.getEnunciado())){
                                                        dummy2 = p;
                                                    }
                                                }
                                            }
                                        }
                                        dummy1.removePregunta(dummy2);
    }
}
