package com.apocalypse.tripto.ui.group

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apocalypse.tripto.databinding.FragmentCommentsBinding
import kotlinx.android.synthetic.main.fragment_comments.*
import kotlinx.android.synthetic.main.fragment_home.*

class Comments : Fragment() {
    lateinit var feedadapter: CommunitySuggestedPlacesAdapter
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var _binding:FragmentCommentsBinding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommentsBinding.inflate(layoutInflater, container, false)
        val root: View = binding.root
        // recyclerView.layoutManager(LinearlayoutManager(this))


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}