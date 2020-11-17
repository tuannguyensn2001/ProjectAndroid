package com.example.deluxe.Entity;

import java.util.ArrayList;
import java.util.Date;

public class Deposit {

	private String email;
	private String username;
	private double money;
	private String created_at;
	private String updated_at;

	public Deposit()
	{

	}

	public Deposit(String email, String username, double money)
	{
		this.email=email;
		this.username=username;
		this.money=money;
		this.created_at= new Date().toString();
		this.updated_at = new Date().toString();

	}

	public Deposit(String email)
	{
		this.email=email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String  getCreated_at() {
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