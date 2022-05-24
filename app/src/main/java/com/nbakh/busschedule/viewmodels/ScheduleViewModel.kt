package com.nbakh.busschedule.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nbakh.busschedule.BusSchedule
import com.nbakh.busschedule.BusScheduleDB
import com.nbakh.busschedule.repos.ScheduleRepository
import com.nbakh.busschedule.scheduleList

class ScheduleViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var repository: ScheduleRepository

    init {
        val dao = BusScheduleDB.getDB(application).getScheduleDao()
        repository = ScheduleRepository(dao)
    }

    fun addSchedule(busSchedule: BusSchedule) {
        repository.addSchedule(busSchedule)
    }

    fun updateSchedule(busSchedule: BusSchedule) {
        repository.updateSchedule(busSchedule)
    }

    fun deleteSchedule(busSchedule: BusSchedule) {
        repository.deleteSchedule(busSchedule)
    }

    fun getAllSchedule() : List<BusSchedule> = repository.getAllSchedule()

//    fun addSchedule(schedule: BusSchedule) {
//        scheduleList.add(schedule)
//    }
//    fun getSchedule() = scheduleList
}