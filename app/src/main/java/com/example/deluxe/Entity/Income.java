package com.example.deluxe.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Income {
	private Date dateConvert;
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

	public Date getDateConvert() {
		return this.dateConvert;
	}

	public void setDateConvert(Date date) {
		this.dateConvert = date;

	}


}