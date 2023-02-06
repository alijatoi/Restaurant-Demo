package com.example.roommvvm.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roommvvm.Repository.RestaurantRepository

class RestaurantViewModelFactory(private val restaurantRepository: RestaurantRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RestaurantViewModel(restaurantRepository) as T

    }

}