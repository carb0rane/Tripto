package com.apocalypse.tripto.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.apocalypse.tripto.R
import com.apocalypse.tripto.databinding.FragmentSecondBinding
/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener {

                findNavController().navigate(R.id.action_SecondFragment_to_fragment_register)

//           val i =Intent(requireContext(),Main2Activity::class.java)
//            startActivity(i)
        }
        binding.btnSignIn.setOnClickListener{
            findNavController().navigate(R.id.action_SecondFragment_to_fragment_signin)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}