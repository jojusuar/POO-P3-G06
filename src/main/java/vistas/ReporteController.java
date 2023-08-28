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
    //Se definen los elementos graficos on sus variables respectivas
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
    
    //Se definen varibales inicializadas vacias, para despues darles el valor de los textfields
    String entrada = "";
    String codigoMateria = "";
    String paralelo = "";
    //Se define la funcion generar reporte que se ejecuta cuando se presiona el boton d egenerar reporte en el menu Repoorte
    @FXML
    private void generarReporte()throws Exception{
        //Solicita la entrada de los textfields
        entrada = entradaPeriodo.getText();
        codigoMateria = entradaCodigoMateria.getText();
        paralelo = entradaParalelo.getText();
        //Se crean Arraylist vacios, en los cuales se almacenaran los listados de juegos y los listados de juegos que cumplen con los parametros
        ArrayList<Juego> listaJuegos;
        ArrayList<Juego> juegosReporte;
        //Se crea un nuevo objeto de tipo DatosReporte, que es una clase que nos ayudara a enviar la informacion de esta clase a la clase PresentacionResporteController
        DatosReporte datos = new DatosReporte();
        //Se abre el stream de datos para obtener el ArrayList de juegos
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/memory/juegoshistorial.ser"))){
            //Se usa otro try por si el objeto esta vacio
            try{
                listaJuegos = (ArrayList<Juego>)in.readObject();
                System.out.println("Juegos registrados: "+listaJuegos.size());
                //Se usa otro try por si la funcuon generarReporte presenta un error
                try{
                    System.out.println("Reportes que coinciden:");
                    //Esta funcion presenta error cuando la entrada de termino no esta separada por "-", y tiene un size diferente a 2
                    juegosReporte = UtilitariaReporte.generarReporte(entrada, codigoMateria, paralelo, listaJuegos);
                }catch(Exception e){
                    //Manejo de error de generarReporte
                    System.out.println("Datos incompletos o incorrectos");
                    juegosReporte = new ArrayList<>();
                }
                //Se asigna el valor de la lista de juegos en el objeto DatosReporte como el ArrayList de juegos coincidentes
                datos.setListaJuegos(juegosReporte);
            //Se maneja el error de datos incorrectos
            }catch(EOFException e) {
                System.out.println("no es de este tipo");
            }
        //Se maneja todos los posibles errores generados por el primer try
        }catch(FileNotFoundException ex){
            System.out.println("Archivo no encontrado");
        }catch(IOException ex){
            System.out.println("IOException");
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
            System.out.println("Clase no encontrada");
        }
        //Se crea un objeto PresentacionReporteController, y se asigna datos como su atributo de clase Datos reporte
        PresentacionReporteController presentacion = new PresentacionReporteController();
        presentacion.recibirDatos(datos);
        //Cambia de pantalla a la generada por PresentacionReporteController
        App.setRoot("PresentacionReporte");
    }
    
    
    //Metodo que permite volver en caso de presionar el boton regresar
    @FXML
    private void regresarMenuPrincipal()throws Exception{
        App.setRoot("primary");
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
}

//Definicion de la clase DatosReporte que nos ayudara a enviar los datos a la clase Presentacion Reporte Controller
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
