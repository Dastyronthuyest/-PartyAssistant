package com.partyassistant;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainPage extends Application {

    private Pane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        root = new Pane();
        Image fon = new Image(getClass().getClassLoader().getResourceAsStream("fon.jpg"));
        ImageView fonView = new ImageView(fon);
        root.getChildren().add(fonView);

        Scene scene = new Scene(root);

        primaryStage.setTitle("Party Assistant");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
