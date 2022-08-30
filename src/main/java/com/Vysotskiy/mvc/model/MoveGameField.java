package com.Vysotskiy.mvc.model;

import com.Vysotskiy.figures.Block;

public interface MoveGameField {
    Block[][] getField();

    void figureFell();

    void figureMoveDrop();

    boolean figureCanDrop();


}
