package com.example.mydiary.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.mydiary.R

import com.example.mydiary.databinding.MainScreenBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainScreenBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.itemActiveIndicatorColor = getColorStateList(R.color.white)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.logbook -> {
                    navController.navigate(R.id.logbookFragment)
                    true
                }

                R.id.statistics -> {
                    navController.navigate(R.id.statisticsFragment)
                    true
                }

                R.id.settings -> {
                    navController.navigate(R.id.settingsFragment)
                    true
                }

                else -> false
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.logbookFragment, R.id.statisticsFragment, R.id.settingsFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                else -> {
                    binding.bottomNavigationView.visibility = View.GONE
                }
            }
        }

        if (savedInstanceState == null) {
            navController.navigate(R.id.logbookFragment)
        }

    }
}