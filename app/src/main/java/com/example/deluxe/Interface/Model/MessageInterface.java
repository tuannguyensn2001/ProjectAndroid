package com.example.deluxe.Interface.Model;

import com.example.deluxe.Entity.LastMessage;
import com.example.deluxe.Entity.Message;

import java.util.ArrayList;

public interface MessageInterface {

	void getListMessage(ArrayList<LastMessage> list);

	public interface MessageItemInterface {
		void getItem(LastMessage message);
	}

	public interface MessageListInterface {
		void getList(ArrayList<Message> list);
	}
}