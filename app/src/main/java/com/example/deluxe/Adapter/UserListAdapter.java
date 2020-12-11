package com.example.deluxe.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deluxe.Entity.User;
import com.example.deluxe.R;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
	com.example.deluxe.Core.View.ViewUseSearchBar parentContext;

	private ArrayList<User> userList;

	public UserListAdapter(com.example.deluxe.Core.View.ViewUseSearchBar parentContext, ArrayList<User> userList) {
		this.parentContext = parentContext;
		this.userList = userList;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.component_user_list, parent, false);
		return new ViewHolder(item);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		final User user = userList.get(position);
		holder.getTitle().setText(String.format("%s", user.getUser()));
		holder.getSubtitle().setText(String.format("%s", user.getEmail()));
		holder.getItemView().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				parentContext.handleOnItemClick(user);
			}
		});
	}

	@Override
	public int getItemCount() {
		return userList.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		private TextView title, subtitle;
		private View itemView;

		public ViewHolder(@NonNull final View itemView) {
			super(itemView);

			this.itemView = itemView;
			this.title = itemView.findViewById(R.id.profile_title);
			this.subtitle = itemView.findViewById(R.id.profile_subtitle);
		}

		public TextView getTitle() {
			return title;
		}

		public TextView getSubtitle() {
			return subtitle;
		}

		public View getItemView() {
			return itemView;
		}
	}
}