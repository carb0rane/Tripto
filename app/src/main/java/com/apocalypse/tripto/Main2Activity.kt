package com.apocalypse.tripto

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.apocalypse.tripto.databinding.ActivityMain2Binding
import com.apocalypse.tripto.ui.home.Filler
import kotlinx.android.synthetic.main.fragment_home.*

open class Main2Activity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain2.toolbar)

       // binding.appBarMain2.
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main2)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        fab.setOnClickListener{

val i =Intent(this,Filler::class.java)
            i.putExtra("goto","create_post")
            startActivity(i)
        }
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nava_community, R.id.nav_create_Group,R.id.nav_create_post,R.id.nav_user_profile
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings -> Log.d("MainActivity","Clicked setting")
            R.id.action_Logout -> {
                Log.d("MainActivity", "Clicked Logout")
                val sharedPreferences = this.getSharedPreferences("UserStatus", Context.MODE_PRIVATE)
                sharedPreferences.edit().clear()
                sharedPreferences.edit().putString("isLoggedIn","False").commit()
                sharedPreferences.edit().apply()
                val i= Intent(this,MainActivity::class.java)
                startActivity(i)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main2)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}