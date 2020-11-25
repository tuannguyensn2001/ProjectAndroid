package com.example.deluxe.View.Components;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.PresenterView.Components.ConfirmPasswordInterface;
import com.example.deluxe.Presenter.Components.ConfirmPasswordPresenter;
import com.example.deluxe.R;
import com.example.deluxe.View.Transaction.TransferActivity;
import com.example.deluxe.View.Transaction.WithdrawActivity;

public class ConfirmPasswordDialog extends AlertDialog.Builder implements ConfirmPasswordInterface.ConfirmPasswordView {
	Context parentActivity;

	EditText password;
	CheckBox showPasswordButton;
	TextView authUsername;
	TextView authEmail;

	ConfirmPasswordInterface.ConfirmPasswordPresenter confirmPasswordPresenter;

	boolean isCorrect;

	public ConfirmPasswordDialog(Context context) {
		super(context);

		this.parentActivity = context;

		init();
	}

	private void init() {
//		Presenter
		confirmPasswordPresenter = new ConfirmPasswordPresenter(this);

//		Layout va cac ban
		LayoutInflater inflater = LayoutInflater.from(parentActivity);
		View alertLayout = inflater.inflate(R.layout.layout_password_input, null);

		this.authUsername = alertLayout.findViewById(R.id.auth_username);
		this.authEmail = alertLayout.findViewById(R.id.auth_email);

		this.password = alertLayout.findViewById(R.id.password_input);
		this.showPasswordButton = alertLayout.findViewById(R.id.show_password_button);

		this.showPasswordButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)
					password.setTransformationMethod(null);
				else
					password.setTransformationMethod(PasswordTransformationMethod.getInstance());
			}
		});

//		Dialog
		this.setTitle("Xac nhan mat khau");
		this.setMessage("Vui lòng nhập mật khẩu để chắc chắn rằng đây là bạn chứ không phải con điên nào hết.");
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
				String passwordInput = password.getText().toString();
				if (!Rules.required(passwordInput))
					setNotification(ErrorMessage.ERR500000);
				else if (!Rules.min(passwordInput, 6))
					setNotification(ErrorMessage.ERR500001);
				else
					confirmPasswordPresenter.handleConfirmUser(passwordInput);
			}
		});
	}

	@Override
	public void loadView(Class<? extends com.example.deluxe.Core.View> view) {

	}

	@Override
	public void setNotification(Enum e) {

	}

	@Override
	public void handleIsUserCorrect(boolean b) {
		setCorrect(b);
		if (parentActivity instanceof WithdrawActivity) {
			((WithdrawActivity) parentActivity).handleIsUserCorrect(b);
		} else if (parentActivity instanceof TransferActivity)
			((TransferActivity) parentActivity).handleIsUserCorrect(b);
	}

	@Override
	public void setUserInfo(User user) {
		authUsername.setText(user.getUser());
		authEmail.setText(user.getEmail());
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean correct) {
		isCorrect = correct;
	}
}
