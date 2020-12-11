package com.example.deluxe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deluxe.Entity.LastMessage;
import com.example.deluxe.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChatUserAdapter extends RecyclerView.Adapter<ChatUserAdapter.ViewHolder> {
	ArrayList<LastMessage> listMessage;
	Context context;
	OnUserListener onUserListener;

	public ChatUserAdapter(ArrayList<LastMessage> list, Context context, OnUserListener onUserListener) {
		this.onUserListener = onUserListener;
		this.listMessage = list;
		this.context = context;
	}


	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		View itemView = layoutInflater.inflate(R.layout.component_user_list, parent, false);

		return new ViewHolder(itemView, onUserListener);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.email.setText(listMessage.get(position).getUsername());
		holder.content.setText(listMessage.get(position).getContent());

		Picasso.get().load(listMessage.get(position).getImage_url()).into(holder.avatar);
	}

	@Override
	public int getItemCount() {
		return this.listMessage.size();
	}

	public interface OnUserListener {
		void onUserClick(int position);
	}

	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		TextView content;
		TextView email;
		ImageView avatar;

		OnUserListener onUserListener;

		public ViewHolder(@NonNull View itemView, OnUserListener onUserListener) {
			super(itemView);
			this.onUserListener = onUserListener;
			content = itemView.findViewById(R.id.profile_subtitle);
			email = itemView.findViewById(R.id.profile_title);
			avatar = itemView.findViewById(R.id.profile_picture);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			onUserListener.onUserClick(getAdapterPosition());
		}
	}
}