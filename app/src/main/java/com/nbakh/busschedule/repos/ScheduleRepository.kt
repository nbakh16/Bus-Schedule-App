package com.nbakh.busschedule.repos

import com.nbakh.busschedule.BusSchedule
import com.nbakh.busschedule.daos.BusScheduleDao

class ScheduleRepository(val busScheduleDao: BusScheduleDao) {
    fun addSchedule(busSchedule: BusSchedule) {
        busScheduleDao.addSchedule(busSchedule)
    }

    fun updateSchedule(busSchedule: BusSchedule) {
        busScheduleDao.updateSchedule(busSchedule)
    }

    fun deleteSchedule(busSchedule: BusSchedule) {
        busScheduleDao.deleteSchedule(busSchedule)
    }

    fun getAllSchedule() : List<BusSchedule> = busScheduleDao.getAllSchedule()
}