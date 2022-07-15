package com.apocalypse.tripto.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.net.toUri
import com.apocalypse.tripto.R
import com.apocalypse.tripto.ui.registration.Profile
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

imgviewUserProfileImage.setImageURI(Profile.profile_image.toUri())
        tvUserID.text=Profile._id
        tvFullName.text=Profile.name
        tvProfileEmail.text=Profile.email
        tvProfileGender.text=Profile.gender
        tvProfileUserName.text=Profile.username


    }
}