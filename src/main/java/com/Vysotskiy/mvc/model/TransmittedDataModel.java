package com.Vysotskiy.mvc.model;

import com.Vysotskiy.figures.Block;

public interface TransmittedDataModel {

    Block[][] getField();

    boolean isGameOver();

    long[] getLEVELS();

    int getGoal();

    int getThisLevel();

}
