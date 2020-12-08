package com.example.deluxe.Presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.deluxe.API.CoreAPI;
import com.example.deluxe.API.ProductAPI;
import com.example.deluxe.Entity.Collection;
import com.example.deluxe.Entity.Product;
import com.example.deluxe.Interface.Model.WalletInterface;
import com.example.deluxe.Interface.PresenterView.MainInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.WalletModel;
import com.example.deluxe.View.Transaction.DepositActivity;
import com.example.deluxe.View.Auth.SignInActivity;
import com.google.gson.*;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPresenter implements MainInterface.MainPresenter {

    private MainInterface.MainView mainView;

    public MainPresenter(MainInterface.MainView view)
    {
        this.mainView = view;

        if (!Auth.getInstance().check()){
            mainView.loadView(SignInActivity.class);
            return ;
        }

        new WalletModel().getMoney(Auth.getInstance().user().getUid(), new WalletInterface() {
            @Override
            public void dataIsLoaded(double money) {
                mainView.setMoney(money);
            }
        });





    }

    @Override
    public void handleLogOut() {
        Auth.getInstance().logout();
        mainView.loadView(SignInActivity.class);
    }
}
