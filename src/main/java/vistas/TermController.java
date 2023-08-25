
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Termino;
import modelo.UtilitariaConfig;

public class TermController implements Initializable {

    private ArrayList<Termino> terminos;
    @FXML
    private Button leaveTerm;
    @FXML
    private VBox vbTerminos;

    @FXML
    private Button ingresarTermino;

    @FXML
    private void leaveTerm() throws IOException {
        App.setRoot("config");
    }

    @FXML
    private void addTerm() throws IOException {
        VBox fields = new VBox(10);
        TextField anio = new TextField("Ingrese el año");
        TextField periodo = new TextField("Ingrese el período académico");
        Button save = new Button("Ingresar");
        fields.getChildren().addAll(anio, periodo, save);
        Scene addTermScene = new Scene(fields, 200, 100);
        Stage addTermStage = new Stage();
        addTermStage.setScene(addTermScene);
        addTermStage.show();
        save.setOnAction(ev -> {
            UtilitariaConfig.ingresarTermino(terminos, Integer.parseInt(anio.getText()), Integer.parseInt(periodo.getText()), vbTerminos);
            addTermStage.close();
        });

    }

    @FXML
    private void editTerm() throws IOException {
        VBox fields = new VBox(10);
        ComboBox<Termino> cb = new ComboBox<>();
        for (Termino t : terminos) {
            cb.getItems().addAll(t);
        }
        cb.setPromptText("[seleccione el término a editar]");
        Button save = new Button("Editar");
        fields.getChildren().addAll(cb, save);
        Scene editTermScene = new Scene(fields, 230, 75);
        Stage editTermStage = new Stage();
        editTermStage.setScene(editTermScene);
        editTermStage.show();
        save.setOnAction(ev -> {
            editTermStage.close();
            VBox fields2 = new VBox(10);
            Termino selected = cb.getValue();
            Termino sel = terminos.get(terminos.indexOf(selected));
            String previous = sel.toString();
            TextField anio = new TextField(selected.getAnio() + "");
            TextField periodo = new TextField(selected.getNumTermino() + "");
            Button save2 = new Button("Ingresar");
            fields2.getChildren().addAll(anio, periodo, save2);
            Scene addTermScene = new Scene(fields2, 200, 100);
            Stage addTermStage = new Stage();
            addTermStage.setScene(addTermScene);
            addTermStage.show();
            save2.setOnAction(ev2 -> {
                sel.setAnio(Integer.parseInt(anio.getText()));
                sel.setNumTermino(Integer.parseInt(periodo.getText()));
                vbTerminos.getChildren().clear();
                for (Termino t : terminos) {
                    vbTerminos.getChildren().add(new Label(t.toString()));
                }
                UtilitariaConfig.editarTermino(terminos, sel, previous);
                addTermStage.close();
            });
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // aqui inicializamos ese vbox vbTerminos y el combobox cbTerminos con los términos académicos disponibles
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/memory/terminos.ser"));) {
            terminos = (ArrayList<Termino>) in.readObject();
        } catch (IOException ex) {
            System.out.println("Error al cargar los términos");
            terminos = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró la clase");
        }

        for (Termino t : terminos) {
            vbTerminos.getChildren().add(new Label(t.toString()));
        }
    }

}
