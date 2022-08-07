package com.Vysotskiy.figures;

import javafx.scene.paint.Color;

public class I extends Figure {

    public I() {
        super( new Block[]{new Block(0, 4), new Block(0, 3), new Block(0, 5), new Block(0, 6)});
    }
    @Override
    public Color getColor() {
        return Color.BLUE;
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
                state[3] = new Block(blocks[0].getRow(), blocks[0].getCol() + 2);

                break;
            }
            case 2 -> {
                state[0] = blocks[0];
                state[1] = new Block(blocks[0].getRow() - 1, blocks[0].getCol());
                state[2] = new Block(blocks[0].getRow() + 1, blocks[0].getCol());
                state[3] = new Block(blocks[0].getRow() + 2, blocks[0].getCol());

                break;
            }
        }
        if (num > 1) num = 0;
    }
}
