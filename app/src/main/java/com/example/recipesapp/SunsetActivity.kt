package com.example.recipesapp


import androidx.fragment.app.Fragment
import com.example.recipesapp.fragments.SunsetFragment

class SunsetActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment {
        return SunsetFragment.newInstance()
    }
}
