package com.apocalypse.tripto.ui.travel

data class HotelDetails(
    val Comparison: List<hotelname>
)
data class hotelname(
    val hotelname:String,
    val site_and_price:List<hotell>
)
data class hotell(
    val price:String,
    val tax:String,
    val vendor:String
)
data class HotelInfoClass(
    val city:String,
    val token:String
    )