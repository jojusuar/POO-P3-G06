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
import javafx.geometry.Pos;
import mail.Email;




/**
 * FXML Controller class
 *
 * @author mateo
 */
public class PresentacionReporteController implements Initializable {

    /**
     *
     */
    @FXML
    public Button regresar;

    /**
     *
     */
    @FXML
    public VBox vbReportes;
    @FXML
    GridPane gpPresentacion;
    private static ArrayList<Juego> listaJuegos;
    
    /**
     *
     * @throws Exception
     */
    public void Regresar()throws Exception{
        App.setRoot("Reporte");
    }

    /**
     *
     * @param datos
     */
    public void recibirDatos(DatosReporte datos){
        listaJuegos = datos.getListaJuegos();
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //print para control de informacion
        System.out.println("Objetos juego recibidos en PresentacionReporteController: "+listaJuegos.size());
        //Se crea el gridPane donde se van a ubicar los juegos previos
        gpPresentacion = new GridPane();
        //se crea una variable contador que definira las filas de nuestro GridPane
        int contador = 0;
        //Se crean los labels del titulo
        Label lbF = new Label("Fecha");
        lbF.setStyle("-fx-font-weight: bold;");
        Label lbPa = new Label("Participante");
        lbPa.setStyle("-fx-font-weight: bold;");
        Label lbNM = new Label("Nivel \nMaximo");
        lbNM.setStyle("-fx-font-weight: bold;");
        Label lbT = new Label("Tiempo");
        lbT.setStyle("-fx-font-weight: bold;");
        Label lbPC = new Label("Preguntas \nContestadas");
        lbPC.setStyle("-fx-font-weight: bold;");
        Label lbCU = new Label("Comodines \nUsados");
        lbCU.setStyle("-fx-font-weight: bold;");
        Label lbPr = new Label("Premio");
        lbPr.setStyle("-fx-font-weight: bold;");
        Label lbO = new Label("Opciones");
        lbO.setStyle("-fx-font-weight: bold;");
        //Se añaden los labels al GridPane
        gpPresentacion.add(lbF,0,contador);
        gpPresentacion.add(lbPa,1,contador);
        gpPresentacion.add(lbNM,2,contador);
        gpPresentacion.add(lbT,3,contador);
        gpPresentacion.add(lbPC,4,contador);
        gpPresentacion.add(lbCU,5,contador);
        gpPresentacion.add(lbPr,6,contador);
        gpPresentacion.add(lbO,7,contador);
        //Se añaden cada uno de los juegos al gridPane mediante un metodo iterativo
        for(Juego juego: listaJuegos){
            contador++;
            String fecha = juego.getFechaJuego();
            String participante = juego.getParticipante().getNombre();
            String nivelMax = juego.getNivelJugador()+"";
            String sTiempo;
            ArrayList<PreguntaRespondida> listaPreguntas = juego.getpRespondidas();
            String preguntasContestadas = listaPreguntas.size()+"";
            int numComUsados = 0;
            for(PreguntaRespondida p: listaPreguntas){
                TipoComodin t1 = p.getComodinUsado();
                System.out.print(t1+"---");
                if(t1 == TipoComodin.Ninguno){
                    System.out.println("No se usa comodin");
                }else{
                    System.out.println("Si se usa comodin");
                    numComUsados++;
                }
            }
            String comodinesUsados = numComUsados+"";
            String premio;
            //En caso de que el premio no exista el premio sera ningun premio
            if(juego.getPremio() == null){
                premio = "NINGUN \nPREMIO";
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
            Label label = new Label("Ver \nDetalle");
            label.setStyle("-fx-underline: true;");
            //Se creo la etiquieta crear detalle que cativara la funcion presentar reporte del juego cuando se presione
            label.setOnMouseClicked(event -> presentarReporteJuego(juego));
            gpPresentacion.add(new Label(fecha),0,contador);
            gpPresentacion.add(new Label(participante),1,contador);
            gpPresentacion.add(new Label(nivelMax),2,contador);
            gpPresentacion.add(new Label(sTiempo),3,contador);
            gpPresentacion.add(new Label(preguntasContestadas),4,contador);
            gpPresentacion.add(new Label(comodinesUsados),5,contador);
            gpPresentacion.add(new Label(premio),6,contador);
            gpPresentacion.add(label,7,contador);
            gpPresentacion.setAlignment(Pos.CENTER);
        }
        gpPresentacion.setVgap(20);
        gpPresentacion.setHgap(5);
        vbReportes.getChildren().setAll(gpPresentacion);
        
        
        
    }

    /**
     *
     * @param j
     */
    public void presentarReporteJuego(Juego j){
        //Se crean los contenedores y los strings correspondientes
        VBox vbDatos = new VBox();
        String fechaJuego = "Fecha: "+ j.getFechaJuego();
        String partJuego = "Participante: " + j.getParticipante();
        String companiero = "Compañero: " + j.getCompanero();
        String nivelMax = "Nivel Maximo: " + j.getNivelJugador();
        String sTiempo;
        //Se usan estructuras de control para presentar el tiempo de forma correcta
        int tiempo = j.getTiempo();
        int minutos = tiempo/60;
        int segundos = tiempo%60;
        if((minutos<10)&(segundos<10)){
            sTiempo = "Tiempo: 0"+minutos + ":0" + segundos;
        }else if(minutos<10){
            sTiempo = "Tiempo: 0"+minutos + ":" + segundos;
        }else if(segundos<10){
            sTiempo = "Tiempo: "+minutos + ":0" + segundos;
        }else{
            sTiempo = "Tiempo: "+minutos + ":" + segundos;
        }
        //Se usan estructuras de control para presentar el premio de forma correcta
        String premio;
        if(j.getPremio() == null){
            premio = "Premio : NINGUNO";
        }else{
            premio = "Premio: " + j.getPremio();
        }
        //Se crean los labels de cada uno de los datos creados en Strings
        Label detalleJuego = new Label("Detalle de juego");
        detalleJuego.setPadding(new javafx.geometry.Insets(5, 0, 0, 20));
        Label lfechaJuego = new Label(fechaJuego);
        lfechaJuego.setPadding(new javafx.geometry.Insets(5, 0, 0, 20));
        Label lpartJuego = new Label(partJuego);
        lpartJuego.setPadding(new javafx.geometry.Insets(5, 0, 0, 20));
        Label lcompaniero = new Label(companiero);
        lcompaniero.setPadding(new javafx.geometry.Insets(5, 0, 0, 20));
        Label lnivelMax = new Label(nivelMax);
        lnivelMax.setPadding(new javafx.geometry.Insets(5, 0, 0, 20));
        Label lTiempo = new Label(sTiempo);
        lTiempo.setPadding(new javafx.geometry.Insets(5, 0, 0, 20));
        Label lPremio = new Label(premio);
        lPremio.setPadding(new javafx.geometry.Insets(5, 0, 0, 20));
        Label preguntasDelJuego = new Label("Preguntas del juego:");
        preguntasDelJuego.setPadding(new javafx.geometry.Insets(10, 0, 0, 25));
        preguntasDelJuego.setStyle("-fx-font-weight: bold;");
        vbDatos.getChildren().setAll(detalleJuego,lfechaJuego,lpartJuego,lcompaniero,lnivelMax,lTiempo, lPremio, preguntasDelJuego );
        
        //Se crea el gridpane donde ira el detalle de las preguntas
        GridPane gpPreguntas = new GridPane();
        //Se crean los labels con los titulos
        Label enunciado = new Label("Enunciado");
        Label nivel = new Label("Nivel");
        Label comodin = new Label("Comodin");
        enunciado.setStyle("-fx-font-weight: bold;");
        enunciado.setPadding(new javafx.geometry.Insets(0, 5, 0, 5));
        nivel.setStyle("-fx-font-weight: bold;");
        nivel.setPadding(new javafx.geometry.Insets(0, 5, 0, 5));
        comodin.setStyle("-fx-font-weight: bold;");
        comodin.setPadding(new javafx.geometry.Insets(0, 5, 0, 5));
        //Se crea un contador para definir la fila del gridpane de preguntas
        int fila = 0;
        //Se agragan los titulos al gridPane
        gpPreguntas.add(enunciado,0,fila);
        gpPreguntas.add(nivel,1,fila);
        gpPreguntas.add(comodin,2,fila);
        //Se crea la variable de lista preguntas con las preguntas respondidas del juego, las cuales tienen los comodines usados
        ArrayList<PreguntaRespondida> listaPreguntas = j.getpRespondidas();
        //Se estructuras de control para llenar el GridPane
        for(PreguntaRespondida p:listaPreguntas){
            //Se aumenta contador 
            fila++;
            //Labels de pregunta
            Label preguntaEnunciado = new Label(p.getEnunciado());
            Label preguntaNivel = new Label(p.getNivel()+"");
            Label preguntaComodin = new Label(p.getComodinUsado()+"");
            //se da formato
            preguntaEnunciado.setPadding(new javafx.geometry.Insets(0, 5, 0, 5));
            preguntaNivel.setPadding(new javafx.geometry.Insets(0, 5, 0, 5));
            preguntaComodin.setPadding(new javafx.geometry.Insets(0, 5, 0, 5));
            preguntaNivel.setAlignment(Pos.CENTER);
            preguntaComodin.setAlignment(Pos.CENTER);
            //se añade labels
            gpPreguntas.add(preguntaEnunciado,0,fila);
            gpPreguntas.add(preguntaNivel,1,fila);
            gpPreguntas.add(preguntaComodin,2,fila);
        }
        //Se da formato al gridPane
        gpPreguntas.setAlignment(Pos.CENTER);
        
        //Se crea el boton de regreso a las preguntas llamado boton
        Button boton = new Button("Volver a preguntas");
        //Se crea un VBox donde estara el boton, para dar un espacio de las preguntas al boton
        VBox vbBoton = new VBox(boton);
        //Se establece este margen
        VBox.setMargin(boton, new javafx.geometry.Insets(10, 0, 0, 0)); // Ma
        //Se indica la accion a realizar en caso de que el boton sea presionado
        boton.setOnAction(e->{
            vbReportes.getChildren().setAll(gpPresentacion);
        });
        //se añade los elementos al VBox exteior
        vbReportes.getChildren().setAll(vbDatos,gpPreguntas, boton);
        
        //Datos para enviar mail
        String correo = fechaJuego+ "\r\n"+partJuego+"\r\n"+companiero+"\r\n"+nivelMax+"\r\n"+sTiempo+"\r\n"+premio;
        Email.sendEmail(j.getParticipante(), correo);
    }

}
