package com.apocalypse.tripto

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apocalypse.tripto.ui.registration.Profile
import com.apocalypse.tripto.ui.registration.RegisterHome

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferenc = this.getSharedPreferences("UserStatus", Context.MODE_PRIVATE)

        if(sharedPreferenc.getString("isLoggedIn","")=="True"){

            Profile._id=sharedPreferenc.getString("_id","62406756d49092d2bfea0f2d").toString()
            Profile.profile_image=sharedPreferenc.getString("profile_image","https://i.imgur.com/GBDCzmc.png").toString()
            Profile.descrption=sharedPreferenc.getString("descrption","Live | Learn").toString()
            Profile.user_city=sharedPreferenc.getString("user_city","Goa").toString()
            Profile.name = sharedPreferenc.getString("name","Anurag Kashyap").toString()
            Profile.email = sharedPreferenc.getString("email","war@monger.com").toString()
            Profile.username = sharedPreferenc.getString("username","hades").toString()
            Profile.number = sharedPreferenc.getString("number","7894665145").toString()
            Profile.gender = sharedPreferenc.getString("gender","Male").toString()

            val i= Intent(this,Main2Activity::class.java)
            startActivity(i)
        }
        else{
            val i= Intent(this,RegisterHome::class.java)
            startActivity(i)
        }
    }
}