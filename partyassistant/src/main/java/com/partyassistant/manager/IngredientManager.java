package com.partyassistant.manager;

import com.partyassistant.dao.IIngredientDao;
import com.partyassistant.dao.IngredientDao;
import com.partyassistant.entity.IngredientEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientManager {
    private static IIngredientDao ingredientDao;
    private static IngredientManager instance;

    public static IngredientManager getInstance() {
        if (instance == null) {
            instance = new IngredientManager();
        }
        return instance;
    }

    private IngredientManager() {
        ingredientDao = new IngredientDao();
    }

    public ArrayList<String> initialize() throws SQLException {
        return getList(1);
    }

    public ArrayList<String> getList(int innerId) throws SQLException{
        List<IngredientEntity> ingredientEntities = ingredientDao.findByParent(innerId);
        ArrayList<String> ingredientList = new ArrayList<>();
        for(IngredientEntity entity: ingredientEntities){
            ingredientList.add(entity.getName());
        }
        return ingredientList;
    }

    public IngredientEntity getByName(String name) throws SQLException {
        return ingredientDao.getByName(name);
    }
}
