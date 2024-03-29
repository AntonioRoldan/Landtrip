package io.keepcoding.mvvmarchitecture.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import io.keepcoding.mvvmarchitecture.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setUpNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {

        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUpNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostContainer) as NavHostFragment // We suppress cast never succeeds so the compiler does not complain
        navController = navHostFragment.navController
        bottomNavigation.setupWithNavController(navController)
        //bottomNavigation.setOnItemSelectedListener { item -> leave this for learning
        //            val fragment: Fragment
        //            when (item.itemId) {
        //                R.id.nav_graph_home -> {
        //                    fragment = HomeFragment.newInstance()
        //                    loadFragment(fragment)
        //                    true
        //                }
        //                R.id.nav_graph_my_trips -> {
        //                    fragment = MyTripsFragment()
        //                    loadFragment(fragment)
        //                    true
        //                }
        //                R.id.nav_graph_settings -> {
        //                    fragment = SettingsFragment()
        //                    loadFragment(fragment)
        //                    true
        //                }
        //                else -> false
        //            }
        //        }
        appBarConfiguration = AppBarConfiguration.Builder(R.id.home_dest, R.id.my_trips_dest, R.id.add_new_trip_dest)
            .build()
        setupActionBarWithNavController(navController)
    }

    //private fun loadFragment(fragment: Fragment){
    //        supportFragmentManager.beginTransaction()
    //            .replace(R.id.navHostContainer, fragment)
    //            .commit()
    //    }


    private fun receiveDataFromFragment() { // We are going to receive the

    }
}