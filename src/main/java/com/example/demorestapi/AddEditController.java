package com.example.demorestapi;

import com.example.demorestapi.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
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
    protected void onNewTaskButtonClick(){
        /*For testing purposes, should */
        HashMap<String, String> mapToJSON = new HashMap<>();
        // Add keys and values (Country, City)
        mapToJSON.put("name", "Hashmap");
        mapToJSON.put("description", "Dela");
        //sender.newtask(response.newJson(mapToJSON));
    }
    @FXML
    protected void cancel(){
        App.state = App.State.NONE;
        App.stage.setScene(App.tasks);
    }

    @FXML
    protected void submit() {
        //TODO CALL API
        if(App.state == App.State.ADD){
            TaskData taskData = new TaskData(description.getText(), name.getText());
            Utils.addTask(taskData);
            App.state = App.State.NONE;
        }

        else{ //EDIT
            for(Node node : ((VBox)App.tasks.lookup("#scroller")).getChildren()){
                if(node == App.tmpEdit){
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