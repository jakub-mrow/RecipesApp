package com.example.recipesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipesapp.R
import com.example.recipesapp.databinding.FragmentViewPagerBinding
import com.example.recipesapp.fragments.Adapters.ViewPagerAdapter

class ViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(layoutInflater)

        setUpTabs()
        return binding.root
    }

    private fun setUpTabs(){
        val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
        adapter.addFragment(HomeFragment(), "Home")
        adapter.addFragment(VeganFragment(), "Vegan")
        adapter.addFragment(FastFoodFragment(), "FastFood")
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_fastfood_24)
        binding.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_home_24)
        binding.tabs.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_food_bank_24)
    }
}
