package com.example.figures.javafx;

import com.example.tetris_javafx.Block;
import javafx.scene.paint.Color;

public class J extends Figure {
    public J() {
        blocks[0] = new Block(0, 4);
        blocks[1] = new Block(0, 3);
        blocks[2] = new Block(0, 5);
        blocks[3] = new Block(1, 5);
    }

    @Override
    public javafx.scene.paint.Color getColor() {
        return Color.DARKGOLDENROD;
    }

    @Override
    public void moveChange() {
        state = new Block[4];
        num++;
        switch (num) {
            case 1 -> {
                state[0] = blocks[0];
                state[1] = new Block(blocks[0].getRow(), blocks[0].getCol() - 1);
                state[2] = new Block(blocks[0].getRow(), blocks[0].getCol() + 1);
                state[3] = new Block(blocks[0].getRow() + 1, blocks[0].getCol() + 1);
                break;
            }
            case 2 -> {
                state[0] = blocks[0];
                state[1] = new Block(blocks[0].getRow() - 1, blocks[0].getCol());
                state[2] = new Block(blocks[0].getRow() + 1, blocks[0].getCol());
                state[3] = new Block(blocks[0].getRow() + 1, blocks[0].getCol() - 1);

                break;
            }
            case 3 -> {
                state[0] = blocks[0];
                state[1] = new Block(blocks[0].getRow(), blocks[0].getCol() + 1);
                state[2] = new Block(blocks[0].getRow(), blocks[0].getCol() - 1);
                state[3] = new Block(blocks[0].getRow() - 1, blocks[0].getCol() - 1);
                break;
            }
            case 4 -> {
                state[0] = blocks[0];
                state[1] = new Block(blocks[0].getRow() + 1, blocks[0].getCol());
                state[2] = new Block(blocks[0].getRow() - 1, blocks[0].getCol());
                state[3] = new Block(blocks[0].getRow() - 1, blocks[0].getCol() + 1);
                break;
            }
        }
        if (num == 4) num = 0;
    }
}

