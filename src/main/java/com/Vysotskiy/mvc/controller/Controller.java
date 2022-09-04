package com.Vysotskiy.mvc.controller;

import com.Vysotskiy.figures.Block;
import com.Vysotskiy.figures.Figure;
import com.Vysotskiy.mvc.controller.interfacesController.TramsmittingData;
import com.Vysotskiy.mvc.model.interfacesModel.FigureableData;
import com.Vysotskiy.mvc.model.interfacesModel.MoveGameField;
import com.Vysotskiy.mvc.model.interfacesModel.ReplaceablePositionFigure;
import com.Vysotskiy.mvc.model.interfacesModel.TransmittedDataModel;

import javafx.scene.paint.Color;

public class Controller implements ReplaceablePositionFigure, MoveGameField, TramsmittingData {

    ReplaceablePositionFigure replaceablePositionFigure;
    MoveGameField moveGameField;
    FigureableData figureableData;
    TransmittedDataModel transmittedDataModel;

    public Controller(ReplaceablePositionFigure replaceablePositionFigure, MoveGameField moveGameField,
                      FigureableData figureableData, TransmittedDataModel transmittedDataModel) {
        this.replaceablePositionFigure = replaceablePositionFigure;
        this.moveGameField = moveGameField;
        this.figureableData = figureableData;
        this.transmittedDataModel = transmittedDataModel;
    }

    @Override
    public void rightAction() {
        replaceablePositionFigure.rightAction();
    }

    @Override
    public void leftAction() {
        replaceablePositionFigure.leftAction();
    }

    @Override
    public void dropAction() {
        replaceablePositionFigure.dropAction();
    }

    @Override
    public void changeAction() {
        replaceablePositionFigure.changeAction();
    }


    @Override
    public Color getFigureColor() {
        return figureableData.getColor();
    }

    @Override
    public Figure getThisFigure() {
        return  figureableData.getThisFigure();
    }

    @Override
    public Block[][] getField() {
        return transmittedDataModel.getField();
    }


    @Override
    public boolean isGameOver() {
        return transmittedDataModel.isGameOver();
    }

    @Override
    public long[] getLEVELS() {
        return transmittedDataModel.getSPEED_LEVELS();
    }

    @Override
    public int getGoal() {
        return transmittedDataModel.getGoal();
    }

    @Override
    public int getThisLevel() {
        return transmittedDataModel.getThisLevel();
    }

    @Override
    public void figureFell() {
        moveGameField.figureFell();
    }

    @Override
    public void figureMoveDrop() {
        moveGameField.figureMoveDrop();
    }

    @Override
    public boolean checkingFigureCanDrop() {
        return moveGameField.checkingFigureCanDrop();
    }
}
