package com.Vysotskiy.tetris;

import com.Vysotskiy.interfaces.ConvBlockToPixel;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Application extends javafx.application.Application implements ConvBlockToPixel {
    Game game = new Game();
    GraphicsContext gc = game.getView().canvas.getGraphicsContext2D();
    @Override
    public void start(Stage primaryStage) {
        FlowPane root = new FlowPane();
        game.getView().canvas.setFocusTraversable(true);
        root.getChildren().add(game.getView().canvas);
        game.getView().canvas.setOnKeyPressed(e -> {
            KeyCode key = e.getCode();
            if (key.equals(KeyCode.UP)) game.getController().changeAction(game.getModel().getThisFigure(), game.getModel().getField());
            if (key.equals(KeyCode.DOWN)) game.getController().dropAction(game.getModel().getThisFigure(), game.getModel().getField());
            if (key.equals(KeyCode.LEFT)) game.getController().leftAction(game.getModel().getThisFigure(), game.getModel().getField());
            if (key.equals(KeyCode.RIGHT)) game.getController().rightAction(game.getModel().getThisFigure(), game.getModel().getField());
            game.getView().draw(game.getModel().getField(), game.getModel().getThisFigure(), gc, game.getModel().isLost());
        });
        primaryStage.setResizable(false);
        Scene scene = new Scene(root,game.getView().getSIZE_WIDTH(),game.getView().getSIZE_HIGH(), Color.SKYBLUE);
        primaryStage.setTitle("I am T_E_T_R_I_S");
        primaryStage.setScene(scene);
        primaryStage.show();
        game.start();
    }

    public static void main(String[] args) {
        launch();
    }




}


