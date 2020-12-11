package com.example.deluxe.Interface.Model;

public interface ChangePasswordInterface {

	void success();

	void failedPassword();

	void failedUpdate(Exception ex);
}