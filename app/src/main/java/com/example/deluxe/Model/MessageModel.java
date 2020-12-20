package com.example.deluxe.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.deluxe.API.CoreAPI;
import com.example.deluxe.API.MessageAPI;
import com.example.deluxe.Entity.LastMessage;
import com.example.deluxe.Entity.Message;
import com.example.deluxe.Interface.Model.MessageInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MessageModel {

	DatabaseReference ref;
	Retrofit retrofit;
	MessageAPI messageAPI;

	public MessageModel() {
		this.ref = FirebaseDatabase.getInstance().getReference().child("message");
		this.retrofit = CoreAPI.build();
		this.messageAPI = retrofit.create(MessageAPI.class);
	}

	public void getUserMessage(final String email, final MessageInterface messageInterface) {

		this.ref.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				ArrayList<Message> listMessage = new ArrayList<>();

				for (DataSnapshot item : snapshot.getChildren()) {
					Message message = item.getValue(Message.class);

					if (message.getEmailReceiver().equals(email) || message.getEmailSender().equals(email)) {
						listMessage.add(message);
					}
				}

				Collections.reverse(listMessage);

				ArrayList<String> partner = new ArrayList<>();

				for (Message index : listMessage) {
					String partnerEmail = index.getEmailSender().equals(email) ? index.getEmailReceiver() : index.getEmailSender();
					if (!partner.contains(partnerEmail)) partner.add(partnerEmail);
				}

				ArrayList<LastMessage> lastMessage = new ArrayList<>();

				for (String item : partner) {
					for (Message index : listMessage) {
						if (index.getEmailSender().equals(email) && index.getEmailReceiver().equals(item) ||
								index.getEmailSender().equals(item) && index.getEmailReceiver().equals(email)) {
							lastMessage.add(new LastMessage(item, index.getContent(), null));
							break;
						}
					}
				}

				final ArrayList<LastMessage> lastMessageData = new ArrayList<>();

				ListMessage list = new ListMessage();
				list.setLastMessages(lastMessage);

				Call<ArrayList<LastMessage>> call = messageAPI.getDetailMessage(list);

				call.enqueue(new Callback<ArrayList<LastMessage>>() {
					@Override
					public void onResponse(Call<ArrayList<LastMessage>> call, Response<ArrayList<LastMessage>> response) {
						ArrayList<LastMessage> newList = response.body();
						messageInterface.getListMessage(newList);
					}

					@Override
					public void onFailure(Call<ArrayList<LastMessage>> call, Throwable t) {
						Log.e("e", "E");
					}
				});


			}


			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
	}

	public void getItemMessage(final String email, final int position, final MessageInterface.MessageItemInterface messageItemInterface) {

		this.ref.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				ArrayList<Message> listMessage = new ArrayList<>();

				for (DataSnapshot item : snapshot.getChildren()) {
					Message message = item.getValue(Message.class);

					if (message.getEmailReceiver().equals(email) || message.getEmailSender().equals(email)) {
						listMessage.add(message);
					}
				}

				Collections.reverse(listMessage);

				ArrayList<String> partner = new ArrayList<>();

				for (Message index : listMessage) {
					String partnerEmail = index.getEmailSender().equals(email) ? index.getEmailReceiver() : index.getEmailSender();
					if (!partner.contains(partnerEmail)) partner.add(partnerEmail);
				}

				ArrayList<LastMessage> lastMessage = new ArrayList<>();

				for (String item : partner) {
					for (Message index : listMessage) {
						if (index.getEmailSender().equals(email) && index.getEmailReceiver().equals(item) ||
								index.getEmailSender().equals(item) && index.getEmailReceiver().equals(email)) {
							lastMessage.add(new LastMessage(item, index.getContent(), null));
							break;
						}
					}
				}

				messageItemInterface.getItem(lastMessage.get(position));
			}


			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
	}

	public void add(Message message) {
		String key = this.ref.push().getKey();
		this.ref.child(key).setValue(message);
	}

	public void getDetailMessage(Message message, final MessageInterface.MessageListInterface messageListInterface) {
		final ArrayList<Message> listMessage = new ArrayList<>();
		final String emailSender = message.getEmailSender();
		final String emailReceiver = message.getEmailReceiver();
		this.ref.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {

				listMessage.clear();

				for (DataSnapshot item : snapshot.getChildren()) {
					Message message = item.getValue(Message.class);
					message.setId(item.getKey());

					if (message.getEmailSender().equals(emailSender) && message.getEmailReceiver().equals(emailReceiver) ||
							message.getEmailSender().equals(emailReceiver) && message.getEmailReceiver().equals(emailSender)
					) {
						listMessage.add(message);
					}
				}

				messageListInterface.getList(listMessage);


			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
	}

	public void updateMessage(final Message message) {
		final String id = message.getId();
		final int status = message.getStatus();
		final double secondMoney = message.getSecondMoney();

		this.ref.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				Message message1 = snapshot.getValue(Message.class);
				message1.setStatus(status);
				message1.setSecondMoney(secondMoney);
				ref.child(id).setValue(message1);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
	}

	public class ListMessage {
		private List<LastMessage> lastMessages;

		public ListMessage() {
		}

		public List<LastMessage> getLastMessages() {
			return lastMessages;
		}

		public void setLastMessages(List<LastMessage> lastMessages) {
			this.lastMessages = lastMessages;
		}


	}

}