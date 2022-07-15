package com.apocalypse.tripto.ui.travel

import com.apocalypse.tripto.api.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Hotels {
    companion object fruit {
        val URLl="https://api.makcorps.com/"
        var makcorp_token="JWT eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDgxMjM3OTcsImlhdCI6MTY0ODEyMTk5NywibmJmIjoxNjQ4MTIxOTk3LCJpZGVudGl0eSI6MTE4Mn0.GC281i8Xz0zi4I6dBT8OPiAl1y18LdLivr10w545Jt4"

        fun fetchHotel() {
        val Hotel_Detail_object =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URLl)
                .build()
                .create(Api::class.java)
        val city = "Ranchi"
        val pompom = HotelInfoClass(city, makcorp_token)

//        Hotel_Detail_object.getHotels(city, makcorp_token).enqueue(object : Callback<HotelDetails> {
//            override fun onResponse(
//                call: Call<HotelDetails>,
//                response: Response<HotelDetails>
//            ) {
//
//
//
//                // Toast.makeText(this@Hotels, response.toString(), Toast.LENGTH_SHORT).show()
//                Log.d("Inside Hotel detail Success ", "Response is : " + response.body().toString())
//
//            }
//
//            override fun onFailure(call: Call<HotelDetails>, t: Throwable) {
//                // Toast.makeText(this@Hotels, t.message, Toast.LENGTH_SHORT).show()
//                Log.d("MainActivity", "onFailure :" + t.message)
//                // Toast.makeText(this@MainActivity, "Error in API", Toast.LENGTH_SHORT).show()
//            }
//        })
    }
}
}