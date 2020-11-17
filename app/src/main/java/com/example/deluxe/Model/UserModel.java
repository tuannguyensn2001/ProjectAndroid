package com.example.deluxe.Model;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.deluxe.Core.Model;
import com.example.deluxe.Entity.Deposit;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.Model.CheckInterface;
import com.example.deluxe.Interface.Model.DataFirebase;
import com.example.deluxe.Interface.Model.UserDetailsInterface;
import com.example.deluxe.Interface.Model.UserDetailsInterface;
import com.example.deluxe.Interface.Model.UserInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class UserModel implements Model {
	ArrayList<String> listUser;
	DatabaseReference ref;
	StorageReference storageReference;

	public UserModel() {
		this.listUser = new ArrayList<>();
		this.ref = FirebaseDatabase.getInstance().getReference().child("user");
		this.storageReference = FirebaseStorage.getInstance().getReference().child("avatar");
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


	public void show(String key, final UserDetailsInterface userDetailsInterface) {
		this.ref.child(key).addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				User user = snapshot.getValue(User.class);

				userDetailsInterface.dataIsLoaded(user);

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


	public void uploadAvatar(Uri filePath)
	{
		final String key = UUID.randomUUID().toString();
		this.storageReference.child(key).putFile(filePath)
				.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
					@Override
					public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
						storageReference.child(key).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
							@Override
							public void onComplete(@NonNull Task<Uri> task) {
								String url = task.getResult().toString();
								updateAvatar(Auth.getInstance().user().getUid(),url);
							}
						});
					}
				});
	}

	public void updateAvatar(final String key,final  String url)
	{
		this.ref.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				User user = snapshot.getValue(User.class);
				user.setAvatar(url);
				ref.child(key).setValue(user);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
	}
}