package com.example.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.navigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        drawerLayout=binding.drawerLayout

        val navController=this.findNavController(R.id.NavHostFragment)
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)

        navController.addOnDestinationChangedListener{ nc: NavController, nd: NavDestination
                                                       , bundle: Bundle? ->
            if (nd.id ==nc.graph.startDestinationId){
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }else{
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }

        appBarConfiguration= AppBarConfiguration(navController.graph,drawerLayout)

        NavigationUI.setupWithNavController(binding.navView,navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController=this.findNavController(R.id.NavHostFragment)
        return NavigationUI.navigateUp(navController,appBarConfiguration)
    }
}