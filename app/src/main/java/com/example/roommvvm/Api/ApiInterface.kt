package com.example.roommvvm.Api

import com.example.roommvvm.Model.RestuarantModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/v1/pages/restaurants")
    suspend fun getRestaurants(@Query("lat") lat:String?, @Query("lon") lon:String? ) : Response<RestuarantModel>

}