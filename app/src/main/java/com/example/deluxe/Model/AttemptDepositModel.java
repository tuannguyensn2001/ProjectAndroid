package com.example.deluxe.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.deluxe.Entity.AttemptDeposit;
import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.Model.AttemptDepositInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class AttemptDepositModel {
	private DatabaseReference ref;

	public AttemptDepositModel() {
		this.ref = FirebaseDatabase.getInstance().getReference().child("attempt_deposit");
	}

	public void add(final String key, final AttemptDeposit attemptDeposit) {


		this.ref.child(key).addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				if (!snapshot.exists()) ref.child(key).setValue(attemptDeposit);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});

	}

	public void update(final String key) {
		this.ref.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				AttemptDeposit attemptDeposit = snapshot.getValue(AttemptDeposit.class);
				attemptDeposit.increaseCount();


				Log.e("user", "Tang len ne");
				boolean check = Rules.isSameDate(attemptDeposit.getUpdated_at(), new Date().toString());

				attemptDeposit.setUpdated_at(new Date().toString());
				if (!check) {
					attemptDeposit.setUpdated_at(new Date().toString());
					attemptDeposit.setCount(0);
				}

				ref.child(key).setValue(attemptDeposit);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
	}

	public void check(String key, final AttemptDepositInterface attemptDepositInterface) {
		this.ref.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				AttemptDeposit attemptDeposit = snapshot.getValue(AttemptDeposit.class);
				if (attemptDeposit == null) {
					attemptDeposit = new AttemptDeposit();
					attemptDeposit.setCount(0);
					attemptDeposit.setUpdated_at(new Date().toString());
					add(Auth.getInstance().user().getUid(), attemptDeposit);
				}

				String now = new Date().toString();
				String before = attemptDeposit.getUpdated_at();
				if (Rules.isSameDate(now, before) && attemptDeposit.getCount() == 5)
					attemptDepositInterface.inValid();
				else {
					attemptDepositInterface.valid();
				}
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
	}

}
