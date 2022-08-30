package com.Vysotskiy.mvc.controller;

import com.Vysotskiy.figures.Figure;

public interface ControllingMoveFigure {
    Figure changeAction();

    Figure dropAction();

    Figure leftAction();

    Figure rightAction();


}
