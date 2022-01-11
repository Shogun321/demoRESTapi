package com.example.demorestapi.utils;

import com.example.demorestapi.App;
import com.example.demorestapi.TaskData;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public final class Utils {

    public static final String ID_TRASH = "56", ID_EDIT = "8tr4", ID_NAME = "jij", ID_DESCRIPTION = "iu";

    private Utils(){}

    public static void addTask(TaskData taskData) {
        VBox taskHolder = (VBox)App.tasks.lookup("#scroller");

        VBox newTask = new VBox();
        newTask.getStyleClass().add("task");
        newTask.setFillWidth(true);
        newTask.setMaxHeight(Control.USE_PREF_SIZE);
        newTask.setMinHeight(Control.USE_PREF_SIZE);
        newTask.setPrefHeight(240);

        Label lName = new Label(taskData.name);
        lName.setId(ID_NAME);
        Label lDescription = new Label(taskData.description);
        lDescription.setId(ID_DESCRIPTION);

        ImageView delImg = new ImageView(App.trashImg);
        delImg.setFitWidth(38);
        delImg.setFitHeight(38);
        StackPane imgHolder = new StackPane ();
        imgHolder.setId(ID_TRASH);
        imgHolder.setMaxWidth(Control.USE_COMPUTED_SIZE);
        imgHolder.getChildren().add(delImg);
        imgHolder.setAlignment(delImg, Pos.CENTER_LEFT);
        imgHolder.setVisible(false);
        imgHolder.managedProperty().bind(newTask.visibleProperty());
        final StackPane tmp = imgHolder;
        delImg.setOnMouseClicked(mouseEvent -> editRemoveClick(tmp));
        newTask.getChildren().add(imgHolder);

        ImageView editImg = new ImageView(App.editImg);
        editImg.setFitWidth(38);
        editImg.setFitHeight(38);
        imgHolder = new StackPane ();
        imgHolder.setId(ID_EDIT);
        imgHolder.setMaxWidth(Control.USE_COMPUTED_SIZE);
        imgHolder.getChildren().add(editImg);
        imgHolder.setAlignment(editImg, Pos.CENTER_LEFT);
        imgHolder.setVisible(false);
        imgHolder.managedProperty().bind(newTask.visibleProperty());
        final StackPane tmp2 = imgHolder;
        editImg.setOnMouseClicked(mouseEvent -> editRemoveClick(tmp2));

        newTask.getChildren().add(imgHolder);
        newTask.getChildren().add(lName);
        newTask.getChildren().add(lDescription);

        taskHolder.getChildren().add(newTask);
    }

    private static void startEditMode(VBox taskHolder){
        App.tmpEdit = taskHolder;
        TextField name = (TextField)App.addEdit.lookup("#name");
        TextArea description = (TextArea)App.addEdit.lookup("#description");

        Label inName = (Label)taskHolder.lookup("#" + ID_NAME),
                inDescription = (Label)taskHolder.lookup("#" + ID_DESCRIPTION);

        name.setText(inName.getText());
        description.setText(inDescription.getText());

        App.state = App.State.EDIT;
        App.stage.setScene(App.addEdit);
    }

    private static void editRemoveClick(StackPane node){
        VBox parent = (VBox)App.tasks.lookup("#scroller");

        if(App.state == App.State.EDIT)
            startEditMode((VBox)node.getParent());

        else if(App.state == App.State.REMOVE) {
            parent.getChildren().remove(node.getParent());
            //TODO removeRequest
        }
    }

}
