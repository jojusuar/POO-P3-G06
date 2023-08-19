package vistas;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToConfig() throws IOException {
        App.setRoot("config");
    }
    @FXML
    private void switchToJuego() throws IOException {
        App.setRoot("juego");
    }
    @FXML
    private void switchToReporte() throws IOException {
        App.setRoot("reporte");
    }
    //jiji
}
