package com.example.rma_lv2.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rma_lv2.model.Person
import com.example.rma_lv2.utilities.Converters

@Database(entities = [Person::class], version = 1)
@TypeConverters(Converters::class)
abstract class PeopleDatabase: RoomDatabase() {
    abstract fun peopleDao(): PeopleDao

    companion object {
        const val NAME = "inspiringPeopleDb"
    }
}