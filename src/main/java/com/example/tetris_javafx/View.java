package com.example.tetris_javafx;

import com.example.figures_javafx.Block;
import com.example.figures_javafx.Figure;
import com.example.interface_javafx.ConvBlockToPixel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class View extends Application {
    Model model = new Model();
    View2 view2 = new View2();
//    private final int rectSize = 25;
//    private final int SIZE_HIGH = model.getROW() * rectSize;
//    private final int SIZE_WIDTH = model.getCOL() * rectSize + 100;
//    private int[] xFigure = new int[4];
//    private int[] yFigure = new int[4];
//    private int[] pixelsRowLine = new int[model.getROW()];
//    private int[] pixelsColLine = new int[model.getCOL()];
//    private int[] convPixel = convToPixel(model.getROW(), rectSize);
//    Canvas canvas = new Canvas(SIZE_WIDTH, SIZE_HIGH);
//    GraphicsContext gc = canvas.getGraphicsContext2D();
    @Override
    public void start(Stage primaryStage) {

        FlowPane root = new FlowPane();
        view2.canvas.setFocusTraversable(true);
        root.getChildren().add(view2.canvas);
        view2.canvas.setOnKeyPressed(e -> {
            KeyCode key = e.getCode();
            if (key.equals(KeyCode.UP)) model.getChangeAction();
            if (key.equals(KeyCode.DOWN)) model.getDropAction();
            if (key.equals(KeyCode.LEFT)) model.getLeftAction();
            if (key.equals(KeyCode.RIGHT)) model.getRightAction();
            view2.draw(model.getField(), model.getThisFigure(), view2.gc);
        });
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, view2.getSIZE_WIDTH(), view2.getSIZE_HIGH(), Color.SKYBLUE);
        primaryStage.setTitle("I am T_E_T_R_I_S");
        primaryStage.setScene(scene);
        primaryStage.show();
        view2.startOne();
    }


    public static void main(String[] args) {
        launch();
    }
//    public void startOne(Model model) {
//        Thread game = new Thread(() -> {
//            drawStrokeScoreLevel(model.getGoal(), model.getThisLevel(), gc);
//            while (!model.lost) {
//                convFigureToPixel(model.getThisFigure());
//                try {
//                    Thread.sleep(model.getLEVELS()[model.getThisLevel()]);
//                    if (model.canDrop()) {
//                        model.getThisFigure().moveDrop();
//                        draw(model.getField(), model.getThisFigure(), gc);
//                        System.out.println(model.getThisFigure().blocks[0].getRow());
//                    } else {
//                        model.addFigureToField();
//                        gameOver(gc,model.lost);
//                        model.removeLine();
//                        draw(model.getField(), model.getThisFigure(), gc);
//                        deleteLine(model.removeLine(), gc);
//                        drawAddScore(model.getGoal(), model.getThisLevel(), gc);
//                        model.setNextFigure(model.randomFigure());
//                        model.setThisFigure(model.getNextFigure());
//                    }
//                } catch (InterruptedException e) {
//                    System.out.println(e);
//                }
//            }
//        });
//        game.start();
//    }
//
//    public void drawStrokeScoreLevel(int goal, int thisLevel, GraphicsContext gc) {
//        gc.setStroke(Paint.valueOf("black"));
//        gc.strokeLine(SIZE_WIDTH - 100, 0, SIZE_WIDTH - 100, SIZE_HIGH);
//        gc.setFill(Paint.valueOf("black"));
//        gc.fillText("SCORES :  " + goal, SIZE_WIDTH - 95, 100);
//        gc.setFill(Paint.valueOf("red"));
//        gc.fillText("LEVEL :  " + thisLevel, SIZE_WIDTH - 95, 150);
//    }
//
//    public void convFigureToPixel(Figure thisFigure) {
//        Block[] blocks = thisFigure.blocks;
//        int i = 0;
//        for (Block a : blocks) {
//            xFigure[i] = convPixel[a.getCol()];
//            yFigure[i] = convPixel[a.getRow()];
//            i++;
//        }
//    }
//
//    private void convFieldToPixel() {
//        for (int i = 0; i < model.getCOL(); i++) {
//            for (int j = 0; j < model.getROW(); j++) {
//                pixelsColLine[i] = convPixel[i];
//                pixelsRowLine[j] = convPixel[j];
//            }
//        }
//    }
//
//    public void draw(Block[][] field, Figure thisFigure, GraphicsContext gc) {
//        clearField(field, gc);
//        if (!model.lost) {
//            convFigureToPixel(thisFigure);
//            gc.setFill(thisFigure.getColor());
//            for (int i = 0; i < 4; i++) {
//                gc.fillRect(xFigure[i], yFigure[i], rectSize, rectSize);
//                gc.strokeRect(xFigure[i], yFigure[i], rectSize - 2, rectSize - 2);
//
//            }
//
//        }
//    }
//
//    public void deleteLine(Block [] field, GraphicsContext gc) {
//        Block[] line = field;
//        for (Block a : line)
//        if (a == null){
//            break;
//        } else{
//            for (Block b : line) {
//                int rowL = b.getRow();
//                int colL = b.getCol();
//                convFieldToPixel();
//                gc.clearRect(pixelsColLine[colL], pixelsRowLine[rowL], rectSize, rectSize);
//            }
//        }
//    }
//
//    public void gameOver(GraphicsContext gc, boolean lost) {
//        if(lost) {
//            gc.setFill(Paint.valueOf("black"));
//            gc.setFont(new Font("Times New Roman", 20));
//            gc.fillText("GAME OVER", SIZE_WIDTH / 2 - 50, SIZE_HIGH / 2, 150);
//        }
//        }
//
//
//    private void clearField(Block[][] field, GraphicsContext gc) {
//        for (int i = 0; i < model.getCOL(); i++) {
//            convFieldToPixel();
//            for (int j = 0; j < model.getROW(); j++) {
//                if (field[j][i] == null) {
//                    gc.clearRect(pixelsColLine[i], pixelsRowLine[j], rectSize, rectSize);
//                } else if (field[j][i] != null) {
//                    gc.setFill(Paint.valueOf("grey"));
//                    gc.fillRect(pixelsColLine[i], pixelsRowLine[j], rectSize, rectSize);
//                    gc.strokeRect(pixelsColLine[i], pixelsRowLine[j], rectSize - 2, rectSize - 2);
//                }
//            }
//        }
//    }
//
//    public void drawAddScore(int goal, int thisLevel, GraphicsContext gc) {
//        gc.clearRect(SIZE_WIDTH - 95, 0, SIZE_WIDTH - 95, 500);
//        gc.setFill(Paint.valueOf("black"));
//        gc.fillText("SCORES :  " + goal, SIZE_WIDTH - 95, 100);
//        gc.setFill(Paint.valueOf("red"));
//        gc.fillText("LEVEL :  " + thisLevel, SIZE_WIDTH - 95, 150);
//
//    }
}


