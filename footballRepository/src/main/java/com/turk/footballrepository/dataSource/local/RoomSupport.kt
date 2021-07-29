package com.turk.footballrepository.dataSource.local

import android.app.Application
import android.content.Context

import androidx.room.Room


class RoomSupport(context: Context) {

    private val DB_NAME = "footballMatches.db"
    private val database: AppLocalDatabase = Room.databaseBuilder(context.applicationContext,
            AppLocalDatabase::class.java, DB_NAME)
            .allowMainThreadQueries().build()

    fun getFootBallMatchDao()= database.footBallMatchDao


//    companion object {
//        private var instance: RoomSupport? = null
//
//        fun createInstance(context: Context) {
//            if (instance != null) {
//                println("Room Instance already exist overriding")
//            }
//            instance = RoomSupport(context)
//        }
//
//        fun getInstance(): RoomSupport {
//            if (instance == null) {
//                throw IllegalArgumentException("Roomdatabase not initialied ..")
//            }
//            return instance!!
//        }
//    }

}