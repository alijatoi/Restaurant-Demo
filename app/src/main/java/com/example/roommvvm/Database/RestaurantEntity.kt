package com.example.roommvvm.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "items",indices = [Index(value = ["id"], unique = true)])
data class RestaurantEntity (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    val name: String,
    val shortDescription: String,
    val image : String,
    @ColumnInfo(name ="value")
    var isFavourite : Boolean)
