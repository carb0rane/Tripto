package com.apocalypse.tripto.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apocalypse.tripto.R
import com.apocalypse.tripto.databinding.ActivityFillerBinding


private lateinit var binding:ActivityFillerBinding

class Filler : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFillerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (intent.getStringExtra("goto")=="create_post"){
            val tes=supportFragmentManager
            tes.beginTransaction().replace(R.id.filler_fragement, createPost()).commit()
        }
    }
}