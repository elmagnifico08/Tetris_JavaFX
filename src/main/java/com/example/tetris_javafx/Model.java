package com.example.tetris_javafx;
import com.example.figures_javafx.Block;
import com.example.figures_javafx.Figure;
import com.example.interface_javafx.Constant;
import com.example.interface_javafx.RandomFigure;
import java.util.Arrays;
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

    public boolean addFigureToField() {
        Block[] blocks = thisFigure.blocks;
        for (Block c : blocks) {
            int row = c.getRow();
            int col = c.getCol();
            field[row][col] = c;
        }
        if(Arrays.stream(blocks).anyMatch(e-> field[1][e.getCol()] != null)){
            return lost = true;
        }
        return lost = false ;
    }
    public Block[] removeLine() {
        Block[] blocks = thisFigure.blocks;
        int row = Arrays.stream(blocks).map(Block::getRow).max(Integer::compare).get();
        for (int i = row; i > 0; i--) {
                if (isFullLine(field[i])) {
                    Block[] line = field[i];
                    field[i] = new Block[COL];
                    System.arraycopy(field, 0, field, 1, i);
                    field[0] = new Block[COL];
                    addScore();
                    return line;
                } else {
                    break;
                }
        }
        return field[0];
    }
    private boolean isFullLine(Block[] line) {
        return Arrays.stream(line).noneMatch(e -> e == null);
    }
    private void addScore() {
        goal += 10;
        thisLevel = goal / 100;
    }

}
