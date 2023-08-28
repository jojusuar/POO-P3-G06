package vistas;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *Visualiza y controla el layout de las opciones de configuración.
 */
public class ConfigController {

    @FXML
    private Button leaveConfigButton;
    private Button termButton;
    private Button materiaycursoButton;
    
    /**
     * Redirecciona al controlador primario.
     * @throws IOException 
     */
    @FXML
    private void leaveConfig() throws IOException {
        App.setRoot("primary");
    }
    /**
     * Redirecciona al controlador de Términos.
     * @throws IOException 
     */
    @FXML
    private void switchToTerm() throws IOException {
        App.setRoot("term");
    }
    /**
     * Redirecciona al controlador de Materias y Paralelos.
     * @throws IOException 
     */
    @FXML
    private void switchToMateriaycurso() throws IOException {
        App.setRoot("Materiaycurso");
    }
    /**
     * Redirecciona al controlador de Preguntas.
     * @throws IOException 
     */
    @FXML
    private void switchToQuestion() throws IOException {
        App.setRoot("question");
    }
}