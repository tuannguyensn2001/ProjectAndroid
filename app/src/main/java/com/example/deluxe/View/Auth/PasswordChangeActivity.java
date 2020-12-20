package com.example.deluxe.View.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.ProgressBar;
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
	PasswordChangeInterface.PasswordChangePresenter passwordChange;
	ProgressBar progressBar;
	private TextView passwordcurrent, passwordnew, passwordchecknew;
	private Button changePass;
	private TextView notiText;
	private String passwordcurrentInput, passwordnewInput, passwordchecknewInput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_password_change);
		init();
		changePass.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {

				passwordcurrentInput = passwordcurrent.getText().toString();
				passwordnewInput = passwordnew.getText().toString();
				passwordchecknewInput = passwordchecknew.getText().toString();

				boolean[] list = {
						Rules.required(passwordcurrentInput),
						Rules.required(passwordnewInput),
						Rules.required(passwordchecknewInput),

						Rules.min(passwordcurrentInput, 6),
						Rules.min(passwordnewInput, 6),
						Rules.min(passwordchecknewInput, 6),
						passwordchecknewInput.equals(passwordnewInput),

						Rules.isPassword(passwordcurrentInput),
						Rules.isPassword(passwordnewInput),
						Rules.isPassword(passwordchecknewInput),
				};
				boolean check = validate(list);

				if (check) {
					passwordChange.handlePassChange(passwordcurrentInput, passwordnewInput, passwordchecknewInput);
					setSubmitable(false);
				} else {
					if (!list[0]) {
						passwordcurrent.setError(ErrorMessage.ERR000000.getValue());
					} else if (!list[3]) {
						passwordcurrent.setError(ErrorMessage.ERR000002.getValue());
					} else if (!list[7]) {
						passwordcurrent.setError(ErrorMessage.ERR000004.getValue());
					}
					if (!list[1]) {
						passwordnew.setError(ErrorMessage.ERR000000.getValue());
					} else if (!list[4]) {
						passwordnew.setError(ErrorMessage.ERR000002.getValue());
					} else if (!list[8]) {
						passwordnew.setError(ErrorMessage.ERR000004.getValue());
					}
					if (!list[2]) {
						passwordchecknew.setError(ErrorMessage.ERR000000.getValue());
					} else if (!list[5]) {
						passwordchecknew.setError(ErrorMessage.ERR000002.getValue());
					} else if (!list[6]) {
						passwordchecknew.setError(ErrorMessage.ERR000001.getValue());
					} else if (!list[9]) {
						passwordchecknew.setError(ErrorMessage.ERR000004.getValue());
					}
				}
			}

		});
		addTextChanged(passwordcurrent);
		addTextChanged(passwordnew);
		addTextChanged(passwordchecknew);

	}

	private void init() {
		this.passwordcurrent = findViewById(R.id.password_current);
		this.passwordnew = findViewById(R.id.password_new);
		this.passwordchecknew = findViewById(R.id.password_check_new);
		this.changePass = findViewById(R.id.password_change_button);

		this.passwordChange = new PasswordChangePresenter(this);
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
