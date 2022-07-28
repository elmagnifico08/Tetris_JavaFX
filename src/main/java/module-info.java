module com.example.tetris_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;

    opens com.example.tetris_javafx to javafx.fxml;
    exports com.example.tetris_javafx;
    exports com.example.figures_javafx;
    opens com.example.figures_javafx to javafx.fxml;
    exports com.example.interface_javafx;
    opens com.example.interface_javafx to javafx.fxml;
}