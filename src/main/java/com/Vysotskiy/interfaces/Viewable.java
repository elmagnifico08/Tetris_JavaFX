package com.Vysotskiy.interfaces;

import com.Vysotskiy.figures.Block;
import com.Vysotskiy.figures.Figure;

public interface Viewable {
    void drawField(Block[][] field, Figure thisFigure, boolean lost);

    void drawScoreAndLevel(int point, int thisLevel);
}
