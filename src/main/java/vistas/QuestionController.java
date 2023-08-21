/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vistas;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Materia;
import modelo.Pregunta;
import modelo.UtilitariaConfig;


public class QuestionController implements Initializable{
    private ArrayList<Materia> materias;
    @FXML
    private Button leaveQuestionsButton;
    @FXML
    private ComboBox<Materia> cbMaterias;
    @FXML
    private void addQuestion() throws IOException {
        Materia m = cbMaterias.getValue();
        UtilitariaConfig.agregarPreguntas(materias, m);
    }
    @FXML
    private void showQuestions() throws IOException {
        VBox vbPreguntas = new VBox(10);
        Materia m = cbMaterias.getValue();
        for(Pregunta p:m.getPreguntas()){
            vbPreguntas.getChildren().add(new Label(p.toString()));
        }
        Scene layout = new Scene(vbPreguntas,200,200);
        Stage popup = new Stage();
        popup.setScene(layout);
        popup.show();
    }
    @FXML
    private void leaveQuestions() throws IOException {
        App.setRoot("config");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // aqui inicializamos ese combobox cbMaterias con las materias a las que agregarles preguntas
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/memory/materias.ser"));){
            materias = (ArrayList<Materia>)in.readObject();
        }
        catch(IOException ex){
            System.out.println("Error al cargar las materias");
            materias = new ArrayList<>();
        }
        catch(ClassNotFoundException e){
            System.out.println("No se encontró la clase");
        }
        for(Materia m: materias){
            cbMaterias.getItems().addAll(m);
        }
    }
    
}
