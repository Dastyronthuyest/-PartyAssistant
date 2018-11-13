package com.partyassistant.dao;

import com.partyassistant.connector.Connector;
import com.partyassistant.entity.RecipeEntity;
import com.partyassistant.mapper.RecipeMapper;

import java.sql.SQLException;
import java.util.List;

public class RecipeDao implements IRecipeDao {
    @Override
    public RecipeEntity findByParent(int parentId) throws SQLException {
        String sql = "SELECT * FROM recipe WHERE ingredient_id=?";
        return Connector.getInstance().queryForObject(sql, new RecipeMapper(), parentId);
    }
}
