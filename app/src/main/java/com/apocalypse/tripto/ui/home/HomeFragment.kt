package com.apocalypse.tripto.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apocalypse.tripto.api.Api
import com.apocalypse.tripto.api.info
import com.apocalypse.tripto.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {
    lateinit var feeddapter: FeedAdapter
    private var _binding: FragmentHomeBinding? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<FeedAdapter.ViewHolder>? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val root: View = binding.root
        // recyclerView.layoutManager(LinearlayoutManager(this))


        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        feed_recycler.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            fab.setOnClickListener { view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            // Comment to block getfeeds
            getfeeds()
            // set the custom adapter to the RecyclerView
            //  adapter = FeedAdapter() }
        }
    }

    fun getfeeds() {

        val feed_Builder_object =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(info.mainURl)
                .build()
                .create(Api::class.java)
        val feed_Data = feed_Builder_object.getData()

        feed_Data.enqueue(object : Callback<List<PostItem>?> {
            override fun onResponse(
                call: Call<List<PostItem>?>,
                response: Response<List<PostItem>?>
            ) {
                if (response.isSuccessful) {

                    val ff_data = response.body()!!

                    feeddapter = FeedAdapter(activity!!.applicationContext, ff_data)

                    feeddapter.notifyDataSetChanged()
                    feed_recycler.adapter = feeddapter
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Something went wrong fetching home feed !",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<PostItem>?>, t: Throwable) {
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