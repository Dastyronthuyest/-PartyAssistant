package com.partyassistant.controller;

import com.partyassistant.dao.GlobalDao;
import com.partyassistant.dao.InnerDao;
import com.partyassistant.entity.GlobalEntity;
import com.partyassistant.entity.InnerEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewController {

    @FXML
    private ComboBox<String> globalCategory;

    @FXML
    private ComboBox<String> innerCategory;

    private GlobalDao globalDao;
    private InnerDao innerDao;

    public void initialize() throws SQLException {
        ObservableList<String> globalOptions = initializeGlobal();
        globalCategory.setItems(globalOptions);

        ObservableList<String> innerOptions = initializeInner();
        innerCategory.setItems(innerOptions);

        globalCategory.valueProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                GlobalEntity newCurrentGlobal = globalDao.getByName(observable.getValue());
                innerCategory.setItems(getInnerList(newCurrentGlobal.getId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));
    }

    private ObservableList<String> initializeGlobal() throws SQLException {
        globalDao = new GlobalDao();
        List<GlobalEntity> globalEntities = globalDao.findAll();
        ArrayList<String> globalList = new ArrayList<>();
        for (GlobalEntity entity: globalEntities) {
            globalList.add(entity.getName());
        }

        return FXCollections.observableArrayList(globalList);
    }

    private ObservableList<String> initializeInner() throws SQLException{
        innerDao = new InnerDao();
        return getInnerList(1);
    }

    private ObservableList<String> getInnerList(int globalId) throws SQLException{
        List<InnerEntity> innerEntities = innerDao.findByParent(globalId);
        ArrayList<String> innerList = new ArrayList<>();
        for (InnerEntity entity: innerEntities) {
            innerList.add(entity.getName());
        }
        return FXCollections.observableArrayList(innerList);
    }
}
