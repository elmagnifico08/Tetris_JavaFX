module com.example.tetris_javafx {

    requires java.desktop;
    requires lombok;
    requires javafx.graphics;


    opens com.Vysotskiy.tetris to javafx.fxml;
    exports com.Vysotskiy.tetris;
    exports com.Vysotskiy.figures;
    exports com.Vysotskiy.mvc.model;


}
