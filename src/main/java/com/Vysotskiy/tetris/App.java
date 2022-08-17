package com.Vysotskiy.tetris;

import com.Vysotskiy.interfaces.ConvBlockToPixel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application implements ConvBlockToPixel {
    Game game = new Game();
    @Override
    public void start(Stage primaryStage) {
        FlowPane root = new FlowPane();
        game.getView().canvas.setFocusTraversable(true);
        root.getChildren().add(game.getView().canvas);
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, game.getView().getSIZE_WIDTH(), game.getView().getSIZE_HIGH(), Color.SKYBLUE);
        primaryStage.setTitle("I am T_E_T_R_I_S");
        primaryStage.setScene(scene);
        primaryStage.show();
        game.start();
    }

    public static void main(String[] args) {
        launch();
    }

}


