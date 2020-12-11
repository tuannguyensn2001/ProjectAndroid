package com.example.deluxe.View.Status;
<<<<<<< HEAD:app/src/main/java/com/example/deluxe/View/Status/DepositSuccessActivity.java
=======

import androidx.appcompat.app.AppCompatActivity;
>>>>>>> 6e52742c5d4d535db56eda368b46d114b2de4a74:app/src/main/java/com/example/deluxe/View/DepositSuccessActivity.java

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

<<<<<<< HEAD:app/src/main/java/com/example/deluxe/View/Status/DepositSuccessActivity.java
import androidx.appcompat.app.AppCompatActivity;

import com.example.deluxe.Interface.PresenterView.Status.DepositSuccessInterface;
import com.example.deluxe.Presenter.Status.DepositSuccessPresenter;
import com.example.deluxe.R;
import com.example.deluxe.View.MainActivity;
import com.example.deluxe.View.Transaction.DepositActivity;
=======
import com.example.deluxe.Interface.PresenterView.DepositSuccessInterface;
import com.example.deluxe.Presenter.Status.DepositSuccessPresenter;
import com.example.deluxe.R;
import com.example.deluxe.View.Transaction.DepositActivity;
import com.example.deluxe.View.MainActivity;
>>>>>>> 6e52742c5d4d535db56eda368b46d114b2de4a74:app/src/main/java/com/example/deluxe/View/DepositSuccessActivity.java

import java.text.DecimalFormat;

public class DepositSuccessActivity extends AppCompatActivity implements DepositSuccessInterface.DepositSuccessView {
	DepositSuccessInterface.DepositSuccessPresenter depositSuccessPresenter;

	TextView homeButton, continueDepositButton;
	TextView authBalance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deposit_success);

		init();

		homeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(MainActivity.class);
			}
		});

		continueDepositButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(DepositActivity.class);
			}
		});
	}

	private void init() {
		authBalance = findViewById(R.id.auth_balance);

		homeButton = findViewById(R.id.home_button);
		continueDepositButton = findViewById(R.id.continue_deposit_button);

		depositSuccessPresenter = new DepositSuccessPresenter(this);

	}

	@Override
	public void loadView(Class<? extends com.example.deluxe.Core.View> view) {
		Intent intent = new Intent(this, view);
		startActivity(intent);
		finish();
	}

	@Override
	public void setNotification(Enum e) {

	}

	@Override
	public void setAuthBalance(double authBalance) {
		this.authBalance.setText(new DecimalFormat("#,###,###").format(authBalance));
	}
}