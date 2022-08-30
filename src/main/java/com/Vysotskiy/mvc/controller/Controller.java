package com.Vysotskiy.mvc.controller;

import com.Vysotskiy.figures.Block;
import com.Vysotskiy.figures.Figure;
import com.Vysotskiy.mvc.model.Model;

import javafx.scene.paint.Color;

public class Controller implements ControllingMoveFigure, ControllingField, TramsmittingData {
    Model model;

    public Controller(Model model) {
        this.model = model;
    }

    @Override
    public void rightAction() {
        model.rightAction();
    }

    @Override
    public void leftAction() {
        model.leftAction();
    }

    @Override
    public void dropAction() {
        model.dropAction();
    }

    @Override
    public void changeAction() {
        model.changeAction();
    }


    @Override
    public void figureFellDown() {
        model.figureFell();
    }

    @Override
    public void figureCanFallDown() {
        model.figureMoveDrop();
    }

    @Override
    public boolean possibleFallFigure() {
        return model.figureCanDrop();
    }

    @Override
    public Color getFigureColor() {
        return model.getColor();
    }

    @Override
    public Figure getThisFigure() {
        return model.getThisFigure();
    }

    @Override
    public Block[][] getField() {
        return model.getField();
    }

    @Override
    public boolean isGameOver() {
        return model.isGameOver();
    }

    @Override
    public long[] getLEVELS() {
        return model.getLEVELS();
    }

    @Override
    public int getGoal() {
        return model.getGoal();
    }

    @Override
    public int getThisLevel() {
        return model.getThisLevel();
    }
}
