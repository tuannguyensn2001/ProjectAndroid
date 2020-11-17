package com.example.deluxe.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.Model.DataFirebase;
import com.example.deluxe.Interface.Model.ListDepositInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.example.deluxe.Entity.Deposit;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import  java.util.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.SimpleFormatter;

public class DepositModel {

	private DatabaseReference ref;
	ArrayList<Deposit> listDeposit;



	public DepositModel()
	{

		this.listDeposit  = new ArrayList<>();
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
	public void  getListDeposit(final ListDepositInterface depositInterface)
	{

		String email = Auth.getInstance().user().getEmail();

		Log.e("email",email+" ");

		FirebaseDatabase.getInstance().getReference().child("deposit").orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				listDeposit.clear();
				for(DataSnapshot item: snapshot.getChildren())
				{
					Deposit deposit = item.getValue(Deposit.class);
					listDeposit.add(deposit);
				}


				Set<String> month = getListMonth(listDeposit);

				HashMap<String,ArrayList<Deposit>> depositList = new HashMap<>();

				for(String item : month){
					depositList.put(item, new ArrayList<Deposit>());
				}



				for(Deposit item : listDeposit)
				{
					String key = convert(item.getUpdated_at());

					depositList.get(key).add(item);
				}

				Log.e("date",depositList.get("Tháng 11 năm 2020").size()+"");



				depositInterface.dataIsLoaded(listDeposit);DepositModel.this.hashCode();

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

	public Set getListMonth( ArrayList<Deposit> listDeposit)
	{

		Set<String> hashSet = new HashSet<>();
		for(Deposit item : listDeposit)
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




}