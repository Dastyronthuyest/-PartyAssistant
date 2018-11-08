package com.partyassistant.entity;

public class IngredientEntity {
    private int id;
    private int innerId;
    private String name;

    public IngredientEntity(){}

    public int getId() {
        return this.id;
    }

    public int getInnerId(){
        return this.innerId;
    }

    public String getName() {
        return this.name;
    }

    public void setId(int value) {
        this.id = value;
    }

    public void setInnerId(int value) {
        this.innerId = value;
    }

    public void setName(String value) {
        this.name = value;
    }
}
