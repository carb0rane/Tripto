package com.apocalypse.tripto.ui.group

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.apocalypse.tripto.R
import com.apocalypse.tripto.api.Api
import com.apocalypse.tripto.api.info
import kotlinx.android.synthetic.main.alert_dialog_choose_image.view.*
import kotlinx.android.synthetic.main.alert_dialog_create_group.*
import kotlinx.android.synthetic.main.alert_dialog_create_group.view.*
import kotlinx.android.synthetic.main.fragment_group.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.group_feed.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Stop Nigga
// Stop Buddy
class Group : AppCompatActivity(),View.OnClickListener {
    lateinit var group_feed: Group_Feed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_group)
        group_recycler.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(this@Group)

            // Comment to block getfeeds
            getGroupDetails()
            // set the custom adapter to the RecyclerView
            //  adapter = FeedAdapter() }


        }
        create_group_fab.setOnClickListener { createGroup()
        }

    }

    override fun onClick(p0: View?) {
        val i=Intent(this,GroupDetails::class.java)
        startActivity(i)
    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?)  {
//        super.onViewCreated(view, savedInstanceState)
//
//
//    }
    fun createGroup(){
        val builder = AlertDialog.Builder(this)

        val dialogview =
            LayoutInflater.from(this).inflate(R.layout.alert_dialog_create_group, null)
        builder.setPositiveButton("Cancel") { dialogInterface, which ->
            // Toast.makeText(requireActivity(), "No Image Picked! ", Toast.LENGTH_SHORT).show()
        }
        // val stateAdapter:ArrayAdapter<String>


        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(true)
        alertDialog.setView(dialogview)
        alertDialog.show()
       //  val stateAdapter=ArrayAdapter.createFromResource(requireActivity(),R.array.array_indian_states,R.layout.spinner_layout)
        getInfoFromAlertDialog_CreateGroup.get_state(dialogview,this)
        dialogview.btn_create_new_group.setOnClickListener{



            Log.d("Day is : ",": "+dialogview.create_group_date_picker.dayOfMonth.toString())

            val i =Intent(this,Group_Created::class.java)
            i.putExtra("date",dialogview.create_group_date_picker.dayOfMonth.toString())
            i.putExtra("month",dialogview.create_group_date_picker.month.toString())
            i.putExtra("year",dialogview.create_group_date_picker.year.toString())
            i.putExtra("location_city",getInfoFromAlertDialog_CreateGroup.District)
            i.putExtra("group_description",dialogview.etJourneyDescription.text.toString())

            startActivity(i)
        }
    }
    fun getGroupDetails() {

        val feed_Builder_object= Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(info.mainURl)
            .build()
            .create(Api::class.java)
        val feed_Data=feed_Builder_object.getGroup()

        feed_Data.enqueue(object : Callback<List<GroupDataItem>?> {
            override fun onResponse(
                call: Call<List<GroupDataItem>?>,
                response: Response<List<GroupDataItem>?>
            ) {

                val ff_data=response.body()!!

                group_feed=Group_Feed(this@Group,ff_data)

                group_feed.notifyDataSetChanged()
                group_recycler.adapter=group_feed


                //  Toast.makeText(this@MainActivity, "Hello Sir", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<List<GroupDataItem>?>, t: Throwable) {
                Toast.makeText(this@Group,"hello", Toast.LENGTH_SHORT).show()
                Log.d("MainActivity","onFailure :"+t.message)
                // Toast.makeText(this@MainActivity, "Error in API", Toast.LENGTH_SHORT).show()
            }
        })
    }


}