package com.example.deluxe.View.Components;

import android.content.Context;
import android.view.View;

import com.example.deluxe.Entity.Message;
import com.example.deluxe.Helper.ConvertData;

public class ReplyTransactionDialog extends SendTransactionDialog {
	double firstMoney;
	String id;

	public ReplyTransactionDialog(Context context, double firstMoney, String id) {
		super(context);
		this.firstMoney = firstMoney;
		this.money.setText(ConvertData.moneyToString(firstMoney));
		this.id = id;
	}

	@Override
	void initView() {
		super.initView();

		this.sendMoneyTab.setVisibility(View.GONE);
		this.receiveMoneyTab.setVisibility(View.GONE);

		this.message.setVisibility(View.GONE);
	}

	@Override
	public void handleSendTransaction() {
		this.dialog.dismiss();
		Message sMessage = new Message();
		sMessage.setId(this.id);
		sMessage.setType(1);
		sMessage.setStatus(1);
		sMessage.setSecondMoney(moneyInputNumber);
		((ViewUseSendTransaction) parentActivity).updateTransaction(sMessage);
	}
}
