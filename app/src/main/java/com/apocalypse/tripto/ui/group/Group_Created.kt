package com.apocalypse.tripto.ui.group

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.apocalypse.tripto.R
import com.apocalypse.tripto.api.Api
import com.apocalypse.tripto.api.info
import com.apocalypse.tripto.databinding.ActivityGroupCreatedBinding
import com.apocalypse.tripto.ui.community.ShowGuides
import com.apocalypse.tripto.ui.community.ShowSuggestedPlaces
import com.apocalypse.tripto.ui.registration.Profile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Group_Created : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityGroupCreatedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGroupCreatedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setSupportActionBar(binding.toolbar)
getIncomingIntent()
val tes=supportFragmentManager
        tes.beginTransaction().replace(R.id.frameLayoutComments,Comments()).commit()
        tes.beginTransaction().replace(R.id.frameLayoutSuggestedPlace,ShowSuggestedPlaces()).commit()
        tes.beginTransaction().replace(R.id.frameLayoutGuides,ShowGuides()).commit()


    }
    fun getIncomingIntent(){
        if(intent.hasExtra("date" )){
            val feed_Builder_object =
                Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(info.mainURl)
                    .build()
                    .create(Api::class.java)
            // val useridd = ArrayList<String>(1)
            // useridd.add("itsjustmeANURAG")

            //  val group_addd=GroupDataItem(null,null,null,null,useridd,intent.getStringExtra("group_id").toString())
            val day=intent.getStringExtra("date").toString()
            var month=intent.getStringExtra("month")!!.toInt()
            month++
            val year=intent.getStringExtra("year").toString()
            val date=year+"-"+month+"-"+day
            val location=intent.getStringExtra("location_city")
            val group_description=intent.getStringExtra("group_description")

            val group_add=NewGroup(Profile._id,group_description,location,date,null)
            feed_Builder_object.join_group(group_add).enqueue(object : Callback<ResponseAfterGroupCreated?> {
                override fun onResponse(
                    call: Call<ResponseAfterGroupCreated?>,
                    response: Response<ResponseAfterGroupCreated?>
                ) {
                    Log.d("Inside Success ","Response is : "+response)

                }

                override fun onFailure(call: Call<ResponseAfterGroupCreated?>, t: Throwable) {
                    Toast.makeText(this@Group_Created, t.message, Toast.LENGTH_SHORT).show()
                    Log.d("MainActivity", "onFailure :" + t.message)
                    // Toast.makeText(this@MainActivity, "Error in API", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }


}