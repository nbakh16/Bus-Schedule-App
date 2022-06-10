package com.nbakh.busschedule

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

val cityList = listOf("Dhaka", "Chittagong", "Cox's Bazar" , "Khulna", "Rajshahi", "Sylhet")

val busTypeEconomy = "Economy"
val busTypeBusiness = "Business"

@Entity(tableName = "tbl_schedule")
data class BusSchedule(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name = "bus_name")
    val busName: String,
    val from: String,
    val to: String,
    @ColumnInfo(name = "departure_date")
    val departureDate: String,
    @ColumnInfo(name = "departure_time")
    val departureTime: String,
    @ColumnInfo(name = "bus_type")
    val busType: String,
) {
}

val scheduleList = mutableListOf<BusSchedule>()