package com.Vysotskiy.tetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application  {
    GameLogic game = new GameLogic();
    @Override
    public void start(Stage primaryStage) {
        FlowPane root = new FlowPane();
        game.view.canvas.setFocusTraversable(true);
        root.getChildren().add(game.view.canvas);
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, game.view.getSIZE_WIDTH(), game.view.getSIZE_HIGH(), Color.SKYBLUE);
        primaryStage.setTitle("I am T_E_T_R_I_S");
        primaryStage.setScene(scene);
        primaryStage.show();
        game.startGame();
    }

    public static void main(String[] args) {
        launch();
    }

}


