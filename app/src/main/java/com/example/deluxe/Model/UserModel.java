package com.example.deluxe.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.deluxe.Core.Model;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.Model.DataFirebase;
import com.example.deluxe.Interface.Model.UserInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserModel implements Model {
	ArrayList<String> listUser;
	DatabaseReference ref;

	public UserModel() {
		this.listUser = new ArrayList<>();
		this.ref = FirebaseDatabase.getInstance().getReference().child("user");
	}

	public void getListUser(final DataFirebase userInterface) {
		this.ref.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				listUser.clear();
				for (DataSnapshot item : snapshot.getChildren()) {
					User user = item.getValue(User.class);
					listUser.add(user.getUser());
				}
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
	}

	public void create(User user, String key) {
		this.ref.child(key).setValue(user);
	}


	public void read(final DataFirebase dataFirebase)
	{

		final ArrayList<User> userArrayList = new ArrayList<>();
		this.ref.addValueEventListener(new ValueEventListener() {

			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				userArrayList.clear();
				for ( DataSnapshot item : snapshot.getChildren())
				{
					User user = item.getValue(User.class);
					userArrayList.add(user);
				}

				dataFirebase.dataIsLoaded(userArrayList);

			}
			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
	}


	public void search(User user, final UserInterface userInterface) {
		String query = user.getEmail();

		this.ref.orderByChild("email")
				.startAt(query)
				.endAt(query + "\uf8ff")
				.addValueEventListener(new ValueEventListener() {
					@Override
					public void onDataChange(@NonNull DataSnapshot snapshot) {
						ArrayList<User> list = new ArrayList<>();
						for (DataSnapshot item : snapshot.getChildren()) {
							User user = item.getValue(User.class);
							list.add(user);
						}
						userInterface.dataIsLoaded(list);
					}

					@Override
					public void onCancelled(@NonNull DatabaseError error) {
					}
				});
	}

}