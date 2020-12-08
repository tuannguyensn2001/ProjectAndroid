package com.example.deluxe.API;

import com.example.deluxe.Entity.Statistic;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StatisticAPI
{
    @GET("statistic/show/{email}")
    Call<Statistic> getStatistic(@Path("email") String email);

}
