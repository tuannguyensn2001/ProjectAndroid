package com.example.deluxe.Model;

import androidx.annotation.NonNull;

import com.example.deluxe.Interface.Model.DataFirebase;
import com.example.deluxe.Interface.Model.ListDepositInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.example.deluxe.Entity.Deposit;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DepositModel {

	private DatabaseReference ref;
	ArrayList<String> listDeposit;

	public DepositModel()
	{
		this.ref = FirebaseDatabase.getInstance().getReference().child("deposit");
	}

	public void create(Deposit deposit)
	{
		String key = this.ref.push().getKey();
		assert key != null;
		this.ref.child(key).setValue(deposit);
	}

	public void read()
	{

	}
	public void  getListDeposit(final DataFirebase depositInterface)
	{
		this.ref.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				listDeposit.clear();
				for(DataSnapshot item: snapshot.getChildren())
				{
					Deposit deposit = item.getValue(Deposit.class);
					listDeposit.add(deposit.getEmail());
				}
				depositInterface.dataIsLoaded(listDeposit);


			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});

	}
}