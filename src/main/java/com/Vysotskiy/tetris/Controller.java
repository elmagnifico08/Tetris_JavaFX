package com.Vysotskiy.tetris;

import com.Vysotskiy.interfaces.Controllable;
import javafx.scene.input.KeyCode;

public class Controller implements Controllable {
    Model model = new Model();
    View view = new View();
    protected void eventHandler() {
        view.canvas.setOnKeyPressed(e -> {
            KeyCode key = e.getCode();
            if (key.equals(KeyCode.UP))
                changeAction(model);
            if (key.equals(KeyCode.DOWN))
                dropAction(model);
            if (key.equals(KeyCode.LEFT))
                leftAction(model);
            if (key.equals(KeyCode.RIGHT))
                rightAction(model);
            view.draw(model.getField(), model.getThisFigure(), model.isLost());
        });
    }

}
