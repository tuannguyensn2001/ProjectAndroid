package com.example.deluxe.API;

import com.example.deluxe.Entity.Address;
import com.example.deluxe.Entity.CartItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OrderAPI
{
    @FormUrlEncoded
    @POST("order/create")
    Call<Object> createOrder(
            @Field("user") String user,
            @Field("product") String product,
            @Field("money") double money,
            @Field("user_id") String id
    );
}
