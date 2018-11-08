package com.partyassistant.entity;

public class InnerEntity {
    private int id;
    private int globalId;
    private String name;

    public InnerEntity(){}

    public int getId() {
        return this.id;
    }

    public int getGlobalId(){
        return this.globalId;
    }

    public String getName() {
        return this.name;
    }

    public void setId(int value) {
        this.id = value;
    }

    public void setGlobalId(int value) {
        this.globalId = value;
    }

    public void setName(String value) {
        this.name = value;
    }
}
