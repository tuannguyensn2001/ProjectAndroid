package com.example.deluxe.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoreAPI {
	public static Retrofit build() {
		return new Retrofit.Builder()
				.baseUrl("http:192.168.1.88/api/mobile/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}
}
