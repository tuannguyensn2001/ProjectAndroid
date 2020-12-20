package com.example.deluxe.View.Chat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deluxe.Adapter.MessageAdapter;
import com.example.deluxe.Entity.Message;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Helper.ConvertData;
import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.PresenterView.Chat.ChatInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Presenter.Chat.ChatPresenter;
import com.example.deluxe.R;
import com.example.deluxe.View.Components.SendTransactionDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity implements ChatInterface.ChatView {

	private ChatInterface.ChatPresenter chatPresenter;
	private boolean isCanSendMessage;


	private String emailReceiver;
	private String emailSender;
	private ImageView sendMessageButton, sendTransactionButton;
	private EditText content;
	private RecyclerView recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		transparentStatusAndNavigation(this);

		this.init();

		this.chatPresenter.getListMessage(new Message(this.emailSender, this.emailReceiver, null));

		this.initBehaviour();
	}

	private void initBehaviour() {

		content.setText(null);
		this.content.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				setCanSendMessage(!Rules.isSpace(content.getText().toString()));
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		this.sendMessageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Message message = new Message(emailSender, emailReceiver, ConvertData.normalizeString(content.getText().toString()));

				chatPresenter.handleInputMessage(message);
				content.setText(null);
			}
		});

		this.sendTransactionButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleDialog();
			}
		});

		setCanSendMessage(false);
	}

	private void transparentStatusAndNavigation(Activity activity) {
		Window win = activity.getWindow();

		win.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
		WindowManager.LayoutParams winParams = win.getAttributes();
		winParams.flags &= ~(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
				| WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		win.setAttributes(winParams);

		win.setStatusBarColor(Color.TRANSPARENT);
		win.setNavigationBarColor(Color.TRANSPARENT);
	}

	public void init() {
		this.chatPresenter = new ChatPresenter(this);

		User user = (User) getIntent().getSerializableExtra("User");
		if (user != null) {
			this.emailReceiver = user.getEmail();
			this.chatPresenter.getReceiverInformation(emailReceiver);
		}

		this.emailSender = Auth.getInstance().user().getEmail();
		this.sendMessageButton = findViewById(R.id.send_message_button);
		this.sendTransactionButton = findViewById(R.id.send_transaction_button);
		this.content = findViewById(R.id.message_input);

		this.recyclerView = findViewById(R.id.list_message);
		this.recyclerView.setHasFixedSize(true);
		this.recyclerView.setItemAnimator(new DefaultItemAnimator());
	}

	public void setCanSendMessage(boolean canSendMessage) {
		this.isCanSendMessage = canSendMessage;

		this.sendMessageButton.setEnabled(canSendMessage);
		this.sendTransactionButton.setEnabled(!isCanSendMessage);

		if (isCanSendMessage) {
			this.sendTransactionButton.setVisibility(View.INVISIBLE);
			this.sendMessageButton.setVisibility(View.VISIBLE);
		} else {
			this.sendMessageButton.setVisibility(View.INVISIBLE);
			this.sendTransactionButton.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void handleSendTransactionButton() {

	}

	@Override
	public void handleDialog() {
		new SendTransactionDialog(this);
	}

	@Override
	public void setAdapter(ArrayList<Message> list) {
		MessageAdapter messageAdapter = new MessageAdapter(list, this);

		this.recyclerView.setAdapter(messageAdapter);
	}

	@Override
	public void loadView(Class<? extends com.example.deluxe.Core.View> view) {
		Intent intent = new Intent(this, view);
		startActivity(intent);
	}

	@Override
	public void setNotification(Enum e) {

	}

	@Override
	public void setReceiverInformation(User user) {
		((TextView) findViewById(R.id.action_bar_title)).setText(user.getUser());
		((TextView) findViewById(R.id.action_bar_subtitle)).setText(user.getEmail());
		Picasso.get().load(user.getAvatar()).into((ImageView) findViewById(R.id.profile_picture));
	}

	@Override
	public void handleIsUserCorrect(boolean b) {
	}

	@Override
	public void sendTransaction(boolean isTransfer, double moneyInputNumber, String messageInput) {
		Message message;
		if (isTransfer) {
			message = new Message(-1, 1, moneyInputNumber, emailSender, emailReceiver, ConvertData.normalizeString(messageInput));
		} else {
			message = new Message(1, 0, moneyInputNumber, emailSender, emailReceiver, ConvertData.normalizeString(messageInput));
		}

		chatPresenter.handleInputMessage(message);
		content.setText(null);

		if (isTransfer) {
			User user = new User(null, null, emailReceiver);
			chatPresenter.handleTransfer(user, moneyInputNumber, messageInput);
		}
	}

	@Override
	public void updateTransaction(Message message) {
		message.setEmailReceiver(emailSender);
		message.setEmailSender(emailReceiver);
		chatPresenter.updateTransaction(message);
	}
}