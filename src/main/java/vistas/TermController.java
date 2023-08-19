/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TermController implements Initializable{
    @FXML
    private Button leaveTerm;
    @FXML 
    private VBox vbTerminos;
    @FXML
    private ComboBox<String> cbTerminos;

    @FXML
    private void leaveTerm() throws IOException {
        App.setRoot("config");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // aqui inicializamos ese vbox vbTerminos y el combobox cbTerminos con los términos académicos disponibles
        vbTerminos.getChildren().add(new Label("probando el vbTerminos"));
        cbTerminos.getItems().addAll("probando el cbTerminos");
    }
}
