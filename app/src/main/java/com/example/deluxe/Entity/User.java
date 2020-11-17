package com.example.deluxe.Entity;

public class User {

	String username, password;
	String email;
<<<<<<< HEAD
	String created_at,updated_at;
=======
	String created_at, updated_at;
>>>>>>> 6e52742c5d4d535db56eda368b46d114b2de4a74
	String token;

	public User() {

	}


	public User(String username, String password) {
			this.username = username;
			this.password = password;
	}

	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUser() {
		return username;
	}

	public void setUser(String user) {
		this.username = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String  getToken()
	{
		return this.token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}
}
