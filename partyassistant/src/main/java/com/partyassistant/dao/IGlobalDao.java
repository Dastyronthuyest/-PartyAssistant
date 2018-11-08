package com.partyassistant.dao;

import com.partyassistant.entity.GlobalEntity;

import java.sql.SQLException;
import java.util.List;

public interface IGlobalDao {
    GlobalEntity getByName(String name) throws SQLException;

    List<GlobalEntity> findAll() throws SQLException;
}
