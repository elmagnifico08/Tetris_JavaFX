package com.Vysotskiy.tetris;

import lombok.Getter;

public class Game {
    @Getter
    Controller controller = new Controller();

    public void start() {
        controller.eventHandler();
        Thread game = new Thread(() -> {
            controller.view.drawStrokeScoreLevel(controller.getModel().getGoal(), controller.getModel().getThisLevel());
            while (!controller.getModel().isLost()) {
                try {
                    Thread.sleep(controller.getModel().getLEVELS()[controller.getModel().getThisLevel()]);
                    if (controller.canDrop()) {
                        canMove(controller.getModel(), controller.view);
                    } else {
                        canNotMove(controller.getModel(), controller.view);
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
