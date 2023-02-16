package com.example.roommvvm.UI


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roommvvm.R
import com.example.roommvvm.Repository.RestaurantRepository
import com.example.roommvvm.UI.RecyclerView.RecyclerViewAdapter
import com.example.roommvvm.ViewModel.RestaurantViewModel
import com.example.roommvvm.ViewModel.RestaurantViewModelFactory
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout

class MainActivity : AppCompatActivity() {

    private  lateinit var restaurantViewModel : RestaurantViewModel
    private lateinit var recyclerView : RecyclerView
    private lateinit var shimmerLayout : ShimmerFrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview)
        shimmerLayout = findViewById(R.id.shimmer)
//shimmer is layout animation, that is running while data is being fetched/filled
        shimmerLayout.startShimmer()

        if (Network.checkConnectivity(this))
        {
            showRestaurants()
        }

        else
        {
            shimmerLayout.stopShimmer()
            shimmerLayout.visibility = View.GONE
            Toast.makeText(this,"Sorry, Please Connect your Phone with Internet",Toast.LENGTH_LONG).show()
        }


    }


    private fun showRestaurants() {

        val restaurantRepository = RestaurantRepository(applicationContext)


        restaurantViewModel =
            ViewModelProvider(this, RestaurantViewModelFactory(restaurantRepository))
                .get(RestaurantViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(this)


        val adapter = RecyclerViewAdapter (object : RecyclerViewAdapter.ItemClickListener {
            override fun onFavouriteClick(id: String, isFavourite : Boolean) {
                restaurantViewModel.updateData(id,isFavourite)
            }
        })

        restaurantViewModel.restaurants.observe(this, Observer {
            adapter.setRestaurant(it)
            shimmerLayout.stopShimmer()
            shimmerLayout.visibility = View.GONE

        })


        recyclerView.adapter = adapter

        restaurantViewModel.restaurants()

    }

}

