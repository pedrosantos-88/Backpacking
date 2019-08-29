package com.android.firebaseapp.backpacking.Model;

public class Expenses {
    String description;
    Double value;
    String currency;
    String date;


    public Expenses() {

    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
