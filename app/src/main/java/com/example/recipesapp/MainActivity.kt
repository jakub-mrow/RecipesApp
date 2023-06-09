package com.example.recipesapp

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.recipesapp.databinding.ActivityMainBinding
import com.example.recipesapp.fragments.BlankRecipeFragment
import com.example.recipesapp.fragments.RecipeFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        val isTablet = resources.getBoolean(R.bool.isTablet)

        if (isTablet){
            val detailsFragment = BlankRecipeFragment()
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragment_container, detailsFragment)
            ft.addToBackStack(null)
            ft.commit()
        }

        setContentView(binding.root)

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        setSupportActionBar(binding.toolbarLayout)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        navController.navigate(R.id.animationFragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

}