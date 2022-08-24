package com.Vysotskiy.tetris;

import com.Vysotskiy.interfaces.Viewable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {
    Viewable view = new View();
    @Override
    public void start(Stage primaryStage) {
        FlowPane root = new FlowPane();
        view.getCanvas().setFocusTraversable(true);
        root.getChildren().add(view.getCanvas());
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, view.getSIZE_WIDTH(), view.getSIZE_HIGH(), Color.SKYBLUE);
        primaryStage.setTitle("I am T_E_T_R_I_S");
        primaryStage.setScene(scene);
        primaryStage.show();
        view.eventHandler();
        view.getModel().startGame(view);
    }

    public static void main(String[] args) {
        launch();
    }

}


