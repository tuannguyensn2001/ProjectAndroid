package com.example.deluxe.Entity;

public class User {
    private String user;
    private String password;

    public  void setUser(String user){
        this.user=user;
    }
    public String getUser(){
        return this.user;
    }

    public  void setPassword(String password){
        this.password=password;
    }
    public  String getPassword(){
        return  this.password;
    }

    public User(String user, String password){
        this.user=user;
        this.password=password;
    }
}
