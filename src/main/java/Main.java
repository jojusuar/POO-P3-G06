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
        ArrayList<Pregunta> preguntas = new ArrayList<>();
        ArrayList<Materia> materias = new ArrayList<>(); 
        ArrayList<Paralelo> paralelos = new ArrayList<>();
        ArrayList<Termino> terminos = new ArrayList<>();
        ArrayList<Juego> juegos = new ArrayList<>();
        Termino t1 = new Termino(2023,1);//creando terminos academicos
        Termino t2 = new Termino(2023,1);
        Termino t3 = new Termino(2023,1);
        Materia m1 = new Materia("001","mate",3,preguntas);//generando materias
        Materia m2 = new Materia("001","fisica",3,preguntas);
        Materia m3 = new Materia("001","poo",3,preguntas);
        Paralelo p1 = new Paralelo(participantes,m1,t1,1);//creando paralelos
        Paralelo p2 = new Paralelo(participantes,m2,t2,2);
        Paralelo p3 = new Paralelo(participantes,m2,t3,3);
        Estudiante al1 = new Estudiante("Angello","angbeand","202105946");//generando estudiantes
        Estudiante al2 = new Estudiante("Julio","euclase","202105856");
        Estudiante al3 = new Estudiante("Mateo","MateoTuPapa","202105976");
        Pregunta pr1 = new Pregunta("cuanto es 2 +2?",1,"4","5","7","8");//generando preguntas
        Pregunta pr2 = new Pregunta("cuanto es 2 x 3?",2,"6","9","7","8");
        Pregunta pr3 = new Pregunta("cuanto es 4 /2?",3,"2","9","7","8");
        materias.add(m1);//agregando materias
        materias.add(m2);
        materias.add(m3);
        participantes.add(al1);//agregando estudiantes
        participantes.add(al2);
        participantes.add(al3);
        preguntas.add(pr1);//agregando preguntas
        preguntas.add(pr2);
        preguntas.add(pr3);
        paralelos.add(p1);//agregando paralelos
        paralelos.add(p2);
        paralelos.add(p3);
        terminos.add(t1);//agregando terminos
        terminos.add(t2);
        terminos.add(t3);
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
                                query4 = input.nextInt();
                                input.nextLine();
                                flagTermino = false;
                                switch(query4){
                                    case 1:
                                        System.out.println("<<INGRESANDO MATERIA>>");
                                        System.out.print("Ingrese el codigo de la materia: ");
                                        String codigoM = input.nextLine();
                                        System.out.print("Ingrese el nombre de la materia: ");
                                        String nombreM = input.nextLine();
                                        System.out.print("Ingrese la cantidad de niveles : ");
                                        int nivelesM = input.nextInt();
                                        input.nextLine();

                                        flagTermino = true;
                                        break;
                                    case 2:
                                        System.out.println("<<EDITANDO MATERIA>>");
                                        System.out.print("Ingrese el codigo o el nombre de la materia a editar: ");
                                        String entrada = input.nextLine();
                                        for(Materia m: materias){
                                            String nMateria = m.getNombre();
                                            String cMateria = m.getCodigo();
                                            if ((entrada.equals(nMateria))||(entrada.equals(cMateria))){
                                                System.out.print("Ingrese nuevo nombre (ingrese '*' si no desea modificar): ");
                                                String nuevoNombre = input.nextLine();
                                                System.out.print("Ingrese nueva cantidad de niveles (ingrese '0' si no desea modificar): ");
                                                int nuevoNivel = input.nextInt();
                                                input.nextLine();
                                                if(!(nuevoNombre.equals("*"))){
                                                    m.setNombre(nuevoNombre);
                                                }
                                                if(nuevoNivel != 0){
                                                    m.setNiveles(nuevoNivel);
                                                }
                                            }
                                        }
                                        System.out.println("");
                                        flagTermino = true;
                                        break;
                                    case 3:
                                        System.out.println("Seleccione la materia en la cual se desee crear el nuevo paralelo:");
                                        /*String materia = input.nextLine();
                                        System.out.print("Ingrese termino academico (####-##): ");
                                        String terminoA = input.nextLine();
                                        System.out.print("Ingrese el numero de paralelo      : ");
                                        int numPar = input.nextInt();
                                        input.nextLine();
                                        Materia materiaParalelo;
                                        System.out.println("");
                                        for(Materia m: materias){
                                            String nMateria = m.getNombre();
                                            if(materia.equals(nMateria)){
                                                materiaParalelo = m;
                                            }
                                        }
                                        
                                        Paralelo p = new Paralelo(participantes, materiaParalelo,, numPar);*/
                                        flagTermino = true;
                                        break;
                                    case 4:
                                        System.out.println("<<ELIMINANDO PARALELO>>");
                                        flagTermino = true;
                                        break;
                                    case 5:
                                        flagConfig = true;
                                        break;
                                }
                            }while(flagTermino);
                            break;
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
                                        flagTermino = true;
                                        break;
                                    case 2:
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
                                                m.setPregunta(new Pregunta(enunciado, z, t, s, x, y));
                                            }
                                        }
                                        flagTermino = true;
                                        break;
                                    case 3:
                                        System.out.println("Seleccione la materia ingresando su código:");
                                        String code3 = input.nextLine();
                                        for(Materia m: materias){
                                            if(code3.equals(m.getCodigo())){
                                                System.out.println("Ingrese el enunciado de la pregunta a eliminar:");
                                                String del = input.nextLine();
                                                for(Pregunta p: m.getPreguntas()){
                                                    if(del.equals(p.getEnunciado())){
                                                        m.removePregunta(p);
                                                    }
                                                }
                                            }
                                        }
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
                    Materia materiaEscogida=null;
                    Paralelo paraleloEscogido=null;
                    int nPreguntas;
                    String participante;
                    String consulta;
                    flagConfig = false;
                    Estudiante estudiante = new Estudiante(null,null,null);
                    Estudiante apoyo = new Estudiante(null,null,null);
                    
                    System.out.println("-----Nuevo juego-----");//pedida de datos antes de comenzar a jugar
                    System.out.println("Ingrese la materia");
                   
                    String materia = input.next();
                    
                     
                    
                    input.nextLine();
                    for(Materia elemento:materias){
                     if(materia.equals(elemento.getNombre()))
                     materiaEscogida = elemento;
                    }
                    
                    System.out.println("Ingrese el paralelo");
                    int paralel = input.nextInt();
                    input.nextLine();
                    for(Paralelo elemento:paralelos){
                     if(paralel<=elemento.getNumero()&&paralel>=elemento.getNumero()){
                     paraleloEscogido = elemento;
                     }}
                   
                    System.out.println("Ingrese los");
                    nPreguntas = input.nextInt();
                   
                    while(nPreguntas!=materias.get(0).getNiveles()){
                        System.out.println("Ingrese el numero de preguntas por nivel");
                        nPreguntas = input.nextInt();}
                    System.out.println("Ingrese la matricula del participante o ingrese aletaorio para seleccionar un participante aleatorio del listado");
                    
                    participante = input.next();
                    if (participante.equals("aleatorio")){
                        Random random = new Random();
                        int indice = random.nextInt(participantes.size());
                        estudiante = participantes.get(indice);
                    
                    }
                    else{
                       for(Estudiante pr: participantes){
                       if(pr.getMatricula().equals(participante)){
                       estudiante = pr;}
                       }
                       
                    }
                    System.out.println("El concursante seleccionado es "+estudiante.getNombre()); 
                    input.nextLine();
                    System.out.println("Ingrese la matricula del companiero de apoyo o ingrese aletaorio para seleccionar un companiero aleatorio del listado");
                    consulta = input.next();                  
                    input.nextLine();
                    
                    if (consulta.equals("aleatorio")){
                        
                        Random random = new Random();
                        int indice = random.nextInt(participantes.size());
                        apoyo = participantes.get(indice);
                        while(participantes.get(indice).getNombre().equals(estudiante.getNombre())){
                        indice = random.nextInt(participantes.size());
                        apoyo = participantes.get(indice);
                        }
                       
                    
                    
                    }
                    else{
                       for(Estudiante pr: participantes){
                       if(pr.getMatricula().equals(consulta)){
                       apoyo = pr;}
                       }
                       
                    }
                    System.out.println("El companiero de consulta seleccionado es "+apoyo.getNombre());
                    System.out.println("El juego tiene la siguiente configuracion");
                    System.out.println("Preguntas sobre la materia "+materiaEscogida.getNombre()+" con el total de "+materiaEscogida.getNiveles()+" niveles");
                    System.out.println("Participante a jugar:"+estudiante.getNombre()+" con matricula:"+estudiante.getMatricula()+" del paralelo "+paraleloEscogido.getNumero());
                    String premio = null;
                    String fechaJuego =null;
                   
                    int tiempo=0;
                    int puntuacion=0;
                    
                    TipoComodin comodin = null;
                    Juego j1 = new Juego(materiaEscogida,paraleloEscogido,estudiante,apoyo,puntuacion,tiempo,premio,comodin,fechaJuego);
                    System.out.println("Comenzando juego...");
                    System.out.println("Cargando...");
                    int incorrectas = 0;
                    int correctas = 0;
                    String correcta;
                    
                     ArrayList<Pregunta> preguntando = materiaEscogida.getPreguntas();
                     for(Pregunta prt: preguntando){
                       prt.mostrarOpciones(prt);
                         System.out.println("Ingrese su respuesta (no el literal) o ingrese * para usar un comodín:");
                         String respuesta = input.next();
                         input.nextLine();
                         if (respuesta.equals(prt.getCorrecta())){
                             System.out.println("respuesta correcta");
                         }
                         else if (respuesta.equals("*")){
                             System.out.println("Comodines disponiles");
                             j1.mostrarComodines(comodin);
                             System.out.println("Ingrese comodin a usar");
                             String komodin=input.next();
                             input.nextLine();
                             switch(komodin){
                                 case "Fifty_Fifty":
                                  j1.usarComodin(comodin.Fifty_Fifty, paraleloEscogido, prt, apoyo);
                                     //prt.mostrarOpciones(prt);
                                     System.out.println("Ingrese su respuesta:");
                                     respuesta = input.next();
                                     input.nextLine();
                             
                             }
                         }
                            
                         
                         }
                     juegos.add(j1);  
                 
                    
                                  
                    flagMenu = true;
                     
                
                break;

                
            case 3:
                System.out.println("----Generar Reporte----");
                System.out.print("Ingrese el termino academico: ");
                String termino = input.nextLine();
                System.out.print("Ingrese el codigo de materia: ");
                String codigo = input.nextLine();
                System.out.print("Ingrese el paralelo:          ");
                String paralelo = input.nextLine();
                System.out.println("<<GENERANDO REPORTE>>");
                for(Juego j: juegos){
                    System.out.println(j);
                }
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
