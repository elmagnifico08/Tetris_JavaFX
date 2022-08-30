package com.Vysotskiy.mvc.controller;

import com.Vysotskiy.figures.Block;
import com.Vysotskiy.figures.Figure;

import javafx.scene.paint.Color;

public interface TramsmittingDataModel {

    Color getFigureColor();

    Figure getThisFigure();

    Block[][] getField();

    boolean isGameOver();

    long[] getLEVELS();

    int getGoal();

    int getThisLevel();


}
