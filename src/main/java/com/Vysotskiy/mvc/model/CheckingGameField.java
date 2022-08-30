package com.Vysotskiy.mvc.model;

import com.Vysotskiy.figures.Block;

public interface CheckingGameField {
    void figureFell();

    void figureMoveDrop();

    boolean figureCanDrop();

    Block[][] getField();


}
