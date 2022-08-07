package com.Vysotskiy.figures;

public class O extends Figure {
    public O() {
        super( new Block[]{new Block(0, 4), new Block(0, 5), new Block(1, 4), new Block(1, 5)});

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
