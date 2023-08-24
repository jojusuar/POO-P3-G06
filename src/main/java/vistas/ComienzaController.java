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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Estudiante;
import modelo.Juego;
import modelo.Paralelo;
import modelo.Pregunta;
import modelo.PreguntaRespondida;
import modelo.Termino;
import modelo.TipoComodin;
import modelo.PreguntaTrucada;

public class ComienzaController implements Initializable {
    Juego juego;
    int npregunta = 0;
    int tiempo = 60;
    boolean wait = false;
    boolean die = false;
    int totaltiempo = 0;
    int cooldown = 5;
    boolean control50 = true;
    boolean controlC = true;
    boolean controlCC = true;
    ArrayList<Juego> juegosPrevios;
    Pregunta actual;
    ArrayList<Pregunta> preguntas;
    ArrayList<PreguntaRespondida> pRespondidas;
    PreguntaRespondida guardar;
    
    @FXML
    Label lbTime;
    @FXML
    Label lbCooldown;
    @FXML
    Label lbEnunciado;
    @FXML
    Button opcionA;
    @FXML
    Button opcionB;
    @FXML
    Button opcionC;
    @FXML
    Button opcionD;
    
    @FXML
    private Button consulta_companiero;
    @FXML
    private Button consulta_curso;
    @FXML
    private Button fifty_fifty;
    
    private boolean uso50 = true;
    
    @FXML
    private ImageView comodin1;
    @FXML
    private HBox panel;
    @FXML
    private Label lC;
    @FXML
    private Label lD;
    
