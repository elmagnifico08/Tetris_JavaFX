package com.Vysotskiy.mvc.controller;

import com.Vysotskiy.figures.Block;
import com.Vysotskiy.figures.Figure;
import com.Vysotskiy.mvc.model.Model;

import javafx.scene.paint.Color;

public class Controller implements ControllingMoveFigure, ControllingField, TramsmittingDataModel {
    Model model;

    public Controller(Model model) {
        this.model = model;
    }

    @Override
    public Figure rightAction() {
        return model.rightAction();
    }

    @Override
    public Figure leftAction() {
        return model.leftAction();
    }

    @Override
    public Figure dropAction() {
        return model.dropAction();
    }

    @Override
    public Figure changeAction() {
        return model.changeAction();
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
