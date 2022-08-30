package com.Vysotskiy.mvc.model.interfacesModel;

import com.Vysotskiy.figures.Block;

public interface TransmittedDataModel {

    Block[][] getField();

    boolean isGameOver();

    long[] getSPEED_LEVELS();

    int getGoal();

    int getThisLevel();

}
