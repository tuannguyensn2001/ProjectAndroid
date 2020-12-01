package com.example.deluxe.Model;

import com.example.deluxe.API.AddressAPI;
import com.example.deluxe.API.CoreAPI;
import com.example.deluxe.Entity.Address;
import com.example.deluxe.Interface.Model.AddressInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddressModel {
	private Retrofit retrofit;
	private AddressAPI addressAPI;

	public AddressModel() {
		this.retrofit = CoreAPI.build();
		this.addressAPI = retrofit.create(AddressAPI.class);
	}

	public void getAddress(String user_id, final AddressInterface addressInterface) {
		Call<Address> call = this.addressAPI.getAddress(user_id);

		call.enqueue(new Callback<Address>() {
			@Override
			public void onResponse(Call<Address> call, Response<Address> response) {
				addressInterface.dataIsLoaded(response.body());
			}

			@Override
			public void onFailure(Call<Address> call, Throwable t) {

			}
		});
	}
}
