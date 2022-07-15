package com.apocalypse.tripto.ui.registration

data class RegistrationResponse(
    val email: String,
    val gender: String,
    val mobile: String,
    val name: String,
    val password: String,
    val username: String,
    val profile_pic:String, //Not Required
    val desc:String, //Not Required
    val city:String  //Not Required
)
