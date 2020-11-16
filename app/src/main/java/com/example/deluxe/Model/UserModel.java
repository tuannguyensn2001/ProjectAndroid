package com.example.deluxe.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.deluxe.Core.Model;
import com.example.deluxe.Entity.Deposit;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.Model.CheckInterface;
import com.example.deluxe.Interface.Model.DataFirebase;
import com.example.deluxe.Interface.Model.DepositInterface;
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
				userInterface.dataIsLoaded(listUser);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
	}

	public void create(User user, String key) {
//		String key = this.ref.push().getKey();
		this.ref.child(key).setValue(user);
	}

	public void search(User user, final UserInterface userInterface) {
//		String email = user.getEmail();
		String query = user.getEmail();

		//SELECT * FROM user WHERE email = "huongtran76@gmail.com"
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


	public void show(String key, final DepositInterface depositInterface) {
		this.ref.child(key).addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				User user = snapshot.getValue(User.class);

				depositInterface.dataIsLoaded(user);

			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
	}

	public void checkEmailPassword(final User user, final CheckInterface checkInterface)
	{
		final ArrayList<User> list = new ArrayList<>();

		String email = user.getEmail();
		final String password = user.getPassword();

		FirebaseDatabase.getInstance().getReference().child("user").orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {

				FirebaseDatabase.getInstance().getReference().child("user").orderByChild("password").equalTo(password).addValueEventListener(new ValueEventListener() {
					@Override
					public void onDataChange(@NonNull DataSnapshot snapshot) {

						for (DataSnapshot item : snapshot.getChildren()) {
							User user1 = item.getValue(User.class);
							list.add(user1);
						}

						if (list.size() == 0) {
							checkInterface.dataIsLoaded(false);

						} else checkInterface.dataIsLoaded(true);


					}

					@Override
					public void onCancelled(@NonNull DatabaseError error) {

					}
				});

			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});

	}
}