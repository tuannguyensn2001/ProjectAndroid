package com.example.deluxe.Model;

import androidx.annotation.NonNull;

import com.example.deluxe.Core.Model;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.Model.DataFirebase;
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


}