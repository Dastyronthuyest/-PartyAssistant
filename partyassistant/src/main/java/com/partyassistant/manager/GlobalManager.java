package com.partyassistant.manager;

import com.partyassistant.dao.GlobalDao;
import com.partyassistant.dao.IGlobalDao;
import com.partyassistant.entity.GlobalEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GlobalManager {
    private static IGlobalDao globalDao;
    private static GlobalManager instance;

    public static GlobalManager getInstance() {
        if (instance == null) {
            instance = new GlobalManager();
        }
        return instance;
    }

    private GlobalManager() {
        globalDao = new GlobalDao();
    }

    public ObservableList<String> initialize() throws SQLException {
        List<GlobalEntity> globalEntities = globalDao.findAll();
        ArrayList<String> globalList = new ArrayList<>();
        for (GlobalEntity entity: globalEntities) {
            globalList.add(entity.getName());
        }

        return FXCollections.observableArrayList(globalList);
    }

    public GlobalEntity getByName(String name) throws SQLException {
        return globalDao.getByName(name);
    }
}
