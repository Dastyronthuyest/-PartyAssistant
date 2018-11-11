package com.partyassistant.dao;

import com.partyassistant.entity.IngredientEntity;

import java.sql.SQLException;
import java.util.List;

public interface IIngredientDao {
    IngredientEntity getByName(String name) throws SQLException;

    List<IngredientEntity> findByParent(int parentId) throws SQLException;
}
