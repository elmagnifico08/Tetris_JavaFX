package com.example.figures.javafx;

import com.example.tetris_javafx.Block;

import java.awt.*;

public class O extends Figure {
    public O() {
        blocks[0] = new Block(0, 4);
        blocks[1] = new Block(0, 5);
        blocks[2] = new Block(1, 4);
        blocks[3] = new Block(1, 5);
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
