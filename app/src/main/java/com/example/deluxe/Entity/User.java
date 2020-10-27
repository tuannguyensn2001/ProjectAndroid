package com.example.deluxe.Entity;


public class User {

    String username,password,email;


    public User(){

    }

    public User(String user, String password, String email) {
        this.username=user;
        this.password=password;
        this.email=email;
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

    public String getEmail(){return email;}

    public void  setEmail(String email){ this.email=email;}



}

