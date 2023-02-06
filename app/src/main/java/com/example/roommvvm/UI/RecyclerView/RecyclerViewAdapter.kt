package com.example.roommvvm.UI.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.roommvvm.Database.RestaurantEntity
import com.example.roommvvm.R


class RecyclerViewAdapter(private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<RecyclerViewAdapter.UserViewHolder>() {

    private var maxItems: Int = 15
    private var restaurants = mutableListOf<RestaurantEntity>()

    fun setRestaurant(newData: List<RestaurantEntity>) {
        restaurants.clear()
        restaurants.addAll(newData)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val  view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_item, parent, false)
        return UserViewHolder(view)
    }




    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val restaurant = restaurants[position]

        holder.title.text = restaurant.name
        holder.descriptionText.text = restaurant.shortDescription

        Glide.with(holder.itemView.context).load(restaurant.image)
            .into(holder.image)

        if (restaurant.isFavourite) holder.fav.setImageResource(R.drawable.favorite)
        else holder.fav.setImageResource(R.drawable.baseline_favorite)


        holder.fav.setOnClickListener {
            restaurant.isFavourite = !restaurant.isFavourite

            if (restaurant.isFavourite) holder.fav.setImageResource(R.drawable.favorite)

            else holder.fav.setImageResource(R.drawable.baseline_favorite)

            itemClickListener.onFavouriteClick(restaurant.id, restaurant.isFavourite)

        }
    }

    override fun getItemCount() = Math.min(restaurants.size, maxItems)




    class UserViewHolder(itemView: View) : ViewHolder(itemView)
    {
        val title : TextView = itemView.findViewById(R.id.name)
        val descriptionText : TextView = itemView.findViewById(R.id.description)
        val image : ImageView = itemView.findViewById(R.id.imageview)
        val fav : ImageView = itemView.findViewById(R.id.favIcon)

    }


    interface ItemClickListener {
        fun onFavouriteClick(id: String,isFavourite:Boolean)
    }
}
