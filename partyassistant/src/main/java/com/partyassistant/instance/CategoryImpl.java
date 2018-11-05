package com.partyassistant.instance;

import java.util.List;

public abstract class CategoryImpl implements Category {
    private List<String> categoriesList;
    private String current;

    public CategoryImpl(){}

    public CategoryImpl(List<String> list){
        categoriesList = list;
        setCurrent(categoriesList.get(0));
    }

    @Override
    public String getCurrent() {
        return this.current;
    }

    @Override
    public boolean setCurrent(String newCategory) {
        if(categoriesList.contains(newCategory)){
            current = newCategory;
            return true;
        }
        return false;
    }
}
