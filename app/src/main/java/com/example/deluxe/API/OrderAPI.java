package com.example.deluxe.API;

import com.example.deluxe.Entity.Object;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderAPI {
	@FormUrlEncoded
	@POST("order/create")
	Call<java.lang.Object> createOrder(
			@Field("user") String user,
			@Field("product") String product,
			@Field("money") double money,
			@Field("user_id") String id
	);

	@GET("order/show/{id}")
	Call<List<Object.ResponeOrderAPI>> getDetailOrder(@Path("id") String id);
}
