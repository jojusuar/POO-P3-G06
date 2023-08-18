module vistas {
    requires javafx.controls;
    requires javafx.fxml;

    opens vistas to javafx.fxml;
    exports vistas;
}
