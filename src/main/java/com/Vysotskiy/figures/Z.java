package com.Vysotskiy.figures;


public class Z extends Figure {
    public Z() {
        super( new Block[]{new Block(1, 4), new Block(0, 3), new Block(0, 4), new Block(1, 5)});
    }
    @Override
    public javafx.scene.paint.Color getColor() {
        return javafx.scene.paint.Color.INDIANRED;
    }

    @Override
    public void moveChange() {
        state = new Block[4];
        num++;
        switch (num) {
            case 1 -> {
                state[0] = blocks[0];
                state[1] = new Block(blocks[0].getRow()-1,blocks[0].getCol()-1);
                state[2] = new Block(blocks[0].getRow()-1,blocks[0].getCol());
                state[3] = new Block(blocks[0].getRow(),blocks[0].getCol()+1);
            }
            case 2 -> {
                state[0] = blocks[0];
                state[1] = new Block(blocks[0].getRow()-1,blocks[0].getCol()+1);
                state[2] = new Block(blocks[0].getRow(),blocks[0].getCol()+1);
                state[3] = new Block(blocks[0].getRow()+1,blocks[0].getCol());

            }

        }
        if (num==2)num=0;

    }
}
