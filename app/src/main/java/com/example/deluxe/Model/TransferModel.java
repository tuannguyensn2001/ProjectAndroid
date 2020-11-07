package com.example.deluxe.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.deluxe.Entity.Deposit;
import com.example.deluxe.Entity.Wallet;
import com.example.deluxe.Interface.Model.DataFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

//<<<<<<< HEAD
//public class TransferModel {
//
//
//    DatabaseReference ref;
//    DatabaseReference userRef;
//    DatabaseReference walletRef;
//
//    public TransferModel()
//    {
//        this.ref = FirebaseDatabase.getInstance().getReference().child("transfer");
//        this.userRef = FirebaseDatabase.getInstance().getReference().child("user");
//        this.walletRef = FirebaseDatabase.getInstance().getReference().child("wallet");
//    }
//
//    public void Transfer(final Transfer transfer)
//    {
//
//        String depositer = transfer.getEmailDepositer();
//        final String receiver = transfer.getEmailReceiver();
//        final double money = transfer.getMoney();
//
//
//        FirebaseDatabase.getInstance().getReference().child("user").orderByChild("email").equalTo(depositer).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                String key1 = null;
//                for (DataSnapshot item: snapshot.getChildren())
//                {
//                    key1=item.getKey();
//                }
//
//                Log.e("user",key1);
//
//                final String finalKey1 = key1;
//                walletRef.child(key1).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        Wallet wallet = snapshot.getValue(Wallet.class);
//
//                       wallet.decreaseAmount(money);
//
//                       walletRef.child(finalKey1).setValue(wallet);
//
//
//                       userRef.orderByChild("email").equalTo(receiver).addValueEventListener(new ValueEventListener() {
//                           @Override
//                           public void onDataChange(@NonNull DataSnapshot snapshot) {
//                               String key2 = null;
//                               for (DataSnapshot item : snapshot.getChildren())
//                                   key2 = item.getKey();
//
//                               final String finalKey2 = key2;
//
//                               walletRef.child(key2).addListenerForSingleValueEvent(new ValueEventListener() {
//                                   @Override
//                                   public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                       Wallet wallet = snapshot.getValue(Wallet.class);
//
//                                       wallet.increaseAmount(money);
//
//                                       walletRef.child(finalKey2).setValue(wallet);
//
//                                       String key = ref.push().getKey();
//
//
//                                       transfer.setCreated_at(new Date().toString());
//                                       transfer.setUpdated_at(new Date().toString());
//
//                                       ref.child(key).setValue(transfer);
//                                   }
//
//                                   @Override
//                                   public void onCancelled(@NonNull DatabaseError error) {
//
//                                   }
//                               });
//
//
//                           }
//
//                           @Override
//                           public void onCancelled(@NonNull DatabaseError error) {
//
//                           }
//                       });
//
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//    }
//
//}
//=======
import com.example.deluxe.Entity.Transfer;

public class TransferModel {


	DatabaseReference ref;
	DatabaseReference userRef;
	DatabaseReference walletRef;
	ArrayList<String> listTransfer;

	public TransferModel() {
		this.ref = FirebaseDatabase.getInstance().getReference().child("transfer");
		this.userRef = FirebaseDatabase.getInstance().getReference().child("user");
		this.walletRef = FirebaseDatabase.getInstance().getReference().child("wallet");
	}

	public void transfer(final Transfer transfer) {

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

										ref.child(key).setValue(transfer);
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

	public void  getListTransfer(final DataFirebase transferInterface) {
		this.ref.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				listTransfer.clear();
				for (DataSnapshot item : snapshot.getChildren()) {
					Transfer transfer = item.getValue(Transfer.class);
					listTransfer.add(transfer.getEmailDepositor());
				}
				transferInterface.dataIsLoaded(listTransfer);

			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
	}

}

