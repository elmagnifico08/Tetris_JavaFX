package com.Vysotskiy.figures;


public class L extends Figure {
    public L() {
        super(new Block[]{ new Block (0, 4),new Block(0, 3),new Block(0, 5),new Block(1, 3)});

    }
    @Override
    public javafx.scene.paint.Color getColor() {
        return javafx.scene.paint.Color.SANDYBROWN;
    }
    @Override
    public void moveChange() {
        state = new Block[4];
        num++;
        switch (num) {
            case 1 -> {
                state[0] = blocks[0];
                state[1] = new Block(blocks[0].getRow(),blocks[0].getCol()-1);
                state[2] = new Block(blocks[0].getRow(),blocks[0].getCol()+1);
                state[3] = new Block(blocks[0].getRow()+1,blocks[0].getCol()-1);
            }
            case 2 -> {
                state[0] = blocks[0];
                state[1] = new Block(blocks[0].getRow()+1,blocks[0].getCol());
                state[2] = new Block(blocks[0].getRow()-1,blocks[0].getCol());
                state[3] = new Block(blocks[0].getRow()-1,blocks[0].getCol()-1);

            }
            case 3 -> {
                state[0] = blocks[0];
                state[1] = new Block(blocks[0].getRow(),blocks[0].getCol()+1);
                state[2] = new Block(blocks[0].getRow(),blocks[0].getCol()-1);
                state[3] = new Block(blocks[0].getRow()-1,blocks[0].getCol()+1);
            }
            case 4 -> {
                state[0] = blocks[0];
                state[1] = new Block(blocks[0].getRow()+1,blocks[0].getCol());
                state[2] = new Block(blocks[0].getRow()-1,blocks[0].getCol());
                state[3] = new Block(blocks[0].getRow()+1,blocks[0].getCol()+1);
            }
        }

        if (num==4)num=0;
    }
}
