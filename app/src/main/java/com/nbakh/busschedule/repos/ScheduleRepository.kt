package com.nbakh.busschedule.repos

import androidx.lifecycle.LiveData
import com.nbakh.busschedule.BusSchedule
import com.nbakh.busschedule.daos.BusScheduleDao

class ScheduleRepository(val busScheduleDao: BusScheduleDao) {
    suspend fun addSchedule(busSchedule: BusSchedule) {
        busScheduleDao.addSchedule(busSchedule)
    }

    suspend fun updateSchedule(busSchedule: BusSchedule) {
        busScheduleDao.updateSchedule(busSchedule)
    }

    suspend fun deleteSchedule(busSchedule: BusSchedule) {
        busScheduleDao.deleteSchedule(busSchedule)
    }

    fun getAllSchedule() : LiveData<List<BusSchedule>> = busScheduleDao.getAllSchedule()

    fun getAllScheduleByID(id: Long) = busScheduleDao.getAllScheduleByID(id)
}