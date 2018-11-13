package com.partyassistant.controller;

import com.partyassistant.dao.*;
import com.partyassistant.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewController {

    @FXML private ComboBox<String> globalCategory;

    @FXML private ComboBox<String> innerCategory;

    @FXML private VBox ingredients;

    @FXML private ImageView recipeImage;

    @FXML private Label recipeName;

    @FXML private Label recipeIngredients;

    @FXML private Label recipeDescription;

    private GlobalDao globalDao;
    private InnerDao innerDao;
    private IngredientDao ingredientDao;
    private IRecipeDao recipeDao;

    private final String DESCRIPTION_PATCH_MASK =
            "C:\\Main\\Java Projects\\PartyAssistant\\partyassistant\\src\\main\\resources\\description\\";
    private final String INGREDIENTS_PATCH_MASK =
            "C:\\Main\\Java Projects\\PartyAssistant\\partyassistant\\src\\main\\resources\\ingredients\\";

    public void initialize() throws SQLException, IOException {
        ObservableList<String> globalOptions = initializeGlobal();
        globalCategory.setItems(globalOptions);

        ObservableList<String> innerOptions = initializeInner();
        innerCategory.setItems(innerOptions);

        ArrayList<String> ingredientList = initializeIngredients();
        for(String ingredient: ingredientList){
            ingredients.getChildren().add(addIngredient(ingredient));
        }

        RecipeEntity entity = initializeRecipe();

        this.recipeImage = new ImageView((new Image(new File(entity.getImage()).toURI().toString())));
        System.out.println(entity.getImage());
        this.recipeName = new Label((entity.getName()));
        System.out.println(this.recipeName.getText());

        try(BufferedReader br = new BufferedReader(new FileReader(INGREDIENTS_PATCH_MASK + entity.getIngredients()))) {
            this.recipeIngredients = new Label(readData(br));
            System.out.println(this.recipeIngredients.getText());
        }

        try(BufferedReader br = new BufferedReader(new FileReader(DESCRIPTION_PATCH_MASK + entity.getDescription()))) {
            this.recipeDescription = new Label(readData(br));
            System.out.println(this.recipeDescription.getText());
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

    private RecipeEntity initializeRecipe() throws SQLException {
        recipeDao = new RecipeDao();

        return getRecipe(1);
    }

    private String readData(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        String text = br.readLine();

        while (text != null) {
            sb.append(text);
            sb.append(System.lineSeparator());
            text = br.readLine();
        }
        return sb.toString();
    }

    private RecipeEntity getRecipe(int ingredientId) throws SQLException{
        return recipeDao.findByParent(ingredientId);
    }
}
