package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.User;

import java.util.ArrayList;

public interface SearchUserInterface {

    public interface SearchUserView extends View
    {
        public void setList(ArrayList<User> users);
    }

    public interface SearchUserPresenter
    {
        public void handleInput(String emailSearch);
    }
}
