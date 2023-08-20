/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vistas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class JuegoController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VBox fields = new VBox(10);
        TextField anio = new TextField("Ingrese el año");
        TextField periodo = new TextField("Ingrese el período académico");
        Button save = new Button("Ingresar");
        fields.getChildren().addAll(anio, periodo, save);
        Scene addTermScene = new Scene(fields,200,200);
        Stage newGameData = new Stage();
        newGameData.setScene(addTermScene);
        newGameData.show();
    }    
    
}
