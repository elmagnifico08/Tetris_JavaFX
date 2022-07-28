package com.example.figures.javafx;

import com.example.tetris_javafx.Block;

import java.util.Arrays;

public abstract class Figure {
    int num = 1;
    public Block[] state = new Block[4];
    public Block[] blocks = new Block[4];
    public abstract javafx.scene.paint.Color getColor();
    public abstract void moveChange();
    public void moveLeft() {
        Arrays.stream(blocks).forEach(Block::left);
    }

    public void moveRight() {
        Arrays.stream(blocks).forEach(Block::right);
    }

    public void moveDrop() {
        Arrays.stream(blocks).forEach(Block::drop);
    }


}

