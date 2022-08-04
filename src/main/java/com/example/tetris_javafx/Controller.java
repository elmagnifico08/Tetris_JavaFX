package com.example.tetris_javafx;

import com.example.figures_javafx.Block;
import com.example.figures_javafx.Figure;

public class Controller  {
        public  boolean canDrop(Figure figure,Block [][] field) {
        Block[] blocks = figure.blocks;
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

    private boolean canRight(Figure figure,Block [][] field) {
        Block[] blocks = figure.blocks;
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

    private boolean canLeft(Figure figure,Block [][] field) {
        Block[] blocks = figure.blocks;
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

    private boolean canRotate(Figure figure,Block [][] field) {
        figure.moveChange();
        Block[] states = figure.state;
        for (Block a : states) {
            int row = a.getRow();
            int col = a.getCol();
            if (a.getCol() > 9 || a.getCol() < 0 || a.getRow() < 0) {
                return false;
            }
            if (field[row][col] != null) {
                return false;
            }
        }
        return true;
    }

    public void getRightAction(Figure figure,Block [][] field) {
        if (canRight(figure,field)) {
            figure.moveRight();
        }
    }

    public void getLeftAction(Figure figure,Block [][] field) {
        if (canLeft(figure,field)) {
            figure.moveLeft();

        }
    }

    public void getDropAction(Figure figure,Block [][] field) {
        if (canDrop(figure,field)) {
            figure.moveDrop();

        }
    }

    public void getChangeAction(Figure figure,Block [][] field) {
        if (canRotate(figure,field)) {
            figure.blocks = figure.state;
        }
    }
}
