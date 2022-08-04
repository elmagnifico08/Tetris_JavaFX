package com.example.tetris_javafx;

import com.example.figures_javafx.Block;
import com.example.figures_javafx.Figure;
import com.example.interface_javafx.RandomFigure;

public class Model implements RandomFigure {
    private final int ROW = 20;
    private final int COL = 10;
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

    public int getROW() {
        return ROW;
    }

    public int getCOL() {
        return COL;
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
        for (int i = 0; i < COL; i++) {
            if (field[0][i] != null) {
                return lost = true;
                //System.exit(0);
            }
        }
        return false;
    }

    public Block[] removeLine() {
        Block[] blocks = thisFigure.blocks;
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
                    field[i] = new Block[COL];
                    System.arraycopy(field, 0, field, 1, i);
                    field[0] = new Block[COL];
                    addScore();
                    return line;
                } else {
                    break;
                }
            }
        }
        return field[0];

    }

    private boolean isFullLine(Block[] line) {
        for (Block a : line) {
            if (a == null) {
                return false;
            }
        }
        return true;
    }

    private void addScore() {
        thisLevel = goal / 100;
        goal += 10;
    }
//
//
//    public boolean canDrop() {
//        Block[] blocks = thisFigure.blocks;
//        for (Block c : blocks) {
//            int row = c.getRow();
//            int col = c.getCol();
//            if (row == 19) {
//                return false;
//            }
//            if (field[row + 1][col] != null) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean canRight() {
//        Block[] blocks = thisFigure.blocks;
//        for (Block a : blocks) {
//            int row = a.getRow();
//            int col = a.getCol();
//            if (col == 9) {
//                return false;
//            }
//            if (field[row][col + 1] != null) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean canLeft() {
//        Block[] blocks = thisFigure.blocks;
//        for (Block a : blocks) {
//            int row = a.getRow();
//            int col = a.getCol();
//            if (col == 0) {
//                return false;
//            }
//            if (field[row][col - 1] != null) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean canRotate() {
//        thisFigure.moveChange();
//        Block[] states = thisFigure.state;
//        for (Block a : states) {
//            int row = a.getRow();
//            int col = a.getCol();
//            if (a.getCol() > 9 || a.getCol() < 0 || a.getRow() < 0) {
//                return false;
//            }
//            if (field[row][col] != null) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public void getRightAction() {
//        if (canRight()) {
//            thisFigure.moveRight();
//        }
//    }
//
//    public void getLeftAction() {
//        if (canLeft()) {
//            thisFigure.moveLeft();
//
//        }
//    }
//
//    public void getDropAction() {
//        if (canDrop()) {
//            thisFigure.moveDrop();
//
//        }
//    }
//
//    public void getChangeAction() {
//        if (canRotate()) {
//            thisFigure.blocks = thisFigure.state;
//        }
//    }

}
