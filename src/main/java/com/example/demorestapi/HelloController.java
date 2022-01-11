package com.example.demorestapi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class HelloController{
    @FXML
    private Label welcomeText;

    @FXML
    private VBox parent;

    @FXML
    private PieChart pie;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void onMybuttononClick() {
        welcomeText.setText("Pressed");
    }
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
    protected void initialize(){
        initPie();
    }


    private void initPie(){
       /* ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Iphone 5S", 13),
                new PieChart.Data("Samsung Grand", 25),
                new PieChart.Data("MOTO G", 10),
                new PieChart.Data("Nokia Lumia", 22));

        pie.setData(pieChartData);*/
    }
}