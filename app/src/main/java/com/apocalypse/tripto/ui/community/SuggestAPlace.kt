package com.apocalypse.tripto.ui.community

data class SuggestAPlace(
    val address: String,
    val desc: String,
    val location: String,
    val userId: String
)

data class SuggestAPlaceResponse(
    val status:String
)
data class test(
    val address: Address,
    val desc: String,
    val hostId: String,
    val mobile: Int
)

data class Address(
    val city: String,
    val houseNumber: String,
    val street: String
)
