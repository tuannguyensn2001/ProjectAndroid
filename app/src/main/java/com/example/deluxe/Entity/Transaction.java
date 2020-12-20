package com.example.deluxe.Entity;

import com.example.deluxe.Enum.TransactionType;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
	private TransactionType type;
	private long money;
	private Date date;
	private boolean isComplete;
	private String userEmail;
	private String message;
	private String id;

	public Transaction(TransactionType type, long money, Date date, boolean isComplete, String userEmail, String message) {
		this.type = type;
		this.money = money;
		this.date = date;
		this.isComplete = isComplete;
		this.userEmail = userEmail;
		this.message = message;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean complete) {
		this.isComplete = complete;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}