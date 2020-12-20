package com.example.deluxe.Model;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.deluxe.API.AddressAPI;
import com.example.deluxe.API.CoreAPI;
import com.example.deluxe.Core.Model;
import com.example.deluxe.Entity.Address;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.Model.AddressInterface;
import com.example.deluxe.Interface.Model.ChangePasswordInterface;
import com.example.deluxe.Interface.Model.CheckInterface;
import com.example.deluxe.Interface.Model.DataFirebase;
import com.example.deluxe.Interface.Model.UserDetailsInterface;
import com.example.deluxe.Interface.Model.UserInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserModel implements Model {
	ArrayList<String> listUser;
	DatabaseReference ref;
	StorageReference storageReference;
	FirebaseUser firebaseUser;
	FirebaseAuth mAuth;

	public UserModel() {
		this.listUser = new ArrayList<>();
		this.ref = FirebaseDatabase.getInstance().getReference().child("user");
		this.storageReference = FirebaseStorage.getInstance().getReference().child("avatar");
		this.mAuth = FirebaseAuth.getInstance();
		this.firebaseUser = mAuth.getCurrentUser();
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

	public void getInfoEmail(final String email, final UserDetailsInterface userDetailsInterface) {
		FirebaseDatabase.getInstance().getReference().child("user").orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {

				for (DataSnapshot item : snapshot.getChildren()) {
					User user = item.getValue(User.class);
					userDetailsInterface.dataIsLoaded(user);
				}
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
	}

	public void checkEmailPassword(final User user, final CheckInterface checkInterface) {
		final ArrayList<User> list = new ArrayList<>();

		final String email = user.getEmail();
		final String password = user.getPassword();

		FirebaseDatabase.getInstance().getReference().child("user").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {

				for (DataSnapshot item : snapshot.getChildren()) {
					User user = item.getValue(User.class);
					list.add(user);
				}

				if (list.get(0).getPassword().equals(password)) {
					checkInterface.dataIsLoaded(true);
				} else checkInterface.dataIsLoaded(false);


			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
	}


	public void uploadAvatar(Uri filePath) {
		final String key = UUID.randomUUID().toString();
		this.storageReference.child(key).putFile(filePath)
				.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
					@Override
					public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
						storageReference.child(key).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
							@Override
							public void onComplete(@NonNull Task<Uri> task) {
								String url = task.getResult().toString();
								updateAvatar(Auth.getInstance().user().getUid(), url);
							}
						});
					}
				});
	}

	public void updateAvatar(final String key, final String url) {
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

	public void updatePassword(final String currentPassword, final String newPassword, final String confirmPassword, final ChangePasswordInterface changePasswordInterface) {
		if (!newPassword.equals(confirmPassword)) {
			changePasswordInterface.failedPassword();
			return;
		}

		this.ref.child(Auth.getInstance().user().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				final User user = snapshot.getValue(User.class);

				if (currentPassword.equals(user.getPassword()) && newPassword.equals(confirmPassword)) {

					firebaseUser.updatePassword(newPassword)
							.addOnSuccessListener(new OnSuccessListener<Void>() {
								@Override
								public void onSuccess(Void aVoid) {
									user.setPassword(newPassword);
									ref.child(Auth.getInstance().user().getUid()).setValue(user);
									changePasswordInterface.success();

								}
							})
							.addOnFailureListener(new OnFailureListener() {
								@Override
								public void onFailure(@NonNull Exception e) {
									changePasswordInterface.failedUpdate(e);
								}
							});

				} else changePasswordInterface.failedPassword();
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
	}

	public void editAddress(String id, String username, String phone, String address) {
		Retrofit retrofit = CoreAPI.build();
		AddressAPI addressAPI = retrofit.create(AddressAPI.class);

		Call<Object> call = addressAPI.editAddress(id, username, phone, address);

		call.enqueue(new Callback<Object>() {
			@Override
			public void onResponse(Call<Object> call, Response<Object> response) {
				int a = 1;
			}

			@Override
			public void onFailure(Call<Object> call, Throwable t) {
				int a = 1;

			}
		});
	}

	public void showAddress(String id, final AddressInterface addressInterface) {
		Retrofit retrofit = CoreAPI.build();
		AddressAPI addressAPI = retrofit.create(AddressAPI.class);

		Call<Address> call = addressAPI.getAddress(id);

		call.enqueue(new Callback<Address>() {
			@Override
			public void onResponse(Call<Address> call, Response<Address> response) {
				addressInterface.dataIsLoaded(response.body());
			}

			@Override
			public void onFailure(Call<Address> call, Throwable t) {
				int a = 1;
			}
		});
	}

}