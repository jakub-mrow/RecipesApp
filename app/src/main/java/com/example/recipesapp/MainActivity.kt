package com.example.recipesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recipesapp.databinding.ActivityMainBinding
import com.example.recipesapp.fragments.Adapters.ViewPagerAdapter
import com.example.recipesapp.fragments.FastFoodFragment
import com.example.recipesapp.fragments.HomeFragment
import com.example.recipesapp.fragments.VeganFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpTabs()
    }

    private fun setUpTabs(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
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