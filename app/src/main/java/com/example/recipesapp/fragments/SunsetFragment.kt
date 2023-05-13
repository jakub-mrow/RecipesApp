package com.example.recipesapp.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recipesapp.R
import com.example.recipesapp.databinding.FragmentSunsetBinding

class SunsetFragment : Fragment() {

    private lateinit var binding: FragmentSunsetBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = FragmentSunsetBinding.inflate(layoutInflater)
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSunsetBinding.inflate(inflater, container, false)

        binding.sun.setOnClickListener {
            startAnimation()
        }

        binding.sky.setOnClickListener {
            findNavController().navigate(R.id.action_sunsetFragment_to_viewPagerFragment)

        }

        return binding.root
    }

    private fun startAnimation() {
        // Animate the sun
        val sunYStart = binding.sun.top.toFloat()
        val sunYEnd = 1150.toFloat()
        val heightAnimator = ObjectAnimator.ofFloat(binding.sun, "y", sunYStart, sunYEnd)
            .setDuration(3000)
        heightAnimator.interpolator = AccelerateInterpolator()
        heightAnimator.start()


    }


}

