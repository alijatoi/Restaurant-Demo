package com.example.roommvvm.Api

import com.example.roommvvm.Helper.Helper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilities {

    fun getInstance() : Retrofit {

        return  Retrofit.Builder()
            .baseUrl(Helper.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}