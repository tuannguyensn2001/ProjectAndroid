package com.example.deluxe.View.Components;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.PresenterView.Components.SendTransactionInterface;
import com.example.deluxe.Presenter.Components.ConfirmPasswordPresenter;
import com.example.deluxe.Presenter.Components.SendTransactionPresenter;
import com.example.deluxe.R;

import java.text.NumberFormat;

public class SendTransactionDialog extends ConfirmPasswordDialog implements SendTransactionInterface.SendTransactionView {
	TextView sendMoneyTab, receiveMoneyTab;
	EditText money, message;
	Button submitButton;

	String moneyInput, messageInput, passwordInput;
	double moneyInputNumber;
	boolean isTransfer;

	SendTransactionPresenter sendTransactionPresenter;

	public SendTransactionDialog(Context context) {
		super(context);
		sendTransactionPresenter = new SendTransactionPresenter(this);
	}

	@Override
	void initDialog() {
		this.setTitle("Gui giao dich qua tin nhan");
		this.setMessage("Lam thu di con dien");
		this.setView(alertLayout);
		this.setCancelable(true);
		this.setNegativeButton("Huy", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		this.setPositiveButton("Gui", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		this.dialog = this.create();
		dialog.show();
		dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleSendButton();
			}
		});
	}

	private void handleSendButton() {
		this.notiText.setVisibility(View.INVISIBLE);

		moneyInput = money.getText().toString();
		messageInput = message.getText().toString();

		boolean check = validateInput();
		if (check) {
			moneyInputNumber = Double.parseDouble(moneyInput.replaceAll("[,.]", ""));
			if (isTransfer)
				sendTransactionPresenter.checkBalance(moneyInputNumber);
			else
				handleSendTransaction();
		}
	}

	private boolean validateInput() {
		boolean check = false;
		if (!Rules.required(moneyInput)) {
			check = false;
			money.setError(ErrorMessage.ERR300000.getValue());
		} else if (!Rules.min(moneyInput, 4)) {
			check = false;
			money.setError(ErrorMessage.ERR300001.getValue());
		} else {
			check = true;
		}

		if (isTransfer) {
			passwordInput = password.getText().toString();
			if (!Rules.required(passwordInput)) {
				check = false;
				password.setError(ErrorMessage.ERR500000.getValue());
			} else if (!Rules.min(passwordInput, 6)) {
				check = false;
				password.setError(ErrorMessage.ERR500001.getValue());
			} else if (!Rules.isPassword(passwordInput)) {
				password.setError(ErrorMessage.ERR500002.getValue());
				check = false;
			} else {
				check = true;
			}
		}

		return check;
	}

	@Override
	void initView() {
//		Presenter
		confirmPasswordPresenter = new ConfirmPasswordPresenter(this);

//		Layout va cac ban
		LayoutInflater inflater = LayoutInflater.from(parentActivity);
		alertLayout = inflater.inflate(R.layout.component_message_transaction_dialog, null);

		this.sendMoneyTab = alertLayout.findViewById(R.id.send_money_tab);
		this.receiveMoneyTab = alertLayout.findViewById(R.id.receive_money_tab);

		this.notiText = alertLayout.findViewById(R.id.notification_text);
		this.password = alertLayout.findViewById(R.id.password_input);
		this.showPasswordButton = alertLayout.findViewById(R.id.show_password_button);
		this.money = alertLayout.findViewById(R.id.money_input);
		this.message = alertLayout.findViewById(R.id.message_input);

//		this.submitButton = alertLayout.findViewById(R.id.submit_button);

		this.money.addTextChangedListener(new TextWatcher() {
			private String current = "";

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (!s.toString().equals("")) {
					if (!s.toString().equals(current)) {
						String cleanString = s.toString().replaceAll("[,.]", "");
						double parsed = Double.parseDouble(cleanString);
						String formatted = NumberFormat.getInstance().format((parsed));

						current = formatted;
						money.setText(formatted);
						money.setSelection(formatted.length());
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		this.showPasswordButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)
					password.setTransformationMethod(null);
				else
					password.setTransformationMethod(PasswordTransformationMethod.getInstance());
			}
		});

		this.sendMoneyTab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				isTransfer = true;
				alertLayout.findViewById(R.id.hidden_view).setVisibility(View.VISIBLE);

				sendMoneyTab.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
				receiveMoneyTab.setBackgroundTintMode(PorterDuff.Mode.SCREEN);

				sendMoneyTab.setTextColor(ContextCompat.getColor(alertLayout.getContext(), R.color.light_background));
				receiveMoneyTab.setTextColor(receiveMoneyTab.getBackgroundTintList().getDefaultColor());
			}
		});

		this.receiveMoneyTab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				isTransfer = false;
				alertLayout.findViewById(R.id.hidden_view).setVisibility(View.GONE);

				sendMoneyTab.setBackgroundTintMode(PorterDuff.Mode.SCREEN);
				receiveMoneyTab.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);

				sendMoneyTab.setTextColor(sendMoneyTab.getBackgroundTintList().getDefaultColor());
				receiveMoneyTab.setTextColor(ContextCompat.getColor(alertLayout.getContext(), R.color.light_background));
			}
		});
		this.sendMoneyTab.performClick();
	}


	@Override
	public void setUserInfo(User user) {

	}

	@Override
	public void handleIsEnoughMoney(boolean b) {
		if (b)
			sendTransactionPresenter.handleConfirmUser(passwordInput);
		else
			setNotification(ErrorMessage.ERR310000);
	}

	@Override
	public void handleSendTransaction() {
		this.dialog.dismiss();
		((ViewUseSendTransaction) parentActivity).sendTransaction(isTransfer, moneyInputNumber, messageInput);
	}
}
