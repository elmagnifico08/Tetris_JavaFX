package com.example.figures_javafx;

public class O extends Figure {
    public O() {
        super( new Block[]{new Block(0, 4), new Block(0, 5), new Block(1, 4), new Block(1, 5)});
//        blocks[0] = new Block(0, 4);
//        blocks[1] = new Block(0, 5);
//        blocks[2] = new Block(1, 4);
//        blocks[3] = new Block(1, 5);
    }
    @Override
    public void moveChange() {
        state = blocks;

    }
    @Override
    public javafx.scene.paint.Color getColor() {
        return javafx.scene.paint.Color.WHEAT;
    }
}
