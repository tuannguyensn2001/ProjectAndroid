package com.example.deluxe.Model;

import com.example.deluxe.Core.Model;
import com.example.deluxe.Entity.User;
import java.util.ArrayList;

public class UserModel implements Model {
    ArrayList<User> listUser;

    public UserModel(){
        init();
    }
    public boolean checkUserExist(User user){

        for(User i : listUser){
            if(user.getUser().equals(i.getUser()) && user.getPassword().equals(i.getPassword())){
                return  true;
            }
        }
        return false;
    }

    public void init(){
        listUser = new ArrayList<User>();
        listUser.add(new User("nguyenvana","123456") );
        listUser.add(new User("nguyenvanb","121212"));
        listUser.add(new User("phamthiduc" , "123123"));
        listUser.add(new User("tranmaihuong","321321"));
        listUser.add(new User("nguyenngocchau","111111"));
        listUser.add(new User("dangtrungdung","222222"));
        listUser.add(new User("nguyenvantuan","123456"));

    }
}