package com.otsembo.kimondo.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import coil.load
import com.otsembo.kimondo.R
import com.otsembo.kimondo.databinding.ActivityMainBinding
import com.otsembo.kimondo.databinding.NavHeaderBinding

class MainActivity : AppCompatActivity() {

    // binding
    private lateinit var binding: ActivityMainBinding

    // viewmodel
    private val viewmodel: MainActivityVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.appNavigationView?.let {
            it.getHeaderView(0).run {
                (this.findViewById<ImageView>(R.id.nav_header_layout)).load(viewmodel.image)
            }
        }

        // drawer setup
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_host) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfig = AppBarConfiguration(navController.graph, binding.rootDrawer)
        setSupportActionBar(binding.appToolbar)
        binding.appToolbar.setupWithNavController(navController, appBarConfig)
        binding.appNavigationView?.setupWithNavController(navController)
        binding.navigationRail?.setupWithNavController(navController)
    }

    // TODO 1: SHOW DETAIL PAGES FOR EVERY LIST OF ITEMS
    // TODO 2: ADD DATE FUNCTIONALITY (view VALENTINE DAY's nasa photos) - NASA SERVICE



}