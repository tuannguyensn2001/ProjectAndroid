package com.example.deluxe.Entity;

public class Message {
	private String emailSender;
	private String emailReceiver;
	private String content;
	private String created_at,updated_at;

	public Message()
	{

	}

	public Message(String sender,String emailReceiver, String content)
	{
		this.emailReceiver = emailReceiver;
		this.emailSender = sender;
		this.content = content;
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
}