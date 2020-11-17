package com.example.deluxe.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.deluxe.Entity.Deposit;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Entity.Wallet;
<<<<<<< HEAD
import com.example.deluxe.Interface.Model.DataFirebase;
import com.example.deluxe.Interface.Model.ListTransferInterface;
=======
import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Enum.SuccessMessage;
import com.example.deluxe.Interface.Model.TransferFirebase;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
>>>>>>> 6e52742c5d4d535db56eda368b46d114b2de4a74
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
	ArrayList<Transfer> listTransfer;

	public TransferModel() {
		this.listTransfer = new ArrayList<>();
		this.ref = FirebaseDatabase.getInstance().getReference().child("transfer");
		this.userRef = FirebaseDatabase.getInstance().getReference().child("user");
		this.walletRef = FirebaseDatabase.getInstance().getReference().child("wallet");
	}

	public void transfer(final Transfer transfer, final TransferFirebase transferFirebase) {

		String depositer = transfer.getEmailDepositor();
		final String receiver = transfer.getEmailReceiver();
		final double money = transfer.getMoney();

		Log.e("test",depositer+" ");

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

	public void  getListTransfer(final ListTransferInterface
										 transferInterface) {

		String email = Auth.getInstance().user().getEmail();

		FirebaseDatabase.getInstance().getReference().child("transfer").orderByChild("emailDepositor").equalTo(email).addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				listTransfer.clear();
				for (DataSnapshot item : snapshot.getChildren()) {
					Transfer transfer = item.getValue(Transfer.class);
					listTransfer.add(transfer);
				}

				Set<String> month = getListMonth(listTransfer);


				Log.e("test" , month.size()+" ");

				HashMap<String,ArrayList<Transfer>> transferList = new HashMap<>();

				for(String item : month){
					transferList.put(item, new ArrayList<Transfer>());
				}



				for(Transfer item : listTransfer)
				{
					String key = convert(item.getUpdated_at());

					transferList.get(key).add(item);
				}

				Log.e("transfer" , transferList.get("Tháng 11 năm 2020").size()+" ");
				Log.e("aaa","aaaaaa");

				transferInterface.dataIsLoaded(listTransfer);

			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
	}

	public Set getListMonth(ArrayList<Transfer> listTransfer)
	{

		Set<String> hashSet = new HashSet<>();
		for(Transfer item : listTransfer)
		{
			String date = item.getUpdated_at();
			hashSet.add(convert(date));

		}

		return hashSet;
	}

	public static String convert(String date)
	{
		String dateArray[] = date.split("\\s");
		String month= dateArray[1];
		return getMonth(month) + " năm " + dateArray[5];
	}

	public static String getMonth(String month)
	{
		String result = null;


		HashMap<String,String> monthList = new HashMap<>();

		monthList.put("Oct", "Tháng 10");
		monthList.put("Jan","Tháng 1");
		monthList.put("Feb", "Tháng 2");
		monthList.put("Mar","Tháng 3");
		monthList.put("Apr", "Tháng 4");
		monthList.put("May", "Tháng 5");
		monthList.put("Jun", "Tháng 6");
		monthList.put("Jul", "Tháng 7");
		monthList.put("Aug", "Tháng 8");
		monthList.put("Sep", "Tháng 9");
		monthList.put("Dec", "Tháng 12");
		monthList.put("Nov", "Tháng 11");

		return monthList.get(month);
	}

}

