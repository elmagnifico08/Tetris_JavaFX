package com.Vysotskiy.tetris;

import com.Vysotskiy.figures.Block;
import com.Vysotskiy.figures.Figure;

import java.util.Arrays;

public class Controller {

    public void rightAction(Figure figure, Block[][] field) {
        if (canRight(figure, field)) {
            figure.moveRight();
        }
    }

    public void leftAction(Figure figure, Block[][] field) {
        if (canLeft(figure, field)) {
            figure.moveLeft();

        }
    }

    public void dropAction(Figure figure, Block[][] field) {
        if (canDrop(figure, field)) {
            figure.moveDrop();

        }
    }
    public void changeAction(Figure figure, Block[][] field) {
        if (canRotate(figure, field)) {
            figure.setBlocks(figure.getState());
        }
    }
    public boolean canDrop(Figure figure, Block[][] field) {
        return Arrays.stream(figure.getBlocks()).noneMatch(e -> e.getRow() == 19
                || field[e.getRow() + 1][e.getCol()] != null);
    }
    private boolean canRight(Figure figure, Block[][] field) {
        return Arrays.stream(figure.getBlocks()).noneMatch(e -> e.getCol() == 9
                || field[e.getRow()][e.getCol() + 1] != null);
    }
    private boolean canLeft(Figure figure, Block[][] field) {
        return Arrays.stream(figure.getBlocks()).noneMatch(e -> e.getCol() == 0
                || field[e.getRow()][e.getCol() - 1] != null);
    }
    private boolean canRotate(Figure figure, Block[][] field) {
        figure.moveChange();
        return Arrays.stream(figure.getState()).noneMatch(e -> e.getCol() > 9 || e.getCol() < 0
                || e.getRow() < 0 || field[e.getRow()][e.getCol()] != null);

    }

}
