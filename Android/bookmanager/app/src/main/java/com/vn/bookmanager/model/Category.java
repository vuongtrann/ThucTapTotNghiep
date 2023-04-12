package com.vn.bookmanager.MODEL;

public class Category {
    String categoryName;
    int id;

    public Category(String categoryName, int id) {
        this.categoryName = categoryName;
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id+" - "+categoryName;
    }
}
