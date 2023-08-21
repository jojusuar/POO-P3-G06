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
import javafx.fxml.Initializable;
import modelo.Juego;
import modelo.Termino;

/**
 * FXML Controller class
 *
 * @author Angello Andrade
 */
public class ComienzaController implements Initializable {
    Juego juego;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/memory/juego.ser"));){
            juego = (Juego)in.readObject();
        }
        catch(IOException ex){
            System.out.println("Error al cargar los datos del juego");
        }
        catch(ClassNotFoundException e){
            System.out.println("No se encontró la clase Juego");
        }
    }    
    
}
