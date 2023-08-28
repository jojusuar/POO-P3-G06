/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vistas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Estudiante;
import modelo.Juego;
import modelo.Paralelo;
import modelo.Pregunta;
import modelo.PreguntaRespondida;
import modelo.TipoComodin;
import modelo.PreguntaTrucada;

/**
 * Visualiza los elementos del Juego.
 */
public class ComienzaController implements Initializable {
    Juego juego;//objeto donde se registran los datos para comenzar el juego
    int npregunta = 0;
    int tiempo = 60;//tiempo del juego
    boolean wait = false;
    boolean die = false;
    int totaltiempo = 0;//tiempo total del juego
    int cooldown = 5;
    boolean control50 = true;//control de la habilitacion de botones
    boolean controlC = true;
    boolean controlCC = true;
    ArrayList<Juego> juegosPrevios;//arraylist donde se guardan todas las partidas jugadas
    Pregunta actual;//objeto donde estan las preguntas con sus opciones
    ArrayList<Pregunta> preguntas;//registro de todas preguntas del juego
    ArrayList<PreguntaRespondida> pRespondidas;//registro de las preguntas respondidas
    PreguntaRespondida guardar;//objeto donde se guarda la pregunta respondida por el usuario
    
    @FXML
    Label lbTime;//pantalla del tiempo
    @FXML
    Label lbCooldown;
    @FXML
    Label lbEnunciado;//pantalla del enunciado
    @FXML//opciones
    Button opcionA;
    @FXML
    Button opcionB;
    @FXML
    Button opcionC;
    @FXML
    Button opcionD;
    
    @FXML//comodines
    private Button consulta_companiero;
    @FXML
    private Button consulta_curso;
    @FXML
    private Button fifty_fifty;
    
    private boolean uso50 = true;//control del comodin 50_50
    

    @FXML
    private HBox panel;//panel de los comodines
    @FXML//letras D y C que se descartan cuando se use el comodin de 50_50
    private Label lC;
    @FXML
    private Label lD;

    /**
     * Valida si el botón accionado es el correcto.
     *
     * @param x Botón de JavaFX
     * @throws IOException
     */
    public void validar(Button x) throws IOException{
        pRespondidas.add(guardar);
        totaltiempo += (60-tiempo);//comparacion de la respuesta seleccionado con la respuesta correcta en el objeto actual
        if(x.getText().equals(actual.getCorrecta())){
           x.setStyle("-fx-base: green");
           nextQuestion();
        }
        else{
           x.setStyle("-fx-base: red");
           lose();
        }
    }

