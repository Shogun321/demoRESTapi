package com.example.demorestapi;

import com.example.demorestapi.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginController {

    @FXML
    protected TextField name;

    @FXML
    protected PasswordField pass;

    @FXML
    protected void login(){
        String sendData = name.getText() + "." + pass.getText();
        //App.sender.login(sendData);
        App.sender.setLogincredentials(sendData);
        int error=App.sender.login(sendData);
        if (error==3){
            System.out.println("Bad credentials");
        }else {
            Pane holderPane = (Pane) App.tasks.lookup("#loginPane");
            Label username = (Label) holderPane.lookup("#username");
            username.setText(name.getText());

            App.stage.setScene(App.tasks);
            //this makes error when bad credentials are passed
            JSONArray backData = App.sender.parseJSON();
            for (int i = 0; i < backData.length(); i++) {
                JSONObject obj = backData.getJSONObject(i);

                String name = obj.getString("name"),
                        description = obj.getString("description") +
                                "\nDATUM: " + obj.getString("task_date");

                TaskData taskData = new TaskData(name, description);
                Utils.addTask(taskData);
            }
            HashMap<String, String> mapToJSON = new HashMap<>();
            mapToJSON.put("name", "Novi task.");
            mapToJSON.put("description", "radi ovaj task");
            App.sender.newtask(App.jsonoperator.newJson(mapToJSON));
        }
    }
}