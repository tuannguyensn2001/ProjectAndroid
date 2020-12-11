package com.example.deluxe.Model;

import com.example.deluxe.API.CoreAPI;
import com.example.deluxe.API.StatisticAPI;
import com.example.deluxe.Entity.Income;
import com.example.deluxe.Entity.Pay;
import com.example.deluxe.Entity.Statistic;
import com.example.deluxe.Interface.Model.StatisticInterface;

import java.util.Date;
import java.util.List;

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

	public void getStatistic(String email, final StatisticInterface statisticInterface) {

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
}