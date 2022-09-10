package com.Vysotskiy.tetrisGame;

import com.Vysotskiy.mvc.controller.Controller;
import com.Vysotskiy.mvc.controller.interfacesController.TransmittingData;
import com.Vysotskiy.mvc.model.Model;
import com.Vysotskiy.mvc.model.interfacesModel.FigureableData;
import com.Vysotskiy.mvc.model.interfacesModel.MoveGameField;
import com.Vysotskiy.mvc.controller.interfacesController.ReplaceablePositionFigure;
import com.Vysotskiy.mvc.model.interfacesModel.TransmittedDataModel;
import com.Vysotskiy.mvc.view.View;
import com.Vysotskiy.mvc.view.interfacesView.Viewable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    Model model = new Model();
    MoveGameField moveGameField = model;
    FigureableData figureableData = model;
    TransmittedDataModel transmittedDataModel = model;
    Controller controller = new Controller(moveGameField, figureableData, transmittedDataModel);
    MoveGameField controllingChangeField = controller;
    ReplaceablePositionFigure controllingMoveFigure = controller;
    TransmittingData transmittingData = controller;
    View view = new View(controllingMoveFigure, transmittingData,controllingChangeField);
    Viewable viewable = view;

    @Override
    public void start(Stage primaryStage) {
        FlowPane root = new FlowPane();
        viewable.getCanvas().setFocusTraversable(true);
        root.getChildren().add(viewable.getCanvas());
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, viewable.getSIZE_WIDTH(), viewable.getSIZE_HIGH(), Color.SKYBLUE);
        primaryStage.setTitle("I am T_E_T_R_I_S");
        primaryStage.setScene(scene);
        primaryStage.show();
        viewable.viewStartGame();
    }

    public static void main(String[] args) {
        launch();
    }

}