    public void validar(Button x) throws IOException{
        pRespondidas.add(guardar);
        totaltiempo += (60-tiempo);
        if(x.getText().equals(actual.getCorrecta())){
           x.setStyle("-fx-base: green");
           nextQuestion();
        }
        else{
           x.setStyle("-fx-base: red");
           lose();
        }
    }
    
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
            die = true;
            opcionA.setDisable(true);
            opcionB.setDisable(true);
            opcionC.setDisable(true);
            opcionD.setDisable(true);
            Stage popup = new Stage();
            VBox fields = new VBox(10);
            TextField premio = new TextField("VICTORIA MAGISTRAL!!! Ingrese el premio:");
            Button save = new Button("Finalizar");
            fields.getChildren().addAll(premio,save);
            Scene layout = new Scene(fields,300,75);
            popup.setScene(layout);
            popup.show();
            save.setOnAction(ev -> {
                juego.setPremio(premio.getText());
                juego.setTiempo(totaltiempo);
                juego.setPreguntasRespondidas(npregunta);
                juego.setNivelJugador(actual.getNivel());
                juego.setpRespondidas(pRespondidas);
                juegosPrevios.add(juego);
                popup.close();
                try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/resources/memory/juegoshistorial.ser"));){
                   out.writeObject(juegosPrevios);
                   App.setRoot("primary");
               }
               catch(IOException ex){
                   ex.printStackTrace();
               }
            });
            
        }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/memory/juego.ser"));){
            juego = (Juego)in.readObject();
        }
        catch(IOException ex){
            System.out.println("Error al cargar los datos del juego");
        }
        catch(ClassNotFoundException e){
            System.out.println("No se encontró la clase Juego");
        }
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/memory/juegoshistorial.ser"));){
            juegosPrevios = (ArrayList<Juego>)in.readObject();
        }
        catch(IOException ex){
            System.out.println("Error al cargar los paralelos, creando nuevo arreglo");
            juegosPrevios = new ArrayList<>();
        }
        catch(ClassNotFoundException e){
            System.out.println("No se encontró la clase");
        }
        actual = new Pregunta();
        pRespondidas = new ArrayList<>();
        preguntas = juego.getPreguntasDelJuego();
        Collections.sort(preguntas);
        callQuestion();
        Timer contador = new Timer();
        contador.setDaemon(true);
        contador.start();
        opcionA.setOnAction(ev -> {
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
        Tooltip tooltip1 = new Tooltip();
        Tooltip tooltip2 = new Tooltip();
        Tooltip tooltip3 = new Tooltip();
        tooltip1.setText("Puedes eliminar dos opciones al azar quedando dos posibles respuestas");
        tooltip2.setText("Puedes consultar a tu companiero: "+juego.getCompanero());
        tooltip3.setText("Puedes consultar con tu curso cual puede ser la respuesta correcta");
        fifty_fifty.setTooltip(tooltip1);
        consulta_companiero.setTooltip(tooltip2);
        consulta_curso.setTooltip(tooltip3);
    }
    private void callQuestion(){
        
        if(uso50){
            
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
        }else{
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
        guardar = new PreguntaRespondida(actual);
    }

    @FXML
    private void usarFiftyFifty(ActionEvent event) {
        fifty_fifty.setDisable(true);
        control50 = false;
        guardar.setComodinUsado(TipoComodin.Fifty_Fifty);
        
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
              
              Collections.shuffle(literales);
              opcionA.setText(literales.get(0));
              opcionB.setText(literales.get(1));
              opcionC.setText("RESPUESTA ELIMINADA");
              opcionD.setText("RESPUESTA ELIMINADA");
              panel.getChildren().remove(opcionC);
              panel.getChildren().remove(opcionD);
              panel.getChildren().remove(lC);
              panel.getChildren().remove(lD);
              uso50 = false;
              //fifty_fifty.setDisable(true);
                        }
        
    }
    
    @FXML
    private void usaeCompanero(ActionEvent event) {
        guardar.setComodinUsado(TipoComodin.ConsultaCompanero);
       controlC = false; 
        System.out.println("ejeje");
        consulta_companiero.setDisable(true);
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
        int index = (int)(literales.size()*Math.random());
        String respuesta = literales.get(index);
        System.out.println(respuesta);
        for(Button opcion:opciones){
          if(opcion.getText().equals(respuesta)){
              System.out.println("hola");
           //opcion.setDisable(true);  
           opcion.setStyle("-fx-base: blue");
          }
        }
        //if(opcionA.getText().equals(respuesta)){
         //  opcionA.setStyle("-fx-background-color: blue");
         // }
       // else if (opcionB.getText().equals(respuesta)){
        
        
        }
                    //String[] liter = {"A)","B)","C)","D)"};
                    //String sugerencia = liter[index];
                   // System.out.println(Juego.getCompanero()+" cree que la respuesta es: "+sugerencia);
    

    @FXML
    private void usarCurso(ActionEvent event) {
        guardar.setComodinUsado(TipoComodin.ConsultaClase);
        controlCC = false;
        consulta_curso.setDisable(true);
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
        int index = (int)(literales.size()*Math.random());
        String respuesta = literales.get(index);
        System.out.println(respuesta);
        for(Button opcion:opciones){
          if(opcion.getText().equals(respuesta)){
              System.out.println("hola");
           //opcion.setDisable(true);  
           opcion.setStyle("-fx-base: blue");
          }
        }
    }
        
    public void lose() {
        Alert defeat = new Alert(AlertType.ERROR);
           defeat.setContentText("PERDISTE, haz click para volver al menú principal");
           die = true;
           Optional<ButtonType> result = defeat.showAndWait();
           ButtonType ok = result.orElse(ButtonType.OK);
           if(ok==ButtonType.OK){
               juego.setTiempo(totaltiempo);
               juego.setPreguntasRespondidas(npregunta+1);
               juego.setNivelJugador(actual.getNivel());
               juego.setpRespondidas(pRespondidas);
               juegosPrevios.add(juego);
               try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/resources/memory/juegoshistorial.ser"));){
                   out.writeObject(juegosPrevios);
                   App.setRoot("primary");
               }
               catch(IOException ex){
                   ex.printStackTrace();

               } 
           }
    }

    class Timer extends Thread{
        public void run(){
            while(tiempo!=0){
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
            Platform.runLater(()-> lose());
        }
    }
    
    class Countdown extends Thread{
        public void run(){
            while(cooldown!=0){
                if(die){
                    //fifty_fifty.setDisable(true);

                    stop();

                }
                        Platform.runLater(() -> lbCooldown.setText("Próx. pregunta en: "+cooldown));
                        
                        fifty_fifty.setDisable(true);
                        opcionA.setDisable(true);
                        opcionB.setDisable(true);
                        opcionC.setDisable(true);
                        opcionD.setDisable(true);
                        consulta_curso.setDisable(true);
                        consulta_companiero.setDisable(true);
                        //control = false;
                        
                        cooldown--;
                        try{
                            sleep(1000);
                        }
                        catch(InterruptedException ie){
                            ie.printStackTrace();
                        }
                    }
                    if(control50){
                    fifty_fifty.setDisable(false);}
                    opcionA.setDisable(false);
                    opcionB.setDisable(false);
                    opcionC.setDisable(false);
                    opcionD.setDisable(false);
                    if(controlCC){
                    consulta_curso.setDisable(false);}
                    if(controlC){
                    consulta_companiero.setDisable(false);}
                    cooldown = 5;
                    Platform.runLater(() -> callQuestion());
        }
    }
}
