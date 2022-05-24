package com.nbakh.busschedule.viewmodels

import androidx.lifecycle.ViewModel
import com.nbakh.busschedule.BusSchedule
import com.nbakh.busschedule.scheduleList

class ScheduleViewModel() : ViewModel() {
    fun addSchedule(schedule: BusSchedule) {
        scheduleList.add(schedule)
    }

    fun getSchedule() = scheduleList
}