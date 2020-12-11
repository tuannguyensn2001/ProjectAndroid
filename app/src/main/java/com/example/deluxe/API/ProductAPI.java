package com.example.deluxe.API;

import com.example.deluxe.Entity.Collection;
import com.example.deluxe.Entity.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductAPI {

	@GET("products")
	Call<List<Collection>> getProducts();

	@GET("product/show/{id}")
	Call<Product> getProduct(@Path("id") int id);

}
