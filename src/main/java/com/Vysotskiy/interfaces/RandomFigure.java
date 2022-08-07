package com.Vysotskiy.interfaces;

import com.Vysotskiy.figures.*;

public interface RandomFigure {
    default Figure randomFigure() {
        int num = (int) (Math.random() * 7);
        Figure f = new T();
        switch (num) {
            case 0 -> f = new T();
            case 1 -> f = new I();
            case 2 -> f = new J();
            case 3 -> f = new L();
            case 4 -> f = new NotZ();
            case 5 -> f = new O();
            case 6 -> f = new Z();
        }
        return f;
    }
}
