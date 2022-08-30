package com.Vysotskiy.mvc.model;

public interface Scoringable {
    boolean isGameOver();

    long[] getLEVELS();

    int getGoal();

    int getThisLevel();

}
