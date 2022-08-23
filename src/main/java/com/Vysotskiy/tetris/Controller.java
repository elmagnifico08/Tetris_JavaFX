package com.Vysotskiy.tetris;

import com.Vysotskiy.interfaces.Controllable;
import javafx.scene.input.KeyCode;

import java.util.Arrays;

public class Controller implements Controllable {

    @Override
    public void eventHandler(Model model,View view) {
        view.canvas.setOnKeyPressed(e -> {
            KeyCode key = e.getCode();
            if (key.equals(KeyCode.UP))
                changeAction(model);
            if (key.equals(KeyCode.DOWN))
                dropAction(model);
            if (key.equals(KeyCode.LEFT))
                leftAction(model);
            if (key.equals(KeyCode.RIGHT))
                rightAction(model);
            view.drawField(model.getField(), model.getThisFigure(), model.isLost());
        });
    }


    @Override
    public void rightAction(Model model) {
        if (Arrays.stream(model.getThisFigure().getBlocks()).noneMatch(e -> e.getCol() == model.getCOL()-1
                || model.getField()[e.getRow()][e.getCol() + 1] != null)) {
            model.getThisFigure().moveRight();
        }
    }

    @Override
    public void leftAction(Model model) {
        if (Arrays.stream(model.getThisFigure().getBlocks()).noneMatch(e -> e.getCol() == 0
                || model.getField()[e.getRow()][e.getCol() - 1] != null)) {
            model.getThisFigure().moveLeft();

        }
    }

    @Override
    public void dropAction(Model model) {
        if (Arrays.stream(model.getThisFigure().getBlocks()).noneMatch(e -> e.getRow() == model.getROW()-1
                || model.getField()[e.getRow() + 1][e.getCol()] != null)) {
            model.getThisFigure().moveDrop();

        }
    }

    @Override
    public void changeAction(Model model) {
        model.getThisFigure().moveChange();
        if (Arrays.stream(model.getThisFigure().getState()).noneMatch(e -> e.getCol() > 9 || e.getCol() < 0
                || e.getRow() < 0 || model.getField()[e.getRow()][e.getCol()] != null)) {
            model.getThisFigure().setBlocks(model.getThisFigure().getState());
        }
    }



}
