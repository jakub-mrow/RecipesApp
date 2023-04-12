package com.example.recipesapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipesapp.ApiService
import com.example.recipesapp.databinding.FragmentHomeBinding
import com.example.recipesapp.R
import com.example.recipesapp.Recipe
import com.example.recipesapp.fragments.Adapters.RecipeRecyclerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList


class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var recipesList: ArrayList<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        recipesList = ArrayList()

        val recyclerViewAdapter = RecipeRecyclerAdapter(recipesList)
        binding.recipesRecyclerView.layoutManager = GridLayoutManager(context, 2)

        val retrofit: Retrofit = Retrofit.Builder().baseUrl("http://18.185.8.100/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api: ApiService = retrofit.create(ApiService::class.java)

        val recipesCall: Call<ArrayList<Recipe>> = api.getRecipes()

        recipesCall.enqueue(object: Callback<ArrayList<Recipe>?>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ArrayList<Recipe>?>,
                response: Response<ArrayList<Recipe>?>
            ) {
                if (response.isSuccessful){
                    recipesList.clear()
                    for (recipe in response.body()!!){
                        recipesList.add(recipe)
                    }
                    binding.recipesRecyclerView.adapter?.notifyDataSetChanged()

                    binding.recipesRecyclerView.adapter = recyclerViewAdapter

                }
            }

            override fun onFailure(call: Call<ArrayList<Recipe>?>, t: Throwable) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

        })

        return binding.root
    }

}