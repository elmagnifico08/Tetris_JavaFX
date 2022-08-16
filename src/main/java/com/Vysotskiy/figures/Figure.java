package com.Vysotskiy.figures;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

public abstract class Figure {
   protected int num = 1;
   @Getter @Setter
    protected Block[] state = new Block[4];
   @Getter @Setter
    protected Block[] blocks;

    public Figure(Block[] blocks) {
        this.blocks = blocks;
    }

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

