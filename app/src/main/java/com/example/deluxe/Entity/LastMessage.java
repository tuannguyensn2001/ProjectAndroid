package com.example.deluxe.Entity;

public class LastMessage {
	private String email;
	private String content;
	private String image_url;
	private String username;


	public LastMessage(String email, String content, String image_url) {
		this.email = email;
		this.content = content;
		this.image_url = image_url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}


}