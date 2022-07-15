package com.apocalypse.tripto.ui.community

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apocalypse.tripto.R
import com.apocalypse.tripto.api.Api
import com.apocalypse.tripto.api.info
import kotlinx.android.synthetic.main.fragment_show_guides.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 private lateinit var guideadapter: ShowGuidesAdapter

private var layoutManager: RecyclerView.LayoutManager? = null
class ShowGuides : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_guides, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        show_guide_recycler.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
            getGuides()
        }
    }
    fun getGuides(){

            val feed_Builder_object =
                Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(info.mainURl)
                    .build()
                    .create(Api::class.java)
            val feed_Data = feed_Builder_object.getGuides("goa")

            feed_Data.enqueue(object : Callback<List<GuideReturnData>?> {
                override fun onResponse(
                    call: Call<List<GuideReturnData>?>,
                    response: Response<List<GuideReturnData>?>
                ) {
                    if (response.isSuccessful) {
                        Log.d("Success Getting Guide Details ! ",":)")

                        val ff_data = response.body()!!

                        guideadapter = ShowGuidesAdapter(requireContext(), ff_data)

                        guideadapter.notifyDataSetChanged()
                        show_guide_recycler.adapter = guideadapter
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Something went wrong fetching home feed !",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<List<GuideReturnData>?>, t: Throwable) {
                    // Toast.makeText(requireActivity().applicationContext, "hello", Toast.LENGTH_SHORT) .show()
                    Log.d("MainActivity", "onFailure :" + t.message)
                    // Toast.makeText(this@MainActivity, "Error in API", Toast.LENGTH_SHORT).show()
                }
            })
        }

        




}