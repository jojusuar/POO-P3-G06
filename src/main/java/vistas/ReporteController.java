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
import javafx.scene.control.TextField;
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
        ArrayList<Juego> listaJuegos;
        ArrayList<Juego> juegosReporte;
        DatosReporte datos = new DatosReporte();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/memory/juegoshistorial.ser"))){
            try{
                listaJuegos = (ArrayList<Juego>)in.readObject();
                System.out.println("Juegos registrados: "+listaJuegos.size());
                try{
                    System.out.println("Reportes que coinciden:");
                    juegosReporte = UtilitariaReporte.generarReporte(entrada, codigoMateria, paralelo, listaJuegos);
                }catch(Exception e){
                    System.out.println("Datos incompletos o incorrectos");
                    juegosReporte = new ArrayList<>();
                }
                datos.setListaJuegos(juegosReporte);
                
            }catch(EOFException e) {
                System.out.println("no es de este tipo");
            }
        }catch(FileNotFoundException ex){
            System.out.println("Archivo no encontrado");
        }catch(IOException ex){
            System.out.println("IOException");
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
            System.out.println("Clase no encontrada");
        }
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
    
    public DatosReporte(){
        listaJuegos = new ArrayList<>();
    }
    //getter
    public ArrayList<Juego> getListaJuegos(){
        return listaJuegos;
    }
    //Setters
    public void setListaJuegos(ArrayList<Juego> listaJuegos){
        this.listaJuegos = listaJuegos;
    }
}
