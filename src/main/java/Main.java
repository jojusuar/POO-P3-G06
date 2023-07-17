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
        ArrayList<Pregunta> preguntasMatematica = new ArrayList<>();
        ArrayList<Materia> materias = new ArrayList<>(); 
        ArrayList<Paralelo> paralelos = new ArrayList<>();
        ArrayList<Termino> terminos = new ArrayList<>();
        ArrayList<Juego> juegos = new ArrayList<>();
        Termino t1 = new Termino(2020,1);//creando terminos academicos
        Termino t2 = new Termino(2021,1);
        Termino t3 = new Termino(2022,1);
        Materia m1 = new Materia("001","mate",3,preguntasMatematica);//generando materias
        Materia m2 = new Materia("002","fisica",3,preguntasMatematica);
        Materia m3 = new Materia("003","poo",3,preguntasMatematica);
        Paralelo p1 = new Paralelo(participantes,m1,t1,1);//creando paralelos
        Paralelo p2 = new Paralelo(participantes,m2,t2,2);
        Paralelo p3 = new Paralelo(participantes,m3,t3,3);
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
        preguntasMatematica.add(pr1);//agregando preguntas
        preguntasMatematica.add(pr2);
        preguntasMatematica.add(pr3);
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
                                    System.out.println("PAO "+t.getNumTermino()+" "+t.getAnio());
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
                                        System.out.println("<<INGRESANDO TÉRMINO>>");
                                        System.out.println("INGRESE AÑO ACADEMICO");
                                        int anioAcademico = input.nextInt();
                                        System.out.println("INGRESE TERMINO ACADEMICO");
                                        int tAcademico = input.nextInt();
                                        Termino t = new Termino(anioAcademico,tAcademico);
                                        terminos.add(t);
                                        flagTermino = true;
                                        break;
        //1.1.2. EDITAR TERMINO
                                    case 2:
                                        System.out.println("<<EDITANDO TÉRMINO>>");
                                        System.out.println("SELECCIONE EL TERMINO A EDITAR");
                                        int pEditar = input.nextInt()-1;
                                        Termino tEditar = terminos.get(pEditar);
                                        System.out.println("INGRESE EL NUEVO ANIO ACADEMICO");
                                        int nAnio = input.nextInt();
                                        System.out.println("INGRESE EL NUEVO TERMINO ACADEMICO");
                                        int nTermino = input.nextInt();
                                        tEditar.setAnio(nAnio);
                                        tEditar.setNumTermino(nTermino);                                                                                                                                                                                                  
                                        flagTermino = true;
                                        break;
        //1.1.3. CONFIGURAR TERMINO PARA JUEGO
                                    case 3:
                                        System.out.println("<<SELECCIONANDO TÉRMINO>>");
                                        System.out.println("SELECCIONE EL TERMINO PARA EL JUEGO");
                                        int opcion = input.nextInt()-1;
                                        Termino tJuego = terminos.get(opcion);
                                        System.out.println("Termino seleccionado:"+"PAO "+tJuego.getNumTermino()+" "+tJuego.getAnio());
                                        
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
                                        flagTermino = true;
                                        break;
        //1.2.2. EDITAR MATERIA
                                    case 2:
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
                                        flagTermino = true;
                                        break;
        //1.2.3. AGREGAR PARALELO
                                    case 3:
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
                                        flagTermino = true;
                                        break;
        //1.2.4. ELIMINAR PARALELO
                                    case 4:
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
                                                m.setPregunta(new Pregunta(enunciado, z, t,s,x,y));
                                            }
                                        }
                                        flagTermino = true;
                                        break;
                                    case 3:
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
            // Inicializamos valores
                Materia materiaEscogida=null;
                Paralelo paraleloEscogido=null;
                int nPreguntas;
                String participante;
                String consulta;
                flagConfig = false;
                //Creamos los estudiantes para apoyo y participante
                Estudiante estudiante = new Estudiante(null,null,null);
                Estudiante apoyo = new Estudiante(null,null,null);
                    
                System.out.println("-----Nuevo juego-----");
                //pedida de datos antes de comenzar a jugar
                
                System.out.println("Ingrese la materia");
                String materia = input.nextLine();
                System.out.println("Ingrese el paralelo");
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
                System.out.println("El companiero de consulta seleccionado es "+apoyo.getNombre());
                System.out.println("");
                System.out.println("El juego tiene la siguiente configuracion");
                System.out.println("Preguntas sobre la materia "+materiaEscogida.getNombre()+" con el total de "+materiaEscogida.getNiveles()+" niveles");
                System.out.println("Participante a jugar:"+estudiante.getNombre()+" con matricula:"+estudiante.getMatricula()+" del paralelo "+paraleloEscogido.getNumero());
                String premio = null;
                String fechaJuego =null;
                int tiempo=0;
                int puntuacion=0;
                TipoComodin comodin = null;
                Juego j1 = new Juego(paraleloEscogido,estudiante,apoyo,puntuacion,tiempo,premio,comodin,fechaJuego);
                System.out.println("Comenzando juego...");
                System.out.println("Cargando...");
                System.out.println("");
                int incorrectas = 0;
                int correctas = 0;
                
                ArrayList<Pregunta> preguntando = materiaEscogida.getPreguntas();
                while(incorrectas<=0 && correctas<materiaEscogida.getNiveles()){
                    for(Pregunta prt: preguntando){
                        ArrayList<String> respuestas = prt.mostrarOpciones(prt);
                        System.out.println("Ingrese su respuesta o ingrese '*' para usar un comodín:");
                        String respuesta = input.nextLine();
                        boolean usando50 = false;
                        String respuesta50 = "";
                        while(respuesta.equals("*")){
                            System.out.println("Comodines disponiles");
                            j1.mostrarComodines(comodin);
                            if(j1.getIntentoComodines()>0){
                                System.out.println("Ingrese comodin a usar");
                                String komodin=input.nextLine();
                                comodin = TipoComodin.valueOf(komodin);
                                if(komodin.equals("Fifty_Fifty")){
                                    usando50 = true;
                                }
                                respuesta50 = j1.usarComodin( comodin, p3, prt, apoyo);
                                System.out.println("Ingrese su respuesta");
                                respuesta = input.nextLine();
                            }else{
                                System.out.println("Se te acabaron los comodines");
                                System.out.println("Ingrese su respuesta");
                                break;
                            }
                        }
                        String seleccion = "";
                        if(usando50){
                            if(respuesta.equals("A")){
                                seleccion = respuesta50;
                            }else if(respuesta.equals("B")){
                                seleccion = respuestas.get(1);
                            }else{
                                System.out.println("Respuesta invalida");
                            }
                        }else{
                            if (respuesta.equals("A")){
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
                        if (seleccion.equals(prt.getCorrecta())){
                            System.out.println("respuesta correcta");
                            correctas++;
                        }else if (respuesta!=prt.getCorrecta()){
                            incorrectas++;
                            System.out.println("Respuesta incorrecta, game over");
                            break;
                        }
                    }
                }
                System.out.println("Felicidad!! haz completado todos los niveles");
                System.out.println("Ingrese el premio a recibir para el ganador");
                premio = input.nextLine();
                j1.setPremio(premio);
                System.out.println("El participante ha ganado lo siguiente:"+j1.getPremio());
                System.out.println("Gracias por jugar");
                int preguntasRespondidas = correctas + incorrectas;
                j1.setPreguntasRespondidas(preguntasRespondidas);
                j1.setNivelJugador(correctas);
                juegos.add(j1);          
                flagMenu = true;
                break;

//-------CASO 3 GENERAR REPORTE-------
            case 3:
                System.out.println("----Generar Reporte----");
                //solicita los datos para generar el reporte
                System.out.print("Ingrese el termino academico (2023-1): ");
                String termino = input.nextLine();
                System.out.print("Ingrese el codigo de materia:          ");
                String codigo = input.nextLine();
                System.out.print("Ingrese el numero de paralelo:         ");
                int paralelo = input.nextInt();
                input.nextLine();
                System.out.println("<<GENERANDO REPORTE>>");
                System.out.println("");
                //Crea un arrayList vacio que va a almacenar las los juegos que cumplan con los parametros
                ArrayList<Juego> juegosReporte = new ArrayList<>();
                //recorre la lista para validar uno por uno
                for(Juego j: juegos){
                    Paralelo pJuego = j.getParalelo();
                    Materia mJuego = pJuego.getMateria();
                    Termino tJuego = pJuego.getTermino();
                    String cmJuego = mJuego.getCodigo();
                    //valida el codigo de la materia
                    if(codigo.equals(cmJuego)){
                        String[] cadena = termino.split("-");
                        int aniojuego = Integer.parseInt(cadena[0]);
                        int terminojuego = Integer.parseInt(cadena[1]);
                        //Valida el termino deseado
                        if ((aniojuego == tJuego.getAnio())&&(terminojuego == tJuego.getNumTermino())){
                            //valida el paralelo deseado
                            if(paralelo == pJuego.getNumero()){
                                juegosReporte.add(j);
                            }
                        }
                    }
                }
                //Se presentan los resultados, si no hay reportes que coincidan con la busqueda se da un mensaje
                if(juegosReporte.size() == 0){
                    System.out.println("<<NO EXISTEN REPORTES QUE COINCIDAN CON EL REGISTRO>>");
                }else{
                    int cont = 1;
                    //Se presentan los juegos del reporte en orden
                    for(Juego jreporte: juegosReporte){
                        System.out.println(cont+ ". " +jreporte);
                        cont++;
                    }
                    System.out.println("");
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
