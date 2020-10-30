package com.example.deluxe.Entity;

public class Wallet {

    double amount;
    String created_at;
    String updated_at;

    public Wallet(){

    }

    public Wallet(double amount, String date)
    {
        this.amount = amount;
        this.created_at = date;
        this.updated_at = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }



}
