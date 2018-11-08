package com.partyassistant.entity;

public class RecipeEntity {
    private int id;
    private int ingredientId;
    private String ingredients;
    private String image;
    private String description;
    private String name;

    public RecipeEntity() {}

    public int getId() {
        return id;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }
}
