package com.otsembo.kimondo.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.otsembo.kimondo.R
import com.otsembo.kimondo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // drawer setup
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_host) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfig = AppBarConfiguration(navController.graph, binding.rootDrawer)
        binding.appToolbar.setupWithNavController(navController, appBarConfig)
        binding.appNavigationView.setupWithNavController(navController)
    }


}