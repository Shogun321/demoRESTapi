package com.example.demorestapi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;

public class App extends Application {

    public static final int WIDTH = 480, HEIGHT = 680;

    public enum State{
        EDIT, ADD, REMOVE, NONE
    }

    public static State state = State.NONE;
    public static Stage stage;
    public static Scene login, tasks, addEdit;

    public static Image trashImg, editImg;
    public static VBox tmpEdit;

    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;

        initScenes();

        stage.setTitle("iTasker Desktop");
        stage.getIcons().add(new Image("file:res/ico.png"));
        stage.setResizable(false);
        stage.setScene(login);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void initScenes() throws IOException {
        FXMLLoader fxmlLogin = new FXMLLoader(App.class.getResource("login.fxml")),
                fxmlTasks = new FXMLLoader(App.class.getResource("tasks.fxml")),
                fxmlAddEdit = new FXMLLoader(App.class.getResource("addEdit.fxml"));

        login = new Scene(fxmlLogin.load(), WIDTH, HEIGHT);
        tasks = new Scene(fxmlTasks.load(), WIDTH, HEIGHT);
        addEdit = new Scene(fxmlAddEdit.load(), WIDTH, HEIGHT);

        ImageView versionIco = (ImageView) login.lookup("#versionIco");
        versionIco.setImage(new Image(new FileInputStream("res/log_bot_ico.png")));

        ImageView add = (ImageView) tasks.lookup("#add");
        add.setImage(new Image(new FileInputStream("res/_iadd.png")));

        ImageView rm = (ImageView) tasks.lookup("#del");
        rm.setImage(new Image(new FileInputStream("res/_itrash.png")));

        ImageView edit = (ImageView) tasks.lookup("#edit");
        edit.setImage(new Image(new FileInputStream("res/_iedit.png")));


        trashImg = new Image(new FileInputStream("res/trash.png"));
        editImg = new Image(new FileInputStream("res/edit.png"));
    }
}