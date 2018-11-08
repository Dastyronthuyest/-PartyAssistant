package com.partyassistant.controller;

import com.partyassistant.dao.GlobalDao;
import com.partyassistant.entity.GlobalEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.List;

public class ViewController {

    @FXML
    private ComboBox<String> globalCategory;

    @FXML
    private ComboBox<String> innerCategory;

    private GlobalDao globalDao;

    public void initialize() throws Exception {
        globalDao = new GlobalDao();
        List<GlobalEntity> globalEntities = globalDao.findAll();
        ArrayList<String> globalList = new ArrayList<>();
        for (GlobalEntity entity: globalEntities) {
            globalList.add(entity.getName());
        }

        ObservableList<String> options = FXCollections.observableArrayList(globalList);
        globalCategory.setItems(options);
    }
}
