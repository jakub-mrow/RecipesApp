package com.example.recipesapp.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recipesapp.R
import com.example.recipesapp.databinding.FragmentAnimationBinding


class AnimationFragment : Fragment() {

    private lateinit var binding: FragmentAnimationBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = FragmentAnimationBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimationBinding.inflate(inflater, container, false)

        binding.cherry.setOnClickListener {
            startAnimation()
        }

        binding.sky.setOnClickListener {
            findNavController().navigate(R.id.action_animationFragment_to_viewPagerFragment)

        }

        return binding.root
    }

    private fun startAnimation() {

        val cherryYStart = binding.cherry.top.toFloat()
        val cherryYEnd = 1150.toFloat()
        val heightAnimator = ObjectAnimator.ofFloat(binding.cherry, "y", cherryYStart, cherryYEnd)
            .setDuration(3000)
        heightAnimator.start()

    }


}

