package com.example.deluxe.Presenter;

import android.util.Log;

import com.example.deluxe.Entity.AttemptDeposit;
import com.example.deluxe.Entity.Card;
import com.example.deluxe.Interface.Model.AttemptDepositInterface;
import com.example.deluxe.Interface.Model.CardInterface;
import com.example.deluxe.Interface.Model.WalletInterface;
import com.example.deluxe.Interface.PresenterView.DepositInterface;
import com.example.deluxe.Model.AttemptDepositModel;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.CardModel;
import com.example.deluxe.Model.UserModel;
import com.example.deluxe.Model.WalletModel;
import com.example.deluxe.View.MainActivity;

import java.util.Date;

public class DepositPresenter implements DepositInterface.DepositPresenter {
    DepositInterface.DepositView depositView;
    UserModel userModel;

    public DepositPresenter(final DepositInterface.DepositView depositView) {
        this.depositView = depositView;

        initModel();


        new WalletModel().getMoney(Auth.getInstance().user().getUid(), new WalletInterface() {
            @Override
            public void dataIsLoaded(double money) {
                depositView.setMoney(money);
            }
        });
    }

    @Override
    public void handleDeposit(String serialInput, String cardCodeInput) {

            final Card card = new Card();
            card.setSerial(serialInput);
            card.setKey(cardCodeInput);
//        AttemptDeposit attemptDeposit = new AttemptDeposit();
//        attemptDeposit.setCount(0);
//        attemptDeposit.setUpdated_at(new Date().toString());
//        new AttemptDepositModel().add(Auth.getInstance().user().getUid(),attemptDeposit);

        Log.e("user","vao dc day roi");
        AttemptDepositModel attemptDepositModel = new AttemptDepositModel();
        attemptDepositModel.check(Auth.getInstance().user().getUid(), new AttemptDepositInterface() {
            @Override
            public void inValid() {
                depositView.setError("Bạn đã thử quá nhiều lần rồi");
            }

            @Override
            public void valid() {
                new CardModel().getListCard(card, new CardInterface() {
                    @Override
                    public void failed() {
                        Log.e("user","That bai");
                        depositView.setError("Thông tin thẻ không hợp lệ");
                        new AttemptDepositModel().update(Auth.getInstance().user().getUid());
                    }

                    @Override
                    public void done(Card card) {
                        Double value = card.getValue();
                        new WalletModel().deposit(Auth.getInstance().user().getUid(),value);
                    }

                    @Override
                    public void inValid() {
                        depositView.setError("Thẻ này đã được sử dụng");
                    }
                });
            }
        });
    }

    private void initModel() {
        this.userModel = new UserModel();
    }




}