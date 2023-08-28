package vistas;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 *Despliega los elementos visuales del menú principal y los enlaza con sus respectivos métodos.
 */
public class PrimaryController {

    /**
     * Redirecciona al controlador de Configuraciones.
     * @throws IOException 
     */
    @FXML
    private void switchToConfig() throws IOException {
        App.setRoot("config");
    }
    /**
     * Redirecciona al controlador de Juego Nuevo.
     * @throws IOException 
     */
    @FXML
    private void switchToJuego() throws IOException {
        App.setRoot("juego");
    }
    /**
     * Redirecciona al controlador de Buscar Reportes.
     * @throws IOException 
     */
    @FXML
    private void switchToReporte() throws IOException {
        App.setRoot("reporte");
    }
    //jiji
}
