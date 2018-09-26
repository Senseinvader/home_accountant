package com.codecool.krk.model;

import java.util.Date;

public class Expense {

    private Integer expenceId;
    private Integer expenceAmount;
    private Date purchaseDate;
    private String comment;
    private ExpenseCategory expenseCategory;

    public Expense(Integer expenceId, Integer expenceAmount, Date purcahseDate, String comment, ExpenseCategory expenseCategory) {
        this.expenceId = expenceId;
        this.expenceAmount = expenceAmount;
        this.purchaseDate = purcahseDate;
        this.comment = comment;
        this.expenseCategory = expenseCategory;
    }

    public Expense(Integer expenceAmount, Date purchaseDate, String comment, ExpenseCategory expenseCategory) {
        this.expenceAmount = expenceAmount;
        this.purchaseDate = purchaseDate;
        this.comment = comment;
        this.expenseCategory = expenseCategory;
    }

    public Integer getExpenceId() {
        return expenceId;
    }

    public void setExpenceId(Integer expenceId) {
        this.expenceId = expenceId;
    }

    public Integer getExpenceAmount() {
        return expenceAmount;
    }

    public void setExpenceAmount(Integer expenceAmount) {
        this.expenceAmount = expenceAmount;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }
}
