package com.apocalypse.tripto.ui.community

import com.apocalypse.tripto.ui.registration.LoginResponse

data class GuideReturnData(
    val __v: Int,
    val _id: String,
    val charge: String,
    val contact: String,
    val desc: String,
    val level: String,
    val location: String,
    val rating: List<Any>,
    val userId: LoginResponse
)
data class CreateGuideSendData(
    val charge: String,
    val contact: String,
    val desc: String,
    val guideName: String,
    val level: String,
    val location: String,
    val userId: String
)