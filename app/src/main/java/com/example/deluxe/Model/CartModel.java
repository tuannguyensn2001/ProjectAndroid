package com.example.deluxe.Model;

import com.example.deluxe.API.CartAPI;
import com.example.deluxe.API.CoreAPI;
import com.example.deluxe.Entity.CartItem;
import com.example.deluxe.Interface.Model.CartInterface;
import com.example.deluxe.Interface.Model.CartItemInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartModel {
	private Retrofit retrofit;
	private CartAPI cartAPI;

	public CartModel() {
		this.retrofit = CoreAPI.build();
		this.cartAPI = this.retrofit.create(CartAPI.class);
	}

	public void addToCart(String user_id, int product_id) {
		Call<Object> call = this.cartAPI.addToCart(user_id, product_id);
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

	public void getCart(String user_id, final CartInterface cartInterface) {
		Call<List<CartItem>> call = cartAPI.getCart(user_id);
		call.enqueue(new Callback<List<CartItem>>() {
			@Override
			public void onResponse(Call<List<CartItem>> call, Response<List<CartItem>> response) {
				List<CartItem> list = response.body();
				cartInterface.dataIsLoaded(list);
			}

			@Override
			public void onFailure(Call<List<CartItem>> call, Throwable t) {

			}
		});
	}

	public void increase(CartItem cartItem, final CartItemInterface cartItemInterface) {
		Call<CartItem> call = this.cartAPI.increaseNumber(cartItem);
		call.enqueue(new Callback<CartItem>() {
			@Override
			public void onResponse(Call<CartItem> call, Response<CartItem> response) {
				cartItemInterface.dataIsLoaded(response.body());
			}

			@Override
			public void onFailure(Call<CartItem> call, Throwable t) {

			}
		});
	}

	public void decrease(CartItem cartItem, final CartItemInterface cartItemInterface) {
		Call<CartItem> call = this.cartAPI.decreaseNumber(cartItem);

		call.enqueue(new Callback<CartItem>() {
			@Override
			public void onResponse(Call<CartItem> call, Response<CartItem> response) {
				cartItemInterface.dataIsLoaded(response.body());
			}

			@Override
			public void onFailure(Call<CartItem> call, Throwable t) {

			}
		});
	}

	public void delete(CartItem cartItem, final CartItemInterface cartItemInterface) {
		Call<CartItem> call = this.cartAPI.delete(cartItem);

		call.enqueue(new Callback<CartItem>() {
			@Override
			public void onResponse(Call<CartItem> call, Response<CartItem> response) {
				cartItemInterface.dataIsLoaded(response.body());
			}

			@Override
			public void onFailure(Call<CartItem> call, Throwable t) {

			}
		});
	}
}
