package com.Vysotskiy.mvc.controller.interfacesController;

import com.Vysotskiy.figures.Figure;

public interface TramsmittingData {

    <T> T getFigureColor();

    Figure getThisFigure();

    <T> T[][] getField();

    boolean isGameOver();

    long[] getLEVELS();

    int getGoal();

    int getThisLevel();


}
