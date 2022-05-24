package com.nbakh.busschedule.daos

import androidx.room.*
import com.nbakh.busschedule.BusSchedule

@Dao
interface BusScheduleDao {
    @Insert
    fun addSchedule(busSchedule: BusSchedule)

    @Update
    fun updateSchedule(busSchedule: BusSchedule)

    @Delete
    fun deleteSchedule(busSchedule: BusSchedule)

    @Query("select * from tbl_schedule")
    fun getAllSchedule() : List<BusSchedule>
}