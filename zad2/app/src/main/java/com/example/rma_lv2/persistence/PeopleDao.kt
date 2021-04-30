package com.example.rma_lv2.persistence

import androidx.room.*
import com.example.rma_lv2.model.Person

@Dao
interface PeopleDao {

    @Query("SELECT * FROM people")
    fun getPeople(): List<Person>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(person: Person)

    @Delete
    fun delete(person: Person)

    @Query("SELECT * FROM people WHERE id=:id")
    fun getPerson(id: Int): Person

    @Query("UPDATE people SET name=:name WHERE id = :id")
    fun updateName(name: String?, id: Int)

    @Query("UPDATE people SET date=:date WHERE id = :id")
    fun updateDate(date: String?, id: Int)

    @Query("UPDATE people SET description=:description WHERE id = :id")
    fun updateDescription(description: String?, id: Int)

    @Query("UPDATE people SET imageUrl=:imageUrl WHERE id = :id")
    fun updateImageUrl(imageUrl: String?, id: Int )

    /**
     * potrebno nakon brisanja kako bi se idovi prilagodili pozicijama u adapteru
     */
    @Query("UPDATE people SET id=:newId WHERE id=:id")
    fun updateId(newId: Int,id: Int)

    @Query("UPDATE people SET quotes=:newQuotes WHERE id=:id")
    fun addQuotes(newQuotes: ArrayList<String>,id: Int)
}