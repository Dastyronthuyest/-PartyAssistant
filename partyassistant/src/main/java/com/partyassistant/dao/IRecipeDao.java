package com.partyassistant.dao;

import com.partyassistant.entity.RecipeEntity;

import java.sql.SQLException;
import java.util.List;

public interface IRecipeDao {
    RecipeEntity findByParent(int parentId) throws SQLException;
}
