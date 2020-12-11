package com.example.deluxe.Model;

import com.example.deluxe.API.CoreAPI;
import com.example.deluxe.API.OrderAPI;
import com.example.deluxe.Entity.Address;
import com.example.deluxe.Entity.CartItem;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderModel {
	private Retrofit retrofit;
	private OrderAPI orderAPI;

	public OrderModel() {
		this.retrofit = CoreAPI.build();
		this.orderAPI = retrofit.create(OrderAPI.class);
	}

	public void create(List<CartItem> list, Address address, double money) {
		Gson gson = new Gson();
		String product = gson.toJson(list);
		String user = gson.toJson(address);
		Call<Object> call = this.orderAPI.createOrder(user, product, money, Auth.getInstance().user().getUid());

		call.enqueue(new Callback<Object>() {
			@Override
			public void onResponse(Call<Object> call, Response<Object> response) {
				Object object = response.body();
			}

			@Override
			public void onFailure(Call<Object> call, Throwable t) {

			}
		});
	}
}
