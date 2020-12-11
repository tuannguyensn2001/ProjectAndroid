package com.example.deluxe.Presenter.Transaction;
<<<<<<< HEAD:app/src/main/java/com/example/deluxe/Presenter/Transaction/WithdrawPresenter.java
=======

import android.util.Log;
>>>>>>> 6e52742c5d4d535db56eda368b46d114b2de4a74:app/src/main/java/com/example/deluxe/Presenter/WithdrawPresenter.java

import com.example.deluxe.Entity.User;
import com.example.deluxe.Entity.Withdraw;
import com.example.deluxe.Enum.ErrorMessage;
<<<<<<< HEAD:app/src/main/java/com/example/deluxe/Presenter/Transaction/WithdrawPresenter.java
=======
import com.example.deluxe.Interface.Model.CheckInterface;
import com.example.deluxe.Interface.Model.UserDetailsInterface;
>>>>>>> 6e52742c5d4d535db56eda368b46d114b2de4a74:app/src/main/java/com/example/deluxe/Presenter/WithdrawPresenter.java
import com.example.deluxe.Interface.Model.UserDetailsInterface;
import com.example.deluxe.Interface.Model.WalletInterface;
import com.example.deluxe.Interface.PresenterView.Transaction.WithdrawInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.UserModel;
import com.example.deluxe.Model.WalletModel;
import com.example.deluxe.Model.WithdrawModel;

public class WithdrawPresenter implements WithdrawInterface.WithDrawPresenter {
	WithdrawInterface.WithdrawView withdrawView;

	public WithdrawPresenter(final WithdrawInterface.WithdrawView withdrawView) {
		this.withdrawView = withdrawView;
	}

	@Override
	public void handleWithdraw(final double money, final String note) {
		if (money < 1000) {
			this.withdrawView.setNotification(ErrorMessage.ERR400001);
		}
		new WalletModel().getMoneyOnce(Auth.getInstance().user().getUid(), new WalletInterface() {
			@Override
			public void dataIsLoaded(double money_now) {
				if (money > money_now) {
					withdrawView.setNotification(ErrorMessage.ERR410000);
				} else {
					(new UserModel()).show(Auth.getInstance().user().getUid(), new UserDetailsInterface() {
						@Override
						public void dataIsLoaded(User user) {
							Withdraw withdraw = new Withdraw(user.getEmail(), user.getUser(), money, note);

							(new WithdrawModel()).withdraw(withdraw);
						}
					});
				}
			}
		});
	}

	@Override
	public void checkBalance(final double money) {
		new WalletModel().getMoneyOnce(Auth.getInstance().user().getUid(), new WalletInterface() {
			@Override
			public void dataIsLoaded(double money_now) {
				if (money > money_now) {
					withdrawView.setNotification(ErrorMessage.ERR410000);
				} else {
					withdrawView.handleDialog();
				}
			}
		});
	}
}
