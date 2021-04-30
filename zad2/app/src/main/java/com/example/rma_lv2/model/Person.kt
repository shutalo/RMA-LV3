package com.example.rma_lv2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people")
data class Person(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "imageUrl") var imageURL: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "quotes") val quotes: ArrayList<String>
)