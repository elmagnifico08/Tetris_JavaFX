package com.Vysotskiy.tetris;

public class GameLogic {
    Controller controller = new Controller();
    View view = new View();
    Model model = new Model();

    public void startGame() {
        controller.eventHandler(model,view);
        Thread game = new Thread(() -> {
            view.drawScoreAndLevel(model.getGoal(), model.getThisLevel());
            while (!model.isLost()) {
                try {
                    Thread.sleep(model.getLEVELS()[model.getThisLevel()]);
                    if (model.figureCanDrop()) {
                        canMove(model, view);
                    } else {
                        canNotMove(model, view);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interrupted exception"+e);
                }
            }
        });
        game.start();
    }
    private void canMove(Model model, View view) {
        model.figureMoveDrop();
        view.drawField(model.getField(), model.getThisFigure(), model.isLost());
    }

    private void canNotMove(Model model, View view) {
        model.figureFell();
        view.drawScoreAndLevel(model.getGoal(), model.getThisLevel());
        view.drawField(model.getField(), model.getThisFigure(), model.isLost());
    }


}
