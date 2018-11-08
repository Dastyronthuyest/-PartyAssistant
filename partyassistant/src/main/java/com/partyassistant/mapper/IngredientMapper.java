package com.partyassistant.mapper;

import com.partyassistant.entity.IngredientEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientMapper implements Mapper<IngredientEntity> {
    @Override
    public IngredientEntity mapRow(ResultSet resultSet) throws SQLException {
        IngredientEntity ingredientEntity = new IngredientEntity();
        ingredientEntity.setId(resultSet.getInt("id"));
        ingredientEntity.setInnerId(resultSet.getInt("inner_id"));
        ingredientEntity.setName(resultSet.getString("name"));
        return ingredientEntity;
    }
}
