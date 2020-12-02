package com.example.deluxe.Presenter.Chat;

import com.example.deluxe.Entity.Message;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.Model.MessageInterface;
import com.example.deluxe.Interface.Model.UserDetailsInterface;
import com.example.deluxe.Interface.PresenterView.Chat.ChatInterface;
import com.example.deluxe.Model.MessageModel;
import com.example.deluxe.Model.UserModel;

import java.util.ArrayList;
import java.util.Date;

public class ChatPresenter implements ChatInterface.ChatPresenter {

	private ChatInterface.ChatView chatView;
	private MessageModel messageModel;

	public ChatPresenter(ChatInterface.ChatView chatView) {
		this.chatView = chatView;
		this.messageModel = new MessageModel();
	}

	@Override
	public void handleInputMessage(Message message) {
		message.setCreated_at(new Date().toString());
		message.setUpdated_at(new Date().toString());
		this.messageModel.add(message);

	}

	@Override
	public void getListMessage(Message message) {
		this.messageModel.getDetailMessage(message, new MessageInterface.MessageListInterface() {
			@Override
			public void getList(ArrayList<Message> list) {
				chatView.setAdapter(list);
			}
		});
	}

	@Override
	public void getReceiverInformation(String emailReceiver) {
		new UserModel().getInfoEmail(emailReceiver, new UserDetailsInterface() {
			@Override
			public void dataIsLoaded(User user) {
				chatView.setReceiverInformation(user);
			}
		});
	}


}