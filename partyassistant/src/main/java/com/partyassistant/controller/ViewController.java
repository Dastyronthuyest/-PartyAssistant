package com.partyassistant.controller;

import com.partyassistant.dao.GlobalDao;
import com.partyassistant.dao.IngredientDao;
import com.partyassistant.dao.InnerDao;
import com.partyassistant.entity.GlobalEntity;
import com.partyassistant.entity.IngredientEntity;
import com.partyassistant.entity.InnerEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewController {

    @FXML
    private ComboBox<String> globalCategory;

    @FXML
    private ComboBox<String> innerCategory;

    @FXML
    private VBox ingredients;

    private GlobalDao globalDao;
    private InnerDao innerDao;
    private IngredientDao ingredientDao;

    public void initialize() throws SQLException {
        ObservableList<String> globalOptions = initializeGlobal();
        globalCategory.setItems(globalOptions);

        ObservableList<String> innerOptions = initializeInner();
        innerCategory.setItems(innerOptions);

        ArrayList<String> ingredientList = initializeIngredients();
        for(String ingredient: ingredientList){
            ingredients.getChildren().add(addIngredient(ingredient));
        }

        globalCategory.valueProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                GlobalEntity newCurrentGlobal = globalDao.getByName(observable.getValue());
                innerCategory.setItems(getInnerList(newCurrentGlobal.getId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));

        innerCategory.valueProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                InnerEntity newCurrentInner = innerDao.getByName(observable.getValue());
                ingredients.getChildren().clear();
                ArrayList<String> newIngredientList = getIngredientList(newCurrentInner.getId());
                for (String ingredient: newIngredientList) {
                    ingredients.getChildren().add(addIngredient(ingredient));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));
    }

    private ObservableList<String> initializeGlobal() throws SQLException {
        globalDao = new GlobalDao();
        List<GlobalEntity> globalEntities = globalDao.findAll();
        ArrayList<String> globalList = new ArrayList<>();
        for (GlobalEntity entity: globalEntities) {
            globalList.add(entity.getName());
        }

        return FXCollections.observableArrayList(globalList);
    }

    private ObservableList<String> initializeInner() throws SQLException{
        innerDao = new InnerDao();
        return getInnerList(1);
    }

    private ObservableList<String> getInnerList(int globalId) throws SQLException{
        List<InnerEntity> innerEntities = innerDao.findByParent(globalId);
        ArrayList<String> innerList = new ArrayList<>();
        for (InnerEntity entity: innerEntities) {
            innerList.add(entity.getName());
        }
        return FXCollections.observableArrayList(innerList);
    }

    private ArrayList<String> initializeIngredients() throws SQLException{
        ingredientDao = new IngredientDao();

        ingredients.setSpacing(15.0);
        return getIngredientList(1);
    }

    private ArrayList<String> getIngredientList(int innerId) throws SQLException{
        List<IngredientEntity> ingredientEntities = ingredientDao.findByParent(innerId);
        ArrayList<String> ingredientList = new ArrayList<>();
        for(IngredientEntity entity: ingredientEntities){
            ingredientList.add(entity.getName());
        }
        return ingredientList;
    }

    private CheckBox addIngredient(String name){
        CheckBox newCheckBox = new CheckBox(name);
        newCheckBox.setStyle("-fx-text-fill: TOMATO; -fx-font-size: 16");
        newCheckBox.setPrefSize(200, 40);
        return newCheckBox;
    }
}
