package com.Vysotskiy.mvc.model;

import com.Vysotskiy.figures.Figure;


public interface ReplaceablePositionFigure  {

    Figure rightAction();


    Figure leftAction();


    Figure dropAction();


    Figure changeAction();
}
