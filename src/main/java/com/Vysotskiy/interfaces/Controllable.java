package com.Vysotskiy.interfaces;

import com.Vysotskiy.tetris.Model;

import java.util.Arrays;

public interface Controllable {

    default void rightAction(Model model) {
        if (Arrays.stream(model.getThisFigure().getBlocks()).noneMatch(e -> e.getCol() == 9
                || model.getField()[e.getRow()][e.getCol() + 1] != null)) {
            model.getThisFigure().moveRight();
        }
    }

    default void leftAction(Model model) {
        if (Arrays.stream(model.getThisFigure().getBlocks()).noneMatch(e -> e.getCol() == 0
                || model.getField()[e.getRow()][e.getCol() - 1] != null)) {
            model.getThisFigure().moveLeft();

        }
    }

    default void dropAction(Model model) {
        if (Arrays.stream( model.getThisFigure().getBlocks()).noneMatch(e -> e.getRow() == 19
                || model.getField()[e.getRow() + 1][e.getCol()] != null)) {
            model.getThisFigure().moveDrop();

        }
    }
    default void changeAction(Model model) {
        model.getThisFigure().moveChange();
        if (Arrays.stream( model.getThisFigure().getState()).noneMatch(e -> e.getCol() > 9 || e.getCol() < 0
                || e.getRow() < 0 || model.getField()[e.getRow()][e.getCol()] != null)) {
            model.getThisFigure().setBlocks( model.getThisFigure().getState());
        }
    }
    default boolean canDrop(Model model) {
        return Arrays.stream(model.getThisFigure().getBlocks()).noneMatch(e -> e.getRow() == 19
                || model.getField()[e.getRow() + 1][e.getCol()] != null);
    }
}
