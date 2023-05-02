package com.example.recipesapp.fragments.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recipesapp.Recipe
import com.example.recipesapp.databinding.RecyclerViewRecipeBinding
import com.squareup.picasso.Picasso

class RecipeRecyclerAdapter(private val recipes: ArrayList<Recipe>) : RecyclerView.Adapter<RecipeRecyclerAdapter.MyViewHolder>() {

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    private var mListener: onItemClickListener? = null

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    inner class MyViewHolder(binding: RecyclerViewRecipeBinding, listener: onItemClickListener) : ViewHolder(binding.root) {
        val recipeImage = binding.recipeImage
        val recipeText = binding.recipeText

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val recyclerViewRecipeBinding = RecyclerViewRecipeBinding.inflate(inflater, parent, false)
        return MyViewHolder(recyclerViewRecipeBinding, mListener!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.recipeText.text = recipes[position].title
        Picasso.get().load(recipes[position].image_name).into(holder.recipeImage)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}