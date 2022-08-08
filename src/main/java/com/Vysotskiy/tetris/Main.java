package com.Vysotskiy.tetris;

import com.Vysotskiy.interfaces.Constant;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application implements Constant {
    Model model = new Model();
    View view = new View();
    Controller controller = new Controller();

    @Override
    public void start(Stage primaryStage) {
        FlowPane root = new FlowPane();
        view.canvas.setFocusTraversable(true);
        root.getChildren().add(view.canvas);
        view.canvas.setOnKeyPressed(e -> {
            KeyCode key = e.getCode();
            if (key.equals(KeyCode.UP)) controller.getChangeAction(model.getThisFigure(), model.getField());
            if (key.equals(KeyCode.DOWN)) controller.getDropAction(model.getThisFigure(), model.getField());
            if (key.equals(KeyCode.LEFT)) controller.getLeftAction(model.getThisFigure(), model.getField());
            if (key.equals(KeyCode.RIGHT)) controller.getRightAction(model.getThisFigure(), model.getField());
            view.draw(model.getField(), model.getThisFigure(), view.gc, model.isLost());
        });
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, SIZE_WIDTH, SIZE_HIGH, Color.SKYBLUE);
        primaryStage.setTitle("I am T_E_T_R_I_S");
        primaryStage.setScene(scene);
        primaryStage.show();
        start();
    }

    public static void main(String[] args) {
        launch();
    }

    private void start() {
        Thread game = new Thread(() -> {
            view.drawStrokeScoreLevel(model.getGoal(), model.getThisLevel(), view.gc);
            while (!model.isLost()) {
                view.convFigureToPixel(model.getThisFigure());
                try {
                    Thread.sleep(model.getLEVELS()[model.getThisLevel()]);
                    if (controller.canDrop(model.getThisFigure(), model.getField())) {
                        model.getThisFigure().moveDrop();
                        view.draw(model.getField(), model.getThisFigure(), view.gc, model.isLost());
                    } else {
                        model.addFigureToField();
                        model.removeLine();
                        view.drawStrokeScoreLevel(model.getGoal(), model.getThisLevel(), view.gc);
                        model.setNextFigure(model.randomFigure());
                        model.setThisFigure(model.getNextFigure());
                        view.draw(model.getField(), model.getThisFigure(), view.gc, model.isLost());
                    }
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        });
        game.start();
    }
}


