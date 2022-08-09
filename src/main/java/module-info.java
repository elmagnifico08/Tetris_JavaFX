module com.example.tetris_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires junit;
    requires lombok;
    requires org.junit.jupiter.api;


    opens com.Vysotskiy.tetris to javafx.fxml;
    exports com.Vysotskiy.tetris;
    exports com.Vysotskiy.figures;
    opens com.Vysotskiy.figures to javafx.fxml;
    exports com.Vysotskiy.interfaces;
    opens com.Vysotskiy.interfaces to javafx.fxml;
}