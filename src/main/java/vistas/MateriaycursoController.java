/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MateriaycursoController implements Initializable{
    @FXML
    private VBox vbMaterias;

    @FXML
    private Button leaveMateriaycurso;

    @FXML
    private void leaveMateriaycurso() throws IOException {
        App.setRoot("config");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // aqui inicializamos ese vbox vbMaterias con las materias y paralelos
        vbMaterias.getChildren().add(new Label("probando el vbMaterias"));
    }
    
}