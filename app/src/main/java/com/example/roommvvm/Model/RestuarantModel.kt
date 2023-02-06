package com.example.roommvvm.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roommvvm.Database.RestaurantEntity
import com.google.gson.annotations.SerializedName
import java.lang.Boolean.FALSE

data class RestuarantModel(

    @SerializedName("sections")
    val sections: List<Section>)
{
    data class Section(
        @SerializedName("items")
        val items: List<Item>
    ) {
        data class Item(
            @SerializedName("image")
            val image: Image,
            @SerializedName("venue")
            val venue: Venue
        ) {
            data class Image(
                @SerializedName("url")
                val url: String
            )
            data class Venue(
                @SerializedName("id")
                val id: String,
                @SerializedName("name")
                val name: String,
                @SerializedName("short_description")
                val shortDescription: String,
                val isFavourite : Boolean = FALSE
            )
        }
    }
}


fun RestuarantModel.Section.Item.toEntityModel(): RestaurantEntity {
    return RestaurantEntity(id = this.venue.id, name = this.venue.name,
        shortDescription = this.venue.shortDescription, image = this.image.url, isFavourite = this.venue.isFavourite)
}
