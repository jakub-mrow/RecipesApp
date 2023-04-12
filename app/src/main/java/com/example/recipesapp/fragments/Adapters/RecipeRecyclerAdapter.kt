package com.example.recipesapp.fragments.Adapters

import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recipesapp.Recipe
import com.example.recipesapp.databinding.RecyclerViewRecipeBinding
import com.squareup.picasso.Picasso

class RecipeRecyclerAdapter(private val recipes: ArrayList<Recipe>) : RecyclerView.Adapter<RecipeRecyclerAdapter.MyViewHolder>() {
    inner class MyViewHolder(binding: RecyclerViewRecipeBinding) : ViewHolder(binding.root) {
        val recipeImage = binding.recipeImage
        val recipeText = binding.recipeText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val recyclerViewRecipeBinding = RecyclerViewRecipeBinding.inflate(inflater, parent, false)
        return MyViewHolder(recyclerViewRecipeBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.recipeText.text = recipes[position].title
        Picasso.get().load(recipes[position].image_name).into(holder.recipeImage)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}