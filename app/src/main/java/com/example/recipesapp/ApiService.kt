package com.example.recipesapp

import retrofit2.Call
import retrofit2.http.GET
import java.util.ArrayList


interface ApiService {
    @GET("/recipes")
    fun getRecipes(): Call<ArrayList<Recipe>>

    @GET("/cake_recipes")
    fun getCakeRecipes(): Call<ArrayList<Recipe>>

    @GET("/salad_recipes")
    fun getSaladRecipes(): Call<ArrayList<Recipe>>
}