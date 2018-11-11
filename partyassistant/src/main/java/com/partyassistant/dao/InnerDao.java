package com.partyassistant.dao;

import com.partyassistant.connector.Connector;
import com.partyassistant.entity.InnerEntity;
import com.partyassistant.mapper.InnerMapper;

import java.sql.SQLException;
import java.util.List;

public class InnerDao implements IInnerDao {
    @Override
    public InnerEntity getByName(String name) throws SQLException {
        String sql = "SELECT * FROM `inner` WHERE name=?";
        return Connector.getInstance().queryForObject(sql, new InnerMapper(), name);
    }

    @Override
    public List<InnerEntity> findByParent(int parentId) throws SQLException {
        String sql = "SELECT * FROM `inner` WHERE global_id=?";
        return Connector.getInstance().query(sql, new InnerMapper(), parentId);
    }
}
