package com.Vysotskiy.tetris;

import com.Vysotskiy.figures.Block;
import com.Vysotskiy.figures.Figure;
import com.Vysotskiy.interfaces.Constant;
import com.Vysotskiy.interfaces.ConvBlockToPixel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class View implements ConvBlockToPixel, Constant {
    private final int[] xFigure = new int[4];
    private final int[] yFigure = new int[4];
    private final int[] convPixel = convToPixel(ROW, RECT_SIZE);
    private final int[] pixelsColLine = convToPixel(COL, RECT_SIZE);
    Canvas canvas = new Canvas(SIZE_WIDTH, SIZE_HIGH);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    public void drawStrokeScoreLevel(int goal, int thisLevel, GraphicsContext gc) {
        gc.setFont(new Font("Times New Roman", 16));
        gc.clearRect(SIZE_WIDTH - 95, 0, SIZE_WIDTH - 95, SIZE_HIGH);
        gc.setStroke(Paint.valueOf("black"));
        gc.strokeLine(SIZE_WIDTH - 100, 0, SIZE_WIDTH - 100, SIZE_HIGH);
        gc.setFill(Paint.valueOf("black"));
        gc.fillText("SCORES : " + goal, SIZE_WIDTH - 95, 100);
        gc.setFill(Paint.valueOf("red"));
        gc.fillText("LEVEL :  " + thisLevel, SIZE_WIDTH - 95, 150);
    }

    public void draw(Block[][] field, Figure thisFigure, GraphicsContext gc, boolean lost) {
        clearField(field, gc);
        if (!lost) {
            convFigureToPixel(thisFigure);
            gc.setFill(thisFigure.getColor());
            for (int i = 0; i < 4; i++) {
                gc.fillRect(xFigure[i], yFigure[i], RECT_SIZE, RECT_SIZE);
                gc.strokeRect(xFigure[i], yFigure[i], RECT_SIZE - 2, RECT_SIZE - 2);
            }
        } else {
            gameOver(gc);
        }
    }

    private void convFigureToPixel(Figure thisFigure) {
        Block[] blocks = thisFigure.blocks;
        int i = 0;
        for (Block a : blocks) {
            xFigure[i] = convPixel[a.getCol()];
            yFigure[i] = convPixel[a.getRow()];
            i++;
        }
    }

    private void gameOver(GraphicsContext gc) {
        gc.setFont(new Font("Times New Roman", 35));
        gc.setFill(Paint.valueOf("black"));
        gc.fillText("GAME OVER", SIZE_WIDTH / 2 - 50, SIZE_HIGH / 2, 200);
    }

    private void clearField(Block[][] field, GraphicsContext gc) {
        for (int i = 0; i < COL; i++) {
            for (int j = 0; j < ROW; j++) {
                if (field[j][i] == null) {
                    gc.clearRect(pixelsColLine[i], convPixel[j], RECT_SIZE, RECT_SIZE);
                } else if (field[j][i] != null) {
                    gc.setFill(Paint.valueOf("grey"));
                    gc.fillRect(pixelsColLine[i], convPixel[j], RECT_SIZE, RECT_SIZE);
                    gc.strokeRect(pixelsColLine[i], convPixel[j], RECT_SIZE - 2, RECT_SIZE - 2);
                }
            }
        }
    }

}