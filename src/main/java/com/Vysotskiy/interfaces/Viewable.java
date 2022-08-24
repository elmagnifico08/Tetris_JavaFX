package com.Vysotskiy.interfaces;

import com.Vysotskiy.tetris.Model;

import javafx.scene.canvas.Canvas;

public interface Viewable {
    void drawField();

    void drawScoreAndLevel();

    void eventHandler();

    Canvas getCanvas();

    int getSIZE_WIDTH();

    int getSIZE_HIGH();

    Model getModel();

}
