package com.example.deluxe.Model;

import com.example.deluxe.API.CoreAPI;
import com.example.deluxe.API.StatisticAPI;
import com.example.deluxe.Entity.Statistic;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StatisticModel
{
    private Retrofit retrofit;
    private StatisticAPI statisticAPI;

    public StatisticModel()
    {
        this.retrofit = CoreAPI.build();
        this.statisticAPI = retrofit.create(StatisticAPI.class);
    }

    public void getStatistic()
    {
       Call<Statistic> call = this.statisticAPI.getStatistic("daigiachau@gmail.com");

       call.enqueue(new Callback<Statistic>() {
           @Override
           public void onResponse(Call<Statistic> call, Response<Statistic> response) {
               Object object = response.body();
           }

           @Override
           public void onFailure(Call<Statistic> call, Throwable t) {

           }
       });
    }
}
