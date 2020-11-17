package com.example.deluxe.Presenter;


import android.util.Log;

import androidx.annotation.NonNull;

import com.example.deluxe.Entity.Deposit;
import com.example.deluxe.Entity.Transfer;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Entity.Withdraw;
import com.example.deluxe.Interface.Model.CheckInterface;
import com.example.deluxe.Interface.Model.ListDepositInterface;
import com.example.deluxe.Interface.Model.ListTransferInterface;
import com.example.deluxe.Interface.Model.ListWithdrawInterface;
import com.example.deluxe.Interface.PresenterView.MainInterface;
import com.example.deluxe.Model.Auth;
<<<<<<< HEAD
import com.example.deluxe.Model.DepositModel;
import com.example.deluxe.Model.TransferModel;
import com.example.deluxe.Model.UserModel;
import com.example.deluxe.Model.WithdrawModel;
import com.example.deluxe.View.DepositActivity;
import com.example.deluxe.View.SignInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
=======
import com.example.deluxe.View.Transaction.DepositActivity;
import com.example.deluxe.View.Auth.SignInActivity;
>>>>>>> 6e52742c5d4d535db56eda368b46d114b2de4a74

public class MainPresenter implements MainInterface.MainPresenter {

    private MainInterface.MainView mainView;

    public MainPresenter(MainInterface.MainView view)
    {
        this.mainView = view;

        if (!Auth.getInstance().check()) mainView.loadView(SignInActivity.class);
<<<<<<< HEAD

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        String token = task.getResult();
                        Log.e("token",token);
                        Log.e("token", FirebaseInstanceId.getInstance().getToken());
                    }
                });

//        new WalletModel().read();
//        new UserModel().read();
//
//            new WithdrawModel().getListWithdraw(new ListWithdrawInterface() {
//                @Override
//                public void dataIsLoaded(ArrayList<Withdraw> list) {
//
//                }
//            });

        final User user = new User("vinhdeptrainhatxom@gmail.com","12346789");

        new UserModel().checkEmailPassword(user, new CheckInterface() {
            @Override
            public void dataIsLoaded(boolean check) {
                Log.e("check",check + "");

            }
        });




=======
>>>>>>> 6e52742c5d4d535db56eda368b46d114b2de4a74
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
