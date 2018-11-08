package com.partyassistant.dao;

import com.partyassistant.connector.Connector;
import com.partyassistant.entity.GlobalEntity;
import com.partyassistant.mapper.GlobalMapper;

import java.sql.SQLException;
import java.util.List;

public class GlobalDao implements IGlobalDao {
    @Override
    public GlobalEntity getByName(String name) throws SQLException {
        String sql = "SELECT * FROM global WHERE id=" + name;
        return Connector.getInstance().queryForObject(sql, new GlobalMapper());
    }

    @Override
    public List<GlobalEntity> findAll() throws SQLException {
        String sql = "SELECT * FROM global ORDER BY name";
        return Connector.getInstance().query(sql, new GlobalMapper());
    }
}
