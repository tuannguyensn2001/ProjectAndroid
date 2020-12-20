package com.example.deluxe.Model;

import androidx.annotation.NonNull;

import com.example.deluxe.API.CoreAPI;
import com.example.deluxe.API.OrderAPI;
import com.example.deluxe.Entity.Deposit;
import com.example.deluxe.Entity.Object;
import com.example.deluxe.Entity.Transaction;
import com.example.deluxe.Entity.Transfer;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Entity.Withdraw;
import com.example.deluxe.Enum.TransactionType;
import com.example.deluxe.Interface.Model.ListTransactionInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HistoryModel {
	private Retrofit retrofit;
	private OrderAPI orderAPI;

	public HistoryModel() {
		this.retrofit = CoreAPI.build();
		this.orderAPI = retrofit.create(OrderAPI.class);
	}


	public Date convertDate(String date) throws ParseException {
		Date dateConvert = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
			dateConvert = formatter.parse(date);
		} catch (Exception ignored) {

		}
		return dateConvert;
	}

	public Date convertDateFromAPI(String date) throws ParseException {
		Date dateConvert = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss ", Locale.ENGLISH);
			dateConvert = formatter.parse(date);
		} catch (Exception ignored) {

		}
		return dateConvert;
	}

	public boolean contains(ArrayList<Date> list, Date date) {
		for (Date item : list) {
			if (item.getMonth() == date.getMonth() && item.getYear() == date.getYear()) {
				return true;
			}
		}
		return false;
	}

	public Date checkKey(ArrayList<Date> list, Date date) {
		Date result = null;
		for (Date item : list) {
			if (item.getMonth() == date.getMonth() && item.getYear() == date.getYear()) {
				result = item;
				break;
			}
		}
		return result;
	}

	public void getListDeposit(User user, final ListTransactionInterface listTransactionInterface) {
		String email = user.getEmail();

		final ArrayList<Transaction> listDeposit = new ArrayList<>();

		FirebaseDatabase.getInstance().getReference().child("deposit").orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				listDeposit.clear();


				for (DataSnapshot item : snapshot.getChildren()) {
					Deposit deposit = item.getValue(Deposit.class);

					try {
						Date date = convertDate(deposit.getUpdated_at());
						long money = (long) deposit.getMoney();
						String userEmail = deposit.getEmail();
						Transaction transaction = new Transaction(TransactionType.DEPOSIT, money, date, true, userEmail, null);
						listDeposit.add(transaction);
					} catch (ParseException e) {
						e.printStackTrace();
					}

				}

				Collections.reverse(listDeposit);

				ArrayList<Date> listKey = new ArrayList<>();

				for (Transaction item : listDeposit) {

					if (!contains(listKey, item.getDate())) {
						listKey.add(item.getDate());
					}
				}

				HashMap<Date, ArrayList<Transaction>> result = new HashMap<>();
				for (Date item : listKey) {
					result.put(item, new ArrayList<Transaction>());
				}

				for (Transaction item : listDeposit) {
					Date date = checkKey(listKey, item.getDate());
					result.get(date).add(item);
				}

				listTransactionInterface.dataIsLoaded(result);

			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});


	}

	public void getListWithdraw(User user, final ListTransactionInterface listTransactionInterface) {
		String email = user.getEmail();
		final ArrayList<Transaction> listWithdraw = new ArrayList<>();


		FirebaseDatabase.getInstance().getReference().child("withdraw").orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				listWithdraw.clear();

				for (DataSnapshot item : snapshot.getChildren()) {
					Withdraw withdraw = item.getValue(Withdraw.class);
					try {
						Date date = convertDate(withdraw.getCreated_at());
						long money = (long) withdraw.getAmount();
						String userEmail = withdraw.getEmail();
						boolean isComplete = withdraw.isIs_active();
						String message = withdraw.getNote();
						Transaction transaction = new Transaction(TransactionType.WITHDRAW, money, date, isComplete, userEmail, message);
						listWithdraw.add(transaction);

					} catch (ParseException e) {
						e.printStackTrace();
					}
				}

				Collections.reverse(listWithdraw);

				final ArrayList<Date> listKey = new ArrayList<>();

				for (Transaction item : listWithdraw) {
					if (!contains(listKey, item.getDate())) {
						listKey.add(item.getDate());
					}
				}

				HashMap<Date, ArrayList<Transaction>> result = new HashMap<>();

				for (Date item : listKey) {

					result.put(item, new ArrayList<Transaction>());
				}
				Date newDate = null;
				result.put(newDate, new ArrayList<Transaction>());

				for (Transaction item : listWithdraw) {
					if (item.isComplete()) {
						Date date = checkKey(listKey, item.getDate());
						Objects.requireNonNull(result.get(date)).add(item);
					} else Objects.requireNonNull(result.get(newDate)).add(item);
				}

				listTransactionInterface.dataIsLoaded(result);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});

	}

	public void getListTransfer(User user, final ListTransactionInterface listTransactionInterface) {
		final String email = user.getEmail();
		final ArrayList<Transfer> list = new ArrayList<>();

		final ArrayList<Transaction> listTransfer = new ArrayList<>();


		FirebaseDatabase.getInstance().getReference().child("transfer").addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {

				for (DataSnapshot item : snapshot.getChildren()) {
					Transfer transfer = item.getValue(Transfer.class);
					list.add(transfer);
				}

				for (Transfer item : list) {
					if (email.equals(item.getEmailReceiver())) {
						try {
							long money = (long) item.getMoney();
							Date date = convertDate(item.getUpdated_at());
							String userEmail = item.getEmailDepositor();
							String message = item.getMessages();
							Transaction transaction = new Transaction(TransactionType.RECEIVE, money, date, true, userEmail, message);
							listTransfer.add(transaction);

						} catch (ParseException e) {
							e.printStackTrace();
						}

					}
					if (email.equals(item.getEmailDepositor())) {
						try {
							long money = (long) item.getMoney();
							Date date = convertDate(item.getUpdated_at());
							String userEmail = item.getEmailReceiver();
							String message = item.getMessages();
							Transaction transaction = new Transaction(TransactionType.TRANSFER, money, date, true, userEmail, message);
							listTransfer.add(transaction);

						} catch (ParseException e) {
							e.printStackTrace();
						}

					}
				}

				Collections.reverse(listTransfer);

				ArrayList<Date> listKey = new ArrayList<>();

				for (Transaction item : listTransfer) {
					if (!contains(listKey, item.getDate())) {
						listKey.add(item.getDate());
					}
				}

				HashMap<Date, ArrayList<Transaction>> result = new HashMap<>();

				for (Date item : listKey) {
					result.put(item, new ArrayList<Transaction>());
				}

				for (Transaction item : listTransfer) {
					Date date = checkKey(listKey, item.getDate());
					Objects.requireNonNull(result.get(date)).add(item);
				}


				listTransactionInterface.dataIsLoaded(result);

			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});


	}

	public void getDetailOrder(String id, final ListTransactionInterface listTransactionInterface) {

		Call<List<Object.ResponeOrderAPI>> call = this.orderAPI.getDetailOrder(id);

		call.enqueue(new Callback<List<Object.ResponeOrderAPI>>() {
			@Override
			public void onResponse(Call<List<Object.ResponeOrderAPI>> call, Response<List<Object.ResponeOrderAPI>> response) {

				List<Transaction> transactionList = new ArrayList<>();

				List<Object.ResponeOrderAPI> list = response.body();

				for (Object.ResponeOrderAPI item : list) {
					try {
						Date date = convertDateFromAPI(item.getUpdate());
						long money = (long) item.getMoney();
						Transaction transaction = new Transaction(TransactionType.USE, money, date, true, null, null);
						transaction.setId(item.getId() + "");
						transactionList.add(transaction);

					} catch (ParseException e) {
						e.printStackTrace();
					}
				}

				Collections.reverse(transactionList);
				ArrayList<Date> listKey = new ArrayList<>();

				for (Transaction item : transactionList) {
					if (!contains(listKey, item.getDate())) {
						listKey.add(item.getDate());
					}
				}

				HashMap<Date, ArrayList<Transaction>> result = new HashMap<>();

				for (Date item : listKey) {
					result.put(item, new ArrayList<Transaction>());
				}

				for (Transaction item : transactionList) {
					Date date = checkKey(listKey, item.getDate());
					Objects.requireNonNull(result.get(date)).add(item);
				}

				listTransactionInterface.dataIsLoaded(result);


			}

			@Override
			public void onFailure(Call<List<Object.ResponeOrderAPI>> call, Throwable t) {

			}
		});
	}

}
