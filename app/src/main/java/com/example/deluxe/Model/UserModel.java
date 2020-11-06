package com.example.deluxe.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.deluxe.Core.Model;
import com.example.deluxe.Entity.Deposit;
import com.example.deluxe.Entity.User;
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
//    public boolean checkUserExist(User user){
//
//        for(User i : listUser){
//            if(user.getUser().equals(i.getUser()) && user.getPassword().equals(i.getPassword())){
//                return  true;
//            }
//        }
//        return false;
//    }
//
//    public void init(){
//        listUser = new ArrayList<User>();
//        listUser.add(new User("nguyenvana","123456") );
//        listUser.add(new User("nguyenvanb","121212"));
//        listUser.add(new User("phamthiduc" , "123123"));
//        listUser.add(new User("tranmaihuong","321321"));
//        listUser.add(new User("nguyenngocchau","111111"));
//        listUser.add(new User("dangtrungdung","222222"));
//        listUser.add(new User("nguyenvantuan","123456"));
//
//    }

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

	public void create(User user,String key)
	{
//		String key = this.ref.push().getKey();
		this.ref.child(key).setValue(user);
	}

	public void search(User user, final UserInterface userInterface)
	{
//		String email = user.getEmail();
		String query = user.getEmail();

		//SELECT * FROM user WHERE email = "huongtran76@gmail.com"
		this.ref.orderByChild("email")
				.startAt(query)
				.endAt(query+"\uf8ff")
				.addValueEventListener(new ValueEventListener() {
					@Override
					public void onDataChange(@NonNull DataSnapshot snapshot) {
						ArrayList<User> list = new ArrayList<>();
						for (DataSnapshot item : snapshot.getChildren())
						{
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


		public void show(String key, final DepositInterface depositInterface)
		{
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


}