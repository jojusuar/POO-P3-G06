
package vistas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Estudiante;
import modelo.Juego;
import modelo.Materia;
import modelo.Paralelo;
import modelo.Pregunta;
import modelo.Termino;


/**
 * FXML Controller class
 *
 * @author Angello Andrade
 */
public class JuegoController implements Initializable {
    ArrayList<Termino> terminos;
    ArrayList<Materia> materias;
    ArrayList<Paralelo> paralelos;
    Paralelo selected;
    
    @FXML
    ComboBox<Termino> cbTerminos;
    @FXML
    ComboBox<Materia> cbMaterias;
    @FXML
    ComboBox<Paralelo> cbParalelos;
    @FXML
    TextField tfNivel;
    @FXML
    ComboBox<Estudiante> cbParticipante;
    @FXML
    ComboBox<Estudiante> cbApoyo;
    @FXML
    Button partRandom;
    @FXML
    Button apoyoRandom;
    @FXML
    Button buscarParalelos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/memory/terminos.ser"));){
            terminos = (ArrayList<Termino>)in.readObject();
        }
        catch(IOException ex){
            System.out.println("Error al cargar los términos");
            terminos = new ArrayList<>();
        }
        catch(ClassNotFoundException e){
            System.out.println("No se encontró la clase");
        }
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
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/memory/paralelos.ser"));){
            paralelos = (ArrayList<Paralelo>)in.readObject();
        }
        catch(IOException ex){
            System.out.println("Error al cargar los paralelos, creando nuevo arreglo");
            paralelos = new ArrayList<>();
        }
        catch(ClassNotFoundException e){
            System.out.println("No se encontró la clase");
        }
        if(!terminos.isEmpty()){
            for(Termino t: terminos){
            cbTerminos.getItems().addAll(t);
            }
        }
        else{
            cbTerminos.setPromptText("NO EXISTEN TÉRMINOS");
        }
        if(!materias.isEmpty()){
            for(Materia m: materias){
            cbMaterias.getItems().addAll(m);
            }       
        }
        else{
            cbMaterias.setPromptText("NO EXISTEN MATERIAS");
        }
        buscarParalelos.setOnAction(ev -> {
            Button save = new Button("Seleccionar");
            cbParalelos = new ComboBox<>();
            cbParalelos.setPromptText("[paralelos encontrados]");
            VBox fields = new VBox(5);
            Materia m = cbMaterias.getValue();
            Termino t = cbTerminos.getValue();
            for(Paralelo p: paralelos){
                if((p.getMateria().getCodigo().equals(m.getCodigo())) && (p.getTermino().toString().equals(t.toString()))){
                    cbParalelos.getItems().addAll(p);
                }
            }
            fields.getChildren().addAll(cbParalelos,save);
            Scene layout = new Scene(fields,200,100);
            Stage popup  = new Stage();
            popup.setScene(layout);
            popup.show();
            save.setOnAction(ev2 -> {
                System.out.println("se añadio paralelo");
                selected = cbParalelos.getValue();
                System.out.println(selected);
                for(Estudiante e: selected.getEstudiantes()){
                    System.out.println(e);
                    cbParticipante.getItems().addAll(e);
                    cbApoyo.getItems().addAll(e);
                }
                popup.close();
            });
        });
        partRandom.setOnAction(ev -> {
            ArrayList<Estudiante> selection = selected.getEstudiantes();
            int index = (int)(Math.random()*selection.size());
            cbParticipante.setValue(selection.get(index));
        });
        apoyoRandom.setOnAction(ev -> {
            Estudiante anyone;
            do{
                ArrayList<Estudiante> selection = selected.getEstudiantes();
                int index = (int)(Math.random()*selection.size());
                anyone = selection.get(index);
            }
            while(anyone.equals(cbParticipante.getValue()));
            cbApoyo.setValue(anyone);
        });
    }    
    
    @FXML
    private void iniciarJuego(ActionEvent event) throws IOException {
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Juego game = new Juego(selected, cbParticipante.getValue(),cbApoyo.getValue(),0,0,null,null, formatter.format(date));
        ArrayList<Pregunta> everyQuestion = game.getParalelo().getMateria().getPreguntas();
        ArrayList<Pregunta> preguntas = new ArrayList<>();
        Collections.shuffle(everyQuestion);
        for(Pregunta p: everyQuestion){
            System.out.println("pregunta hallada: "+p);
            int match = 0;
            for(Pregunta q: preguntas){
                if(p.getNivel()==q.getNivel()){
                    match++;
                }
            }
            if(match<Integer.parseInt(tfNivel.getText())){
                System.out.println("pregunta que cumple: "+p);
                preguntas.add(p);
            }
        }
        if(preguntas.size()!=Integer.parseInt(tfNivel.getText())*game.getParalelo().getMateria().getNiveles()){
           Alert a  = new Alert(AlertType.WARNING);
           a.setContentText("NO HAY PREGUNTAS SUFICIENTES POR NIVEL");
           a.show();
        }
        else{
            game.setPreguntasDelJuego(preguntas);
            try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/resources/memory/juego.ser"));){
            out.writeObject(game);
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        App.setRoot("Comienza");
        }
        
    }
    

}