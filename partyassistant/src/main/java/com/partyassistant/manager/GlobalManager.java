package com.partyassistant.manager;

import com.partyassistant.instance.CategoryImpl;
import com.partyassistant.instance.category.Global;
import javafx.scene.layout.StackPane;

import javax.swing.*;
import java.util.*;

public class GlobalManager {
    private JComboBox<String> listBox;
    private CategoryImpl globalCategory;

    public GlobalManager(List<String> list){
        String[] strs = (String[]) list.toArray();
        listBox = new JComboBox<String>(strs);
        globalCategory = new Global(list);

//        Map<String, List<String>> map = new HashMap<String, List<String>>();
//        map.put("Блюдо", Arrays.asList("Блюдо1", "Блюдо2", "Блюдо3"));
//        map.put("Десерт", Arrays.asList("Десерт1", "Десерт2", "Десерт3"));
//
//        printMap(map);
    }

    public String getCategory(){
        return globalCategory.getCurrent();
    }

    public boolean setCategory(String category){
        boolean success = globalCategory.setCurrent(category);
        if(success){
            listBox.setSelectedItem(category);
        }
        return success;
    }

    public JComboBox<String> getListEntity() {
        return listBox;
    }

    //    private void printMap(Map<String, List<String>> map){
//        for (Map.Entry<String, List<String>> elem : map.entrySet()) {
//            System.out.println(elem.getKey() + ": " + elem.getValue().toString());
//        }
//    }
}
