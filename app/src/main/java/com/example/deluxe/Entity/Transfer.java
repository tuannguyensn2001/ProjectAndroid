package com.example.deluxe.Entity;

public class Transfer {
	private String emailDepositor, emailReceiver, messages;
	private double money;
	private String created_at, updated_at;

	public Transfer() {

	}

	public Transfer(String emailDepositor, String emailReceiver, double money, String messages) {
		this.emailDepositor = emailDepositor;
		this.emailReceiver = emailReceiver;
		this.money = money;
		this.messages = messages;
	}

	public String getEmailDepositor() {
		return emailDepositor;
	}

	public void setEmailDepositor(String emailDepositor) {
		this.emailDepositor = emailDepositor;
	}

	public String getEmailReceiver() {
		return emailReceiver;
	}

	public void setEmailReceiver(String emailReceiver) {
		this.emailReceiver = emailReceiver;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
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