package com.Vysotskiy.tetris;

import com.Vysotskiy.figures.Block;
import com.Vysotskiy.figures.Figure;
import com.Vysotskiy.interfaces.ConvBlockToPixel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lombok.Getter;

public class View implements ConvBlockToPixel {
    @Getter
    Controller controller = new Controller();
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

    public void eventHandler() {
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
            draw(controller.getModel().getField(), controller.getModel().getThisFigure(), controller.getModel().isLost());
        });
    }


    public void drawStrokeScoreLevel(int goal, int thisLevel) {
        gc.setFont(new Font("Times New Roman", 17));
        gc.clearRect(SIZE_WIDTH - RECT_SIZE * 3, 0, SIZE_WIDTH - RECT_SIZE * 3, SIZE_HIGH);
        gc.setStroke(Color.BLACK);
        gc.strokeLine(SIZE_WIDTH - RECT_SIZE * 4, 0, SIZE_WIDTH - RECT_SIZE * 4, SIZE_HIGH);
        gc.setFill(Color.BLACK);
        gc.fillText("SCORES : " + goal, SIZE_WIDTH - RECT_SIZE * 4 + 5, RECT_SIZE * 3);
        gc.setFill(Color.RED);
        gc.fillText("LEVEL :  " + thisLevel, SIZE_WIDTH - RECT_SIZE * 4 + 5, RECT_SIZE * 5);
    }

    public void draw(Block[][] field, Figure thisFigure, boolean lost) {
        clearField(field);
        if (!lost) {
            gc.setFill(thisFigure.getColor());
            for (Block a : thisFigure.getBlocks()) {
                gc.fillRect(pixelsField[a.getCol()], pixelsField[a.getRow()], RECT_SIZE, RECT_SIZE);
                gc.strokeRect(pixelsField[a.getCol()], pixelsField[a.getRow()], RECT_SIZE - 2, RECT_SIZE - 2);
            }
        } else {
            gameOver();
        }
    }

    private void gameOver() {
        gc.setFont(new Font("Times New Roman", 50));
        gc.setFill(Color.BLACK);
        gc.fillText("GAME OVER", SIZE_WIDTH / 2 - 50, SIZE_HIGH / 2, 200);
    }

    private void clearField(Block[][] field) {
        for (int i = 0; i < COL; i++) {
            for (int j = 0; j < ROW; j++) {
                if (field[j][i] == null) {
                    gc.clearRect(pixelsField[i], pixelsField[j], RECT_SIZE, RECT_SIZE);
                } else if (field[j][i] != null) {
                    gc.setFill(Color.GREY);
                    gc.fillRect(pixelsField[i], pixelsField[j], RECT_SIZE, RECT_SIZE);
                    gc.strokeRect(pixelsField[i], pixelsField[j], RECT_SIZE - 2, RECT_SIZE - 2);
                }
            }
        }
    }
}