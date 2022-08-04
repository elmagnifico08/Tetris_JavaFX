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
    Controller controller = new Controller();
    @Override
    public void start(Stage primaryStage) {
        FlowPane root = new FlowPane();
        view.canvas.setFocusTraversable(true);
        root.getChildren().add(view.canvas);
        view.canvas.setOnKeyPressed(e -> {
            KeyCode key = e.getCode();
            if (key.equals(KeyCode.UP)) controller.getChangeAction(model.getThisFigure(),model.getField());
            if (key.equals(KeyCode.DOWN)) controller.getDropAction(model.getThisFigure(),model.getField());
            if (key.equals(KeyCode.LEFT)) controller.getLeftAction(model.getThisFigure(),model.getField());
            if (key.equals(KeyCode.RIGHT)) controller.getRightAction(model.getThisFigure(),model.getField());
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
        view.drawStrokeScoreLevel(model.getGoal(), model.getThisLevel(), view.gc);
        Thread game = new Thread(() -> {
            while (!view.gameOver(view.gc, model.getLost())) {
                view.convFigureToPixel(model.getThisFigure());
                try {
                    Thread.sleep(model.getLEVELS()[model.getThisLevel()]);
                    if (controller.canDrop(model.getThisFigure(),model.getField())) {
                        model.getThisFigure().moveDrop();
                        view.draw(model.getField(), model.getThisFigure(), view.gc);
                        System.out.println(model.getThisFigure().blocks[0].getRow());
                    } else {
                        model.addFigureToField();
                        view.gameOver(view.gc, model.getLost());
                        model.removeLine();
                        view.deleteLine(model.removeLine(), view.gc);
                        view.drawAddScore(model.getGoal(), model.getThisLevel(), view.gc);
                        model.setNextFigure(model.randomFigure());
                        model.setThisFigure(model.getNextFigure());
                        view.draw(model.getField(), model.getThisFigure(), view.gc);
                    }
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        });
        game.start();
    }
}


