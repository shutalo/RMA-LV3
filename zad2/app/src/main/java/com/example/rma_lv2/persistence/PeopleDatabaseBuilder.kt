package com.example.rma_lv2.persistence

import androidx.room.Room
import com.example.rma_lv2.Persony

object PeopleDatabaseBuilder {

    private var instance: PeopleDatabase? = null

    fun getInstance(): PeopleDatabase {
        synchronized(PeopleDatabase::class) {
            if (instance == null) {
                instance = buildDatabase()
            }
        }
        return instance!!
    }

    private fun buildDatabase(): PeopleDatabase {
        return Room.databaseBuilder(
            Persony.application, PeopleDatabase::class.java,
            PeopleDatabase.NAME)

            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}