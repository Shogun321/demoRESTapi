package com.example.demorestapi;

import com.jsonpackaging.Jsonoperation;
import com.sender.Sender;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HelloApplication extends Application {
    private Jsonoperation jsonoperator;
    private Sender sender;
    public static final int WIDTH = 640, HEIGHT = 800;

    private ArrayList<Image> img = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        //sends initial request to download data
        sender = new Sender();
        jsonoperator = new Jsonoperation(sender.parseJSON());

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        VBox root = fxmlLoader.load();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        init(root, scene);

        stage.setTitle("iTasker Desktop");
        stage.getIcons().add(new Image("file:res/ico.png"));
        stage.setResizable(false);
        stage.setScene(scene);

        stage.show();

        /*For testing purposes, should be moved to helloController*/
        HashMap<String, String> mapToJSON = new HashMap<>();
        mapToJSON.put("name", "Hashmap1");
        mapToJSON.put("description", "Delanje");
        sender.newtask(jsonoperator.newJson(mapToJSON));

        /*HashMap<String, String> delete = new HashMap<>();
        delete.put("task_id","43");
        sender.delete(jsonoperator.newJson(delete));*/

        HashMap<String, String> edit = new HashMap<>();
        edit.put("name","Trta");
        edit.put("description", "Mrta");
        edit.put("task_id","44");
        sender.edit(jsonoperator.newJson(edit));
    }

    public static void main(String[] args) {
        launch();
    }

    public void init(VBox root, Scene scene) throws FileNotFoundException {
        String[] img = {
                "res/_login.png", "res/_login_2.png",
                "res/bg.png", "res/ime.png"
        };

        for(String _img : img)
            this.img.add(new Image(new FileInputStream(_img)));

        ImageView ime = (ImageView) scene.lookup("#ime");
        ime.setImage(this.img.get(3));

        ImageView login = new ImageView(this.img.get(0));
        login.setOnMouseEntered(mouseEvent -> { swapLoginImg(login); });
        login.setOnMouseExited(mouseEvent -> { swapLoginImg(login); });

        Pane loginPane = (Pane) scene.lookup("#loginPane");
        /*loginPane.setBackground(new Background(new BackgroundImage(this.img.get(2),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));*/

        loginPane.getChildren().add(login);
    }

    private void swapLoginImg(ImageView login){
        Image newImg = login.getImage() == this.img.get(0)?
                this.img.get(1) : this.img.get(0);
        login.setImage(newImg);
    }

    public Sender getSender() {
        return sender;
    }
}