package com.apocalypse.tripto.ui.group

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.apocalypse.tripto.api.Api
import com.apocalypse.tripto.api.info
import com.apocalypse.tripto.databinding.ActivityGroupDetailsBinding
import com.apocalypse.tripto.ui.travel.Hotels
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class GroupDetails : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityGroupDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGroupDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
getIncomingIntent()

    }
    fun getIncomingIntent(){
        if(intent.hasExtra("group_id")){
            val feed_Builder_object =
                Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(info.mainURl)
                    .build()
                    .create(Api::class.java)
           // val useridd = ArrayList<String>(1)
           // useridd.add("itsjustmeANURAG")

   //  val group_addd=GroupDataItem(null,null,null,null,useridd,intent.getStringExtra("group_id").toString())
          val group_add=GroupAdd("this_time_riya",intent.getStringExtra("group_id").toString())
            feed_Builder_object.join_group(group_add).enqueue(object : Callback<String?> {
                override fun onResponse(
                    call: Call<String?>,
                    response: Response<String?>
                ) {

                    Toast.makeText(this@GroupDetails, response.toString(), Toast.LENGTH_SHORT).show()
                    Hotels.fetchHotel()
                  Log.d("Inside Success ","Response is : "+response)

                }

                override fun onFailure(call: Call<String?>, t: Throwable) {
                    Toast.makeText(this@GroupDetails, t.message, Toast.LENGTH_SHORT).show()
                    Log.d("MainActivity", "onFailure :" + t.message)
                    // Toast.makeText(this@MainActivity, "Error in API", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }


}