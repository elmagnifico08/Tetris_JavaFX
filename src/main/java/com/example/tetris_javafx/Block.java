package com.example.tetris_javafx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block {

    private int row;
    private int col;

    public Block( int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }


    public void right() {
        col++;
    }

    public void left() {
        col--;
    }

    public void drop() {
        row++;
    }

    @Override
    public String toString() {
        return "Block{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
