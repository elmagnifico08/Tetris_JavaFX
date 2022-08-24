package com.Vysotskiy.tetris;

import com.Vysotskiy.interfaces.Controllable;

public class Controller implements Controllable {


    @Override
    public void rightAction(Model model) {
        model.rightAction();
    }

    @Override
    public void leftAction(Model model) {
        model.leftAction();
    }

    @Override
    public void dropAction(Model model) {
        model.dropAction();
    }

    @Override
    public void changeAction(Model model) {
        model.changeAction();
    }


}
