package com.apocalypse.tripto.ui.home

/**
 * Feed Rsponse variables
 */
// data class PostItem(
//    val body: String,
//    val id: Int,
//    val title: String,
//    val userId: Int
//    val userId: String,
//    val desc: String
//
//
//)
// class t : ArrayList<tItem>()

//data class PostItem(
//    val __v: Int,
//    val _id: String,
//    val createdAt: String,
//    val desc: String,
//   val img: String,
//   val likes: ArrayList<String>,
//    val updatedAt: String,
//    val userId: String
//)

data class PostItem(
    val Comment: List<Any>?,
    val desc: String,
    val img: String,
    val likes: List<Any>,
    val userId: skdjf?
)
data class skdjf(
    var _id: String,
    var city: String,
    var desc: String,
    var email: String,
    var gender: String,
    var mobile: String,
    var name: String,
    var profile_pic: String,
    var username: String
)
data class UserId(
    val _id: String,
    val city: String,
    val createdAt: String,
    val email: String,
    val gender: String,
    val mobile: String,
    val name: String,
    val profile_pic: String,
    val updatedAt: String,
    val username: String
)
/**
 * Imgur response data variables
 */
data class UploadResponse(
    val data: Upload,
    val status: Int,
    val success: Boolean,
    val upload:Upload
)

data class Data(
    val error: String,
    val method: String,
    val request: String
)


data class Upload(

    val accountId: Int?,
    val accountUrl: String?,
    val adType: Int?,
    val adUrl: String?,
    val animated: Boolean,
    val bandwidth: Int,
    val datetime: Long,
    val deletehash: String,
    val description: String?,
    val favorite: Boolean,
    val hasSound: Boolean,
    val height: Int,
    val hls: String,
    val id: String,
    val inGallery: Boolean,
    val inMostViral: Boolean,
    val isAd: Boolean,
     val link: String,
    val mp4: String,
    val name: String,
    val size: Int,
    val tags: List<String>,
    val title: String?,
    val type: String,
    val views: Int,
   val width: Int
)
