package com.example.figures_javafx;
public class Block {

    private int row;
    private int col;

    public Block(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
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
