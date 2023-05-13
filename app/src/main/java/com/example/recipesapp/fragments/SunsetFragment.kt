package com.example.recipesapp.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recipesapp.R

class SunsetFragment : Fragment() {

    private lateinit var mSceneView: View
    private lateinit var mSunView: View
    private lateinit var mSkyView: View

    companion object {
        fun newInstance(): SunsetFragment {
            return SunsetFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sunset, container, false)
        mSceneView = view
        mSunView = view.findViewById(R.id.sun)
        mSkyView = view.findViewById(R.id.sky)

        mSceneView.setOnClickListener {
            startAnimation()
        }

        return view
    }

    private fun startAnimation() {
        val sunYStart = mSunView.top.toFloat()
        val sunYEnd = mSkyView.height.toFloat()

        val heightAnimator = ObjectAnimator.ofFloat(mSunView, "y", sunYStart, sunYEnd)
            .setDuration(3000)
        heightAnimator.start()

    }

}
