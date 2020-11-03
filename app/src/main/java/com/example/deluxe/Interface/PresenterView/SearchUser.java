package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Core.View;

public interface SearchUser {

    public interface SearchUserView extends View
    {

    }

    public interface SearchUserPresenter
    {
        public void handleInput(String emailSearch);
    }
}
