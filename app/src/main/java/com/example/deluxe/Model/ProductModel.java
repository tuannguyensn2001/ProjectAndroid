package com.example.deluxe.Model;

import android.util.Log;

import com.example.deluxe.API.CoreAPI;
import com.example.deluxe.API.ProductAPI;
import com.example.deluxe.Entity.Collection;
import com.example.deluxe.Entity.Product;
import com.example.deluxe.Interface.Model.ProductModelInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductModel {
	private Retrofit retrofit;

	public ProductModel() {
		this.retrofit = CoreAPI.build();
	}

	public void getProducts(final ProductModelInterface productModelInterface) {
		ProductAPI productAPI = this.retrofit.create(ProductAPI.class);
		Call<List<Collection>> call = productAPI.getProducts();
		call.enqueue(new Callback<List<Collection>>() {
			@Override
			public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {
				Log.e("hello", "hello");
				List<Collection> list = response.body();
				productModelInterface.dataIsLoaded(list);
			}

			@Override
			public void onFailure(Call<List<Collection>> call, Throwable t) {
				Log.e("hello", "hello");
			}
		});
	}

	public void getProduct(int id, final ProductModelInterface productModelInterface) {
		ProductAPI productAPI = this.retrofit.create(ProductAPI.class);
		Call<Product> call = productAPI.getProduct(id);
		call.enqueue(new Callback<Product>() {
			@Override
			public void onResponse(Call<Product> call, Response<Product> response) {
				Product product = response.body();
				productModelInterface.dataIsLoaded(product);
			}

			@Override
			public void onFailure(Call<Product> call, Throwable t) {

			}
		});
	}


}
