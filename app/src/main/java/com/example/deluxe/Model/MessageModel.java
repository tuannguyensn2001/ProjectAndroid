package com.example.deluxe.Model;

import android.util.Log;

import androidx.annotation.NonNull;

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

public class MessageModel {

	DatabaseReference ref;

	public MessageModel() {
		this.ref = FirebaseDatabase.getInstance().getReference().child("message");
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


				messageInterface.getListMessage(lastMessage);
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
				Log.e("message", "reload");
				listMessage.clear();

				for (DataSnapshot item : snapshot.getChildren()) {
					Message message = item.getValue(Message.class);

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

}