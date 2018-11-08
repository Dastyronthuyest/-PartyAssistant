package com.partyassistant.mapper;

import com.partyassistant.entity.RecipeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeMapper implements Mapper<RecipeEntity> {
    @Override
    public RecipeEntity mapRow(ResultSet resultSet) throws SQLException {
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setId(resultSet.getInt("id"));
        recipeEntity.setIngredientId(resultSet.getInt("ingredient_id"));
        recipeEntity.setIngredients(resultSet.getString("ingredients"));
        recipeEntity.setImage(resultSet.getString("image"));
        recipeEntity.setDescription(resultSet.getString("description"));
        recipeEntity.setName(resultSet.getString("name"));
        return recipeEntity;
    }
}
