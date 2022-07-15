package com.apocalypse.tripto.ui.community

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import com.apocalypse.tripto.R
import com.apocalypse.tripto.api.Api
import com.apocalypse.tripto.api.info
import com.apocalypse.tripto.ui.group.ResponseAfterGroupCreated
import com.apocalypse.tripto.ui.registration.Profile
import kotlinx.android.synthetic.main.alert_dialog_be_a_guide.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BeAGuide {
    companion object{
        fun be_a_guide(context: Context){
           Log.d("Inside: ","Be a Guide Function")
            val builder = AlertDialog.Builder(context)

            val dialogview =
                LayoutInflater.from(context).inflate(R.layout.alert_dialog_be_a_guide, null)
            builder.setPositiveButton("Done") { dialogInterface, which ->

                // Toast.makeText(requireActivity(), "No Image Picked! ", Toast.LENGTH_SHORT).show()
            }

            // val stateAdapter:ArrayAdapter<String>


            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(true)
            alertDialog.setView(dialogview)
            alertDialog.show()
dialogview.btnGetGuideDetails.setOnClickListener{
    val sendGuide_Builder_object =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(info.mainURl)
            .build()
            .create(Api::class.java)
    val send_data=CreateGuideSendData(dialogview.guideCharge.text.toString(),
    dialogview.guidePhoneNumber.text.toString(),
    dialogview.guideDescription.text.toString(),
    Profile.name,
    "1",
    dialogview.guideLocation.text.toString(),
    Profile._id)
    sendGuide_Builder_object.be_a_guide(send_data).enqueue(object : Callback<ResponseAfterGroupCreated?> {
        override fun onResponse(
            call: Call<ResponseAfterGroupCreated?>,
            response: Response<ResponseAfterGroupCreated?>
        ) {

            Log.d("Create a new guide" ,"Response is: "+response.body()?.status.toString())


        }

        override fun onFailure(call: Call<ResponseAfterGroupCreated?>, t: Throwable) {
           // Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            Log.d("Create Guide", "onFailure :" + t.message)
            // Toast.makeText(this@MainActivity, "Error in API", Toast.LENGTH_SHORT).show()
        }
    })
}

        }
    }
}