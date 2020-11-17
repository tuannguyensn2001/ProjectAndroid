package com.example.deluxe.Model;


import android.util.Log;

import androidx.annotation.NonNull;

import com.example.deluxe.Entity.Deposit;
import com.example.deluxe.Entity.Withdraw;
import com.example.deluxe.Interface.Model.ListDepositInterface;
import com.example.deluxe.Interface.Model.ListWithdrawInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WithdrawModel {

	DatabaseReference ref;
	ArrayList<Withdraw> listWithdraw;

	public WithdrawModel() {
		this.listWithdraw = new ArrayList<>();
		this.ref = FirebaseDatabase.getInstance().getReference().child("withdraw");
	}

	public void withdraw(Withdraw withdraw) {

		String key = this.ref.push().getKey();
		this.ref.child(key).setValue(withdraw);

	}


	public void  getListWithdraw(final ListWithdrawInterface withdrawInterface)
	{

		String email = Auth.getInstance().user().getEmail();

		Log.e("email",email+" ");

		FirebaseDatabase.getInstance().getReference().child("withdraw").orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				listWithdraw.clear();
				for(DataSnapshot item: snapshot.getChildren())
				{
					Withdraw withdraw = item.getValue(Withdraw.class);
					listWithdraw.add(withdraw);
				}


				Set<String> month = getListMonth(listWithdraw);

				HashMap<String, ArrayList<Withdraw>> withdrawList = new HashMap<>();

				for(String item : month){
					withdrawList.put(item, new ArrayList<Withdraw>());
				}



				for(Withdraw item : listWithdraw)
				{
					String key = convert(item.getUpdate_at());

					withdrawList.get(key).add(item);
				}

				Log.e("date",withdrawList.get("Tháng 11 năm 2020").size()+"");



				withdrawInterface.dataIsLoaded(listWithdraw);

			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});
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

	public Set getListMonth( ArrayList<Withdraw> listWithdraw)
	{

		Set<String> hashSet = new HashSet<>();
		for(Withdraw item : listWithdraw)
		{
			String date = item.getUpdate_at();
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

}