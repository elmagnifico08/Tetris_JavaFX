package com.Vysotskiy.tetrisGame;

import com.Vysotskiy.mvc.controller.Controller;
import com.Vysotskiy.mvc.controller.interfacesController.TramsmittingData;
import com.Vysotskiy.mvc.model.Model;
import com.Vysotskiy.mvc.model.interfacesModel.FigureableData;
import com.Vysotskiy.mvc.model.interfacesModel.MoveGameField;
import com.Vysotskiy.mvc.model.interfacesModel.ReplaceablePositionFigure;
import com.Vysotskiy.mvc.model.interfacesModel.TransmittedDataModel;
import com.Vysotskiy.mvc.view.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    Model model = new Model();
    ReplaceablePositionFigure replaceablePositionFigure = model;
    MoveGameField moveGameField = model;
    FigureableData figureableData = model;
    TransmittedDataModel transmittedDataModel = model;
    Controller controller = new Controller(replaceablePositionFigure, moveGameField, figureableData, transmittedDataModel);
    MoveGameField controllingChangeField = controller;
    ReplaceablePositionFigure controllingMoveFigure = controller;
    TramsmittingData tramsmittingData = controller;
    View view = new View(controllingMoveFigure,tramsmittingData,controllingChangeField);

    @Override
    public void start(Stage primaryStage) {
        FlowPane root = new FlowPane();
        view.getCanvas().setFocusTraversable(true);
        root.getChildren().add(view.getCanvas());
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, view.getSIZE_WIDTH(), view.getSIZE_HIGH(), Color.SKYBLUE);
        primaryStage.setTitle("I am T_E_T_R_I_S");
        primaryStage.setScene(scene);
        primaryStage.show();
        view.viewStartGame();
    }

    public static void main(String[] args) {
        launch();
    }

}


