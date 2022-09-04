package com.Vysotskiy.mvc.model.interfacesModel;

public interface TransmittedDataModel {

    <T> T[][] getField();

    boolean isGameOver();

    long[] getSPEED_LEVELS();

    int getGoal();

    int getThisLevel();

}
