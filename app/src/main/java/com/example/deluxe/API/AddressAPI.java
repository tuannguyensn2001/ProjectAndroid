package com.example.deluxe.API;

import com.example.deluxe.Entity.Address;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AddressAPI {
	@GET("address/show/{id}")
	Call<Address> getAddress(@Path("id") String id);

	@FormUrlEncoded
	@PUT("address/edit")
	Call<Object> editAddress(
			@Field("user_id") String user_id,
			@Field("fullname") String fullname,
			@Field("phone") String phone,
			@Field("address") String address
	);
}