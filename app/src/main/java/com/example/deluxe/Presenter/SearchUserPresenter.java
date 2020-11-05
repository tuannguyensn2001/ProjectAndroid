package com.example.deluxe.Presenter;

import android.util.Log;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.Model.UserInterface;
import com.example.deluxe.Interface.PresenterView.SearchUserInterface;
import com.example.deluxe.Model.UserModel;

import java.util.ArrayList;

public class SearchUserPresenter implements SearchUserInterface.SearchUserPresenter {
    UserModel userModel;
    SearchUserInterface.SearchUserView searchUserView;

    public SearchUserPresenter(SearchUserInterface.SearchUserView searchUserView) {
        this.searchUserView = searchUserView;
        this.userModel = new UserModel();
    }

    @Override
    public void handleInput(String emailSearch) {
        User user = new User(null, null, emailSearch);
        this.userModel.search(user, new UserInterface() {
            @Override
            public void dataIsLoaded(ArrayList<User> list) {
                Log.e("Search", list.size()+"");
                searchUserView.setList(list);
            }
        });
    }
}
