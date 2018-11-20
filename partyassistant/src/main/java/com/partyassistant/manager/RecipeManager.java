package com.partyassistant.manager;

import com.partyassistant.dao.IRecipeDao;
import com.partyassistant.dao.RecipeDao;
import com.partyassistant.entity.RecipeEntity;

import java.sql.SQLException;

public class RecipeManager {
    private static IRecipeDao recipeDao;
    private static RecipeManager instance;

    public static RecipeManager getInstance() {
        if (instance == null) {
            instance = new RecipeManager();
        }
        return instance;
    }

    private RecipeManager() {
        recipeDao = new RecipeDao();
    }

    public RecipeEntity initialize() throws SQLException {
        return getRecipe(1);
    }

    public RecipeEntity getRecipe(int ingredientId) throws SQLException {
        return recipeDao.findByParent(ingredientId);
    }
}
