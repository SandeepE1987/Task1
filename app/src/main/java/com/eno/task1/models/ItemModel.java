package com.eno.task1.models;

public class ItemModel {
    String value;
    boolean isVisible;

    public ItemModel() {
        value = "0.00";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
