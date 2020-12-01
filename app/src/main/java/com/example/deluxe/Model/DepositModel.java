package com.example.deluxe.Model;

import com.example.deluxe.Entity.Deposit;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DepositModel {

	private DatabaseReference ref;

	public DepositModel() {
		this.ref = FirebaseDatabase.getInstance().getReference().child("deposit");
	}

	public void create(Deposit deposit) {
		String key = this.ref.push().getKey();
		assert key != null;
		this.ref.child(key).setValue(deposit);
	}
}