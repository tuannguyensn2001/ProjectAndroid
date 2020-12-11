package com.example.deluxe.Presenter.Chat;

import com.example.deluxe.Entity.Message;
import com.example.deluxe.Entity.Transfer;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Enum.SuccessMessage;
import com.example.deluxe.Interface.Model.MessageInterface;
import com.example.deluxe.Interface.Model.TransferFirebase;
import com.example.deluxe.Interface.Model.UserDetailsInterface;
import com.example.deluxe.Interface.Model.WalletInterface;
import com.example.deluxe.Interface.PresenterView.Chat.ChatInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.MessageModel;
import com.example.deluxe.Model.TransferModel;
import com.example.deluxe.Model.UserModel;
import com.example.deluxe.Model.WalletModel;

import java.util.ArrayList;
import java.util.Date;

public class ChatPresenter implements ChatInterface.ChatPresenter {

	private final ChatInterface.ChatView chatView;
	private final MessageModel messageModel;

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

	@Override
	public void handleTransfer(final User user, final double money, final String message) {
		new WalletModel().getMoneyOnce(Auth.getInstance().user().getUid(), new WalletInterface() {
			@Override
			public void dataIsLoaded(double money_now) {
				if (money > money_now) {
				} else {
					final Transfer transfer = new Transfer(Auth.getInstance().user().getEmail(), user.getEmail(), money, message);
					(new TransferModel()).transfer(transfer, new TransferFirebase() {
						@Override
						public void success(SuccessMessage successMessage) {
						}

						@Override
						public void failed(ErrorMessage errorMessage) {
						}
					});
				}
			}
		});

	}

	@Override
	public void updateTransaction(Message message) {
		if (message.getStatus() == 1 && message.getType() == 1) {
			User user = new User(null, null, message.getEmailSender());
			handleTransfer(user, message.getSecondMoney(), message.getContent());
		}
		new MessageModel().updateMessage(message);
	}


}