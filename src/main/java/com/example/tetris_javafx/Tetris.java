package com.example.tetris_javafx;

import com.example.figures_javafx.Block;
import com.example.figures_javafx.Figure;
import com.example.interface_javafx.ConvBlockToPixel;
import com.example.interface_javafx.RandomFigure;



public class Tetris  implements RandomFigure, ConvBlockToPixel {
    private final int ROW = 20;
    private final int COL = 10;
    private final int rectSize = 25;
    private final long[] LEVELS = new long[]{700, 500, 275, 180};
    private Block[][] field = new Block[ROW][COL];
    private Figure thisFigure = randomFigure();
    private Figure nextFigure = randomFigure();
    private int rowPixel;
    private int colPixel;
    private int thisLevel = 0;
    static int goal = 0;
    boolean lost = false;

//    public void startOne() {
//        Thread game = new Thread(() -> {
//            while (!lost) {
//                convFigureToPixel();
//                try {
//                    Thread.sleep(LEVELS[thisLevel]);
//                    if (canDrop()) {
//                        thisFigure.moveDrop();
//                        draw();
//                    } else {
//                        addFigureToField();
//                        removeLine();
//                        nextFigure = randomFigure();
//                        thisFigure = nextFigure;
//                    }
//                } catch (InterruptedException e) {
//                    System.out.println(e);
//                }
//            }
//        });
//        game.start();
//    }


    private void addFigureToField() {
        Block[] blocks = thisFigure.blocks;
        for (Block c : blocks) {
            int row = c.getRow();
            int col = c.getCol();
            field[row][col] = c;
        }
        for (int i = 0; i < COL; i++) {
            if (field[0][i] != null) {
                lost = true;
                //System.exit(0);
            }
        }
    }

    private void removeLine() {
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
                    addScore();
                    field[0] = new Block[COL];
                } else {
                    break;
                }
            }
        }
    }

    private boolean isFullLine(Block[] line) {
        for (Block a : line) {
            if (a == null) {
                return false;
            }
        }
        return true;
    }

    void addScore() {
      thisLevel = goal/100;
        goal += 10;
    }


    private boolean canDrop() {
        Block[] blocks = thisFigure.blocks;
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

    private boolean canRight() {
        Block[] blocks = thisFigure.blocks;
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

    private boolean canLeft() {
        Block[] blocks = thisFigure.blocks;
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

    private boolean canRotate() {
        thisFigure.moveChange();
        Block[] states = thisFigure.state;
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

    public void getRightAction() {
        if (canRight()) {
            thisFigure.moveRight();
        }
    }
    public void getLeftAction() {
        if (canLeft()) {
            thisFigure.moveLeft();

        }
    }
    public void getDropAction() {
        if (canDrop()) {
            thisFigure.moveDrop();

        }
    }
    public void getChangeAction() {
        if (canRotate()) {
            thisFigure.blocks = thisFigure.state;
        }
    }

}
