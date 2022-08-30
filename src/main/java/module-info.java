module com.example.tetris_javafx {

    requires java.desktop;
    requires lombok;
    requires javafx.graphics;


    opens com.Vysotskiy.tetrisGame to javafx.fxml;
    exports com.Vysotskiy.tetrisGame;
    exports com.Vysotskiy.figures;
    exports com.Vysotskiy.mvc.model;
    exports com.Vysotskiy.mvc.model.interfacesModel;


}
