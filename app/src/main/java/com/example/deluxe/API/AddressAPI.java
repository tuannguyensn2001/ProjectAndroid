package com.example.deluxe.API;

import com.example.deluxe.Entity.Address;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AddressAPI
{
    @GET("address/show/{id}")
    Call<Address> getAddress(@Path("id") String id);
}
