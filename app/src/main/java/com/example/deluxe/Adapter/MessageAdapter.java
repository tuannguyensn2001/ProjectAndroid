package com.example.deluxe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deluxe.Entity.Message;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.R;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
	ArrayList<Message> listMessage;
	Context context;

	public MessageAdapter(ArrayList<Message> list, Context context) {
		this.listMessage = list;
		this.context = context;
	}

	public void setList(ArrayList<Message> list) {
		this.listMessage = list;
	}


	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		View itemView = layoutInflater.inflate(R.layout.component_chat_message_right, parent, false);
		if (viewType == 0) {
			itemView = layoutInflater.inflate(R.layout.component_chat_message_left, parent, false);
		}


		return new ViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.message.setText(listMessage.get(position).getContent());
	}

	@Override
	public int getItemCount() {
		return this.listMessage.size();
	}

	@Override
	public int getItemViewType(int position) {
		if (listMessage.get(position).getEmailSender().equals(Auth.getInstance().user().getEmail()))
			return 1;
		return 0;
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		TextView message;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			message = (TextView) itemView.findViewById(R.id.message);

		}
	}
}