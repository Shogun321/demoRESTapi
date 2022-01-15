package com.example.demorestapi;

import com.example.demorestapi.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class TasksController {

    @FXML
    protected ImageView add;

    @FXML
    protected ImageView del;

    @FXML
    protected ImageView edit;

    @FXML
    protected void addTask() {
        App.state = App.State.ADD;
        App.stage.setScene(App.addEdit);
    }

    public void editTask(){
        if(App.state != App.State.EDIT){
            App.state = App.State.EDIT;
            toggleVisibility(false, Utils.ID_TRASH);
            toggleVisibility(true, Utils.ID_EDIT);
        }

        else{
            App.state = App.State.NONE;
            toggleVisibility(false, Utils.ID_EDIT);
        }
    }

    private void toggleVisibility(boolean value, String ID){
        VBox parent = (VBox)App.tasks.lookup("#scroller");

        for(Node node : parent.getChildren()){
            VBox task = (VBox)node;
            for(Node taskNode : task.getChildren())
                if(taskNode.getId().equals(ID))
                    taskNode.setVisible(value);
        }
    }

    @FXML
    protected void deleteTask(){
        if(App.state != App.State.REMOVE){
            App.state = App.State.REMOVE;
            toggleVisibility(false, Utils.ID_EDIT);
            toggleVisibility(true, Utils.ID_TRASH);
        }

        else{
            App.state = App.State.NONE;
            toggleVisibility(false, Utils.ID_TRASH);
        }
    }

    @FXML
    protected void logout(){
        if(App.state == App.State.REMOVE)
            toggleVisibility(false, Utils.ID_TRASH);
        else toggleVisibility(false, Utils.ID_EDIT);

        App.state = App.State.NONE;
        App.stage.setScene(App.login);
        App.sender.setLogincredentials("");
    }
}