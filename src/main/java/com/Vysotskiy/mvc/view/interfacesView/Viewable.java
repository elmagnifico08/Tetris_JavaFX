package com.Vysotskiy.mvc.view.interfacesView;

import javafx.scene.canvas.Canvas;

public interface Viewable {
    Canvas getCanvas();

    int getSIZE_WIDTH();

    int getSIZE_HIGH();

    void viewStartGame();
}
