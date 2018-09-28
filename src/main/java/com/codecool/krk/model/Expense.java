package com.codecool.krk.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Expense {

    private Integer expenseId;
    private Integer expenseAmount;
    private Date purchaseDate;
    private String comment;
    private ExpenseCategory expenseCategory;

    public Expense(Integer expenseId, Integer expenseAmount, Date purchaseDate, String comment, ExpenseCategory expenseCategory) {
        this.expenseId = expenseId;
        this.expenseAmount = expenseAmount;
        this.purchaseDate = purchaseDate;
        this.comment = comment;
        this.expenseCategory = expenseCategory;
    }

    public Expense(Integer expenseAmount, Date purchaseDate, String comment, ExpenseCategory expenseCategory) {
        this.expenseAmount = expenseAmount;
        this.purchaseDate = purchaseDate;
        this.comment = comment;
        this.expenseCategory = expenseCategory;
    }

    public Integer getExpenceId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }

    public Integer getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(Integer expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public String getStringPurchaseDate() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(purchaseDate);
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

    public String getExpenseCategoryName() {
        return expenseCategory.getCategoryName();
    }

    public void setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }
}
