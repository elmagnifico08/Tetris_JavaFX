package com.example.interface_javafx;

import com.example.figures_javafx.*;

public interface RandomFigure {
    default Figure randomFigure() {
        Figure f = new T();
        int num = (int) (Math.random() * 7);
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
