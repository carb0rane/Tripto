package com.apocalypse.tripto.ui.registration


data class HostResponse(
    val address: fineAddress,
    val desc: String,
    val hostId: String,
    val mobile: String
)

data class fineAddress(
    val city: String,
    val houseNumber: String,
    val street: String
)
data class HostResponseFromServer(
    val status:String
)