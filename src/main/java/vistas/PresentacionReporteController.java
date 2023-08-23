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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modelo.*;
import java.util.ArrayList;


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
        System.out.println(listaJuegos);
    }    
    
}
