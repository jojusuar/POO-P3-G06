/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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


public class QuestionController implements Initializable{
    @FXML
    private Button leaveQuestionsButton;
    @FXML
    private ComboBox<String> cbMaterias;
    
    @FXML
    private void leaveQuestions() throws IOException {
        App.setRoot("config");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // aqui inicializamos ese combobox cbMaterias con las materias a las que agregarles preguntas
        cbMaterias.getItems().addAll("probando el cbMaterias");
    }
    
}
