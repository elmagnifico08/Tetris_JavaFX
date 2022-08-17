package com.Vysotskiy.tetris;

import lombok.Getter;

public class Game {
    @Getter
    View view = new View();

    public void start() {
        view.eventHandler();
        Thread game = new Thread(() -> {
            view.drawStrokeScoreLevel(view.getController().getModel().getGoal(), view.getController().getModel().getThisLevel());
            while (!view.getController().getModel().isLost()) {
                try {
                    Thread.sleep(view.getController().getModel().getLEVELS()[view.getController().getModel().getThisLevel()]);
                    if (view.getController().canDrop()) {
                        canMove(view.getController().getModel(), view);
                    } else {
                        canNotMove(view.getController().getModel(), view);
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
