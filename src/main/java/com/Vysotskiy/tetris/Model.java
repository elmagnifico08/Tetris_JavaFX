package com.Vysotskiy.tetris;

import com.Vysotskiy.figures.*;
import com.Vysotskiy.interfaces.Modelable;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public class Model implements Modelable {
    final private int ROW = 20;
    final private int COL = 10;
    private final int MIN_NUM_OF_POINT = 10;
    private final int MAX_POINT_GO_TO_NEXT_LEVEL = 310;
    private final long[] LEVELS = new long[]{700, 500, 275, 180};
    private final Block[][] field = new Block[ROW][COL];
    private Figure thisFigure = randomFigure();
    private Figure nextFigure = randomFigure();
    private int thisLevel = 0;
    private int goal = 0;
    private boolean lost = false;


    @Override
    public void figureFell() {
        addFigureToField();
        removeLine();
        addNewFigure();
    }

    @Override
    public void figureMoveDrop() {
        thisFigure.moveDrop();
    }

    @Override
    public boolean figureCanDrop() {
        return Arrays.stream(thisFigure.getBlocks()).noneMatch(e -> e.getRow() == 19
                || field[e.getRow() + 1][e.getCol()] != null);
    }


    private void addFigureToField() {
        for (Block c : thisFigure.getBlocks()) {
            field[c.getRow()][c.getCol()] = Arrays.stream(thisFigure.getBlocks()).iterator().next();
        }
        if (Arrays.stream(thisFigure.getBlocks()).anyMatch(e -> field[0][e.getCol()] != null)) {
            lost = true;
        }
    }

    private void removeLine() {
        for (int i = maxRowThisFigure(); i > 0; i--) {
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

    private void addNewFigure() {
        nextFigure = randomFigure();
        thisFigure = nextFigure;
    }

    private int maxRowThisFigure() {
        return Arrays.stream(thisFigure.getBlocks()).map(Block::getRow).max(Integer::compare).get();
    }

    private boolean isFullLine(Block[] line) {
        return Arrays.stream(line).noneMatch(Objects::isNull);
    }

    private void addScore() {
        goal += MIN_NUM_OF_POINT;
        if (goal < MAX_POINT_GO_TO_NEXT_LEVEL)
            thisLevel = goal / 100;
    }

    private Figure randomFigure() {
        int num = (int) (Math.random() * 7);
        Figure f = new T();
        switch (num) {
            case 0 -> f = new T();
            case 1 -> f = new I();
            case 2 -> f = new J();
            case 3 -> f = new L();
            case 4 -> f = new NotZ();
            case 5 -> f = new O();
            case 6 -> f = new Z();
        }
        return f;
    }

}
