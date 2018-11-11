package com.partyassistant.dao;

import com.partyassistant.entity.InnerEntity;

import java.sql.SQLException;
import java.util.List;

public interface IInnerDao {
    InnerEntity getByName(String name) throws SQLException;

    List<InnerEntity> findByParent(int parentId) throws SQLException;
}
