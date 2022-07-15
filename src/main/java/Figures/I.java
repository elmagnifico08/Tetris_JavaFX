package Figures;

import com.example.tetris_javafx.Block;
import com.example.tetris_javafx.Figure;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static java.awt.Color.*;

public class I extends Figure {
    int num = 1;
    String color = "gold";
    public I() {
        blocks[0] = new Block(0, 4);
        blocks[1] = new Block(0, 3);
        blocks[2] = new Block(0, 5);
        blocks[3] = new Block(0, 6);


    }

    public String getColor() {
        return color;
    }

    @Override
    public void moveChange() {
         state = new Block[4];
        num++;
        switch (num) {
            case 1 -> {
                state[0] = blocks[0];
                state[1] = new Block(blocks[0].getRow(),blocks[0].getCol()-1);
                state[2] = new Block(blocks[0].getRow(),blocks[0].getCol()+1);
                state[3] = new Block(blocks[0].getRow(),blocks[0].getCol()+2);
                break;
            }
            case 2 -> {
                state[0] = blocks[0];
                state[1] = new Block(blocks[0].getRow()-1,blocks[0].getCol());
                state[2] = new Block(blocks[0].getRow()+1,blocks[0].getCol());
                state[3] = new Block(blocks[0].getRow()+2,blocks[0].getCol());
                break;
            }
        }
        if (num>1)num=0;
    }
}
