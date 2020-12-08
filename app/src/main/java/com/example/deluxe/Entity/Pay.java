package com.example.deluxe.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pay
{
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("money")
    @Expose
    private Integer money;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

}
