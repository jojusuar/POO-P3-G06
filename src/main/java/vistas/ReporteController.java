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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import modelo.*;
import java.util.ArrayList;
import java.io.*;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ReporteController implements Initializable {
    
    @FXML
    private VBox vb;
    @FXML
    private Button generarReporte;
    @FXML
    private Button regresar;
    @FXML
    private TextField entradaPeriodo;
    @FXML
    private TextField entradaCodigoMateria;
    @FXML
    private TextField entradaParalelo;
    
    
    String entrada = "";
    String codigoMateria = "";
    String paralelo = "";
    
    @FXML
    private void generarReporte()throws Exception{
        entrada = entradaPeriodo.getText();
        codigoMateria = entradaCodigoMateria.getText();
        paralelo = entradaParalelo.getText();
        ArrayList<Juego> listaJuegos = new ArrayList<>();
        ArrayList<Juego> juegosReporte;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/memory/juego.ser"))){
            Juego juego;
            while(true){
                try{
                    juego = (Juego)in.readObject();
                    System.out.println(juego+"");
                    listaJuegos.add(juego);
                }catch(EOFException e) {
                    System.out.println("ya no hay mas objetos");
                    break;
                }
            }
           
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado");
        } catch (IOException ex) {
            System.out.println("IOException");
        } catch (ClassNotFoundException ex) {
            System.out.println("Clase no encontrada");
        }
        System.out.println(listaJuegos.size());
        try{
            juegosReporte = UtilitariaReporte.generarReporte(entrada, codigoMateria, paralelo, listaJuegos);
        }catch(Exception e){
            System.out.println("Datos incompletos o incorrectos");
            juegosReporte = new ArrayList<>();
        }
        DatosReporte datos = new DatosReporte();
        datos.setListaJuegos(juegosReporte);
        PresentacionReporteController presentacion = new PresentacionReporteController();
        presentacion.recibirDatos(datos);
        App.setRoot("PresentacionReporte");
    }
    
    
    
    @FXML
    private void regresarMenuPrincipal()throws Exception{
        App.setRoot("primary");
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
}
class DatosReporte{
    private ArrayList<Juego> listaJuegos;
    //getter
    public ArrayList<Juego> getListaJuegos(){
        return listaJuegos;
    }
    //Setters
    public void setListaJuegos(ArrayList<Juego> listaJuegos){
        this.listaJuegos = listaJuegos;
    }
}
