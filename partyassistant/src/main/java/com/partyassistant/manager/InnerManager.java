package com.partyassistant.manager;

import com.partyassistant.dao.IInnerDao;
import com.partyassistant.dao.InnerDao;
import com.partyassistant.entity.InnerEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InnerManager {
    private static IInnerDao innerDao;
    private static InnerManager instance;

    public static InnerManager getInstance() {
        if (instance == null) {
            instance = new InnerManager();
        }
        return instance;
    }

    private InnerManager() {
        innerDao = new InnerDao();
    }

    public ObservableList<String> initialize() throws SQLException {
        return getList(1);
    }

    public ObservableList<String> getList(int globalId) throws SQLException {
        List<InnerEntity> innerEntities = innerDao.findByParent(globalId);
        ArrayList<String> innerList = new ArrayList<>();
        for (InnerEntity entity: innerEntities) {
            innerList.add(entity.getName());
        }
        return FXCollections.observableArrayList(innerList);
    }

    public InnerEntity getByName(String name) throws SQLException {
        return innerDao.getByName(name);
    }
}
