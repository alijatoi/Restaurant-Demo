package com.example.roommvvm.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roommvvm.Model.RestuarantModel


@Dao
interface RestaurantDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
   suspend fun insertItems(item : List<RestaurantEntity>)

   @Query("SELECT * FROM items")
   suspend fun getItems() : List<RestaurantEntity>

    @Query("UPDATE items SET value = :isFavourite WHERE id = :id")
    fun update(id: String, isFavourite: Boolean)

}