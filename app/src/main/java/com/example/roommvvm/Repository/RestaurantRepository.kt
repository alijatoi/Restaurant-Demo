package com.example.roommvvm.Repository

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roommvvm.Api.ApiInterface
import com.example.roommvvm.Api.ApiUtilities
import com.example.roommvvm.Database.RestaurantDatabase
import com.example.roommvvm.Database.RestaurantEntity
import com.example.roommvvm.Helper.Helper
import com.example.roommvvm.Model.toEntityModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RestaurantRepository(private val context: Context)

{

    private val database = RestaurantDatabase.getDatabase(context)


    private lateinit var restaurantList : List<RestaurantEntity>
    private  val restaurantData = MutableLiveData<List<RestaurantEntity>>()

    val restaurants : LiveData<List<RestaurantEntity>>
        get() = restaurantData

    suspend fun getRestaurants() {
        val apiInterface = ApiUtilities.getInstance().create(ApiInterface::class.java)

        try {

            val result = apiInterface.getRestaurants(Helper.LATITUDE, Helper.LONGITUDE)
             if (result.isSuccessful) {

                 restaurantList = result.body()!!.sections[1].items.map {
                it.toEntityModel()
            }
            withContext(Dispatchers.IO) {
                databaseOperation(restaurantList)
            }

        }}

        catch(e:Exception){
            (context as Activity).runOnUiThread {
                Toast.makeText(context, "Sorry, Error Occured, Error: ${e.message.toString()}", Toast.LENGTH_LONG).show()
            }
        }
    }


private suspend fun databaseOperation(restaurantList : List<RestaurantEntity>){
    withContext(Dispatchers.IO){


        database.restaurantDao().insertItems(restaurantList)

        val list = database.restaurantDao().getItems()
        restaurantData.postValue(list)

    }
}

    suspend fun updateRestaurants(id: String, isFavourite:Boolean,) {

        database.restaurantDao().update(id,isFavourite)

    }

}


