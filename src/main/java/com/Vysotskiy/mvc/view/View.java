package com.Vysotskiy.mvc.view;

import com.Vysotskiy.figures.Block;
import com.Vysotskiy.mvc.controller.Controller;
import com.Vysotskiy.mvc.view.interfacesView.Viewable;

import java.util.stream.IntStream;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lombok.Getter;

public class View implements Viewable {
    Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    private final int RECT_SIZE = 30;
    private final int ROW = 20;
    private final int COL = 10;
    @Getter
    private final int SIZE_HIGH = ROW * RECT_SIZE;
    @Getter
    private final int SIZE_WIDTH = COL * RECT_SIZE + RECT_SIZE * 4;
    private final int[] pixelsField = convToPixel(ROW, RECT_SIZE);

    Canvas canvas = new Canvas(SIZE_WIDTH, SIZE_HIGH);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    @Override
    public void viewStartGame() {
        eventHandler();
        Thread game = new Thread(() -> {
            drawScoreAndLevel();
            while (!controller.isGameOver()) {
                try {
                    Thread.sleep(controller.getLEVELS()[controller.getThisLevel()]);
                    if (controller.checkingCanFigureFell()) {
                        canMove();
                    } else {
                        canNotMove();
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interrupted exception" + e);
                }
            }
        });
        game.start();
    }

    @Override
    public Canvas getCanvas() {
        return canvas;
    }

    private void eventHandler() {
        canvas.setOnKeyPressed(e -> {
            KeyCode key = e.getCode();
            if (key.equals(KeyCode.UP))
                controller.changeAction();
            if (key.equals(KeyCode.DOWN))
                controller.dropAction();
            if (key.equals(KeyCode.LEFT))
                controller.leftAction();
            if (key.equals(KeyCode.RIGHT))
                controller.rightAction();
            drawField();
        });
    }

    private void canMove() {
        controller.figureCanFallDown();
        drawField();
    }

    private void canNotMove() {
        controller.figureFellDown();
        drawScoreAndLevel();
        drawField();
    }


    private void drawField() {
        clearField(controller.getField());
        if (!controller.isGameOver()) {
            gc.setFill(controller.getFigureColor());
            for (Block a : controller.getThisFigure().getBlocks()) {
                gc.fillRect(pixelsField[a.getCol()], pixelsField[a.getRow()], RECT_SIZE, RECT_SIZE);
                gc.strokeRect(pixelsField[a.getCol()], pixelsField[a.getRow()], RECT_SIZE - 2, RECT_SIZE - 2);
            }
        } else {
            gameOver();
        }
    }


    private void drawScoreAndLevel() {
        int POSITION_STROKE_POINT_WIDTH_AND_LEVEL = SIZE_WIDTH - RECT_SIZE * 4 + 5;
        int POSITION_STROKE_POINT_HIGH = RECT_SIZE * 3;
        int POSITION_STROKE_LINE = SIZE_WIDTH - RECT_SIZE * 4;
        int POSITION_SCORES = SIZE_WIDTH - RECT_SIZE * 3;
        int POSITION_STROKE_LEVEL_HIGH = RECT_SIZE * 5;
        gc.clearRect(POSITION_SCORES, 0, POSITION_SCORES, SIZE_HIGH);
        gc.setFont(new Font("Times New Roman", 17));
        gc.setStroke(Color.BLACK);
        gc.strokeLine(POSITION_STROKE_LINE, 0, POSITION_STROKE_LINE, SIZE_HIGH);
        gc.setFill(Color.BLACK);
        gc.fillText("SCORES : " + controller.getGoal(), POSITION_STROKE_POINT_WIDTH_AND_LEVEL, POSITION_STROKE_POINT_HIGH);
        gc.setFill(Color.RED);
        gc.fillText("LEVEL :  " + controller.getThisLevel(), POSITION_STROKE_POINT_WIDTH_AND_LEVEL, POSITION_STROKE_LEVEL_HIGH);
    }

    private void gameOver() {
        int POSITION_WIDTH_STROKE_GAME_OVER = SIZE_WIDTH / 2 - 50;
        int POSITION_HIGH_STROKE_GAME_OVER = SIZE_HIGH / 2;
        gc.setFont(new Font("Times New Roman", 50));
        gc.setFill(Color.BLACK);
        gc.fillText("GAME OVER", POSITION_WIDTH_STROKE_GAME_OVER, POSITION_HIGH_STROKE_GAME_OVER, 200);
    }

    private int[] convToPixel(int row, int rectSize) {
        return IntStream.range(0, row).map(e -> e * rectSize).toArray();
    }

    private void clearField(Block[][] field) {
        for (int i = 0; i < COL; i++) {
            for (int j = 0; j < ROW; j++) {
                if (field[j][i]==null) {
                    gc.clearRect(pixelsField[i], pixelsField[j], RECT_SIZE, RECT_SIZE);
                } else if (field[j][i]!=null) {
                    gc.setFill(Color.GREY);
                    gc.fillRect(pixelsField[i], pixelsField[j], RECT_SIZE, RECT_SIZE);
                    gc.strokeRect(pixelsField[i], pixelsField[j], RECT_SIZE - 2, RECT_SIZE - 2);
                }
            }
        }
    }
}