    /**
     * Actualiza la ventana y las variables al pasar de pregunta, o finaliza el
     * juego si se contestó todo correctamente.
     *
     * @throws IOException
     */
    public void nextQuestion() throws IOException{
        npregunta ++; 
        tiempo = 60;
        try{
          System.out.println(preguntas.get(npregunta));
          wait = true;
          Countdown cool = new Countdown();
          cool.setDaemon(true);
          cool.start();
        }
        catch(IndexOutOfBoundsException ie){
            die = true;//se deshabilitan las opciones despues de que el usuario termine el juego
            opcionA.setDisable(true);
            opcionB.setDisable(true);
            opcionC.setDisable(true);
            opcionD.setDisable(true);
            Stage popup = new Stage();
            VBox fields = new VBox(10);
            TextField premio = new TextField("VICTORIA MAGISTRAL!!! Ingrese el premio:");
            Button save = new Button("Finalizar");
            fields.getChildren().addAll(premio,save);//se guarda el premio ingresado
            Scene layout = new Scene(fields,300,75);
            popup.setScene(layout);
            popup.show();//se muestra la escena que aparece cuando el jugador gana el juego
            save.setOnAction(ev -> {//se guarda en el objeto juego toda la info de la partida jugada
                juego.setPremio(premio.getText());
                juego.setTiempo(totaltiempo);
                juego.setPreguntasRespondidas(npregunta);
                juego.setNivelJugador(actual.getNivel());
                juego.setpRespondidas(pRespondidas);
                juegosPrevios.add(juego);//se aniade la partida 
                popup.close();
                try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/resources/memory/juegoshistorial.ser"));){
                   out.writeObject(juegosPrevios);//se registra en al archivo juegohisorial.ser la partida jugada
                   App.setRoot("primary");//se regresa al menu principal
               }
               catch(IOException ex){
                   ex.printStackTrace();
               }
            });
            
        }
        
    }

    /**
     * Inicializa los elementos gráficos del Juego y los hilos que controlan el
     * temporizador.
     *
     * @param url
     * @param rb
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/memory/juego.ser"));){
            juego = (Juego)in.readObject();
        }
        catch(IOException ex){//se revisa si no estan bien cargado los datos del juego
            System.out.println("Error al cargar los datos del juego");
        }
        catch(ClassNotFoundException e){//verifica la clase juego
            System.out.println("No se encontró la clase Juego");
        }
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/memory/juegoshistorial.ser"));){
            juegosPrevios = (ArrayList<Juego>)in.readObject();//lee el archivo juegoshitorial.ser
        }
        catch(IOException ex){//verifica los paralelos
            System.out.println("Error al cargar los paralelos, creando nuevo arreglo");
            juegosPrevios = new ArrayList<>();//se crea EL ArrayList de juegosPrevios
        }
        catch(ClassNotFoundException e){
            System.out.println("No se encontró la clase");
        }
        actual = new Pregunta();
        pRespondidas = new ArrayList<>();
        preguntas = juego.getPreguntasDelJuego();
        Collections.sort(preguntas);//se ordenan las preguntas
        callQuestion();
        Timer contador = new Timer();
        contador.setDaemon(true);
        contador.start();
        opcionA.setOnAction(ev -> {//validacoon de las opciones
                try{
                    validar(opcionA);
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            });
        opcionB.setOnAction(ev -> {
                try{
                    validar(opcionB);
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            });
        opcionC.setOnAction(ev -> {
                try{
                    validar(opcionC);
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            });
        opcionD.setOnAction(ev -> {
                try{
                    validar(opcionD);
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            });
        Tooltip tooltip1 = new Tooltip();//informacion de lo que hace cada comodin en los botones de los comodines
        Tooltip tooltip2 = new Tooltip();
        Tooltip tooltip3 = new Tooltip();
        tooltip1.setText("Puedes eliminar dos opciones al azar quedando dos posibles respuestas");
        tooltip2.setText("Puedes consultar a tu companiero: "+juego.getCompanero());
        tooltip3.setText("Puedes consultar con tu curso cual puede ser la respuesta correcta");
        fifty_fifty.setTooltip(tooltip1);
        consulta_companiero.setTooltip(tooltip2);
        consulta_curso.setTooltip(tooltip3);
    }

    /**
     * Actualiza los elementos de la interfaz al pasar de pregunta.
     */
    private void callQuestion(){//se generan las preguntas y opciones nuevas al inicio y despues de ir  a la siguiente pregnta
        
        if(uso50){
            
            actual = preguntas.get(npregunta);//preguntas
            lbEnunciado.setText(actual.getEnunciado());//enunciado nuevo
            ArrayList<String> literales = new ArrayList<>();//creacion del ArrayList que va a guardar las opciones
            literales.add(actual.getCorrecta());
            literales.add(actual.getPosible1());
            literales.add(actual.getPosible2());
            literales.add(actual.getPosible3());
            Collections.shuffle(literales);//ordena las opciones de forma aleatoria
            opcionA.setText(literales.get(0));//se colocan las opciones en cada boton
            opcionB.setText(literales.get(1));
            opcionC.setText(literales.get(2));
            opcionD.setText(literales.get(3));
            opcionA.setStyle("-fx-base: lightgrey");//se le coloca un color caracteristico 
            opcionB.setStyle("-fx-base: lightgrey");
            opcionC.setStyle("-fx-base: lightgrey");
            opcionD.setStyle("-fx-base: lightgrey");
        }else{//lo que ocurre cuando el comodin de 50_50 no ha sido seleccionado 
            panel.getChildren().addAll(lC,opcionC,lD,opcionD);
            actual = preguntas.get(npregunta);
            lbEnunciado.setText(actual.getEnunciado());
            ArrayList<String> literales = new ArrayList<>();
            literales.add(actual.getCorrecta());
            literales.add(actual.getPosible1());
            literales.add(actual.getPosible2());
            literales.add(actual.getPosible3());
            Collections.shuffle(literales);
            opcionA.setText(literales.get(0));
            opcionB.setText(literales.get(1));
            opcionC.setText(literales.get(2));
            opcionD.setText(literales.get(3));
            opcionA.setStyle("-fx-base: lightgrey");
            opcionB.setStyle("-fx-base: lightgrey");
            opcionC.setStyle("-fx-base: lightgrey");
            opcionD.setStyle("-fx-base: lightgrey");
            uso50 = true;
        }
        guardar = new PreguntaRespondida(actual);//se crea el objeto guardar con actual
    }


    /**
     *
     * @param event
     */
    @FXML
    private void usarFiftyFifty(ActionEvent event) {
        fifty_fifty.setDisable(true);//deshabilito el comodin cuando lo uso
        control50 = false;
        guardar.setComodinUsado(TipoComodin.Fifty_Fifty);//se guarda el comodin usado 
        
        ArrayList<String> literales = new ArrayList<>();
        literales.add(actual.getCorrecta());
        literales.add(actual.getPosible1());
        literales.add(actual.getPosible2());
        literales.add(actual.getPosible3());
        literales.remove(literales.get(0));//saco la respuesta correcta de la lista
                    for(int i=0;i<2;i++){ //elimino una respuesta falsa por iteración
                        int index = (int)(literales.size()*Math.random());
                        
                        literales.remove(literales.get(index));
                    }
        PreguntaTrucada preguntaTrucada = new PreguntaTrucada(actual.getEnunciado(), actual.getNivel(), actual.getCorrecta(), literales.get(0)); //creo una copia especial de la pregunta en la cual solo hay la respuesta correcta y una falsa
                    System.out.println("A) "+preguntaTrucada.getCorrecta());
                    System.out.println("B) "+preguntaTrucada.getPosible());
                    String rspt50 = preguntaTrucada.getCorrecta();
                    literales.add(actual.getCorrecta());
        TipoComodin comodin = TipoComodin.valueOf("Fifty_Fifty");
        String respuesta50 = "";
        Paralelo paraleloEscogido = juego.getParalelo();
        actual = preguntas.get(npregunta);
        Estudiante apoyo =juego.getCompanero();        
        uso50 = true;
        respuesta50 = juego.usarComodin( comodin, paraleloEscogido, actual, apoyo);
        if(uso50){//validacion de la seleccion del literal correcto por parte del jugador  en el comodin de 50_50
              
              Collections.shuffle(literales);//se desordenan las opciones
              opcionA.setText(literales.get(0));//se colocan las opciones aleatoiras
              opcionB.setText(literales.get(1));
              opcionC.setText("RESPUESTA ELIMINADA");//opciones que se eliminan
              opcionD.setText("RESPUESTA ELIMINADA");
              panel.getChildren().remove(opcionC);
              panel.getChildren().remove(opcionD);
              panel.getChildren().remove(lC);//ya no se muestran en el juego las opciones C y D
              panel.getChildren().remove(lD);
              uso50 = false;
             
                        }
        
    }

    /**
     *
     * @param event
     */
    @FXML
    private void usaeCompanero(ActionEvent event) {
        guardar.setComodinUsado(TipoComodin.ConsultaCompanero);//guardo el comodin si lo uso
       controlC = false; 
       
        consulta_companiero.setDisable(true);//deshabilito el boton del comodin cuando lo use
        ArrayList<String> literales = new ArrayList<>();//creo las opciones de nuevo
        ArrayList<Button> opciones = new ArrayList<>();
        opciones.add(opcionA);
        opciones.add(opcionB);
        opciones.add(opcionC);
        opciones.add(opcionD);
        literales.add(actual.getCorrecta());
        literales.add(actual.getPosible1());
        literales.add(actual.getPosible2());
        literales.add(actual.getPosible3());
        int index = (int)(literales.size()*Math.random());//se lecciono una opcion al azar 
        String respuesta = literales.get(index);
        System.out.println(respuesta);
        for(Button opcion:opciones){
          if(opcion.getText().equals(respuesta)){//se recomienda la opcion escogida como si fuera la correcta
 
           opcion.setStyle("-fx-base: blue");
          }
        }      
        }        
    /**
     *
     * @param event
     */
    @FXML
    private void usarCurso(ActionEvent event) {
        guardar.setComodinUsado(TipoComodin.ConsultaClase);//guarda el comodin si se lo usa
        controlCC = false;
        consulta_curso.setDisable(true);//deshabilita el comodin si se lo usa
        ArrayList<String> literales = new ArrayList<>();
        ArrayList<Button> opciones = new ArrayList<>();
        opciones.add(opcionA);
        opciones.add(opcionB);
        opciones.add(opcionC);
        opciones.add(opcionD);
        literales.add(actual.getCorrecta());
        literales.add(actual.getPosible1());
        literales.add(actual.getPosible2());
        literales.add(actual.getPosible3());
        int index = (int)(literales.size()*Math.random());//se selecciona una opcion al azar
        String respuesta = literales.get(index);
        System.out.println(respuesta);
        for(Button opcion:opciones){
          if(opcion.getText().equals(respuesta)){//se la recomienda como la correcta
           opcion.setStyle("-fx-base: blue");
          }
        }
    }

    /**
     * Método que cierra el Juego y guarda las variables al perder.
     */
    public void lose() {
        Alert defeat = new Alert(AlertType.ERROR);//se muestra el mensaje en el caso de excoger la opcion incorrecta
           defeat.setContentText("PERDISTE, haz click para volver al menú principal");
           die = true;
           Optional<ButtonType> result = defeat.showAndWait();
           ButtonType ok = result.orElse(ButtonType.OK);
           if(ok==ButtonType.OK){
               juego.setTiempo(totaltiempo);//se crea el objeto juego con la info de la partida jugada
               juego.setPreguntasRespondidas(npregunta+1);
               juego.setNivelJugador(actual.getNivel());
               juego.setpRespondidas(pRespondidas);
               juegosPrevios.add(juego);//se agrega al arrayList de juegoPrevios
               try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/resources/memory/juegoshistorial.ser"));){
                   out.writeObject(juegosPrevios);
                   App.setRoot("primary");//se guarda la partida juga en el archivo del historial de juegos
               }
               catch(IOException ex){
                   ex.printStackTrace();

               } 
           }
    }

    /**
     * Hilo que controla el tiempo para responder. Al acabarse el tiempo, llama
     * a lose().
     */
    class Timer extends Thread{
        public void run(){
            while(tiempo!=0){//tiempo del juego, cuando llegue igual a cero se acaba el juego
                if(wait){
                    try{
                    sleep(5000); 
                    wait=false;
                }
                catch(InterruptedException ie ){
                    ie.printStackTrace();
                }
                    
                }
                else if(die){
                    stop();
                }
                else{
                    Platform.runLater(()-> lbTime.setText("TIEMPO RESTANTE: "+tiempo) );
                tiempo--;
                try{
                    sleep(1000);
                }
                catch(InterruptedException ie ){
                    ie.printStackTrace();
                }
                }
            }
            Platform.runLater(()-> lose());//si se acaba el juego se llama a lose lo que quiere decir que se pierde la partida 
        }
    }

    /**
     *Hilo que controla el tiempo de descanso entre preguntas. Mientras corre, el hilo de clase Timer se pausa y los botones se bloquean.
     */
    class Countdown extends Thread{
        public void run(){
            while(cooldown!=0){
                if(die){
                   

                    stop();

                }
                        Platform.runLater(() -> lbCooldown.setText("Próx. pregunta en: "+cooldown));
                        
                        fifty_fifty.setDisable(true);//se deshabilitan todo los botones, despues de que se selecciona una respuesta 
                        opcionA.setDisable(true);
                        opcionB.setDisable(true);
                        opcionC.setDisable(true);
                        opcionD.setDisable(true);
                        consulta_curso.setDisable(true);
                        consulta_companiero.setDisable(true);
                        
                        
                        cooldown--;
                        try{
                            sleep(1000);
                        }
                        catch(InterruptedException ie){
                            ie.printStackTrace();
                        }
                    }
                    if(control50){//queda deshabilitado el comodin por el resto del juego si ya se lo usó
                    fifty_fifty.setDisable(false);}
                    opcionA.setDisable(false);//se vuelven a habilitar las opciones despues de pasar a la siguiente pregunta
                    opcionB.setDisable(false);
                    opcionC.setDisable(false);
                    opcionD.setDisable(false);
                    if(controlCC){//se deshabilita el comodin consulta_curso por el resto del juego despues de haberlo usado
                    consulta_curso.setDisable(false);}
                    if(controlC){//se deshabilita el comodin conculta_companiero por el resto del juego despues de haberlo usado
                    consulta_companiero.setDisable(false);}
                    cooldown = 5;
                    Platform.runLater(() -> callQuestion());
        }
    }
}

