package com.example.deluxe.Model;

import com.example.deluxe.API.CoreAPI;
import com.example.deluxe.API.StatisticAPI;
import com.example.deluxe.Entity.Income;
import com.example.deluxe.Entity.Object;
import com.example.deluxe.Entity.Pay;
import com.example.deluxe.Entity.Statistic;
import com.example.deluxe.Entity.Transaction;
import com.example.deluxe.Enum.TransactionType;
import com.example.deluxe.Interface.Model.ListGetPerMonth;
import com.example.deluxe.Interface.Model.StatisticInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StatisticModel {
	private final Retrofit retrofit;
	private final StatisticAPI statisticAPI;


	public StatisticModel() {
		this.retrofit = CoreAPI.build();
		this.statisticAPI = retrofit.create(StatisticAPI.class);

	}

	public void getStatistic(final String email, final StatisticInterface statisticInterface) {

		Call<Statistic> call = this.statisticAPI.getStatistic(email);
		call.enqueue(new Callback<Statistic>() {
			@Override
			public void onResponse(Call<Statistic> call, Response<Statistic> response) {
				Statistic object = response.body();
				List<Income> listIncome = object.getIncome();
				List<Pay> listPay = object.getPay();

				for (Income item : listIncome) {
					item.setDateConvert(convertDate(item.getDate()));
				}

				for (Pay pay : listPay) {
					pay.setDateConvert(convertDate(pay.getDate()));
				}

				statisticInterface.dataIsLoaded(listIncome, listPay);


			}

			@Override
			public void onFailure(Call<Statistic> call, Throwable t) {

			}
		});
	}

	public Date convertDate(String date) {
		Date date1 = new Date();
		String[] dateArray = date.split("\\s");
		date1.setMonth(Integer.parseInt(dateArray[0]) - 1);
		date1.setYear(Integer.parseInt(dateArray[1]) - 1900);
		return date1;
	}

	public void getPerMonth(String id, final ListGetPerMonth listGetPerMonth) {

		Call<List<Object.getPerMonthAPI>> call = this.statisticAPI.getPerMonth(id);

		call.enqueue(new Callback<List<Object.getPerMonthAPI>>() {
			@Override
			public void onResponse(Call<List<Object.getPerMonthAPI>> call, Response<List<Object.getPerMonthAPI>> response) {

				List<Transaction> transactionList = new ArrayList<>();
				List<Object.getPerMonthAPI> list = response.body();

				for (Object.getPerMonthAPI item : list) {
					if (item.getType().equals("1")) {
						try {
							Date date = convertDateFromAPI(item.getCreatedAt());
							String id = item.getId();
							String email = item.getEmail();
							long money = (long) item.getMoney();
							Transaction transaction = new Transaction(TransactionType.TRANSFER, money, date, true, email, null);
							transactionList.add(transaction);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					} else {
						try {
							Date date = convertDate2(item.getCreatedAt());
							String id = item.getId();
							String email = item.getEmail();
							long money = (long) item.getMoney();
							Transaction transaction = new Transaction(TransactionType.TRANSFER, money, date, true, email, null);
							transactionList.add(transaction);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				}
				Collections.sort(transactionList, new Comparator<Transaction>() {
					@Override
					public int compare(Transaction o1, Transaction o2) {
						return o1.getDate().compareTo(o2.getDate());
					}
				});

				listGetPerMonth.dataIsLoaded(transactionList);

			}

			@Override
			public void onFailure(Call<List<Object.getPerMonthAPI>> call, Throwable t) {


			}
		});
	}

	public void getLimit(String id, final ListGetPerMonth listGetPerMonth) {
		Call<List<Object.getPerMonthAPI>> call = this.statisticAPI.getLimit(id);

		call.enqueue(new Callback<List<Object.getPerMonthAPI>>() {
			@Override
			public void onResponse(Call<List<Object.getPerMonthAPI>> call, Response<List<Object.getPerMonthAPI>> response) {

				List<Transaction> transactionList = new ArrayList<>();
				List<Object.getPerMonthAPI> list = response.body();

				for (Object.getPerMonthAPI item : list) {
					if (item.getType().equals("1")) {
						try {
							Date date = convertDateFromAPI(item.getCreatedAt());
							String id = item.getId();
							String email = item.getEmail();
							long money = (long) item.getMoney();
							Transaction transaction = new Transaction(TransactionType.TRANSFER, money, date, true, email, null);
							transactionList.add(transaction);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					} else {
						try {
							Date date = convertDate2(item.getCreatedAt());
							String id = item.getId();
							String email = item.getEmail();
							long money = (long) item.getMoney();
							Transaction transaction = new Transaction(TransactionType.TRANSFER, money, date, true, email, null);
							transactionList.add(transaction);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				}

				Collections.sort(transactionList, new Comparator<Transaction>() {
					@Override
					public int compare(Transaction o1, Transaction o2) {
						return o1.getDate().compareTo(o2.getDate());
					}
				});

				listGetPerMonth.dataIsLoaded(transactionList);
			}

			@Override
			public void onFailure(Call<List<Object.getPerMonthAPI>> call, Throwable t) {


			}
		});
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

	public Date convertDate2(String date) throws ParseException {
		Date dateConvert = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
			dateConvert = formatter.parse(date);
		} catch (Exception ignored) {

		}
		return dateConvert;
	}
}