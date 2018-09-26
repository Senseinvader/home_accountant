package com.codecool.krk.model;

public class ExpenseCategory {

    private Integer categoryId;
    private String categoryName;

    public ExpenseCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public ExpenseCategory(Integer categoryId, String categoryName) {
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
