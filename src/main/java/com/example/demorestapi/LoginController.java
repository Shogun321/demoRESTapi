package com.example.demorestapi;

import com.example.demorestapi.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

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
            Utils.alert("Bad credentials");
            System.out.println("Bad credentials");
        } else {
            Pane holderPane = (Pane) App.tasks.lookup("#loginPane");
            Label username = (Label) holderPane.lookup("#username");
            username.setText(name.getText());

            App.stage.setScene(App.tasks);
            App.refresh();
        }
    }
}