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

/**
 * Despliega los elementos visuales y controla el layout del menú de Preguntas.
 */
public class QuestionController implements Initializable {

    private ArrayList<Materia> materias;
    @FXML
    private Button leaveQuestionsButton;
    @FXML
    private ComboBox<Materia> cbMaterias;

    /**
     * Redirecciona al método utilitario agregarPreguntas().
     */
    @FXML
    private void addQuestion() throws IOException {
        Materia m = cbMaterias.getValue();
        UtilitariaConfig.agregarPreguntas(materias, m);
    }

    /**
     * Despliega una ventana con un VBox que contiene a las preguntas de la
     * materia seleccionada.
     */
    @FXML
    private void showQuestions() throws IOException {
        VBox vbPreguntas = new VBox(10);
        Materia m = cbMaterias.getValue();
        for (Pregunta p : m.getPreguntas()) {
            vbPreguntas.getChildren().add(new Label(p.toString()));
        }
        Scene layout = new Scene(vbPreguntas, 200, 200);
        Stage popup = new Stage();
        popup.setScene(layout);
        popup.show();
    }

    /**
     * Despliega las preguntas pertenecientes a la materia seleccionada para seleccionar cuál será eliminada, acutalizando la materia asociada y los paralelos que la contengan.
     * @throws IOException 
     */
    @FXML
    private void deleteQuestion() throws IOException {
        VBox fields = new VBox(10);
        ComboBox<Pregunta> cb = new ComboBox<>();
        Materia course = cbMaterias.getValue();
        for (Pregunta p : course.getPreguntas()) {
            cb.getItems().addAll(p);
        }
        cb.setPromptText("[seleccione la pregunta a eliminar]");
        Button save = new Button("Eliminar");
        fields.getChildren().addAll(cb, save);
        Scene editTermScene = new Scene(fields, 550, 75);
        Stage editTermStage = new Stage();
        editTermStage.setScene(editTermScene);
        editTermStage.show();
        save.setOnAction(ev -> {
            course.getPreguntas().remove(cb.getValue());
            UtilitariaConfig.editarMateria(materias, course, course.getCodigo());
            editTermStage.close();
        });
    }

    /**
     * Redirecciona al controlador de Configuraciones.
     * @throws IOException 
     */
    @FXML
    private void leaveQuestions() throws IOException {
        App.setRoot("config");
    }

    /**
     *Inicializa los elementos visuales del menú de preguntas.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // aqui inicializamos ese combobox cbMaterias con las materias a las que agregarles preguntas
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/memory/materias.ser"));) {
            materias = (ArrayList<Materia>) in.readObject();
        } catch (IOException ex) {
            System.out.println("Error al cargar las materias");
            materias = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró la clase");
        }
        for (Materia m : materias) {
            cbMaterias.getItems().addAll(m);
        }
    }

}
