package com.partyassistant.mapper;

import com.partyassistant.entity.InnerEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InnerMapper implements Mapper<InnerEntity> {
    @Override
    public InnerEntity mapRow(ResultSet resultSet) throws SQLException {
        InnerEntity innerEntity = new InnerEntity();
        innerEntity.setId(resultSet.getInt("id"));
        innerEntity.setGlobalId(resultSet.getInt("global_id"));
        innerEntity.setName(resultSet.getString("name"));
        return innerEntity;
    }
}
