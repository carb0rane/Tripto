package com.apocalypse.tripto.ui.group

import android.content.Intent
import android.os.Bundle
import com.apocalypse.tripto.Main2Activity
import com.apocalypse.tripto.databinding.ActivityChooseGroupOrSoloBinding
import com.apocalypse.tripto.ui.solo.SoloHomePage
import kotlinx.android.synthetic.main.activity_choose_group_or_solo.*

class ChooseGroupOrSolo : Main2Activity() {
 lateinit var binding:ActivityChooseGroupOrSoloBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityChooseGroupOrSoloBinding.inflate(layoutInflater)
        setContentView(binding.root)


        groups_have_fun.setOnClickListener{
//            val tp = supportFragmentManager
//            val to= Group()
//            tp.beginTransaction().add(R.id.choose_group_solo,to).commit()

            val i = Intent(this,Group::class.java)
            startActivity(i)
        }


        solo_is_fun.setOnClickListener{
            val i = Intent(this,SoloHomePage::class.java)
            startActivity(i)
        }
    }
}