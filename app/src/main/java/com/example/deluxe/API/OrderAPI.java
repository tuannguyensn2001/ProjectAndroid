package com.example.deluxe.API;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OrderAPI {
	@FormUrlEncoded
	@POST("order/create")
	Call<Object> createOrder(
			@Field("user") String user,
			@Field("product") String product,
			@Field("money") double money,
			@Field("user_id") String id
	);
}
