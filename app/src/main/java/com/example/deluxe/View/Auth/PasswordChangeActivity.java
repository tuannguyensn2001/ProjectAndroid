package com.example.deluxe.View.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.deluxe.Core.View;
import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Enum.SuccessMessage;
import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.PresenterView.Auth.PasswordChangeInterface;
import com.example.deluxe.Presenter.Auth.PasswordChangePresenter;
import com.example.deluxe.R;

public class PasswordChangeActivity extends AppCompatActivity implements PasswordChangeInterface.PasswordChangeView {
	PasswordChangeInterface.PasswordChangePresenter passwordChangePresenter;
	private TextView passwordCurrent, passwordNew, passwordCheckNew;
	private Button changePass;
	private TextView notiText;
	private String passwordCurrentInput, passwordNewInput, passwordCheckNewInput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password_change);
		init();
		changePass.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {

				passwordCurrentInput = passwordCurrent.getText().toString();
				passwordNewInput = passwordNew.getText().toString();
				passwordCheckNewInput = passwordCheckNew.getText().toString();

				boolean[] list = {
						Rules.required(passwordCurrentInput),
						Rules.required(passwordNewInput),
						Rules.required(passwordCheckNewInput),

						Rules.min(passwordCurrentInput, 6),
						Rules.min(passwordNewInput, 6),
						Rules.min(passwordCheckNewInput, 6),
						passwordCheckNewInput.equals(passwordNewInput),

						Rules.isPassword(passwordCurrentInput),
						Rules.isPassword(passwordNewInput),
						Rules.isPassword(passwordCheckNewInput),

						!passwordNewInput.equals(passwordCurrentInput),
				};
				boolean check = validate(list);

				if (check) {
					passwordChangePresenter.handlePassChange(passwordCurrentInput, passwordNewInput, passwordCheckNewInput);
					setSubmitable(false);
				} else {
					if (!list[0]) {
						passwordCurrent.setError(ErrorMessage.ERR000000.getValue());
					} else if (!list[3]) {
						passwordCurrent.setError(ErrorMessage.ERR000002.getValue());
					} else if (!list[7]) {
						passwordCurrent.setError(ErrorMessage.ERR000004.getValue());
					}
					if (!list[1]) {
						passwordNew.setError(ErrorMessage.ERR000000.getValue());
					} else if (!list[4]) {
						passwordNew.setError(ErrorMessage.ERR000002.getValue());
					} else if (!list[8]) {
						passwordNew.setError(ErrorMessage.ERR000004.getValue());
					}
					if (!list[2]) {
						passwordCheckNew.setError(ErrorMessage.ERR000000.getValue());
					} else if (!list[5]) {
						passwordCheckNew.setError(ErrorMessage.ERR000002.getValue());
					} else if (!list[6]) {
						passwordCheckNew.setError(ErrorMessage.ERR000001.getValue());
					} else if (!list[9]) {
						passwordCheckNew.setError(ErrorMessage.ERR000004.getValue());
					}
					if (!list[10]) {
						passwordNew.setError(ErrorMessage.ERR000004.getValue());
					}
				}
			}

		});
		addTextChanged(passwordCurrent);
		addTextChanged(passwordNew);
		addTextChanged(passwordCheckNew);
	}

	private void init() {
		this.notiText = findViewById(R.id.notification_text);

		this.passwordCurrent = findViewById(R.id.password_input);
		this.passwordNew = findViewById(R.id.password_new_input);
		this.passwordCheckNew = findViewById(R.id.password_new_check_input);
		this.changePass = findViewById(R.id.submit_button);

		this.passwordChangePresenter = new PasswordChangePresenter(this);
	}

	public void addTextChanged(final TextView textView) {
		textView.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				textView.setError(null);
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}

	@Override
	public void loadView(Class<? extends View> view) {
		Intent intent = new Intent(this, view);
		startActivity(intent);
	}

	@Override
	public void setNotification(Enum e) {
		if (e instanceof ErrorMessage) {
			notiText.setTextColor(ContextCompat.getColor(this, R.color.light_error));
			notiText.setText(((ErrorMessage) e).getValue());
		} else {
			notiText.setTextColor(ContextCompat.getColor(this, R.color.light_mainColor));
			notiText.setText(((SuccessMessage) e).getValue());
		}
		notiText.setVisibility(android.view.View.VISIBLE);
	}

	public boolean validate(boolean[] list) {
		for (boolean i : list) {
			if (!i) return false;
		}
		return true;
	}

	@Override
	public void setSubmitable(boolean can) {
		changePass.setClickable(can);
	}
}
