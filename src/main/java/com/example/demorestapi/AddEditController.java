package com.example.demorestapi;

import com.example.demorestapi.utils.Utils;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class AddEditController {

    @FXML
    protected TextField name;

    @FXML
    protected TextArea description;

    @FXML
    protected Button cancel;

    @FXML
    protected Button submit;

    @FXML
    protected Label taskID;

    @FXML
    protected void cancel(){
        App.state = App.State.NONE;
        App.stage.setScene(App.tasks);
    }

    @FXML
    protected void submit() {

        if(App.state == App.State.ADD){
            TaskData taskData = new TaskData(description.getText(), name.getText(), taskID.getText());
            Utils.addTask(taskData);
            App.state = App.State.NONE;

            HashMap<String, String> mapToJSON = new HashMap<>();
            mapToJSON.put("name", name.getText());
            mapToJSON.put("description", description.getText());
            App.sender.newtask(App.jsonoperator.newJsonObject(mapToJSON));
        }

        else{ //EDIT
            HashMap<String, String> mapToJSON = new HashMap<>();
            mapToJSON.put("task_id", taskID.getText());
            mapToJSON.put("name", name.getText());
            mapToJSON.put("description", description.getText());
            App.sender.edit(App.jsonoperator.newJsonObject(mapToJSON));

            ObservableList<Node> items = ((VBox)App.tasks.lookup("#scroller")).getChildren();
            for(int i = 0; i < items.size(); i++){
                Node node = items.get(i);

                if(node == App.tmpEdit){
                    App.tmpId = i;
                    VBox vbox = (VBox) node;

                    Label outName = (Label)vbox.lookup("#" + Utils.ID_NAME),
                            outDescription = (Label)vbox.lookup("#" + Utils.ID_DESCRIPTION);

                    outName.setText(name.getText());
                    outDescription.setText(description.getText());
                    break;
                }
            }
        }

        App.stage.setScene(App.tasks);
    }

}