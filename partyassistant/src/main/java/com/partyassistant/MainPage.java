package com.partyassistant;

import com.partyassistant.dao.GlobalDao;
import com.partyassistant.entity.GlobalEntity;
import com.partyassistant.manager.GlobalManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MainPage extends Application {

    private static Stage primaryStage;

    public static void main(String[] args) throws SQLException {
        GlobalDao globalDao = new GlobalDao();
        List<GlobalEntity> globalEntities = globalDao.findAll();
        for (GlobalEntity entity: globalEntities) {
            System.out.println(entity.getId() + " - " + entity.getName());
        }

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view.fxml"));
        MainPage.primaryStage = stage;
        primaryStage.setTitle("Party Assistant");
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
