package com.partyassistant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.SQLException;

public class MainPage extends Application {

    private static Stage primaryStage;

    public static void main(String[] args) throws SQLException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view.fxml"));
        MainPage.primaryStage = stage;
        MainPage.primaryStage.getIcons().add(new Image("/icon.jpg"));
        primaryStage.setTitle("Party Assistant");
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
