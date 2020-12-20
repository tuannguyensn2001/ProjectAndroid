package com.example.deluxe.API;

import com.example.deluxe.Entity.CartItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CartAPI {
	@FormUrlEncoded
	@PUT("cart/add")
	Call<Object> addToCart(
			@Field("user_id") String user_id,
			@Field("product_id") int product_id
	);


	@GET("cart/show/{id}")
	Call<List<CartItem>> getCart(@Path("id") String user_id);

	@PUT("cart/increase")
	Call<CartItem> increaseNumber(@Body CartItem cartItem);

	@PUT("cart/decrease")
	Call<CartItem> decreaseNumber(@Body CartItem cartItem);

	@PUT("cart/delete")
	Call<CartItem> delete(@Body CartItem cartItem);
}