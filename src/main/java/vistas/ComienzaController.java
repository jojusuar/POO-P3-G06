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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Juego;
import modelo.Paralelo;
import modelo.Pregunta;
import modelo.Termino;


public class ComienzaController implements Initializable {
    Juego juego;
    int npregunta = 0;
    int tiempo = 60;
    boolean wait = false;
    boolean die = false;
    int totaltiempo = 0;
    int cooldown = 5;
    ArrayList<Juego> juegosPrevios;
    Pregunta actual;
    ArrayList<Pregunta> preguntas;
    
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
    
    public void validar(Button x) throws IOException{
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
        
    }
    private void callQuestion(){
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
                    stop();
                }
                        Platform.runLater(() -> lbCooldown.setText("Próx. pregunta en: "+cooldown));
                        cooldown--;
                        try{
                            sleep(1000);
                        }
                        catch(InterruptedException ie){
                            ie.printStackTrace();
                        }
                    }
                    cooldown = 5;
                    Platform.runLater(() -> callQuestion());
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
}
