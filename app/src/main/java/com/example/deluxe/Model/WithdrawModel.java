package com.example.deluxe.Model;

import com.example.deluxe.Entity.Withdraw;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WithdrawModel {

	DatabaseReference ref;

	public WithdrawModel() {
		this.ref = FirebaseDatabase.getInstance().getReference().child("withdraw");
	}

	public void withdraw(Withdraw withdraw) {

		String key = this.ref.push().getKey();
		this.ref.child(key).setValue(withdraw);

	}
}