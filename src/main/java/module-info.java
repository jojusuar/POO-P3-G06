module vistas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.base;
    requires javafx.graphics;

    opens vistas to javafx.fxml;
    exports vistas;
}
