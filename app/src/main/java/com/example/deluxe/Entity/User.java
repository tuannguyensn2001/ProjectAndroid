package com.example.deluxe.Entity;


public class User {

    String user,password;


    public void User() {

    }

    public User(String user, String password) {
        this.user=user;
        this.password=password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}

