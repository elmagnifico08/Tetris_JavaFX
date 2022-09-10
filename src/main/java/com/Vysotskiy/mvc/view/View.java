package com.Vysotskiy.mvc.view;

import com.Vysotskiy.figures.Block;
import com.Vysotskiy.mvc.controller.interfacesController.TransmittingData;
import com.Vysotskiy.mvc.model.interfacesModel.MoveGameField;
import com.Vysotskiy.mvc.controller.interfacesController.ReplaceablePositionFigure;
import com.Vysotskiy.mvc.view.interfacesView.Viewable;

import java.util.stream.IntStream;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lombok.Getter;

public class View implements Viewable {
    ReplaceablePositionFigure controllingMoveFigure;
    TransmittingData transmittingData;
    MoveGameField changeField;

    public View(ReplaceablePositionFigure controllingMoveFigure, TransmittingData transmittingData,
                MoveGameField changeField) {
        this.controllingMoveFigure = controllingMoveFigure;
        this.transmittingData = transmittingData;
        this.changeField = changeField;
    }


    private final int RECT_SIZE = 30;
    private final int ROW = 20;
    private final int COL = 10;
    @Getter
    private final int SIZE_HIGH = ROW * RECT_SIZE;
    @Getter
    private final int SIZE_WIDTH = COL * RECT_SIZE + RECT_SIZE * 4;
    private final int[] pixelsField = convToPixel(ROW, RECT_SIZE);
    private final int POSITION_STROKE_POINT_WIDTH_AND_LEVEL = SIZE_WIDTH - RECT_SIZE * 4 + 5;
    private final int POSITION_STROKE_POINT_HIGH = RECT_SIZE * 3;
    private final int POSITION_STROKE_LINE = SIZE_WIDTH - RECT_SIZE * 4;
    private final int POSITION_SCORES = SIZE_WIDTH - RECT_SIZE * 3;
    private final int POSITION_STROKE_LEVEL_HIGH = RECT_SIZE * 5;
    private final int POSITION_WIDTH_STROKE_GAME_OVER = SIZE_WIDTH / 2 - 50;
    private final int POSITION_HIGH_STROKE_GAME_OVER = SIZE_HIGH / 2;

    Canvas canvas = new Canvas(SIZE_WIDTH, SIZE_HIGH);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    @Override
    public void viewStartGame() {
        eventHandler();
        Thread game = new Thread(() -> {
            drawScoreAndLevel();
            while (!transmittingData.isGameOver()) {
                try {
                    Thread.sleep(transmittingData.getLEVELS()[transmittingData.getThisLevel()]);
                    if (changeField.checkingFigureCanDrop()) {
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
                controllingMoveFigure.changeAction();
            if (key.equals(KeyCode.DOWN))
                controllingMoveFigure.dropAction();
            if (key.equals(KeyCode.LEFT))
                controllingMoveFigure.leftAction();
            if (key.equals(KeyCode.RIGHT))
                controllingMoveFigure.rightAction();
            drawField();
        });
    }

    private void canMove() {
        changeField.figureMoveDrop();
        drawField();
    }

    private void canNotMove() {
        changeField.figureFell();
        drawScoreAndLevel();
        drawField();
    }


    private void drawField() {
        clearField(transmittingData.getField());
        if (!transmittingData.isGameOver()) {
            gc.setFill(transmittingData.getFigureColor());
            for (Block a : transmittingData.getThisFigure().getBlocks()) {
                gc.fillRect(pixelsField[a.getCol()], pixelsField[a.getRow()], RECT_SIZE, RECT_SIZE);
                gc.strokeRect(pixelsField[a.getCol()], pixelsField[a.getRow()], RECT_SIZE - 2, RECT_SIZE - 2);
            }
        } else {
            gameOver();
        }
    }


    private void drawScoreAndLevel() {
        gc.clearRect(POSITION_SCORES, 0, POSITION_SCORES, SIZE_HIGH);
        gc.setFont(new Font("Times New Roman", 17));
        gc.setStroke(Color.BLACK);
        gc.strokeLine(POSITION_STROKE_LINE, 0, POSITION_STROKE_LINE, SIZE_HIGH);
        gc.setFill(Color.BLACK);
        gc.fillText("SCORES : " + transmittingData.getGoal(), POSITION_STROKE_POINT_WIDTH_AND_LEVEL, POSITION_STROKE_POINT_HIGH);
        gc.setFill(Color.RED);
        gc.fillText("LEVEL :  " + transmittingData.getThisLevel(), POSITION_STROKE_POINT_WIDTH_AND_LEVEL, POSITION_STROKE_LEVEL_HIGH);
    }

    private void gameOver() {
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