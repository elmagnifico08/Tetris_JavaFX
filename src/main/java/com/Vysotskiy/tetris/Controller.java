package com.Vysotskiy.tetris;

import javafx.scene.input.KeyCode;
import lombok.Getter;

import java.util.Arrays;

public class Controller {
    @Getter
    Model model = new Model();
    View view = new View();
    public void eventHandler() {
        view.canvas.setOnKeyPressed(e -> {
            KeyCode key = e.getCode();
            if (key.equals(KeyCode.UP))
                changeAction();
            if (key.equals(KeyCode.DOWN))
                dropAction();
            if (key.equals(KeyCode.LEFT))
                leftAction();
            if (key.equals(KeyCode.RIGHT))
                rightAction();
            view.draw(model.getField(), model.getThisFigure(), model.isLost());
        });
    }

    private void rightAction() {
        if (Arrays.stream(model.getThisFigure().getBlocks()).noneMatch(e -> e.getCol() == 9
                || model.getField()[e.getRow()][e.getCol() + 1] != null)) {
            model.getThisFigure().moveRight();
        }
    }

    private void leftAction() {
        if (Arrays.stream(model.getThisFigure().getBlocks()).noneMatch(e -> e.getCol() == 0
                || model.getField()[e.getRow()][e.getCol() - 1] != null)) {
            model.getThisFigure().moveLeft();

        }
    }

    private void dropAction() {
        if (Arrays.stream( model.getThisFigure().getBlocks()).noneMatch(e -> e.getRow() == 19
                || model.getField()[e.getRow() + 1][e.getCol()] != null)) {
            model.getThisFigure().moveDrop();

        }
    }
    private void changeAction() {
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
