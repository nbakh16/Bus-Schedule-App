package com.nbakh.busschedule

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nbakh.busschedule.daos.BusScheduleDao

@Database(entities = [BusSchedule::class], version = 1)
abstract class BusScheduleDB : RoomDatabase(){
    abstract fun getScheduleDao() : BusScheduleDao

    companion object {
        private  var db : BusScheduleDB? = null

        fun getDB(context : Context) : BusScheduleDB {
            if(db == null) {
                db = Room.databaseBuilder(context, BusScheduleDB::class.java, "bus_schedule_db")
                    //.allowMainThreadQueries() //removed db operation from mainthread for better performance
                    .build()
                return db!!
            }
            return db!!
        }
    }
}