package com.Vysotskiy.tetris;

import com.Vysotskiy.figures.Block;
import com.Vysotskiy.figures.Figure;

import java.util.Arrays;

public class Controller {

    public void getRightAction(Figure figure, Block[][] field) {
        if (canRight(figure, field)) {
            figure.moveRight();
        }
    }

    public void getLeftAction(Figure figure, Block[][] field) {
        if (canLeft(figure, field)) {
            figure.moveLeft();

        }
    }

    public void getDropAction(Figure figure, Block[][] field) {
        if (canDrop(figure, field)) {
            figure.moveDrop();

        }
    }

    public void getChangeAction(Figure figure, Block[][] field) {
        if (canRotate(figure, field)) {
            figure.blocks = figure.state;
        }
    }
    public boolean canDrop(Figure figure, Block[][] field) {
        Block[] blocks = figure.blocks;
        return Arrays.stream(blocks).noneMatch(e -> e.getRow() == 19
                || field[e.getRow() + 1][e.getCol()] != null);
    }

    private boolean canRight(Figure figure, Block[][] field) {
        Block[] blocks = figure.blocks;
        return Arrays.stream(blocks).noneMatch(e -> e.getCol() == 9
                || field[e.getRow()][e.getCol() + 1] != null);

    }

    private boolean canLeft(Figure figure, Block[][] field) {
        Block[] blocks = figure.blocks;
        return Arrays.stream(blocks).noneMatch(e -> e.getCol() == 0
                || field[e.getRow()][e.getCol() - 1] != null);
    }

    private boolean canRotate(Figure figure, Block[][] field) {
        figure.moveChange();
        Block[] states = figure.state;
        return Arrays.stream(states).noneMatch(e -> e.getCol() > 9 || e.getCol() < 0
                || e.getRow() < 0 || field[e.getRow()][e.getCol()] != null);

    }

}
