package com.Vysotskiy.tetris;

import lombok.Getter;

import java.util.Arrays;

public class Controller {
    @Getter
    Model model = new Model();

    public void rightAction() {
        if (Arrays.stream(model.getThisFigure().getBlocks()).noneMatch(e -> e.getCol() == 9
                || model.getField()[e.getRow()][e.getCol() + 1] != null)) {
            model.getThisFigure().moveRight();
        }
    }

    public void leftAction() {
        if (Arrays.stream(model.getThisFigure().getBlocks()).noneMatch(e -> e.getCol() == 0
                || model.getField()[e.getRow()][e.getCol() - 1] != null)) {
            model.getThisFigure().moveLeft();

        }
    }

    public void dropAction() {
        if (Arrays.stream( model.getThisFigure().getBlocks()).noneMatch(e -> e.getRow() == 19
                || model.getField()[e.getRow() + 1][e.getCol()] != null)) {
            model.getThisFigure().moveDrop();

        }
    }
    public void changeAction() {
        model.getThisFigure().moveChange();
        if (Arrays.stream( model.getThisFigure().getState()).noneMatch(e -> e.getCol() > 9 || e.getCol() < 0
                || e.getRow() < 0 || model.getField()[e.getRow()][e.getCol()] != null)) {
            model.getThisFigure().setBlocks( model.getThisFigure().getState());
        }
    }
    public boolean canDrop() {
        return Arrays.stream( model.getThisFigure().getBlocks()).noneMatch(e -> e.getRow() == 19
                || model.getField()[e.getRow() + 1][e.getCol()] != null);
    }


}
