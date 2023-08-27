package vistas;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author Euclasio
 */
public class ConfigController {

    @FXML
    private Button leaveConfigButton;
    private Button termButton;
    private Button materiaycursoButton;

    @FXML
    private void leaveConfig() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    private void switchToTerm() throws IOException {
        App.setRoot("term");
    }
    @FXML
    private void switchToMateriaycurso() throws IOException {
        App.setRoot("Materiaycurso");
    }
    @FXML
    private void switchToQuestion() throws IOException {
        App.setRoot("question");
    }
}