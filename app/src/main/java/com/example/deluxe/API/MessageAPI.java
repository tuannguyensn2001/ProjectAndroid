package com.example.deluxe.API;

import com.example.deluxe.Entity.LastMessage;
import com.example.deluxe.Entity.Message;
import com.example.deluxe.Model.MessageModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MessageAPI
{
    @POST("user/edit")
    Call<List<LastMessage>> getDetailMessage(@Body MessageModel.ListMessage list);


}
