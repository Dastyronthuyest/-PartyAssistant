package com.partyassistant.dao;

import com.partyassistant.connector.Connector;
import com.partyassistant.entity.IngredientEntity;
import com.partyassistant.mapper.IngredientMapper;

import java.sql.SQLException;
import java.util.List;

public class IngredientDao implements IIngredientDao {
    @Override
    public IngredientEntity getByName(String name) throws SQLException {
        String sql = "SELECT * FROM `ingredient` WHERE name=?";
        return Connector.getInstance().queryForObject(sql, new IngredientMapper(), name);
    }

    @Override
    public List<IngredientEntity> findByParent(int parentId) throws SQLException {
        String sql = "SELECT * FROM `ingredient` WHERE inner_id=?";
        return Connector.getInstance().query(sql, new IngredientMapper(), parentId);
    }
}
