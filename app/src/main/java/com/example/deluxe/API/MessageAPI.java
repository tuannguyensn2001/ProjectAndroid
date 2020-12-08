package com.example.deluxe.API;

import com.example.deluxe.Entity.LastMessage;
import com.example.deluxe.Model.MessageModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MessageAPI {
	@POST("user/edit")
	Call<ArrayList<LastMessage>> getDetailMessage(@Body MessageModel.ListMessage list);


}