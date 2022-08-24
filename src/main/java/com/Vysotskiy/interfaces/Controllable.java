package com.Vysotskiy.interfaces;

import com.Vysotskiy.tetris.Model;

public interface Controllable {
    void changeAction(Model model);

    void dropAction(Model model);

    void leftAction(Model model);

    void rightAction(Model model);


}
