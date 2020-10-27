package com.example.deluxe.Entity;


public class User {

    String username,password;


    public User(){

    }

    public User(String user, String password) {
        this.username=user;
        this.password=password;
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



}

