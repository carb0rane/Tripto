package com.apocalypse.tripto.ui.community

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.apocalypse.tripto.R
import com.apocalypse.tripto.api.Api
import com.apocalypse.tripto.api.info
import com.apocalypse.tripto.ui.registration.HostResponse
import com.apocalypse.tripto.ui.registration.HostResponseFromServer
import com.apocalypse.tripto.ui.registration.fineAddress
import kotlinx.android.synthetic.main.activity_be_a_host.*
import kotlinx.android.synthetic.main.activity_suggest_a_place.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BeAHost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_be_a_host)
        btnBeAHost.setOnClickListener {
            val address = fineAddress(
                etCityAddress.text.toString(),
                etFlatAddress.text.toString(),
                etStreetAddress.text.toString()
            )
            val sendData = HostResponse(
                address,
                etHostDescription.text.toString(),
                "623df9aae9b30a65fb511fdb",
                etContactNumberBeAHost.text.toString()
            )
            val Be_A_Host_Builder_object =
                Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(info.mainURl)
                    .build()
                    .create(Api::class.java)

            Be_A_Host_Builder_object.be_a_host(sendData)
                .enqueue(object : Callback<HostResponseFromServer> {
                    override fun onResponse(
                        call: Call<HostResponseFromServer>,
                        response: Response<HostResponseFromServer>
                    ) {

                        //val testit = response.body()
                        //Toast.makeText(this@BeAHost, "" + testit?.status, Toast.LENGTH_SHORT).show()
                        Log.d("Hello From Be A host","response is: "+response)
                    }

                    override fun onFailure(call: Call<HostResponseFromServer>, t: Throwable) {
                        //Toast.makeText(this@BeAHost, "Something went wrong !", Toast.LENGTH_SHORT) .show()
                        Log.d("Failure From Be A host","Error is: "+t)
                    }
                }


                )
        }
    }
}