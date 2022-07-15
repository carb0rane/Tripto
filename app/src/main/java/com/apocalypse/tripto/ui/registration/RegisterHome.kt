package com.apocalypse.tripto.ui.registration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.apocalypse.tripto.R
import com.apocalypse.tripto.databinding.ActivityRegisterHomeBinding

class RegisterHome : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityRegisterHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_register_home)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

//       // requestWindowFeature(Window.FEATURE_NO_TITLE);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
//        getSupportActionBar()?.hide(); //hide the title bar
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_register_home)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}