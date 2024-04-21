package com.example.utsanmp160421024.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.utsanmp160421024.databinding.ActivityMainBinding
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentContainerView
import com.example.utsanmp160421024.R

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        binding.navView.visibility = View.GONE

        setSupportActionBar(binding.toolbar)
        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this, navController)
        NavigationUI.setupWithNavController(binding.navView, navController)

        setSupportActionBar(binding.toolbar)
        binding.BottomNav.setupWithNavController(navController)

        Log.d("TAG", "Main Activity Binding")

        navController.addOnDestinationChangedListener{ _,destination, _ ->
            if(destination.id == R.id.loginFragment || destination.id == R.id.registerFragment){
                binding.BottomNav.visibility = View.GONE
                binding.toolbar.visibility = View.GONE
            }
            else{
                binding.BottomNav.visibility = View.VISIBLE
                binding.toolbar.visibility = View.VISIBLE
            }
        }

        binding.toolbar.setNavigationOnClickListener{
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }
}