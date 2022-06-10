package com.nbakh.busschedule.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nbakh.busschedule.BusSchedule

@Dao
interface BusScheduleDao {
    @Insert
    suspend fun addSchedule(busSchedule: BusSchedule)

    @Update
    suspend fun updateSchedule(busSchedule: BusSchedule)

    @Delete
    suspend fun deleteSchedule(busSchedule: BusSchedule)

    @Query("select * from tbl_schedule")
    fun getAllSchedule() : LiveData<List<BusSchedule>>

    @Query("select * from tbl_schedule where id = :id")
    fun getAllScheduleByID(id: Long) : LiveData<BusSchedule>
}