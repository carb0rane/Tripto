package com.apocalypse.tripto.ui.registration

data class LoginResponse(
    val __v: Int, //
    val _id: String, //
    val city: String, //
    val createdAt: String, //
    val desc: String, //
    val email: String, //
    val profile_pic: String, //
    val gender: String, //
    val mobile: String, //
    var name: String, //
    val updatedAt: String, //
    val username: String //
)
data class LoginSend(
    val email: String,
    val password: String
)



