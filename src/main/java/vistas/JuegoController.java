
package vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;


/**
 * FXML Controller class
 *
 * @author Angello Andrade
 */
public class JuegoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void iniciarJuego(ActionEvent event) throws IOException {
        App.setRoot("Comienza");
    }
    

}