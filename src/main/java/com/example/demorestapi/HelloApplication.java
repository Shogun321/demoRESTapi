package com.example.demorestapi;

import com.jsonpackaging.Jsonoperation;
import com.sender.Sender;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        Sender sender = new Sender();
        Jsonoperation operator = new Jsonoperation(sender.parseJSON());
        sender.newtask("bastrijan.111",operator.newJson());
    }

    public static void main(String[] args) {
        launch();
    }
}