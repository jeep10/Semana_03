package com.example.cafe.api;

import com.example.cafe.entity.User;

import java.util.List;

import retrofit2.Call;

import retrofit2.http.GET;



public interface ServiceApi {



    @GET("users")

    public abstract Call<List<User>> listaUsuario();

}
