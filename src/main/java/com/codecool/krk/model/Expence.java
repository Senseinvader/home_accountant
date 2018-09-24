package com.codecool.krk.model;

import java.util.Date;

public class Expence {

    private Integer expenceId;
    private Integer expenceAmount;
    private Date purchaseDate;
    private String comment;
    private ExpenceCategory expenceCategory;

    public Expence(Integer expenceId, Integer expenceAmount, Date purcahseDate, String comment, ExpenceCategory expenceCategory) {
        this.expenceId = expenceId;
        this.expenceAmount = expenceAmount;
        this.purchaseDate = purcahseDate;
        this.comment = comment;
        this.expenceCategory = expenceCategory;
    }

    public Expence(Integer expenceAmount, Date purchaseDate, String comment, ExpenceCategory expenceCategory) {
        this.expenceAmount = expenceAmount;
        this.purchaseDate = purchaseDate;
        this.comment = comment;
        this.expenceCategory = expenceCategory;
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

    public ExpenceCategory getExpenceCategory() {
        return expenceCategory;
    }

    public void setExpenceCategory(ExpenceCategory expenceCategory) {
        this.expenceCategory = expenceCategory;
    }
}
