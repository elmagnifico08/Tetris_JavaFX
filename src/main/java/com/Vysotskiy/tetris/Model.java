package com.Vysotskiy.tetris;

import com.Vysotskiy.figures.Block;
import com.Vysotskiy.figures.Figure;
import com.Vysotskiy.interfaces.Constant;
import com.Vysotskiy.interfaces.RandomFigure;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public class Model implements RandomFigure, Constant {
    private final long[] LEVELS = new long[]{700, 500, 275, 180};
    private Block[][] field = new Block[ROW][COL];
    @Setter
    private Figure thisFigure = randomFigure();
    @Setter
    private Figure nextFigure = randomFigure();
    private int thisLevel = 0;
    private int goal = 0;
    private boolean lost = false;


    public void addFigureToField() {
        Block[] blocks = thisFigure.blocks;
        for (Block c : blocks) {
            field[c.getRow()][c.getCol()] = Arrays.stream(blocks).iterator().next();
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
        if (goal < 310)
            thisLevel = goal / 100;
    }

}
