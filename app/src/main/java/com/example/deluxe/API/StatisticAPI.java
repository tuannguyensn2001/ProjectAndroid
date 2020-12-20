package com.example.deluxe.API;

import com.example.deluxe.Entity.Object;
import com.example.deluxe.Entity.Statistic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StatisticAPI {
	@GET("statistic/show/{email}")
	Call<Statistic> getStatistic(@Path("email") String email);

	@GET("statistic/month/{id}")
	Call<List<Object.getPerMonthAPI>> getPerMonth(@Path("id") String id);

	@GET("statistic/limit/{id}")
	Call<List<Object.getPerMonthAPI>> getLimit(@Path("id") String id);
}