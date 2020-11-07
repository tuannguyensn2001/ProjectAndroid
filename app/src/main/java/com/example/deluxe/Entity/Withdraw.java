package com.example.deluxe.Entity;

import java.util.Date;

public class Withdraw {
	private String email, username;
	private double amount;
	private String created_at, update_at;
	private boolean is_active;
	private String note;

	public Withdraw() {

	}

	public Withdraw(String email, String username, double amount, String note) {
		this.email = email;
		this.username = username;
		this.amount = amount;
		this.note = note;

		created_at = new Date().toString();
		update_at = new Date().toString();
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

	public String getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(String update_at) {
		this.update_at = update_at;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}