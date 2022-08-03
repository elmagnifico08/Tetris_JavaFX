package com.example.tetris_javafx;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    Model model = new Model();
    View view = new View();
    @Override
    public void start(Stage primaryStage) {
        FlowPane root = new FlowPane();
        view.canvas.setFocusTraversable(true);
        root.getChildren().add(view.canvas);
        view.canvas.setOnKeyPressed(e -> {
            KeyCode key = e.getCode();
            if (key.equals(KeyCode.UP)) model.getChangeAction();
            if (key.equals(KeyCode.DOWN)) model.getDropAction();
            if (key.equals(KeyCode.LEFT)) model.getLeftAction();
            if (key.equals(KeyCode.RIGHT)) model.getRightAction();
            view.draw(model.getField(), model.getThisFigure(), view.gc);
        });
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, view.getSIZE_WIDTH(), view.getSIZE_HIGH(), Color.SKYBLUE);
        primaryStage.setTitle("I am T_E_T_R_I_S");
        primaryStage.setScene(scene);
        primaryStage.show();
        startOne();
    }
    public static void main(String[] args) {
        launch();
    }

    public void startOne() {
        Thread game = new Thread(() -> {
            view.drawStrokeScoreLevel(model.getGoal(), model.getThisLevel(), view.gc);
            while (!model.lost) {
                view.convFigureToPixel(model.getThisFigure());
                try {
                    Thread.sleep(model.getLEVELS()[model.getThisLevel()]);
                    if (model.canDrop()) {
                        model.getThisFigure().moveDrop();
                        view.draw(model.getField(), model.getThisFigure(), view.gc);
                        System.out.println(model.getThisFigure().blocks[0].getRow());
                    } else {
                        model.addFigureToField();
                        view.gameOver(view.gc, model.lost);
                        model.removeLine();
                        view.draw(model.getField(), model.getThisFigure(), view.gc);
                        view.deleteLine(model.removeLine(), view.gc);
                        view.drawAddScore(model.getGoal(), model.getThisLevel(), view.gc);
                        model.setNextFigure(model.randomFigure());
                        model.setThisFigure(model.getNextFigure());
                    }
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        });
        game.start();
    }
}


