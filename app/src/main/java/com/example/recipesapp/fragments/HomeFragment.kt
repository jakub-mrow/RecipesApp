package com.example.recipesapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipesapp.ApiService
import com.example.recipesapp.databinding.FragmentHomeBinding
import com.example.recipesapp.R
import com.example.recipesapp.Recipe
import com.example.recipesapp.fragments.Adapters.RecipeRecyclerAdapter
import com.example.recipesapp.fragments.HomeFragment.Companion.newInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var recipesList: ArrayList<Recipe>
    private lateinit var mRecyclerViewAdapter: RecipeRecyclerAdapter


    companion object {
        fun newInstance(recipeId: Int, recipeTitle: String, recipeIngredients: String, recipeInstructions: String, recipeImageUrl: String): RecipeFragment {
            val fragment = RecipeFragment()
            val args = Bundle()
            args.putInt("recipeId", recipeId)
            args.putString("recipeTitle", recipeTitle)
            args.putString("recipeIngredients", recipeIngredients)
            args.putString("recipeInstructions", recipeInstructions)
            args.putString("recipeImageUrl", recipeImageUrl)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        recipesList = ArrayList()

        mRecyclerViewAdapter = RecipeRecyclerAdapter(recipesList)
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

                    binding.recipesRecyclerView.adapter = mRecyclerViewAdapter

                }
            }

            override fun onFailure(call: Call<ArrayList<Recipe>?>, t: Throwable) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

        })


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRecyclerViewAdapter.setOnItemClickListener(object : RecipeRecyclerAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val navigationId = findNavController().currentDestination?.label
                //Toast.makeText(context, "Navigation id. $navigationId", Toast.LENGTH_SHORT).show()
                val bundle = Bundle()
                val currentRecipe: Recipe = recipesList.get(position)

                bundle.putInt("recipeId", currentRecipe.id)
                bundle.putString("recipeTitle", currentRecipe.title)
                bundle.putString("recipeIngredients", currentRecipe.ingredients)
                bundle.putString("recipeInstructions", currentRecipe.instructions)
                Log.d("INGREDIENTS", currentRecipe.ingredients)
                Log.d("INSTRUCTIONS", currentRecipe.instructions)
                bundle.putString("recipeImageUrl", currentRecipe.image_name)

                val isTablet = resources.getBoolean(R.bool.isTablet)

                if (isTablet){
                    val recipeFragment: RecipeFragment = newInstance(currentRecipe.id, currentRecipe.title, currentRecipe.ingredients, currentRecipe.instructions, currentRecipe.image_name)
                    val ft = requireActivity().supportFragmentManager.beginTransaction()
                    ft.replace(R.id.fragment_container, recipeFragment)
                    ft.addToBackStack(null)
                    ft.commit()
                } else {
                    findNavController().navigate(R.id.action_viewPagerFragment_to_recipeFragment, bundle)
                }

            }
        })
    }

}