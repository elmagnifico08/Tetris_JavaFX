package com.Vysotskiy.tetris;

import lombok.Getter;

public class Game {
    @Getter
    Model model = new Model();
    @Getter
    Controller controller = new Controller();
    @Getter
    View view = new View();

    public void start() {
        Thread game = new Thread(() -> {
            view.drawStrokeScoreLevel(model.getGoal(), model.getThisLevel(), view.gc);
            while (!model.isLost()) {
                try {
                    Thread.sleep(model.getLEVELS()[model.getThisLevel()]);
                    if (controller.canDrop(model.getThisFigure(), model.getField())) {
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
        view.draw(model.getField(), model.getThisFigure(), view.gc, model.isLost());
    }

    private void canNotMove(Model model , View view) {
        model.addFigureToField();
        model.removeLine();
        view.drawStrokeScoreLevel(model.getGoal(), model.getThisLevel(), view.gc);
        model.setNextFigure(model.randomFigure());
        model.setThisFigure(model.getNextFigure());
        view.draw(model.getField(), model.getThisFigure(), view.gc, model.isLost());
    }
}
