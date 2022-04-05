package com.nbakh.busschedule

val cityList = listOf("Dhaka", "Chittagong", "Cox's Bazar" , "Khulna", "Rajshahi", "Sylhet")

val busTypeEconomy = "Economy"
val busTypeBusiness = "Business"

data class BusSchedule(
    val id: Long,
    val busName: String,
    val from: String,
    val to: String,
    val departureDate: String,
    val departureTime: String,
    val busType: String,
)