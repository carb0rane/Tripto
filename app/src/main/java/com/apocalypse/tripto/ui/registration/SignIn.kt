package com.apocalypse.tripto.ui.registration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.apocalypse.tripto.MainActivity
import com.apocalypse.tripto.R
import com.apocalypse.tripto.api.Api
import com.apocalypse.tripto.api.info
import kotlinx.android.synthetic.main.fragment_group.*
import kotlinx.android.synthetic.main.fragment_sign_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SignIn : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSubmitLogin.setOnClickListener {
            val username = etLoginUserName.text.toString()
            val password = etLoginPassword.text.toString()
            Log.d("testse","jdsf"+username+password)
            val login_Builder_object =
                Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(info.mainURl)
                    .build()
                    .create(Api::class.java)
            val final = LoginSend(username, password)
            val Login_Data = login_Builder_object.signin(final)
            Login_Data.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {

if(response.isSuccessful) {
    //val ff_data = response.body()!!
    Log.d("Called from login", " Name: " + response)
    val repbody = response.body()!!
   Profile._id=repbody._id
    Profile.profile_image=repbody.profile_pic
    Profile.descrption=repbody.desc
    Profile.user_city=repbody.city
    Profile.name = repbody.name
    Profile.email = repbody.email
    Profile.username = repbody.username
    Profile.number = repbody.mobile
    Profile.gender = repbody.gender
    val sharedPreferences =
        requireContext().getSharedPreferences("UserStatus", Context.MODE_PRIVATE)
    sharedPreferences.edit().clear()
    sharedPreferences.edit().putString("isLoggedIn", "True").commit()
    sharedPreferences.edit().putString("_id", repbody._id).commit()
    sharedPreferences.edit().putString("profile_image", repbody.profile_pic).commit()
    sharedPreferences.edit().putString("descrption", repbody.desc).commit()
    sharedPreferences.edit().putString("user_city", repbody.city).commit()
    sharedPreferences.edit().putString("name", repbody.name).commit()
    sharedPreferences.edit().putString("email", repbody.email).commit()
    sharedPreferences.edit().putString("username", repbody.username).commit()
    sharedPreferences.edit().putString("number", repbody.mobile).commit()
    sharedPreferences.edit().putString("gender", repbody.gender).commit()

    sharedPreferences.edit().apply()
    Log.d("Shared Pref: ", "This: " + sharedPreferences.getString("userID", ""))
    val i = Intent(requireContext(),MainActivity::class.java)
    startActivity(i)
}
//                    else {
//    Log.d("Response from login", ": " + response.code() + " " + response.isSuccessful)
//    Toast.makeText(requireContext(), "Email/Password Incorrect", Toast.LENGTH_SHORT).show()
//}
                    //  Toast.makeText(this@MainActivity, "Hello Sir", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(
                        requireActivity().applicationContext,
                        "hello",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("Hello from Sign In Page", "onFailure :" + t.message)
                    // Toast.makeText(this@MainActivity, "Error in API", Toast.LENGTH_SHORT).show()
                }

            })


        }
    }
}