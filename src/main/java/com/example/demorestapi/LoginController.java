package com.example.demorestapi;

import com.example.demorestapi.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.json.JSONArray;
import org.json.JSONObject;

public class LoginController {

    @FXML
    protected TextField name;

    @FXML
    protected PasswordField pass;

    @FXML
    protected void login(){
        String sendData = name.getText() + "." + pass.getText();
        //App.sender.login(sendData);

        Pane holderPane = (Pane)App.tasks.lookup("#loginPane");
        Label username = (Label)holderPane.lookup("#username");
        username.setText(name.getText());

        App.stage.setScene(App.tasks);

        JSONArray backData = App.sender.parseJSON();
        for(int i = 0; i < backData.length(); i++){
            JSONObject obj = backData.getJSONObject(i);

            String name = obj.getString("name"),
                    description = obj.getString("description") +
                            "\nDATUM: " + obj.getString("task_date");

            TaskData taskData = new TaskData(name, description);
            Utils.addTask(taskData);
        }


    }
}