package com.example.rma_lv2.persistence

import com.example.rma_lv2.model.Person

object PeopleRepository {

    private val people = mutableListOf<Person>()

    fun getPeople(): List<Person> = people
    fun getPerson(position: Int): Person = people[position]
    fun insert(person: Person) = people.add(person)
    fun delete(position: Int) = people.removeAt(position)
    fun getLastIndex():Int = people.size - 1
}