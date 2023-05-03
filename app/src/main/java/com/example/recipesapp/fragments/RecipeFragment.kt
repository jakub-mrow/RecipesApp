package com.example.recipesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.recipesapp.R
import com.example.recipesapp.databinding.FragmentRecipeBinding
import com.squareup.picasso.Picasso


class RecipeFragment: Fragment() {
    private lateinit var binding: FragmentRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)

        binding.recipeName.text = arguments?.getString("recipeTitle")
        binding.recipeIngredients.text = arguments?.getString("recipeIngredients")
        binding.recipeText.text = arguments?.getString("recipeInstructions")

        binding.floatingButton.setOnClickListener{
            Toast.makeText(context, "Floating action button clicked!", Toast.LENGTH_SHORT).show()
        }

        Picasso.get().load(arguments?.getString("recipeImageUrl")).into(binding.recipeImage)

        val toolbar: androidx.appcompat.widget.Toolbar = binding.toolbarLayout
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.apply {
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        }


        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.viewPagerFragment)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        return binding.root
    }


}