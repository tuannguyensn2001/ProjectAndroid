package com.example.deluxe.View.Components;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Enum.SuccessMessage;
import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.PresenterView.Components.ConfirmPasswordInterface;
import com.example.deluxe.Presenter.Components.ConfirmPasswordPresenter;
import com.example.deluxe.R;

public class ConfirmPasswordDialog extends AlertDialog.Builder implements ConfirmPasswordInterface.ConfirmPasswordView {
	Context parentActivity;

	EditText password;
	CheckBox showPasswordButton;
	TextView authUsername;
	TextView authEmail;
	TextView notiText;

	AlertDialog dialog;
	View alertLayout;

	ConfirmPasswordInterface.ConfirmPasswordPresenter confirmPasswordPresenter;

	public ConfirmPasswordDialog(Context context) {
		super(context);

		this.parentActivity = context;

		initView();

		initDialog();
	}

	void initDialog() {
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
			}
		});

		this.dialog = this.create();
		dialog.show();
		dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String passwordInput = password.getText().toString();
				if (!Rules.required(passwordInput)) {
					password.setError(ErrorMessage.ERR500000.getValue());
				} else if (!Rules.min(passwordInput, 6)) {
					password.setError(ErrorMessage.ERR500001.getValue());
				} else if (!Rules.isPassword(passwordInput)) {
					password.setError(ErrorMessage.ERR500002.getValue());
				} else {
					confirmPasswordPresenter.handleConfirmUser(passwordInput);
				}
			}
		});
	}

	void initView() {
//		Presenter
		confirmPasswordPresenter = new ConfirmPasswordPresenter(this);

//		Layout va cac ban
		LayoutInflater inflater = LayoutInflater.from(parentActivity);
		alertLayout = inflater.inflate(R.layout.layout_password_input, null);

		this.authUsername = alertLayout.findViewById(R.id.auth_username);
		this.authEmail = alertLayout.findViewById(R.id.auth_email);

		this.notiText = alertLayout.findViewById(R.id.notification_text);
		this.password = alertLayout.findViewById(R.id.password_input);
		this.showPasswordButton = alertLayout.findViewById(R.id.show_password_button);

		password.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				notiText.setVisibility(View.INVISIBLE);
				password.setError(null);
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
	}

	@Override
	public void loadView(Class<? extends com.example.deluxe.Core.View> view) {

	}

	@Override
	public void setNotification(Enum e) {
		if (e instanceof ErrorMessage) {
			notiText.setTextColor(ContextCompat.getColor(this.getContext(), R.color.light_error));
			notiText.setText(((ErrorMessage) e).getValue());
		} else {
			notiText.setTextColor(ContextCompat.getColor(this.getContext(), R.color.light_mainColor));
			notiText.setText(((SuccessMessage) e).getValue());
		}
		notiText.setVisibility(View.VISIBLE);
	}

	@Override
	public void handleIsUserCorrect(boolean b) {
		if (b)
			dialog.dismiss();
		((ViewUseCheckPasswordDialog) parentActivity).handleIsUserCorrect(b);
	}

	@Override
	public void setUserInfo(User user) {
		authUsername.setText(user.getUser());
		authEmail.setText(user.getEmail());
	}

}
