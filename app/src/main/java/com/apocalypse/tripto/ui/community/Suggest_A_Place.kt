package com.apocalypse.tripto.ui.community

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.apocalypse.tripto.R
import com.apocalypse.tripto.api.Api
import com.apocalypse.tripto.api.info
// this import is for getting the state and city , this class is used at other places too :)
import com.apocalypse.tripto.ui.group.getInfoFromAlertDialog_CreateGroup
import kotlinx.android.synthetic.main.activity_suggest_a_place.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Suggest_A_Place : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggest_a_place)
        val viewGroup =
            (findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup
        getInfoFromAlertDialog_CreateGroup.get_state(viewGroup, this)
        btnSubmitSuggestion.setOnClickListener {
            val sendData = SuggestAPlace(
                etSuggestedPlace.text.toString(),
                etSuggestedPlaceDescription.text.toString(),
                getInfoFromAlertDialog_CreateGroup.District,
                "riya_here"
            )
            val Suggested_Builder_object =
                Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(info.mainURl)
                    .build()
                    .create(Api::class.java)

            Suggested_Builder_object.post_suggestion_of_a_place(sendData)
                .enqueue(object : Callback<SuggestAPlaceResponse> {
                    override fun onResponse(
                        call: Call<SuggestAPlaceResponse>,
                        response: Response<SuggestAPlaceResponse>
                    ) {

                        val testit=response.body()
                        Toast.makeText(this@Suggest_A_Place,""+testit?.status,Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<SuggestAPlaceResponse>, t: Throwable) {
                        Toast.makeText(this@Suggest_A_Place,"Something went wrong !",Toast.LENGTH_SHORT).show()
                    }
                }


                )

        }
    }
}