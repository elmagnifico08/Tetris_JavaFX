package com.example.tetris_javafx;

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


public class Tetris extends Application {

    public Figure oneFigure = Figure.randomFigure();
    public Figure nextFigure = Figure.randomFigure();
    int rowPixel;
    int colPixel;
    int[] allLevel = new int[]{700, 500, 275, 180};
    int thisLevel = 0;
    int[] pixelsRowLine = new int[20];
    int[] pixelsColLine = new int[10];
    int[] pixelsRow = new int[4];
    int[] pixelsCol = new int[4];
    final int ROW = 20;
    final int COL = 10;
    int rectSize = 25;
    final int SIZE_HIGH = ROW * rectSize;
    final int SIZE_WIDTH = COL * rectSize + 100;
    static int goal = 0;
    int[] xFigure = new int[SIZE_WIDTH];
    int[] yFigure = new int[SIZE_HIGH];
    Block[][] field = new Block[ROW][COL];
    Canvas canvas;
    GraphicsContext gc;
    boolean lost = false;
    Thread game;

    int[] convPixel = new int[]{0, 25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500};

    @Override
    public void start(Stage primaryStage) {
        FlowPane root = new FlowPane();
        canvas = new Canvas(SIZE_WIDTH, SIZE_HIGH);
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        root.getChildren().add(canvas);

        canvas.setOnKeyPressed(e -> {
            KeyCode key = e.getCode();
            if (key.equals(KeyCode.UP)) getChangeAction();
            if (key.equals(KeyCode.DOWN)) getDropAction();
            if (key.equals(KeyCode.LEFT)) getLeftAction();
            if (key.equals(KeyCode.RIGHT)) getRightAction();
        });
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, SIZE_WIDTH, SIZE_HIGH, Color.SKYBLUE);
        primaryStage.setTitle("I am T_E_T_R_I_S");
        primaryStage.setScene(scene);
        primaryStage.show();
        startOne();
    }


    public static void main(String[] args) {
        launch();
    }
    public void startOne() {
        game = new Thread(() -> {
            gc.setStroke(Paint.valueOf("black"));
            gc.strokeLine(SIZE_WIDTH - 100, 0, SIZE_WIDTH - 100, 500);
            gc.setFill(Paint.valueOf("black"));
            gc.fillText("SCORES :  " + goal, SIZE_WIDTH - 95, 100);
            gc.setFill(Paint.valueOf("red"));
            gc.fillText("LEVEL :  " + thisLevel, SIZE_WIDTH - 95, 150);
                while (!lost) {
                    convFigureToPixel();
                    try {
                        Thread.sleep(allLevel[thisLevel]);
                        if (canDrop()) {
                            oneFigure.moveDrop();
                            draw();
                        } else {
                            addToField();
                            removeSpace();
                            removeLine();
                            nextFigure = Figure.randomFigure();
                            oneFigure = nextFigure;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        game.start();
    }

    private void draw() {
        clear();
        if (!lost) {
            convFigureToPixel();
            gc.setFill(Paint.valueOf(oneFigure.getColor()));
            for (int i = 0; i < 4; i++) {
                gc.fillRect(xFigure[i], yFigure[i], rectSize, rectSize);
                gc.strokeRect(xFigure[i], yFigure[i], rectSize - 2, rectSize - 2);

            }

        }
    }


    private void convFieldToPixel() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                pixelsColLine[i] = convPixel[i];
                pixelsRowLine[j] = convPixel[j];
            }
        }
    }

    private void convFigureToPixel() {
        Block[] blocks = oneFigure.blocks;
        int i = 0;
        for (Block a : blocks) {
            colPixel = convPixel[a.getCol()];
            rowPixel = convPixel[a.getRow()];
            pixelsCol[i] = colPixel;
            pixelsRow[i] = rowPixel;
            i++;
        }
        for (int j = 0; j < 4; j++) {
            yFigure[j] = pixelsRow[j];
            xFigure[j] = pixelsCol[j];
        }
    }


    private void addToField() {
        Block[] blocks = oneFigure.blocks;
        for (Block c : blocks) {
            int row = c.getRow();
            int col = c.getCol();
            field[row][col] = c;
        }
        for (int i = 0; i < COL; i++) {
            if (field[0][i] != null) {
                gc.setFill(Paint.valueOf("black"));
                gc.setFont(new Font("Times New Roman", 20));
                gc.fillText("GAME OVER" , SIZE_WIDTH/2-50, SIZE_HIGH/2,150);
                lost = true;
                //System.exit(0);
            }
        }
    }


    void removeLine() {
        Block[] blocks = oneFigure.blocks;
        int row = blocks[0].getRow();
        for (Block block : blocks) {
            if (block.getRow() >= row) {
                row = block.getRow();
            }
        }
        for (int i = row; i > 0; i--) {
            while (true) {
                if (isFullLine(field[i])) {
                    Block[] line = field[i];
                    for (Block a : line) {
                        int rowL = a.getRow();
                        int colL = a.getCol();
                        convFieldToPixel();
                        gc.clearRect(pixelsColLine[colL], pixelsRowLine[rowL], rectSize, rectSize);

                    }
                    field[i] = new Block[10];
                    for (int j = i; j > 0; j--) {
                        field[j] = field[j - 1];
                    }
                    addScore();
                    field[0] = new Block[10];
                } else {
                    break;
                }
            }
        }
    }
    void clear() {
        for (int i = 0; i < 10; i++) {
            convFieldToPixel();
            for (int j = 0; j < 20; j++) {
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

    boolean isFullLine(Block[] line) {
        for (Block a : line) {
            if (a == null) {
                return false;
            }
        }

        return true;
    }

    void addScore() {
        if (goal == 90) {
            thisLevel = 1;
        }
        if (goal ==190 ) {
            thisLevel = 2;
        }
        if (goal == 290) {
            thisLevel = 3;
        }
        goal += 10;
        gc.clearRect(SIZE_WIDTH - 95, 0, SIZE_WIDTH - 95, 500);
        gc.setFill(Paint.valueOf("black"));
        gc.fillText("SCORES :  " + goal, SIZE_WIDTH - 95, 100);
        gc.setFill(Paint.valueOf("red"));
        gc.fillText("LEVEL :  " + thisLevel, SIZE_WIDTH - 95, 150);

    }

    void removeSpace() {
        for (int i = 0; i < COL; i++) {
            for (int j = 0; j < ROW; j++) {
                if (field[j][i] != null) {
                    if (field[j][Math.max(i, 1) - 1] == null && field[j][Math.min(8, j) + 1] == null) {
                        if(field[Math.min(j,19)+1][i] == null) {
                            field[j][i].drop();
                        }
                    }
                } break;
            }
        }
    }
    private boolean canDrop() {
        Block[] blocks = oneFigure.blocks;
        for (Block c : blocks) {
            int row = c.getRow();
            int col = c.getCol();
            if (row == 19) {
                return false;
            }
            if (field[row + 1][col] != null) {
                return false;
            }
        }
        return true;
    }

    boolean canRight() {
        Block[] blocks = oneFigure.blocks;
        for (Block a : blocks) {
            int row = a.getRow();
            int col = a.getCol();
            if (col == 9) {
                return false;
            }
            if (field[row][col + 1] != null) {
                return false;
            }
        }
        return true;
    }

    boolean canLeft() {
        Block[] blocks = oneFigure.blocks;
        for (Block a : blocks) {
            int row = a.getRow();
            int col = a.getCol();
            if (col == 0) {
                return false;
            }
            if (field[row][col - 1] != null) {
                return false;
            }
        }
        return true;
    }

    boolean canRotate() {
        oneFigure.moveChange();
        Block[] states = oneFigure.state;
        for (Block a : states) {
            int row = a.getRow();
            int col = a.getCol();
            if (a.getCol() > 9) {
                return false;
            }
            if (a.getCol() < 0) {
                return false;
            }
            if (a.getRow() < 0) {
                return false;
            }
            if (field[row][col] != null) {
                return false;
            }
        }
        return true;
    }


    void getRightAction() {
        if (canRight()) {
            oneFigure.moveRight();
            draw();
        }
    }

    void getLeftAction() {
        if (canLeft()) {
            oneFigure.moveLeft();
            draw();
        }
    }

    void getDropAction() {
        if (canDrop()) {
            oneFigure.moveDrop();
            draw();
        }
    }

    void getChangeAction() {
        if (canRotate()) {
            oneFigure.blocks = oneFigure.state;
            draw();
        }
    }

}
