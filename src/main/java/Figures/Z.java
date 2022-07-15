package Figures;

import com.example.tetris_javafx.Block;
import com.example.tetris_javafx.Figure;

public class Z extends Figure {
    String color = "red";
    int num = 1;
    public Z() {
        blocks[0] = new Block(1, 4);
        blocks[1] = new Block(0, 3);
        blocks[2] = new Block(0, 4);
        blocks[3] = new Block(1, 5);
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
                state[1] = new Block(blocks[0].getRow()-1,blocks[0].getCol()-1);
                state[2] = new Block(blocks[0].getRow()-1,blocks[0].getCol());
                state[3] = new Block(blocks[0].getRow(),blocks[0].getCol()+1);

                break;
            }
            case 2 -> {
                state[0] = blocks[0];
                state[1] = new Block(blocks[0].getRow()-1,blocks[0].getCol()+1);
                state[2] = new Block(blocks[0].getRow(),blocks[0].getCol()+1);
                state[3] = new Block(blocks[0].getRow()+1,blocks[0].getCol());

                break;
            }

        }

        if (num==2)num=0;

    }
}
