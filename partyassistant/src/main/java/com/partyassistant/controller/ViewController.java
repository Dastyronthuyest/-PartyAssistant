package com.partyassistant.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class ViewController {

    @FXML
    private ComboBox<String> globalCategory;

    @FXML
    private ComboBox<String> innerCategory;

    public void initialize() throws Exception {
        ObservableList<String> options = FXCollections.observableArrayList("Бутерброды", "Алкашка");
        globalCategory.setItems(options);
    }
}
