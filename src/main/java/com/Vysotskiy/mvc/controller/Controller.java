package com.Vysotskiy.mvc.controller;

import com.Vysotskiy.figures.Block;
import com.Vysotskiy.figures.Figure;
import com.Vysotskiy.mvc.controller.interfacesController.TransmittingData;
import com.Vysotskiy.mvc.model.interfacesModel.FigureableData;
import com.Vysotskiy.mvc.model.interfacesModel.MoveGameField;
import com.Vysotskiy.mvc.controller.interfacesController.ReplaceablePositionFigure;
import com.Vysotskiy.mvc.model.interfacesModel.TransmittedDataModel;

import java.util.Arrays;

import javafx.scene.paint.Color;

public class Controller implements ReplaceablePositionFigure, MoveGameField, TransmittingData {

    ReplaceablePositionFigure replaceablePositionFigure;
    MoveGameField moveGameField;
    FigureableData figureableData;
    TransmittedDataModel transmittedDataModel;

    public Controller( MoveGameField moveGameField,
                      FigureableData figureableData, TransmittedDataModel transmittedDataModel) {
        this.replaceablePositionFigure = replaceablePositionFigure;
        this.moveGameField = moveGameField;
        this.figureableData = figureableData;
        this.transmittedDataModel = transmittedDataModel;
    }


    @Override
    public void rightAction() {
        if (Arrays.stream(figureableData.getThisFigure().getBlocks()).noneMatch(e -> e.getCol()== transmittedDataModel.getCol()-1
                || transmittedDataModel.getField()[e.getRow()][e.getCol() + 1]!=null)) {
            figureableData.getThisFigure().moveRight();
        }

    }

    @Override
    public void leftAction() {
        if (Arrays.stream(figureableData.getThisFigure().getBlocks()).noneMatch(e -> e.getCol()==0
                || transmittedDataModel.getField()[e.getRow()][e.getCol() - 1]!=null)) {
            figureableData.getThisFigure().moveLeft();
        }
    }

    @Override
    public void dropAction() {
        if (checkingFigureCanDrop()) {
            figureableData.getThisFigure().moveDrop();
        }

    }

    @Override
    public void changeAction() {
        figureableData.getThisFigure().moveChange();
        if (Arrays.stream(figureableData.getThisFigure().getState()).noneMatch(e -> e.getCol() > 9 || e.getCol() < 0
                || e.getRow() < 0 || transmittedDataModel.getField()[e.getRow()][e.getCol()]!=null)) {
            figureableData.getThisFigure().setBlocks(figureableData.getThisFigure().getState());
        }
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
