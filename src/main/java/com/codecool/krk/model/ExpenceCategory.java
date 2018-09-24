package com.codecool.krk.model;

public class ExpenceCategory {

    private Integer categoryId;
    private String categoryName;

    public ExpenceCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public ExpenceCategory(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
