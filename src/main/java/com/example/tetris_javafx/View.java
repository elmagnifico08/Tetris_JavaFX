package com.example.tetris_javafx;

import com.example.figures_javafx.Block;
import com.example.figures_javafx.Figure;
import com.example.interface_javafx.ConvBlockToPixel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class View implements ConvBlockToPixel {
    Model model = new Model();
    private final int rectSize = 25;
    private final int SIZE_HIGH = model.getROW() * rectSize;
    private final int SIZE_WIDTH = model.getCOL() * rectSize + 100;
    private int[] xFigure = new int[4];
    private int[] yFigure = new int[4];
    private int[] pixelsRowLine = new int[model.getROW()];
    private int[] pixelsColLine = new int[model.getCOL()];
    private int[] convPixel = convToPixel(model.getROW(), rectSize);
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
        for (int i = 0; i < model.getCOL(); i++) {
            for (int j = 0; j < model.getROW(); j++) {
                pixelsColLine[i] = convPixel[i];
                pixelsRowLine[j] = convPixel[j];
            }
        }
    }

    public void draw(Block[][] field, Figure thisFigure, GraphicsContext gc) {
        clearField(field, gc);
        if (!model.getLost()) {
            convFigureToPixel(thisFigure);
            gc.setFill(thisFigure.getColor());
            for (int i = 0; i < 4; i++) {
                gc.fillRect(xFigure[i], yFigure[i], rectSize, rectSize);
                gc.strokeRect(xFigure[i], yFigure[i], rectSize - 2, rectSize - 2);
            }
        }
    }

    public void deleteLine(Block [] field, GraphicsContext gc) {
        Block[] line = field;
        for (Block a : line)
            if (a == null){
                break;
            } else{
                for (Block b : line) {
                    int rowL = b.getRow();
                    int colL = b.getCol();
                    convFieldToPixel();
                    gc.clearRect(pixelsColLine[colL], pixelsRowLine[rowL], rectSize, rectSize);
                }
            }
    }

    public boolean gameOver(GraphicsContext gc, boolean lost) {
        if(lost) {
            gc.setFill(Paint.valueOf("black"));
            gc.setFont(new Font("Times New Roman", 20));
            gc.fillText("GAME OVER" , SIZE_WIDTH/2-50, SIZE_HIGH/2,180);
            return true;
        }
        return false;
    }


    private void clearField(Block[][] field, GraphicsContext gc) {
        for (int i = 0; i < model.getCOL(); i++) {
            convFieldToPixel();
            for (int j = 0; j < model.getROW(); j++) {
                if (field[j][i] == null) {
                    gc.clearRect(pixelsColLine[i], pixelsRowLine[j], rectSize, rectSize);
                } else if (field[j][i] != null) {
                    gc.setFill(Paint.valueOf("grey"));
                    gc.fillRect(pixelsColLine[i], pixelsRowLine[j], rectSize, rectSize);
                    gc.strokeRect(pixelsColLine[i], pixelsRowLine[j], rectSize - 2, rectSize - 2);
                }
            }
        }
    }

    public void drawAddScore(int goal, int thisLevel, GraphicsContext gc) {
        gc.clearRect(SIZE_WIDTH - 95, 0, SIZE_WIDTH - 95, 500);
        gc.setFill(Paint.valueOf("black"));
        gc.fillText("SCORES :  " + goal, SIZE_WIDTH - 95, 100);
        gc.setFill(Paint.valueOf("red"));
        gc.fillText("LEVEL :  " + thisLevel, SIZE_WIDTH - 95, 150);

    }
}
