package com.example.harjoitus3_1_mobiiliohjelmointi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.harjoitus3_1_mobiiliohjelmointi.Fragments.EndFragmentDirections
import com.example.harjoitus3_1_mobiiliohjelmointi.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bind: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        drawerLayout =bind.drawerLayout

        val navController = findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupWithNavController(bind.navView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.myNavHostFragment)
        //return navController.navigateUp()
        //return NavigationUI.navigateUp(navController, drawerLayout)
        return when(navController.currentDestination?.id) {
            R.id.endFragment -> {
                // custom behavior here
                navController.navigate(EndFragmentDirections.actionEndFragmentToStartFragment())
                true
            }
            else -> NavigationUI.navigateUp(navController, drawerLayout)
        }
    }

    override fun onResume() {
        Timber.i("TIMBER onResume called")
        Log.i("MAIN", "onResume called")
        super.onResume()
    }
    override fun onPause() {
        Timber.i("TIMBER onPause called")
        Log.i("MAIN", "onPause called")
        super.onPause()
    }
    override fun onStart() {
        Timber.i("TIMBER onStart called")
        Log.i("MAIN", "onStart called")
        super.onStart()
    }
    override fun onDestroy() {
        Timber.i("TIMBER onDestroy called")
        Log.i("MAIN", "onDestroy called")
        super.onDestroy()
    }
    override fun onRestart() {
        Timber.i("TIMBER onRestart called")
        Log.i("MAIN", "onRestart called")
        super.onRestart()
    }
    override fun onStop() {
        Timber.i("TIMBER onStop called")
        Log.i("MAIN", "onStop called")
        super.onStop()
    }
}
