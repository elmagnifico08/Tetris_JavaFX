package Figures;

import com.example.tetris_javafx.Block;
import com.example.tetris_javafx.Figure;

public class O extends Figure {
    String color = "white";
    public O() {
        blocks[0] = new Block(0, 4);
        blocks[1] = new Block(0, 5);
        blocks[2] = new Block(1, 4);
        blocks[3] = new Block(1, 5);
    }
    @Override
    public void moveChange() {
        state = blocks;

    }
    public String getColor() {
        return color;
    }
}
