package com.example.tetris_javafx;

import Figures.*;
import java.util.Arrays;

public class Figure {
   protected Block[] state = new Block[4];
    protected Block[] blocks = new Block[4];
    protected String color;

    public Figure() {
    }

    public String getColor() {
        return color;
    }

    public void moveLeft() {
        for (Block b : this.blocks) {
            b.left();
        }
    }

    public void moveRight() {
        for (Block b : this.blocks) {
            b.right();
        }
    }

    public void moveDrop() {
        for (Block b : this.blocks) {
            b.drop();
        }
    }

    public void moveChange() {
    }

    public static Figure randomFigure() {
        Figure t = null;
        int num = (int) (Math.random() * 7);
        switch (num) {
            case 0 -> t = new T();
            case 1 -> t = new I();
            case 2 -> t = new J();
            case 3 -> t = new L();
            case 4 -> t = new NotZ();
            case 5 -> t = new O();
            case 6 -> t = new Z();
        }
        return t;
    }

    @Override
    public String toString() {
        return "Figure{" +
                "blocks=" + Arrays.toString(blocks) +
                '}';
    }

}

