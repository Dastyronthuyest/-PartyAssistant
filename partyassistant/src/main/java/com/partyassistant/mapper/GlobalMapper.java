package com.partyassistant.mapper;

import com.partyassistant.entity.GlobalEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GlobalMapper implements Mapper<GlobalEntity> {
    @Override
    public GlobalEntity mapRow(ResultSet resultSet) throws SQLException {
        GlobalEntity globalEntity = new GlobalEntity();
        globalEntity.setId(resultSet.getInt("id"));
        globalEntity.setName(resultSet.getString("name"));
        return globalEntity;
    }
}
