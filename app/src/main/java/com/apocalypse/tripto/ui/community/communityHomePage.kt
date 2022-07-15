package com.apocalypse.tripto.ui.community

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apocalypse.tripto.R
import kotlinx.android.synthetic.main.activity_community_home_page.*

class communityHomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_home_page)
        beAHost.setOnClickListener{
            val i=Intent(this,BeAHost::class.java)
            startActivity(i)
        }
        beAGuide.setOnClickListener{

            BeAGuide.be_a_guide(this)
        }

        suggestAPlace.setOnClickListener{
val i=Intent(this,Suggest_A_Place::class.java)
            startActivity(i)


        }
    }

}