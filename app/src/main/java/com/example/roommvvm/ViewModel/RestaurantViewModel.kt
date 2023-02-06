package com.example.roommvvm.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roommvvm.Database.RestaurantEntity
import com.example.roommvvm.Repository.RestaurantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantViewModel (private val restaurantRepository: RestaurantRepository): ViewModel(){

    fun restaurants() {

        viewModelScope.launch(Dispatchers.IO) {

            restaurantRepository.getRestaurants()
        }
    }

    val restaurants : LiveData<List<RestaurantEntity>>

    get() = restaurantRepository.restaurants


     fun updateData(id:String,isFavourite:Boolean) {
         viewModelScope.launch(Dispatchers.IO) {
             restaurantRepository.updateRestaurants(id, isFavourite)
         }
     }
}