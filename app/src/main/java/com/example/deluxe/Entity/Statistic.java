package com.example.deluxe.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Statistic {
	@SerializedName("income")
	@Expose
	private List<Income> income = null;
	@SerializedName("pay")
	@Expose
	private List<Pay> pay = null;

	public List<Income> getIncome() {
		return income;
	}

	public void setIncome(List<Income> income) {
		this.income = income;
	}

	public List<Pay> getPay() {
		return pay;
	}

	public void setPay(List<Pay> pay) {
		this.pay = pay;
	}

}