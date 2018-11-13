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
    private List<CheckBox> boxes;

    private final String DESCRIPTION_PATCH_MASK =
            "C:\\Main\\Java Projects\\PartyAssistant\\partyassistant\\src\\main\\resources\\description\\";
    private final String INGREDIENTS_PATCH_MASK =
            "C:\\Main\\Java Projects\\PartyAssistant\\partyassistant\\src\\main\\resources\\ingredients\\";

    public void initialize() throws SQLException, IOException {
        boxes = new ArrayList<>();

        ObservableList<String> globalOptions = initializeGlobal();
        globalCategory.setItems(globalOptions);

        ObservableList<String> innerOptions = initializeInner();
        innerCategory.setItems(innerOptions);

        ArrayList<String> ingredientList = initializeIngredients();
        for(String ingredient: ingredientList){
            CheckBox newBox = addIngredient(ingredient);
            ingredients.getChildren().add(newBox);
            boxes.add(newBox);
        }

        customizeRecipe(initializeRecipe());
        ingredientEventHandler();

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
                boxes.clear();
                ArrayList<String> newIngredientList = getIngredientList(newCurrentInner.getId());
                for (String ingredient: newIngredientList) {
                    CheckBox newBox = addIngredient(ingredient);
                    ingredients.getChildren().add(newBox);
                    boxes.add(newBox);
                }

                ingredientEventHandler();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));
    }

    private void ingredientEventHandler() {
        for(CheckBox box: boxes){
            box.selectedProperty().addListener(((observable1, oldValue1, newValue1) -> {
                if(box.isSelected()){
                    try {
                        IngredientEntity newCurrentIngredient = ingredientDao.getByName(box.getText());
                        customizeRecipe(getRecipe(newCurrentIngredient.getId()));
                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
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
        newCheckBox.setStyle("-fx-text-fill: TOMATO; -fx-font-size: 16; -fx-font-family: Candara;");
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

    private void customizeRecipe(RecipeEntity entity) throws IOException {
        recipeImage.setImage(new Image(entity.getImage()));
        recipeName.setText(entity.getName());
        recipeName.setWrapText(true);

        try(BufferedReader br = new BufferedReader(new FileReader(INGREDIENTS_PATCH_MASK + entity.getIngredients()))) {
            recipeIngredients.setText(readData(br));
        }

        try(BufferedReader br = new BufferedReader(new FileReader(DESCRIPTION_PATCH_MASK + entity.getDescription()))) {
            recipeDescription.setText(readData(br));
        }
    }
}
