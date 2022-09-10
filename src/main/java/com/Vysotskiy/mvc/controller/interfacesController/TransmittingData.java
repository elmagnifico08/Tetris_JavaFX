package com.Vysotskiy.mvc.controller.interfacesController;

import com.Vysotskiy.figures.Figure;

public interface TransmittingData {

    <T> T getFigureColor();

    Figure getThisFigure();


    <T> T[][] getField();

     boolean isGameOver();

    long[] getLEVELS();

    int getGoal();

    int getThisLevel();

}
