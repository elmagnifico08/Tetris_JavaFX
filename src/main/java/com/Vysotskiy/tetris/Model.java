package com.Vysotskiy.tetris;

import com.Vysotskiy.figures.Block;
import com.Vysotskiy.figures.Figure;
import com.Vysotskiy.interfaces.Constant;
import com.Vysotskiy.interfaces.RandomFigure;

import java.util.Arrays;
import java.util.Objects;

public class Model implements RandomFigure, Constant {
    private final long[] LEVELS = new long[]{700, 500, 275, 180};
    private Block[][] field = new Block[ROW][COL];
    private Figure thisFigure = randomFigure();
    private Figure nextFigure = randomFigure();
    private int thisLevel = 0;
    private int goal = 0;
    private boolean lost = false;

    public boolean getLost() {
        return lost;
    }

    public long[] getLEVELS() {
        return LEVELS;
    }

    public Block[][] getField() {
        return field;
    }

    public void setThisFigure(Figure thisFigure) {
        this.thisFigure = thisFigure;
    }

    public void setNextFigure(Figure nextFigure) {
        this.nextFigure = nextFigure;
    }

    public Figure getThisFigure() {
        return thisFigure;
    }

    public Figure getNextFigure() {
        return nextFigure;
    }

    public int getThisLevel() {
        return thisLevel;
    }

    public int getGoal() {
        return goal;
    }

    public void addFigureToField() {
        Block[] blocks = thisFigure.blocks;
        for (Block c : blocks) {
            int row = c.getRow();
            int col = c.getCol();
            field[row][col] = c;
        }
        if (Arrays.stream(blocks).anyMatch(e -> field[1][e.getCol()] != null)) {
            lost = true;
        }
    }

    public void removeLine() {
        Block[] blocks = thisFigure.blocks;
        int row = Arrays.stream(blocks).map(Block::getRow).max(Integer::compare).get();
        for (int i = row; i > 0; i--) {
            while (true) {
                if (isFullLine(field[i])) {
                    field[i] = new Block[COL];
                    System.arraycopy(field, 0, field, 1, i);
                    field[0] = new Block[COL];
                    addScore();
                } else {
                    break;
                }
            }
        }
    }

    private boolean isFullLine(Block[] line) {
        return Arrays.stream(line).noneMatch(Objects::isNull);
    }
    private void addScore() {
        goal += 10;
        if(goal< 310 )
        thisLevel = goal / 100;
    }

}
