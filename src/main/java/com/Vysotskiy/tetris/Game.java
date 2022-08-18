package com.Vysotskiy.tetris;

public class Game extends Controller {

    public void start() {
        eventHandler();
        Thread game = new Thread(() -> {
            view.drawStrokeScoreLevel(model.getGoal(), model.getThisLevel());
            while (!model.isLost()) {
                try {
                    Thread.sleep(model.getLEVELS()[model.getThisLevel()]);
                    if (canDrop()) {
                        canMove(model, view);
                    } else {
                        canNotMove(model, view);
                    }
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        });
        game.start();
    }

    private void canMove(Model model, View view) {
        model.getThisFigure().moveDrop();
        view.draw(model.getField(), model.getThisFigure(), model.isLost());
    }

    private void canNotMove(Model model, View view) {
        model.addFigureToField();
        model.removeLine();
        view.drawStrokeScoreLevel(model.getGoal(), model.getThisLevel());
        model.setNextFigure(model.randomFigure());
        model.setThisFigure(model.getNextFigure());
        view.draw(model.getField(), model.getThisFigure(), model.isLost());
    }
}
