package com.example.deluxe.View.Chat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deluxe.Adapter.MessageAdapter;
import com.example.deluxe.Entity.Message;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.PresenterView.Chat.ChatInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Presenter.Chat.ChatPresenter;
import com.example.deluxe.R;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity implements ChatInterface.ChatView {

	private ChatInterface.ChatPresenter chatPresenter;
	private String emailReceiver;
	private String emailSender;
	private TextView submitButton;
	private EditText content;
	private RecyclerView recyclerView;
	private MessageAdapter messageAdapter;
	private ArrayList<Message> listMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);

		this.init();

		this.chatPresenter.getListMessage(new Message(this.emailSender, this.emailReceiver, null));

		content.setText(null);
		this.content.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				submitButton.setEnabled(!Rules.isSpace(content.getText().toString()));
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		this.submitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Message message = new Message(emailSender, emailReceiver, content.getText().toString());

				chatPresenter.handleInputMessage(message);
				content.setText(null);
			}
		});
	}

	public void init() {
		this.listMessage = new ArrayList<>();
		this.chatPresenter = new ChatPresenter(this);

		User user = (User) getIntent().getSerializableExtra("User");
		if (user != null)
			this.emailReceiver = user.getEmail();

		this.emailSender = Auth.getInstance().user().getEmail();
		this.submitButton = findViewById(R.id.send_message_button);
		this.content = findViewById(R.id.message_input);

		((TextView) findViewById(R.id.action_bar_title)).setText(this.emailReceiver);
		findViewById(R.id.action_bar_title).setClickable(true);
		findViewById(R.id.action_bar_title).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		this.recyclerView = findViewById(R.id.list_message);
		this.recyclerView.setHasFixedSize(false);
		LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		layoutManager.setStackFromEnd(true);
		this.recyclerView.setLayoutManager(layoutManager);

	}

	@Override
	public void setAdapter(ArrayList<Message> list) {

		this.messageAdapter = new MessageAdapter(list, getApplicationContext());
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

}