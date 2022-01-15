package com.example.demorestapi.utils;

import com.example.demorestapi.App;
import com.example.demorestapi.TaskData;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public final class Utils {

    public static final String ID_TRASH = "56", ID_EDIT = "8tr4", ID_NAME = "jij", ID_DESCRIPTION = "iu", ID_TASK = "uoioj";

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
        Label ID = new Label(taskData.id);
        ID.setId(ID_TASK);

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
        delImg.setOnMouseClicked(mouseEvent -> editRemoveClick(newTask));
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
        editImg.setOnMouseClicked(mouseEvent -> editRemoveClick(newTask));

        newTask.getChildren().add(imgHolder);
        newTask.getChildren().add(ID);
        newTask.getChildren().add(lName);
        newTask.getChildren().add(lDescription);

        taskHolder.getChildren().add(newTask);
    }

    public static void alert(String msg){
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setTitle(msg);
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.show();
    }

    private static void startEditMode(VBox taskHolder){
        App.tmpEdit = taskHolder;
        TextField name = (TextField)App.addEdit.lookup("#name");
        TextArea description = (TextArea)App.addEdit.lookup("#description");
        Label id = (Label)App.addEdit.lookup("#taskID");

        Label inName = (Label)taskHolder.lookup("#" + ID_NAME),
                inDescription = (Label)taskHolder.lookup("#" + ID_DESCRIPTION),
                inId = (Label)taskHolder.lookup("#" + ID_TASK);

        name.setText(inName.getText());
        description.setText(inDescription.getText());
        id.setText(inId.getText());

        App.state = App.State.EDIT;
        App.stage.setScene(App.addEdit);
    }

    private static void editRemoveClick(VBox parent){
        VBox controller = (VBox)App.tasks.lookup("#scroller");

        if(App.state == App.State.EDIT)
            startEditMode(parent);

        else if(App.state == App.State.REMOVE) {
            String id = ((Label)parent.lookup("#" + ID_TASK)).getText();

            HashMap<String, String> mapToJSON = new HashMap<>();
            mapToJSON.put("task_id", id);
            App.sender.delete(App.jsonoperator.newJsonObject(mapToJSON));

            controller.getChildren().remove(parent);
        }
    }

}
