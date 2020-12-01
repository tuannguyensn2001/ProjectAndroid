package com.example.deluxe.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.deluxe.Entity.Transfer;
import com.example.deluxe.Entity.Wallet;
import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Enum.SuccessMessage;
import com.example.deluxe.Interface.Model.TransferFirebase;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class TransferModel {


	DatabaseReference ref;
	DatabaseReference userRef;
	DatabaseReference walletRef;

	public TransferModel() {
		this.ref = FirebaseDatabase.getInstance().getReference().child("transfer");
		this.userRef = FirebaseDatabase.getInstance().getReference().child("user");
		this.walletRef = FirebaseDatabase.getInstance().getReference().child("wallet");
	}

	public void transfer(final Transfer transfer, final TransferFirebase transferFirebase) {

		String depositer = transfer.getEmailDepositor();
		final String receiver = transfer.getEmailReceiver();
		final double money = transfer.getMoney();


		FirebaseDatabase.getInstance().getReference().child("user").orderByChild("email").equalTo(depositer).addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {

				String key1 = null;
				for (DataSnapshot item : snapshot.getChildren()) {
					key1 = item.getKey();
				}

				Log.e("user", key1);

				final String finalKey1 = key1;
				walletRef.child(key1).addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(@NonNull DataSnapshot snapshot) {
						Wallet wallet = snapshot.getValue(Wallet.class);

						wallet.decreaseAmount(money);

						walletRef.child(finalKey1).setValue(wallet);


						userRef.orderByChild("email").equalTo(receiver).addValueEventListener(new ValueEventListener() {
							@Override
							public void onDataChange(@NonNull DataSnapshot snapshot) {
								String key2 = null;
								for (DataSnapshot item : snapshot.getChildren())
									key2 = item.getKey();

								final String finalKey2 = key2;

								walletRef.child(key2).addListenerForSingleValueEvent(new ValueEventListener() {
									@Override
									public void onDataChange(@NonNull DataSnapshot snapshot) {
										Wallet wallet = snapshot.getValue(Wallet.class);

										wallet.increaseAmount(money);

										walletRef.child(finalKey2).setValue(wallet);

										String key = ref.push().getKey();


										transfer.setCreated_at(new Date().toString());
										transfer.setUpdated_at(new Date().toString());

										Task<Void> check = ref.child(key).setValue(transfer);

										check.addOnCanceledListener(new OnCanceledListener() {
											@Override
											public void onCanceled() {
												transferFirebase.failed(ErrorMessage.ERR310001);
											}
										});
										check.addOnCompleteListener(new OnCompleteListener<Void>() {
											@Override
											public void onComplete(@NonNull Task<Void> task) {
												transferFirebase.success(SuccessMessage.SUC000003);
											}
										});
										check.addOnFailureListener(new OnFailureListener() {
											@Override
											public void onFailure(@NonNull Exception e) {
												transferFirebase.failed(ErrorMessage.ERR310002);
											}
										});
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