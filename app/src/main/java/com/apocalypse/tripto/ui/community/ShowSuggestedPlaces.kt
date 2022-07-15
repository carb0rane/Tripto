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
import com.apocalypse.tripto.api.SuggestedPlacesResponseItem
import com.apocalypse.tripto.api.info
import com.apocalypse.tripto.databinding.FragmentCommentsBinding
import com.apocalypse.tripto.ui.group.CommunitySuggestedPlacesAdapter
import kotlinx.android.synthetic.main.fragment_show_suggested_places.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

lateinit var feedadapter: CommunitySuggestedPlacesAdapter
private var layoutManager: RecyclerView.LayoutManager? = null
private var _binding: FragmentCommentsBinding?=null
private val binding get() = _binding!!
class ShowSuggestedPlaces : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_suggested_places, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        suggested_place_recycler.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
            getcommunity()
        }
    }
    fun getcommunity() {

        val feed_Builder_object =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(info.mainURl)
                .build()
                .create(Api::class.java)
        val feed_Data = feed_Builder_object.getSuggestedPlaces("goa")

        feed_Data.enqueue(object : Callback<List<SuggestedPlacesResponseItem>?> {
            override fun onResponse(
                call: Call<List<SuggestedPlacesResponseItem>?>,
                response: Response<List<SuggestedPlacesResponseItem>?>
            ) {
                if (response.isSuccessful) {

                    val ff_data = response.body()!!

                    feedadapter = CommunitySuggestedPlacesAdapter(requireContext(), ff_data)

                    feedadapter.notifyDataSetChanged()
                    suggested_place_recycler.adapter = feedadapter
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Something went wrong fetching home feed !",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<SuggestedPlacesResponseItem>?>, t: Throwable) {
                Toast.makeText(requireActivity().applicationContext, "hello", Toast.LENGTH_SHORT)
                    .show()
                Log.d("MainActivity", "onFailure :" + t.message)
                // Toast.makeText(this@MainActivity, "Error in API", Toast.LENGTH_SHORT).show()
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}