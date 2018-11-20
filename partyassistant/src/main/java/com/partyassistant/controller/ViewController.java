package com.partyassistant.controller;

import com.partyassistant.dao.*;
import com.partyassistant.entity.*;
import com.partyassistant.manager.GlobalManager;
import com.partyassistant.manager.IngredientManager;
import com.partyassistant.manager.InnerManager;
import com.partyassistant.manager.RecipeManager;
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

    private List<CheckBox> boxes;

    private final String DESCRIPTION_PATCH_MASK =
            "C:\\Main\\Java Projects\\PartyAssistant\\partyassistant\\src\\main\\resources\\description\\";
    private final String INGREDIENTS_PATCH_MASK =
            "C:\\Main\\Java Projects\\PartyAssistant\\partyassistant\\src\\main\\resources\\ingredients\\";

    public void initialize() throws SQLException, IOException {
        boxes = new ArrayList<>();

        ObservableList<String> globalOptions = GlobalManager.getInstance().initialize();
        globalCategory.setItems(globalOptions);

        ObservableList<String> innerOptions = InnerManager.getInstance().initialize();
        innerCategory.setItems(innerOptions);

        ArrayList<String> ingredientList = IngredientManager.getInstance().initialize();
        for(String ingredient: ingredientList){
            CheckBox newBox = addIngredient(ingredient);
            ingredients.getChildren().add(newBox);
            boxes.add(newBox);
        }
        ingredients.setSpacing(15.0);

        customizeRecipe(RecipeManager.getInstance().initialize());
        ingredientEventHandler();

        globalCategory.valueProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                GlobalEntity newCurrentGlobal = GlobalManager.getInstance().getByName(observable.getValue());
                innerCategory.setItems(InnerManager.getInstance().getList(newCurrentGlobal.getId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));

        innerCategory.valueProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                InnerEntity newCurrentInner = InnerManager.getInstance().getByName(observable.getValue());
                ingredients.getChildren().clear();
                boxes.clear();
                ArrayList<String> newIngredientList = IngredientManager.getInstance().getList(newCurrentInner.getId());
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
                        IngredientEntity newCurrentIngredient = IngredientManager.getInstance().getByName(box.getText());
                        customizeRecipe(RecipeManager.getInstance().getRecipe(newCurrentIngredient.getId()));
                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
    }

    private CheckBox addIngredient(String name){
        CheckBox newCheckBox = new CheckBox(name);
        newCheckBox.setStyle("-fx-text-fill: TOMATO; -fx-font-size: 16; -fx-font-family: Candara;");
        newCheckBox.setPrefSize(200, 40);
        return newCheckBox;
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
