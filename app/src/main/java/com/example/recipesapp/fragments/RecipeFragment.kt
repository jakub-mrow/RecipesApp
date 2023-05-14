package com.example.recipesapp.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recipesapp.R
import com.example.recipesapp.databinding.FragmentRecipeBinding
import com.squareup.picasso.Picasso
import kotlin.random.Random


class RecipeFragment: Fragment() {
    private lateinit var binding: FragmentRecipeBinding
    private var baseSeconds: Int = 30
    private var seconds: Int = 30
    private var running: Boolean = false
    private var wasRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        baseSeconds = Random.nextInt(1, 6) * 30
        seconds = baseSeconds

        binding = FragmentRecipeBinding.inflate(inflater, container, false)

        binding.recipeName.text = arguments?.getString("recipeTitle")
        binding.recipeIngredients.text = arguments?.getString("recipeIngredients")
        binding.recipeText.text = arguments?.getString("recipeInstructions")

        binding.startButton.setOnClickListener {
            onClickStart()
        }

        binding.stopButton.setOnClickListener {
            onClickStop()
        }

        binding.resetButton.setOnClickListener {
            onClickReset()
        }

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

        runStoper()

        return binding.root
    }


    override fun onPause() {
        super.onPause()
        wasRunning = running
        running = false
    }

    override fun onResume() {
        super.onResume()
        if (wasRunning){
            running = true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("seconds", seconds)
        outState.putBoolean("running", running)
        outState.putBoolean("wasRunning", wasRunning)
    }

    private fun onClickStart(){
        running = true
    }

    private fun onClickStop(){
        running = false
    }

    private fun onClickReset(){
        running = false
        seconds = baseSeconds
    }

    private fun runStoper(){
        val timeView = binding.timeView
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = seconds % 3600 / 60
                val secs = seconds % 60
                val time = String.format("%d:%02d:%02d", hours, minutes, secs)
                timeView.text = time
                if (running) {
                    if (seconds > 0) {
                        seconds--
                    } else {
                        running = false
                        Toast.makeText(context, "Time's up!", Toast.LENGTH_SHORT).show()
                    }
                }
                handler.postDelayed(this, 1000)
            }
        })

    }


}