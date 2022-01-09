package com.example.demorestapi;

import com.jsonpackaging.Jsonoperation;
import com.sender.Sender;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class HelloApplication extends Application {

    public static final int WIDTH = 640, HEIGHT = 800;

    private ArrayList<Image> img = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        VBox root = fxmlLoader.load();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        init(root, scene);

        stage.setTitle("iTasker Desktop");
        stage.getIcons().add(new Image("file:res/ico.png"));
        stage.setResizable(false);
        stage.setScene(scene);

        stage.show();

        Sender sender = new Sender();
        Jsonoperation operator = new Jsonoperation(sender.parseJSON());
        sender.newtask("bastrijan.111",operator.newJson());
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
}