package com.turk.footballrepository.dataSource.local

import android.content.Context

import androidx.room.Room


class RoomSupport private constructor(cntxt: Context) {

    private val DB_NAME = "footballMatches.db"
    val database: AppLocalDatabase
    val context: Context

    init {
        this.context = cntxt
        this.database = Room.databaseBuilder(context.applicationContext,
                AppLocalDatabase::class.java, DB_NAME)
                .allowMainThreadQueries().build()
    }



    companion object {
        private var instance: RoomSupport? = null

        fun createInstance(context: Context) {
            if (instance != null) {
                println("Room Instance already exist overriding")
            }
            instance = RoomSupport(context)
        }

        fun getInstance(): RoomSupport {
            if (instance == null) {
                throw IllegalArgumentException("Roomdatabase not initialied ..")
            }
            return instance!!
        }
    }

}