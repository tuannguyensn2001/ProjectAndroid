package com.example.deluxe.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.deluxe.Entity.Wallet;
import com.example.deluxe.Interface.Model.WalletInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class WalletModel {
	private DatabaseReference ref;

	public WalletModel() {
		this.ref = FirebaseDatabase.getInstance().getReference().child("wallet");
	}

	public void add(String key, Wallet wallet) {
		this.ref.child(key).setValue(wallet);
	}

	public void deposit(final String key, final double value) {
		FirebaseDatabase.getInstance().getReference().child("wallet").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				Wallet wallet = snapshot.getValue(Wallet.class);
				Log.e("card", wallet.getAmount() + "");
				double money = wallet.getAmount();
				wallet.setAmount(money + value);
				wallet.setUpdated_at(new Date().toString());
				Log.e("card", wallet.getAmount() + "");
				FirebaseDatabase.getInstance().getReference().child("wallet").child(key).setValue(wallet);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
	}

	public void getMoney(String key, final WalletInterface walletInterface) {
		this.ref.child(key).addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				Wallet wallet = snapshot.getValue(Wallet.class);

				walletInterface.dataIsLoaded(wallet.getAmount());
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
	}

	public void getMoneyOnce(String key, final WalletInterface walletInterface) {
		this.ref.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				Wallet wallet = snapshot.getValue(Wallet.class);

				walletInterface.dataIsLoaded(wallet.getAmount());
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
	}


}