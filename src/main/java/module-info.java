module com.example.tetris_javafx {

    requires java.desktop;
    requires lombok;
    requires javafx.graphics;


    opens com.Vysotskiy.tetris to javafx.fxml;
    exports com.Vysotskiy.tetris;
    exports com.Vysotskiy.figures;
    opens com.Vysotskiy.figures to javafx.fxml;
    exports com.Vysotskiy.interfaces;
    opens com.Vysotskiy.interfaces to javafx.fxml;
}