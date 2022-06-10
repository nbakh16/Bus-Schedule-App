package com.nbakh.busschedule.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nbakh.busschedule.BusSchedule
import com.nbakh.busschedule.BusScheduleDB
import com.nbakh.busschedule.repos.ScheduleRepository
import com.nbakh.busschedule.scheduleList
import kotlinx.coroutines.launch

class ScheduleViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var repository: ScheduleRepository

    init {
        val dao = BusScheduleDB.getDB(application).getScheduleDao()
        repository = ScheduleRepository(dao)
    }

    fun addSchedule(busSchedule: BusSchedule) {
        viewModelScope.launch {
            repository.addSchedule(busSchedule)
        }
    }

    fun updateSchedule(busSchedule: BusSchedule) {
        viewModelScope.launch {
            repository.updateSchedule(busSchedule)
        }
    }

    fun deleteSchedule(busSchedule: BusSchedule) {
        viewModelScope.launch {
            repository.deleteSchedule(busSchedule)
        }
    }

    fun getAllSchedule() : LiveData<List<BusSchedule>> = repository.getAllSchedule()

    fun getAllScheduleByID(id: Long) = repository.getAllScheduleByID(id)

//    fun addSchedule(schedule: BusSchedule) {
//        scheduleList.add(schedule)
//    }
//    fun getSchedule() = scheduleList
}