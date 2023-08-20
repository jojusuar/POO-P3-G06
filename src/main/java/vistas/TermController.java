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
    private ComboBox<Termino> cbTerminos;
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
        Scene addTermScene = new Scene(fields,200,100);
        Stage addTermStage = new Stage();
        addTermStage.setScene(addTermScene);
        addTermStage.show();
        save.setOnAction(ev -> {
            UtilitariaConfig.ingresarTermino(terminos, Integer.parseInt(anio.getText()),Integer.parseInt(periodo.getText()),vbTerminos,cbTerminos);
        });
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // aqui inicializamos ese vbox vbTerminos y el combobox cbTerminos con los términos académicos disponibles
        terminos = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/memory/terminos.ser"));){
            terminos = (ArrayList<Termino>)in.readObject();
        }
        catch(IOException ex){
            System.out.println("Error al cargar los términos");
        }
        catch(ClassNotFoundException e){
            System.out.println("No se encontró la clase");
        }
        for(Termino t: terminos){
            vbTerminos.getChildren().add(new Label(t.toString()));
            cbTerminos.getItems().addAll(t);
        }
    }
    
}
