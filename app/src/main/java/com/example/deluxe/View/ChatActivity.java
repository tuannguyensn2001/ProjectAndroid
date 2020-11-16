package com.example.deluxe.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.deluxe.Adapter.MessageAdapter;
import com.example.deluxe.Entity.Message;
import com.example.deluxe.Interface.PresenterView.ChatInterface;
import com.example.deluxe.Presenter.ChatPresenter;
import com.example.deluxe.R;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity implements ChatInterface.ChatView {

	private ChatInterface.ChatPresenter chatPresenter;
	private String emailReceiver;
	private String emailSender;
	private Button submitButton;
	private TextView receiver;
	private EditText content;
	private RecyclerView recyclerView;
	private MessageAdapter messageAdapter;
	private ArrayList<Message> listMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);

		this.init();

		this.receiver.setText(this.emailReceiver);

		this.chatPresenter.getListMessage(new Message(this.emailSender, this.emailReceiver, null));

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
		this.emailReceiver = getIntent().getStringExtra("emailReceiver");
		this.emailSender = getIntent().getStringExtra("emailSender");
		this.submitButton = findViewById(R.id.send_message_button);
		this.receiver = findViewById(R.id.email_receiver);
		this.content = findViewById(R.id.message_input);

		this.recyclerView = findViewById(R.id.list_message);
		this.recyclerView.setHasFixedSize(false);
		LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		this.recyclerView.setLayoutManager(layoutManager);

	}

	@Override
	public void setAdapter(ArrayList<Message> list) {

		this.messageAdapter = new MessageAdapter(list, getApplicationContext());
		this.recyclerView.setAdapter(messageAdapter);
	}

	@Override
	public void loadView(Class view) {

	}

	@Override
	public void setNotification(Enum e) {

	}
}