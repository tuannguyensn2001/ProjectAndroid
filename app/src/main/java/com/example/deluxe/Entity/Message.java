package com.example.deluxe.Entity;

public class Message {
	private String id;
	private int type; /* 0 = binh thuong, -1 = gui tien, 1 = doi tien */
	private int status; /* -1 = bi huy, 0 = dang cho, 1 = xong roi */
	private double firstMoney, secondMoney;
	private String emailSender;
	private String emailReceiver;
	private String content;
	private String created_at, updated_at;

	public Message() {

	}

	public Message(int type, int status, double firstMoney, String emailSender, String emailReceiver, String content) {
		this.type = type;
		this.status = status;
		this.firstMoney = firstMoney;
		this.emailSender = emailSender;
		this.emailReceiver = emailReceiver;
		this.content = content;
	}

	public Message(String sender, String emailReceiver, String content) {
		this.type = 0;
		this.emailReceiver = emailReceiver;
		this.emailSender = sender;
		this.content = content;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmailSender() {
		return emailSender;
	}

	public void setEmailSender(String emailSender) {
		this.emailSender = emailSender;
	}

	public String getEmailReceiver() {
		return emailReceiver;
	}

	public void setEmailReceiver(String emailReceiver) {
		this.emailReceiver = emailReceiver;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getFirstMoney() {
		return firstMoney;
	}

	public void setFirstMoney(double firstMoney) {
		this.firstMoney = firstMoney;
	}

	public double getSecondMoney() {
		return secondMoney;
	}

	public void setSecondMoney(double secondMoney) {
		this.secondMoney = secondMoney;
	}
}