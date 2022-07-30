package com.example.tetris_javafx;
import javafx.scene.input.KeyCode;
public class Controller {
    Tetris tetris = new Tetris();
    View view = new View();
    public void action(){
        view.canvas.setOnKeyPressed(e -> {
            KeyCode key = e.getCode();
            if (key.equals(KeyCode.UP)) tetris.getChangeAction();
            if (key.equals(KeyCode.DOWN)) tetris.getDropAction();
            if (key.equals(KeyCode.LEFT)) tetris.getLeftAction();
            if (key.equals(KeyCode.RIGHT)) tetris.getRightAction();
        });
    }

}
