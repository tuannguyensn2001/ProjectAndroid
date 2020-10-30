package com.example.deluxe.Helper;

public class Rules {
    public static boolean required(String s){
        if(s.length()>0) return true;
        return false;
    }
    public static boolean min(String value,int length){
        if(value.length()>=length) return true;
        return false;
    }
    public static boolean isEmail(String value){
        String emailPattern = "\\w+@\\w+[.]\\w+";
        boolean flag = value.matches(emailPattern);
        if(!flag) return false;
        return true;
    }
    public static boolean stringLength( String value, int length){
        if(value.length()==length) return true;
        return false;
    }
}