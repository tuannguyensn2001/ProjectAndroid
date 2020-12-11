package com.example.deluxe.Presenter.Chat;

import com.example.deluxe.Entity.LastMessage;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.Model.MessageInterface;
import com.example.deluxe.Interface.PresenterView.Chat.ChatUserInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.MessageModel;

import java.util.ArrayList;

public class ChatUserPresenter implements ChatUserInterface.ChatUserPresenter {

	private ChatUserInterface.ChatUserView chatUserInterfaceView;

	public ChatUserPresenter(ChatUserInterface.ChatUserView chatUserInterfaceView) {
		this.chatUserInterfaceView = chatUserInterfaceView;
	}

	@Override
	public void showData() {
		new MessageModel().getUserMessage(Auth.getInstance().user().getEmail(), new MessageInterface() {
			@Override
			public void getListMessage(ArrayList<LastMessage> list) {
				chatUserInterfaceView.handleIsHaveMessage(list.size() != 0);
				chatUserInterfaceView.setAdapter(list);
			}
		});
	}

	@Override
	public void getItem(int position) {
		new MessageModel().getItemMessage(Auth.getInstance().user().getEmail(), position, new MessageInterface.MessageItemInterface() {
			@Override
			public void getItem(LastMessage message) {
				chatUserInterfaceView.handleOnItemClick(new User(null, null, message.getEmail()));
			}
		});
	}


}