package com.Vysotskiy.mvc.model;

import com.Vysotskiy.figures.Block;
import com.Vysotskiy.figures.Figure;
import com.Vysotskiy.figures.I;
import com.Vysotskiy.figures.J;
import com.Vysotskiy.figures.L;
import com.Vysotskiy.figures.NotZ;
import com.Vysotskiy.figures.O;
import com.Vysotskiy.figures.T;
import com.Vysotskiy.figures.Z;

import java.util.Arrays;
import java.util.Objects;

import javafx.scene.paint.Color;


public class Model implements MoveGameField, ReplaceablePositionFigure,Scoringable {
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
    private boolean gameOver = false;


    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public long[] getLEVELS() {
        return LEVELS;
    }

    @Override
    public int getGoal() {
        return goal;
    }

    @Override
    public int getThisLevel() {
        return thisLevel;
    }
    @Override
    public Figure getThisFigure() {
        return thisFigure;
    }

    @Override
    public Color getColor() {
        return  thisFigure.getColor();
    }
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
        return Arrays.stream(thisFigure.getBlocks()).noneMatch(e -> e.getRow()==19
                || field[e.getRow() + 1][e.getCol()]!=null);
    }

    @Override
    public Block[][] getField() {
        return field;
    }

    @Override
    public Figure rightAction() {
        if (Arrays.stream(thisFigure.getBlocks()).noneMatch(e -> e.getCol()==COL - 1
                || field[e.getRow()][e.getCol() + 1]!=null)) {
            thisFigure.moveRight();
        }
        return thisFigure;
    }

    @Override
    public Figure leftAction() {
        if (Arrays.stream(thisFigure.getBlocks()).noneMatch(e -> e.getCol()==0
                || field[e.getRow()][e.getCol() - 1]!=null)) {
            thisFigure.moveLeft();
        }
        return thisFigure;
    }

    @Override
    public Figure dropAction() {
        if (Arrays.stream(thisFigure.getBlocks()).noneMatch(e -> e.getRow()==ROW - 1
                || field[e.getRow() + 1][e.getCol()]!=null)) {
            thisFigure.moveDrop();
        }
        return thisFigure;
    }

    @Override
    public Figure changeAction() {
        thisFigure.moveChange();
        if (Arrays.stream(thisFigure.getState()).noneMatch(e -> e.getCol() > 9 || e.getCol() < 0
                || e.getRow() < 0 || field[e.getRow()][e.getCol()]!=null)) {
            thisFigure.setBlocks(thisFigure.getState());
        }
        return thisFigure;
    }

    private void addFigureToField() {
        for (Block c : thisFigure.getBlocks()) {
            field[c.getRow()][c.getCol()] = Arrays.stream(thisFigure.getBlocks()).iterator().next();
        }
        if (Arrays.stream(thisFigure.getBlocks()).anyMatch(e -> field[0][e.getCol()]!=null)) {
            gameOver = true;
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
