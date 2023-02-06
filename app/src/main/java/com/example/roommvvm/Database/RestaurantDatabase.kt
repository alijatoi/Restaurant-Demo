package com.example.roommvvm.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [RestaurantEntity::class], version = 7)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract  fun restaurantDao() : RestaurantDao


    companion object{
        private  var INSTANCE : RestaurantDatabase? = null

        fun getDatabase(context : Context) : RestaurantDatabase{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                  RestaurantDatabase::class.java,
                    "restaurantDB",
                ) .fallbackToDestructiveMigration()
                    .build() }

            return INSTANCE!!

        }
    }
}