package com.apocalypse.tripto.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.apocalypse.tripto.R
import kotlinx.android.synthetic.main.fragment_register_page.*

class RegisterPage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSubmitRegister.setOnClickListener{

           
            Profile.email=etEmailID.text.toString()
            Profile.gender=etGender.text.toString()
            Profile.number=etPhoneNumber.text.toString()
            Profile.name=etFullName.text.toString()
            Profile.password=etPassword.text.toString()
            Profile.username=etUserName.text.toString()

            findNavController().navigate(R.id.action_RegisterFragment_to_NextPage)
        }


    }


}