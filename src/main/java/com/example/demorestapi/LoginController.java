package com.example.demorestapi;

import com.jsonpackaging.Jsonoperation;
import com.sender.Sender;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    protected TextField name;

    @FXML
    protected PasswordField pass;

    @FXML
    protected void login(){
        String sendData = name.getText() + "." + pass.getText();

        /*Sender sender = new Sender();
        Jsonoperation operator = new Jsonoperation(sender.parseJSON());//, callback);
        sender.newtask(sendData,operator.newJson());*/

        App.stage.setScene(App.tasks);
    }

}