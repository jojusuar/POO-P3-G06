/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vistas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modelo.*;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;




/**
 * FXML Controller class
 *
 * @author mateo
 */
public class PresentacionReporteController implements Initializable {
    @FXML
    public Button regresar;
    @FXML
    public VBox vbReportes;
   
    private static ArrayList<Juego> listaJuegos;
    
    
    
    
    public void Regresar()throws Exception{
        App.setRoot("Reporte");
    }
    public void recibirDatos(DatosReporte datos){
        listaJuegos = datos.getListaJuegos();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Objetos juego recibidos en PresentacionReporteController: "+listaJuegos.size());
        GridPane gpPresentacion = new GridPane();
        int contador = 0;
        gpPresentacion.add(new Label("Fecha"),0,contador);
        gpPresentacion.add(new Label("Participante"),1,contador);
        gpPresentacion.add(new Label("Nivel \nMaximo"),2,contador);
        gpPresentacion.add(new Label("Tiempo"),3,contador);
        gpPresentacion.add(new Label("Preguntas \nContestadas"),4,contador);
        gpPresentacion.add(new Label("Comodines \nUsadas"),5,contador);
        gpPresentacion.add(new Label("Premio"),6,contador);
        gpPresentacion.add(new Label("Opciones"),7,contador);
        for(Juego juego: listaJuegos){
            contador++;
            String fecha = juego.getFechaJuego();
            String participante = juego.getParticipante().getNombre();
            String nivelMax = juego.getNivelJugador()+"";
            String sTiempo;
            String preguntasContestadas = juego.getPreguntasRespondidas()+"";
            String comodinesUsados = (3-juego.getIntentoComodines())+"";
            String premio;
            
            if(juego.getPremio() == null){
                premio = "NINGUN PREMIO";
            }else{
                premio = juego.getPremio();
            }
            int tiempo = juego.getTiempo();
            int minutos = tiempo/60;
            int segundos = tiempo%60;
            if((minutos<10)&(segundos<10)){
                sTiempo = "0"+minutos + ":0" + segundos;
            }else if(minutos<10){
                sTiempo = "0"+minutos + ":" + segundos;
            }else if(segundos<10){
                sTiempo = minutos + ":0" + segundos;
            }else{
                sTiempo = minutos + ":" + segundos;
            }
            Label label = new Label("Ver Detalle");
            label.setStyle("-fx-underline: true;");
            /*label.setOnMouseClicked(event -> {
                    System.out.println("Label clicado: " + label.getText());
                    // Aquí puedes agregar la lógica que deseas ejecutar al hacer clic en el Label
                });*/
            gpPresentacion.add(new Label(fecha),0,contador);
            gpPresentacion.add(new Label(participante),1,contador);
            gpPresentacion.add(new Label(nivelMax),2,contador);
            gpPresentacion.add(new Label(sTiempo),3,contador);
            gpPresentacion.add(new Label(preguntasContestadas),4,contador);
            gpPresentacion.add(new Label(comodinesUsados),5,contador);
            gpPresentacion.add(new Label(premio),6,contador);
            gpPresentacion.add(label,7,contador);
        }
        gpPresentacion.setVgap(10);
        gpPresentacion.setHgap(5);
        vbReportes.getChildren().setAll(gpPresentacion);
        
    }    
    
}
