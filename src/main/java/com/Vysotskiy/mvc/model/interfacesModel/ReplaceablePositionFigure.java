package com.Vysotskiy.mvc.model.interfacesModel;

public interface ReplaceablePositionFigure  {

    <T> T rightAction();


    <T> T leftAction();


    <T> T dropAction();


    <T> T changeAction();
}
