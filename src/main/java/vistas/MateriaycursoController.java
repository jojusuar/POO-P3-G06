/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.Estudiante;
import modelo.Materia;
import modelo.Paralelo;
import modelo.Termino;
import modelo.UtilitariaConfig;

/**
 *
 * @author Euclasio
 */
public class MateriaycursoController implements Initializable {

    private ArrayList<Materia> materias;
    private ArrayList<Paralelo> paralelos;
    private ArrayList<Termino> terminos;
    private ArrayList<Estudiante> estudiantes;
    @FXML
    private VBox vbParalelos;

    @FXML
    private Button leaveMateriaycurso;

    @FXML
    private void leaveMateriaycurso() throws IOException {
        App.setRoot("config");
    }

    @FXML
    private void addMateria() throws IOException {
        VBox fields = new VBox(10);
        TextField name = new TextField("Ingrese el nombre de la materia");
        TextField code = new TextField("Ingrese el código de la materia");
        TextField lvl = new TextField("Ingrese el nivel máximo de la materia");
        Button save = new Button("Ingresar");
        fields.getChildren().addAll(name, code, lvl, save);
        Scene addMateriaScene = new Scene(fields, 230, 130);
        Stage addMateriaStage = new Stage();
        addMateriaStage.setScene(addMateriaScene);
        addMateriaStage.show();
        save.setOnAction(ev -> {
            UtilitariaConfig.ingresarMateria(materias, name.getText(), code.getText(), Integer.parseInt(lvl.getText()));
            addMateriaStage.close();
        });
    }

    @FXML
    private void editMateria() throws IOException {
        VBox fields = new VBox(10);
        ComboBox<Materia> cb = new ComboBox<>();
        for (Materia t : materias) {
            cb.getItems().add(t);
        }
        cb.setPromptText("[seleccione la materia a editar]");
        Button save = new Button("Editar");
        fields.getChildren().addAll(cb, save);
        Scene editMateriaScene = new Scene(fields, 230, 75);
        Stage editMateriaStage = new Stage();
        editMateriaStage.setScene(editMateriaScene);
        editMateriaStage.show();
        save.setOnAction(ev -> {
            editMateriaStage.close();
            Materia selected = cb.getValue();
            Materia sel = materias.get(materias.indexOf(selected));
            String previous = sel.getCodigo();
            VBox fields2 = new VBox(10);
            TextField name = new TextField(sel.getNombre());
            TextField code = new TextField(sel.getCodigo());
            TextField lvl = new TextField(sel.getNiveles() + "");
            Button save2 = new Button("Ingresar");
            fields2.getChildren().addAll(name, code, lvl, save2);
            Scene addMateriaScene = new Scene(fields2, 230, 130);
            Stage addMateriaStage = new Stage();
            addMateriaStage.setScene(addMateriaScene);
            addMateriaStage.show();
            save2.setOnAction(ev2 -> {
                sel.setNombre(name.getText());
                sel.setCodigo(code.getText());
                sel.setNiveles(Integer.parseInt(lvl.getText()));
                UtilitariaConfig.editarMateria(materias, sel, previous);
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/memory/paralelos.ser"));) {
                    paralelos = (ArrayList<Paralelo>) in.readObject();
                } catch (IOException ex) {
                    System.out.println("Error al cargar los paralelos, creando nuevo arreglo");
                    paralelos = new ArrayList<>();
                } catch (ClassNotFoundException e) {
                    System.out.println("No se encontró la clase");
                }
                for (Paralelo p : paralelos) {
                    vbParalelos.getChildren().add(new Label(p.toString()));
                }
                vbParalelos.getChildren().clear();
                for (Paralelo p : paralelos) {
                    vbParalelos.getChildren().add(new Label(p.toString()));
                }
                addMateriaStage.close();
            });
        });
    }

    @FXML
    private void addParalelo() throws IOException {
        VBox fields = new VBox(10);
        ComboBox<Materia> materiasdisp = new ComboBox<>();
        materiasdisp.setPromptText("[seleccione una materia]");
        if (!materias.isEmpty()) {
            for (Materia m : materias) {
                materiasdisp.getItems().addAll(m);
            }
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/memory/terminos.ser"));) {
            terminos = (ArrayList<Termino>) in.readObject();
        } catch (IOException ex) {
            System.out.println("Error al cargar los términos");
            terminos = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró la clase");
        }
        ComboBox<Termino> termdisp = new ComboBox<>();
        termdisp.setPromptText("[seleccione un término académico]");
        if (!terminos.isEmpty()) {
            for (Termino t : terminos) {
                termdisp.getItems().addAll(t);
            }
        }
        estudiantes = new ArrayList<>();
        Button load = new Button("Cargar archivo de estudiantes");
        load.setOnAction(ev -> {
            cargarEstudiantes();
        });
        TextField number = new TextField("Ingrese el número del paralelo");
        Button save = new Button("Agregar el paralelo");
        fields.getChildren().addAll(materiasdisp, termdisp, load, number, save);
        Scene query = new Scene(fields, 300, 180);
        Stage popup = new Stage();
        popup.setScene(query);
        popup.show();
        save.setOnAction(ev -> {
            UtilitariaConfig.agregarParalelo(vbParalelos, materiasdisp.getValue(), termdisp.getValue(), paralelos, estudiantes, Integer.parseInt(number.getText()));
            popup.close();
        });
    }

    @FXML
    private void deleteParalelo() {
        VBox fields = new VBox(10);
        ComboBox<Paralelo> cb = new ComboBox<>();
        for (Paralelo p : paralelos) {
            cb.getItems().addAll(p);
        }
        cb.setPromptText("[seleccione el paralelo a eliminar]");
        Button save = new Button("Eliminar");
        fields.getChildren().addAll(cb, save);
        Scene editCourseScene = new Scene(fields, 550, 75);
        Stage editCourseStage = new Stage();
        editCourseStage.setScene(editCourseScene);
        editCourseStage.show();
        save.setOnAction(ev -> {
            paralelos.remove(cb.getValue());
            UtilitariaConfig.eliminarParalelo(paralelos);
            vbParalelos.getChildren().clear();
            for (Paralelo p : paralelos) {
                vbParalelos.getChildren().add(new Label(p.toString()));
            }
            editCourseStage.close();
        });

    }

    private void cargarEstudiantes() {
        ArrayList<Estudiante> e = new ArrayList<>();
        FileChooser examinar = new FileChooser();
        examinar.setTitle("Cargar archivo .csv");
        Stage filebrowser = new Stage();
        File file = examinar.showOpenDialog(filebrowser);
        if (file != null) {
            try (BufferedReader read = new BufferedReader(new FileReader(file))) {
                String line = read.readLine();
                while ((line = read.readLine()) != null) {
                    String[] datos = line.split(";");
                    e.add(new Estudiante(datos[1], datos[2], datos[0]));
                }
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
        estudiantes = e;
    }

    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // aqui inicializamos ese vbox vbMaterias con las materias y paralelos
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/memory/materias.ser"));) {
            materias = (ArrayList<Materia>) in.readObject();
        } catch (IOException ex) {
            System.out.println("Error al cargar las materias");
            materias = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró la clase");
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/memory/paralelos.ser"));) {
            paralelos = (ArrayList<Paralelo>) in.readObject();
        } catch (IOException ex) {
            System.out.println("Error al cargar los paralelos, creando nuevo arreglo");
            paralelos = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró la clase");
        }
        for (Paralelo p : paralelos) {
            vbParalelos.getChildren().add(new Label(p.toString()));
        }
    }
}
