package com.example.deluxe.Presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.deluxe.Interface.PresenterView.MainInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.View.DepositActivity;
import com.example.deluxe.View.SignInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainPresenter implements MainInterface.MainPresenter {

    private MainInterface.MainView mainView;

    public MainPresenter(MainInterface.MainView view)
    {
        this.mainView = view;

        if (!Auth.getInstance().check()) mainView.loadView(SignInActivity.class);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        String token = task.getResult();
                        Log.e("token",token);
                        Log.e("token",FirebaseInstanceId.getInstance().getToken());
                    }
                });





    }

    @Override
    public void handleLogOut() {
        Auth.getInstance().logout();
        mainView.loadView(SignInActivity.class);
    }

    @Override
    public void handleDeposit() {
        mainView.loadView(DepositActivity.class);
    }
}
