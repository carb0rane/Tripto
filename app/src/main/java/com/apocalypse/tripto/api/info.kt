package com.apocalypse.tripto.api

/**
 * It contains url or other variables used throughout the app
 */
class info {
    companion object {
        const val mainURl = "https://blank0x00.herokuapp.com/"
        const val CLIENT_ID = "04ae4f56bea9845" //Imgur client ID
        const val imgurURL="https://api.imgur.com/"
    }

}

data class SuggestedPlacesResponseItem(
    val __v: Int,
    val _id: String,
    val address: String,
    val desc: String,
    val location: String,
    val rating: List<Any>,
    val userId: UserId
)

data class UserId(
    val __v: Int,
    val _id: String,
    val city: String,
    val createdAt: String,
    val desc: String,
    val email: String,
    val gender: String,
    val mobile: String,
    val name: String,
    val profile_pic: String,
    val updatedAt: String,
    val username: String
)