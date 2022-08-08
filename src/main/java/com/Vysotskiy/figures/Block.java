package com.Vysotskiy.figures;

import lombok.Getter;

@Getter
public class Block {

    private int row;
    private int col;

    public Block(int row, int col) {
        this.row = row;
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
}
