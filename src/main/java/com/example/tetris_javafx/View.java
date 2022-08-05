package com.example.tetris_javafx;

import com.example.figures_javafx.Block;
import com.example.figures_javafx.Figure;
import com.example.interface_javafx.Constant;
import com.example.interface_javafx.ConvBlockToPixel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class View implements ConvBlockToPixel, Constant {
    private int[] xFigure = new int[4];
    private int[] yFigure = new int[4];
    private int[] pixelsRowLine = new int[ROW];
    private int[] pixelsColLine = new int[COL];
    private int[] convPixel = convToPixel(ROW, RECT_SIZE);
    Canvas canvas = new Canvas(SIZE_WIDTH, SIZE_HIGH);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    public int getSIZE_HIGH() {
        return SIZE_HIGH;
    }

    public int getSIZE_WIDTH() {
        return SIZE_WIDTH;
    }

    public void drawStrokeScoreLevel(int goal, int thisLevel, GraphicsContext gc) {
        gc.setStroke(Paint.valueOf("black"));
        gc.strokeLine(SIZE_WIDTH - 100, 0, SIZE_WIDTH - 100, SIZE_HIGH);
        gc.setFill(Paint.valueOf("black"));
        gc.fillText("SCORES :  " + goal, SIZE_WIDTH - 95, 100);
        gc.setFill(Paint.valueOf("red"));
        gc.fillText("LEVEL :  " + thisLevel, SIZE_WIDTH - 95, 150);
    }

    public void convFigureToPixel(Figure thisFigure) {
        Block[] blocks = thisFigure.blocks;
        int i = 0;
        for (Block a : blocks) {
            xFigure[i] = convPixel[a.getCol()];
            yFigure[i] = convPixel[a.getRow()];
            i++;
        }
    }

    private void convFieldToPixel() {
        for (int i = 0; i < COL; i++) {
            for (int j = 0; j < ROW; j++) {
                pixelsColLine[i] = convPixel[i];
                pixelsRowLine[j] = convPixel[j];
            }
        }
    }

    public void draw(Block[][] field, Figure thisFigure, GraphicsContext gc,boolean lost) {
        clearField(field, gc);
        if (!lost) {
            convFigureToPixel(thisFigure);
            gc.setFill(thisFigure.getColor());
            for (int i = 0; i < 4; i++) {
                gc.fillRect(xFigure[i], yFigure[i], RECT_SIZE, RECT_SIZE);
                gc.strokeRect(xFigure[i], yFigure[i], RECT_SIZE - 2, RECT_SIZE - 2);
            }
        }
    }

    public void deleteLine(Block[] field, GraphicsContext gc) {
        Block[] line = field;
        for (Block a : line)
            if (a == null) {
                break;
            } else {
                for (Block b : line) {
                    int rowL = b.getRow();
                    int colL = b.getCol();
                    convFieldToPixel();
                    gc.clearRect(pixelsColLine[colL], pixelsRowLine[rowL], RECT_SIZE, RECT_SIZE);
                }
            }
    }

    public void gameOver(GraphicsContext gc, boolean lost) {
        if(lost) {
            gc.fillText("GAME OVER", SIZE_WIDTH / 2 - 50, SIZE_HIGH / 2, 180);
            gc.setFill(Paint.valueOf("black"));
            gc.setFont(new Font("Times New Roman", 20));
        }
    }


    private void clearField(Block[][] field, GraphicsContext gc) {
        for (int i = 0; i < COL; i++) {
            convFieldToPixel();
            for (int j = 0; j < ROW; j++) {
                if (field[j][i] == null) {
                    gc.clearRect(pixelsColLine[i], pixelsRowLine[j], RECT_SIZE, RECT_SIZE);
                } else if (field[j][i] != null) {
                    gc.setFill(Paint.valueOf("grey"));
                    gc.fillRect(pixelsColLine[i], pixelsRowLine[j], RECT_SIZE, RECT_SIZE);
                    gc.strokeRect(pixelsColLine[i], pixelsRowLine[j], RECT_SIZE - 2, RECT_SIZE - 2);
                }
            }
        }
    }

    public void drawAddScore(int goal, int thisLevel, GraphicsContext gc) {
        gc.clearRect(SIZE_WIDTH - 95, 0, SIZE_WIDTH - 95, SIZE_HIGH);
        gc.setFill(Paint.valueOf("black"));
        gc.fillText("SCORES :  " + goal, SIZE_WIDTH - 95, 100);
        gc.setFill(Paint.valueOf("red"));
        gc.fillText("LEVEL :  " + thisLevel, SIZE_WIDTH - 95, 150);

    }
}
