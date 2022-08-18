package com.Vysotskiy.tetris;

import com.Vysotskiy.figures.Block;
import com.Vysotskiy.figures.Figure;
import com.Vysotskiy.interfaces.ConvBlockToPixel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lombok.Getter;

public class View implements ConvBlockToPixel {

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

    public void drawStrokeScoreLevel(int point, int thisLevel) {
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
        gc.fillText("SCORES : " + point, POSITION_STROKE_POINT_WIDTH_AND_LEVEL, POSITION_STROKE_POINT_HIGH);
        gc.setFill(Color.RED);
        gc.fillText("LEVEL :  " + thisLevel, POSITION_STROKE_POINT_WIDTH_AND_LEVEL, POSITION_STROKE_LEVEL_HIGH);
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
        int POSITION_WIDTH_STROKE_GAME_OVER = SIZE_WIDTH / 2 - 50;
        int POSITION_HIGH_STROKE_GAME_OVER = SIZE_HIGH / 2;
        gc.setFont(new Font("Times New Roman", 50));
        gc.setFill(Color.BLACK);
        gc.fillText("GAME OVER", POSITION_WIDTH_STROKE_GAME_OVER, POSITION_HIGH_STROKE_GAME_OVER, 200);
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